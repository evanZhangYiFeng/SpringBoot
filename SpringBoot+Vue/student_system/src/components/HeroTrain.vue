<template>
  <!--英雄刷金币升级装备训练场所-->
  <div>
    <el-row>
      <main-left></main-left>
      <main-top></main-top>
      <div style="position:absolute;margin-bottom: 10px;margin-top: 10px;margin-left: 17%;width: 83%;">
        <div style="margin-top: 10%;">
          <el-select id="eqp_Id" style="width: 20%;margin-left: 20%" @change="getSelectTrainingHero"
                    value-key="name" v-model="selected1" placeholder="请选择出战英雄">
            <el-option v-for="item in heroList" :label="item.name" :value="item"></el-option>
          </el-select>
          <el-radio-group v-model="radio">
            <el-radio style="margin-left: 25%" label="1" @change="clickHeroCheckBox" border>士兵</el-radio>
            <br>
            <el-radio style="margin-left: 25%;margin-top: 2%" label="2" @change="clickHeroCheckBox" border >野怪</el-radio>
            <br>
            <el-radio style="margin-left: 25%;margin-top: 2%" label="3" @change="clickHeroCheckBox" border>英雄</el-radio>
            <el-select id="eqp_Id" style="width: 50%;margin-left: 25%; margin-top: 2%" v-model="selected2" value-key="name"
                       placeholder="请选择迎战英雄" :disabled="isClickHero" @change="getSelectBeTrainedHeroInfo" >
              <el-option v-for="item in heroList" :label="item.name" :value="item"></el-option>
            </el-select>
          </el-radio-group>

        </div>
          <el-button style="margin-left: 42%;margin-top: 5%" size="medium" type="success"
                     @click="createSoldierAndMonster" icon="el-icon-help">开始对战</el-button>
      </div>
    </el-row>
  </div>
</template>

<script>
    export default {
        name: "HeroTrain",
        created(){
          this.getHeroList();
        },
        data(){
            return{
                isClickHero: true,     //表示对战英雄选择器不可选
                radio: "1",
                heroList: [],
                selected1: '',
                selected2: '',
                soldier:{            //小兵属性
                    name: '小兵',
                    attack: 20,
                    blood: 450,
                    value: 35
                },
                monster:{            //野怪属性
                    name: '大怪兽',
                    attack: 50,
                    blood: 800,
                    value: 50
                },
                //需要训练的英雄
                trainingHero:{
                    name: '',
                    attack: '',
                    magic: '',
                    armor: '',
                    spell: '',
                    crit: '',
                    blood: '',
                    damage1: '',
                    damage2: '',
                    damage3: '',
                    money: ''
                },
                //被训练的英雄
                beTrainedHero:{
                    name: '',
                    attack: '',
                    magic: '',
                    armor: '',
                    spell: '',
                    crit: '',
                    blood: '',
                    damage1: '',
                    damage2: '',
                    damage3: '',
                    value: 400
                }
            }
        },
        methods:{
            //点击英雄复选框时,后面的英雄选择器可选
            clickHeroCheckBox(){
                if(this.radio === "3"){
                    this.isClickHero = false;
                }else{
                    this.isClickHero = true;
                }
            },
            //获得选中的被训练英雄信息并提取部分属性信息
            getSelectBeTrainedHeroInfo(){
                this.beTrainedHero = {
                    name: this.selected2.name,
                    attack: this.selected2.attack,
                    magic: this.selected2.magic,
                    armor: this.selected2.armor,
                    spell: this.selected2.spell,
                    crit: this.selected2.crit,
                    blood: this.selected2.blood,
                    damage1: this.selected2.damage1,
                    damage2: this.selected2.damage2,
                    damage3: this.selected2.damage3,
                    value: this.beTrainedHero.value
                };
                console.log("需要被训练英雄的信息:"+this.beTrainedHero);
            },
            //需要训练的英雄信息
            getSelectTrainingHero(){
                this.trainingHero = {
                    name: this.selected1.name,
                    attack: this.selected1.attack,
                    magic: this.selected1.magic,
                    armor: this.selected1.armor,
                    spell: this.selected1.spell,
                    crit: this.selected1.crit,
                    blood: this.selected1.blood,
                    damage1: this.selected1.damage1,
                    damage2: this.selected1.damage2,
                    damage3: this.selected1.damage3,
                    money: this.selected1.money
                };
                console.log("需要训练英雄的信息:"+this.trainingHero);
            },
            //创建一个小兵和野怪供英雄训练
            createSoldierAndMonster(){
                var soldier = this.soldier;
                var monster = this.monster;
                var beTrainedHero = this.beTrainedHero
                if(this.radio === "1"){
                    this.fight(this.trainingHero,soldier);
                }else if(this.radio === "2"){
                    this.fight(this.trainingHero,monster);
                }else if(this.radio === "3"){
                    this.fight(this.trainingHero,beTrainedHero);
                }
            },
            getHeroList(){
                this.heroList = JSON.parse(localStorage.getItem("heroList"));
            },
            //定义一个对战方法,训练英雄先出招
            fight(trainingObject,beTrainedObject){
                if(trainingObject.name === '' || beTrainedObject.name === ''){
                    this.$message({
                        message: '请先选择双方对战英雄',
                        type: 'warning'
                    });
                    return
                }
                let attackDamage = this.critCount(trainingObject);
                let beTrainedAttackDamage = this.critCount(beTrainedObject);     //对战英雄普攻伤害
                let beTrainedObjectBlood = beTrainedObject.blood;     //对方血量
                let trainingObjectBlood = trainingObject.blood;       //训练英雄血量

                //计算双方血量
                while (true){
                    //双方英雄对战计算详情：魔抗、护甲等
                    if(beTrainedObject.armor != null && beTrainedObject.armor != ''){
                          beTrainedObjectBlood = beTrainedObjectBlood +beTrainedObject.armor - attackDamage;
                          trainingObjectBlood = trainingObjectBlood + trainingObject.armor - beTrainedAttackDamage;
                          beTrainedObjectBlood -= trainingObject.damage1 + beTrainedObject.armor + beTrainedObject.spell;
                          trainingObjectBlood -= beTrainedObject.damage1 + trainingObject.armor + trainingObject.spell;
                          beTrainedObjectBlood -= trainingObject.damage2 + beTrainedObject.armor + beTrainedObject.spell;
                          trainingObjectBlood -= beTrainedObject.damage2 + trainingObject.armor + trainingObject.spell;
                          beTrainedObjectBlood -= trainingObject.damage3 + beTrainedObject.armor + beTrainedObject.spell;
                          trainingObjectBlood -= beTrainedObject.damage3 + trainingObject.armor + trainingObject.spell;
                          if(trainingObjectBlood <=0 || trainingObjectBlood <=0){
                              this.$message({
                                  message: '战斗结束',
                                  type: 'success'
                              });
                              break;
                          }
                    }else{
                        beTrainedObjectBlood = beTrainedObject.blood - attackDamage;
                        trainingObjectBlood = trainingObject.blood - beTrainedObject.attack;
                        beTrainedObjectBlood -= trainingObject.damage1;
                        trainingObjectBlood -= beTrainedObject.attack;
                        beTrainedObjectBlood -= trainingObject.damage2;
                        trainingObjectBlood -= beTrainedObject.attack;
                        beTrainedObjectBlood -= trainingObject.damage3;
                        trainingObjectBlood -= beTrainedObject.attack;
                        if(beTrainedObjectBlood <= 0){
                            //向后端插入信息：增加获得的赏金
                            trainingObject.money += beTrainedObject.value;
                            let heroMoney = {
                                name: trainingObject.name,
                                money: trainingObject.money
                            };
                            this.axios.put(
                                "http://localhost:8085/updateHeroMoney", heroMoney
                            ).then( (response) => {
                                if(response.data === 1){
                                    this.$message({
                                        message: '恭喜'+trainingObject.name+'击败对方,赏金'+beTrainedObject.value,
                                        type: 'success'
                                    });
                                }
                            });
                            break;
                        }else if(trainingObjectBlood <= 0) {
                            this.$message({
                                message: trainingObject.name+'不幸战败',
                                type: 'danger'
                            });
                            break;
                        }
                    }
                }
            },
            //普攻附带暴击计算
            critCount(object){
                let attackDamage = 0;
                let random=Math.ceil(Math.random()*100)     //外层100以内的随机整数
                if(object.magic === 0){
                    if(random <= Number(object.crit)){             //如何生产随机整数在暴击概率内，则触发暴击，否则普通攻击
                        attackDamage = Number(object.attack) * 2;
                    }else{
                        attackDamage = Number(object.attack);
                    }
                }else {
                    attackDamage = Number(object.attack);
                }
                return attackDamage;
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
