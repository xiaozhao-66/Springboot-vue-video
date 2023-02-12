package com.xz.video.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xz.video.entity.TagEntity;
import com.xz.video.queryParams.TagQueryParams;
import com.xz.video.service.TagService;
import io.renren.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后端标签管理模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/tag")
public class TagController {

    @Autowired
    TagService tagService;

    /**
     * 查找所有的标签
     * @param page 分页
     * @param limit 分页数
     * @param tagQueryParams 查询条件
     */
    @RequestMapping("getAllTags/{page}/{limit}")
    public Result getAllTags(@PathVariable long page, @PathVariable long limit, @RequestBody TagQueryParams tagQueryParams){
        Result<IPage<TagEntity>> result = new Result<>();
        IPage<TagEntity> pageInfo = tagService.getAllTags(page,limit,tagQueryParams);
        return result.ok(pageInfo);
    }

    /**
     * 增加标签
     * @param tag 标签类
     */
    @RequestMapping("addTag")
    public Result addTag(@RequestBody TagEntity tag){
        tagService.addTag(tag);
        return new Result().ok();
    }

    /**
     * 得到标签信息
     * @param tagId 标签id
     */
    @RequestMapping("getTagInfo/{tagId}")
    public Result getTagInfo(@PathVariable String tagId){
        Result<TagEntity> result = new Result<>();
        TagEntity tag = tagService.getTagInfo(tagId);
        return result.ok(tag);
    }

    /**
     * 修改标签信息
     * @param tag 标签实体类
     */
    @RequestMapping("updateTag")
    public Result updateTag(@RequestBody TagEntity tag){
        tagService.updateTag(tag);
        return new Result().ok();
    }

    /**
     * 删除标签
     * @param tagId 标签id
     */
    @RequestMapping("removeAll/{tagId}")
    public Result removeAll(@PathVariable String tagId){
        tagService.removeAll(tagId);
        return new Result().ok();
    }
}
