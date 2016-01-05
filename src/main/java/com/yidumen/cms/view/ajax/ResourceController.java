package com.yidumen.cms.view.ajax;

import com.yidumen.cms.entity.Audio;
import com.yidumen.cms.entity.Image;
import com.yidumen.cms.entity.Page;
import com.yidumen.cms.entity.Video;
import com.yidumen.cms.service.ResourceService;
import com.yidumen.cms.view.DWZResponse;
import com.yidumen.cms.view.DWZResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 蔡迪旻
 *         2015年07月31日
 */
@RestController
@RequestMapping("material")
public final class ResourceController {
    @Autowired
    private ResourceService service;

    @RequestMapping(value = "images", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Image> getPictures() {
        return service.getImages();
    }

    @RequestMapping(value = "videos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Video> getVideos() {
        return service.getVideos();
    }

    @RequestMapping(value = "audios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Audio> getAudios() {
        return service.getAudios();
    }

    @RequestMapping(value = "upload/image", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> uploadImage(@RequestParam String title,
                                           @RequestParam MultipartFile file) throws IOException {
        final Image image = service.uploadImage(title, file);

        final Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("title", "操作完成");
        result.put("text", "图片 " + title + "已创建成功。");
        result.put("entity", image);
        return result;
    }

    @RequestMapping(value = "upload/video", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> uploadVideo(@RequestParam String title,
                                           @RequestParam MultipartFile file) {
        try {
            service.uploadVideo(title, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("title", "操作完成");
        result.put("text", "视频 " + title + "已创建成功。");
        return result;
    }

    @RequestMapping(path = "create/page", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DWZResponse createPage(@RequestBody Page page) {
        service.createPage(page);
        return DWZResponseBuilder.initiate().success("成功创建页面 " + page.getTitle()).forwardUrl("/wechat/material/page/list").builder();
    }
}
