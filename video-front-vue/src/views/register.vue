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
                                    <h3>注册账号</h3>
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
                                            <span class="input-group-label"><i class="fa fa-envelope"></i></span>
                                            <input class="input-group-field" type="email" placeholder="Enter your email"
                                                required="" v-model='userInfo.email'>
                                        </div>

                                        <div class="input-group">
                                            <span class="input-group-label"><i class="fa fa-lock"></i></span>
                                            <input type="password" id="password" placeholder="Enter your password"
                                                required="" v-model='userInfo.password'>
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-label"><i class="el-icon-phone"></i></span>
                                            <input type="text" placeholder="Re-type your mobilephone" required=""
                                                v-model='userInfo.mobile'>
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-label"><i class="el-icon-s-help"></i></span>
                                            <el-row>
                                                <el-col :span="18"> <input type="text"
                                                        placeholder="Re-type your mobilephone" required=""
                                                        v-model="userInfo.code"> </el-col>
                                                <el-col :span="6" v-if="flag">
                                                    <p style="text-align:center;margin-top: 10px;">
                                                        <el-link @click="getCode()" target="_blank"
                                                            :underline="false">验证码</el-link>
                                                    </p>
                                                </el-col>
                                                <el-col :span="6" v-else>
                                                    <p v-text="count" style="text-align:center;margin-top: 10px;"></p>
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <button class="button expanded" type="button" name="submit"
                                            @click="submit()">注册</button>
                                        <p class="loginclick"> <a href="/login">去登录</a></p>
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
import { isMobile, isEmail } from '@/utils/validate';
import videoHeader from "@/components/videoHeader";
import videoFooter from "@/components/videoFooter";
export default {
    //import 引入的组件需要注入到对象中才能使用
    components: {
        videoHeader,
        videoFooter,
    },
    data() {
        //这里存放数据
        return {
            flag: true,
            count: 0,
            userInfo: {},
        };
    },
    //方法集合
    methods: {
        getCode() {
            //发送验证码
            var isphone = isMobile(this.userInfo.mobile)
            if (isphone) {
                this.flag = false
                this.count = 60
                var times = setInterval(() => {
                    this.count--; //递减
                    if (this.count <= 0) {
                        // <=0 变成获取按钮
                        this.flag = true;
                        clearInterval(times);
                    }
                }, 1000); //1000毫秒后执行
                user.getCode(this.userInfo.mobile).then()
            } else {
                this.$message({
                    message: '请重新正确的手机号码',
                    type: 'warning'
                });
            }
        },

        submit() {
            var isemail = isEmail(this.userInfo.email)
            if (isemail) {
                user.register(this.userInfo).then(response => {
                    if (response.data.code == 0) {
                        this.$message({
                            message: '注册成功',
                            type: 'success'
                        });
                        this.$router.push('/login')
                    } else {
                        this.$message({
                            message: response.data.msg,
                            type: 'warning'
                        });
                    }
                })
            } else {
                this.$message({
                    message: '请重新正确的邮箱',
                    type: 'warning'
                });
            }

        }
    },
}
</script>
<style>
@import '../assets/css/app.css';
@import '../assets/css/theme.css';
@import '../assets/css/font-awesome.min.css';
@import '../assets/css/owl.carousel.min.css';
</style>