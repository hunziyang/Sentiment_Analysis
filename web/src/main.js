import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import Moment from 'moment'

Vue.config.productionTip = false
Vue.prototype.moment = Moment

new Vue({
    render: h => h(App),
    router
}).$mount('#app')
