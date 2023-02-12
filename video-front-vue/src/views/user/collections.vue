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
                            <h4>收藏视频</h4>
                        </div>
                        <div class="profile-video" v-for="(collection, index) in dataList" :key="index">
                            <div class="media-object stack-for-small">
                                <div class="media-object-section media-img-content">
                                    <div class="video-img">
                                        <img :src="collection.cover" alt="video thumbnail">
                                    </div>
                                </div>
                                <div class="media-object-section media-video-content">
                                    <div class="video-content">
                                        <h5><a
                                                :href="`/dashboard/videoInfo/${collection.videoId}`">{{ collection.title }}</a>
                                        </h5>
                                        <p>{{ collection.description }}</p>
                                    </div>
                                    <div class="video-detail clearfix">
                                        <div class="video-stats">
                                            <span><i class="fa fa-clock-o"></i>{{ collection.time }}</span>
                                            <span><i class="fa fa-eye"></i>{{ collection.viewCount }}</span>
                                        </div>
                                        <div class="video-btns" v-show="isAdmin">
                                            <a class="video-btn" @click="delOneCollection(collection.videoId)"><i
                                                    class="fa fa-pencil-square-o"></i>取消收藏</a>
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
import collection from '@/api/collection';
export default {
    data() {
        //这里存放数据
        return {
            userId: '',
            dataList: [],
            page: 1,
            limit: 5,
            isEnd: false,
            loading: true,
            total: 0,
            isAdmin:false,
        };
    },
    //方法集合
    methods: {

        getAllUserCollectionLogs(userId) {
            collection.getAllUserCollectionLogs(this.page, this.limit, userId).then(response => {
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

        delOneCollection(videoId) {

            var model = {}
            model.userId = this.userId;
            model.videoId = videoId;

            collection.delUserCollectionLog(model).then(response => {
                this.$message({
                    message: '取消成功',
                    type: 'success'
                });
                this.getAllUserCollectionLogs(this.userId)
            })
        },
        showMore() {
            this.page = this.page + 1
            collection.getAllUserCollectionLogs(this.page, this.limit, this.userId).then(response => {
                this.isEnd = false;
                var videoList = this.dataList.concat(response.data.data.list)
                this.dataList = videoList
                if (videoList.length == this.total) {
                    this.loading = false
                    this.isEnd = true
                }
            })
        }

    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$store.getters.getUser) {
            var admin = JSON.parse(this.$store.getters.getUser)
                if (this.$route.query.userId) {
                    if(admin.id == this.$route.query.userId){
                        this.isAdmin = true
                    }
                    this.userId = this.$route.query.userId
                    this.getAllUserCollectionLogs(this.$route.query.userId)
                }
       }
    },
}
</script>
<style >

</style>
