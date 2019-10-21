<template>
  <!--设定登录样式-->
  <div id="index_pc_bj" style="height: 600px">
    <div style="margin-top: 100px">
      <label style="text-align: center;font-family: SimSun;font-size: 35px; color: black;font-weight: 700">
        学生管理系统登录界面</label>
    </div>
    <div style="height: 340px;">
      <div style="margin-top: 100px">
        <label style="color: black;font-family: SimSun;font-weight: 700">用户名:&nbsp;
          <el-input v-model="login.name" style="width: 20%" placeholder="请输入用户名"></el-input>
        </label>
      </div>
      <div style="margin-top: 1%">
        <label style="color: black;font-family: SimSun;font-weight: 700">
          密码:&nbsp;&nbsp;
          <el-input v-model="login.password" placeholder="请输入密码" style="width: 20%" show-password></el-input>
        </label>
      </div>
      <div style="margin-top: 10px">
        <el-button v-on:click="loginForm" type="success">登录</el-button>
      </div>
    </div>
    <div style="margin-bottom: 2px">
      <label style="color: AliceBlue">版权著作人@Evan张益峰</label>
    </div>
  </div>
</template>

<script>
export default {
    name: 'App',
    data(){
        return{
            login:{
                name: '',
                password: ''
            }
        }
    },
    methods:{
        loginForm(){
            this.axios.get(
                "http://localhost:8085/api/login",
                {params:{name:this.login.name,password:this.login.password}}
            ).then((response) => {
                if(response.data.success){
                    this.$router.push("/Main")
                }else{
                    alert(response.data.msg)
                }
            })
        }
    }
}
</script>

<style scoped>
  @import url("//unpkg.com/element-ui@2.12.0/lib/theme-chalk/index.css");  <!--必须引入，否则css样式失效-->
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
#index_pc_bj{
  width:100%;
  height:100%;
  background-size:cover;
  overflow: hidden;
  background-image: url("../assets/login.png");
  /*background-position:center;*/
  box-shadow: 0 0px 3px rgba(0,0,0,.5);
  text-align: center;
}
</style>
