#use BERT to detect bad items from item names
import os
import pandas as pd
import numpy as np
import tensorflow as tf
from keras_bert import AdamWarmup, calc_train_steps
from tensorflow import keras
from keras_bert import get_base_dict, get_model, compile_model, gen_batch_inputs
from keras_bert import load_trained_model_from_checkpoint, get_custom_objects,Tokenizer
from keras_bert import get_pretrained, PretrainedList, get_checkpoint_paths, load_trained_model_from_checkpoint


#from bert_generator_pretrain import Generator
import shutil
#set hyper paremeters
os.environ['CUDA_VISIBLE_DEVICES']='0'
SEQ_LEN=512
BATCH_SIZE = 8
EPOCHS = 50
LR = 5e-5
NUM_CLASSES=2
#NUM_CLASSES_business=len(labels_business)
def preprocess_data_inference(paths,texts_train):
    #preprocess data for inference
    # Build token dictionary and initialize tonkenizer 建立字典，把wordpiece映射成向量
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



def preprocess_data_finetune(paths,texts_train,label_train):
    # Build token dictionary and initialize tonkenizer
    token_dict = {}
    with open(paths.vocab, 'r',encoding='UTF-8') as reader:
        for line in reader:
            token = line.strip()
            token_dict[token] = len(token_dict)
    tokenizer = Tokenizer(token_dict)

    #encode
    indices, labels,words = [], [],[]
    for i,j in zip(texts_train,label_train):
        ids, segments = tokenizer.encode(i, max_len=SEQ_LEN)
       
        
        label=j
        print(label)
        # print(label_train[j])
        indices.append(ids)
        labels.append(label)
    items = list(zip(indices, labels))
    np.random.seed(0)
    np.random.shuffle(items)
    indices, labels = zip(*items)
    indices = np.array(indices)
    mod = indices.shape[0] % BATCH_SIZE
    if mod > 0:
        indices, labels = indices[:-mod], labels[:-mod]
    return [indices, np.zeros_like(indices)], np.array(labels),token_dict



def main():
# -----------------------------------------1.preprocess NLP data-----------------------------------------------
    df=pd.read_csv('./dataset/data.csv')
    # df=df.head(2)
    # print(df)

    model_path = './uncased_L-2_H-128_A-2'
    paths = get_checkpoint_paths(model_path)
    
    X=[]
    for i in range(df.shape[0]):
        X.append('Job Title:'+str(df.loc[i,'Job Title'])+',Job Description:'+ str(df.loc[i,'Job Description']))    
    texts_train=X
    label_train=df['label']
    # print(label_train)
    print('finetune---------------------------------------------------------')
    train_x, train_y, token_dict=preprocess_data_finetune(paths,texts_train,label_train)
    fine_tune(train_x,train_y,paths,token_dict)

  
def get_model(paths):
    model = load_trained_model_from_checkpoint(
        config_file=paths.config,
        checkpoint_file=paths.checkpoint,
        training=True,#pretrain
        trainable=True,
        # output_layer_num=2,
    )
    #模型结构
    # model.load_weights('./checkpoints/uncased_L-2_H-128_A-2/updated_model_checkpoint_0.4623.h5',by_name=True)
    #load pretrain model
    inputs = model.inputs[:2]
    dense =model.get_layer('NSP-Dense').output
    outputs = keras.layers.Dense(units=NUM_CLASSES, activation='softmax')(dense)
    model = keras.models.Model(inputs, outputs)
    return model
# #------------------------------------------3. fine tune model-------------------------------------------------
def fine_tune(train_x,train_y,paths,token_dict):
    #get model structure
    model=get_model(paths)
    model.load_weights('./uncased_L-2_H-128_A-2/finetune_model_1.0000.h5',by_name=True) # load entire model
    checkpoint = keras.callbacks.ModelCheckpoint(
    './uncased_L-2_H-128_A-2/finetune_model'+f'_{{val_sparse_categorical_accuracy:.4f}}'+'.h5',
    verbose=1,
    save_weights_only=False,
    save_best_only=False,
    monitor="val_loss",
    mode='min'
    )  # define model chekpoint 每次训练最末尾都会储存在文件里面
    model.summary()
    model.compile(
        tf.keras.optimizers.Adam(lr=LR, decay=1e-7),
        loss='sparse_categorical_crossentropy',
        metrics=['sparse_categorical_accuracy'],
    )
    
    model.fit(
        train_x,
        train_y,
        epochs=EPOCHS,
        batch_size=BATCH_SIZE,
        callbacks=[checkpoint],
        validation_split=0.2,
        shuffle=True,
        # steps_per_epoch=2,
        # validation_steps=2,
    )


def inference():
    df=pd.read_csv('./try_data.csv',encoding="utf8")
    # df=df.head(5)
    model_path = './uncased_L-2_H-128_A-2'
    paths = get_checkpoint_paths(model_path)
    X=[]
    for i in range(df.shape[0]):
        X.append('Job Title:'+df.loc[i,'Job Title']+',Job Description:'+df.loc[i,'Job Description'])    
    texts_train=X
   
    x, token_dict=preprocess_data_inference(paths,texts_train)
    model = get_model(paths)
    # get_model建立model在这个路径上。
    # model = keras.models.load_model(filepath=('./uncased_L-2_H-128_A-2/finetune_model_1.0000.h5'),compile=False)
    model.load_weights('./uncased_L-2_H-128_A-2/finetune_model_1.0000.h5')
    #加载之前训练好的参数
    model.summary()
    #predict
    #df['predict_label']=[ ]
    ff =np.argmax(model.predict(x, verbose=True),axis=1)
    #aa=model.predict(x)
    print(ff)
    df['label']=ff
    
    df.to_csv('./dataset/test.csv')

    

if __name__ == "__main__":
     #main()
     inference()