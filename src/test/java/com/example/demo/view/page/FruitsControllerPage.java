package com.example.demo.view.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class FruitsControllerPage {
	
	private static final String URL = "http://localhost:8080/";
	
	@FindBy(id = "registerButton")
	private SelenideElement registerButton;
	
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
}

