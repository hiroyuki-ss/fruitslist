package com.example.demo.view.page;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class RegisterFruitsControllerPage {
	
	@FindBy(id = "registerButton")
	private SelenideElement registerButton;

	@FindBy(id = "id")
	private SelenideElement id;

	@FindBy(id = "name")
	private SelenideElement name;

	@FindBy(id = "price")
	private SelenideElement price;

	public String title() {
		return Selenide.title();
	}
	
	public RegisterFruitsControllerPage idは(String Id) {
		id.setValue(Id);
		return page(RegisterFruitsControllerPage.class);
	}
	
	public RegisterFruitsControllerPage nameは(String Name) {
		name.setValue(Name);
		return page(RegisterFruitsControllerPage.class);
	}
	
	public RegisterFruitsControllerPage priceは(String Price) {
		price.setValue(Price);
		return page(RegisterFruitsControllerPage.class);
	}
}
