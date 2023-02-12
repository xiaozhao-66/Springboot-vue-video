<template>

<div class="off-canvas-wrapper">
    <div class="off-canvas-wrapper-inner" data-off-canvas-wrapper="">
        <!--header-->
        
        <div class="off-canvas-content" data-off-canvas-content="">
            <header>
                <!--导航栏设计-->
                 <section id="navBar">
                    <nav class="sticky-container" data-sticky-container="">


                        <el-row>
                          <el-col :span="6">
                             <img src="../../../assets/image/logo.png" style="height:60px">
                          </el-col>
                          <el-col :span="12">
                            <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                            <el-menu-item index="1">处理中心</el-menu-item>
                            <el-submenu index="2">
                                <template slot="title">我的工作台</template>
                                <el-menu-item index="2-1">选项1</el-menu-item>
                                <el-menu-item index="2-2">选项2</el-menu-item>
                                <el-menu-item index="2-3">选项3</el-menu-item>
                                <el-submenu index="2-4">
                                <template slot="title">选项4</template>
                                <el-menu-item index="2-4-1">选项1</el-menu-item>
                                <el-menu-item index="2-4-2">选项2</el-menu-item>
                                <el-menu-item index="2-4-3">选项3</el-menu-item>
                                </el-submenu>
                            </el-submenu>
                            <el-menu-item index="3" disabled>消息中心</el-menu-item>
                            <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
                            <el-menu-item index="5">处理中心</el-menu-item>
                            <el-menu-item index="6">处理中心</el-menu-item>
                            <el-menu-item index="7">处理中心</el-menu-item>
                        </el-menu>

                          </el-col>
                          <el-col :span="6">
                            <div class="top-button" style="margin-top: 15px;margin-right: 15px" >
                                <ul class="menu float-right" >
                                    <li>
                                        <a href="submit-post.html">upload Video</a>
                                    </li>
                                    <li class="dropdown-login">
                                        <a class="loginReg" data-toggle="example-dropdown" href="#">login/Register</a>
                                        <div class="login-form">
                                            <h6 class="text-center">Great to have you back!</h6>
                                            <form method="post">
                                                <div class="input-group">
                                                    <span class="input-group-label"><i class="fa fa-user"></i></span>
                                                    <input class="input-group-field" type="text" placeholder="Enter username">
                                                </div>
                                                <div class="input-group">
                                                    <span class="input-group-label"><i class="fa fa-lock"></i></span>
                                                    <input class="input-group-field" type="text" placeholder="Enter password">
                                                </div>
                                                <div class="checkbox">
                                                    <input id="check1" type="checkbox" name="check" value="check">
                                                    <label class="customLabel" for="check1">Remember me</label>
                                                </div>
                                                <input type="submit" name="submit" value="Login Now">
                                            </form>
                                            <p class="text-center">New here? <a class="newaccount" href="login-register.html">Create a new Account</a></p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                          </el-col>
                        </el-row>
                     

                    
                        

                    </nav>
                </section>
            </header>
            <!-- End Header -->
            <!-- Premium Videos -->
            <section id="premium">
                <div class="row">
                    <div class="heading clearfix">
                        <div class="large-11 columns">
                            <h4><i class="fa fa-play-circle-o"></i>Premium Videos</h4>
                        </div>
                    </div>
                </div>
   <el-carousel interval="6000" height="300px" style="margin-left: 20px;">
    <el-carousel-item v-for="item in 6" :key="item">
        <a href=""> <img src="../../../assets/picture/premium1.png" alt="carousel"></a>
        <a href=""> <img src="../../../assets/picture/premium1.png" alt="carousel"></a>
        <a href=""> <img src="../../../assets/picture/premium1.png" alt="carousel"></a>
        <a href=""> <img src="../../../assets/picture/premium1.png" alt="carousel"></a>        
    </el-carousel-item>
  </el-carousel>
                <div id="owl-demo" class="owl-carousel carousel" data-car-length="4" data-items="4" data-loop="true" data-nav="false" data-autoplay="true" data-autoplay-timeout="3000" data-dots="false" data-auto-width="false" data-responsive-small="1" data-responsive-medium="2" data-responsive-xlarge="5">
                    <!-- <div class="item">
                        <figure class="premium-img">
                            <img src="../../../assets/picture/premium1.png" alt="carousel">
                            <figcaption>
                                <h5>ICE Age 5 upcoming Movie</h5>
                                <p>Movies Trailer</p>
                            </figcaption>
                        </figure>
                        <a href="single-video-v1.html" class="hover-posts">
                           <span><i class="fa fa-play"></i>Watch Video</span>
                        </a>
                    </div> -->
    
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
                                            <h4>Entertainment</h4>
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
                                        <p class="pull-left">All Videos : <span>1,862 Videos posted</span></p>
                                        <div class="grid-system pull-right show-for-large">
                                            <a class="secondary-button grid-default" href="#"><i class="fa fa-th"></i></a>
                                            <a class="secondary-button current grid-medium" href="#"><i class="fa fa-th-large"></i></a>
                                            <a class="secondary-button list" href="#"><i class="fa fa-th-list"></i></a>
                                        </div>
                                    </div>
                                    <div class="tabs-content" data-tabs-content="newVideos">
                                        <div class="tabs-panel  is-active " id="new-all">
                                            <div class="row list-group" >
                                                <!-- 显示视频 -->
                                                <div class="item large-4 medium-6 columns grid-medium" v-for="(video, index) in dataList" ::key="video.id">
                                                    <div class="post thumb-border">
                                                        <div class="post-thumb">
                                                            <img :src="video.cover" alt="new video">
                                                            <a href="single-video-v2.html" class="hover-posts">
                                                                <span><i class="fa fa-play"></i>Watch Video</span>
                                                            </a>
                                                            <div class="video-stats clearfix">
                                                                <div class="thumb-stats pull-left">
                                                                    <h6>HD</h6>
                                                                </div>
                                                                <div class="thumb-stats pull-left">
                                                                    <i class="fa fa-heart"></i>
                                                                    <span>506</span>
                                                                </div>
                                                                <div class="thumb-stats pull-right">
                                                                    <span>05:56</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="post-des">
                                                            <h6><a href="single-video-v2.html">{{video.title}}</a></h6>
                                                            <div class="post-stats clearfix">
                                                                <p class="pull-left">
                                                                    <i class="fa fa-user"></i>
                                                                    <span><a href="#">admin</a></span>
                                                                </p>
                                                                <p class="pull-left">
                                                                    <i class="fa fa-clock-o"></i>
                                                                    <span>{{video.gmtModified}}</span>
                                                                </p>
                                                                <p class="pull-left">
                                                                    <i class="fa fa-eye"></i>
                                                                    <span>{{video.viewCount}}</span>
                                                                </p>
                                                            </div>
                                                            <div class="post-summary">
                                                                <p>{{video.description}}</p>
                                                            </div>
                                                            <div class="post-button">
                                                                <a href="single-video-v2.html" class="secondary-button"><i class="fa fa-play-circle"></i>watch video</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                       
                                        </div>
                                    </div>
                                    <div class="pagination">                  
                                        <!-- 分页 -->
                                        <el-pagination
                                            :current-page="page"
                                            :page-size="limit"
                                            :total="total"
                                            style="padding: 30px 0; text-align: center;"
                                            layout="total, prev, pager, next, jumper"
                                            @current-change="getList"
                                        />
                                    </div>
                                    </div>
                            </div>
                        </section>
                        <!-- ad Section -->
                        <div class="googleAdv">
                            <a href="#"><img src="static/picture/goodleadv.png" alt="googel ads"></a>
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
                                            <h5>Search Videos</h5>
                                        </div>
                                        <form id="searchform" method="get" role="search">
                                            <div class="input-group">
                                                <input class="input-group-field" type="text" placeholder="Enter your keyword">
                                                <div class="input-group-button">
                                                    <input type="submit" class="button" value="Submit">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div><!-- End search Widget -->

                                <!-- categories -->
                                <div class="large-12 medium-7 medium-centered columns">
                                    <div class="widgetBox">
                                        <div class="widgetTitle">
                                            <h5>categories</h5>
                                        </div>
                                        <div class="widgetContent">
                                            <ul class="accordion" data-accordion="">
                                                <li :class="focus == index ?'  accordion-item is-active':'accordion-item' " data-accordion-item="" v-for="(categoryOne,index) in categoryList" :key="index">
                                                    <span  class="accordion-title"   @click="click(index)" >{{categoryOne.title}}</span>
                                                    <div class="accordion-content" data-tab-content="" :style="focus == index ? 'display:inline':'dispaly:none'">
                                                       <ul>
                                                           <li class="clearfix" v-for="categoryTwo in categoryOne.children"  >
                                                               <i class="fa fa-play-circle-o"></i>
                                                               <a href="#">{{categoryTwo.title}} <span>(10)</span></a>
                                                           </li>
                                                       </ul>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <!-- social Fans Widget -->
                                <div class="large-12 medium-7 medium-centered columns">
                                    <div class="widgetBox">
                                        <div class="widgetTitle">
                                            <h5>social fans</h5>
                                        </div>
                                        <div class="widgetContent">
                                            <div class="social-links">
                                                <a class="socialButton" href="#">
                                                    <i class="fa fa-facebook"></i>
                                                    <span>698K</span>
                                                    <span>fans</span>
                                                </a>
                                                <a class="socialButton" href="#">
                                                    <i class="fa fa-twitter"></i>
                                                    <span>598</span>
                                                    <span>followers</span>
                                                </a>
                                                <a class="socialButton" href="#">
                                                    <i class="fa fa-google-plus"></i>
                                                    <span>98k</span>
                                                    <span>followers</span>
                                                </a>
                                                <a class="socialButton" href="#">
                                                    <i class="fa fa-youtube"></i>
                                                    <span>168k</span>
                                                    <span>followers</span>
                                                </a>
                                                <a class="socialButton" href="#">
                                                    <i class="fa fa-vimeo"></i>
                                                    <span>498</span>
                                                    <span>followers</span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div><!-- End social Fans Widget -->

                                <!-- Recent post videos -->
                                <div class="large-12 medium-7 medium-centered columns">
                                    <div class="widgetBox">
                                        <div class="widgetTitle">
                                            <h5>Recent post videos</h5>
                                        </div>
                                        <div class="widgetContent">
                                            <div class="media-object stack-for-small" >
                                                <div class="media-object-section">
                                                    <div class="recent-img">
                                                        <img src="static/picture/category4.png" alt="recent">
                                                        <a href="#" class="hover-posts">
                                                            <span><i class="fa fa-play"></i></span>
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="media-object-section">
                                                    <div class="media-content">
                                                        <h6><a href="#">The lorem Ipsumbeen the industry's standard.</a></h6>
                                                        <p><i class="fa fa-user"></i><span>admin</span><i class="fa fa-clock-o"></i><span>5 january 16</span></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div><!-- End Recent post videos -->

                                <!-- tags -->
                                <div class="large-12 medium-7 medium-centered columns">
                                    <div class="widgetBox">
                                        <div class="widgetTitle">
                                            <h5>Tags</h5>
                                        </div>
                                        <div class="tagcloud">
                                            <a href="#" v-for="category in categoryList">{{category.title}}</a>
                                        </div>
                                    </div>
                                </div><!-- End tags -->


                            </div>
                        </aside>
                    </div><!-- end sidebar -->
                </div>
            </section><!-- End Category Content-->
            <!-- footer -->
            <footer>
                <div class="row">
                    <div class="large-3 medium-6 columns">
                        <div class="widgetBox">
                            <div class="widgetTitle">
                                <h5>About Betube</h5>
                            </div>
                            <div class="textwidget">
                                Betube video wordpress theme lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s book.
                            </div>
                        </div>
                    </div>
                    <div class="large-3 medium-6 columns">
                        <div class="widgetBox">
                            <div class="widgetTitle">
                                <h5>Recent Videos</h5>
                            </div>
                            <div class="widgetContent">
                                <div class="media-object">
                                    <div class="media-object-section">
                                        <div class="recent-img">
                                            <img src="static/picture/recent1.png" alt="recent">
                                            <a href="#" class="hover-posts">
                                                <span><i class="fa fa-play"></i></span>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="media-object-section">
                                        <div class="media-content">
                                            <h6><a href="#">The lorem Ipsumbeen the industry's standard.</a></h6>
                                            <p><i class="fa fa-user"></i><span>admin</span><i class="fa fa-clock-o"></i><span>5 january 16</span></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="media-object">
                                    <div class="media-object-section">
                                        <div class="recent-img">
                                            <img src="static/picture/1.jpg" alt="recent">
                                            <a href="#" class="hover-posts">
                                                <span><i class="fa fa-play"></i></span>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="media-object-section">
                                        <div class="media-content">
                                            <h6><a href="#">The lorem Ipsumbeen the industry's standard.</a></h6>
                                            <p><i class="fa fa-user"></i><span>admin</span><i class="fa fa-clock-o"></i><span>5 january 16</span></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="media-object">
                                    <div class="media-object-section">
                                        <div class="recent-img">
                                            <img src="static/picture/recent3.png" alt="recent">
                                            <a href="#" class="hover-posts">
                                                <span><i class="fa fa-play"></i></span>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="media-object-section">
                                        <div class="media-content">
                                            <h6><a href="#">The lorem Ipsumbeen the industry's standard.</a></h6>
                                            <p><i class="fa fa-user"></i><span>admin</span><i class="fa fa-clock-o"></i><span>5 january 16</span></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="large-3 medium-6 columns">
                        <div class="widgetBox">
                            <div class="widgetTitle">
                                <h5>Tags</h5>
                            </div>
                            <div class="tagcloud" >
                                <a href="#">3D Videos</a>
                                <a href="#">Videos</a>
                                <a href="#">HD</a>
                                <a href="#">Movies</a>
                                <a href="#">Sports</a>
                                <a href="#">3D</a>
                                <a href="#">Movies</a>
                                <a href="#">Animation</a>
                                <a href="#">HD</a>
                                <a href="#">Music</a>
                                <a href="#">Recreation</a>
                            </div>
                        </div>
                    </div>
                    <div class="large-3 medium-6 columns">
                        <div class="widgetBox">
                            <div class="widgetTitle">
                                <h5>Subscribe Now</h5>
                            </div>
                            <div class="widgetContent">
                                <form data-abide="" novalidate="" method="post">
                                    <p>Subscribe to get exclusive videos</p>
                                    <div class="input">
                                        <input type="text" placeholder="Enter your full Name" required="">
                                        <span class="form-error">
                                            Yo, you had better fill this out, it's required.
                                        </span>
                                    </div>
                                    <div class="input">
                                        <input type="email" id="email" placeholder="Enter your email addres" required="">
                                        <span class="form-error">
                                          I'm required!
                                        </span>
                                    </div>
                                    <button class="button" type="submit" value="Submit">Sign up Now</button>
                                </form>
                                <div class="social-links">
                                    <h5>We’re a Social Bunch</h5>
                                    <a class="secondary-button" href="#"><i class="fa fa-facebook"></i></a>
                                    <a class="secondary-button" href="#"><i class="fa fa-twitter"></i></a>
                                    <a class="secondary-button" href="#"><i class="fa fa-google-plus"></i></a>
                                    <a class="secondary-button" href="#"><i class="fa fa-instagram"></i></a>
                                    <a class="secondary-button" href="#"><i class="fa fa-vimeo"></i></a>
                                    <a class="secondary-button" href="#"><i class="fa fa-youtube"></i></a>
                                    <a class="secondary-button" href="#"><i class="fa fa-flickr"></i></a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <a href="#" id="back-to-top" title="Back to top"><i class="fa fa-angle-double-up"></i></a>
            </footer><!-- footer -->
            <div id="footer-bottom">
                <div class="logo text-center">
                    <img src="static/picture/footerlogo.png" alt="footer logo">
                </div>
                <div class="btm-footer-text text-center">
                    <p>Copyright &copy; 2022.Company name All rights reserved.<a target="_blank" href="https://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
                </div>
            </div>
        </div><!--end off canvas content-->
    </div><!--end off canvas wrapper inner-->
</div><!--end off canvas wrapper-->
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import video from '@/api/front/video'
import category from '@/api/front/category'
export default {
//import 引入的组件需要注入到对象中才能使用
components: {},
props: {},
data() {
    //这里存放数据
    return {
        dataList:[],
        categoryList:[],
        page:1,//当前页
        limit:12,//每页记录数
        total:0,//总记录数
        videoQuery:{
            categoryId:''
        },//条件封装对象
        focus:0,
    }   
},
//计算属性类似于data 概念
computed: {},
//监控data 中的数据变化
watch: {},
//方法集合
methods: {
     //得到所有的视频
     getList(page=1) {
        console.log(11);
        this.page = page
        video.getListPage(this.page,this.limit,this.videoQuery)
            .then(response =>{//请求成功
                this.dataList = response.data.data.records
                this.total = response.data.data.total
            })
    },
    //得到所有的分类
    getAllCategory(){
        category.getAllCategory().then(response=>{
        this.categoryList = response.data.data
      })
    },
    click(index){
        this.focus = index;
    }
},
//生命周期- 创建完成（可以访问当前this 实例）
created() {

    this.getList()
    this.getAllCategory()
},
//生命周期- 挂载完成（可以访问DOM 元素）
mounted() {

},
beforeCreate() {}, //生命周期- 创建之前
beforeMount() {}, //生命周期- 挂载之前
beforeUpdate() {}, //生命周期- 更新之前
updated() {}, //生命周期- 更新之后
beforeDestroy() {}, //生命周期- 销毁之前
destroyed() {}, //生命周期- 销毁完成
activated() {}, //如果页面有keep-alive 缓存功能，这个函数会触发
}
</script>
<style lang='scss' scoped>
//@import url(); 引入公共css 类
@import '../../../assets/css/app.css';
@import '../../../assets/css/theme.css';
@import '../../../assets/css/font-awesome.min.css';
//@import '../../../assets/css/css.css';
//@import '../../../assets/css/layerslider.css';
@import '../../../assets/css/owl.carousel.min.css';
</style>