package com.yidumen.cms.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.yidumen.cms.JacksonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author 蔡迪旻<yidumen.com>
 */
@Entity
@Table(name = "web_sutra")
public class Sutra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 章节标识
     */
    @Column(name = "part_identifier", length = 16)
    private String partIdentifier;

    /**
     * 标题
     */
    @Column(name = "title", length = 50)
    private String title;

    /**
     * 左值
     *
     * @see http://blog.csdn.net/MONKEY_D_MENG/article/details/6647488
     */
    @Column(name = "left_value")
    private Long leftValue;

    /**
     * 右值
     *
     * @see http://blog.csdn.net/MONKEY_D_MENG/article/details/6647488
     */
    @Column(name = "right_value")
    private Long rightValue;
    @ManyToMany
    @JoinTable(name = "related_sutra_tag", joinColumns = @JoinColumn(name = "sutra_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    /**
     * 佛经内容
     */
    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "content", columnDefinition = "MEDIUMTEXT")
    private String content;

    @JsonView(JacksonView.Less.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(JacksonView.Less.class)
    public String getPartIdentifier() {
        return partIdentifier;
    }

    public void setPartIdentifier(String partIdentifier) {
        this.partIdentifier = partIdentifier;
    }

    @JsonView(JacksonView.Less.class)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonView(JacksonView.Normal.class)
    public Long getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(Long leftValue) {
        this.leftValue = leftValue;
    }

    @JsonView(JacksonView.Normal.class)
    public Long getRightValue() {
        return rightValue;
    }

    public void setRightValue(Long rightValue) {
        this.rightValue = rightValue;
    }

    @JsonView(JacksonView.MuchMore.class)
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @JsonView(JacksonView.More.class)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
