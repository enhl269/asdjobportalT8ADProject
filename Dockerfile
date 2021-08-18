FROM python:3.6.13

RUN apt-get update && \
    apt-get install python3.7 -y && \
    apt-get install python3-pip -y && \
    apt-get install zip -y && \
    pip3 install -U pip

WORKDIR /app
COPY . .
RUN wget --load-cookies /tmp/cookies.txt "https://docs.google.com/uc?export=download&confirm=$(wget --quiet --save-cookies /tmp/cookies.txt --keep-session-cookies --no-check-certificate 'https://docs.google.com/uc?export=download&id=1-Y1IlzS6YXC3Xy5BaaqfNnNhquC8NUKv' -O- | sed -rn 's/.*confirm=([0-9A-Za-z_]+).*/\1\n/p')&id=1-Y1IlzS6YXC3Xy5BaaqfNnNhquC8NUKv" -O uncased_L-2_H-128_A-2.zip && rm -rf /tmp/cookies.txt
RUN pip3 install -r ./requirements.txt
CMD python bertAPI.py

