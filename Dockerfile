FROM python:3.6.13

RUN apt-get update && \
    apt-get install python3.7 -y && \
    apt-get install python3-pip -y && \
    apt-get install zip -y && \
    pip3 install -U pip

WORKDIR /app
COPY . .
RUN wget --no-check-certificate 'https://docs.google.com/uc?export=download&id=1-Y1IlzS6YXC3Xy5BaaqfNnNhquC8NUKv' && \
unzip uncased_L-2_H-128_A-2.zip
RUN pip3 install -r ./requirements.txt
CMD python bertAPI.py

