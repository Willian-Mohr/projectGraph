package br.com.wohr.graph.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wohr.graph.domain.Graph;

@Repository
public interface GraphRepository extends JpaRepository<Graph, Integer> {

}
