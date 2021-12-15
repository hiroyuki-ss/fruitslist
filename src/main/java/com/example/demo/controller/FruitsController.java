package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.FruitsService;

@Controller
@RequestMapping("/fruits")
public class FruitsController {
	
	@Autowired
	private FruitsService service;
	
    //top.htmlを表示
    @GetMapping("")
    public String top(Model model, @ModelAttribute User u) {
        model.addAttribute("fruits", service.getList());
        model.addAttribute("message", "hello!");
        return "fruits/top";
    }

    //select１件 詳細画面
    @GetMapping("/details/{id}")
    public String details(@PathVariable Integer id, Model model) {
    	
        model.addAttribute("fruits", service.getUserOne(id));
        return "fruits/details";
    }

    //新規登録画面へ遷移
    @GetMapping("/register")
    public String registerUser(@ModelAttribute User u, Model model) {
        model.addAttribute("fruits", u);
        return "fruits/register";
    }
    
    //新規登録してtop画面へ遷移
    @PostMapping("/register")
    public String create(@ModelAttribute @Validated User u,
    		BindingResult result, Model model) {
        if (result.hasErrors()) {
            return registerUser(u, model);
        }
        service.insertOne(u);
        return "redirect:/fruits";
    }


    //変更画面へ遷移
    @GetMapping("/change/{id}")
    public String change(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("fruits", service.getUserOne(id));
        return "fruits/change";
    }
    
    //詳細変更をしてtop画面へ遷移（変更はhtml内でreadonlyにして、値段のみ変更可）
    @PostMapping("/change/{id}")
    public String update(@ModelAttribute @Validated User u,
    		BindingResult result, Model model) {
    	if (result.hasErrors()) {
            return "fruits/change";
        }
    	
        service.updateOne(u.getId(), u.getName(), u.getPrice());
        return "redirect:/fruits";
    }

    //削除してtop画面に遷移
    @PostMapping("/delete/{id}")
    
    public String delete(@PathVariable Integer id) {
    	
    	service.deleteOne(id);
        return "redirect:/fruits";
    }
}
