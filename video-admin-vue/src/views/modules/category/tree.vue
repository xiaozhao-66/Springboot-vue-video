<template>
   <div>
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />
      <el-tree
        ref="tree"
        :data="data2"
        :props="defaultProps"
        :filter-node-method="filterNode"
        class="filter-tree"
        default-expand-all
        @node-click="handleNodeClick"
      >
      </el-tree>
    </div>
</template>
  
  <script>
  import category from '@/api/category/category'
  export default {
    data() {
      return {
        filterText: '',
        data2: [],
        defaultProps: {
          children: 'children',
          label: 'title'
        },
       }
      
    },
    watch: {
      filterText(val) {
        this.$refs.tree.filter(val)
      }
    },
    created(){
      this.getCategoryList()
    },
    methods: {
      getCategoryList(){
        category.getAllCategory().then(res=>{ 
         this.data2=res.data.data
      })
      },
      filterNode(value, data) {
        if (!value) return true
        return data.title.indexOf(value) !== -1
      },
      handleNodeClick(data) {
        console.log(data)
        this.$emit('clickNode',data)
      }
    }
  }
  </script>
  
  