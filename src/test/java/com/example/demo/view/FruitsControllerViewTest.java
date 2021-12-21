package com.example.demo.view;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeborne.selenide.Configuration;
import com.example.demo.view.page.FruitsControllerPage;
import com.example.demo.view.page.RegisterFruitsControllerPage;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
public class FruitsControllerViewTest {
	
	private FruitsControllerPage page;
	
	@BeforeClass
	public static void setUp() {
		Configuration.holdBrowserOpen = false;
	}
	
	@Before
	public void setUpTest() {
		page = FruitsControllerPage.open();
	}
	
	@Test
	public void トップ画面から新規フルーツ登録画面へ遷移できる() throws Exception {
		RegisterFruitsControllerPage actual = page.新規フルーツ登録画面へ遷移する();
		assertThat(actual.title()).isEqualTo("新規フルーツ登録");
	}
}
