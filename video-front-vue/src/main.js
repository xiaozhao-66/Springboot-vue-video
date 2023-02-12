import Vue from 'vue'
import axios from 'axios'
import store from './store'
import Element from 'element-ui'
import VueAliplayerV2 from 'vue-aliplayer-v2'
import VueQriously from 'vue-qriously'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import "element-ui/lib/theme-chalk/index.css"
import App from './App.vue'
import router from './router'
import "./permission"
// 使用vue加载组件

Vue.use(Element)
Vue.use(VueAliplayerV2);
Vue.use(VueQriously)
Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.use(ViewUI);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
