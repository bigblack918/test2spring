package com.leo.testspring.service;

import com.leo.testspring.dao.UserRepository;
import com.leo.testspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public Integer getUserId(){
        System.out.println("進入了UserService的getUserId方法");
        int userId=5;
        return userId;
    }

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        System.out.println(user.getName());
        System.out.println(user.getAge());
        userRepository.save(user);
        return userRepository.findById(user.getId());
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
