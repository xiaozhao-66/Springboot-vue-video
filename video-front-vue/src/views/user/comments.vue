<template>
    <div>
        <!-- right side content area -->
        <div class="large-8 columns profile-inner">
            <!-- Comments -->
            <section class="content comments">
                <div class="row secBg">
                    <div class="large-12 columns">
                        <div class="main-heading borderBottom">
                            <div class="row padding-14">
                                <div class="medium-12 small-12 columns">
                                    <div class="head-title">
                                        <i class="fa fa-comments"></i>
                                        <h4>回复评论</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- main comment -->
                        <div class="main-comment showmore_one">
                            <div class="media-object stack-for-small" v-for="(comment, index) in dataList"
                                :key="index">
                                <div class="media-object-section comment-img text-center">
                                    <div class="comment-box-img">
                                        <img :src="comment.avatar" alt="comment">
                                    </div>
                                </div>
                                <div class="media-object-section comment-desc">
                                    <div class="comment-title">
                                        <span class="name"><a href="#">{{ comment.username }}</a>:</span>
                                        <span class="time">&nbsp;<i
                                                class="fa fa-clock-o"></i>{{ comment.gmtCreate }}</span>
                                        <div class=" current_user_text float-right">
                                            {{ comment.mcontent }}
                                        </div>
                                    </div>
                                    <div class="comment-text">
                                        <a @click='toJump(comment.videoId, comment.id)'>{{ comment.content }}</a>
                                    </div>
                                </div>
                            </div>
                        </div><!-- End main comment -->
                        <div class="show-more-inner text-center">
                          <el-link :underline="false" @click="showMore()" v-if="loading">点击加载更多</el-link>
                          <p v-if="isEnd">我也是有底线的~</p>
                        </div>
                    </div>
                </div>
            </section><!-- End Comments -->
        </div><!-- end left side content area -->
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import comment from '@/api/comment.js';
export default {
    data() {
        //这里存放数据
        return {
            userInfo: {},
            dataList: [],
            page: 1,
            limit: 12,
            isEnd: false,
            loading: true,
            total: 0,
        };
    },
    //方法集合
    methods: {
      getAllNoReplyComment() {
        comment.getAllNoReplyComment(this.page, this.limit,this.userInfo.id).then(response => {
          this.dataList = response.data.data.list
          this.total = response.data.data.count
          if (this.total > this.limit) {
            this.loading = true
          } else {
            this.loading = false
            this.isEnd = true
          }
        })
      },
        toJump(videoId, commentId) {
            this.$router.push({ path: `/dashboard/videoInfo/${videoId}`, query: { commentId: commentId } });
        },
      showMore() {
        this.page = this.page + 1
        comment.getAllNoReplyComment(this.page, this.limit,this.userInfo.id).then(response => {
          this.isEnd = false;
          var commentList = this.dataList.concat(response.data.data.list)
          this.dataList = commentList
          if (commentList.length == this.total) {
            this.loading = false
            this.isEnd = true
          }
        })
      },
    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$store.getters.getUser) {
            this.userInfo = JSON.parse(this.$store.getters.getUser)
            this.getAllNoReplyComment()
        }
    },
}
</script>
<style scoped>
.current_user_text {
    width: 140px;
    height: 35px;
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    word-break: break-all;

}
</style>
