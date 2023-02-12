package com.xz.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xz.video.entity.TagEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.queryParams.TagQueryParams;

import java.util.List;

/**
 *
 */
public interface TagService extends IService<TagEntity> {
    IPage<TagEntity> getAllTags(long page, long limit, TagQueryParams tagQueryParams);

    void addTag(TagEntity tag);

    TagEntity getTagInfo(String tagId);

    void updateTag(TagEntity tag);

    void removeAll(String tagId);

    List<TagEntity> getTagList();
}
