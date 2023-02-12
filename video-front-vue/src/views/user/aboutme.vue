<template>
    <div>
        <div class="large-8 columns profile-inner">
            <!-- single post description -->
            <section class="singlePostDescription">
                <div class="row secBg">
                    <div class="large-12 columns">
                        <div class="heading">
                            <i class="fa fa-user"></i>
                            <h4>个人信息</h4>
                        </div>
                        <div class="description">
                            <p>{{ userInfo.description }}</p>
                            <div class="site profile-margin">
                                <button><i class="fa fa-globe"></i>Site</button>
                                <a href="#" class="inner-btn">www.betube.com</a>
                            </div>
                            <div class="email profile-margin">
                                <button><i class="fa fa-envelope"></i>Email</button>
                                <span class="inner-btn">{{ userInfo.email }}</span>
                            </div>
                            <div class="phone profile-margin">
                                <button><i class="fa fa-phone"></i>Phone</button>
                                <span class="inner-btn">{{ userInfo.mobile }}</span>
                            </div>
                            <div class="socialLinks profile-margin">
                                <button><i class="fa fa-share-alt"></i>get socialize</button>
                                <a href="#" class="inner-btn"><i class="fa fa-facebook"></i></a>
                                <a href="#" class="inner-btn"><i class="fa fa-twitter"></i></a>
                                <a href="#" class="inner-btn"><i class="fa fa-google-plus"></i></a>
                                <a href="#" class="inner-btn"><i class="fa fa-flickr"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </section><!-- End single post description -->
        </div><!-- end left side content area -->
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import user from '@/api/user';
export default {
    data() {
        //这里存放数据
        return {
            userInfo: {},
        };
    },
    //方法集合
    methods: {

        getUserInfoByUserId(userId) {
            user.getUserInfoByUserId(userId).then(response => {
                this.userInfo = response.data.data
            })
        }
    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$route.query.userId) {
            this.getUserInfoByUserId(this.$route.query.userId)
        }
    },
}
</script>
<style >

</style>