package com.yidumen.cms.view.ajax;

import com.yidumen.cms.repository.ImageHibernateRepository;
import com.yidumen.cms.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 蔡迪旻
 *         2016年01月04日
 */
@RestController
@RequestMapping("ueditor")
public class UEditor {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(method = RequestMethod.GET, params = "action=config", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> config() {
        final Map<String, String> result = new HashMap<>();
        result.put("imageManagerActionName", "listimage");
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, params = "action=listimage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> listImage() {
        final Map<String, Object> result = new HashMap<>();
        result.put("state", "SUCCESS");
        final List<Map<String, Object>> listImages = new ArrayList<>();
        resourceService.getImages().forEach(image -> {
            final Map<String, Object> listImage = new HashMap<>();
            listImage.put("url", "/oss/wechat/images/" + image.getFile());
            listImage.put("mtime", image.getCreateDate().getTime());
            listImages.add(listImage);
        });
        result.put("list", listImages);
        return result;
    }
}
