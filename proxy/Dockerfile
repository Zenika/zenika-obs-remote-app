FROM ubuntu:18.04 AS proxy-base-essential
# Get essential elements
RUN apt-get update \
    && apt-get install -y build-essential libpcre3 libpcre3-dev libssl-dev


FROM proxy-base-essential AS proxy-base-nginx-rtmp
# Get nginx and nginx rtmp module
RUN apt-get install -y wget \
    && wget http://nginx.org/download/nginx-1.15.1.tar.gz \
    && wget https://github.com/sergey-dryabzhinsky/nginx-rtmp-module/archive/dev.zip


FROM proxy-base-nginx-rtmp AS proxy-base-nginx-rtmp-install

RUN apt-get install unzip \
    && tar -zxvf nginx-1.15.1.tar.gz \
    && unzip dev.zip
RUN apt-get install --reinstall zlibc zlib1g zlib1g-dev \
    && cd nginx-1.15.1 \
    && ./configure --with-http_ssl_module --add-module=../nginx-rtmp-module-dev \
    && make \
    && make install

COPY nginx.conf /usr/local/nginx/conf/


FROM proxy-base-nginx-rtmp-install AS proxy

RUN /usr/local/nginx/sbin/nginx
EXPOSE 80
# CMD [ "/usr/local/nginx/sbin/nginx" ]
