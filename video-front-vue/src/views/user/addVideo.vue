<template>
  <div>
    <!-- right side content area -->
    <div class="large-8 columns profile-inner">
      <!-- profile settings -->
      <section class="submit-post">
        <div class="row secBg">
          <div class="large-12 columns">
            <div class="heading">
              <i class="fa fa-pencil-square-o"></i>
              <h4>上传视频</h4>
            </div>
            <div class="row">
              <div class="large-12 columns">

                <form data-abide="" novalidate="">
                  <div data-abide-error="" class="alert callout" style="display: none;">
                    <p><i class="fa fa-exclamation-triangle"></i>
                      There are some errors in your form.</p>
                  </div>
                  <div class="row">
                    <div class="large-12 columns">
                      <label>视频标题
                        <input type="text" placeholder="enter you video title..." required="" v-model="videoInfo.title">
                        <span class="form-error">
                          Yo, you had better fill this out, it's required.
                        </span>
                      </label>
                    </div>
                    <div class="large-12 columns">
                      <label>视频描述
                        <textarea v-model="videoInfo.description"></textarea>
                      </label>
                    </div>
                    <div class="large-12 columns">
                      <h6 class="borderBottom">选择视频分类</h6>
                      <el-select v-model="videoInfo.categoryParentId" placeholder="一级分类"
                        @change="subjectLevelOneChanged">
                        <el-option v-for="subject in subjectOneList" :key="subject.id" :label="subject.title"
                          :value="subject.id" />
                      </el-select>

                      <!-- 二级分类 -->
                      <el-select v-model="videoInfo.categoryId" placeholder="二级分类">
                        <el-option v-for="subject in subjectTwoList" :key="subject.id" :label="subject.title"
                          :value="subject.id" />
                      </el-select>
                    </div>
                    <div class="large-12 columns">
                      <h6 class="borderBottom">视频封面</h6>
                      <el-upload class="avatar-uploader" :action="BASE_API + '/thirdPart/fileoss/uploadOssFile'"
                        :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="videoInfo.cover" :src="videoInfo.cover" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                      </el-upload>
                    </div>
                    <div class="large-12 columns">
                      <h6 class="borderBottom">上传视频</h6>
                      <el-upload :on-success="handleVodUploadSuccess" :on-remove="handleVodRemove"
                        :before-remove="beforeVodRemove" :on-exceed="handleUploadExceed" :file-list="fileList"
                        :action="BASE_API + '/thirdPart/fileoss/uploadVideoFile'" :limit="1" class="upload-demo">
                        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                      </el-upload>
                    </div>
                    <div>&nbsp;</div>
                    <div class="large-12 columns">
                      <h6 class="borderBottom">标签</h6>
                       <Select v-model="videoInfo.selectTags"  multiple  style="width:260px">
                        <Option v-for="item in tagList" :value="item.id" :key="item.id">{{ item.label }}</Option>
                       </Select>
                    </div>
                    <div class="large-12 columns">
                      <button  class="button expanded button-submit" type="button" name="submit" @click="publish">发布</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </section><!-- End profile settings -->
    </div><!-- end left side content area -->
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import category from "@/api/category";
import vod from "@/api/video";
import tag from "@/api/tag";
import {videoInfoAlidate} from '@/utils/validate';
export default {
  data() {
    return {
      saveBtnDisabled: false,
      videoInfo: {
        id: "",
        title: "",
        categoryId: "", //二级分类id
        categoryParentId: "", //一级分类id
        description: "",
        cover: "",
        price: 0,
        playerUrl: "",
        videoOriginalName: "", //视频名称
        selectTags:[],
      },
      tagList: [],
      userInfo: {},
      BASE_API: "http://43.248.97.192:9956/api", // 接口API地址
      subjectOneList: [], //一级分类
      subjectTwoList: [], //二级分类
      fileList: [], //上传文件列表
      addOrUpdate: "add",
    };
  },
  created() {
    if (this.$store.getters.getUser) {
      this.userInfo = JSON.parse(this.$store.getters.getUser)
      this.getTagList();
      if (this.$route.query && this.$route.query.videoId) {
        this.videoInfo.id = this.$route.query.videoId;
        this.getInfo();
        this.addOrUpdate = "update";
      } else {
        this.getOneSubject();
      }
    }
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
      // const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 5;

      // if (!isJPG) {
      //   this.$message.error("上传头像图片只能是 JPG 格式!");
      // }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 5MB!");
      }
      return isLt2M;
    },
    //得到视频信息
    getInfo() {
      vod.getVideoInfo(this.videoInfo.id).then((response) => {
        this.videoInfo = response.data.data;
        var url = response.data.data.playerUrl;
        var index = url.lastIndexOf("/")
        var name = url.substring(index + 1, url.length)
        this.fileList.push({ name: name, url: url })
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
      if (res.response) {
        file = res.response.data
      } else {
        file = res.url
      }
      var strs = file.split("//")
      var index = strs[1].indexOf("/")
      var fileName = strs[1].substring(index + 1, strs[1].length)

      if (this.videoInfo.id) {
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
    //-----------------------------------------------------------------

    publish() {
      //发布之前先要检验
      var res = videoInfoAlidate(this.videoInfo)
      if(res.flag){
        if (this.addOrUpdate === "add") {
          this.videoInfo.userId = this.userInfo.id
          this.videoInfo.username = this.userInfo.username
          this.videoInfo.avatar = this.userInfo.avatar
          vod.getPublish(this.videoInfo).then((response) => {
            this.$message({
              type: "success",
              message: "视频发布成功!",
            });
          });
        } else {
          //修改视频信息
          vod.updateVideoInfo(this.videoInfo).then(response => {
            this.$message({
              type: "success",
              message: "修改成功!",
            });
          })
        }
        this.$router.push({ path: "/dashboard/user/2/videos", query: { userId: this.userInfo.id } });
      }else{
        this.$message({
          type: "warning",
          message: `${res.data}`,
        });
      }
    },
    // ------------------------------------------------------------------------
    getTagList(){
      tag.getTagList().then(response=>{
        this.tagList = response.data.data
      })
    }
  },
};
</script>
<style >
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
