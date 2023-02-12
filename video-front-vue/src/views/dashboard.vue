<template>

  <div class="off-canvas-wrapper">
    <div class="off-canvas-wrapper-inner" data-off-canvas-wrapper="">
      <!--header-->

      <div class="off-canvas-content" data-off-canvas-content="">
        <header>
          <!--导航栏设计-->
          <section id="navBar">
            <nav class="sticky-container" data-sticky-container="">
              <div class="row">
                <div class="large-12 columns">
                  <div class="top-bar show-for-large w" id="beNav" style="width: 100%;">
                    <div class="logo">
                      <h1>
                        <a href="index.html" title="视频播放">视频播放</a>
                      </h1>
                    </div>

                    <div class="top-bar-left">
                      <ul>
                        <li>
                          <a href="/"><i class="fa fa-home"></i>&nbsp;主页</a>
                        </li>
                        <li>
                          <a href="#"><i class="fa fa-th"></i>&nbsp;分类</a>
                        </li>
                        <li>
                          <a href="#"><i class="fa fa-edit"></i>&nbsp;博客</a>
                        </li>
                      </ul>
                    </div>
                    <div class="search">
                      <input type="search" name="" id="" class="search-input" placeholder="语言开发"
                             v-model="text">
                      <button @click="search()">搜索</button>
                    </div>
                    <div class="top-right">
                      <div v-if="isTrue" class="login">
                        <a href="/login">
                          <Button class="a-button">登录</Button>
                        </a>
                      </div>
                      <div class="avatar" v-else>
                        <Avatar icon="ios-person" class="person-avatar" size="large"
                                :src="userInfo.avatar" />


                        <div class="person">
                          <div class="info">
                            <h5>{{ userInfo.username }}</h5>
                            <p v-if="userInfo.description == ''">此用户什么都没有写</p>
                            <p v-else>{{ userInfo.description }}</p>
                          </div>
                          <div class="fan">
                            <ul>
                              <li>
                                <h5>{{ userInfo.followCount }}</h5>
                                <p>关注</p>
                              </li>
                              <li>
                                <h5>{{ userInfo.fanCount }}</h5>
                                <p>粉丝</p>
                              </li>
                              <li>
                                <h5>{{ userInfo.addVideoCount }}</h5>
                                <p>动态</p>
                              </li>
                            </ul>
                          </div>
                          <div class="personal-center">
                            <a :href="'/dashboard/user/1/aboutme?userId=' + userInfo.id">
                              <Icon type="ios-contact" />&nbsp;个人中心
                            </a>
                          </div>
                          <hr />
                          <div class="lougout">
                            <a href="#" @click="loginOut">
                              <Icon type="ios-power" />&nbsp;退出登录
                            </a>
                          </div>
                        </div>
                      </div>
                      <div class="right-list">
                        <ul>
                          <li>
                            <a href="#" class="right-item">
                              <Icon type="md-text" />&nbsp;消息
                            </a>
                            <i class="count">{{ count }}</i>
                            <div class="md">
                              <ul>
                                <li><a
                                  href="/dashboard/user/7/comments">回复我的&nbsp;{{noReplyCommentCount }}</a>
                                </li>
                                <li><a
                                  href="/dashboard/user/8/privateLetter">私信消息&nbsp;{{noReplyLetterCount }}</a>
                                </li>
                              </ul>
                            </div>
                          </li>
                          <li> <a class="right-item"
                                  :href="(`/dashboard/user/2/videos?userId=${userInfo.id}`)">
                            <Icon type="ios-paper" />&nbsp;动态
                          </a></li>
                          <li> <a class="right-item"
                                  :href="(`/dashboard/user/4/collections?userId=${userInfo.id}`)">
                            <Icon type="ios-star" />&nbsp;收藏
                          </a></li>
                          <li> <a class="right-item" href="/dashboard/user/3/browseRecords">
                            <Icon type="ios-time" />&nbsp;历史
                          </a>

                          </li>
                        </ul>

                      </div>
                      <div class="upload-video">
                        <a href="/dashboard/user/11/addVideo">
                          <Button class="a-button">上传视频</Button>
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </nav>
          </section>
        </header>
        <!-- End Header -->
        <router-view></router-view>
        <!-- footer -->
        <video-footer></video-footer>
      </div><!--end off canvas content-->
    </div><!--end off canvas wrapper inner-->
  </div><!--end off canvas wrapper-->
</template>

<script>
  //这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
  //例如：import 《组件名称》from '《组件路径》';
  import user from "@/api/user";
  import message from "@/api/message";
  import videoFooter from "../components/videoFooter";

  export default {
    //import 引入的组件需要注入到对象中才能使用
    components: {
      videoFooter
    },
    data() {
      //这里存放数据
      return {
        flag: false,
        isTrue: true,
        userInfo: {},
        count: 0,
        text: "",
        noReplyCommentCount:0,
        noReplyLetterCount:0,
      }
    },
    //方法集合
    methods: {
      isLogin() {
        this.flag = !this.flag
      },

      getToken() {
        var url = window.document.location.href.toString();
        //http://localhost:7001/dashboard/index?
        //token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY5MjcwMjczLCJleHAiOjE2NjkzNTY2NzMsInVzZXJuYW1lIjoieHpqc2NjeiIsInBhc3N3b3JkIjoiMTIzNDUifQ.C-dlORhlLk5NER1ExHKobvTQt_wAqxJV_AjdQpbWTW0
        var strings = url.split("?");
        var str_token = strings[strings.length - 1].split("=");
        var token = str_token[1];

        var reg = /^[\d]+$/;
        if (!reg.test(token)) {
          if (token != undefined && token != {}) {
            //获取用户的基本信息
            user.getUserInfoByToken(token).then(response => {
              this.userInfo = response.data.data
              this.$store.commit('SET_USERINFO', JSON.stringify(this.userInfo))
              this.flag = false
              this.isTrue = false
            })
          }
        }
      },

      loginOut() {
        this.isTrue = true
        this.$store.commit('REMOVE_INFO')
        window.location.href = "/"
      },

      getUserNoReplyCount(userId) {
            setInterval(e=>{
                    message.getUserNoReplyCount(userId).then(response => {
                      var result = response.data.data
                      this.count = result[0] + result[1]
                      this.noReplyCommentCount = result[0]
                      this.noReplyLetterCount = result[1]
                    })
                    user.getUserInfoByUserId(userId).then(response=>{
                      this.userInfo = response.data.data
                    })
            },2000)
        },
      search() {
        if (this.text == '') {
          this.$Message.warning('请输入想要搜索的内容');
        } else {
          window.location.href = "/dashboard?content=" + this.text
        }
      },
    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
      this.getToken()
      if (this.$store.getters.getUser) {
        this.userInfo = JSON.parse(this.$store.getters.getUser)
        if (this.userInfo) {
          this.isTrue = false
          this.flag = false
          this.getUserNoReplyCount(this.userInfo.id)
        } else {
          this.isTrue = true
        }
      }
    },
    mounted(){
    },
    beforeDestroy () {
      this.onbeforeunload()
    },
  }
</script>
<style >
  @import '../assets/css/common.css';
  @import '../assets/css/app.css';
  @import '../assets/css/theme.css';
  @import '../assets/css/font-awesome.min.css';
  @import '../assets/css/owl.carousel.min.css';

  .a-button {
    background-color: #C1121C;
    width: 80px;
    height: 30px;
    border-radius: 6px;
    color: #fff;
  }

  .a-button:hover {
    background-color: #D47479;
  }
</style>
