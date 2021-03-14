package com.delay.news.service;

import com.delay.news.entity.Comment;

import java.util.List;

public interface CommentService {
    void saveOrUpdate(Comment comment);

    List<Comment> findByEssayId(Integer essayId);
}
