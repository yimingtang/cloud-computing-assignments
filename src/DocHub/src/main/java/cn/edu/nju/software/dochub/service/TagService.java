package cn.edu.nju.software.dochub.service;

import cn.edu.nju.software.dochub.data.dao.TagDAO;
import cn.edu.nju.software.dochub.data.dao.TaggingDAO;
import cn.edu.nju.software.dochub.data.dataobject.Tag;

import java.util.List;

/**
 * Created by tym on 13-12-31.
 */
public class TagService {

    private TagDAO tagDAO;
    private TaggingDAO taggingDAO;

    public List<Tag> getAllTags() {
        return tagDAO.findAll();
    }

    public void addTag(Tag tag) {
        if (tagDAO.findByName(tag.getName()).size() == 0) {
            tagDAO.save(tag);
        }
    }

    public void deleteTagById(int id) {
        deleteTag(tagDAO.findById(id));
    }

    public void deleteTag(Tag tag) {
        tagDAO.delete(tag);
    }

    public List<Tag> getHotTags() {
        return null;
    }

    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    public void setTaggingDAO(TaggingDAO taggingDAO) {
        this.taggingDAO = taggingDAO;
    }
}
