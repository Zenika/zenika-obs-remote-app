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
$ sudo apt-get install build-essential libpcre3 libpcre3-dev libssl-dev 
```

From your home directory, get the nginx source code:  
```
$ wget http://nginx.org/download/nginx-1.15.1.tar.gz
``` 
or higher version

Next, get the RTMP module source code from git:  
```
$ wget https://github.com/sergey-dryabzhinsky/nginx-rtmp-module/archive/dev.zip
```

Unpack/unzip them both, and enter the nginx directory:  
```
$ tar -zxvf nginx-1.15.1.tar.gz
$ unzip dev.zip
$ cd nginx-1.15.1 
```

Build nginx:
```
$ ./configure --with-http_ssl_module --add-module=../nginx-rtmp-module-dev
$ make
$ sudo make install 
```

Start nginx first with the command below:  
```
$ sudo /usr/local/nginx/sbin/nginx
```

To stop nginx:
```
$ sudo /usr/local/nginx/sbin/nginx -s stop
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
![scene_main!](./obs_config/scene_main.png "scene_main")

> - Screen
>> With sources in this order:  
>> *speaker-screan*, *mic*  
> - Speaker
>> With sources in this order:  
>> *sticker*, *dericam*, *mic*  
>> Manage to dispose sources in order to have the same picture as
![scene_speaker!](./obs_config/scene_speaker.png "scene_speaker")
> - OBS
>> With sources in this order:  
>> *obs-screan*, *mic*  
> - Speaker
>> With sources in this order:  
>> *sticker*, *background*  
>> Manage to dispose sources in order to have the same picture as
![standby!](./obs_config/standby.png "scene_speaker")

In the **audio mixer** box, mute *dericam* and *Mic/Aux* 

### API config

This api uses some environment variables, so make sure to set them before starting.
* `NODE_ENV` in order to set the mode to **dev**, **test** or **prod**  
`export NODE_ENV=<mode>`

Then run the api with `npm run start`
