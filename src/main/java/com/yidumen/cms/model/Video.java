package com.yidumen.cms.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 蔡迪旻
 */
public final class Video extends BaseModel<Video> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public static final Video dao = new Video();

    public Video() {
        super("Video");
    }

    public List<Video> getNew(int limit) {
        return this.find("SELECT * FROM Video ORDER BY pubDate DESC LIMIT ?", limit);
    }
    
    public Video extInfo() {
        final List<Record> extInfos = Db.find("select * from VideoInfo where video_id = ? order by resolution", this.get("id"));
        return this.put("extInfo", extInfos);
    }

    public Video addTags() {
        return this.put("tags", queryTags());
    }

    public void updateWithRelate() {
        this.update();
        LOG.debug("{}", this.get("extInfo"));
        final List<Record> extInfos = this.get("extInfo");
        if (extInfos != null) {
            for (Record extInfo : extInfos) {
                Db.update("VideoInfo", new Record().setColumns(extInfo));
            }
        }
        /*
        Tag的更新（多对多关联的更新）是难点，需要区别2种不同的tag：
        1. 新增；2. 已删除；
         */
        final List<Record> tags = this.get("tags");
        if (tags != null) {
            final List<Record> tempRecords = new ArrayList<>();
            final List<Record> tempTags = new ArrayList<>();
            //1.筛选出不需要更新的记录，排除掉
            final List<Record> videoTags = Db.find("select * from Tag_Video where videos_id = ?", this.get("id"));
            for (Record videoTag : videoTags) {
                LOG.debug("videoTag:{}->{}", videoTag.toJson(), videoTag.get("tags_id").getClass().getName());
                for (Record tag : tags) {
                    LOG.debug("tag:{}->{}", tag.toJson(), tag.get("id").getClass().getName());
                    if (tag.getInt("id").longValue() == videoTag.getLong("tags_id")) {
                        LOG.debug("add  videoTag to remove : {}", videoTag.toJson());
                        tempRecords.add(videoTag);
                        LOG.debug("add tag to remove : {}", tag.toJson());
                        tempTags.add(tag);
                    }
                }
            }
            tags.removeAll(tempTags);
            videoTags.removeAll(tempRecords);
            //2. 现在videoTags中如果还有的记录就是已删除的，tags中如果还有记录就是新增的
            for (Record tag : tags) {
                LOG.debug("tag on saving : {}", tag.toJson());
                Db.save("Tag_Video", new Record().set("videos_id", this.get("id")).set("tags_id", tag.get("id")));
            }
            for (Record videoTag : videoTags) {
                Db.update("delete from `Tag_Video` where `videos_id` = ? and `tags_id` = ?", videoTag.get("videos_id"), videoTag.get("tags_id"));
            }
        }

    }

    private List<Tag> queryTags() {
        return Tag.dao.find("SELECT Tag.* FROM Tag INNER JOIN Tag_Video ON Tag_Video.tags_id = Tag.id INNER JOIN Video ON Tag_Video.videos_id = Video.id WHERE Video.id = ?", this.get("id"));
    }

}
