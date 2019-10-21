<template>
    <div id="main">
      <el-row class="el-row-main">
        <main-left></main-left>
        <main-top></main-top>
        <div style="position:absolute;margin-bottom: 10px;margin-top: 10px;margin-left: 17%;width: 83%;">
            <el-input id="idOrName" v-model="input" placeholder="请输入id或者名字" size="mini" style="width: 20%;margin-left: 10px"></el-input>
            <el-button type="success" icon="el-icon-search" size="mini" @click="getStudentById">搜索</el-button>
            <el-button type="primary" icon="el-icon-search" size="mini" @click ="getAllStudent">全部搜索</el-button>
            <el-button type="warning" icon="el-icon-download" size="mini" style="float: right;margin-right: 2%" @click="exportExcel">导出</el-button>
        </div>
        <div style="margin-top: 5%;height:75%">
          <!--分页操作-->
          <el-table :data="stuList.slice((currentPage-1)*pageSize,currentPage*pageSize)" border style="float: right; width: 83%;">
            <el-table-column width="50px" property="number" label="序号" type="index" sortable align="center" ></el-table-column>
            <el-table-column width="80px" property="stuId" label="id" align="center"></el-table-column>
            <el-table-column width="120px" property="stuName" label="姓名" align="center"></el-table-column>
            <el-table-column width="60px" property="stuSex" label="性别" align="center">
              <template slot-scope="scope"> {{scope.row.stuSex | filterType}} </template>
            </el-table-column>
            <el-table-column width="220px" property="stuEmail" label="邮箱" align="center"></el-table-column>
            <el-table-column width="280px" property="stuAddress" label="家庭住址" align="center"></el-table-column>
            <el-table-column width="100px" label="编辑" align="center">
              <template slot-scope="scope">
                <el-button type="warning" icon="el-icon-edit" size="mini" v-on:click="getNeedUpdateStudent(scope.row)">编辑</el-button>
                <!--弹出对话框更改学生信息-->
                <div>
                  <el-dialog title="学生信息修改" :visible.sync="dialogFormVisible" width="45%" >
                    <el-form :model="form" style="margin-left: -20%">
                      <el-form-item :label-width="formLabelWidth">
                        <el-input v-model="form.stuId" autocomplete="off" style="width: 60%" :disabled="true">
                          <template slot="prepend">学生学号</template>
                        </el-input>
                      </el-form-item>
                      <el-form-item :label-width="formLabelWidth">
                        <el-input v-model="form.stuName" autocomplete="off" style="width: 60%">
                          <template slot="prepend">学生姓名</template>
                        </el-input>
                      </el-form-item>
                      <el-form-item :label-width="formLabelWidth">
                        <el-radio v-model="form.stuSex" label="男" border style="margin-left: 5%">男</el-radio>
                        <el-radio v-model="form.stuSex" label="女" border>女</el-radio>
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
                      <el-button type="primary" @click="updateStudent">确 定</el-button>
                    </div>
                  </el-dialog>
                </div>
              </template>
            </el-table-column>
            <el-table-column width="100px" label="删除" align="center">
              <template slot-scope="scope">
                <el-button type="danger" icon="el-icon-delete" size="mini" @click.prevent="deleteBlog(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-row>
      <el-row>
        <div style="margin-left: 50%;margin-top: -5%">
          <!--分页参数设置  current-page.sync：默认显示第几页-->
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="4"
            layout="prev, pager, next, jumper"
            :total="stuList.length">     <!--后端传来的总数量-->
          </el-pagination>
        </div>
      </el-row>
    </div>
</template>


<script>
    export default {
        name: "ListStudent",
        mounted () {
            document.getElementById('idOrName').focus()
        },
        data(){
            return{
                currentPage: 1,
                pageSize: 4,     //每页显示数量
                currentShow: '',     //当前显示的数据
                stuList:[],
                input: '',
                form: {
                    stuId: '',
                    stuName: '',
                    stuSex: '男',
                    stuEmail: '',
                    stuAddress: ''
                },
                formLabelWidth: '100px',
                dialogFormVisible: false,
            }
        },
        //过滤器filters(注意filters写法)，1表示男生，2表示女生
        filters:{
            filterType:function(stuSex){
                return stuSex === 1 ? '男' : '女'
            }
        },
        methods:{
            getAllStudent(){
                this.axios.get(
                  "http://localhost:8085/getAll"
                ).then((response) => {
                    this.stuList = response.data;
                    console.log(this.stuList.length);
                    this.open1();
                })
            },
            getStudentById(){
                if(this.input.toString() === ''){
                    this.open2();
                    return
                }
                this.axios.get(
                    "http://localhost:8085/getStudentById",
                    {params:{idOrStuName:this.input.toString()}}
                ).then( (response) =>{
                    /*console.log("条件查询结果:"+response.data);*/
                    if(response.data != null && response.data != ''){
                        this.stuList = response.data;
                        this.open1();
                    }else {
                      this.open3();
                      this.stuList = '';
                    }
                    this.input = '';
                })
            },
            deleteBlog(object){
                let stuId = object.stuId;   //获取该行学生信息的stuId
                //弹框确认是否删除
                this.$confirm("是否永久删除该条学生信息?",'弹框提醒',{
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then( () =>{
                    console.log(stuId)
                    this.axios.delete(
                        "http://localhost:8085/deleteStudent",{data:stuId}
                    ).then( (response) =>{
                        if(response.data === 1){
                            this.open4();
                            this.getAllStudent();     //删除完成后，从新加载所有数据
                        }
                    })
                }).catch( () => {
                    this.open5();
                })
            },
            //弹框获得需要修改学生信息
            getNeedUpdateStudent(object){
                this.dialogFormVisible = true;    //打开对话框
                console.log("性别:"+object.stuSex);
                //将需要修改的信息显示到对话框中
                this.form.stuId = object.stuId;
                this.form.stuName = object.stuName;
                if(object.stuSex === 1){
                    this.form.stuSex = '男'
                }else{
                    this.form.stuSex = '女';
                }
                this.form.stuEmail = object.stuEmail;
                this.form.stuAddress = object.stuAddress;
                //将修改后的内容封装成对象

            },
            //发送需要更改的信息去后端
            updateStudent(){
                let stuObject = {
                    stuId: this.form.stuId,
                    stuName: this.form.stuName,
                    stuSex: this.form.stuSex === '男'? 1:2,
                    stuEmail: this.form.stuEmail,
                    stuAddress: this.form.stuAddress
                }
                //let json = JSON.stringify(stuObject);
                console.log("封装好的对象是:"+stuObject);
                this.axios.put(
                    "http://localhost:8085/updateStudent", stuObject
                ).then((response) =>{
                    if(response.data != null){
                        console.log(response.data);
                        this.dialogFormVisible = false;    //修改成功后关闭对话框
                        this.open6();
                        this.getAllStudent();
                    }
                })
            },
            //弹框
            open1() {
                this.$message({
                    message: '查询成功',
                    type: 'success'
                });
            },
            open2() {
                this.$message({
                    message: '查询条件不可为空',
                    type: 'warning'
                });
            },
            open3() {
                this.$message({
                    message: '未查询到相关结果，请检查查询条件',
                    type: 'warning'
                });
            },
            open4() {
                this.$message({
                    message: '删除成功',
                    type: 'success'
                });
            },
            open5() {
                this.$message({
                    message: '已取消删除操作',
                    type: 'info'
                });
            },
            open6() {
                this.$message({
                    message: '信息修改成功',
                    type: 'success'
                });
            },
            //分页
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.currentPage = 1;
                this.pageSize = val;
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.currentPage = val;
            },
            //数据导出至Excel
            exportExcel(){
                require.ensure( [],() =>{
                    const { export_json_to_excel } = require('../vender/Export2Excel.js');
                    //设置表格的标题行
                    const tHeader = ['学生学号','学生姓名','学生姓名','学生邮箱','学生住址'];
                    const excelValue = ['stuId','stuName','stuSex','stuEmail','stuAddress'];
                    const list = this.stuList;
                    /*for(let i=0;i<list.length;i++){
                        let object = list.get(i);
                        if(list.get(i).stuSex === 1){

                        }
                    }*/
                    const data = this.formatJson(excelValue,list);
                    export_json_to_excel(tHeader,data,'学生信息导出表');
                    this.$message({
                        message: '导出成功',
                        type: 'success'
                    })
                } )
            },
            formatJson(excelValue,jsonData){
                return jsonData.map( v=> excelValue.map( j => v[j]) );
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
  #el-button{
    margin-right: 2%;
    float: right
  }
</style>
