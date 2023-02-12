<template>
    <div>
        <!-- right side content area -->
        <div class="large-8 columns profile-inner">
            <!-- single post description -->
            <section class="profile-videos">
                <div class="row secBg">
                    <div class="large-12 columns">
                        <div class="heading">
                            <i class="fa fa-video-camera"></i>
                            <h4>My Videos</h4>
                        </div>
                        <div class="profile-video" v-for="(video, index) in dataList" :key="index">
                            <div class="media-object stack-for-small">
                                <div class="media-object-section media-img-content">
                                    <div class="video-img">
                                        <a @click = "open(video.playerUrl,index)">
                                            <el-tooltip content="点击浏览" placement="top" effect="light">
                                                <img :src="video.cover" alt="video thumbnail">
                                            </el-tooltip>
                                          
                                        </a>
                                        <!-- 观看小视频 -->
                                        <div :class="videoPlayer==index ? ' video-palyer' : 'video-palyer actived'">
                                            <div class="palyer_close">
                                                <div class="palyer_close_icon">
                                                    <a @click ="close"><i class="el-icon-close"></i></a> 
                                                </div>
                                            </div>
                                            <div class="aliplayer">
                                                <vue-aliplayer-v2 :source="url" ref="player"  />
                                            </div>
                                        </div>
                                </div>
                                </div>

                                

                                <div class="media-object-section media-video-content">
                                    <div class=" check-video float-right" v-if="video.status==0">
                                        <p>审核中...</p>
                                    </div>
                                    <div class="video-content">
                                            <el-tooltip content="内容还在审核..." placement="top" effect="dark" v-if="video.status==0">
                                                <h5>{{ video.title }}</h5> 
                                            </el-tooltip>
                                         <a v-else :href="(`/dashboard/videoInfo/${video.id}`)"><h5>{{ video.title }}</h5></a>
                                        <p>{{ video.description }}</p>
                                    </div>
                                   
                                    <div class="video-detail clearfix">
                                        <div class="video-stats">
                                            <span><i class="fa fa-clock-o"></i>{{ video.gmtModified }}</span>
                                            <span><i class="fa fa-eye"></i>{{ video.viewCount }}</span>
                                        </div>
                                        <div class="video-btns" v-show="T">
                                            <a class="video-btn" @click="editVideo(video.id)"><i
                                                    class="fa fa-pencil-square-o"></i>编辑</a>
                                            <a class="video-btn" @click="deleteVideo(video.id)"><i
                                                    class="fa fa-pencil-square-o"></i>删除</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="show-more-inner text-center">
                            <el-link :underline="false" @click="showMore()" v-if="loading">点击加载更多</el-link>
                            <p v-if="isEnd">我也是有底线的~</p>
                        </div>
                    </div>
                </div>
            </section><!-- End single post description -->
        </div><!-- end left side content area -->
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import video from "@/api/video"
export default {
    data() {
        //这里存放数据
        return {
            dataList: [],
            T: false,
            userId: '',
            page: 1,
            limit: 5,
            isEnd: false,
            loading: true,
            videoPlayer:-1,
            url:'',
        };
    },
    //方法集合
    methods: {
        getAllVideosWithUser() {
            video.getAllVideosWithUser(this.page, this.limit, this.userId).then(response => {
                this.dataList = response.data.data.records
                if (response.data.data.total > this.limit) {
                    this.loading = true
                } else {
                    this.loading = false
                    this.isEnd = true
                }
            })
        },
        editVideo(videoId) {
            this.$router.push({ path: '/dashboard/user/11/addVideo', query: { videoId: videoId } });
        },
        deleteVideo(videoId) {
            video.removeAll(videoId).then(response => {
                this.$message({
                    message: '删除成功',
                    type: 'success'
                });
                this.getAllVideosWithUser()
            })
        },
        showMore() {
            this.page = this.page + 1
            video.getAllVideosWithUser(this.page, this.limit, this.userId).then(response => {
                this.isEnd = false;
                var videoList = this.dataList.concat(response.data.data.records)
                this.dataList = videoList
                if (videoList.length == response.data.data.total) {
                    this.loading = false
                    this.isEnd = true
                }
            })
        },
        open(playerUrl,index) {
           this.videoPlayer=index
           this.url = playerUrl
           console.log(playerUrl)
        },
        close(){
            this.videoPlayer=-1
        }
    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$store.getters.getUser) {
            var user = JSON.parse(this.$store.getters.getUser)
            if (this.$route.query.userId) {
                this.userId = this.$route.query.userId
                this.getAllVideosWithUser()
                if (user.id == this.$route.query.userId) {
                    this.T = true;
                } else {
                    this.T = false;
                }
            }
        }

    },
}
</script>
<style >
.check-video{
    margin-top: 15px;
}
.video-img{
    position: relative;
}
.video-palyer{
    background-color: white;
    width: 500px;
    height: 200px;
    position: absolute;
    top:-40px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    z-index: 3;
}
.palyer-close{
   height: 25px;

}
.palyer_close_icon{
    margin-left: 480px;
}
.actived{
   display: none;
}
</style>
