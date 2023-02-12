<template>
    <div>
        <!-- right side content area -->
        <div class="large-8 columns profile-inner">
            <!-- profile settings -->
            <section class="profile-settings">
                <div class="row secBg">
                    <div class="large-12 columns">
                        <div class="heading">
                            <i class="fa fa-gears"></i>
                            <h4>个人设置</h4>
                        </div>
                        <div class="row">
                            <div class="large-12 columns">
                                <div class="setting-form">
                                    <form method="">
                                        <div class="setting-form-inner">
                                            <div class="row">
                                                <div class="medium-6 columns">
                                                    <label>
                                                        用户名:
                                                        <input type="text" placeholder="enter your first name.."
                                                            v-model="userInfo.username">
                                                    </label>
                                                    <label>密码:
                                                        <input type="password" placeholder="enter your new password.."
                                                            v-model="userInfo.password">
                                                    </label>
                                                    <label>重复密码:
                                                        <input type="password" placeholder="enter your new password.."
                                                            v-model="userInfo.retypePassword">
                                                    </label>
                                                    <p>(不需要修改密码设置为空)</p>
                                                </div>
                                                <div class="medium-6 columns">
                                                    <el-upload class="avatar-uploader"
                                                        :action="BASE_API + '/thirdPart/fileoss/uploadOssFile'"
                                                        :show-file-list="false" :on-success="handleAvatarSuccess"
                                                        :before-upload="beforeAvatarUpload">
                                                        <img v-if="userInfo.avatar" :src="userInfo.avatar"
                                                            class="avatar">
                                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                                    </el-upload>
                                                </div>
                                                <div class="medium-12 columns">
                                                </div>
                                                <div class="medium-12 columns">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="setting-form-inner">
                                            <div class="row">
                                                <div class="large-12 columns">
                                                    <h6 class="borderBottom">关于我:</h6>
                                                </div>
                                                <div class="medium-6 columns">
                                                    <label>Email ID:
                                                        <input type="email" placeholder="enter your email address.."
                                                            v-model="userInfo.email">
                                                    </label>
                                                </div>
                                                <div class="medium-6 columns">
                                                    <label>网站 URL:
                                                        <input type="url" placeholder="enter your website url.."
                                                            v-model="userInfo.websiteUrl">
                                                    </label>
                                                </div>
                                                <div class="medium-6 columns end">
                                                    <label>手机号:
                                                        <input type="tel" placeholder="enter your phone.."
                                                            v-model="userInfo.mobile">
                                                    </label>
                                                </div>
                                                <div class="medium-12 columns">
                                                    <label>介绍自己:
                                                        <textarea v-model="userInfo.description"></textarea>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="setting-form-inner">
                                            <button class="button expanded" type="button" name="setting"
                                                @click="UpdateUser()">修改</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section><!-- End profile settings -->
        </div><!-- end left side content area -->
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import user from "@/api/user";

export default {
    data() {
        //这里存放数据
        return {
            userInfo: {},
            BASE_API: "http://43.248.97.192:9956/api", // 接口API地址
        };
    },
    //方法集合
    methods: {

        //上传封面成功调用的方法
        handleAvatarSuccess(res, file) {
            this.userInfo.avatar = res.data;
        },
        //上传之前调用的方法
        beforeAvatarUpload(file) {
            const isJPG = file.type === "image/jpeg";
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error("上传头像图片只能是 JPG 格式!");
            }
            if (!isLt2M) {
                this.$message.error("上传头像图片大小不能超过 2MB!");
            }
            return isJPG && isLt2M;
        },
        UpdateUser() {
            user.setting(this.userInfo).then(response => {
                if (response.data.code == 0) {
                    this.$Message.success('修改成功');
                    setInterval(e => {
                        window.location.href = "/dashboard/user/1/aboutme?userId=" + this.userInfo.id;
                    }, 1000)
                } else {
                    this.$Message.warning('密码不一致');
                }

            })
        },
        getUserInfo(userId) {
            user.getUserInfoByUserId(userId).then(response => {
                this.userInfo = response.data.data
            })
        }

    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$store.getters.getUser) {
            this.userInfo = JSON.parse(this.$store.getters.getUser)
            this.userInfo.password = ''
            this.getUserInfo(this.userInfo.id)
        }
    },
}
</script>
<style>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>
