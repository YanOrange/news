package com.delay.news.repository;

import com.delay.news.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CommentRepository extends JpaRepository<Comment,Integer> {


    List<Comment> findByEssayId(Integer essayId);
}
