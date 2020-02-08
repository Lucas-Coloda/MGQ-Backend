package br.com.opet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.opet.entity.History;
import br.com.opet.repository.HistoryRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/histories")
public class HistoryController {
	@Autowired
	HistoryRepository historyRepository;

	@Autowired
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@GetMapping
	public String list() {
		return gson.toJson(historyRepository.findAll());
	}
	
	@GetMapping("/retrieve/{id}")
	public String retrieve(@PathVariable Long id) {
		return gson.toJson(historyRepository.findById(id));
	}
	
	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public String add(@RequestBody String historyJson) {
		return gson.toJson(historyRepository.save(gson.fromJson(historyJson, History.class)));			
	}

	@PutMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(@RequestBody String historyJson) {
		return gson.toJson(historyRepository.save(gson.fromJson(historyJson, History.class)));			
	}
	// URGENTE: asdasdas
    @DeleteMapping(path = "/delete/{id}")
	public HttpStatus delete(@PathVariable Long id) {
    	if (historyRepository.findById(id).isPresent()) {
    		historyRepository.deleteById(id);    		
    		return HttpStatus.ACCEPTED;
    	}
		return HttpStatus.NO_CONTENT;
	}
}
