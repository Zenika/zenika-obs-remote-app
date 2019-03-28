module.exports = {
  configureWebpack: config => {
    if (process.env.NODE_ENV === 'production') {
      let VUE_APP_API_URL = 'http://production:3000/obs/';
      let VUE_APP_PREVIEW_URL = 'http://192.168.1.109:3000/obs/';
    } else {
      let VUE_APP_API_URL = 'http://localhost:3000/obs/';
      let VUE_APP_PREVIEW_URL = 'http://192.168.1.109:3000/obs/';
    }
  }
};
