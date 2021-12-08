package com.example.demo.dbtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.UController;

@AutoConfigureMockMvc
@SpringBootTest
public class DeleteUControllerTest {
	
	@Autowired
	private UController target;
	
	@Test //delete
	void 存在しないidを選択しdeleteOneメソッドをするとNullPointerExceptionをthrowする() {
		
		assertThrows(NullPointerException.class, () -> target.delete(null));
	}
}
