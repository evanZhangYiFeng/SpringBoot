<template>
  <!--装备分类设置-->
  <div id="main">
    <el-row class="el-row-main">
      <main-left></main-left>
      <main-top></main-top>
      <div style="position:absolute;margin-bottom: 10px;margin-top: 10px;margin-left: 17%;width: 83%;">
        <div style="margin-top: 6%">
          <el-input id="eqp_Id" style="width: 30%;margin-left: 33%" placeholder="请输入分类序号" v-model="eqpTypeId">
            <template slot="prepend">装备分类序号</template>
          </el-input>
        </div>
        <div style="margin-top: 2%">
          <el-input style="width: 30%;margin-left: 33%" placeholder="请输入分类名称" v-model="eqpTypeName">
            <template slot="prepend">装备分类名称</template>
          </el-input>
        </div>
        <div style="margin-top: 2%">
          <el-radio style="margin-left: 37%" v-model="radio" label="1" border>攻击类</el-radio>
          <el-radio v-model="radio" label="2" border>防御类</el-radio>
        </div>
        <div style="margin-top: 3%">
          <el-button style="margin-left: 42%" type="success" @click="saveMsg" >保存信息</el-button>
        </div>
      </div>
    </el-row>
  </div>
</template>

<script>
    export default {
        name: "eqpTypeSet",
        data(){
            return{
                eqpTypeId: '',
                eqpTypeName: '',
                radio: '1',       //属于攻击类还是防御类
            }
        },
        methods:{
            empty(){
                this.eqpTypeId= "";
                this.eqpTypeName= "";
                this.radio = '1';
                document.getElementById("eqp_Id").focus();
            },
            saveMsg(){
                var equipType = {
                    eqp_id: this.eqpTypeId,
                    eqp_type_name: this.eqpTypeName,
                    eqp_attack_defense: this.radio,
                };
                console.log("分类信息:"+this.eqpTypeName);
                this.axios.post(
                    "http://localhost:8085/insertEqpTypes",
                    equipType
                ).then( (response) =>{
                    if(response.data === 1){
                        this.$message({
                            message: '装备类型插入成功',
                            type: 'success'
                        });
                        this.empty();
                    }else {
                        this.$message({
                            message: '装备类型插入失败',
                            type: 'warning'
                        })
                    }
                })
            }
        }
    }
</script>

<style scoped>
  @import url("//unpkg.com/element-ui@2.12.0/lib/theme-chalk/index.css");
  #main{
    width: 100%;
    height: 100%;
    position: absolute;
  }
  .el-row-main {
    height: inherit;
  }
</style>
