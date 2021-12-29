package com.example.demo.view;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeborne.selenide.Configuration;
import com.example.demo.view.page.DetailsFruitsControllerPage;
import com.example.demo.view.page.FruitsControllerPage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestPropertySource(locations = "classpath:test.properties")
public class DetailsControllerViewTest {
	
	private DetailsFruitsControllerPage page;
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/hiroyuki/Downloads/chromedriver");
		Configuration.holdBrowserOpen = true;
	}
	
	@Before
	public void setUpTest() {
		page = FruitsControllerPage.open().フルーツ詳細画面へ遷移する();
	}
	
	@Test
	public void No1フルーツ詳細画面からトップ画面へ戻る() throws Exception {
		FruitsControllerPage actual = page.トップ画面へ戻る();
		assertThat(actual.title()).isEqualTo("フルーツ一覧");
	}
}
