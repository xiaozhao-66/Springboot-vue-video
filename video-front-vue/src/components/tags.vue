<template>
  <div class="large-12 medium-7 medium-centered columns">
    <div class="widgetBox">
      <div class="widgetTitle">
        <h5>标签</h5>
      </div>
      <div class="tagcloud">
        <a href="#" v-for="(tag,index) in tagList" :key="index">{{tag.label}}</a>
      </div>
    </div>
  </div><!-- End tags -->
</template>

<script>
  import tag from "@/api/tag"
  export default {
    data() {
      //这里存放数据
      return {
        tagList:[]
      };
    },
//方法集合
    methods: {
      getTagList(){
        tag.getTagList().then(response=>{
          this.tagList =response.data.data
        })
      }

    },
//生命周期- 创建完成（可以访问当前this 实例）
    created() {
      this.getTagList()
    },
  }
</script>

<style>
  @import '../assets/css/app.css';
  @import '../assets/css/theme.css';
  @import '../assets/css/font-awesome.min.css';
  @import '../assets/css/owl.carousel.min.css';
</style>
