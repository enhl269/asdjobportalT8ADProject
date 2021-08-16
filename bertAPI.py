# middle client script between TF-S and backend

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
        training=True,#pretrain
        trainable=True,
        # output_layer_num=2,
    )
    # model.load_weights('./checkpoints/uncased_L-2_H-128_A-2/updated_model_checkpoint_0.4623.h5',by_name=True)
    #load pretrain model
    inputs = model.inputs[:2]
    dense =model.get_layer('NSP-Dense').output
    outputs = keras.layers.Dense(units=2, activation='softmax')(dense)
    model = keras.models.Model(inputs, outputs)
    return model
paths = get_checkpoint_paths(model_path)
model=get_model(paths)
model.load_weights(model_weight)

#get URL of image from the client
class get_url(Resource):
    # @app.route('/')
    def post(self):
        
        
        #get item name
        nlp_contents=request.form.getlist('nlp_content')
        print(len(nlp_contents),' cases received')
        
        
        x, token_dict=preprocess_data_inference(paths,nlp_contents)
        res=np.argmax(model.predict(x, verbose=True),axis=1).tolist()
        print(res)
        print('success response')
        
   
        
        return jsonify(res=res)
    
    
    
api.add_resource(get_url, '/')

if __name__ == '__main__':
    # from waitress import serve
    # serve(app, host="0.0.0.0", port=5000)
    app.run(debug=False)