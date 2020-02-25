<template>

  <div id="main">
    <!-- 页眉 -->
    <el-row style="width: 100%;height: 3%">
      <el-col><div style="width: 100%"></div></el-col>
    </el-row>
    <!-- 第一行布局 -->
    <el-row style="width: 100%;height: 10%">
      <el-col><div>
        <center><label style="font-family: 宋体;font-size: 30px;color: #98F5FF">智能控制单元机器人远程控制系统</label></center>
      </div></el-col>
    </el-row>
    <!-- 中级行布局 -->
    <el-row class="MianLeft">
      <div style="width: 63%;height: 100%;float: left">
        <div style="width: 100%;height: 25%;float: top">
          <center><label style="font-family: 宋体;font-size: 20px;color: #98F5FF">当前坐标位置</label></center>
        </div>
        <div id="XLayout">
          <div style="width: 100%;height: 30%;float: top">
            <center><label id="XTitle">X坐标</label></center>
          </div>
          <div style="width: 100%;height: 70%;float: top">
            <center><el-input id="xNum" placeholder="####.####" v-model="xrt"></el-input></center>
          </div>
        </div>
        <div id="YLayout">
          <div style="width: 100%;height: 30%;float: top">
            <center><label id="YTitle">Y坐标</label></center>
          </div>
          <div style="width: 100%;height: 70%;float: top">
            <center><el-input id="yNum" placeholder="####.####" v-model="yrt" ></el-input></center>
          </div>
        </div>
        <div id="ZLayout">
          <div style="width: 100%;height: 30%;float: top">
            <center><label id="ZTitle">Z坐标</label></center>
          </div>
          <div style="width: 100%;height: 70%;float: top">
            <center><el-input id="zNum" placeholder="####.####" v-model="zrt" ></el-input></center>
          </div>
        </div>
        <div id="RLayout">
          <div style="width: 100%;height: 30%;float: top">
            <center><label id="RTitle">R坐标</label></center>
          </div>
          <div style="width: 100%;height: 70%;float: top">
            <center><el-input id="rNum" placeholder="####.####" v-model="rrt" ></el-input></center>
          </div>
        </div>
      </div>
      <div style="width: 33%;height: 100%;float: right">  <!--控制按钮-->
        <div style="width: 100%;height: 40%;float: top">
          <center><label style="font-family: 宋体;font-size: 20px;color: #98F5FF">系统状态</label></center>
        </div>
        <div style="width: 100%;height: 50%;margin-top: 2%">
          <center><el-button icon="el-icon-s-opportunity" type="success"></el-button></center>
        </div>
      </div>
    </el-row>
    <!-- 按钮行 -->
    <el-row class="MianLeft">
      <div style="width: 63%;height: 100%;float: left">
        <div class="ButtonCol">
          <div style="margin-top: 40%">
            <center v-if="sign === true"><el-button type="primary" style="background-color: #96CEB4;border-color: #96CEB4" v-on:click="getButtonStatus('xf')" >X-</el-button></center>
            <center v-else>
              <el-button type="primary" style="background-color: #96CEB4;border-color: #96CEB4" @mousedown.native="updateButtonDown('xf')" @mouseup.native="updateButtonUp('xf')" >X-</el-button>
            </center>
          </div>
        </div>
        <div class="ButtonCol">
          <div>
            <center v-if="sign === true"><el-button type="primary" style="background-color: #00A1D9;border-color: #00A1D9" v-on:click="getButtonStatus('yf')">Y-</el-button></center>
            <center v-else>
              <el-button type="primary" style="background-color: #00A1D9;border-color: #00A1D9" @mousedown.native="updateButtonDown('yf')" @mouseup.native="updateButtonUp('yf')" >Y-</el-button>
            </center>
          </div>
          <div style="margin-top: 60%">
            <center v-if="sign === true"><el-button type="primary" style="background-color: #00A1D9;border-color: #00A1D9" v-on:click="getButtonStatus('yz')" >Y+</el-button></center>
            <center v-else>
              <el-button type="primary" style="background-color: #00A1D9;border-color: #00A1D9" @mousedown.native="updateButtonDown('yz')" @mouseup.native="updateButtonUp('yz')" >Y+</el-button>
            </center>
          </div>
        </div>
        <div class="ButtonCol">
          <div style="margin-top: 40%">
            <center v-if="sign === true"><el-button type="primary" style="background-color: #96CEB4;border-color: #96CEB4" v-on:click="getButtonStatus('xz')" >X+</el-button></center>
            <center v-else>
              <el-button type="primary" style="background-color: #96CEB4;border-color: #96CEB4" @mousedown.native="updateButtonDown('xz')" @mouseup.native="updateButtonUp('xz')" >X+</el-button>
            </center>
          </div>
        </div>
        <div class="ButtonCol">
          <div style="margin-top: 40%">
            <center v-if="sign === true"><el-button type="primary" style="background-color: #FF6F69;border-color: #FF6F69" v-on:click="getButtonStatus('zf')" >Z-</el-button></center>
            <center v-else>
              <el-button type="primary" style="background-color: #FF6F69;border-color: #FF6F69" @mousedown.native="updateButtonDown('zf')" @mouseup.native="updateButtonUp('zf')" >Z-</el-button>
            </center>
          </div>
        </div>
        <div class="ButtonCol">
          <div>
            <center v-if="sign === true"><el-button type="primary" style="background-color: #FFCC5C;border-color: #FFCC5C" v-on:click="getButtonStatus('rf')">R-</el-button></center>
            <center v-else>
              <el-button type="primary" style="background-color: #FFCC5C;border-color: #FFCC5C" @mousedown.native="updateButtonDown('rf')" @mouseup.native="updateButtonUp('rf')" >R-</el-button>
            </center>
          </div>
          <div style="margin-top: 60%">
            <center v-if="sign === true"><el-button type="primary" style="background-color: #FFCC5C;border-color: #FFCC5C" v-on:click="getButtonStatus('rz')">R+</el-button></center>
            <center v-else>
              <el-button type="primary" style="background-color: #FFCC5C;border-color: #FFCC5C" @mousedown.native="updateButtonDown('rz')" @mouseup.native="updateButtonUp('rz')" >R+</el-button>
            </center>
          </div>
        </div>
        <div class="ButtonCol">
          <div style="margin-top: 40%"><center v-if="sign === true">
            <el-button type="primary" style="background-color: #FF6F69;border-color: #FF6F69" v-on:click="getButtonStatus('zz')" >Z+</el-button></center>
            <center v-else>
              <el-button type="primary" style="background-color: #FF6F69;border-color: #FF6F69" @mousedown.native="updateButtonDown('zz')" @mouseup.native="updateButtonUp('zz')" >Z+</el-button>
            </center>
          </div>
        </div>
      </div>
      <div style="width: 33%;height: 100%;float: right">
        <div class="MainRightButton">
          <center v-if="sign === true"><el-button class="ButtonSize" type="primary" v-on:click="getButtonStatus('start')">启动</el-button></center>
          <center v-else>
            <el-button class="ButtonSize" type="primary" @mousedown.native="updateButtonDown('start')" @mouseup.native="updateButtonUp('start')" >启动</el-button>
          </center>
        </div>
        <div class="MainRightButton">
          <center v-if="sign === true"><el-button class="ButtonSize" type="warning" v-on:click="getButtonStatus('pause')">暂停</el-button></center>
          <center v-else>
            <el-button class="ButtonSize" type="warning" @mousedown.native="updateButtonDown('pause')" @mouseup.native="updateButtonUp('pause')" >暂停</el-button>
          </center>
        </div>
        <div class="MainRightButton">
          <center v-if="sign === true"><el-button class="ButtonSize" type="danger" v-on:click="getButtonStatus('stop')">停止</el-button></center>
          <center v-else>
            <el-button class="ButtonSize" type="danger" @mousedown.native="updateButtonDown('pause')" @mouseup.native="updateButtonUp('stop')" >停止</el-button>
          </center>
        </div>
      </div>
    </el-row>
    <!-- 最下一行布局 -->
    <el-row class="MianLeft">
      <div style="width: 63%;height: 100%;float: left">
        <div style="width: 15%;height: 100%;float: left">
          <div class="LeftBottomButton">
            <center v-if="sign === true"><el-button type="primary" v-on:click="getButtonStatus('ok0')" >确认</el-button></center>
            <center v-else>
              <el-button type="primary" @mousedown.native="updateButtonDown('ok0')" @mouseup.native="updateButtonUp('ok0')" >确认</el-button>
            </center>
          </div>
          <div class="LeftBottomButton">
            <center v-if="sign === true"><el-button type="success" v-on:click="getButtonStatus('ok1')" >确认</el-button></center>
            <center v-else>
              <el-button type="success" @mousedown.native="updateButtonDown('ok1')" @mouseup.native="updateButtonUp('ok1')" >确认</el-button>
            </center>
          </div>
          <div class="LeftBottomButton">
            <center v-if="sign === true"><el-button type="warning" v-on:click="getButtonStatus('ok2')" >确认</el-button></center>
            <center v-else>
              <el-button type="warning" @mousedown.native="updateButtonDown('ok2')" @mouseup.native="updateButtonUp('ok2')" >确认</el-button>
            </center>
          </div>
        </div>
        <div style="width: 10%;height: 100%;float: left">
          <div class="LeftBottomButton" style="margin-top: 6%"><center><el-tag effect="plain" type="primary">P0:</el-tag></center></div>
          <div class="LeftBottomButton" style="margin-top: 3%"><center><el-tag effect="plain" type="success">P1:</el-tag></center></div>
          <div class="LeftBottomButton"><center><el-tag effect="plain" type="warning">P2:</el-tag></center></div>
        </div>
        <div class="BottomInput">
          <div class="LeftBottomButton" style="margin-top: 0%"><center><el-input v-model="x0"></el-input></center></div>
          <div class="LeftBottomButton" style="margin-top: 0%"><center><el-input v-model="x1"></el-input></center></div>
          <div class="LeftBottomButton"><center><el-input v-model="x2"></el-input></center></div>
        </div>
        <div class="BottomInput">
          <div class="LeftBottomButton"><center><el-input v-model="y0"></el-input></center></div>
          <div class="LeftBottomButton"><center><el-input v-model="y1"></el-input></center></div>
          <div class="LeftBottomButton"><center><el-input v-model="y2"></el-input></center></div>
        </div>
        <div class="BottomInput">
          <div class="LeftBottomButton"><center><el-input v-model="z0"></el-input></center></div>
          <div class="LeftBottomButton"><center><el-input v-model="z1"></el-input></center></div>
          <div class="LeftBottomButton"><center><el-input v-model="z2"></el-input></center></div>
        </div>
        <div class="BottomInput">
          <div class="LeftBottomButton" style="margin-top: 0%"><center><el-input v-model="r0"></el-input></center></div>
          <div class="LeftBottomButton" style="margin-top: 0%"><center><el-input v-model="r1"></el-input></center></div>
          <div class="LeftBottomButton"><center><el-input v-model="r2" ></el-input></center></div>
        </div>
      </div>
      <div style="width: 33%;height: 100%;float: right">
        <div class="MainRightButton">
          <center v-if="sign === true">
            <el-button class="ButtonSize" type="primary" v-on:click="getButtonStatus('go0')" >GO</el-button></center>
          <center v-else>
            <el-button class="ButtonSize" type="primary" @mousedown.native="updateButtonDown('go0')" @mouseup.native="updateButtonUp('go0')" >GO</el-button>
          </center>
        </div>
        <div class="MainRightButton">
          <center v-if="sign === true">
            <el-button class="ButtonSize" type="success" v-on:click="getButtonStatus('go1')" >GO</el-button></center>
          <center v-else>
            <el-button class="ButtonSize" type="success" @mousedown.native="updateButtonDown('go1')" @mouseup.native="updateButtonUp('go1')" >GO</el-button>
          </center>
        </div>
        <div class="MainRightButton">
          <center v-if="sign === true">
            <el-button class="ButtonSize" type="warning" v-on:click="getButtonStatus('go2')" >GO</el-button></center>
          <center v-else>
            <el-button class="ButtonSize" type="warning" @mousedown.native="updateButtonDown('go2')" @mouseup.native="updateButtonUp('go2')" >GO</el-button>
          </center>
        </div>
      </div>
    </el-row>
    <!-- 页脚 -->
    <!--<el-row style="width: 100%;height: 2%">
      <div style="margin-right: 3%;float: right"><label style="font-family: 宋体;font-size:14px;color: antiquewhite">埃斯顿自动化</label></div>
    </el-row>-->
  </div>
</template>

<script>
    export default {
        name: "Robort",
        data(){
            return{
              x0: '', y0: '', z0: '', r0: '',
              x1: '', y1: '', z1: '', r1: '',
              x2: '', y2: '', z2: '', r2: '',
              xrt: '', yrt: '', zrt: '', rrt: '',
              sign: null,activeTime: '',logName: '',count:0,
              clearTimeSet: null
            }
        },
        created(){
           //触发轮训查询，每秒一次
          window.setInterval( ()=> {
              setTimeout(this.getLocationInfo(),0);
          },1000);
          this.getActiveTime();
          if(this.activeTime != ''){
            this.countDown();
          }
        },
        mounted(){
          this.PCOrMoblie();
        },
        beforeDestroy(){
          clearInterval(this.clearTimeSet);
        },
        methods:{
            getActiveTime(){
              this.activeTime = this.$route.query.activeTime;
              this.logName = this.$route.query.logName;
              // console.log(this.activeTime+this.logName);
              return this.activeTime;
            },
            //倒计时登录
            countDown(){
              this.count = parseFloat(this.activeTime)*60;
              this.clearTimeSet = setInterval(()=>{
                if(this.count <=0){
                  clearInterval(this.clearTimeSet);
                  this.$router.push({
                    path: '/',
                    query:{logName:this.logName}
                  });
                }else{
                  this.count --;
                }
                console.log(this.count);
              },1000)
            },
            getLocationInfo(){
                this.axios.get(
                    "http://58.213.47.154:8086/robot/getLocationInfo"
                ).then((response) => {
                    if(response.data != ""){
                        this.robortList =response.data;
                        this.fillData(this.robortList);
                    }
                })
            },
            fillData(arr){
              this.x0 = arr[0].x0;this.x1 = arr[0].x1;this.x2 = arr[0].x2;
              this.y0 = arr[0].y0;this.y1 = arr[0].y1;this.y2 = arr[0].y2;
              this.z0 = arr[0].z0;this.z1 = arr[0].z1;this.z2 = arr[0].z2;
              this.r0 = arr[0].r0;this.r1 = arr[0].r1;this.r2 = arr[0].r2;
              this.xrt = arr[0].xrt;this.yrt = arr[0].yrt;this.zrt = arr[0].zrt;
              this.rrt = arr[0].rrt;
            },
            updateButtonDown(e){
              let buttonObj = [{
                name: e,
                status: "1"
              }];
              this.axios.post(
                "http://58.213.47.154:8086/robot/updateButtonStatus",buttonObj
              ).then((response) => {
                if(response.data != ""){
                  this.open();
                }
              })
            },
            updateButtonUp(e){
              let buttonObj = [{
                name: e,
                status: "0"
              }];
              this.axios.post(
                "http://58.213.47.154:8086/robot/updateButtonStatus",buttonObj
              ).then((response) => {
                if(response.data != ""){
                  this.open();
                }
              })
            },
            getButtonStatus(e){
              let obj = [{name: e}];
              this.axios.post(
                "http://58.213.47.154:8086/robot/selectButtonStatus",obj
              ).then((response) =>{
                // console.log(response.data);
                this.buttonStatus = response.data.toString();
                this.updateByMoblie(e,this.buttonStatus);
              });
            },
            updateByMoblie(e,value){
              let content = value==="0"? "1": "0";
              let obj = [{
                name: e, status: content
              }];
              this.axios.post(
                "http://58.213.47.154:8086/robot/updateButtonStatus",obj
              ).then((response) => {
                if(response.data != ""){
                  this.open();
                }
              })
            },
            open(){
                this.$message({
                    message:'刷新成功',
                    type: 'success'
                })
            },
            //判断手机端还是电脑端
            PCOrMoblie(){
              let flag = /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i.test(navigator.userAgent);
              if(flag == true){
                this.sign = true
              }else{
                this.sign = false;
              }
              // alert(this.sign);
            }
        }
    }
</script>

<style scoped>
  @import url("//unpkg.com/element-ui@2.12.0/lib/theme-chalk/index.css");
  #main{
    width: 100%;
    height: 100%;
    /*background-color: Honeydew;*/
    position: absolute;
    background-image: url("../assets/back.jpg");
  }
  #XLayout,#YLayout,#ZLayout,#RLayout{
    width: 20%;
    height: 70%;
    margin-left: 2%;
    margin-right: 2%;
    float: left
  }
  #xNum,#yNum,#zNum,#rNum{
    margin-left: 2%;
    margin-right: 2%;
  }
  #XTitle,#YTitle,#ZTitle,#RTitle{
    font-family: 宋体;
    font-size: 16px;
    color: #98F5FF
  }
  .MianLeft{
    width: 100%;
    height: 28%;
    margin-left: 5%
  }
  .MainRightButton{
    width: 100%;height: 33%;float: top
  }
  .ButtonCol{
    width: 15%;
    height: 100%;
    float: left;
  }
  .ButtonSize{
    width: 30%;
    height: 20%;
  }
  .LeftBottomButton{
    height: 33%;width: 100%;float: top;
  }
  .BottomInput {
    width: 16%;
    height: 100%;
    float: left;
    margin-left: 1%;
  }
  .ButtonWidth{
    width: 30%;
  }
</style>
