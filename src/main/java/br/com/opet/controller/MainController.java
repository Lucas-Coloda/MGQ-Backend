package br.com.opet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/")
	public String home () {
		return "home.html";
	}

	@RequestMapping("/**")
	@ResponseBody
	public String notFound () {
		return "404";
	}

}
