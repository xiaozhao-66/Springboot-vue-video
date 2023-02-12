<template>
    <div>
        <videoHeader></videoHeader>
        <!-- registration -->
        <section class="registration">
            <div class="row secBg">
                <div class="large-12 columns">
                    <div class="login-register-content">
                        <div class="row collapse borderBottom">
                            <div class="medium-6 large-centered medium-centered">
                                <div class="page-heading text-center">
                                    <h3>登录</h3>
                                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem
                                        Ipsum has been the industry's standard dummy text ever since the 1500s</p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-4 medium-6 columns " style="margin-left:30%;">
                                <div class="register-form">
                                    <form data-abide="" novalidate="">
                                        <div data-abide-error="" class="alert callout" style="display: none;">
                                            <p><i class="fa fa-exclamation-triangle"></i> There are some errors in your
                                                form.</p>
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-label"><i class="fa fa-user"></i></span>
                                            <input class="input-group-field" type="text"
                                                placeholder="Enter your username" required=""
                                                v-model='userInfo.username'>
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-label"><i class="fa fa-lock"></i></span>
                                            <input type="password" id="password" placeholder="Enter your password"
                                                required="" v-model='userInfo.password'>
                                        </div>
                                        <el-row>
                                            <el-col :span="6">
                                                <a class="newaccount" @click="loginByGitee()"> <img
                                                        src="https://gitee.com/static/images/logo-black.svg?t=158106664"
                                                        alt="" style="position: relative;top: -2px;" class="mr-1" /></a>
                                            </el-col>
                                        </el-row>

                                        <button class="button expanded" type="button" name="submit"
                                            @click="login()">登录</button>
                                        <p class="loginclick"> <a href="/register">创建一个账户?</a></p>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <videoFooter></videoFooter>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import user from "@/api/user";
import videoHeader from "@/components/videoHeader";
import videoFooter from "@/components/videoFooter";
import Cookies from 'js-cookie';
export default {
    //import 引入的组件需要注入到对象中才能使用
    components: {
        videoHeader,
        videoFooter,
    },
    data() {
        //这里存放数据
        return {
            userInfo: {},
        };
    },
    //方法集合
    methods: {
        loginByGitee() {
            user.loginByGitee().then(res => {
                var url = res.data.data
                window.location.href = url
            })
        },
        //用户登录
        login() {
            user.login(this.userInfo).then(response => {
                //登录成功
                if (response.data.code == 0) {
                    Cookies.set('Jwt_token', response.data.data.Jwt_token)
                    //将用户信息也保存在cookies
                    this.userInfo = response.data.data.userInfo
                    this.$store.commit('SET_USERINFO', JSON.stringify(this.userInfo))
                    this.$router.push("/dashboard/index")
                } else {
                    this.$Message.error(response.data.msg);
                }
            })
        },
    },
}
</script>
<style>
@import '../assets/css/app.css';
@import '../assets/css/theme.css';
@import '../assets/css/font-awesome.min.css';
@import '../assets/css/owl.carousel.min.css';
</style>
