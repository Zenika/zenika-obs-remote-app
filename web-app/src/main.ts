import Vue from 'vue';
import Buefy from 'buefy';
import 'buefy/dist/buefy.css';
import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css';
import App from './App.vue';
import router from './router';

Vue.config.productionTip = false;
Vue.use(Buefy);
Vue.use(Vuetify);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app');
