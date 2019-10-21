import Vue from 'vue'
import Router from 'vue-router'
import AddStudent from "../components/AddStudent";
import ListStudent from "../components/ListStudent";
import Main from "../components/Main";
import Login from "../components/Login";
import mainLeft from "../components/mainLeft";
import mainTop from "../components/mainTop"
import updateDialog from "../components/updateDialog"
import EqpTypeSet from "../components/EqpTypeSet";
import EqpSpecific from "../components/EqpSpecific";
import EqpCompound from "../components/EqpCompound";
import HeroProperty from "../components/HeroProperty";
import HeroTrain from "../components/HeroTrain";
import EqpDefense from "../components/EqpDefense";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/ListStudent',
      component: ListStudent
    },
    {
      path: '/AddStudent',
      component: AddStudent
    },
    {
      path: '/EqpTypeSet',
      component: EqpTypeSet
    },
    {
      path: '/Main',
      component: Main
    },
    {
      path: '/',                 //表示默认加载页面
      component: Login
    },
    {
      path: '/mainLeft',
      component: mainLeft
    },
    {
      path: "/mainTop",
      component: mainTop
    },
    {
      path: '/updateDialog',
      component: updateDialog
    },
    {
      path: '/EqpSpecific',
      component: EqpSpecific
    },
    {
      path: '/EqpCompound',
      component: EqpCompound
    },
    {
      path: '/HeroProperty',
      component: HeroProperty
    },
    {
      path: '/HeroTrain',
      component: HeroTrain
    },
    {
      path: '/EqpDefense',
      component: EqpDefense
    }
  ]
})
