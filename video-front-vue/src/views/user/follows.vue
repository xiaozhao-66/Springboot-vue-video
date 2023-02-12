<template>
  <div>
    <!-- right side content area -->
    <div class="large-8 columns profile-inner">
      <!-- followers -->
      <section class="content content-with-sidebar followers margin-bottom-10">
        <div class="row secBg">
          <div class="large-12 columns">
            <div class="row column head-text clearfix">
              <h4 class="pull-left"><i class="fa fa-users"></i>我的关注</h4>
            </div>
            <div class="row collapse">
              <div class="large-2 small-6 medium-3 columns" v-for="(follow, index) in dataList" :key="index">
                <div class="follower">
                  <div class="follower-img">
                    <a @click ="see(follow.id)" >
                       <img :src="follow.avatar" alt="followers">
                    </a>
                  </div>
                  <p style="text-align:center ">{{ follow.username }}</p>
                  <button type="button" name="follow" @click="cancelFollow(follow.id)" v-show="isAdmin">取消关注</button>
                </div>
              </div>
            </div>
          </div>
          <div class="show-more-inner text-center">
            <el-link :underline="false" @click="showMore()" v-if="loading">点击加载更多</el-link>
            <p v-if="isEnd">我也是有底线的~</p>
          </div>
        </div>
      </section>
    </div><!-- end left side content area -->
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';
import follow from '@/api/follow';
export default {
  data() {
    //这里存放数据
    return {
      dataList: [],
      userId: '',
      page: 1,
      limit: 12,
      isEnd: false,
      loading: true,
      total: 0,
      isAdmin:false,
    };
  },
  //方法集合
  methods: {
    getAllFollow() {
      follow.getAllFollow(this.page, this.limit, this.userId).then(response => {
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
    showMore() {
      this.page = this.page + 1
      follow.getAllFollow(this.page, this.limit, this.userId).then(response => {
        this.isEnd = false;
        var followList = this.dataList.concat(response.data.data.list)
        this.dataList = followList
        if (followList.length == this.total) {
          this.loading = false
          this.isEnd = true
        }
      })
    },
    see(userId) {
      window.location.href = "/dashboard/user/1/aboutme?userId=" + userId
    },
    //取消关注
    cancelFollow(id) {
        var followEntity = {}
        followEntity.userId = this.userId
        followEntity.followUserId = id
        follow.cancelFollow(followEntity).then(response=>{
           window.location.href = window.location.href
        })
    },
  },
  //生命周期- 创建完成（可以访问当前this 实例）
  created() {
    if (this.$store.getters.getUser) {
        var admin = JSON.parse(this.$store.getters.getUser)
            if (this.$route.query.userId) {
                if(admin.id == this.$route.query.userId){
                    this.isAdmin = true
                }
                this.userId = this.$route.query.userId
                this.getAllFollow()
            }
       }
  },
}
</script>
<style >

</style>
