package br.com.wohr.graph.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.wohr.graph.domain.Data;
import br.com.wohr.graph.services.DataService;

@RestController
@RequestMapping(value = "/data")
public class DataController {

	@Autowired
	private DataService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Data> find(@PathVariable Integer id) {
		Data obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Data>> findAll() {
		List<Data> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Data> insert(@Validated @RequestBody Data obj) {
//		service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).body(obj);
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Data> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Data> update(@RequestBody Data obj, @PathVariable Integer id) {
		obj.setId(id);
		Data data = service.update(obj);
		return ResponseEntity.ok().body(data);
	}
}
