<template>
    <!-- Recent post videos -->
    <div class="large-12 medium-7 medium-centered columns">
        <div class="widgetBox">
            <div class="widgetTitle">
                <h5>热门视频</h5>
            </div>
            <div class="widgetContent">
                <div class="media-object stack-for-small" v-for="(video, index) in popularVideos" :key="index">
                    <div class="media-object-section">
                        <div class="recent-img">
                            <img :src="video.cover" alt="recent">
                            <a :href="`/dashboard/videoInfo/${video.id}`" class="hover-posts">
                                <span><i class="fa fa-play"></i></span>
                            </a>
                        </div>
                    </div>
                    <div class="media-object-section">
                        <div class="media-content">
                            <h6><a :href="`/dashboard/videoInfo/${video.id}`">{{ video.title }}</a></h6>
                            <el-row>
                                <el-col :span="14">
                                    <p><i class="fa fa-user"></i><span>{{ video.username }}</span></p>
                                </el-col>
                                <el-col :span="10">
                                    <p><i class="fa fa-eye"></i><span>{{ video.viewCount }}</span></p>
                                </el-col>
                            </el-row>
                            <p><i class="fa fa-clock-o"></i><span>{{ video.gmtModified }}</span></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- End Recent post videos -->
</template>

<script>
import video from '@/api/video';

export default {
    data() {
        //这里存放数据
        return {
            popularVideos: [],
            videoId: '',

        };
    },
    //方法集合
    methods: {

        getPopularVideos() {
            video.getPopularVideos().then(response => {
                this.popularVideos = response.data.data
            })
        }

    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        this.getPopularVideos()
    }

}
</script>

<style>

</style>