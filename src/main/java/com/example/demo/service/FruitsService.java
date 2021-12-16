package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.FruitsMapper;

@Service
public class FruitsService {
	
	@Autowired
	FruitsMapper mapper;

    public User getUserOne(Integer id) {
        return mapper.findOne(id);
    }
    
    public List<User> getList() {
        return mapper.find();
    }

    public void insertOne(User u) {
        mapper.insertOne(u);
    }

    public void updateOne(Integer id, String name, Integer price) {
        mapper.updateOne(id, name, price);
    }

    public void deleteOne(Integer id) {
        mapper.deleteOne(id);
    }
}
