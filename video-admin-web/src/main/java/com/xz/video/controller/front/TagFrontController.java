package com.xz.video.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.entity.TagEntity;
import com.xz.video.queryParams.TagQueryParams;
import com.xz.video.service.TagService;
import io.renren.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 标签模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/tag")
@Slf4j
public class TagFrontController {

    @Autowired
    TagService tagService;

    /**
     * 得到所有标签
     */
    @RequestMapping("getTagList")
    public Result getTagList(){
        Result<List<TagEntity>> result = new Result<>();
        List<TagEntity> list = tagService.getTagList();
        return result.ok(list);
    }
}
