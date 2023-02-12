<template>
  <el-card shadow="never" class="aui-card--fill">
    <div class="mod-sys__category">
      <el-button type="primary" @click="Visible = true,Item = 'add'" >添加一级分类</el-button>
      <el-divider></el-divider>
      <el-table
        v-loading="dataListLoading"
        :data="dataList"
        row-key="id"
        border
        style="width: 100%"
      >
        <el-table-column
          prop="title"
          :label="$t('category.title')"
          header-align="center"
          min-width="150"
        ></el-table-column>
        <el-table-column
          prop="categoryType"
          :label="$t('category.type')"
          header-align="center"
          align="center"
        >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.parentId === '0'" size="small">{{
              $t("category.type0")
            }}</el-tag>
            <el-tag v-else size="small" type="info">{{
              $t("category.type1")
            }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="sort"
          :label="$t('category.sort')"
          header-align="center"
          align="center"
        ></el-table-column>
        <el-table-column
          :label="$t('handle')"
          fixed="right"
          header-align="center"
          align="center"
          width="150"
        >
          <template slot-scope="scope">
            <el-button
              v-if="scope.row.parentId === '0'"
              type="text"
              size="small"
              @click="addCategory(scope.row.id, 'add')"
              >{{ $t("add") }}</el-button
            >
            <el-button
              type="text"
              size="small"
              @click="updateCategory(scope.row.id, 'update')"
              >{{ $t("update") }}</el-button
            >
            <el-button
              type="text"
              size="small"
              @click="deleteHandle(scope.row.id)"
              >{{ $t("delete") }}</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <!-- 增加分类 -->
      <el-dialog title="添加分类" :visible.sync="Visible">
        <el-form :model="category">
          <el-form-item
            :label="$t('category.title')"
            :label-width="formLabelWidth"
          >
            <el-input v-model="category.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item
            :label="$t('category.sort')"
            :label-width="formLabelWidth"
          >
            <el-input-number
              v-model="category.sort"
              :min="0"
              controls-position="right"
            />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addOrUpdateCategory(Item)"
            >确 定</el-button
          >
        </div>
      </el-dialog>
    </div>
  </el-card>
</template>
  
  <script>
import mixinViewModule from "@/mixins/view-module";
import category from "@/api/category/category";
export default {
  mixins: [mixinViewModule],
  data() {
    return {
      mixinViewModuleOptions: {
        getDataListURL: "/video/category/getAllCategory",
        deleteURL: '/video/category/deleteCategory'
      },
      dataList:[],
      Visible: false,
      category: {
        title: "",
        sort: 0,
        parentId: "0",
      },
      Item: "",
    };
  },
  components: {},
  methods: {
    getAllCategory(){
      category.getAllCategory().then(response=>{
        this.dataList = response.data.data
      })
    },
    //添加一级分类
    addCategory(categoryId, item) {
      this.Visible = true;
      this.Item = item;
      console.log(categoryId)
      //如果categoryId存在则是添加二级分类
      if (categoryId) {
        category.getOneCategory(categoryId).then((response) => {
          this.category.parentId = response.data.data.id;
        });
      }
    },
    updateCategory(categoryId, item) {
      this.Visible = true;
      this.Item = item;
      category.getOneCategory(categoryId).then((response) => {
        this.category = response.data.data;
      });
    },
    //添加二级分类或修改操作
    addOrUpdateCategory(item) {
     
      if (item == "add") {
        category.addCategory(this.category).then((response) => {
          console.log(this.category);
          this.$message({
            showClose: true,
            message: "添加成功",
            type: "success",
          });
        });
      } else {
        category.updateCategory(this.category).then((response) => {
          console.log(this.category);
          this.$message({
            showClose: true,
            message: "修改成功",
            type: "success",
          });

        });
      }
      this.Visible =false
      this.category = {}
      this.getAllCategory()
    },
    deleteCategory(category){
      category.deleteCategory(category).then(response=>{
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      })
    }
  },
};
</script>
  