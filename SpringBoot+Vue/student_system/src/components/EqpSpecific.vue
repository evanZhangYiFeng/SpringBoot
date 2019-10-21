<template>
    <!--装备具体化-->
  <div id="main">
    <el-row>
      <main-left></main-left>
      <main-top></main-top>
      <div style="position:absolute;margin-bottom: 10px;margin-top: 10px;margin-left: 17%;width: 83%;">
        <div style="margin-top: 5%;">
          <el-select id="eqp_Id" style="width: 25%;margin-left: 15%" v-model="selected" @change="selectType">
            <el-option v-for="item in eqpType" :label="item.eqp_name" :value="item.eqp_name"></el-option>
          </el-select>
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入法强" v-model="eqpMagic" :disabled="isMagic">
            <template slot="prepend">法术强度</template>
          </el-input>
        </div>
        <div style="margin-top: 1%">
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入装备序号" v-model="eqpId">
            <template slot="prepend">装备序号</template>
          </el-input>
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入法穿" v-model="eqpMagicPenetration" :disabled="isMagicPenetration">
            <template slot="prepend">法术穿透</template>
          </el-input>
        </div>
        <div style="margin-top: 1%">
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入装备名称" v-model="eqpName">
            <template slot="prepend">装备名称</template>
          </el-input>
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入暴击率" v-model="eqpCrit" :disabled="isCrit">
            <template slot="prepend">暴击概率</template>
          </el-input>
        </div>
        <div style="margin-top: 1%">
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入装备总价" v-model="eqpMoney">
            <template slot="prepend">装备总价</template>  eqpPenetration
          </el-input>
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入物穿" v-model="eqpPhysicsPenetration" :disabled="isPhysicsPenetration">
            <template slot="prepend">物理穿透</template>
          </el-input>
        </div>
        <div style="margin-top: 1%">
          <el-radio style="margin-left: 15.5%" v-model="eqpCompound" label="1" border>可合成</el-radio>
          <el-radio v-model="eqpCompound" label="0" border>不可合成</el-radio>
        </div>
        <div style="margin-top: 1%">
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入攻击力" v-model="eqpAttack" :disabled="isAttack">
            <template slot="prepend">攻击力</template>
          </el-input>
          <el-input style="width: 25%;margin-left: 15%" placeholder="请输入物理吸血" v-model="eqpSuckBlood" :disabled="isSuckBlood">
            <template slot="prepend">物理吸血</template>
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
                eqpCompound: '1',    //是否可以继续合成,默认初始小件
                eqpAttack: '',
                eqpCrit: '',
                eqpPhysicsPenetration: '',     //物理穿透
                eqpSuckBlood: '',         //物理吸血
                eqpMagicBlood: '',
                eqpMagic: '',
                eqpMagicPenetration: '',
                isCrit: false,
                isPhysicsPenetration: false,
                isAttack: false,
                isMagic: false,
                isMagicPenetration: false,
                isSuckBlood: false,
                isMagicBlood: false
            }
        },
        methods:{
            //获得装备类型
            getEqpType(){
              this.axios.get(
                  "http://localhost:8085/getAllTypes"
              ).then( (response) =>{
                  this.eqpType = response.data;
              })
            },
            //保存完信息清空输入框
            empty(){
                this.eqpId = "";
                this.eqpName = "";
                this.eqpAttack = "";
                this.eqpMagic = "";
                this.eqpMoney = "";
                this.eqpCompound = '1';
                this.eqpCrit = "";
                this.eqpPhysicsPenetration = "";
                this.eqpMagicPenetration = ""
                this.eqpSuckBlood = "";
                this.eqpMagicBlood = "";
            },
            //选择某个装备类型后，出发该方法
            selectType(){
                if(this.selected === '物理攻击'){
                    //魔法
                    this.isMagic = true;
                    this.isMagicPenetration = true;
                    this.isMagicBlood = true;
                    //物理
                    this.isAttack = false;
                    this.isCrit = false;
                    this.isPhysicsPenetration = false;
                    this.isSuckBlood = false;
                }else if(this.selected === '魔法强度'){
                    this.isAttack = true;
                    this.isCrit = true;
                    this.isPhysicsPenetration = true;
                    this.isSuckBlood = true;
                    //魔法
                    this.isMagic = false;
                    this.isMagicPenetration = false;
                    this.isMagicBlood = false;
                }
            },
            //封装装备全部信息
            getAllEqpInfo() {
                var eqpObject = {
                  eqp_type: this.selected,
                  eqp_id: this.eqpId,
                  eqp_name: this.eqpName,
                  eqp_money: this.eqpMoney,
                  eqp_boolean_compound: this.eqpCompound,
                  eqp_attack: this.eqpAttack != ''? this.eqpAttack:'0',       //如果输入为null，则用0替换
                  eqp_magic: this.eqpMagic != ""? this.eqpMagic:'0',
                  eqp_crit: this.eqpCrit != ""?this.eqpCrit:'0',
                  eqp_suck_blood: this.eqpSuckBlood != ""?this.eqpSuckBlood : '0',
                  eqp_magic_blood: this.eqpMagicBlood != ""?this.eqpMagicBlood : '0',
                  eqp_physics_penetration: this.eqpPhysicsPenetration != ""?this.eqpPhysicsPenetration : '0',
                  eqp_magic_penetration: this.eqpMagicPenetration != ""?this.eqpMagicPenetration : '0'
                };
                console.log(eqpObject);
                this.saveEquipmentInfo(eqpObject);
            },
            //保存装备信息
            saveEquipmentInfo(object){
              this.axios.post(
                  "http://localhost:8085/insertEqpAttack",object
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
