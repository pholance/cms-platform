package com.yidumen.cms.view.ajax;

import com.fasterxml.jackson.annotation.JsonView;
import com.yidumen.cms.JacksonView;
import com.yidumen.cms.entity.Tag;
import com.yidumen.cms.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author 蔡迪旻
 */
@RestController
@RequestMapping("tag")
public final class TagAjaxCtrl{

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "columns")
    @JsonView(JacksonView.MostLess.class)
    public List<Tag> columns() {
        return tagService.findColumnTags();
    }

    @RequestMapping(value = "tags")
    @JsonView(JacksonView.MostLess.class)
    public List<Tag> tags() {
        return tagService.findAll();
    }
}
