package com.trade.controller;

import static com.trade.util.ViewConstant.MAIN_HOME;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Home")
public class HomeController {

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String mainHome(){
		return "redirect:/trade";
	}
	
}
