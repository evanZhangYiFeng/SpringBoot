<template>
    <!--装备具体化-->
  <div id="main">
    <el-row>
      <main-left></main-left>
      <main-top></main-top>
      <div style="position:absolute;margin-bottom: 10px;margin-top: 10px;margin-left: 17%;width: 83%;">
        <div style="margin-top: 10%;">
          <el-select id="eqp_Id" style="width: 25%;margin-left: 15%" v-model="selected">
            <el-option v-for="item in eqpType" :label="item.eqp_name" :value="item.eqp_name"></el-option>
          </el-select>
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入血量" v-model="eqpBlood">
            <template slot="prepend">血量</template>
          </el-input>
        </div>
        <div style="margin-top: 1%">
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入装备序号" v-model="eqpId">
            <template slot="prepend">装备序号</template>
          </el-input>
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入护甲" v-model="eqpArmor" >
            <template slot="prepend">护甲</template>
          </el-input>
        </div>
        <div style="margin-top: 1%">
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入装备名称" v-model="eqpName">
            <template slot="prepend">装备名称</template>
          </el-input>
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入魔抗" v-model="eqpSpell">
            <template slot="prepend">魔抗</template>
          </el-input>
        </div>
        <div style="margin-top: 1%">
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入装备总价" v-model="eqpMoney">
            <template slot="prepend">装备总价</template>  eqpPenetration
          </el-input>
        </div>
        <div style="margin-top: 1%">
          <el-button style="margin-left: 42%" size="medium" type="success" @click="getAllEqpInfo" >保存装备</el-button>
        </div>
      </div>
    </el-row>
  </div>

</template>

<script>
    export default {
        name: "EqpSpecific",
        created(){
          this.getEqpType();
        },
        data(){
            return{
                selected: '',
                eqpType: [],
                eqpId: '',
                eqpName: '',
                eqpMoney: '',
                eqpBlood: '',
                eqpArmor: '',
                eqpSpell: ''
            }
        },
        methods:{
            //获得装备类型
            getEqpType(){
              this.axios.get(
                  "http://localhost:8085/getEqpTypesDefense"
              ).then( (response) =>{
                  this.eqpType = response.data;
              })
            },
            //保存完信息清空输入框
            empty(){
                this.eqpId = "";
                this.eqpName = "";
                this.eqpBlood = "";
                this.eqpArmor = "";
                this.eqpSpell = "";
            },
            //封装装备全部信息
            getAllEqpInfo() {
                var eqpObject = {
                  eqp_type: this.selected,
                  eqp_id: this.eqpId,
                  eqp_name: this.eqpName,
                  eqp_money: this.eqpMoney,
                  eqp_blood: this.eqpBlood != '' ? this.eqpBlood: '0',
                  eqp_armor: this.eqpArmor != ''? this.eqpArmor:'0',
                  eqp_spell: this.eqpSpell != ""? this.eqpSpell:'0',
                };
                console.log(eqpObject);
                this.saveEquipmentInfo(eqpObject);
            },
            //保存装备信息
            saveEquipmentInfo(object){
              this.axios.post(
                  "http://localhost:8085/insertEqpDefense",object
              ).then( (response) =>{
                  if(response.data === 1){
                      this.$message({
                          message: '装备信息保存成功',
                          type: 'success'
                      });
                      this.empty();
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
