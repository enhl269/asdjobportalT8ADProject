FROM python:3.6.13

RUN apt-get update && \
    apt-get install python3.7 -y && \
    apt-get install python3-pip -y && \
    apt-get install zip -y && \
    pip3 install -U pip

WORKDIR /app
COPY . .
RUN wget https://storage.googleapis.com/bert_models/2018_11_23/multi_cased_L-12_H-768_A-12.zip && \
    uncased_L-2_H-128_A-2.zip
RUN pip3 install -r ./requirements.txt
CMD python bertAPI.py

