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
import com.example.demo.service.UService;
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
/*@DbUnitConfiguration
 * DbUnitの設定を行うアノテーション
 * 「dataSetLoder」プロパティへ、 CsvDataSetLoaderを指定することで、
 * CSVファイルの読み込みが可能となる
 * 
 *@TestExecutionListeners
 * ・DependencyInjectionTestExecutionListener
 * DIをテストコードでも利用する場合に指定
 * テスト対象クラスをAutowired等でDIコンテナからインジェクションすることが可能
 * 
 * ・TransactionalTestExecutionListener
 * DB操作の際のトランザクションを設定する際に指定
 * DBを扱うテストで使用
 * 
 */
public class DbCrudTest {
	
	@Autowired
	private UService service;
	//When-Given-Then  振る舞いー振る舞いを実行する前の状態ー振る舞いの結果
	//作業単位ーテスト条件ー想定行動
	@Test
	@DatabaseSetup(value = "/testData/init-data/")
	void initData内のCSVファイルのデータを読み取り_Idを引数として1件取得_変数expectedの値と結果が期待通りか()
				throws Exception {
		User expected = User.builder()//lombokのBuilderを使用
				.id(2)
				.name("test2")
				.price(150)
				.build();
		User actual = service.getUserOne(2);//IDを引数に使用
		assertThat(actual, is(expected));
		//assertEquals(expected, actual);
		//expected=予想、期待値　actual=実測値
		//assertEquals(期待値, 実測値);
		//assertThat(実測値, is(期待値));
		
		
		/*・＠DatabaseSetup
		 * データベースの「初期状態」を定義するためのアノテーション
		 * valueプロパティにCSVファイルが配置されているディレクトリを指定して、
		 * CSVファイルをものにデータベースのテーブルに値を詰め込んでくれる
		 * 
		 */
	}

	@Test
    @DatabaseSetup(value = "/testData/init-data/")
    void initData内のCSVファイルのデータを読み取り_全件取得した変数fruitsの要素数が4つであるか()
    		throws Exception {
        List<User> fruits = service.getList();
        //int expected = 4;
        //int actual = fruits.size();
        assertThat(fruits.size(), is(4));//要素数が4であるかのテスト
        //assertEquals(expected, actual);
    }
	
	@Test
	@DatabaseSetup(value = "/testData/init-data/")
	@ExpectedDatabase(value = "/testData/after-create-data/",
		table = "fruits", assertionMode = DatabaseAssertionMode
			.NON_STRICT_UNORDERED)
	void initData内のCSVファイルのデータを読み取り_変数newUserに入っている値をinsertして_afterCreateData内のCSVファイルの中身と同じか検証する()
				throws Exception {
		User newUser = User.builder()
				.id(5)
				.name("test5")
				.price(230)
				.build();
		service.insertOne(newUser);
		
		/*init-data内のcsvファイルのデータを読み取り、
		 * メソッド内の処理を実行し、ExpectedDatabaseで指定したディレクトリ内の
		 * csvファイルと同じ中身になっているか検証
		 * 
		 * ・@ExpectedDatabase
		 * DatabaseSetupアノテーションと同時に用いられる
		 * テストメソッドの実行後のデータベースの状態を検証する
		 * 
		 * assertionMode属性に、以下の値が設定が可能
		 * DEFAULT（または指定なし）：全てのテーブルとカラムの一致を比較する
		 * NON_STRICT：期待結果データファイルに存在しないテーブル、
		 * カラムが実際のデータベースに存在しても無視する
		 * UNORDERED：NON_STRICTモードに加え、行の順序についても無視する
		 */
	}
	
	//insertテストとほぼ同様
	@Test
	@DatabaseSetup(value = "/testData/init-data/")
	@ExpectedDatabase(value = "/testData/after-update-data/",
		table = "fruits", assertionMode = DatabaseAssertionMode
			.NON_STRICT_UNORDERED)
	void initData内のCSVファイルのデータを読み取り_Idが4のレコードのnameがtest4からupdateに変更され_afterUpdateData内のCSVファイルの中身と同じか検証する()
				throws Exception {
		service.updateOne(4, "update", 200);
	}
	
	//insertテストとほぼ同様
	@Test
	@DatabaseSetup(value = "/testData/init-data/")
	@ExpectedDatabase(value = "/testData/after-delete-data/",
		table = "fruits", assertionMode = DatabaseAssertionMode
			.NON_STRICT_UNORDERED)
	void initData内のCSVファイルのデータを読み取り_Idを引数として_Idが4のレコードが削除され_afterDeleteData内のCSVファイルの中身と同じか検証する()
			throws Exception {
		//List<User> fruits = service.getList();	
		service.deleteOne(4);
	}
}