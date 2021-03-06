package br.com.wohr.graph.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.wohr.graph.domain.Graph;
import br.com.wohr.graph.services.GraphService;

@RestController
@RequestMapping(value = "/graph")
public class GraphController {
	
	@Autowired
	private GraphService service;
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Graph> find(@PathVariable Integer id) {
        Graph obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Graph>insert(@Validated @RequestBody Graph obj){
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Graph>> findAll() {
        List<Graph> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Graph> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
