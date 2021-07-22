package br.com.wohr.graph.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.wohr.graph.domain.Data;
import br.com.wohr.graph.repositories.DataRepository;
import br.com.wohr.graph.services.exceptions.DataIntegrityException;
import br.com.wohr.graph.services.exceptions.ObjectNotFoundException;

@Service
public class DataService {

	@Autowired
	private DataRepository dataRepository;

	public Data findById(Integer id) {
		Optional<Data> obj = dataRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Data.class.getName()));
	}

	public List<Data> findAll() {
		return dataRepository.findAll();
	}

//	public Data insert(Data obj) {
//		obj.setId(null);
//		dataRepository.save(obj);
//		return obj;
//	}

	public void delete(Integer id) {
		findById(id);
		try {
			dataRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma data que contenha graph");
        }
	}

	public Data update(Data obj) {
		Data newObj = findById(obj.getId());
		updateData(newObj, obj);
		return dataRepository.save(newObj);
	}

	private void updateData(Data newObj, Data obj) {
		newObj.setSource(obj.getSource());
		newObj.setTarget(obj.getTarget());
		newObj.setDistance(obj.getDistance());
	}
}
