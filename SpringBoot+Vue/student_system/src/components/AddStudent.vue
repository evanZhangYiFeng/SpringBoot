<template>
  <!--学生基础信息维护页面-->
  <div id="main">
    <el-row class="el-row-main">
      <main-left></main-left>
      <main-top></main-top>
      <div style="position:absolute;margin-bottom: 10px;margin-top: 10px;margin-left: 17%;width: 83%;">
        <div style="float: right;margin-right: 5%">
          <el-upload
            class="upload-demo"
            action=""
            :on-change="handleChange"
            accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
            :auto-upload="false">
            <el-button size="mini" type="primary" icon="el-icon-upload">点击上传</el-button>
          </el-upload>
        </div>
        <div style="margin-top: 6%">
          <el-input id="stuId" style="width: 30%;margin-left: 33%" placeholder="请输入学号" v-model="stuIdInput">
            <template slot="prepend">学生学号</template>
          </el-input>
        </div>
        <div style="margin-top: 2%">
          <el-input style="width: 30%;margin-left: 33%" placeholder="请输入姓名" v-model="stuNameInput">
            <template slot="prepend">学生姓名</template>
          </el-input>
        </div>
        <div style="margin-top: 2%">
          <el-radio style="margin-left: 39%" v-model="radio" label="男" border>男</el-radio>
          <el-radio v-model="radio" label="女" border>女</el-radio>
        </div>
        <div style="margin-top: 2%">
          <el-input style="width: 30%;margin-left: 33%" placeholder="请输入邮箱" v-model="stuEmailInput">
            <template slot="prepend">学生邮箱</template>
          </el-input>
        </div>
        <div style="margin-top: 2%">
          <el-input style="width: 30%;margin-left: 33%" placeholder="请输入家庭地址" v-model="stuAddressInput">
            <template slot="prepend">学生住址</template>
          </el-input>
        </div>
        <div style="margin-top: 3%">
          <el-button style="margin-left: 42%" type="success" @click="getStudentInfo" >保存信息</el-button>
        </div>
      </div>
    </el-row>
  </div>
</template>

<script>
    export default {
        name: "AddStudent",
        mounted () {
            document.getElementById('stuId').focus()
        },
        data(){
            return{
                stuIdInput: '',
                stuNameInput: '',
                stuEmailInput: '',
                stuAddressInput: '',
                radio: '男'
            }
        },
        methods:{
            //提交完信息清空输入框
            empty(){
                this.stuIdInput= "";
                this.stuNameInput= "";
                this.stuEmailInput= "";
                this.stuAddressInput= "";
                this.radio = '男' ;
                document.getElementById("stuId").focus();
            },
            //封装学生对象
            getStudentInfo(){
                let stuSex = 1;
                if(this.radio === '男'){
                    stuSex = 1;
                }else {
                    stuSex = 2;
                }
                if(this.stuIdInput === '' || this.stuNameInput === '' || this.radio === '' || this.stuEmailInput === '' || this.stuAddressInput === ''){
                    this.open3();
                    return
                }
                //使用正则判断邮箱是否正确
                let regEmail = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
                if( !regEmail.test(this.stuEmailInput)){
                    this.open4();
                    return;
                }
                let stuObject = [{
                    stuId:this.stuIdInput,
                    stuName:this.stuNameInput,
                    stuSex: stuSex,
                    stuEmail: this.stuEmailInput,
                    stuAddress: this.stuAddressInput
                }]
                this.insertStu(stuObject);
                this.empty();
                //return stuObject;
            },
            insertStu(stuObject){
              /*if(this.getStudentInfo() != null && this.getStudentInfo() != ""){
                  stuObject = this.getStudentInfo();
              }else{
                  stuObject = localStorage.getItem("jsonArray");
                  localStorage.removeItem("jsonArray");
                  console.log("从内存获得解析的数据:"+stuObject);
              }*/
             // let stuObject = this.getStudentInfo();
              this.axios.post(
                  "http://localhost:8085/insertStudent",
                  stuObject
              ).then( (response) =>{
                  console.log("是否插入成功:"+response.data)
                  if(response.data >= 1){
                      this.open1();
                  }else if(response.data === 0){
                      this.open5();
                  }else {
                      this.open2();
                  }
              })
            },
            open1() {
                this.$message({
                    message: '学生信息插入成功',
                    type: 'success'
                });
            },
            open2() {
                this.$message({
                    message: '学生信息插入失败',
                    type: 'danger'
                });
            },
            open3(){
                this.$message({
                    message: '信息填写不完整',
                    type: 'warning'
                })
            },
            open4(){
                this.$message({
                    message: '邮箱格式不正确',
                    type: 'warning'
                })
            },
            open5(){
                this.$message({
                    message: '学生Id已经存在,请重新输入',
                    type: 'warning'
                })
            },

            handleChange(file,fileList){
              this.fileTemp = file.raw;
              console.log(this.fileTemp);
              if(this.fileTemp){
                  if(this.fileTemp.type == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                      || this.fileTemp.type == "application/vnd.ms-excel"){
                      console.log("该文件是Excel格式");
                      this.parseExcel(this.fileTemp);
                  }else {
                      this.$message({
                          message: '文件格式错误,请重新选择',
                          type: 'danger'
                      })
                  }
              }else{
                  this.$message({
                      message: '请选择需要上传的文件',
                      type: 'warning'
                  })
              }
            },
            //解析Excel
            parseExcel(obj){
                let _this = this;
                let imputDOM = this.$refs.inputer;
                //通过DOM读取文件
                this.file = event.currentTarget.files[0];
                let rABS = false;    //是否将文件读取为二进制字符串
                let f = this.file;
                let reader = new FileReader();
                FileReader.prototype.readAsBinaryString = function (f) {
                    let binary = '';
                    let rABS = false;
                    let pt = this;
                    let wb;
                    let outdata;
                    let reader = new FileReader();
                    reader.onload = function (e) {
                        let bytes = new Uint8Array(reader.result);
                        let length = bytes.byteLength;
                        for(let i=0;i<length;i++){
                            binary += String.fromCharCode(bytes[i]);
                        }
                        var XLSX = require('xlsx');
                        if(rABS){
                            wb = XLSX.read(btoa(fixdata(binary)),{
                                type: "base64"
                            } );
                        }else {
                            wb = XLSX.read(binary,{
                                type: "binary"
                            });
                        }
                        outdata = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
                        let arr = [];
                        outdata.map( v =>{
                            let obj = {};
                            obj.stuId = v.学生学号;
                            obj.stuName = v.学生姓名;
                            if(v.学生性别 === '男'){
                                obj.stuSex = 1;
                            }else{
                                obj.stuSex = 2;
                            }
                            obj.stuEmail = v.学生邮箱;
                            obj.stuAddress = v.学生住址;
                            arr.push(obj);
                        })
                        this.accountList = [...arr];
                        console.log(this.accountList);
                        localStorage.setItem('jsonArray',this.accountList);    //将解析的json数据暂时存放在内存，方面后面传到后端存入数据库
                        _this.insertStu(this.accountList);
                        /* _this.reload();*/
                    }
                    reader.readAsArrayBuffer(f);
                }
                if (rABS) {
                    reader.readAsArrayBuffer(f);
                } else {
                    reader.readAsBinaryString(f);
                }
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
  }
  .el-row-main {
    height: inherit;
  }
</style>
