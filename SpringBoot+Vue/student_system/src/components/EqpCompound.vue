<template>
  <!--装备合成升级-->
    <div id="main">
      <el-row>
        <main-left></main-left>
        <main-top></main-top>
        <div style="position:absolute;margin-bottom: 10px;margin-top: 10px;margin-left: 17%;width: 83%;">
          <div style="margin-top: 5%;">
            <el-select id="eqp_Id" style="width: 20%;margin-left: 20%" v-model="selected1" >
              <el-option v-for="item in equipList" :label="item.eqp_name" :value="item.eqp_name"></el-option>
            </el-select>
            <el-select id="eqp_Id" style="width: 20%;margin-left: 15%" v-model="selected2">
              <el-option v-for="item in equipList" :label="item.eqp_name" :value="item.eqp_name"></el-option>
            </el-select>
          </div>
          <div style="margin-top: 5%">
            <el-button style="margin-left: 42%" size="medium" type="success" icon="el-icon-help" @click="openDialog"  >合成升级</el-button>
            <!--弹出对话框填写新装备信息-->
           <div>
             <el-dialog title="新装备信息" :visible.sync="dialogFormVisible" width="45%" >
               <el-form :model="form" style="margin-left: -20%">
                 <el-form-item :label-width="formLabelWidth">
                   <el-input v-model="form.stuId" autocomplete="off" style="width: 60%" :disabled="true">
                     <template slot="prepend">新装备序号</template>
                   </el-input>
                 </el-form-item>
                 <el-form-item :label-width="formLabelWidth">
                   <el-input v-model="form.stuName" autocomplete="off" style="width: 60%">
                     <template slot="prepend">新装备名称</template>
                   </el-input>
                 </el-form-item>
                 <el-form-item :label-width="formLabelWidth">
                   <el-radio v-model="form.stuSex" label="1" border style="margin-left: 5%">可以合成</el-radio>
                   <el-radio v-model="form.stuSex" label="0" border>不可合成</el-radio>
                 </el-form-item>
                 <el-form-item :label-width="formLabelWidth">
                   <el-input v-model="form.stuEmail" autocomplete="off" style="width: 60%">
                     <template slot="prepend">学生邮箱</template>
                   </el-input>
                 </el-form-item>
                 <el-form-item :label-width="formLabelWidth">
                   <el-input v-model="form.stuAddress" autocomplete="off" style="width: 60%"   >
                     <template slot="prepend">学生住址</template>
                   </el-input>
                 </el-form-item>
               </el-form>
               <div slot="footer" class="dialog-footer">
                 <el-button @click="dialogFormVisible = false">取 消</el-button>
                 <el-button type="primary" @click="upgrade">确 定</el-button>
               </div>
             </el-dialog>
           </div>
          </div>
        </div>
      </el-row>
    </div>
</template>

<script>
    export default {
        name: "EqpCompound",
        created(){
          this.getEquipList();
        },
        data(){
            return{
                formLabelWidth: '100px',
                dialogFormVisible: false,
                selected1: '',
                selected2: '',
                equipList: [],
                newName: '',             //合成新装备的名字
                needMoney: '',            //合成新装备需要的金币
                booleanContinueCom: '1',    //是否可以继续合成
                form: {
                    eqp_id: '',
                    eqp_type: '',
                    eqp_name: '',
                    eqp_money: '',
                    eqp_attack: '',
                    eqp_magic: '',
                    eqp_crit: '',
                    eqp_boolean_compound: '1',
                    eqp_suck_blood: '',
                    eqp_magic_blood: '',
                    eqp_physics_penetration: '',
                    eqp_magic_penetration: ''
                },
            }
        },
        methods:{
            //获得装备列表
            getEquipList(){
              this.axios.get(
                  "http://localhost:8085/getAllEquip"
              ).then( (response) =>{
                  this.equipList = response.data;
                  console.log(this.equipList);
              })
            },
            //打开对话框
            openDialog(){
                this.dialogFormVisible = true;
            },
            upgrade(){

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
