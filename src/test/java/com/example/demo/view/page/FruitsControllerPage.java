package com.example.demo.view.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class FruitsControllerPage {
	
	private static final String URL = "http://localhost:8080/fruits";
	
	
	@FindBy(id = "registerButton")
	private SelenideElement registerButton;
	
	@FindBy(id = "detailsButton")
	private SelenideElement detailsButton;
	
	@FindBy(id = "changeButton")
	private SelenideElement changeButton;
	
	@FindBy(id = "deleteButton")
	private SelenideElement deleteButton;
	
	public static FruitsControllerPage open() {
		return Selenide.open(URL, FruitsControllerPage.class);
	}
	
	public String title() {
		return Selenide.title();
	}
	
	public RegisterFruitsControllerPage 新規フルーツ登録画面へ遷移する() {
		registerButton.click();
		return page(RegisterFruitsControllerPage.class); 
	}
	
	public DetailsFruitsControllerPage フルーツ詳細画面へ遷移する() {
		detailsButton.click();
		return page(DetailsFruitsControllerPage.class); 
	}
	
	public ChangeFruitsControllerPage フルーツ変更画面へ遷移する() {
		changeButton.click();
		return page(ChangeFruitsControllerPage.class); 
	}
	
	public FruitsControllerPage 削除する(int id) {
		Selenide.$(By.id("deleteButton")).click();
		return page(FruitsControllerPage.class); 
	}
	
	public SelenideElement 新規登録変更削除後の画面() {
		return $(By.cssSelector("tbody"));
	}
	public int 新規登録変更削除後の要素数() {
		return $(By.cssSelector("tbody")).findElements
				(By.tagName("tr")).size();
	}
}

