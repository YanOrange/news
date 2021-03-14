package com.delay.news.service;

import com.delay.news.entity.Fav;

import java.util.List;

public interface FavService {
    List<Fav> findByUserId(Integer id);

    void saveOrUpdate(Fav fav);

    Fav findByUserIdAndEssayId(Integer id, Integer essayId);
}
