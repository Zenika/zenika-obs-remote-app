# zenika-obs-remote-api
A nodejs remote api for OBS studio

## Getting started

### Proxy config 

Start nginx first with the command below:
> sudo /usr/local/nginx/sbin/nginx

### OBS config

First, launch the OBS application

Go into the **obs_config** directory and follow this instructions

> Create sources
>> - **sticker** is an `image source` located at *obs_config/zenika-sticker1.png*.
>> - **speaker-screen** is a `video capture device` 
>> - **dericam** is a `media source` with this url: `rtsp://admin:zenika@192.168.1.58:554/11`
>> - **background** is an `image source` located at *obs_config/white-bg.png

> Create 4 scenes : 
>>1. Main
>>2. Screen
>>3. Speaker
>>4. OBS

### API config

This api uses some environment variables, so make sure to set them before starting.
* `NODE_ENV` in order to set the mode to `dev`, `test` or `prod`
> export NODE_ENV=`mode`

Then run the api with `npm run start`

## Installation
