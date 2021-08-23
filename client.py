

import json
import requests
import time
import numpy as np
import http
import pandas as pd
def feature_encode():
  
    SERVER_URL='http://127.0.0.1:5000/'#local URL(for test)
    #---------------------------------Variable-------------------------------------
    num=1
    keywords='cleaner'
    location='Singapore'
     #-------------------------------request ------------------------------------------------------
    data={
            'num':num,
            'keywords':keywords,
            'location':location
    }
    df= requests.post(SERVER_URL, json=(data)).json()['res']#use url
    #print(df.dtype())
    print(df)
    #df_n=df.json()['res']
    



if __name__ == "__main__":
    feature_encode()
