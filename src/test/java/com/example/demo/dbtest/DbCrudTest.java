package com.example.demo.dbtest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.* ;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.service.FruitsService;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;


@SpringBootTest
@Transactional
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class
})

public class DbCrudTest {
	
	@Autowired
	private FruitsService service;
	
	@Test
	@DatabaseSetup(value = "/testData/init-data/")
	void Idに指定したユーザーが期待通り取得できること() throws Exception {
		User expected = User.builder()//lombokのBuilderを使用
				.id(2)
				.name("test2")
				.price(150)
				.build();
		User actual = service.getUserOne(2);//IDを引数に使用
		assertThat(actual, is(expected));
	}
	
	@Test
    @DatabaseSetup(value = "/testData/init-data/")
    void 登録されているデータがすべて取得できること() throws Exception {
        List<User> fruits = service.getList();
        assertThat(fruits.size(), is(4));//要素数が4であるかのテスト
    }
	
	@Test
	@DatabaseSetup(value = "/testData/init-data/")
	@ExpectedDatabase(value = "/testData/after-create-data/",
		table = "fruits", assertionMode = DatabaseAssertionMode
			.NON_STRICT_UNORDERED)
	void 生成した新規ユーザーが挿入されていること() throws Exception {
		User newUser = User.builder()
				.id(5)
				.name("test5")
				.price(230)
				.build();
		service.insertOne(newUser);
	}
	
	@Test
	@DatabaseSetup(value = "/testData/init-data/")
	@ExpectedDatabase(value = "/testData/after-update-data/",
		table = "fruits", assertionMode = DatabaseAssertionMode
			.NON_STRICT_UNORDERED)
	void Idレコードの内容が更新出来ていること() throws Exception {
		service.updateOne(4, "update", 200);
	}
	
	@Test
	@DatabaseSetup(value = "/testData/init-data/")
	@ExpectedDatabase(value = "/testData/after-delete-data/",
		table = "fruits", assertionMode = DatabaseAssertionMode
			.NON_STRICT_UNORDERED)
	void 選択したIdレコードが削除されていること() throws Exception {
		service.deleteOne(4);
	}
}