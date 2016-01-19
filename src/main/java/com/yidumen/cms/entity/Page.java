package com.yidumen.cms.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.yidumen.cms.JacksonView;

import javax.persistence.*;
import java.util.List;

/**
 * @author 蔡迪旻
 *         2015年11月15日
 */
@Entity
@Table(name = "resource_page")
public class Page extends Resource {
    @Column(name = "description")
    private String description;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "content", columnDefinition = "MEDIUMTEXT")
    private String content;

    @JsonView(JacksonView.Normal.class)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Page page = (Page) o;

        if (description != null ? !description.equals(page.description) : page.description != null) return false;
        return !(content != null ? !content.equals(page.content) : page.content != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
