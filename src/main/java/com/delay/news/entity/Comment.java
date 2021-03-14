package com.delay.news.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长
    private Integer id;

    private Date createTime;

    @JoinColumn(name = "essay_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Essay essay;//新闻

    private String content;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
