<template>
  <!--设定登录样式-->
  <div id="index_pc_bj">
    <div style="margin-top: 13%;height: 10%">
      <center><label style="text-align: center;font-family: SimSun;font-size: 35px; color: DarkSlateGray;font-weight: 700">
        南京埃斯顿自动化股份有限公司</label></center>
      <center style="margin-top: 1.5%"><label style="text-align: center;font-family: SimSun;font-size: 35px; color: DarkSlateGray;font-weight: 700">
        欢&nbsp;迎&nbsp;您!</label></center>
    </div>
    <el-row style="width: 100%;height: 25%;margin-top: 1.5%">
      <div style="height: 100%;width: 50%;float: left">
        <div style="width: 40%;float: right;margin-top: 10%;margin-right: -3%">
          <div><label class="fontStyle" style="float: top;margin-top: 20%">用户名:</label></div>
          <div style="margin-top: 10%"><label class="fontStyle">密&nbsp;码:</label></div>
        </div>
      </div>
      <div style="height: 100%;width: 50%;float: right">
        <div style="height: 100%;width: 50%;margin-top: 8.5%;margin-left: -2%">
          <div><el-input v-model="login.name" style="float: top;float: left;width: 60%" placeholder="请输入用户名"></el-input></div>
          <div><el-input v-model="login.password" @keydown.enter.native="loginForm" style="float: left;width: 60%;margin-top: 3%" placeholder="请输入密码" show-password></el-input></div>
        </div>
      </div>
    </el-row>
    <el-row style="margin-top: 2%">
      <center><el-button v-on:click="loginForm" style="width: 10%" type="primary">登&nbsp;录</el-button></center>
    </el-row>
  </div>
</template>

<script>
export default {
    name: 'App',
    data(){
        return{
            login:{
                name: '',
                password: '',
            },
            tranferName: ''
        }
    },
    created(){
      this.getName();
    },
    methods:{
        loginForm(){
            this.axios.get(
                "http://58.213.47.154:8086/LOGON",
                {params:{name:this.login.name,password:this.login.password}}
            ).then((response) => {
                if(response.data.success){
                    this.$router.push({
                      path:"/Robot",
                      query:{activeTime:response.data.active_time,logName:response.data.name}
                    })
                }else{
                    alert(response.data.message);
                }
            })
        },
        getName(){
          this.tranferName = this.$route.query.logName;
          console.log(this.tranferName);
          if(this.tranferName != ''){
            this.login.name = this.tranferName;
          }
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
  box-shadow: 0 0px 3px rgba(0,0,0,.5);
  text-align: center;
  position: absolute;
}
.fontStyle{
  color: black;
  font-family: SimSun;
  font-weight: 800;
  font-size: 20px;
  color: #00008B;
}
</style>
