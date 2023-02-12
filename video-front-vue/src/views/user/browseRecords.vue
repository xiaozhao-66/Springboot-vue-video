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
                            <h4>浏览记录</h4>
                        </div>
                        <div class="profile-video" v-for="(video, index) in dataList" :key="index">
                            <div class="media-object stack-for-small">
                                <div class="media-object-section media-img-content">
                                    <div class="video-img">
                                        <img :src="video.cover" alt="video thumbnail">
                                    </div>
                                </div>
                                <div class="media-object-section media-video-content">
                                    <div class="video-content">
                                        <h5><a :href="`/dashboard/videoInfo/${video.id}`">{{ video.title }}</a></h5>
                                        <p>{{ video.description }}</p>
                                    </div>
                                    <div class="video-detail clearfix">
                                        <div class="video-stats">
                                            <span><i class="fa fa-clock-o"></i>{{ video.updateTime }}</span>
                                            <span><i class="fa fa-eye"></i>{{ video.viewCount }}</span>
                                        </div>
                                        <div class="video-btns">
                                            <a class="video-btn" @click="delOneRecord(video.id)"><i
                                                    class="fa fa-pencil-square-o"></i>删除记录</a>
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
import userViewLogs from '@/api/userViewLogs';
export default {
    data() {
        //这里存放数据
        return {
            userInfo: {},
            dataList: [],
            page: 1,
            limit: 2,
            total: 0,
            isEnd: false,
            loading: true,
        };
    },
    //方法集合
    methods: {

        getAllUserViewLogs(userId) {
            userViewLogs.getAllUserViewLogs(this.page, this.limit, userId).then(response => {
                this.dataList = response.data.data.list
                this.total = response.data.data.count
                if (this.total > this.limit) {
                    this.loading = true
                } else {
                    this.loading = false
                    this.isEnd = true
                }
            })
        },
        showMore() {
            this.page = this.page + 1
            userViewLogs.getAllUserViewLogs(this.page, this.limit, this.userInfo.id).then(response => {
                this.isEnd = false;
                var videoList = this.dataList.concat(response.data.data.list)
                this.dataList = videoList
                if (videoList.length == this.total) {
                    this.loading = false
                    this.isEnd = true
                }
            })
        },
        delOneRecord(videoId) {
            userViewLogs.delOneRecord(this.userInfo.id, videoId).then(response => {
                this.$message({
                    message: '删除成功',
                    type: 'success'
                });
                this.getAllUserViewLogs(this.userInfo.id)

            })
        }
    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$store.getters.getUser) {
            this.userInfo = JSON.parse(this.$store.getters.getUser)
            this.getAllUserViewLogs(this.userInfo.id)
        }
    },
}
</script>
<style >

</style>
