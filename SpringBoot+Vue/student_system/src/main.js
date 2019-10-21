// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import axios from 'axios';
import VueAxios from 'vue-axios'
import eharts from 'echarts'

//引入导入导出相关JS
import XLSX from 'xlsx'


/*import Blob from "./vender/Blob.js"
import Export2Excel from "./vender/Export2Excel.js"*/

import mainLeft from "./components/mainLeft"
import mainTop from "./components/mainTop"
import updateDialog from "./components/updateDialog"


Vue.config.productionTip = false

Vue.use(XLSX)

Vue.use(ElementUI)
Vue.use(VueAxios,axios)
Vue.prototype.$axios = axios         //定义为对象方便后面调用
Vue.prototype.$echarts = eharts

//引用自定义组件
Vue.use(mainLeft)
Vue.use(mainTop)
Vue.use(updateDialog)

/*Vue.use(Blob)
Vue.use(Export2Excel)*/

//初始化自定义组件
Vue.component('main-left',mainLeft)
Vue.component('main-top',mainTop)
Vue.component('update-dialog',updateDialog)



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {
    App,
    mainLeft,
    mainTop,
    updateDialog
  },
  template: '<App/>'
})
