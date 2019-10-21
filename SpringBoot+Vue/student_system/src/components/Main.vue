<template xmlns="http://www.w3.org/1999/html">
    <!--后台管理主界面，左侧导航栏，右侧信息-->
  <div id="main">
    <el-row class="el-row-main">
      <main-left></main-left>
      <main-top></main-top>
    </el-row>
    <label style="text-align: right">开发者:Evan</label>
  </div>
</template>

<script>
    export default {

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
