package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorController {
	
	//エラー画面からのトップ画面へ遷移
	@PostMapping("/logout")
	public String postError() {
		return "redirect:/fruits";
	}
}
