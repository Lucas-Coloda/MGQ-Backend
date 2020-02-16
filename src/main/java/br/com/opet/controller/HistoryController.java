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
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.opet.entity.History;
import br.com.opet.repository.HistoryRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class HistoryController {
	@Autowired
	HistoryRepository historyRepository;

	@Autowired
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@GetMapping("/histories")
	public String list() {
		return gson.toJson(historyRepository.findAll());
	}
	
	@GetMapping("/history/{id}")
	public String retrieve(@PathVariable Long id) {
		return gson.toJson(historyRepository.findById(id));
	}
	
	@PostMapping(path = "/history", consumes = "application/json", produces = "application/json")
	public HttpStatus add(@RequestBody String historyJson) {
		historyRepository.save(gson.fromJson(historyJson, History.class));
		return HttpStatus.CREATED;
	}

	@PutMapping(path = "/history", consumes = "application/json", produces = "application/json")
	public HttpStatus edit(@RequestBody String historyJson) {
		History history = gson.fromJson(historyJson, History.class);
		if (historyRepository.findById(history.getId()).isPresent()) {
			historyRepository.save(history);
			return HttpStatus.OK;
    	}
		return HttpStatus.NOT_FOUND;
	}

    @DeleteMapping(path = "/history")
	public HttpStatus delete(@RequestBody String historyJson) {
		long id =  gson.fromJson(historyJson, History.class).getId();
    	if (historyRepository.findById(id).isPresent()) {
    		historyRepository.deleteById(id);    		
    		return HttpStatus.OK;
    	}
		return HttpStatus.NOT_FOUND;
	}
}
