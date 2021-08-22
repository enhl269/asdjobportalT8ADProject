

import json
import requests
import time
import numpy as np
import http
import pandas as pd
def feature_encode():
    '''
        inference category from item name

        input:
        nlp_content(list):list of item names

        output:
        category1:list of standard label
    '''

    SERVER_URL='http://127.0.0.1:5000/'#local URL(for test)
    num=1
    keywords='cleaner'
    location='Singapore'
   
    data={
            'num':num,
            'keywords':keywords,
            'location':location
    }
    
    df= requests.post(SERVER_URL, json=(data)).json()['res']#use url
    
    print(df)
    #df_n=df.json()['res']
    



if __name__ == "__main__":
    feature_encode()
