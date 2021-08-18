FROM python:3.6.13

RUN apt-get update && \
    apt-get install python3.7 -y && \
    apt-get install python3-pip -y && \
    apt-get install zip -y && \
    pip3 install -U pip

WORKDIR /app
COPY . .

RUN pip3 install -r ./requirements.txt
CMD python bertAPI.py

