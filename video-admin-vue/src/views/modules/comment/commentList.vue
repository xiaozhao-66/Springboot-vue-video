<template>
    <div class="app-container">
      <!--查询表单-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="CommentQueryParams.title" placeholder="评论内容"/>
        </el-form-item>
  
        <el-form-item label="添加时间">
          <el-date-picker
            v-model="CommentQueryParams.begin"
            type="datetime"
            placeholder="选择开始时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
          />
        </el-form-item>
        <el-form-item>
          <el-date-picker
            v-model="CommentQueryParams.end"
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

        <el-table-column  label="图片" width="140" align="center">
           <template slot-scope="scope">
            <el-avatar :src="scope.row.avatar" ></el-avatar>
          </template>
        </el-table-column>
  
        <el-table-column  label="评论内容"  prop="content" width="180"  align="content" class="info"/>
         
        <el-table-column  label="评论等级"  width="180"  align="level">
            <template slot-scope="scope">
             <el-tag  v-if="scope.row.level==1">一级评论</el-tag>
             <el-tag type="success" v-else>二级评论</el-tag>
          </template>
        </el-table-column>
  
        
       
       
          <el-table-column prop="gmtCreate" label="创建时间" width="180" align="center"/>
          <el-table-column prop="gmtModified" label="修改时间" width="180" align="center"/>
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
  import comment from '@/api/comment/comment'
  
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
            CommentQueryParams:{
            },//条件封装对象
           
          }   
      },
      created() { 
          this.getList()
      },
      methods:{  //创建具体的方法，调用teacher.js定义的方法
          
          //列表的方法
          getList(page=1) {
              this.page = page
              comment.getListPage(this.page,this.limit,this.CommentQueryParams)
                  .then(response =>{//请求成功
                      this.list = response.data.data.records
                      this.total = response.data.data.total
                  })
          },
          resetData() {//清空的方法
              //表单输入项数据清空
              this.CommentQueryParams = {}
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
                  comment.removeAll(id).then(response=>{
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