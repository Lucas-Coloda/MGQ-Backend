package br.com.opet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {
	@GetMapping(path = "histories/add")
	public String add() {
		return "add";
	}	

	@GetMapping(path = "histories/edit")
	public String edit() {
		return "gedit";
	}	

	@GetMapping(path = "histories/delete")
	public String delete() {
		return "delete";
	}
	
	@GetMapping(path = "histories/details")
	public String details() {
		return "details";
	}

	@GetMapping(path = "histories/list")
	public String list() {
		return "list";
	}	
}
