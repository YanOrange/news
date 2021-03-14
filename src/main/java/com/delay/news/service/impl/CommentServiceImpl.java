package com.delay.news.service.impl;

import com.delay.news.entity.Comment;
import com.delay.news.repository.CommentRepository;
import com.delay.news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void saveOrUpdate(Comment comment) {
        commentRepository.saveAndFlush(comment);
    }

    @Override
    public List<Comment> findByEssayId(Integer essayId) {
        return commentRepository.findByEssayId(essayId);
    }
}
