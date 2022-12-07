import pandas as pd 
import numpy as np
import tensorflow as tf
from datetime import datetime
import statistics
# connect to db
import pymysql
import sqlalchemy
from sqlalchemy import create_engine
# misc
import random as rn
from sklearn.model_selection import train_test_split
#pipeline
from sklearn.preprocessing import Normalizer, MinMaxScaler
from sklearn.pipeline import Pipeline
# manual parameters
RANDOM_SEED = 42
TRAINING_SAMPLE = 200000
VALIDATE_SIZE = 0.2

import mysql_auth
login = mysql_auth.info

import time

# db 테이블 df로 가져오기
def get_data():
    conn = pymysql.connect(
        host=login['host'], user=login['user'], 
        password=login['passwd'], db=login['db'], 
        charset=login['charset']
    )
    sql = 'SELECT * FROM {};'.format(login['column'])
    df = pd.read_sql(sql,conn)
    date=df['FILE_DT']
    data = df[df.columns.difference(['EQUIPMENT_NAME', 'DATA_NO', 'FILE_NAME', 'FILE_DT', 'FILE_TIME', 'FIRST_REGIST_DATE', 'NOISE' , 'Unnamed: 0'])]
    data[:20]
    data = data.apply(pd.to_numeric)
    if(data.isnull().values.any()):
        data = data.fillna(0)
    # data.set_index('EQUIPMENT_NAME',inplace=True)
    # data = data.astype('float')   #inplace 가 없음
    # data.reset_index(inplace=True)
    return data, date, conn

def pipeline(X_train, X_validate):
    print(X_train)
    pipeline = Pipeline([('normalizer', Normalizer()),
                     ('scaler', MinMaxScaler())])
    pipeline.fit(X_train)

    X_train_transformed = pipeline.transform(X_train)
    X_validate_transformed = pipeline.transform(X_validate)

    return X_train_transformed, X_validate_transformed

# 데이터 처리
def data_prop(data):
    np.random.seed(RANDOM_SEED)
    rn.seed(RANDOM_SEED)
    tf.random.set_seed(RANDOM_SEED)
    X_train, X_test = train_test_split(data,test_size=0.2,shuffle=False,random_state=1004)
    #X_train.drop('EQUIPMENT_NAME', axis=1, inplace=True)
    print(X_train[:2])
    #X_test.drop('EQUIPMENT_NAME', axis=1, inplace=True)
    # X_train.astype('float')
    # X_test.astype('float')
    X_train, X_validate = train_test_split(X_train, 
                                       test_size=VALIDATE_SIZE, 
                                       random_state=RANDOM_SEED)
    X_test = X_test.values

    return X_train, X_validate, X_test  

# 오토인코더 
def model(X_train_transformed, X_validate_transformed, data_out, X_train):
    input_dim = X_train_transformed.shape[1]
    BATCH_SIZE = 256
    EPOCHS = 6
    # 모델 불러오기
    # if(autoencoder.load('autoencoder_best_weights.hdf5')):
    #      continue
    # else

    # https://keras.io/layers/core/
    autoencoder = tf.keras.models.Sequential([  
        # deconstruct / encode
        tf.keras.layers.Dense(input_dim, activation='elu', input_shape=(input_dim, )), 
        tf.keras.layers.Dense(16, activation='elu'),
        tf.keras.layers.Dense(8, activation='elu'),
        tf.keras.layers.Dense(4, activation='elu'),
        tf.keras.layers.Dense(2, activation='elu'),
        # reconstruction / decode
        tf.keras.layers.Dense(4, activation='elu'),
        tf.keras.layers.Dense(8, activation='elu'),
        tf.keras.layers.Dense(16, activation='elu'),
        tf.keras.layers.Dense(input_dim, activation='elu')
        ])

    autoencoder.compile(optimizer="adam", 
                    loss="mse",
                    metrics=["acc"])


    #####
    # current date and time
    yyyymmddHHMM = datetime.now().strftime('%Y%m%d%H%M')

    # new folder for a new run
    log_subdir = f'{yyyymmddHHMM}_batch{BATCH_SIZE}_layers{len(autoencoder.layers)}'

    # define our early stopping
    early_stop = tf.keras.callbacks.EarlyStopping(
    monitor='val_loss',
    min_delta=0.0001,
    patience=10,
    verbose=1, 
    mode='min',
    restore_best_weights=True
    )

    save_model = tf.keras.callbacks.ModelCheckpoint(
        filepath='autoencoder_best_weights.hdf5',
        save_best_only=True,
        monitor='val_loss',
        verbose=0,
        mode='min'
    )

    tensorboard = tf.keras.callbacks.TensorBoard(
        f'logs/{log_subdir}',
        batch_size=BATCH_SIZE,
        update_freq='batch'
    )

    # callbacks argument only takes a list
    cb = [early_stop, save_model, tensorboard]
    history = autoencoder.fit(
        X_train_transformed, X_train_transformed,
        shuffle=True,
        epochs=EPOCHS,
        batch_size=BATCH_SIZE,
        callbacks=cb,
        validation_data=(X_validate_transformed, X_validate_transformed)
    );

    # autoencoder.save("model.h5")
    pipeline = Pipeline([('normalizer', Normalizer()),
                     ('scaler', MinMaxScaler())])
    pipeline.fit(X_train)

    X_test_transformed = pipeline.fit_transform(data_out)
    reconstructions = autoencoder.predict(X_test_transformed)

    return X_test_transformed, reconstructions

# 이상치 탐지 및 json으로 보낼 데이터 생성
def ab_detec(X_test_transformed, reconstructions, data, date):
    mse = np.mean(np.power(X_test_transformed - reconstructions, 2), axis=1)
    print(mse, reconstructions, X_test_transformed)
    z_score = mad_score(mse)
    THRESHOLD = threshold(z_score)
    #THRESHOLD = 45

    data["label"] = 0

    for i in range(len(z_score)):
        if(THRESHOLD<z_score[i]):
            data.loc[[i],["label"]] = 1 # 이상

    z_score = pd.DataFrame(z_score)
    z_score.columns=['z_score']
    label = pd.DataFrame(data['label'])
    label.columns=['label']
    # date = pd.DataFrame(data['FILE_DT'])
    # date.columns=['FILE_DT']
    result = pd.concat([date,z_score,label],axis=1)
    
    return result

# z_score 계산
def mad_score(points):
    m = np.median(points)
    ad = np.abs(points - m)
    mad = np.median(ad)
    
    return 0.6745 * ad / mad

# threshold 계산
def threshold(z_scores):
    out=[]
    THRESHOLD = 0
    #z_scores = mad_score(mse)
    outliers = z_scores > THRESHOLD
    answer = []
    print(len(z_scores))
    while(np.sum(outliers)/np.size(z_scores)>0):
        if(THRESHOLD==0):
            outliers = z_scores > THRESHOLD
            compare = np.sum(outliers)/np.size(z_scores)
        # out.append(compare)
        else:
            outliers = z_scores > THRESHOLD
            me = np.sum(outliers)/np.size(z_scores)
            if compare - me not in out:
                out.append(compare - me)
                answer.append(THRESHOLD)      
            compare = me
        THRESHOLD+=1
    #print(len(out))
    if(len(out)%2==0):
        out.append(max(out)+1)
    idx = []
    #print(len(out))
    m=statistics.median(list(set(out)))
    #print(out)
    idx=out.index(m)
    return answer[idx]

# main 함수
# def silhang():
def main():
    while True:
        data,date,conn = get_data()
        X_train, X_validate, X_test = data_prop(data)
        X_train_transformed, X_validate_transformed = pipeline(X_train, X_validate)
        print(X_train.columns)
        #data_out = data.drop(['EQUIPMENT_NAME'], axis=1, inplace=False)
        X_test_transformed, reconstructions = model(X_train_transformed, X_validate_transformed, data, X_train)
        result = ab_detec(X_test_transformed, reconstructions, data, date)

        pymysql.install_as_MySQLdb()
        engine = create_engine("mysql+mysqldb://" + "root" + ":" + "1234" + "@"+ "127.0.0.1" + ":" + "3306" + "/" + "kepri_data", encoding='utf-8')
        conn = engine.connect()
        dtype={'FILE_DT':sqlalchemy.types.INT(), 'z_score':sqlalchemy.types.FLOAT(), 'label':sqlalchemy.types.INT()}

        try:   
                result.to_sql(name='ae', con=engine, if_exists='replace', index=False, dtype=dtype)
        except:
            conn.rollback()
            print("It is Failed")
        conn.close()
        time.sleep(600)
    #return result

if __name__ == "__main__":
    main()