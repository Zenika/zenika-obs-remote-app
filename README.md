# zenika-obs-remote-app
A nodejs remote api for OBS studio

## Getting started

Before running the app, you'll have to set project's modules in this order:
1. Set the nginx-rtmp proxy
2. Set the OBS app
3. Set the API
4. Set the web application 


### Proxy config 

Create the proxy's docker image:  
```
docker build -t proxy-for-obs /proxy
```

Run a docker container:  
```
docker run --rm -p 80:80 -p 1935:1935 -p 8090:8090 -v <absolute-path-to-your-local-repo>/proxy/nginx.conf:/etc/nginx/nginx.conf --name proxy -d proxy-for-obs
``` 


### OBS config

First, launch the OBS application

Then import the `Default2` profile located at *config/obs_config/Default2*
> Profil -> Importer

In the **audio mixer** box mute *Mic/Aux*  
Open vlc media player and open your camera stream :
> Default until now is **rtsp://admin:zenika@192.168.1.58:554/11**

In the **souces** box select the `dericam-vlc` source and then select the vlc media player's window

You can now start streaming


### API config

This api uses some environment variables, so make sure to set them before starting.  
Avalaible modes are : **dev**, **test** or **prod***

You can configure them with files located at **config/api_config/.env.api.***  
Each of these modes is related to other environment variables that you can set : 
*Please replace <mode> with **DEV**, **TEST** or **PROD**
* `process.env.<mode>_APP_HOST` will set the api's hostname (default is *localhost*)
* `process.env.<mode>_APP_PORT` will set the api's port (default is *3000*)
* `process.env.<mode>_OBS_HOST` will set OBS's hostname (default is *localhost*)
* `process.env.<mode>_OBS_PORT` will set OBS's port (default is *4444*)
* `process.env.<mode>_OBS_PWD` will set OBS's password 
* `process.env.<mode>_CAMERA_USER` will set the camera's username (default is *admin*)
* `process.env.<mode>_CAMERA_PWD` will set the camera's username (default is *zenika*)
* `process.env.<mode>_CAMERA_PROTOCOL` will set the camera's protocol (default is *http*)
* `process.env.<mode>_CAMERA_HOST` will set the camera's hostname 

Create the api's docker image:
```
docker build -t api-for-obs api/
```

Run a docker container:
```
docker run --rm -p 3000:3000 -e NODE_ENV=<mode> -v <absolute-path-to-your-local-file>/config/api_config/config.js:/usr/src/app/config.js --net=host --name api -d api-for-obs
```


### Web-App Config

Then set some environment variables before running.
Available modes are : **development** and **production**

You can configure them with files located at **config/.env.webapp.***

**VUE_APP_API_REMOTE_URL=<api-host>:<api-host:port>/obs**
* **api-host**: hostname of the obs remote api. Default is localhost

* **api-port**: port used by the obs remote api. Default is 3000

**VUE_APP_PREVIEW_URL=<proxy-host>:<proxy-port>/hls/<video-stream-name>.m3u8**
* **proxy-host**: hostname of the proxy server for hls streaming. Default is localhost

* **api-port**: port used by the proxy server. Default is 8090   


Create the web-app's docker image
```
docker build -t web-app-for-obs web-app/
```

Run a docker container :
```
docker run --rm -p 8080:8080 --env-file=config/webapp_config/.env.<mode> -v <absolute-path-to-your-local-file>:/app/api.json --net=host --name web-app -d web-app-for-obs
```
