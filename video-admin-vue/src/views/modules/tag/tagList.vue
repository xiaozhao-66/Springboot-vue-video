<template>
    <div class="app-container">
        <!--查询表单-->
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item>
                <el-input v-model="TagQueryParams.title" placeholder="视频名称"/>
            </el-form-item>

            <el-form-item label="添加时间">
                <el-date-picker
                        v-model="TagQueryParams.begin"
                        type="datetime"
                        placeholder="选择开始时间"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        default-time="00:00:00"
                />
            </el-form-item>
            <el-form-item>
                <el-date-picker
                        v-model="TagQueryParams.end"
                        type="datetime"
                        placeholder="选择截止时间"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        default-time="00:00:00"
                />
            </el-form-item>

            <el-button type="primary" icon="el-icon-search" @click="getAllTags()">查询</el-button>
            <el-button type="default" @click="resetData()">清空</el-button>
            <el-button type="success" @click="Visible = true,type = 'add'">添加标签</el-button>
        </el-form>

        <!-- 表格 -->
        <el-table
                :data="dataList"
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

            <el-table-column prop="label" label="名称" width="110" align="center" />


            <el-table-column  label="排序"  prop="sort" width="180"  align="center"/>

            <el-table-column prop="gmtCreate" label="创建时间" width="180" align="center"/>
            <el-table-column prop="gmtModified" label="修改时间" width="240" align="center"/>
            <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <el-button type="success" size="mini" icon="el-icon-edit" @click="getTagInfo(scope.row.id)">编辑</el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>


        <!-- 增加分类 -->
        <el-dialog title="标签管理" :visible.sync="Visible">
            <el-form :model="tag">
                <el-form-item
                        label="标签名称"
                        :label-width="formLabelWidth"
                >
                    <el-input v-model="tag.label" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item
                        label="排序"
                        :label-width="formLabelWidth"
                >
                    <el-input-number
                            v-model="tag.sort"
                            :min="0"
                            controls-position="right"
                    />
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancel()">取 消</el-button>
                <el-button type="primary" @click="addOrUpdateTag(type)"
                >确 定</el-button
                >
            </div>
        </el-dialog>

        <!-- 分页 -->
        <el-pagination
                :current-page="page"
                :page-size="limit"
                :total="total"
                style="padding: 30px 0; text-align: center;"
                layout="total, prev, pager, next, jumper"
                @current-change="getAllTags"
        />
    </div>
</template>

<script>
    import tag from '@/api/tag/tag'
    export default {
        //写核心代码位置
        // data:{
        // },
        data() { //定义变量和初始值
            return {
                dataList:null,//查询之后接口返回集合
                page:1,//当前页
                limit:10,//每页记录数
                total:0,//总记录数
                TagQueryParams:{
                },//条件封装对象
                Visible:false,
                type:'',
                tag:{
                    sort:0,
                },
            }
        },
        created() {
            this.getAllTags()
        },
        methods:{  //创建具体的方法，调用teacher.js定义的方法
            //列表的方法
            getAllTags(page=1) {
                this.page = page
                tag.getAllTags(this.page,this.limit,this.TagQueryParams)
                    .then(response =>{//请求成功
                        this.dataList = response.data.data.records
                        this.total = response.data.data.total
                    })
            },
            resetData() {//清空的方法
                //表单输入项数据清空
                this.TagQueryParams = {}
                this.getAllTags()
            },
            cancel(){
                this.Visible =false
            },
            getTagInfo(tagId){
                this.Visible = true
                this.type='update'
                tag.getTagInfo(tagId).then(response =>{//请求成功
                    this.tag = response.data.data
                })
            },
            removeDataById(id) {
                this.$confirm('此操作将永久删除视频记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {  //点击确定，执行then方法
                    //删除视频和视频的资源
                    tag.removeAll(id).then(response=>{
                        //提示信息
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        //回到列表页面
                        this.getAllTags()
                    })
                }) //点击取消，执行catch方法
            },
            addOrUpdateTag(type){
                console.log(type)
                if(type=='add'){
                    tag.addTag(this.tag).then(response=>{
                        this.$message({
                            type: 'success',
                            message: '添加成功!'
                        });
                        this.tag.label=''
                        this.tag.sort = 0
                    })

                }else{
                    tag.updateTag(this.tag).then(response=>{
                        this.$message({
                            type: 'success',
                            message: '修改成功!'
                        });
                        this.tag.label=''
                        this.tag.sort = 0
                    })
                }
                this.Visible = false;
                this.getAllTags()
            }
        }
    }
</script>