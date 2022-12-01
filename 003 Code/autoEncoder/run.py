from flask import Flask, request, jsonify #간단히 플라스크 서버를 만든다
import pandas as pd 
import urllib.request
import json
from flask_cors import CORS
CORS(app, resources={r'*': {'origins': 'https://127.0.0.1'}})
app = Flask(__name__)

# 이상탐지
import AE

app = Flask(__name__)
app.config['TO_JSON_PRETTYPRINT_REGULAR'] = True 

@app.route("/Dashboard/risk")
def spring():
    data = AE.silhang()
    json_data = data.to_json(orient = 'records', indent = 4)
    json_test = jsonify(json_data)
    return json_data
    
if __name__ == '__main__':
    app.run(debug=False,host="127.0.0.1",port=5000)