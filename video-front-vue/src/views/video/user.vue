<template>
    <div>
        <!-- profile top section -->
        <section class="topProfile">
            <div class="main-text text-center">
                <div class="row">
                    <div class="large-12 columns">
                        <h3>World’s Biggest</h3>
                        <h1>Powerfull Video Theme</h1>
                    </div>
                </div>
            </div>
            <div class="profile-stats">
                <div class="row secBg">
                    <div class="large-12 columns">
                        <div class="profile-author-img">
                            <img :src="userInfo.avatar" alt="profile author img">
                        </div>
                        <div class="profile-share">
                            <div class="easy-share" data-easyshare="" data-easyshare-http=""
                                data-easyshare-url="http://joinwebs.com">
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
                        <div class="clearfix">
                            <div class="profile-author-name float-left">
                                <h4>{{ userInfo.username }}<el-button v-if="isGuest" type="danger" round
                                        @click="letter(userInfo.id)">私信</el-button></h4>
                                <p>加入日期 : <span>{{ userInfo.gmtCreate }}</span></p>
                            </div>

                            <div class="profile-author-stats float-right">
                                <ul class="menu">
                                    <li>
                                        <div class="icon float-left">
                                            <i class="fa fa-video-camera"></i>
                                        </div>
                                        <div class="li-text float-left">
                                            <p class="number-text">{{ userInfo.addVideoCount }}</p>
                                            <span>Videos</span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="icon float-left">
                                            <i class="fa fa-heart"></i>
                                        </div>
                                        <div class="li-text float-left">
                                            <p class="number-text">{{ userInfo.collectionVideoCount }}</p>
                                            <span>favorites</span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="icon float-left">
                                            <i class="fa fa-users"></i>
                                        </div>
                                        <div class="li-text float-left">
                                            <p class="number-text">{{ userInfo.followCount }}</p>
                                            <span>followers</span>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="icon float-left">
                                            <i class="fa fa-users"></i>
                                        </div>
                                        <div class="li-text float-left">
                                            <p class="number-text">{{ userInfo.fanCount }}</p>
                                            <span>fans</span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section><!-- End profile top section -->
        <div class="row">
            <!-- left sidebar -->
            <div class="large-3 columns">
                <aside class="secBg sidebar">
                    <div class="row">
                        <!-- profile overview -->
                        <div class="large-12 columns">
                            <div class="widgetBox">
                                <div class="widgetTitle">
                                    <h5>个人中心</h5>
                                </div>
                                <div class="widgetContent">
                                    <ul class="profile-overview">
                                        <li v-if='isAdmin || isGuest' class="clearfix"><a :class="item == '1' ? 'active' : ''"
                                                :href="(`/dashboard/user/1/aboutme?userId=${userInfo.id}`)"><i
                                                    class="el-icon-user"></i>关于自己</a></li>
                                        <li v-if='isAdmin || isGuest' class="clearfix"><a :class="item == '2' ? 'active' : ''"
                                                :href="(`/dashboard/user/2/videos?userId=${userInfo.id}`)"><i
                                                    class="el-icon-video-camera"></i>上传的视频<span
                                                    class="float-right">{{ userInfo.addVideoCount }}</span></a></li>
                                        <li v-if='isAdmin' class="clearfix"><a :class="item == '3' ? 'active' : ''"
                                                href="/dashboard/user/3/browseRecords"><i
                                                    class="el-icon-time"></i>浏览记录</a></li>
                                        <li v-if='isAdmin || isGuest' class="clearfix"><a :class="item == '4' ? 'active' : ''"
                                                :href="(`/dashboard/user/4/collections?userId=${userInfo.id}`)"><i
                                                    class="el-icon-folder"></i>收藏的视频<span
                                                    class="float-right">{{ userInfo.collectionVideoCount }}</span></a>
                                        </li>
                                        <li v-if='isAdmin || isGuest' class="clearfix"><a :class="item == '5' ? 'active' : ''"
                                                :href="(`/dashboard/user/5/follows?userId=${userInfo.id}`)"><i
                                                    class="el-icon-user"></i>关注用户<span
                                                    class="float-right">{{ userInfo.followCount }}</span></a></li>
                                        <li v-if='isAdmin || isGuest' class="clearfix"><a :class="item == '6' ? 'active' : ''"
                                                :href="(`/dashboard/user/6/fans?userId=${userInfo.id}`)"><i
                                                    class="el-icon-user"></i>粉丝<span
                                                    class="float-right">{{ userInfo.fanCount }}</span></a></li>
                                        <li v-if='isAdmin' class="clearfix"><a :class="item == '7' ? 'active' : ''"
                                                href="/dashboard/user/7/comments"><i
                                                    class="fa fa-comments-o"></i>回复我的<span
                                                    class="float-right">{{ userInfo.noReplyCommentCount }}</span></a></li>
                                        <li v-if='isAdmin' class="clearfix"><a :class="item == '8' ? 'active' : ''"
                                                href="/dashboard/user/8/privateLetter"><i
                                                    class="el-icon-chat-line-round"></i>私信<span
                                                    class="float-right">{{ userInfo.noReplyLetterCount }}</span></a></li>
                                        <li v-if='isAdmin' class="clearfix"><a :class="item == '9' ? 'active' : ''"
                                                href="/dashboard/user/9/setting"><i class="fa fa-gears"></i>个人设置</a>
                                        </li>
                                    </ul>
                                    <a href="/dashboard/user/11/addVideo" class="button"><i
                                            class="fa fa-plus-circle"></i>上传视频</a>
                                </div>
                            </div>
                        </div><!-- End profile overview -->
                    </div>
                </aside>
            </div><!-- end sidebar -->
            <!-- right side content area -->
            <router-view></router-view>
        </div>

    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import user from "@/api/user"
import message from '@/api/message'
export default {
    data() {
        //这里存放数据
        return {
            userInfo: {},
            item: 1,
            isAdmin: false,
            isGuest: false,
            guest: {},
        };
    },
    //方法集合
    methods: {
        //给某人私信
        letter(toUserId) {
            var userMessageCount = {}
            userMessageCount.currentUserId = toUserId
            this.userInfo = JSON.parse(this.$store.getters.getUser)
            userMessageCount.fromUserId = this.userInfo.id
            message.addUserMessageCount(userMessageCount).then(response=>{
                this.isAdmin = true
                this.isGuest = false
                window.location.href = '/dashboard/user/8/privateLetter?toUserId='+toUserId
                //this.$router.push({ path: '/dashboard/user/8/privateLetter', query: { toUserId: toUserId } });
            })
        },
        getUserInfo(userId) {
            user.getUserInfoByUserId(userId).then(response => {
                this.userInfo = response.data.data
            })
        },
        getUserNoReplyCount(userId) {
            setInterval(e=>{
                    message.getUserNoReplyCount(userId).then(response => {
                    var result = response.data.data
                    this.count = result[0] + result[1]
                    this.userInfo.noReplyCommentCount = result[0]
                    this.userInfo.noReplyLetterCount = result[1]
              })
            },2000)
        },
    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$store.getters.getUser) {
            var user = JSON.parse(this.$store.getters.getUser)
            this.item = this.$route.params.item
           
            if (this.$route.query.userId) {
                if (this.$route.query.userId == user.id) {
                    this.isAdmin = true;
                    this.getUserInfo(user.id)
                    this.getUserNoReplyCount(user.id)
                } else {
                    //查询当前用户信息
                    this.getUserInfo(this.$route.query.userId)
                    this.isGuest = true;
                }
            } else {
                this.$nextTick(e=>{
                
                    this.isAdmin = true;
                    this.getUserInfo(user.id)
                    this.getUserNoReplyCount(user.id)
                })
            }

        }

    },
    
}
</script>
<style>

</style>
