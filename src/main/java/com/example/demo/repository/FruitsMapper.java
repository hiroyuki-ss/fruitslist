package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.User;

@Mapper
public interface FruitsMapper {
	
    public User findOne(Integer id);

    public List<User> find();

    public void insertOne(User u);

    public void updateOne(@Param("id") Integer id,
    		@Param("name") String name, @Param("price") Integer price);

    public void deleteOne(Integer id);
}
