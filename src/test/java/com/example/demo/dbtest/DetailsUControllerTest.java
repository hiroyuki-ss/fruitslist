package com.example.demo.dbtest;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.UController;

@AutoConfigureMockMvc
@SpringBootTest
public class DetailsUControllerTest {
	
	@Autowired
	private UController target;
	
	@Test //select１件
	void 存在しないidを選択しdetailsメソッドをするとNullPointerExceptionをthrowする() {
		
		assertThrows(NullPointerException.class, () -> target.details(null, null));
	}
}
