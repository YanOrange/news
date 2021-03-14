package com.delay.news.service;

import com.delay.news.entity.Type;

import java.util.List;

public interface TypeService {

    Type findById(Integer typeId);

    List<Type> findAll();

    void saveOrUpdate(Type byId);

    void delete(Integer o);

    Type findByName(String name);
}
