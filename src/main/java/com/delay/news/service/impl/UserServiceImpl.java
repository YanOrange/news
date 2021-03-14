package com.delay.news.service.impl;

import com.delay.news.entity.User;
import com.delay.news.repository.UserRepository;
import com.delay.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(Integer authorId) {
        Optional<User> byId = userRepository.findById(authorId);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    @Override
    public void saveOrUpdate(User comAuthor) {
        userRepository.saveAndFlush(comAuthor);
    }

    @Override
    public List<User> findAllByStatus(int status) {
        return userRepository.findAllByStatus(status);
    }

    @Override
    public void delete(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public User findByAccountAndPassWord(String userName, String passWord) {
        return userRepository.findByAccountAndPassWord(userName,passWord);
    }
}
