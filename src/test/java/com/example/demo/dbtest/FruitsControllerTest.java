package com.example.demo.dbtest;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc //MockMvc使用するためのアノテーション
@SpringBootTest
public class FruitsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void top処理でhttpステータス200が返る() throws Exception {
		this.mockMvc.perform(get("/fruits"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
    void top処理でviewとしてfruits_topHtmlが渡される() throws Exception {
        this.mockMvc.perform(get("/fruits"))
            .andExpect(view().name("fruits/top"));
    }
	
	
	@Test
	void top処理でmodelのmessageにhelloが渡される() throws Exception {
		this.mockMvc.perform(get("/fruits"))
			.andExpect(model().attribute("message", "hello!"));
	}
	
	@Test
	void top処理でmodelのfruitsへgetListが格納_レコードの一つidが1であるものが存在する()
			throws Exception {
		this.mockMvc.perform(get("/fruits"))
			.andExpect(model().attribute("fruits", hasItem(
					hasProperty("id", is(1)))));
	}
}
