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
import com.example.demo.service.UService;

@Controller
@RequestMapping("/fruits")
public class UController {
	
	@Autowired
	private UService service;
	
	//select全件表示
//    @GetMapping("/list")
//    public String getUserList(Model model) {
//        List<User> userList =  service.getList();
//        model.addAttribute("fruits", userList);
//        //model.addAttribute("message", "hello!");
//        return "fruits/list";
//    }
	
    //top.htmlを表示
    @GetMapping("")
    public String top(Model model, @ModelAttribute User u) {
        model.addAttribute("fruits", service.getList());
        model.addAttribute("message", "hello!");
        return "fruits/top";
    }

    //select１件 詳細画面
    @GetMapping("details/id={id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("fruits", service.getUserOne(id));
        return "fruits/details";
    }

    //新規登録画面へ遷移
    @GetMapping("/register")
    public String registerUser(Model model, @ModelAttribute User u) {
        model.addAttribute("fruits", u);
        return "fruits/register";
    }
    
    //新規登録してtop画面へ遷移
    @PostMapping("/register")
    public String create(@Validated @ModelAttribute User u,
    		BindingResult result) {
        if (result.hasErrors()) {
            return "fruits/register";
        }
        service.insertOne(u);
        return "redirect:/fruits";
    }


    //変更画面へ遷移
    @GetMapping("change/id={id}")
    public String change(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("fruits", service.getUserOne(id));
        return "fruits/change";
    }
    
    //詳細変更をしてtop画面へ遷移（変更はhtml内でreadonlyにして、値段のみ変更可）
    @PostMapping("change/id={id}")
    public String update(@ModelAttribute User u, Model model) {
        service.updateOne(u.getId(), u.getName(), u.getPrice());
        return "redirect:/fruits";
    }


    //削除してtop画面に遷移
    @PostMapping("delete/id={id}")
    public String delete(@PathVariable Integer id) {
        service.deleteOne(id);
        return "redirect:/fruits";
    }

}
