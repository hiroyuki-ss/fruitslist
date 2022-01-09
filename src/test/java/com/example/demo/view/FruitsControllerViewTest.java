package com.example.demo.view;
import static com.codeborne.selenide.Condition.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeborne.selenide.Configuration;
import com.example.demo.component.UserProperty;
import com.example.demo.view.page.ChangeFruitsControllerPage;
import com.example.demo.view.page.DetailsFruitsControllerPage;
import com.example.demo.view.page.FruitsControllerPage;
import com.example.demo.view.page.RegisterFruitsControllerPage;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestPropertySource(locations = "classpath:test.properties")

@ContextConfiguration(classes = UserProperty.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FruitsControllerViewTest {
	
	private FruitsControllerPage page;
	
	@Autowired
	static UserProperty userProperty;

	@BeforeClass
	public static void setUp() {
		String properOne = userProperty.getPropertyOne();
		System.setProperty("webdriver.chrome.driver", properOne);
		Configuration.holdBrowserOpen = true;
	}
	
	@Before
	public void setUpTest() {
		page = FruitsControllerPage.open();
	}
	
	@Test
	public void No1トップ画面から新規フルーツ登録画面へ遷移できる() throws Exception {
		RegisterFruitsControllerPage actual = page.新規フルーツ登録画面へ遷移する();
		assertThat(actual.title()).isEqualTo("新規フルーツ登録");
	}
	
	@Test
	public void No2トップ画面からフルーツ詳細画面へ遷移できる() throws Exception {
		DetailsFruitsControllerPage actual = page.フルーツ詳細画面へ遷移する();
		assertThat(actual.title()).isEqualTo("フルーツ詳細");
	}
	
	@Test
	public void No3トップ画面からフルーツ変更画面へ遷移できる() throws Exception {
		ChangeFruitsControllerPage actual = page.フルーツ変更画面へ遷移する();
		assertThat(actual.title()).isEqualTo("フルーツ変更");
	}
	
	@Test
	public void No4トップ画面から削除できる() throws Exception {
		FruitsControllerPage actual = page.削除する(1);
		actual.新規登録変更削除後の画面().shouldBe(visible);
		assertThat(actual.新規登録変更削除後の要素数()).isEqualTo(3);
	}
	
}
