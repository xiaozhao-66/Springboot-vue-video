<template>
     <el-card>
        <el-row>
          <el-col :span="8">
            <category-tree @clickNode = clickNode></category-tree>
          </el-col>
          <el-col :span="15" offset="1">
            <video-info  ref ='childVideoInfo'></video-info>
          </el-col>
        </el-row>
    </el-card>
</template>
  
  <script>
  import category from '@/api/category/category'
  import CategoryTree from '../category/tree'
  import VideoInfo from './list'
  export default {
    data() {
      return {
         categoryId:''
      }
    },
    components: {
        CategoryTree,
        VideoInfo
    },
    created(){
       
    },
    methods: {
        clickNode(data){

            console.log('父组件的方法',data)

            this.$refs.childVideoInfo.getDateList(data.id);
        }
    }
  }
  </script>
  
  