package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.User;

@Mapper
public interface UMapper {
	
	//select１件
    public User findOne(Integer id);

    //select全件
    public List<User> find();

    //insert
    public void insertOne(User u);

    //update
    public void updateOne(@Param("id") Integer id,
    		@Param("name") String name, @Param("price") Integer price);

    //delete
    public void deleteOne(User u);
}
