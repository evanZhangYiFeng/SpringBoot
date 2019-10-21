<template>
  <!--英雄属性-->
  <div id="main">
    <el-row class="el-row-main">
      <main-left></main-left>
      <main-top></main-top>
      <div style="position:absolute;margin-bottom: 10px;margin-top: 10px;margin-left: 17%;width: 83%;">
        <el-input id="idOrName" v-model="input" placeholder="请输入id或者名字" size="mini" style="width: 20%;margin-left: 10px"></el-input>
        <el-button type="success" icon="el-icon-search" size="mini" @click="getAllHero">搜索</el-button>
        <el-button type="primary" icon="el-icon-circle-plus" size="mini" @click="getHeroInfo">新增</el-button>
        <div>
          <el-dialog title="新增英雄" :visible.sync="dialogFormVisible" width="70%" >
            <el-form :model="form" style="margin-left: -20%">
              <el-form-item :label-width="formLabelWidth">
                <el-input v-model="form.id" style="width: 23%;margin-left: 10%;" size="mini">
                  <template slot="prepend">英雄序号</template>
                </el-input>
                <el-input v-model="form.physical_penetration" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">物理穿透</template>
                </el-input>
                <el-input v-model="form.damage1" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">技能1伤害</template>
                </el-input>
              </el-form-item>

              <el-form-item :label-width="formLabelWidth" style="margin-top: -2%">
                <el-input v-model="form.name" style="width: 23%;margin-left: 10%;" size="mini">
                  <template slot="prepend">英雄名称</template>
                </el-input>
                <el-input v-model="form.magic_penetration" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">法术穿透</template>
                </el-input>
                <el-input v-model="form.skill2" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">技能2</template>
                </el-input>
              </el-form-item>

              <el-form-item :label-width="formLabelWidth" style="margin-top: -2%">
                <el-input v-model="form.money" style="width: 23%;margin-left: 10%;" size="mini" placeholder="初始金币">
                  <template slot="prepend">英雄金币</template>
                </el-input>
                <el-input v-model="form.crit" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">&nbsp;暴击率&nbsp;&nbsp;</template>
                </el-input>
                <el-input v-model="form.damage2" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">技能2伤害</template>
                </el-input>
              </el-form-item>

              <el-form-item :label-width="formLabelWidth" style="margin-top: -2%">
                <el-input v-model="form.attack" style="width: 23%;margin-left: 10%;" size="mini" placeholder="初始攻击力">
                  <template slot="prepend">&nbsp;攻击力&nbsp;&nbsp;</template>
                </el-input>
                <el-input v-model="form.blood" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">&nbsp;&nbsp;&nbsp;血量&nbsp;&nbsp;&nbsp;</template>
                </el-input>
                <el-input v-model="form.skill3" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">技能3</template>
                </el-input>
              </el-form-item>

              <el-form-item :label-width="formLabelWidth" style="margin-top: -2%">
                <el-input v-model="form.magic" style="width: 23%;margin-left: 10%;" size="mini" placeholder="初始法强">
                  <template slot="prepend">魔法强度</template>
                </el-input>
                <el-input v-model="form.armor" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">&nbsp;&nbsp;&nbsp;护甲&nbsp;&nbsp;&nbsp;</template>
                </el-input>
                <el-input v-model="form.damage3" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">技能3伤害</template>
                </el-input>
              </el-form-item>

              <el-form-item :label-width="formLabelWidth" style="margin-top: -2%">
                <el-input v-model="form.suck_blood" style="width: 23%;margin-left: 10%;" size="mini">
                  <template slot="prepend">物理吸血</template>
                </el-input>
                <el-input v-model="form.spell" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">&nbsp;&nbsp;&nbsp;魔抗&nbsp;&nbsp;&nbsp;</template>
                </el-input>
              </el-form-item>

              <el-form-item :label-width="formLabelWidth" style="margin-top: -2%">
                <el-input v-model="form.magic_blood" style="width: 23%;margin-left: 10%;" size="mini">
                  <template slot="prepend">法术吸血</template>
                </el-input>
                <el-input v-model="form.skill1" style="width: 23%;margin-left: 5%;" size="mini">
                  <template slot="prepend">&nbsp;&nbsp;&nbsp;技能1&nbsp;&nbsp;&nbsp;</template>
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="addHero" >确 定</el-button>
            </div>
          </el-dialog>
        </div>

      </div>
      <div style="margin-top: 5%;height:75%">
        <!--分页操作-->
        <el-table :data="heroList.slice((currentPage-1)*pageSize,currentPage*pageSize)" border style="float: right; width: 83%;">
          <el-table-column width="80px" property="number" label="序号" type="index" sortable align="center" ></el-table-column>
          <el-table-column width="80px" property="name" label="名字" align="center"></el-table-column>
          <el-table-column width="80px" property="attack" label="攻击力" align="center"></el-table-column>
          <el-table-column width="80px" property="magic" label="法强" align="center"></el-table-column>
          <el-table-column width="80px" property="blood" label="生命值" align="center"></el-table-column>
          <el-table-column width="80px" property="armor" label="物抗" align="center"></el-table-column>
          <el-table-column width="80px" property="spell" label="魔抗" align="center"></el-table-column>
          <el-table-column width="80px" property="crit" label="暴击" align="center"></el-table-column>
          <el-table-column width="80px" property="suck_blood" label="物理吸血" align="center"></el-table-column>
          <el-table-column width="80px" property="magic_blood" label="法术吸血" align="center"></el-table-column>
          <el-table-column width="80px" property="money" label="金币" align="center"></el-table-column>
          <el-table-column width="130px" label="查看详情" align="center">
            <template slot-scope="scope">
              <el-button type="warning" icon="el-icon-edit" size="mini" v-on:click="getDetailMsg(scope.row.name)">查看详情</el-button>
            </template>
          </el-table-column>
          <el-table-column width="100px" label="装备操作" align="center">
            <template slot-scope="scope">
              <el-button type="primary" icon="el-icon-goods" size="mini" @click.prevent="addAndUpgrade(scope.row)">操作</el-button>
              <!--增加装备-->
              <div>
                <el-dialog title="选择装备装配" :visible.sync="openEqpForm" width="45%" >
                  <el-form>
                    <el-form-item :label-width="equipFormWidth">
                      <el-radio v-model="radio" :label="1" @change="getTrueOrFalse">攻击装</el-radio>
                      <el-select id="eqp_Id" style="width: 40%;margin-left: -5%" :disabled= flag
                                 v-model="selected1" value-key="eqp_name" @change="getSelectEqpInfo">
                        <el-option v-for="item in equipList" :label="item.eqp_name" :value="item"></el-option>
                      </el-select>
                      <br>
                      <el-radio style="margin-top: 5%" v-model="radio" :label="2" @change="getTrueOrFalse">防御装</el-radio>
                      <el-select style="width: 40%;margin-left: -5%" :disabled= !flag
                                 v-model="selected2" value-key="eqp_name" @change="getSelectEqpInfo">
                        <el-option v-for="item in equipDefenseList" :label="item.eqp_name" :value="item"></el-option>
                      </el-select>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="openEqpForm = false">取 消</el-button>
                    <el-button type="primary" @click="buyEquip" >购买装备</el-button>
                  </div>
                </el-dialog>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-row>
  </div>
</template>

<script>
    export default {
        name: "HeroProperty",
        created(){
          this.getEquipList();
          this.getEquipDefenseList();
        },
        data(){
            return{
                radio: 1,
                selected1: {},
                selected2: {},
                flag: false,        //true和false用来设置攻击装可选时防御装不可选，反之亦然
                openEqpForm: false,
                formLabelWidth: '100px',
                equipFormWidth: '60px',
                dialogFormVisible: false,
                currentPage: 1,
                pageSize: 4,     //每页显示数量
                currentShow: '',     //当前显示的数据
                input: '',
                heroList: [],
                equipList: [],
                equipDefenseList: [],
                form:{
                    id: '',  name: '',  money: '',  attack: '',
                    magic: '',  suck_blood: '',  magic_blood: '', physical_penetration: '',
                    magic_penetration: '', crit: '', blood: '', armor: '',
                    spell: '', equip1: '', equip2: '', equip3: '',
                    equip4: '', equip5: '', equip6: '', skill1: '',
                    damage1: '', skill2: '', damage2: '', skill3: '', damage3: ''
                },
                baseAttack: '',                   //基础攻击力、法强、伤害，计算技能伤害需要额外攻击力
                baseMagic: '',
                baseDamage1: '',
                baseDamage2: '',
                baseDamage3: '',
            }
        },
        methods:{
            //封装英雄对象
            getHeroInfo(){
                this.dialogFormVisible = true;
            },
            //新增英雄
            addHero(){
                var heroObject = {
                    id: this.form.id, name: this.form.name,
                    money: this.form.money, attack: this.form.attack,
                    magic: this.form.magic, suck_blood: this.form.suck_blood,
                    magic_blood: this.form.magic_blood, physical_penetration: this.form.physical_penetration,
                    magic_penetration: this.form.magic_penetration, crit: this.form.crit,
                    blood: this.form.blood, armor: this.form.armor,
                    spell: this.form.spell, equip1:this.form.equip1 != "" ? this.form.equip1: '',
                    equip2: this.form.equip2 != "" ? this.form.equip2: '',
                    equip3: this.form.equip3 != "" ? this.form.equip3: '',
                    equip4: this.form.equip4 != "" ? this.form.equip4: '',
                    equip5: this.form.equip5 != "" ? this.form.equip5: '',
                    equip6: this.form.equip6 != "" ? this.form.equip6: '',
                    skill1: this.form.skill1, damage1: this.form.damage1,
                    skill2: this.form.skill2, damage2: this.form.damage2,
                    skill3: this.form.skill3, damage3: this.form.damage3
                };
                console.log(heroObject);
                //将新建英雄时的基础基础攻击力、法强和基础伤害存起来
                localStorage.setItem("baseAttack",this.form.attack);
                localStorage.setItem("baseMagic",this.form.magic);
                localStorage.setItem("baseDamage1",this.form.damage1);
                localStorage.setItem("baseDamage2",this.form.damage2);
                localStorage.setItem("baseDamage3",this.form.damage3);
                this.axios.post(
                  "http://localhost:8085/insertHero",heroObject
                ).then( (response) =>{
                    if(response.data === 1){
                        console.log(response.data);
                        this.$message({
                            message: '新英雄创造成功',
                            type: 'success'
                        })
                        this.getAllHero();
                    }else{
                        this.$message({
                            message: '新英雄创造失败',
                            type: 'warning'
                        })
                    }
                    this.dialogFormVisible = false;      //关闭对话框
                })
            },
            getTrueOrFalse(){
                if(this.radio === 1){
                    this.flag = false;
                }else{
                    this.flag = true;
                }
            },
            //获得所有英雄
            getAllHero(){
                var text = this.input === "" ? this.input:'';    //获得输入框内容
                console.log("查询条件:"+text);
                this.axios.get(
                    "http://localhost:8085/getAllHero",text
                ).then( (response) =>{
                    this.heroList = response.data;
                    console.log(response.data);
                    localStorage.setItem("heroList",JSON.stringify(this.heroList));
                })
            },

            //通过名称查看该英雄详细信息(由于时间问题功能暂未实现，有兴趣的小伙伴可以自己增加实现)
            getDetailMsg(name){
            },

            //根据英雄操作装备增加升级
            addAndUpgrade(object){
                this.openEqpForm = true;
                localStorage.setItem("heroInfo",JSON.stringify(object));
                //console.log("英雄信息:"+object.name);
            },
            //获得所有攻击装备
            getEquipList(){
                this.axios.get(
                    "http://localhost:8085/getAllEquip"
                ).then( (response) =>{
                    this.equipList = response.data;
                    console.log(this.equipList);
                })
            },
            //获取所有防御装备
            getEquipDefenseList(){
                this.axios.get(
                    "http://localhost:8085/getAllEqpDefense"
                ).then( (response) =>{
                    this.equipDefenseList = response.data;
                    console.log(this.equipDefenseList);
                })
            },

            //获得选中装备的信息:攻击装或者防御装
            getSelectEqpInfo(){
                var equip = this.selected1;
                var equipDefense = this.selected2;
                localStorage.setItem("equipInfo",JSON.stringify(equip));
                localStorage.setItem("equipDefense",JSON.stringify(equipDefense));
            },
            //点击购买攻击装备按钮
            buyEquip(){
                //获取选中装备的信息
                var equipInfo = JSON.parse(localStorage.getItem("equipInfo"));
                var equipDefense = JSON.parse(localStorage.getItem("equipDefense"));
                localStorage.removeItem("equipInfo");
                console.log("想要购买的装备信息:"+equipInfo);
                //先获取当前英雄信息，金币是否足够
                var hero = JSON.parse(localStorage.getItem("heroInfo"));     //将字符串转化为json对象
                console.log("获取的英雄信息:"+hero.money);
                //获取基础攻击力和伤害、生命值、抗性
                this.baseAttack = localStorage.getItem("baseAttack");
                this.baseMagic = localStorage.getItem("baseMagic");
                this.baseDamage1 = localStorage.getItem("baseDamage1");
                this.baseDamage2 = localStorage.getItem("baseDamage2");
                this.baseDamage3 = localStorage.getItem("baseDamage3");
                if(hero.money >= equipInfo.eqp_money || hero.money >= equipDefense.eqp_money){                //当英雄拥有的金币大于装备价值时,才可以进行购买
                    hero.attack += equipInfo.eqp_attack;
                    hero.magic += equipInfo.eqp_magic;
                    hero.suck_blood += equipInfo.eqp_suck_blood;
                    hero.magic_blood += equipInfo.eqp_magic_blood;
                    hero.physical_penetration += equipInfo.eqp_physics_penetration;
                    hero.magic_penetration += equipInfo.eqp_magic_penetration;
                    hero.crit += equipInfo.eqp_crit;
                    hero.money -= equipInfo.eqp_money;
                    //防御属性增加
                    /*hero.blood += equipDefense.blood;
                    hero.armor += equipDefense.armor;
                    hero.spell += equipDefense.spell;*/
                    if(hero.magic === '' || hero.magic === 0 || hero.magic === null){
                        //物理技能伤害计算
                        hero.damage1 = 1.0*(hero.attack - Number(this.baseAttack)) + Number(this.baseDamage1);
                        hero.damage2 = 0.9*(hero.attack - Number(this.baseAttack)) + Number(this.baseDamage2);
                        hero.damage3 = 1.2*(hero.attack - Number(this.baseAttack)) + Number(this.baseDamage3);
                    }else{
                        //法师技能伤害计算
                        hero.damage1 = 0.8*(hero.magic - Number(this.baseMagic)) + Number(this.baseDamage1);
                        hero.damage2 = 0.7*(hero.magic - Number(this.baseMagic)) + Number(this.baseDamage2);
                        hero.damage3 = 1.0*(hero.magic - Number(this.baseMagic)) + Number(this.baseDamage3);
                    }
                    //给英雄的6个装备属性依次附上装备
                    const arr = [               //将6个装备封装为数组依次遍历然后填充装备
                        hero.equip1, hero.equip2, hero.equip3,
                        hero.equip4, hero.equip5, hero.equip6
                    ];
                    for(let i=0;i<arr.length;i++){
                        if(arr[i] === null || arr[i] === ''){
                            let index = 'equip'+(i+1).toString();
                            hero[index] = equipInfo.eqp_name;
                            console.log(hero.equip1);
                            break;                      //退出当前循环，执行for循环后面的代码
                        }
                    }
                    hero.equip1 = hero.equip1 != null ? hero.equip1 : '';
                    hero.equip2 = hero.equip2 != null ? hero.equip2 : '';
                    hero.equip3 = hero.equip3 != null ? hero.equip3 : '';
                    hero.equip4 = hero.equip4 != null ? hero.equip4 : '';
                    hero.equip5 = hero.equip5 != null ? hero.equip5 : '';
                    hero.equip6 = hero.equip6 != null ? hero.equip6 : '';
                    console.log("英雄新属性:"+hero);
                    this.axios.put(
                        "http://localhost:8085/updateHeroInfo",hero
                    ).then( (response) =>{
                        if(response.data === 1){
                            this.$message({
                                message: '购买成功',
                                type: 'success'
                            });
                            this.getAllHero();
                            this.openEqpForm = false;
                        }
                    });
                }else{
                    this.$message({
                        message: '金币不足,请先刷兵或者击杀其他英雄',
                        type: 'warning'
                    });
                    return;
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
    position: absolute;
  }
  .el-row-main {
    height: inherit;
  }
  #el-button{
    margin-right: 2%;
    float: right
  }
</style>
