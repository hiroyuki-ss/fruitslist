package com.example.demo.dbtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class UControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getUserList処理でhttpステータス200が返る() throws Exception {
		this.mockMvc.perform(get("/fruits/list"))
			.andDo(print())
			.andExpect(status().isOk());
		
	}
}
