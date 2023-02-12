package com.xz.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.TagDao;
import com.xz.video.entity.TagEntity;
import com.xz.video.queryParams.TagQueryParams;
import com.xz.video.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagDao, TagEntity> implements TagService {
    @Override
    public IPage<TagEntity> getAllTags(long page, long limit, TagQueryParams tagQueryParams) {
        QueryWrapper<TagEntity> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(tagQueryParams.getTitle())){
            queryWrapper.like("label",tagQueryParams.getTitle());
        }
        if (!org.apache.commons.lang.StringUtils.isEmpty(tagQueryParams.getBegin())) {
            queryWrapper.ge("gmt_create", tagQueryParams.getBegin());
        }
        if (!org.apache.commons.lang.StringUtils.isEmpty(tagQueryParams.getEnd())) {
            queryWrapper.le("gmt_create", tagQueryParams.getEnd());
        }
        queryWrapper.orderByAsc("sort");
        Page<TagEntity> tagPage = new Page<>(page,limit);
        return this.baseMapper.selectPage(tagPage, queryWrapper);
    }

    @Override
    @CacheEvict(cacheNames = "TagList", key = "'TagList'")
    public void addTag(TagEntity tag) {
        this.baseMapper.insert(tag);
    }

    @Override
    public TagEntity getTagInfo(String tagId) {
        return this.baseMapper.selectById(tagId);
    }

    @Override
    @CacheEvict(cacheNames = "TagList", key = "'TagList'")
    public void updateTag(TagEntity tag) {
         this.baseMapper.updateById(tag);
    }

    @Override
    @CacheEvict(cacheNames = "TagList", key = "'TagList'")
    public void removeAll(String tagId) {
        this.baseMapper.deleteById(tagId);
    }

    @Override
    @Cacheable(cacheNames = "TagList", key = "'TagList'", sync = true)
    public List<TagEntity> getTagList() {
        return this.baseMapper.selectList(new QueryWrapper<TagEntity>().orderByAsc("sort"));
    }
}
