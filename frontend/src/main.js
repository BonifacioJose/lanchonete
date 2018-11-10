import Vue from 'vue'
import './plugins/vuetify'
import Vuetify from 'vuetify'
import App from './App.vue'
import router from './router'
import store from './store'
import Axios from 'axios'

Vue.config.productionTip = false
Axios.defaults.baseURL = 'http://lanchonete.dextra.com'

Vue.use(Vuetify)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
