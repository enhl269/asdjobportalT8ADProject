

import json
import requests
import time
import numpy as np
import http
import pandas as pd

BATCH_SIZE=16


def feature_encode():
    '''
        inference category from item name

        input:
        nlp_content(list):list of item names

        output:
        category1:list of standard label
    '''

    SERVER_URL='http://47.241.209.188:5000/'#local URL(for test)

    #------------------------1.read data----------------------------
    df=pd.read_csv('./dataset/crawl_data.csv')
    
    X=[]
    for i in range(df.shape[0]):
        X.append('Job Title:'+str(df.loc[i,'Job Title'])+',Job Description:'+ str(df.loc[i,'Job Description']))    
    #------------------------2.vectorization by calling the API----------------------------
    if len(X)>BATCH_SIZE:
        groups= [[X[j % len(X)] for j in range(i, i + BATCH_SIZE)] for i in
                range(0, len(X), BATCH_SIZE)]
        
        groups[-1]=groups[-1] if len(X)%BATCH_SIZE ==0 else groups[-1][:(len(X)%BATCH_SIZE)]
    else:
        groups=[X]
        
    represen=[]
    c=0
    for X_item in groups:
        data={
                'nlp_content':X_item
        }
        print(c)
        represen += requests.post(SERVER_URL, data=(data)).json()['res']#use url
        print(represen)
        #represen=represen.json()['res']
        c+=BATCH_SIZE
    print(len(represen))
    represen=np.array(represen)



if __name__ == "__main__":
    feature_encode()
