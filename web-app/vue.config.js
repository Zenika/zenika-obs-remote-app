module.exports = {
  configureWebpack: config => {
    if (process.env.NODE_ENV === 'production') {
      // mutate config for production...
    } else {
      let VUE_APP_API_LOCAL_URL = 'http://localhost:3000/obs/';
      let VUE_APP_API_REMOTE_URL = 'http://192.168.1.52:3000/obs/';
    }
  }
};
