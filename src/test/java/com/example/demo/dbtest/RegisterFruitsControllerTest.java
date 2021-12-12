package com.example.demo.dbtest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.demo.controller.FruitsController;
import com.example.demo.service.FruitsService;
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class RegisterFruitsControllerTest {
	
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
	
	@Test
	void registerUser処理でhttpステータス200が返る() throws Exception {
			this.mockMvc.perform(get("/fruits/register"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	void 新規登録時にidが空の状態で登録処理をするとregister画面に戻る() throws Exception {
		
			this.mockMvc.perform(post("/fruits/register")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "")
			.param("name", "テスト")
			.param("price", "テスト"))
			.andExpect(model().hasErrors()) // 新規登録部分にエラーが存在するか
			.andExpect(model().errorCount(1))//エラー数を検証
			.andExpect(view().name("fruits/register"));
	}
	
	@Test
	void 新規登録時にidがNULLの状態で登録処理をするとregister画面に戻る() throws Exception {
		
			this.mockMvc.perform(post("/fruits/register")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("name", "テスト")
			.param("price", "テスト"))
			.andExpect(model().hasErrors())
			.andExpect(model().attributeErrorCount("user", 1))//エラー数を検証
			.andExpect(view().name("fruits/register"));
	}
	
	@Test
	void 新規登録時にidが空白の状態で登録処理をするとregister画面に戻る() throws Exception {
		
			this.mockMvc.perform(post("/fruits/register")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", " ")
			.param("name", "テスト")
			.param("price", "テスト"))
			.andExpect(model().hasErrors())
			.andExpect(model().attributeErrorCount("user", 1))
			.andExpect(view().name("fruits/register"));
	}
	
	@Test
	void 新規登録時にnameが空の状態で登録処理をするとregister画面に戻る() throws Exception {
		
			this.mockMvc.perform(post("/fruits/register")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "1")
			.param("name", "")
			.param("price", "test"))
			.andExpect(model().hasErrors())
			.andExpect(model().attributeErrorCount("user", 1))
			.andExpect(view().name("fruits/register"));
	}
	
	@Test
	void 新規登録時にnameがNULLの状態で登録処理をするとregister画面に戻る() throws Exception {
		
			this.mockMvc.perform(post("/fruits/register")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "1")
			.param("price", "test"))
			.andExpect(model().hasErrors())
			.andExpect(model().attributeErrorCount("user", 1))
			.andExpect(view().name("fruits/register"));
	}
	
	@Test
	void 新規登録時にnameが空白の状態で登録処理をするとregister画面に戻る() throws Exception {
		
			this.mockMvc.perform(post("/fruits/register")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "1")
			.param("name", " ")
			.param("price", "test"))
			.andExpect(model().hasErrors())
			.andExpect(model().attributeErrorCount("user", 1))
			.andExpect(view().name("fruits/register"));
	}
	
	@Test
	void 新規登録時にnameが21文字以上の状態で登録処理をするとregister画面に戻る() throws Exception {
		
			this.mockMvc.perform(post("/fruits/register")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "1")
			.param("name", "123456789012345678901")
			.param("price", "1"))
			.andExpect(model().hasErrors())
			.andExpect(model().attributeErrorCount("user", 1))
			.andExpect(view().name("fruits/register"));
	}
	
	@Test
	void 新規登録時にidが重複の状態で登録処理をするとregister画面に戻る() throws Exception {
		
			this.mockMvc.perform(post("/fruits/register")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "1")
			.param("name", "test")
			.param("price", "test"))
			.andExpect(model().hasErrors())
			.andExpect(model().attributeErrorCount("user", 1))
			.andExpect(view().name("fruits/register"));
	}
}