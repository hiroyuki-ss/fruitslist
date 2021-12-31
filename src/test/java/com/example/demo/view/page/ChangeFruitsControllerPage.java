package com.example.demo.view.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class ChangeFruitsControllerPage {
	
	@FindBy(id = "backButton")
	private SelenideElement backButton;
	
	@FindBy(id = "changeButton")
	private SelenideElement changeButton;

	@FindBy(id = "id")
	private SelenideElement id;

	@FindBy(id = "name")
	private SelenideElement name;

	@FindBy(id = "price")
	private SelenideElement price;

	public String title() {
		return Selenide.title();
	}
	
	public ChangeFruitsControllerPage idは(String Id) {
		id.setValue(Id);
		return page(ChangeFruitsControllerPage.class);
	}
	
	public ChangeFruitsControllerPage nameは(String Name) {
		name.setValue(Name);
		return page(ChangeFruitsControllerPage.class);
	}
	
	public ChangeFruitsControllerPage priceは(String Price) {
		price.setValue(Price);
		return page(ChangeFruitsControllerPage.class);
	}
	
	public FruitsControllerPage トップ画面へ戻る() {
		backButton.click();
		return page(FruitsControllerPage.class); 
	}
	
	public FruitsControllerPage 変更する() {
		changeButton.click();
		return page(FruitsControllerPage.class);
	}
}
