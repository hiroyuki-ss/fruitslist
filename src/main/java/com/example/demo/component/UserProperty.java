package com.example.demo.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProperty {
	
	@Value("${example.firstProperty}")
	private String propertyOne;
	
	public String getPropertyOne() {
		return propertyOne;
	}
}
