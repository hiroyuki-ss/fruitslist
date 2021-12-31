package com.example.demo.view.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class DetailsFruitsControllerPage {
	
	@FindBy(id = "backButton")
	private SelenideElement backButton;

	@FindBy(id = "id")
	private SelenideElement id;

	@FindBy(id = "name")
	private SelenideElement name;

	@FindBy(id = "price")
	private SelenideElement price;

	public String title() {
		return Selenide.title();
	}
	
	public DetailsFruitsControllerPage idは(String Id) {
		id.setValue(Id);
		return page(DetailsFruitsControllerPage.class);
	}
	
	public DetailsFruitsControllerPage nameは(String Name) {
		name.setValue(Name);
		return page(DetailsFruitsControllerPage.class);
	}
	
	public DetailsFruitsControllerPage priceは(String Price) {
		price.setValue(Price);
		return page(DetailsFruitsControllerPage.class);
	}
	
	public FruitsControllerPage トップ画面へ戻る() {
		backButton.click();
		return page(FruitsControllerPage.class); 
	}
}
