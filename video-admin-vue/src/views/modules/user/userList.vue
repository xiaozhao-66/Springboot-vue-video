<template>
    <div class="app-container">
      <!--查询表单-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="UserQueryParams.username" placeholder="视频名称"/>
        </el-form-item>
  
        <el-form-item label="添加时间">
          <el-date-picker
            v-model="UserQueryParams.begin"
            type="datetime"
            placeholder="选择开始时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
          />
        </el-form-item>
        <el-form-item>
          <el-date-picker
            v-model="UserQueryParams.end"
            type="datetime"
            placeholder="选择截止时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
          />
        </el-form-item>
  
        <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form>
  
      <!-- 表格 -->
      <el-table
        :data="list"
        style="width: 90%"
         border>
  
        <el-table-column
          label="序号"
          width="80"
          align="center">
          <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
          </template>
        </el-table-column>
  
        <el-table-column prop="username" label="姓名" width="110" align="center" />
  
  
        <el-table-column  label="邮箱"  prop="email" width="180"  align="center"/>
         
        <el-table-column  label="手机号"  prop="mobile" width="180"  align="center"/>
  
        <el-table-column  label="图片" width="140" align="center">
           <template slot-scope="scope">
            <el-avatar :src="scope.row.avatar" ></el-avatar>
          </template>
        </el-table-column>
       
       
          <el-table-column prop="gmtCreate" label="创建时间" width="180" align="center"/>
          <el-table-column prop="gmtModified" label="修改时间" width="240" align="center"/>
        <el-table-column  label="状态" width="180" align="center">
           <template slot-scope="scope">
            <el-switch
            v-model = "scope.row.status"
            @change = "updateStatus(scope.row.id,scope.row.status)"
            active-color="#13ce66"
            :active-value= '1'
            active-text="正常"
            inactive-color="#ff4949"
            :inactive-value='0'
            inactive-text="异常">
          </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
  
    <!-- 分页 -->
      <el-pagination
        :current-page="page"
        :page-size="limit"
        :total="total"
        style="padding: 30px 0; text-align: center;"
        layout="total, prev, pager, next, jumper"
        @current-change="getList"
      />
  
    </div>
  </template>
  <script>
  import user from '@/api/user/user'
  
  export default {
      //写核心代码位置
      // data:{
      // },
      data() { //定义变量和初始值
          return {
            list:null,//查询之后接口返回集合
            page:1,//当前页
            limit:10,//每页记录数
            total:0,//总记录数
            UserQueryParams:{
            },//条件封装对象
           
          }   
      },
      created() { 
          this.getList()
      },
      methods:{  //创建具体的方法，调用teacher.js定义的方法
          
        //  getDateList(categoryId){
        //    this.UserQueryParams.categoryId = categoryId 
        //    this.getList(1);
        //  }, 
          //列表的方法
          getList(page=1) {
              this.page = page
              user.getListPage(this.page,this.limit,this.UserQueryParams)
                  .then(response =>{//请求成功
                      this.list = response.data.data.records
                      this.total = response.data.data.total
                  })
          },
          resetData() {//清空的方法
              //表单输入项数据清空
              this.UserQueryParams = {}
              //查询所有讲师数据
              this.getList()
          },
          removeDataById(id) {
              this.$confirm('此操作将永久删除视频记录, 是否继续?', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
              }).then(() => {  //点击确定，执行then方法
                  //删除视频和视频的资源
                  user.removeAll(id).then(response=>{
                        //提示信息
                      this.$message({
                          type: 'success',
                          message: '删除成功!'
                      });
                      //回到列表页面
                      this.getList()
                  })
              }) //点击取消，执行catch方法
          },
          //修改状态
          updateStatus(id,status){
            user.updateStatus(id,status).then(response=>{
                     this.$message({
                          type: 'success',
                          message: '修改成功'
                      });

            })
          },
      }
  }
  </script>
  
<style>
.info{
overflow: hidden;

text-overflow: ellipsis;

display: -webkit-box;

-webkit-line-clamp: 3;

-webkit-box-orient: vertical;

}
</style>