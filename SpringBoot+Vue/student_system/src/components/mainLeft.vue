<template xmlns="http://www.w3.org/1999/html">
  <!--抽取后台管理左侧导航栏-->
  <div id="main">
    <el-container style="height: 580px;border: 1px solid #eee">
      <el-aside width="17%" style="height: 580px;background-color: #545c64 " >   <!--左侧导航栏-->
        <el-menu router :default-openeds="['1','3']"  :default-active="this.$route.path"
                 background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>学生管理</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1" route="/ListStudent" style="color: #98F5FF">学生信息列表</el-menu-item>
              <el-menu-item index="1-2" route="/AddStudent" style="color: #98F5FF ">学生信息维护</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>数据可视化</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1" route="" style="color: #AB82FF">曲线对比分析</el-menu-item>
              <el-menu-item index="2-2" route="" style="color: #AB82FF">学生驾驶舱</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>英雄联盟</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1" route="/EqpTypeSet" style="color: #ffd04b">装备分类设置</el-menu-item>
              <el-menu-item index="3-2" route="/EqpSpecific" style="color: #ffd04b">攻击装</el-menu-item>
              <el-menu-item index="3-3" route="/EqpDefense" style="color: #ffd04b">防御装</el-menu-item>
              <!--<el-menu-item index="3-3" route="/EqpCompound" style="color: #ffd04b">装备合成</el-menu-item>-->
            </el-menu-item-group>
            <el-menu-item-group>
              <template slot="title">分组二</template>
              <el-menu-item index="3-4" route="/HeroProperty" style="color: #ffd04b">英雄属性</el-menu-item>
              <el-menu-item index="3-5" route="/HeroTrain" style="color: #ffd04b">英雄训练</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
    </el-container>
  </div>
</template>

<script>
    export default {
        name: "main-left",
        data(){
            return{
                url: "/HeroProperty"
            }
        }
    }
</script>

<style scoped>
  @import url("//unpkg.com/element-ui@2.12.0/lib/theme-chalk/index.css");
  #main{
    width: 100%;
    height: 100%;
    /*!*background-color: Honeydew;*!
    position: absolute;*/
  }
</style>
