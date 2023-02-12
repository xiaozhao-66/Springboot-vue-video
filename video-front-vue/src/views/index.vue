<template>
    <div>
        <!-- Premium Videos -->
        <section id="premium">
            <div class="row">
                <div class="heading clearfix">
                    <div class="large-11 columns">
                        <h4><i class="fa fa-play-circle-o"></i>推荐视频</h4>
                    </div>
                </div>
            </div>
            <div class="carousel">
                <el-carousel interval="6000" height="300px">
                    <el-carousel-item v-for="item in 6" :key="item">
                        <div class="carsousel-item">
                            <img src="@/assets/picture/premium1.png" alt="carousel">
                            <img src="@/assets/picture/premium2.png" alt="carousel">
                            <img src="@/assets/picture/premium3.png" alt="carousel">
                            <img src="@/assets/picture/premium4.png" alt="carousel">
                        </div>
                    </el-carousel-item>
                </el-carousel>
            </div>
        </section><!-- End Premium Videos -->
        <section class="category-content">
            <div class="row">
                <!-- left side content area -->
                <div class="large-8 columns">
                    <section class="content content-with-sidebar">
                        <!-- newest video -->
                        <div class="main-heading removeMargin">
                            <div class="row secBg padding-14 removeBorderBottom">
                                <div class="medium-8 small-8 columns">
                                    <div class="head-title">
                                        <i class="fa fa-film"></i>
                                        <h4>全部视频</h4>
                                    </div>
                                </div>
                                <div class="medium-4 small-4 columns">
                                    <ul class="tabs text-right pull-right" data-tabs="" id="newVideos">
                                        <li class="tabs-title is-active"><a href="#new-all">all</a></li>
                                        <li class="tabs-title"><a href="#new-hd">HD</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="row column head-text clearfix">
                                    <p class="pull-left">全部视频 : <span>{{ total }} Videos posted</span></p>
                                    <div class="grid-system pull-right show-for-large">
                                        <a class="secondary-button grid-default" href="#"><i class="fa fa-th"></i></a>
                                        <a class="secondary-button current grid-medium" href="#"><i
                                                class="fa fa-th-large"></i></a>
                                        <a class="secondary-button list" href="#"><i class="fa fa-th-list"></i></a>
                                    </div>
                                </div>
                                <div class="tabs-content" data-tabs-content="newVideos">
                                    <div class="tabs-panel  is-active " id="new-all">
                                        <div class="row list-group">
                                            <!-- 显示视频 -->
                                            <div class="item large-4 medium-6 columns grid-medium"
                                                v-for="(video, index) in dataList" :key="index">
                                                <div class="post thumb-border">
                                                    <div class="post-thumb">
                                                        <img :src="video.cover" alt="new video">
                                                        <a :href="`/dashboard/videoInfo/${video.id}`"
                                                            class="hover-posts">
                                                            <span><i class="fa fa-play"></i>Watch Video</span>
                                                        </a>
                                                        <div class="video-stats clearfix">
                                                            <div class="thumb-stats pull-left">
                                                                <h6>HD</h6>
                                                            </div>
                                                            <div class="thumb-stats pull-left">
                                                                <i class="fa fa-heart"></i>
                                                                <span>{{ video.collectionCount }}</span>
                                                            </div>
                                                            <div class="thumb-stats pull-right">
                                                                <span>{{ video.duration }}</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="post-des">
                                                        <h6><a href="single-video-v2.html">{{ video.title }}</a></h6>
                                                        <div class="post-stats clearfix">
                                                            <p class="pull-left">
                                                                <i class="fa fa-user"></i>
                                                                <span><a href="#">{{ video.username }}</a></span>
                                                            </p>
                                                            <p class="pull-left">
                                                                <i class="fa fa-clock-o"></i>
                                                                <span>{{ video.gmtCreate }}</span>
                                                            </p>
                                                            <p class="pull-left">
                                                                <i class="fa fa-eye"></i>
                                                                <span>{{ video.viewCount }}</span>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="pagination">
                                    <!-- 分页 -->
                                    <el-pagination :current-page="page" :page-size="limit" :total="total"
                                        style="padding: 30px 0; text-align: center;"
                                        layout="total, prev, pager, next, jumper" @current-change="getList" />
                                </div>
                            </div>
                        </div>
                    </section>
                    <!-- ad Section -->
                    <div class="googleAdv">
                        <a href="#"><img src="@/assets/picture/goodleadv.png" alt="googel ads"></a>
                    </div><!-- End ad Section -->
                </div><!-- end left side content area -->
                <!-- sidebar 侧边栏 -->
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
                                            <input class="input-group-field" type="text"
                                                placeholder="Enter your keyword" v-model="videoFrontQuery.searchInfo">
                                            <div class="input-group-button">
                                                <button type="info" @click="getList()" class="s-button">搜索</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div><!-- End search Widget -->

                            <!-- categories -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>视频分类</h5>
                                    </div>
                                    <div class="widgetContent">
                                        <ul class="accordion" data-accordion="">
                                            <li :class="focus == index ? '  accordion-item is-active' : 'accordion-item'"
                                                data-accordion-item="" v-for="(categoryOne, index) in categoryList"
                                                :key="index">
                                                <span class="accordion-title"
                                                    @click="click(index)">{{ categoryOne.title }}</span>
                                                <div class="accordion-content" data-tab-content=""
                                                    :style="focus == index ? 'display:inline' : 'dispaly:none'">
                                                    <ul>
                                                        <li class="clearfix"
                                                            v-for="(categoryTwo, item) in categoryOne.children"
                                                            :key="item">
                                                            <i class="fa fa-play-circle-o"></i>
                                                            <a
                                                                @click="getVideoByCategory(categoryTwo.id)">{{ categoryTwo.title }}</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <Social></Social>
                            
                            <PopularVideos></PopularVideos>

                            <!-- tags -->
                           <Tags></Tags>
                        </div>
                    </aside>
                </div><!-- end sidebar -->
            </div>
        </section><!-- End Category Content-->
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import video from '@/api/video'
import category from '@/api/category'
import PopularVideos from '@/components/popularvideos'
import Social from '@/components/social'
import Tags from '@/components/tags'
export default {
    //import 引入的组件需要注入到对象中才能使用
    components: {
        PopularVideos,
        Tags,
        Social,
    },
    data() {
        //这里存放数据
        return {
            dataList: [],
            categoryList: [],
            page: 1,//当前页
            limit: 12,//每页记录数
            total: 0,//总记录数
            videoFrontQuery: {
                categoryId: '',
                searchInfo: ''
            },//条件封装对象
            focus: 0,
        }
    },
    //方法集合
    methods: {
        //得到所有的视频
        getList(page = 1) {
            this.page = page
            video.getListPage(this.page, this.limit, this.videoFrontQuery)
                .then(response => {//请求成功
                    this.dataList = response.data.data.records

                    this.total = response.data.data.total
                })
        },
        //得到所有的分类
        getAllCategory() {
            category.getAllCategory().then(response => {
                this.categoryList = response.data.data
            })
        },
        click(index) {
            this.focus = index;
        },
        getVideoByCategory(categoryId) {
            this.videoFrontQuery.categoryId = categoryId
            this.getList()
        }
    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        this.videoFrontQuery.searchInfo = this.$route.query.content
        this.getList()
        this.getAllCategory()

    },
}
</script>
<style scoped>
@import '../assets/css/app.css';
@import '../assets/css/theme.css';
@import '../assets/css/font-awesome.min.css';
@import '../assets/css/owl.carousel.min.css';
.carsousel-item{
    text-align: center;
}
.s-button {
    background-color: #C1121C;
    width: 80px;
    height: 40px;
    border-radius: 6px;
    color: #fff;
}

.s-button:hover {
    background-color: #D47479;
}
</style>
