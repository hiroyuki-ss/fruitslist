package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.FruitsService;


@RestController
@RequestMapping("/fruits/api")
public class FruitsRestApiController {

	@Autowired
	private FruitsService service;
	
	@GetMapping
	
	public List<User> seleteAll() {
		return service.getList();
	}
	
	@GetMapping("/details/{id}")
	
	public User seleteOne(@PathVariable Integer id) {
		return service.getUserOne(id);
	}
}