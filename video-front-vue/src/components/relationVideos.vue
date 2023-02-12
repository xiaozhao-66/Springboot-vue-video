<template>
    <!-- related Posts -->
    <section class="content content-with-sidebar related">
        <div class="row secBg">
            <div class="large-12 columns">
                <div class="main-heading borderBottom">
                    <div class="row padding-14">
                        <div class="medium-12 small-12 columns">
                            <div class="head-title">
                                <i class="fa fa-film"></i>
                                <h4>相关视频</h4>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row list-group">
                    <div class="item large-4 columns end group-item-grid-default"
                        v-for="(videoInfo, index) in relationVideos" :key="index">
                        <div class="post thumb-border">
                            <div class="post-thumb">
                                <img :src="videoInfo.cover" alt="landing">
                                <a href="#" class="hover-posts">
                                    <span><i class="fa fa-play"></i>Watch Video</span>
                                </a>
                                <div class="video-stats clearfix">
                                    <div class="thumb-stats pull-left">
                                        <h6>HD</h6>
                                    </div>
                                    <div class="thumb-stats pull-left">
                                        <i class="fa fa-heart"></i>
                                        <span>{{ videoInfo.collectionCount }}</span>
                                    </div>
                                    <div class="thumb-stats pull-right">
                                                        <span>{{ videoInfo.duration }}</span>
                                                    </div>
                                </div>
                            </div>
                            <div class="post-des">
                                <h6><a href="#">{{ videoInfo.title }}</a></h6>
                                <div class="post-stats clearfix">
                                    <p class="pull-left">
                                        <i class="fa fa-user"></i>
                                        <span><a href="#">{{ videoInfo.username }}</a></span>
                                    </p>
                                    <p class="pull-left">
                                        <i class="fa fa-clock-o"></i>
                                        <span>{{ videoInfo.gmtModified }}</span>
                                    </p>
                                    <p class="pull-left">
                                        <i class="fa fa-eye"></i>
                                        <span>{{ videoInfo.viewCount }}</span>
                                    </p>
                                </div>
                                <div class="post-summary">
                                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium
                                        doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore
                                        veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam
                                        voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia
                                        consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
                                </div>
                                <div class="post-button">
                                    <a href="#" class="secondary-button"><i class="fa fa-play-circle"></i>watch
                                        video</a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section><!--end related posts-->
</template>

<script>
import video from '@/api/video'
export default {

    data() {
        //这里存放数据
        return {
            relationVideos: [],
            videoId: '',

        };

    },
    //方法集合
    methods: {
        getDataList() {

            video.getRelatedVideos(this.videoId).then(response => {
                this.relationVideos = response.data.data
            })
        }

    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        this.videoId = this.$route.params.id
        this.getDataList()
    }


}
</script>

<style>

</style>