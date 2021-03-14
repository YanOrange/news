package com.delay.news.service.impl;

import com.delay.news.entity.Fav;
import com.delay.news.repository.FavRepository;
import com.delay.news.service.FavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavServiceImpl implements FavService {

    @Autowired
    FavRepository favRepository;

    @Override
    public List<Fav> findByUserId(Integer id) {
        return favRepository.findAllByUserId(id);
    }

    @Override
    public void saveOrUpdate(Fav fav) {
        favRepository.saveAndFlush(fav);
    }

    @Override
    public Fav findByUserIdAndEssayId(Integer id, Integer essayId) {
        return favRepository.findByUserIdAndNewsId(id,essayId);
    }
}
