package com.delay.news.controller;

import com.delay.news.entity.Comment;
import com.delay.news.entity.Essay;
import com.delay.news.entity.User;
import com.delay.news.service.CommentService;
import com.delay.news.utils.ExecuteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("comment")
public class CommentController extends BaseController{

    @Autowired
    CommentService commentService;

    @RequestMapping("commit")
    @ResponseBody
    public ExecuteResult commit(String content,Integer essayId){
        User user2 = (User)getSession().getAttribute("user2");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateTime(new Date());
        Essay essay = new Essay();
        essay.setId(essayId);
        comment.setEssay(essay);
        comment.setUser(user2);
        commentService.saveOrUpdate(comment);
        return ExecuteResult.ok();
    }

    @RequestMapping("findByEssayId")
    @ResponseBody
    public ExecuteResult findByEssayId(Integer essayId){


        List<Comment> list = commentService.findByEssayId(essayId);
        return ExecuteResult.ok(list);
    }

}
