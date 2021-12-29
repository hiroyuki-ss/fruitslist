package com.example.demo.view;
import static com.codeborne.selenide.Condition.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeborne.selenide.Configuration;
import com.example.demo.view.page.FruitsControllerPage;
import com.example.demo.view.page.RegisterFruitsControllerPage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestPropertySource(locations = "classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterControllerViewTest {
	
	private RegisterFruitsControllerPage page;
	
	@BeforeClass
	public static void setUp() {
		Configuration.holdBrowserOpen = true;
	}
	
	@Before
	public void setUpTest() {
		page = FruitsControllerPage.open().新規フルーツ登録画面へ遷移する();
	}
	
	@Test
	public void No1新規フルーツ登録画面からトップ画面へ戻る() throws Exception {
		FruitsControllerPage actual = page.トップ画面へ戻る();
		assertThat(actual.title()).isEqualTo("フルーツ一覧");
	}
	
	@Test
	public void No2新規フルーツ登録画面から新規登録できる() throws Exception {
		FruitsControllerPage actual = page
				.idは("5")
				.nameは("mango")
				.priceは("500")
				.新規登録する();
		actual.新規登録変更削除後の画面().shouldBe(visible);
		assertThat(actual.新規登録変更削除後の要素数()).isEqualTo(5);
	}
}
