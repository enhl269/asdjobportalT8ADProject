FROM python:3.6.13

RUN yum install python3.7 -y && \
    yum install python3-pip -y && \
    yum install zip -y && \
    pip3 install -U pip

WORKDIR /app
COPY . .

RUN pip3 install -r ./requirements.txt
CMD python bertAPI.py
