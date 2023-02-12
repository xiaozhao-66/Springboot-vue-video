package com.xz.video.controller.admin;


import com.xz.video.entity.CategoryEntity;
import com.xz.video.service.CategoryService;
import io.renren.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器层
 * @author xiaozhao
 */
@RestController
@RequestMapping("/video/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 得到所有的一级和二级分类
     * @return 分类集合
     */
    @GetMapping("getAllCategory")
    public Result getAllCategory() {
        Result<List<CategoryEntity>> result = new Result<>();
        List<CategoryEntity> categories = categoryService.getAllOneTwoSubject();
        return result.ok(categories);
    }

    /**
     * 根据分类id得到一级分类
     * @param categoryId 分类id
     * @return 分类实体类
     */
    @GetMapping("getOneCategory/{categoryId}")
    public Result getOneCategory(@PathVariable String categoryId) {
        Result<CategoryEntity> result = new Result<>();
        CategoryEntity CategoryEntity = categoryService.getOneCategory(categoryId);
        return result.ok(CategoryEntity);
    }

    /**
     * 根据分类id得到二级分类
     * @param category 分类实体类
     * @return 受否增加成功
     */
    @PostMapping("addCategory")
    public Result addCategory(@RequestBody CategoryEntity category) {
        Result result = new Result();
        boolean f = categoryService.addCategory(category);
        return f ? result.ok() : result.error();
    }

    /**
     * 修改分类
     * @param category 分类实体类
     * @return 是否修改成功
     */
    @PostMapping("updateCategory")
    public Result updateCategory(@RequestBody CategoryEntity category) {
        Result result = new Result();
        boolean f = categoryService.updateCategory(category);
        return f ? result.ok() : result.error();
    }

    /**
     * 删除分类
     * @param categoryId 分类id
     * @return 是否删除成功
     */
    @DeleteMapping("deleteCategory/{categoryId}")
    public Result deleteCategoryEntity(@PathVariable String categoryId) {
        Result result = new Result();
        boolean f = categoryService.deleteCategory(categoryId);
        return f ? result.ok() : result.error();
    }

}

