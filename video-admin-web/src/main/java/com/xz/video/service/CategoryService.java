package com.xz.video.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.CategoryEntity;

import java.util.List;

/**
 * <p>
 * 视频分类
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface CategoryService extends IService<CategoryEntity> {

    /**
     * 得到所有的一级和二级分类
     * @return 分类集合
     */
    List<CategoryEntity> getAllOneTwoSubject();

    /**
     * 根据分类id得到一级分类
     * @param categoryId 分类id
     * @return 分类实体类
     */
    CategoryEntity getOneCategory(String categoryId);

    /**
     * 根据分类id得到二级分类
     * @param category 分类实体类
     * @return 受否增加成功
     */
    boolean addCategory(CategoryEntity category);

    /**
     * 修改分类
     * @param category 分类实体类
     * @return 是否修改成功
     */
    boolean updateCategory(CategoryEntity category);

    /**
     * 删除分类
     * @param categoryId 分类id
     * @return 是否删除成功
     */
    boolean deleteCategory(String categoryId);
}
