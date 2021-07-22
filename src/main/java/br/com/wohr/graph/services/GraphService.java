package br.com.wohr.graph.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.wohr.graph.domain.Data;
import br.com.wohr.graph.domain.Graph;
import br.com.wohr.graph.repositories.DataRepository;
import br.com.wohr.graph.repositories.GraphRepository;
import br.com.wohr.graph.services.exceptions.DataIntegrityException;
import br.com.wohr.graph.services.exceptions.ObjectNotFoundException;

@Service
public class GraphService {

	@Autowired
	private GraphRepository graphRepository;

	@Autowired
	private DataRepository dataRepository;

	public Graph findById(Integer id) {
		Optional<Graph> obj = graphRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Data.class.getName()));
	}

	public List<Graph> findAll() {
		return graphRepository.findAll();
	}

	public Graph insert(Graph obj) {
		obj.getData().stream().forEach(x -> x.setGraph(obj));
		graphRepository.save(obj);
		dataRepository.saveAll(obj.getData());
		return obj;
	}

	public void delete(Integer id) {
		findById(id);
		try {
			graphRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um graph que contenha data");
        }
	}

}