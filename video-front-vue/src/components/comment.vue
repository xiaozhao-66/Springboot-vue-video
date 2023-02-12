<template>
    <!-- Comments -->
    <section class="content comments">
        <div class="row secBg">
            <div class="large-12 columns">
                <div class="main-heading borderBottom">
                    <div class="row padding-14">
                        <div class="medium-12 small-12 columns">
                            <div class="head-title">
                                <i class="fa fa-comments"></i>
                                <h4>评论</h4>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="comment-box thumb-border">
                    <div class="media-object stack-for-small">
                        <div class="media-object-section comment-img text-center">
                            <div class="comment-box-img">
                                <img :src="userInfo.avatar" alt="comment">
                            </div>
                            <h6><a href="#">{{ userInfo.username }}</a></h6>
                        </div>
                        <div class="media-object-section comment-textarea">
                            <form method="post">
                                <textarea name="commentText" placeholder="Add a comment here.."
                                    v-model="content1"></textarea>
                                <el-button type="info" size="small" round style="float:right"
                                    @click="addComment('')">发送</el-button>

                            </form>
                        </div>
                    </div>
                </div>

                <div class="comment-sort text-right">
                    <span>Sort By : <a href="#">newest</a> | <a href="#">oldest</a></span>
                </div>

                <!-- main comment -->
                <div class="main-comment showmore_one">
                    <div class="media-object stack-for-small" v-for="(comment, index) in commentList" :key="index">
                        <div class="media-object-section comment-img text-center">
                            <div class="comment-box-img">
                                <img :src="comment.avatar" alt="comment">
                            </div>
                        </div>
                        <div class="media-object-section comment-desc">
                            <div class="comment-title">
                                <span class="name"><a href="#">{{ comment.username }}</a>:</span>
                                <span class="time float-right"><i class="fa fa-clock-o"></i>{{ comment.gmtCreate }}</span>
                            </div>
                            <div class="comment-text">
                                <p>{{ comment.content }}</p>
                            </div>
                            <div class="comment-btns">
                                <span><a href="#"><i class="fa fa-thumbs-o-up"></i></a> | <a href="#"><i
                                            class="fa fa-thumbs-o-down"></i></a></span>
                                <span @click="replyClick(index, comment)"><i class="fa fa-share"></i>Reply</span>
                                <span class='reply float-right hide-reply'></span>
                            </div>

                            <div class="comment-box thumb-border"
                                :style="reply == index && status && rootNode == 0 ? 'display:inline' : 'display:none'">
                                <div class="media-object stack-for-small">
                                    <div class="media-object-section comment-img text-center">
                                        <div class="comment-box-img">
                                            <img :src="userInfo.avatar" alt="comment">
                                        </div>
                                        <h6><a href="#">{{ userInfo.username }}</a></h6>
                                    </div>
                                    <div class="media-object-section comment-textarea">
                                        <form method="post">
                                            <textarea name="commentText" placeholder="Add a comment here.."
                                                v-model="content2"></textarea>
                                            <el-button type="info" size="small" round style="float:right"
                                                @click="addComment(comment)">Send</el-button>

                                        </form>
                                    </div>
                                </div>
                            </div>

                            <!--sub comment-->
                            <div class="media-object stack-for-small reply-comment" :id="replyComment.id"
                                v-for="(replyComment, item) in comment.children.records" :key="item">
                                <div class="media-object-section comment-img text-center">
                                    <div class="comment-box-img">
                                        <img :src="replyComment.avatar" alt="comment">
                                    </div>
                                </div>
                                <div class="media-object-section comment-desc">
                                    <div class="comment-title">
                                        <span class="name"><a href="#">{{ replyComment.username }}</a><span> 回复 <a href=""
                                                    style="color: #00a7d0;">@{{ replyComment.replyCommentUsername }}</a></span>:</span>
                                        <span class="time float-right"><i
                                                class="fa fa-clock-o"></i>{{ replyComment.gmtCreate }}</span>
                                    </div>
                                    <div class="comment-text">
                                        <p>{{ replyComment.content }}</p>
                                    </div>
                                    <div class="comment-btns">
                                        <span><a href="#"><i class="fa fa-thumbs-o-up"></i></a> | <a href="#"><i
                                                    class="fa fa-thumbs-o-down"></i></a></span>
                                        <span @click="replyClick(item, replyComment)"><i
                                                class="fa fa-share"></i>Reply</span>
                                        <span class='reply float-right hide-reply'></span>
                                    </div>
                                </div>
                                <div class="comment-box thumb-border"
                                    :style="reply == item && status && rootNode == replyComment.rootCommentId ? 'display:inline' : 'display:none'">
                                    <div class="media-object stack-for-small">
                                        <div class="media-object-section comment-img text-center">
                                            <div class="comment-box-img">
                                                <img :src="userInfo.avatar" alt="comment">
                                            </div>
                                            <h6><a href="#">{{ userInfo.username }}</a></h6>
                                        </div>
                                        <div class="media-object-section comment-textarea">
                                            <form method="post">
                                                <textarea name="commentText" placeholder="Add a comment here.."
                                                    v-model="content2"></textarea>
                                                <el-button type="info" size="small" round style="float:right"
                                                    @click="addComment(replyComment)">Send</el-button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- end sub comment -->
                            <el-pagination small :current-page.sync="page2" :page-size="limit2"
                                :total="comment.children.total" layout="total,prev, pager, next"
                                @current-change="getAllCommentByVideo" />
                        </div>
                    </div>
                </div><!-- End main comment -->
                <div class="pagination">
                    <!-- 分页 -->
                    <el-pagination :current-page.sync="page" :page-size="limit" :total="total"
                        style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper"
                        @current-change="getAllCommentByVideo" />
                </div>
            </div>
        </div>
    </section><!-- End Comments -->
</template>

<script>
import comment from '@/api/comment.js';
export default {
    //import 引入的组件需要注入到对象中才能使用
    components: {},
    props: {},
    data() {
        //这里存放数据
        return {
            videoId: '',
            userInfo: {},
            commentInfo: {},
            commentList: [],
            reply: -1,
            status: false,
            content1: '',
            content2: '',
            page: 1,
            limit: 4,
            total: 0,
            rootNode: -1,
            page2: 1,
            limit2: 3,
        };
    },
    //计算属性类似于data 概念
    computed: {

    },
    //监控data 中的数据变化
    watch: {
    },
    //方法集合
    methods: {
        getAllCommentByVideo() {
            comment.getAllCommentByVideo(this.videoId, this.page, this.limit, this.page2, this.limit2).then(response => {
                this.commentList = response.data.data.records
                this.total = response.data.data.total
            })
        },

        replyClick(index, comment) {
            this.reply = index
            this.status = !this.status
            this.rootNode = comment.rootCommentId
        },

        scrollComment(commentId) {
            
            setTimeout(e => {
                let toElement = document.getElementById(commentId)
                toElement.scrollIntoView()
                toElement.style.backgroundColor = '#e6e1e1'
                setTimeout(e=>{
                    toElement.style.backgroundColor = '#fff'
                },1000)
            }, 1000)
        },

        addComment(com) {
            if (this.userInfo.id == '' || this.userInfo.id == null) {
                this.$message({
                    message: '请先登录',
                    type: 'warning'
                });
            } else {

                if (this.content1 == '' && this.content2 == '') {
                    this.$message({
                        message: '内容不能为空',
                        type: 'warning'
                    });
                } else {
                    this.commentInfo.videoId = this.videoId
                    this.commentInfo.userId = this.userInfo.id
                    this.commentInfo.username = this.userInfo.username
                    this.commentInfo.avatar = this.userInfo.avatar

                    if (com == '') {
                        //增加一级评论
                        this.commentInfo.rootCommentId = '0'
                        this.commentInfo.replyCommentId = '0'
                        this.commentInfo.level = 1
                        this.commentInfo.content = this.content1
                    } else {
                        //增加回复评论
                        if (com.level == 1) {
                            this.commentInfo.rootCommentId = com.id
                        } else {
                            this.commentInfo.rootCommentId = com.rootCommentId
                        }
                        this.commentInfo.replyCommentId = com.id
                        this.commentInfo.replyCommentUsername = com.username
                        this.commentInfo.level = 2
                        this.commentInfo.content = this.content2
                    }
                    comment.addComment(this.commentInfo).then(response => {
                        this.$message({
                            message: '添加评论成功',
                            type: 'success'
                        });
                        this.reply = -1
                        this.status = false
                        this.level = -1
                        this.content1 = ''
                        this.content2 = ''
                        this.getAllCommentByVideo()
                    })
                }
            }
        },
    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$store.getters.getUser) {
            this.userInfo = JSON.parse(this.$store.getters.getUser)
        }
        if (this.$route.params) {
            this.videoId = this.$route.params.id
            this.getAllCommentByVideo()
            if (this.$route.query.commentId) {
                this.scrollComment(this.$route.query.commentId)
            }
        }

    },
}
</script>
<style>

</style>
