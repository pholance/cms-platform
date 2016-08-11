package com.yidumen.cms.view.ajax;

import com.fasterxml.jackson.annotation.JsonView;
import com.yidumen.cms.JacksonView;
import com.yidumen.cms.entity.Sutra;
import com.yidumen.cms.service.SutraService;
import com.yidumen.cms.view.DWZResponse;
import com.yidumen.cms.view.DWZResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 蔡迪旻
 *         2016年01月21日
 */
@RestController
@RequestMapping("sutra")
public class SutraController {


    @Autowired
    private SutraService sutraService;

    @RequestMapping(path = "infos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @JsonView(JacksonView.Normal.class)
    public List<Sutra> getAll() {
        return sutraService.findAll();
    }

    @RequestMapping(path = "info/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @JsonView(JacksonView.More.class)
    public Sutra getSutra(@PathVariable Long id) {
        return sutraService.find(id);
    }
    @RequestMapping(path = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DWZResponse create(@RequestBody Sutra sutra) {
        sutraService.update(sutra);
        return DWZResponseBuilder.initiate().success("佛经《" + sutra.getTitle() + "》已创建").builder();
    }

    @RequestMapping(path = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DWZResponse update(@RequestBody Sutra sutra) {
        sutraService.update(sutra);
        return DWZResponseBuilder.initiate().success("佛经《" + sutra.getTitle() + "》已更新").builder();
    }
}
