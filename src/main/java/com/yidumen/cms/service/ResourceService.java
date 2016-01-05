package com.yidumen.cms.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.yidumen.cms.entity.Audio;
import com.yidumen.cms.entity.Image;
import com.yidumen.cms.entity.Page;
import com.yidumen.cms.entity.Video;
import com.yidumen.cms.repository.AudioHibernateRepository;
import com.yidumen.cms.repository.HibernateRepository;
import com.yidumen.cms.repository.ImageHibernateRepository;
import com.yidumen.cms.repository.VideoHibernateRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author 蔡迪旻
 *         2015年08月04日
 */
@Service
public final class ResourceService {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private String bucket;
    @Autowired
    private ImageHibernateRepository imageDao;
    @Autowired
    private VideoHibernateRepository videoDao;
    @Autowired
    private AudioHibernateRepository audioDao;
    @Qualifier("pageRepository")
    @Autowired
    private HibernateRepository<Page> pageRepository;

    public List<Image> getImages() {
        return imageDao.findAll();
    }

    public List<Video> getVideos() {
        return videoDao.findAll();
    }

    public List<Audio> getAudios() {
        return audioDao.findAll();
    }

    public Image uploadImage(String title, MultipartFile file) throws IOException {
        final String storeFileName = uploadToOSS(title, file, "wechat/images");
        final Image entity = new Image();
        entity.setTitle(title);
        entity.setFile(storeFileName);
        entity.setCreateDate(new Date());
        imageDao.create(entity);
        return entity;
    }

    public Video uploadVideo(String title, MultipartFile file) throws IOException {
        final String storeFileName = uploadToOSS(title, file, "video");
        final Video entity = new Video();
        entity.setTitle(title);
        entity.setFile(storeFileName);
        entity.setCreateDate(new Date());
        videoDao.create(entity);
        return entity;
    }

    private String uploadToOSS(String title, MultipartFile file, String path) throws IOException {
        final String originalFilename = file.getOriginalFilename();
        final String extName = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentDisposition(file.getOriginalFilename());
        objectMetadata.setContentType(file.getContentType());

        final OSSClient client = createOSSClient();
        //文件名由title与当前时间毫秒数的MD5码组成，暂时不考虑重复的情况
        final String storeFileName = DigestUtils.md5Hex(title + System.currentTimeMillis()) + extName;
        client.putObject(bucket, path + "/" + storeFileName, file.getInputStream(), objectMetadata);
        return storeFileName;
    }


    private OSSClient createOSSClient() {
        return (OSSClient) applicationContext.getBean("ossClient");
    }


    public void createPage(Page page) {
        page.setCreateDate(new Date());
        pageRepository.create(page);
    }
}
