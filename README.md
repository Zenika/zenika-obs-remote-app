# zenika-obs-remote-app
A nodejs remote api for OBS studio

## Getting started

Before running the app, you'll have to set project's modules in this order:
1. Set the nginx-rtmp proxy
2. Set the OBS app
3. Set the API
4. Set the web application 


### Proxy config 

Get the necessary tools to build nginx:  
```
sudo apt-get install build-essential libpcre3 libpcre3-dev libssl-dev 
```

From your home directory, get the nginx source code:  
```
wget http://nginx.org/download/nginx-1.15.1.tar.gz
``` 
or higher version

Next, get the RTMP module source code from git:  
```
wget https://github.com/sergey-dryabzhinsky/nginx-rtmp-module/archive/dev.zip
```

Unpack/unzip them both, and enter the nginx directory:  
```
tar -zxvf nginx-1.15.1.tar.gz
unzip dev.zip
cd nginx-1.15.1 
```

Build nginx:
```
./configure --with-http_ssl_module --add-module=../nginx-rtmp-module-dev
make
sudo make install 
```

Start nginx first with the command below:  
```
sudo /usr/local/nginx/sbin/nginx
```

To stop nginx:
```
sudo /usr/local/nginx/sbin/nginx -s stop
```


### OBS config

First, launch the OBS application

Create sources
> - **sticker** is an `image source` located at *obs_config/zenika-sticker1.png*.
> - **speaker-screen** is a `video capture device` 
> - **dericam** is a `media source` with this url: `rtsp://admin:zenika@192.168.1.58:554/11`
> - **background** is an `image source` located at *obs_config/white-bg.png*  
>  add this filter to dericam
> - **mic** is an `audio capture device`
> - **obs-screen** is a `screen capture`

 Create 4 scenes : 
> - Main
>> With sources in this order:  
>> *sticker*, *speaker-screan*, *dericam*, *mic*  
>> Manage to dispose sources in order to have the same picture as
![scene_main!](config/obs_config/scene_main.png "scene_main")

> - Screen
>> With sources in this order:  
>> *speaker-screan*, *mic*  
> - Speaker
>> With sources in this order:  
>> *sticker*, *dericam*, *mic*  
>> Manage to dispose sources in order to have the same picture as
![scene_speaker!](config/obs_config/scene_speaker.png "scene_speaker")
> - OBS
>> With sources in this order:  
>> *obs-screan*, *mic*  
> - Speaker
>> With sources in this order:  
>> *sticker*, *background*  
>> Manage to dispose sources in order to have the same picture as
![standby!](config/obs_config/standby.png "scene_speaker")

In the **audio mixer** box, mute *dericam* and *Mic/Aux* 


### API config

This api uses some environment variables, so make sure to set them before starting.  
In order to set the mode to **dev**, **test** or **prod**  
``` 
source config/.env.api.<mode>
```
You can configure those files located at **config/.env.api.***

Then run the api with 
```
npm run start
```

Later with docker
```
docker run --rm -p 3000:3000 --env-file=config/.env.api.dev --name api --network=host --hostname=api api-for-obs
```


### Web-App Config
Get all dependancies by running
```
npm install
```
Then set some environment variables before running.  
In order to set the mode.
Available modes are : **development** and **production**
```
source config/.env.webapp.<mode>
```
You can configure those files located at **config/.env.webapp.***

**VUE_APP_API_REMOTE_URL=<api-host>:<api-host:port>/obs**
* **api-host**: hostname of the obs remote api. Default is localhost

* **api-port**: port used by the obs remote api. Default is 3000

**VUE_APP_PREVIEW_URL=<proxy-host>:<proxy-port>/hls/<video-stream-name>.m3u8**
* **proxy-host**: hostname of the proxy server for hls streaming. Default is localhost

* **api-port**: port used by the proxy server. Default is 8090   


Compiles and hot-reloads for development
```
npm run serve
```

Compiles and minifies for production
```
npm run build
```

Docker command
```
docker run --rm -p 8080:5000 --env-file=config/webapp_config/.env.production -v /home/zenika/projects/obs/zenika-obs-remote-app/config/webapp_config/api.json:/app/api.json --name web-app web-app-for-obs
```

Run your tests
```
npm run test
```

Lints and fixes files
```
npm run lint
```

Run your unit tests
```
npm run test:unit
```

Customizing configuration

See [Configuration Reference](https://cli.vuejs.org/config/).
