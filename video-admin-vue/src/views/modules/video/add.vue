<template>
  <el-card>
    <h2 style="text-align: center">发布新视频</h2>

    <el-steps
      :active="active"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写基本信息" />
      <el-step title="发布视频" />
      <el-step title="最终发布" />
    </el-steps>

    <el-form label-width="120px"  v-if="active == 1">
      <el-form-item label="视频标题" prop="title">
        <el-input
          v-model="videoInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>

      <!-- 所属分类 TODO -->
      <el-form-item label="视频分类" prop="category">
        <el-select
          v-model="videoInfo.categoryParentId"
          placeholder="一级分类"
          @change="subjectLevelOneChanged"
        >
          <el-option
            v-for="subject in subjectOneList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>

        <!-- 二级分类 -->
        <el-select v-model="videoInfo.categoryId" placeholder="二级分类">
          <el-option
            v-for="subject in subjectTwoList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
      </el-form-item>

      <!-- 课程简介 TODO -->
      <el-form-item label="视频描述" prop="description">
        <mavon-editor v-model="videoInfo.description" />
      </el-form-item>

      <!-- 课程封面 TODO -->
      <!-- 课程封面-->
      <el-form-item label="视频封面" prop="cover">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API + '/thirdPart/fileoss/uploadOssFile'"
          class="avatar-uploader"
        >
          <img :src="videoInfo.cover" />
        </el-upload>
      </el-form-item>

      <el-form-item label="视频价格">
        <el-input-number
          :min="0"
          v-model="videoInfo.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />
        元
      </el-form-item>

      <el-form-item label="标签">
        <el-select
          v-model="videoInfo.selectTags"
          multiple
          filterable
          allow-create
          default-first-option
          placeholder="请选择标签">
          <el-option
            v-for="item in tagList"
            :key="item.id"
            :label="item.label"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>


      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="next('two')"
          >保存并下一步</el-button
        >
      </el-form-item>
    </el-form>

    <!-- 第二步 -->
    <el-card v-if="active == 2">
      <el-upload
        :on-success="handleVodUploadSuccess"
        :on-remove="handleVodRemove"
        :before-remove="beforeVodRemove"
        :on-exceed="handleUploadExceed"
        :file-list="fileList"
        :action="BASE_API + '/thirdPart/fileoss/uploadVideoFile'"
        :limit="1"
        class="upload-demo"
    >
        <el-button slot="trigger" size="small" type="primary"
          >选取文件</el-button
        >
      </el-upload>
      <el-divider></el-divider>
      <div>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next('three')"
          >下一步</el-button
        >
      </div>
    </el-card>

    <!-- 第三步 -->
    <el-card v-if="active == 3">
      <div class="ccInfo">
        <img :src="videoInfo.cover" />
        <div class="main">
          <h2>标题:{{ videoInfo.title }}</h2>
          <p>描述：{{ videoInfo.description }}</p>
          <p>视频名称：{{ videoInfo.videoOriginalName }}</p>
          <h3 class="red">￥{{ videoInfo.price }}</h3>
        </div>
      </div>
      <div>
        <el-button @click="previous">上一步</el-button>
        <el-button type="primary" @click="publish">发布</el-button>
      </div>
    </el-card>
  </el-card>
</template>
  <script>
import category from "@/api/category/category";
import vod from "@/api/video/video";
import tag from "@/api/tag/tag"
export default {
  data() {
    return {
      active: 1,
      saveBtnDisabled: false,
      tagList: [],
      videoInfo: {
        id: "",
        title: "",
        categoryId: "", //二级分类id
        categoryParentId: "", //一级分类id
        description: "",
        cover:
          "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        price: 0,
        videoSourceId: "",
        videoOriginalName: "", //视频名称
      },
      BASE_API: window.SITE_CONFIG["apiURL"], // 接口API地址
      subjectOneList: [], //一级分类
      subjectTwoList: [], //二级分类
      fileList: [], //上传文件列表
      addOrUpdate: "add",
      selectTags:[],
    };
  },
  created() {
    if (this.$route.query && this.$route.query.id) {
      this.videoInfo.id = this.$route.query.id;
      this.getInfo();
      this.addOrUpdate = "update";
    } else {
      this.getOneSubject();
    }
    this.getTagList()
  },
  methods: {
    //点击某个一级分类，触发change，显示对应二级分类
    subjectLevelOneChanged(value) {
      //value就是一级分类id值
      //遍历所有的分类，包含一级和二级
      for (var i = 0; i < this.subjectOneList.length; i++) {
        //每个一级分类
        var oneSubject = this.subjectOneList[i];
        //判断：所有一级分类id 和 点击一级分类id是否一样
        if (value === oneSubject.id) {
          //从一级分类获取里面所有的二级分类
          this.subjectTwoList = oneSubject.children;
          //把二级分类id值清空
          this.videoInfo.subjectId = "";
        }
      }
    },
    //查询所有的一级分类
    getOneSubject() {
      category.getAllCategory().then((response) => {
        this.subjectOneList = response.data.data;
      });
    },
    //上传封面成功调用的方法
    handleAvatarSuccess(res, file) {
      this.videoInfo.cover = res.data;
    },
    //上传之前调用的方法
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    //得到视频信息
    getInfo() {
      vod.getVideoInfo(this.videoInfo.id).then((response) => {
        
        this.videoInfo = response.data.data;
        var url = response.data.data.playerUrl;
        var index = url.lastIndexOf("/")
        var name = url.substring(index+1,url.length)
        this.fileList.push({name:name,url:url})
        //1 查询所有的分类，包含一级和二级
        category.getAllCategory().then((response) => {
          //2 获取所有一级分类
          this.subjectOneList = response.data.data;
          //3 把所有的一级分类数组进行遍历，
          for (var i = 0; i < this.subjectOneList.length; i++) {
            //获取每个一级分类
            var oneSubject = this.subjectOneList[i];

            if (this.videoInfo.categoryParentId == oneSubject.id) {
              //获取一级分类所有的二级分类
              this.subjectTwoList = oneSubject.children;
            }
          }
        });
      });
    },
    //----------------------------------------------------------
    //==================视频操作=======
    //上传视频成功调用的方法
    handleVodUploadSuccess(response, file, fileList) {
      //上传视频id赋值
      this.videoInfo.playerUrl = response.data.url;
      this.videoInfo.duration = response.data.duration
      //上传视频名称赋值
      this.videoInfo.videoOriginalName = file.name;

      //目前只能上传一个视频
      for (var i = 0; i < fileList.length; i++) {
        this.fileList.push(fileList[i]);
      }
      this.saveBtnDisabled = false;
    },
    handleUploadExceed() {
      this.$message.warning("想要重新上传视频，请先删除已上传的视频");
    },
    //点击×调用这个方法
    beforeVodRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    //点击确定调用的方法
    handleVodRemove(res, fileList) {
      var file = ''
      if(res.response){
        file = res.response.data
      }else{
        file = res.url
      }
      var strs = file.split("//")
      var index = strs[1].indexOf("/")
      var fileName = strs[1].substring(index+1,strs[1].length)
    
      if(this.videoInfo.id){
        vod.deletePlayerUrl(this.videoInfo.id).then()
      }
      //调用接口的删除视频的方法
      vod.deleteAliyunFile(fileName).then((response) => {

      //提示信息
        this.$message({
          type: "success",
          message: "删除视频成功!",
        });
       //把文件列表清空
        this.fileList = [];
       //把video视频id和视频名称值清空
        //上传视频id赋值
        this.videoInfo.playerUrl = "";
        //上传视频名称赋值
        this.videoInfo.videoOriginalName = "";
      });
    },
    previous() {
      this.active--;
    },
    next(item) {
      if(item==='two'){
         if(this.videoInfo.title&&this.videoInfo.categoryId&&this.videoInfo.description){
            this.active++ 
         }else{
            this.$message({
            type: "warning",
            message: "请输入完整信息",
          });
         }
      }else{
        if(this.videoInfo.playerUrl){
           this.active++;
        }else{
          this.$message({
            type: "warning",
            message: "请插入一条视频",
          });
        }
      }
    },
    //-----------------------------------------------------------------

    publish() {
      if (this.addOrUpdate === "add") {
        console.log(this.videoInfo)
        vod.getPublish(this.videoInfo).then((response) => {
          //添加视频，并跳转到list页面
          //提示
          this.$message({
            type: "success",
            message: "视频发布成功!",
          });
        });
      }else{
         //修改视频信息
        vod.updateVideoInfo(this.videoInfo).then(response=>{
          this.$message({
            type: "success",
            message: "视频修改成功!",
          });
        })
      }
      this.$router.push({ path: "/video-list" });
      this.active = 1
    },
     // ------------------------------------------------------------------------
     getTagList(){
      tag.getTagList().then(response=>{
        this.tagList = response.data.data
      })
    }
  },
  mounted() {},
};
</script>

<style scoped>
.ccInfo {
  background: #f5f5f5;
  padding: 20px;
  overflow: hidden;
  border: 1px dashed #ddd;
  margin-bottom: 40px;
  position: relative;
}
img {
  background: #d6d6d6;
  width: 500px;
  height: 278px;
  display: block;
  float: left;
  border: none;
}
.ccInfo .main {
  margin-left: 520px;
}

.ccInfo .main h2 {
  font-size: 28px;
  margin-bottom: 30px;
  line-height: 1;
  font-weight: normal;
}
.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}

.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}
.ccInfo .main h3 {
  left: 540px;
  bottom: 20px;
  line-height: 1;
  font-size: 28px;
  color: #d32f24;
  font-weight: normal;
  position: absolute;
}
</style>
  