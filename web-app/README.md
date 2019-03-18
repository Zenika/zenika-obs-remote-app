# front-obs-remote

## Project setup
```
npm install
```
First, set some environment variables before starting.
* `export NODE_ENV=<mode>` in order to set the mode.
   Available modes are : **development** and **production**

* `export VUE_APP_API_REMOTE_URL=<api-host>:<api-host:port>/obs`
  
> **api-host**: hostname of the obs remote api. Default is localhost

> **api-port**: port used by the obs remote api. Default is 3000
   
* `export VUE_APP_PREVIEW_URL=<proxy-host>:<proxy-port>/hls/<video-stream-name>.m3u8`

> **proxy-host**: hostname of the proxy server for hls streaming. Default is localhost

> **api-port**: port used by the proxy server. Default is 8090

Then run `npm run serve`

Compiles and hot-reloads for development
```
npm run serve
```

Compiles and minifies for production
```
npm run build
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
