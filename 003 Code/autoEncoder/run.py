from flask import Flask, request, jsonify #간단히 플라스크 서버를 만든다
import pandas as pd 
import urllib.request
import json
import time

# 이상탐지
import AE

app = Flask(__name__)
#app.config['TO_JSON_PRETTYPRINT_REGULAR'] = True 

@app.route("/")
def start():
    AE.main()
    
if __name__ == '__main__':
    app.run(debug=False,host="127.0.0.1",port=5000)