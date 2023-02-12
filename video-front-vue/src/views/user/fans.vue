<template>
  <div>
    <!-- right side content area -->
    <div class="large-8 columns profile-inner">
      <!-- followers -->
      <section class="content content-with-sidebar followers margin-bottom-10">
        <div class="row secBg">
          <div class="large-12 columns">
            <div class="row column head-text clearfix">
              <h4 class="pull-left"><i class="fa fa-users"></i>我的粉丝</h4>
            </div>
            <div class="row collapse">
              <div class="large-2 small-6 medium-3 columns" v-for="(fan, index) in dataList" :key="index">
                <a @click ="see(fan.id)" >
                  <div class="follower">
                  <div class="follower-img">
                    <img :src="fan.avatar" alt="followers">
                  </div>
                  <p style="text-align:center ">{{ fan.username }}</p>
                </div>
                </a>
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
import fan from '@/api/fan';
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
    };
  },
  //方法集合
  methods: {
    getAllFan() {
      fan.getAllFan(this.page, this.limit, this.userId).then(response => {
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
      fan.getAllFan(this.page, this.limit, this.userId).then(response => {
        this.isEnd = false;
        var fanList = this.dataList.concat(response.data.data.list)
        this.dataList = fanList
        if (fanList.length == this.total) {
          this.loading = false
          this.isEnd = true
        }
      })
    },
    see(userId) {
      window.location.href = "/dashboard/user/1/aboutme?userId=" + userId
    }
  },
  //生命周期- 创建完成（可以访问当前this 实例）
  created() {
    if (this.$route.query.userId) {
      this.userId = this.$route.query.userId
      this.getAllFan()
    }
  },
}
</script>
<style >

</style>
