package com.example.demo.dbtest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.demo.controller.FruitsController;
import com.example.demo.service.FruitsService;

@AutoConfigureMockMvc
@SpringBootTest
public class DetailsFruitsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FruitsService service;
	
	@Autowired
	private FruitsController target;
	
	@BeforeEach
	public void setUp() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("classpath:templates/");
		viewResolver.setSuffix(".html");

		mockMvc= MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}
	
	@Test //select１件
	void 存在しないidユーザーを取得しようするとエラー画面となる() throws Exception {
		
		this.mockMvc.perform(get("/details", "/details/id={id}")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isNotFound());
	}
}
