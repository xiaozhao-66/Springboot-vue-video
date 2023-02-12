package com.xz.video.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.CategoryDao;
import com.xz.video.entity.CategoryEntity;

import com.xz.video.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 视频分类
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {



    /**
     * 得到所有的一级和二级分类
     * @return 分类集合
     */
    @Override
    @Cacheable(cacheNames = "CategoryList", key = "'CategoryList'", sync = true)
    public List<CategoryEntity> getAllOneTwoSubject() {
        List<CategoryEntity> rootCategoryEntityList = new ArrayList<>();
        QueryWrapper<CategoryEntity> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id", 0);
        List<CategoryEntity> oneList = this.baseMapper.selectList(oneWrapper);
        QueryWrapper<CategoryEntity> twoWrapper = new QueryWrapper<>();

        twoWrapper.ne("parent_id", 0);
        List<CategoryEntity> twoList = this.baseMapper.selectList(twoWrapper);

        for (CategoryEntity categoryEntity : oneList) {
            rootCategoryEntityList.add(categoryEntity);
            List<CategoryEntity> twoCategoryEntityList = new ArrayList<>();
            for (CategoryEntity twoCategoryEntity : twoList) {
                if (twoCategoryEntity.getParentId().equals(categoryEntity.getId())) {
                    twoCategoryEntityList.add(twoCategoryEntity);
                }
            }
            categoryEntity.setChildren(twoCategoryEntityList);
        }
        return rootCategoryEntityList;
    }

    /**
     * 根据分类id得到一级分类
     * @param categoryId 分类id
     * @return 分类实体类
     */
    @Override
    public CategoryEntity getOneCategory(String categoryId) {
        QueryWrapper<CategoryEntity> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("id", categoryId);
        return this.baseMapper.selectOne(oneWrapper);
    }

    /**
     * 根据分类id得到二级分类
     * @param category 分类实体类
     * @return 受否增加成功
     */
    @Override
    @CacheEvict(cacheNames = "CategoryList", key = "'CategoryList'")
    public boolean addCategory(CategoryEntity category) {
        return this.baseMapper.insert(category)>0;
    }

    /**
     * 修改分类
     * @param category 分类实体类
     * @return 是否修改成功
     */
    @Override
    @CacheEvict(cacheNames = "CategoryList", key = "'CategoryList'")
    public boolean updateCategory(CategoryEntity category) {
        return this.baseMapper.updateById(category)>0;
    }

    /**
     * 删除分类
     * @param categoryId 分类id
     * @return 是否删除成功
     */
    @Override
    @CacheEvict(cacheNames = "CategoryList", key = "'CategoryList'")
    public boolean deleteCategory(String categoryId) {
        return this.baseMapper.deleteById(categoryId)>0;
    }
}