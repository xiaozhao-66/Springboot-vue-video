<template>
    <div>
        <div class="row">
            <!-- left side content area -->
            <div class="large-8 columns">
                <!--single inner video-->
                <section class="inner-video">
                    <div class="row secBg">
                        <div class="large-12 columns inner-flex-video" style="position: relative;">

                            <div class="flex-video widescreen">
                                <vue-aliplayer-v2 :source="url" ref="player" style="height: 370px;margin-top: 5px" />
                            </div>
                            <div class="bulletFrame" v-show="M">
                                <p>您需要支付 {{ videoInfo.price }} 元才能观看此视频</p>
                                <el-button type="primary" @click="submitOrder">支付</el-button>
                            </div>
                        </div>
                    </div>
                </section>
                <!-- single post stats -->
                <section class="SinglePostStats">
                    <!-- newest video -->
                    <div class="row secBg">
                        <div class="large-12 columns">
                            <div class="media-object stack-for-small">
                                <div class="media-object-section">
                                    <div class="author-img-sec">
                                        <div class="thumbnail author-single-post">
                                            <a :href="('/dashboard/user/1/aboutme?userId=' + videoInfo.userId)"><img
                                                    :src="videoInfo.avatar" alt="post"></a>
                                        </div>
                                        <p class="text-center">
                                            <a
                                                :href="('/dashboard/user/1/aboutme?userId=' + videoInfo.userId)">{{ videoInfo.username }}</a>

                                        </p>
                                    </div>
                                </div>
                                <div class="media-object-section object-second">
                                    <div class="author-des clearfix">
                                        <div class="post-title">
                                            <h4>{{ videoInfo.title }}</h4>
                                            <p>
                                                <span><i class="fa fa-clock-o"></i>{{ videoInfo.gmtModified }}</span>
                                                <span><i class="fa fa-eye"></i>{{ videoInfo.viewCount }}</span>
                                                <span><i class="fa fa-thumbs-o-up"></i>{{ videoInfo.supportCount }}</span>
                                                <span><i
                                                        class="fa fa-thumbs-o-down"></i>{{ videoInfo.unsupportCount }}</span>
                                            </p>
                                        </div>
                                        <div style="margin-left: 80%;" v-show="H">
                                            <el-button type="danger" @click="addCollection(videoInfo.id)" v-if="store"
                                                round size="mini">收藏</el-button>
                                            <el-button type="info" @click="delCollection(videoInfo.id)" v-else round
                                                size="mini">取消收藏</el-button>
                                        </div>
                                    </div>
                                    <div class="social-share">
                                        <div class="post-like-btn clearfix">
                                            <span v-show="H">
                                                <el-tooltip content="关注用户" placement="top" v-if='T'>
                                                    <el-button icon="el-icon-user" @click="follow(videoInfo.userId)"
                                                        circle size="small"></el-button>
                                                </el-tooltip>
                                                <el-tooltip content="取消关注" placement="top" v-else>
                                                    <el-button icon="el-icon-user-solid"
                                                        @click="cancelFollow(videoInfo.userId)" circle
                                                        size="small"></el-button>
                                                </el-tooltip>
                                            </span>
                                            <a class="secondary-button" @click="likeIt(0)" v-if="UP"><i
                                                    class="fa fa-thumbs-o-up"></i></a>
                                            <a class="secondary-button-select" @click="likeIt(1)" v-else><i
                                                    class="fa fa-thumbs-o-up"></i></a>
                                            <a class="secondary-button" @click="downLikeIt(0)" v-if="DOWN"><i
                                                    class="fa fa-thumbs-o-down"></i></a>
                                            <a class="secondary-button-select" @click="downLikeIt(1)" v-else><i
                                                    class="fa fa-thumbs-o-down"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section><!-- End single post stats -->

                <!-- single post description -->
                <section class="singlePostDescription">
                    <div class="row secBg">
                        <div class="large-12 columns">
                            <div class="heading">
                                <h5>视频描述</h5>
                            </div>
                            <div class="description showmore_one">
                                <!-- markdown语法 -->
                                <div v-html="markdownToHtml"> </div>

                                <div class="tags">
                                    <button><i class="fa fa-tags"></i>Tags</button>
                                  <a href="#" class="inner-btn" v-for="(tag,index) in videoInfo.tags" :key="index">{{tag.label}}</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </section><!-- End single post description -->

                <relation-videos ref="myRelationVideos"></relation-videos>

                <!-- Comments -->
                <comment ref="myComment"></comment>
                <!-- End Comments -->

            </div><!-- end left side content area -->
            <!-- sidebar -->
            <div class="large-4 columns">
                <aside class="secBg sidebar">
                    <div class="row">
                        <!-- search Widget -->
                        <div class="large-12 medium-7 medium-centered columns">
                            <div class="widgetBox">
                                <div class="widgetTitle">
                                    <h5>搜索视频</h5>
                                </div>
                                <form id="searchform" method="get" role="search">
                                    <div class="input-group">
                                        <input class="input-group-field" type="text" placeholder="Enter your keyword">
                                        <div class="input-group-button">
                                            <input type="submit" class="button" value="搜索">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div><!-- End search Widget -->


                        <Social></Social>

                        <!-- 受欢迎的视频 -->
                        <PopularVideos></PopularVideos>

                        <!-- tags -->
                      <Tags></Tags>

                    </div>
                </aside>
            </div><!-- end sidebar -->
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import video from '@/api/video';
import { marked } from 'marked';
import userViewLogs from '@/api/userViewLogs';
import collection from '@/api/collection';
import Comment from '@/components/comment'
import PopularVideos from '@/components/popularvideos'
import RelationVideos from '@/components/relationVideos';
import follow from '@/api/follow'
import order from '@/api/order';
import likeIt from '@/api/likeIt'
import Tags from '@/components/tags'
import Social from '@/components/social'
export default {
    //import 引入的组件需要注入到对象中才能使用
    components: {
        Comment,
        PopularVideos,
        RelationVideos,
        Tags,
        Social,
    },
    data() {
        //这里存放数据
        return {
            url: "",
            videoInfo: {},
            userInfo: {},
            vplayerInfo: {},
            store: true,//是否收藏
            url: '',
            T: true,//是否关注
            H: true,//是否是本用户上传的视频
            M: false,//弹窗
            UP: true,
            DOWN: true,
        };
    },
    //计算属性类似于data 概念
    computed: {
        markdownToHtml() {
            return marked(this.videoInfo.description);
        }
    },
    //方法集合
    methods: {

        getVideoInfo(videoId) {
            video.getVideoInfo(videoId).then(response => {
                this.videoInfo = response.data.data
                this.checkFollow(this.videoInfo.userId)
                this.checkCollection(this.videoInfo.id)
                if (this.userInfo.id == this.videoInfo.userId) {
                    this.H = false
                    this.url = this.videoInfo.playerUrl
                } else {
                    //这里就要进行判断
                    order.payStatus(this.userInfo.id, this.videoInfo.id).then(response => {
                        if (response.data.code === 0) {
                            this.url = this.videoInfo.playerUrl
                        } else {
                            this.M = true
                        }
                    })
                }
            })
        },

        //添加浏览记录
        addUserViewLogs(videoId) {
            var userViewLogsInfo = {}
            userViewLogsInfo.userId = this.userInfo.id;
            userViewLogsInfo.videoId = videoId;
            userViewLogs.addUserViewLogs(userViewLogsInfo).then()
        },

        //添加收藏
        addCollection(videoId) {
            var model = {}
            model.userId = this.userInfo.id
            model.videoId = videoId
            collection.addUserCollectionLog(model).then()
            this.store = false
        },

        //取消收藏
        delCollection(videoId) {
            var model = {}
            model.userId = this.userInfo.id
            model.videoId = videoId
            collection.delUserCollectionLog(model).then()
            this.store = true
        },
        //校验当前收藏是否存在
        checkCollection(videoId) {

            var model = {}
            model.userId = this.userInfo.id
            model.videoId = videoId

            collection.checkCollection(model).then(response => {
                if (response.data.data) {
                    this.store = false
                }
            })
        },
        //添加关注
        follow(userId) {
            this.T = false;
            var followEntity = {}
            followEntity.userId = this.userInfo.id
            followEntity.followUserId = userId

            follow.addFollow(followEntity).then()

        },
        //取消关注
        cancelFollow(userId) {
            this.T = true;
            var followEntity = {}
            followEntity.userId = this.userInfo.id
            followEntity.followUserId = userId
            follow.cancelFollow(followEntity).then()
        },
        checkFollow(userId) {
            var followEntity = {}
            followEntity.userId = this.userInfo.id
            followEntity.followUserId = userId

            follow.checkFollow(followEntity).then(response => {

                if (response.data.data) {
                    this.T = false
                }
            })
        },
        //
        submitOrder() {
            window.location.href = "http://43.248.97.192:9956/api/video/front/order/addOrder/" + this.userInfo.id + "/" + this.videoInfo.id
        },
        //好评
        likeIt(select) {
            var likeItModel = {}
            likeItModel.userId = this.userInfo.id
            likeItModel.videoId = this.videoInfo.id
            likeItModel.type = 1
            if (select == 0) {
                //点赞
                likeIt.support(likeItModel).then(response => {
                    this.UP = false
                    this.DOWN = true
                })
            } else {
                //取消点赞
                likeIt.cancelSupport(likeItModel).then(response => {
                    this.UP = true
                    this.DOWN = true
                })
            }
        },
        downLikeIt(select) {
            var likeItModel = {}
            likeItModel.userId = this.userInfo.id
            likeItModel.videoId = this.videoInfo.id
            likeItModel.type = 0
            if (select == 0) {
                //差评
                likeIt.support(likeItModel).then(response => {
                    this.DOWN = false
                    this.UP = true
                })

            } else {
                //取消差评
                likeIt.cancelSupport(likeItModel).then(response => {
                    this.DOWN = true
                    this.UP = true
                })
            }
        },
        getRecord(videoId) {
            likeIt.getRecord(this.userInfo.id, videoId).then(response => {

                if (response.data.data == 1) {
                    this.DOWN = true
                    this.UP = false
                } else if (response.data.data == 0) {
                    this.UP = true
                    this.DOWN = false
                } else {
                    this.UP = true
                    this.DOWN = true
                }
            })
        },

    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$store.getters.getUser) {
            this.userInfo = JSON.parse(this.$store.getters.getUser)
        }
        if (this.$route.params) {
            this.getVideoInfo(this.$route.params.id)
            this.addUserViewLogs(this.$route.params.id)
            this.getRecord(this.$route.params.id)
        }

    },
}
</script>
<style>
.bulletFrame {
    position: absolute;
    top: 6vw;
    left: 12vw;
    background-color: white;
    width: 250px;
    height: 100px;
}

.bulletFrame p {
    margin-left: 15px;
    margin-top: 5px;
}

.bulletFrame .el-button {
    margin-left: 80px;
}

.secondary-button-select {
    width: 30px;
    height: 30px;
    line-height: 30px;
    border-radius: 3px;
    background: #e96969;
    display: inline-block;
    color: #fff;
    text-align: center;
}
</style>
