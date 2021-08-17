FROM python:3.6.13

RUN sudo apt-get update && \
    sudo apt-get install python3.7 -y && \
    sudo apt-get install python3-pip -y && \
    sudo apt-get install zip -y && \
    sudo pip3 install -U pip

WORKDIR /app
COPY . .

RUN pip3 install -r ./requirements.txt
CMD python bertAPI.py
