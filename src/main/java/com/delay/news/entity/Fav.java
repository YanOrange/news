package com.delay.news.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "t_fav")
public class Fav {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长
    private Integer id;

    private Integer newsId;

    private Integer userId;

    private Date createTime;

}
