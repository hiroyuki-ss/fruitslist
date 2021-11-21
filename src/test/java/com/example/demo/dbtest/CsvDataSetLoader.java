package com.example.demo.dbtest;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public class CsvDataSetLoader extends AbstractDataSetLoader {
	
	@Override
	protected IDataSet createDataSet(Resource resource) throws Exception {
		return new CsvURLDataSet(resource.getURL());
	}
}

/*・AbstractDataSetLoader
 * 何らかのデータセットを読み込むための抽象クラス
 * 抽象クラスはDataSetLoaderインターフェースの実装クラス、作成するクラスは、
 * 「DataSetLoader」型となる
 * クラスレベルの作成で、「これはデータセットを読み込むためのクラス」となる
 * 
 * ・createDataSet
 * データセットを作成するためのファクトリメソッド
 * 引数として渡されるResources型の「resouce」オブジェクトは、
 * 「実ファイル」へアクセスするための情報・振る舞いを持っている
 * 実際のテストでは、resourceオブジェクトは処理対象のCSVファイルのパスが
 * 格納されている、という形になる
 * 
 * ・CsvURLDataSet
 * resourceオブジェクトもとにCSVの実ファイルを取得し、
 * データセットオブジェクトへ変換することで、DBUnitが処理できるようになる
 */