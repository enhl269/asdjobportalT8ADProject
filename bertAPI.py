
import json
import requests
import time
from flask import Flask, jsonify,request
from flask_restful import reqparse, abort, Api, Resource
from keras_bert import Tokenizer
import numpy as np
import pandas as pd
import http
import keras
from keras_bert import get_base_dict, get_model, compile_model, gen_batch_inputs,extract_embeddings
from keras_bert import load_trained_model_from_checkpoint, get_custom_objects
from keras_bert import get_pretrained, PretrainedList, get_checkpoint_paths, load_trained_model_from_checkpoint,POOL_NSP, POOL_MAX
from careerjet_api import CareerjetAPIClient

SEQ_LEN=512
BATCH_SIZE = 8
EPOCHS = 50
LR = 5e-5
NUM_CLASSES=2
batch_size=512
cased=True
poolings=[POOL_NSP]
model_path='./uncased_L-2_H-128_A-2'
model_weight='./uncased_L-2_H-128_A-2/finetune_model_1.0000.h5'
app = Flask(__name__)
api = Api(app)

def country(location):
    area={'Singapore':"en_SG",
          'Australia':"en_AU",
          'UAE':"en_AE",
          'Bangladesh':"en_BD",
          'Canada':"en_CA",
          'Ireland':"en_IE",
          'India':"en_IN",
          'Kuwait':"en_KW",
          'Malaysia':"en_MY",
          'New Zealand':"en_NZ",
          'Oman':"en_OM",
          'Philippines':"en_PH",
          'Pakistan':"en_PK",
          'Qatar':"en_QA",
          'UK':"en_GB",
          'USA':"en_US",
          'South Africa':"en_ZA",
          'Saudi Arabia':"en_SA",
          'Vietnam':"en_VN" 
          }
    c_country=area[location]
    return c_country
def careerjet_api(num,keywords,location):
    
    cj  =  CareerjetAPIClient(country(location));

    result_json = cj.search({
                            'location'    :  location,
                            'contractperiod':'f',
                                
                            'keywords'    :  keywords,
                            'pagesize'    :  num,
                            'affid'       : '213e213hd12344552',
                            'user_ip'     : '11.22.33.44',
                            'url'         : 'http://www.example.com/jobsearch?q=python&l='+location,
                            'user_agent'  : 'Mozilla/5.0 (X11; Linux x86_64; rv:31.0) Gecko/20100101 Firefox/31.0'
                          });
    
    cj_offers = result_json['jobs']
    cj_df = pd.DataFrame(cj_offers)
    print('CareerJet data downloaded succesfully')
    print(cj_df.columns)
    cj_df=cj_df.drop(['date','site'], axis=1)
    cj_df['Industry']=keywords
    cj_df['Type of Employment']='Full-time'
    re_col=['area','link','Job Title','Job Description','Company','Salary Range','Industry','Type of Employment']
    print(cj_df.columns)
    cj_df.columns=re_col
    
    print(cj_df.columns)
    
    print(cj_df.columns)
    # cj_df.to_csv('./try_data.csv')
    return cj_df

def preprocess_data_inference(paths,texts_train):
    #preprocess data for inference
    # Build token dictionary and initialize tonkenizer 
    token_dict = {}
    with open(paths.vocab, 'r',encoding='UTF-8') as reader:
        for line in reader:
            token = line.strip()
            token_dict[token] = len(token_dict)
    tokenizer = Tokenizer(token_dict)
    #encode
    indices,words = [],[]
    for i in texts_train:
        ids, segments = tokenizer.encode(i, max_len=SEQ_LEN)
        word=tokenizer.decode(ids)
        indices.append(ids)
    indices = np.array(indices)
    return [indices, np.zeros_like(indices)],token_dict #[token_embedding,segmentation_embedding],token_dict

    # print(label_train)
def get_model(paths):
    model = load_trained_model_from_checkpoint(
        config_file=paths.config,
        checkpoint_file=paths.checkpoint,
        training=True,
        trainable=True,
        # output_layer_num=2,
    )
    # model.load_weights('./checkpoints/uncased_L-2_H-128_A-2/updated_model_checkpoint_0.4623.h5',by_name=True)
   
    inputs = model.inputs[:2]
    dense =model.get_layer('NSP-Dense').output
    outputs = keras.layers.Dense(units=2, activation='softmax')(dense)
    model = keras.models.Model(inputs, outputs)
    return model


def inference(num,keywords,location):
    df=careerjet_api(num,keywords,location)
  
    paths = get_checkpoint_paths(model_path)
    X=[]
    for i in range(df.shape[0]):
        X.append('Job Title:'+df.loc[i,'Job Title']+',Job Description:'+df.loc[i,'Job Description'])    
    texts_train=X
   
    x, token_dict=preprocess_data_inference(paths,texts_train)
    model = get_model(paths)
  
    model.load_weights(model_weight)

    model.summary()
    #predict
    #df['predict_label']=[ ]
    ff =np.argmax(model.predict(x, verbose=True),axis=1)
    #aa=model.predict(x)
    print(ff)
    df['label']=ff
    return df


class get_url(Resource):
    # @app.route('/')
    def post(self):
        
        print('this part have number')
        #get item name
        content=request.json
        # print((content))
        print(content)
        num=content['num']
        print(num)
        keywords=content['keywords']
        location=content['location']
        
        
        
        df=inference(num,keywords,location)
        dic_df=df=df.values.tolist()
        print(df)
        print('success response')
        return jsonify(res=dic_df)
    
    
    
api.add_resource(get_url, '/')

if __name__ == '__main__':
 
    # serve(app, host="0.0.0.0", port=5000)
    app.run(debug=False)