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
            <!--breadcrumbs-->
            <section id="breadcrumb">
                <div class="row">
                    <div class="large-12 columns">
                        <nav aria-label="You are here:" role="navigation">
                            <ul class="breadcrumbs">
                                <li><i class="fa fa-home"></i><a href="home-v1.html">Home</a></li>
                                <li><a href="#">Animation</a></li>
                                <li class="disabled">Gene Splicing</li>
                                <li>
                                    <span class="show-for-sr">Current: </span> Comedy video
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </section><!--end breadcrumbs-->
            <!-- full width Video -->
            <section class="fullwidth-single-video">
                <div class="row">
                    <div class="large-12 columns">
                        <div class="flex-video widescreen">
                            <iframe width="420" height="315" src="javascript:;" allowfullscreen=""></iframe>
                        </div>
                    </div>
                </div>
            </section>
            <div class="row">
                <!-- left side content area -->
                <div class="large-8 columns">
                    <!-- single post stats -->
                    <section class="SinglePostStats">
                        <!-- newest video -->
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="media-object stack-for-small">
                                    <div class="media-object-section">
                                        <div class="author-img-sec">
                                            <div class="thumbnail author-single-post">
                                                <a href="#"><img src="static/picture/post-author-post.png" alt="post"></a>
                                            </div>
                                            <p class="text-center"><a href="#">Joseph John</a></p>
                                        </div>
                                    </div>
                                    <div class="media-object-section object-second">
                                        <div class="author-des clearfix">
                                            <div class="post-title">
                                                <h4>There are many variations of passage.</h4>
                                                <p>
                                                    <span><i class="fa fa-clock-o"></i>5 January 16</span>
                                                    <span><i class="fa fa-eye"></i>1,862K</span>
                                                    <span><i class="fa fa-thumbs-o-up"></i>1,862</span>
                                                    <span><i class="fa fa-thumbs-o-down"></i>180</span>
                                                    <span><i class="fa fa-commenting"></i>8</span>
                                                </p>
                                            </div>
                                            <div class="subscribe">
                                                <form method="post">
                                                    <button type="submit" name="subscribe">Subscribe</button>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="social-share">
                                            <div class="post-like-btn clearfix">
                                                <form method="post">
                                                    <button type="submit" name="fav"><i class="fa fa-heart"></i>Add to</button>
                                                </form>
                                                <a href="#" class="secondary-button"><i class="fa fa-thumbs-o-up"></i></a>
                                                <a href="#" class="secondary-button"><i class="fa fa-thumbs-o-down"></i></a>

                                                <div class="float-right easy-share" data-easyshare="" data-easyshare-http="" data-easyshare-url="http://joinwebs.com">
                                                    <!-- Total -->
                                                    <button data-easyshare-button="total">
                                                        <span>Total</span>
                                                    </button>
                                                    <span data-easyshare-total-count="">0</span>

                                                    <!-- Facebook -->
                                                    <button data-easyshare-button="facebook">
                                                        <span class="fa fa-facebook"></span>
                                                        <span>Share</span>
                                                    </button>
                                                    <span data-easyshare-button-count="facebook">0</span>

                                                    <!-- Twitter -->
                                                    <button data-easyshare-button="twitter" data-easyshare-tweet-text="">
                                                        <span class="fa fa-twitter"></span>
                                                        <span>Tweet</span>
                                                    </button>
                                                    <span data-easyshare-button-count="twitter">0</span>

                                                    <!-- Google+ -->
                                                    <button data-easyshare-button="google">
                                                        <span class="fa fa-google-plus"></span>
                                                        <span>+1</span>
                                                    </button>
                                                    <span data-easyshare-button-count="google">0</span>

                                                    <div data-easyshare-loader="">Loading...</div>
                                                </div>
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
                                    <h5>Description</h5>
                                </div>
                                <div class="description showmore_one">
                                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>

                                    <p>Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur</p>
                                    <h6>Bullets List :</h6>
                                    <ul>
                                        <li>Sed ut perspiciatis unde omnis</li>
                                        <li>But I must explain to you how</li>
                                        <li>At vero eos et accusamus et iusto</li>
                                        <li>On the other hand, we denounce</li>
                                        <li>There are many variations of passages</li>
                                    </ul>
                                    <div class="categories">
                                        <button><i class="fa fa-folder"></i>Categories</button>
                                        <a href="#" class="inner-btn">entertainment</a>
                                        <a href="#" class="inner-btn">comedy</a>
                                    </div>
                                    <div class="tags">
                                        <button><i class="fa fa-tags"></i>Tags</button>
                                        <a href="#" class="inner-btn">3D Videos</a>
                                        <a href="#" class="inner-btn">Videos</a>
                                        <a href="#" class="inner-btn">HD</a>
                                        <a href="#" class="inner-btn">Movies</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section><!-- End single post description -->

                    <!-- related Posts -->
                    <section class="content content-with-sidebar related">
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="main-heading borderBottom">
                                    <div class="row padding-14">
                                        <div class="medium-12 small-12 columns">
                                            <div class="head-title">
                                                <i class="fa fa-film"></i>
                                                <h4>Related Videos</h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row list-group">
                                    <div class="item large-4 columns end group-item-grid-default">
                                        <div class="post thumb-border">
                                            <div class="post-thumb">
                                                <img src="static/picture/landing-small1.png" alt="landing">
                                                <a href="#" class="hover-posts">
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
                                                <h6><a href="#">There are many variations of passage.</a></h6>
                                                <div class="post-stats clearfix">
                                                    <p class="pull-left">
                                                        <i class="fa fa-user"></i>
                                                        <span><a href="#">admin</a></span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-clock-o"></i>
                                                        <span>5 January 16</span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-eye"></i>
                                                        <span>1,862K</span>
                                                    </p>
                                                </div>
                                                <div class="post-summary">
                                                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
                                                </div>
                                                <div class="post-button">
                                                    <a href="#" class="secondary-button"><i class="fa fa-play-circle"></i>watch video</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item large-4 columns end group-item-grid-default">
                                        <div class="post thumb-border">
                                            <div class="post-thumb">
                                                <img src="static/picture/landing-small2.png" alt="landing">
                                                <a href="#" class="hover-posts">
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
                                                <h6><a href="#">There are many variations of passage.</a></h6>
                                                <div class="post-stats clearfix">
                                                    <p class="pull-left">
                                                        <i class="fa fa-user"></i>
                                                        <span><a href="#">admin</a></span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-clock-o"></i>
                                                        <span>5 January 16</span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-eye"></i>
                                                        <span>1,862K</span>
                                                    </p>
                                                </div>
                                                <div class="post-summary">
                                                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
                                                </div>
                                                <div class="post-button">
                                                    <a href="#" class="secondary-button"><i class="fa fa-play-circle"></i>watch video</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item large-4 columns end group-item-grid-default">
                                        <div class="post thumb-border">
                                            <div class="post-thumb">
                                                <img src="static/picture/landing-small3.png" alt="landing">
                                                <a href="#" class="hover-posts">
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
                                                <h6><a href="#">There are many variations of passage.</a></h6>
                                                <div class="post-stats clearfix">
                                                    <p class="pull-left">
                                                        <i class="fa fa-user"></i>
                                                        <span><a href="#">admin</a></span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-clock-o"></i>
                                                        <span>5 January 16</span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-eye"></i>
                                                        <span>1,862K</span>
                                                    </p>
                                                </div>
                                                <div class="post-summary">
                                                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
                                                </div>
                                                <div class="post-button">
                                                    <a href="#" class="secondary-button"><i class="fa fa-play-circle"></i>watch video</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item large-4 columns end group-item-grid-default">
                                        <div class="post thumb-border">
                                            <div class="post-thumb">
                                                <img src="static/picture/widget-most2.png" alt="landing">
                                                <a href="#" class="hover-posts">
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
                                                <h6><a href="#">There are many variations of passage.</a></h6>
                                                <div class="post-stats clearfix">
                                                    <p class="pull-left">
                                                        <i class="fa fa-user"></i>
                                                        <span><a href="#">admin</a></span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-clock-o"></i>
                                                        <span>5 January 16</span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-eye"></i>
                                                        <span>1,862K</span>
                                                    </p>
                                                </div>
                                                <div class="post-summary">
                                                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
                                                </div>
                                                <div class="post-button">
                                                    <a href="#" class="secondary-button"><i class="fa fa-play-circle"></i>watch video</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item large-4 columns end group-item-grid-default">
                                        <div class="post thumb-border">
                                            <div class="post-thumb">
                                                <img src="static/picture/slider-carousel4.png" alt="landing">
                                                <a href="#" class="hover-posts">
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
                                                <h6><a href="#">There are many variations of passage.</a></h6>
                                                <div class="post-stats clearfix">
                                                    <p class="pull-left">
                                                        <i class="fa fa-user"></i>
                                                        <span><a href="#">admin</a></span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-clock-o"></i>
                                                        <span>5 January 16</span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-eye"></i>
                                                        <span>1,862K</span>
                                                    </p>
                                                </div>
                                                <div class="post-summary">
                                                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
                                                </div>
                                                <div class="post-button">
                                                    <a href="#" class="secondary-button"><i class="fa fa-play-circle"></i>watch video</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item large-4 columns end group-item-grid-default">
                                        <div class="post thumb-border">
                                            <div class="post-thumb">
                                                <img src="static/picture/1.jpg" alt="landing">
                                                <a href="#" class="hover-posts">
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
                                                <h6><a href="#">There are many variations of passage.</a></h6>
                                                <div class="post-stats clearfix">
                                                    <p class="pull-left">
                                                        <i class="fa fa-user"></i>
                                                        <span><a href="#">admin</a></span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-clock-o"></i>
                                                        <span>5 January 16</span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-eye"></i>
                                                        <span>1,862K</span>
                                                    </p>
                                                </div>
                                                <div class="post-summary">
                                                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
                                                </div>
                                                <div class="post-button">
                                                    <a href="#" class="secondary-button"><i class="fa fa-play-circle"></i>watch video</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section><!--end related posts-->
                    <!-- Comments -->
                    <section class="content comments">
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="main-heading borderBottom">
                                    <div class="row padding-14">
                                        <div class="medium-12 small-12 columns">
                                            <div class="head-title">
                                                <i class="fa fa-comments"></i>
                                                <h4>Comments <span>(4)</span></h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="comment-box thumb-border">
                                    <div class="media-object stack-for-small">
                                        <div class="media-object-section comment-img text-center">
                                            <div class="comment-box-img">
                                                <img src="static/picture/post-author-post.png" alt="comment">
                                            </div>
                                            <h6><a href="#">Joseph John</a></h6>
                                        </div>
                                        <div class="media-object-section comment-textarea">
                                            <form method="post">
                                                <textarea name="commentText" placeholder="Add a comment here.."></textarea>
                                                <input type="submit" name="submit" value="send">
                                            </form>
                                        </div>
                                    </div>
                                </div>

                                <div class="comment-sort text-right">
                                    <span>Sort By : <a href="#">newest</a> | <a href="#">oldest</a></span>
                                </div>

                                <!-- main comment -->
                                <div class="main-comment showmore_one">
                                    <div class="media-object stack-for-small">
                                        <div class="media-object-section comment-img text-center">
                                            <div class="comment-box-img">
                                                <img src="static/picture/post-author-post.png" alt="comment">
                                            </div>
                                        </div>
                                        <div class="media-object-section comment-desc">
                                            <div class="comment-title">
                                                <span class="name"><a href="#">Joseph John</a> Said:</span>
                                                <span class="time float-right"><i class="fa fa-clock-o"></i>1 minute ago</span>
                                            </div>
                                            <div class="comment-text">
                                                <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventoresunt explicabo.</p>
                                            </div>
                                            <div class="comment-btns">
                                                <span><a href="#"><i class="fa fa-thumbs-o-up"></i></a> | <a href="#"><i class="fa fa-thumbs-o-down"></i></a></span>
                                                <span><a href="#"><i class="fa fa-share"></i>Reply</a></span>
                                                <span class='reply float-right hide-reply'></span>
                                            </div>

                                            <!--sub comment-->
                                            <div class="media-object stack-for-small reply-comment">
                                                <div class="media-object-section comment-img text-center">
                                                    <div class="comment-box-img">
                                                        <img src="static/picture/post-author-post.png" alt="comment">
                                                    </div>
                                                </div>
                                                <div class="media-object-section comment-desc">
                                                    <div class="comment-title">
                                                        <span class="name"><a href="#">Joseph John</a> Said:</span>
                                                        <span class="time float-right"><i class="fa fa-clock-o"></i>1 minute ago</span>
                                                    </div>
                                                    <div class="comment-text">
                                                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventoresunt explicabo.</p>
                                                    </div>
                                                    <div class="comment-btns">
                                                        <span><a href="#"><i class="fa fa-thumbs-o-up"></i></a> | <a href="#"><i class="fa fa-thumbs-o-down"></i></a></span>
                                                        <span><a href="#"><i class="fa fa-share"></i>Reply</a></span>
                                                        <span class='reply float-right hide-reply'></span>
                                                    </div>
                                                </div>
                                            </div><!-- end sub comment -->

                                            <!--sub comment-->
                                            <div class="media-object stack-for-small reply-comment">
                                                <div class="media-object-section comment-img text-center">
                                                    <div class="comment-box-img">
                                                        <img src="static/picture/post-author-post.png" alt="comment">
                                                    </div>
                                                </div>
                                                <div class="media-object-section comment-desc">
                                                    <div class="comment-title">
                                                        <span class="name"><a href="#">Joseph John</a> Said:</span>
                                                        <span class="time float-right"><i class="fa fa-clock-o"></i>1 minute ago</span>
                                                    </div>
                                                    <div class="comment-text">
                                                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventoresunt explicabo.</p>
                                                    </div>
                                                    <div class="comment-btns">
                                                        <span><a href="#"><i class="fa fa-thumbs-o-up"></i></a> | <a href="#"><i class="fa fa-thumbs-o-down"></i></a></span>
                                                        <span><a href="#"><i class="fa fa-share"></i>Reply</a></span>
                                                        <span class='reply float-right hide-reply'></span>
                                                    </div>

                                                </div>
                                            </div><!-- end sub comment -->

                                        </div>
                                    </div>

                                    <div class="media-object stack-for-small">
                                        <div class="media-object-section comment-img text-center">
                                            <div class="comment-box-img">
                                                <img src="static/picture/post-author-post.png" alt="comment">
                                            </div>
                                        </div>
                                        <div class="media-object-section comment-desc">
                                            <div class="comment-title">
                                                <span class="name"><a href="#">Joseph John</a> Said:</span>
                                                <span class="time float-right"><i class="fa fa-clock-o"></i>1 minute ago</span>
                                            </div>
                                            <div class="comment-text">
                                                <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventoresunt explicabo.</p>
                                            </div>
                                            <div class="comment-btns">
                                                <span><a href="#"><i class="fa fa-thumbs-o-up"></i></a> | <a href="#"><i class="fa fa-thumbs-o-down"></i></a></span>
                                                <span><a href="#"><i class="fa fa-share"></i>Reply</a></span>
                                                <span class='reply float-right hide-reply'></span>
                                            </div>

                                        </div>
                                    </div>

                                    <div class="media-object stack-for-small">
                                        <div class="media-object-section comment-img text-center">
                                            <div class="comment-box-img">
                                                <img src="static/picture/post-author-post.png" alt="comment">
                                            </div>
                                        </div>
                                        <div class="media-object-section comment-desc">
                                            <div class="comment-title">
                                                <span class="name"><a href="#">Joseph John</a> Said:</span>
                                                <span class="time float-right"><i class="fa fa-clock-o"></i>1 minute ago</span>
                                            </div>
                                            <div class="comment-text">
                                                <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventoresunt explicabo.</p>
                                            </div>
                                            <div class="comment-btns">
                                                <span><a href="#"><i class="fa fa-thumbs-o-up"></i></a> | <a href="#"><i class="fa fa-thumbs-o-down"></i></a></span>
                                                <span><a href="#"><i class="fa fa-share"></i>Reply</a></span>
                                                <span class='reply float-right hide-reply'></span>
                                            </div>
                                            <!--sub comment-->
                                            <div class="media-object stack-for-small reply-comment">
                                                <div class="media-object-section comment-img text-center">
                                                    <div class="comment-box-img">
                                                        <img src="static/picture/post-author-post.png" alt="comment">
                                                    </div>
                                                </div>
                                                <div class="media-object-section comment-desc">
                                                    <div class="comment-title">
                                                        <span class="name"><a href="#">Joseph John</a> Said:</span>
                                                        <span class="time float-right"><i class="fa fa-clock-o"></i>1 minute ago</span>
                                                    </div>
                                                    <div class="comment-text">
                                                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventoresunt explicabo.</p>
                                                    </div>
                                                    <div class="comment-btns">
                                                        <span><a href="#"><i class="fa fa-thumbs-o-up"></i></a> | <a href="#"><i class="fa fa-thumbs-o-down"></i></a></span>
                                                        <span><a href="#"><i class="fa fa-share"></i>Reply</a></span>
                                                        <span class='reply float-right hide-reply'></span>
                                                    </div>
                                                    <!--sub comment-->
                                                    <div class="media-object stack-for-small reply-comment">
                                                        <div class="media-object-section comment-img text-center">
                                                            <div class="comment-box-img">
                                                                <img src="static/picture/post-author-post.png" alt="comment">
                                                            </div>
                                                        </div>
                                                        <div class="media-object-section comment-desc">
                                                            <div class="comment-title">
                                                                <span class="name"><a href="#">Joseph John</a> Said:</span>
                                                                <span class="time float-right"><i class="fa fa-clock-o"></i>1 minute ago</span>
                                                            </div>
                                                            <div class="comment-text">
                                                                <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventoresunt explicabo.</p>
                                                            </div>
                                                            <div class="comment-btns">
                                                                <span><a href="#"><i class="fa fa-thumbs-o-up"></i></a> | <a href="#"><i class="fa fa-thumbs-o-down"></i></a></span>
                                                                <span><a href="#"><i class="fa fa-share"></i>Reply</a></span>
                                                                <span class='reply float-right hide-reply'></span>
                                                            </div>
                                                        </div>
                                                    </div><!-- end sub comment -->
                                                </div>
                                            </div><!-- end sub comment -->
                                        </div>
                                    </div>
                                </div><!-- End main comment -->

                            </div>
                        </div>
                    </section><!-- End Comments -->
                </div><!-- end left side content area -->
                <!-- sidebar -->
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

                            <!-- most view Widget -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>Most View Videos</h5>
                                    </div>
                                    <div class="widgetContent">
                                        <div class="video-box thumb-border">
                                            <div class="video-img-thumb">
                                                <img src="static/picture/7.jpg" alt="most viewed videos">
                                                <a href="#" class="hover-posts">
                                                    <span><i class="fa fa-play"></i>Watch Video</span>
                                                </a>
                                            </div>
                                            <div class="video-box-content">
                                                <h6><a href="#">There are many variations of passage. </a></h6>
                                                <p>
                                                    <span><i class="fa fa-user"></i><a href="#">admin</a></span>
                                                    <span><i class="fa fa-clock-o"></i>5 January 16</span>
                                                    <span><i class="fa fa-eye"></i>1,862K</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="video-box thumb-border">
                                            <div class="video-img-thumb">
                                                <img src="static/picture/widget-most1.png" alt="most viewed videos">
                                                <a href="#" class="hover-posts">
                                                    <span><i class="fa fa-play"></i>Watch Video</span>
                                                </a>
                                            </div>
                                            <div class="video-box-content">
                                                <h6><a href="#">There are many variations of passage. </a></h6>
                                                <p>
                                                    <span><i class="fa fa-user"></i><a href="#">admin</a></span>
                                                    <span><i class="fa fa-clock-o"></i>5 January 16</span>
                                                    <span><i class="fa fa-eye"></i>1,862K</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="video-box thumb-border">
                                            <div class="video-img-thumb">
                                                <img src="static/picture/widget-most2.png" alt="most viewed videos">
                                                <a href="#" class="hover-posts">
                                                    <span><i class="fa fa-play"></i>Watch Video</span>
                                                </a>
                                            </div>
                                            <div class="video-box-content">
                                                <h6><a href="#">There are many variations of passage. </a></h6>
                                                <p>
                                                    <span><i class="fa fa-user"></i><a href="#">admin</a></span>
                                                    <span><i class="fa fa-clock-o"></i>5 January 16</span>
                                                    <span><i class="fa fa-eye"></i>1,862K</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="video-box thumb-border">
                                            <div class="video-img-thumb">
                                                <img src="static/picture/widget-most3.png" alt="most viewed videos">
                                                <a href="#" class="hover-posts">
                                                    <span><i class="fa fa-play"></i>Watch Video</span>
                                                </a>
                                            </div>
                                            <div class="video-box-content">
                                                <h6><a href="#">There are many variations of passage. </a></h6>
                                                <p>
                                                    <span><i class="fa fa-user"></i><a href="#">admin</a></span>
                                                    <span><i class="fa fa-clock-o"></i>5 January 16</span>
                                                    <span><i class="fa fa-eye"></i>1,862K</span>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- end most view Widget -->

                            <!-- categories -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox clearfix">
                                    <div class="widgetTitle">
                                        <h5>Categories</h5>
                                    </div>
                                    <div class="widgetContent clearfix">
                                        <ul>
                                            <li class="cat-item"><a href="#">Entertainment &nbsp; (6)</a></li>
                                            <li class="cat-item"><a href="#">Historical &amp; Archival &nbsp;(8)</a></li>
                                            <li class="cat-item"><a href="#">Technology&nbsp;(4)</a></li>
                                            <li class="cat-item"><a href="#">People&nbsp;(3)</a></li>
                                            <li class="cat-item"><a href="#">Fashion &amp; Beauty&nbsp;(2)</a></li>
                                            <li class="cat-item"><a href="#">Nature&nbsp;(1)</a></li>
                                            <li class="cat-item"><a href="#">Automotive&nbsp;(5)</a></li>
                                            <li class="cat-item"><a href="">Foods &amp; Drinks&nbsp;(5)</a></li>
                                            <li class="cat-item"><a href="#">Foods &amp; Drinks&nbsp;(10)</a></li>
                                            <li class="cat-item"><a href="#">Animals&nbsp;(12)</a></li>
                                            <li class="cat-item"><a href="#">Sports &amp; Recreation&nbsp;(14)</a></li>
                                            <li class="cat-item"><a href="">Places &amp; Landmarks&nbsp;(16)</a></li>
                                            <li class="cat-item"><a href="">Places &amp; Landmarks&nbsp;(1)</a></li>
                                            <li class="cat-item"><a href="#">Travel&nbsp;(2)</a></li>
                                            <li class="cat-item"><a href="#">Transportation&nbsp;(3)</a></li>
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

                            <!-- ad banner widget -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>Recent post videos</h5>
                                    </div>
                                    <div class="widgetContent">
                                        <div class="advBanner text-center">
                                            <a href="#"><img src="static/picture/sideradv.png" alt="sidebar adv"></a>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- end ad banner widget -->

                            <!-- Recent post videos -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>Recent post videos</h5>
                                    </div>
                                    <div class="widgetContent">
                                        <div class="media-object stack-for-small">
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
                                        <div class="media-object stack-for-small">
                                            <div class="media-object-section">
                                                <div class="recent-img">
                                                    <img src="static/picture/category2.png" alt="recent">
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
                                        <div class="media-object stack-for-small">
                                            <div class="media-object-section">
                                                <div class="recent-img">
                                                    <img src="static/picture/sidebar-recent1.png" alt="recent">
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
                                        <div class="media-object stack-for-small">
                                            <div class="media-object-section">
                                                <div class="recent-img">
                                                    <img src="static/picture/sidebar-recent2.png" alt="recent">
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
                            </div><!-- End tags -->
                        </div>
                    </aside>
                </div><!-- end sidebar -->
            </div>

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
                            <div class="tagcloud">
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
                                        <input type="email" id="email" placeholder="Enter your email address" required="">
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

export default {
//import 引入的组件需要注入到对象中才能使用
components: {},
props: {},
data() {
//这里存放数据
return {

};
},
//计算属性类似于data 概念
computed: {},
//监控data 中的数据变化
watch: {},
//方法集合
methods: {

},
//生命周期- 创建完成（可以访问当前this 实例）
created() {

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
@import '../../../assets/css/css.css';
@import '../../../assets/css/layerslider.css';
@import '../../../assets/css/owl.carousel.min.css';
@import '../../../assets/css/owl.theme.default.min.css';
//@import '../../../assets/css/jquery.kyco.easyshare.css';
@import '../../../assets/css/responsive.css';
</style>