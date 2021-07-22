package br.com.wohr.graph.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.wohr.graph.domain.Data;
import br.com.wohr.graph.domain.Graph;
import br.com.wohr.graph.services.DataService;
import br.com.wohr.graph.services.GraphService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;

@WebMvcTest
public class GraphControllerTest {

	@Autowired
	private GraphController graphController;

	@MockBean
	private GraphService graphService;
	
	@MockBean
	private DataService dataService;
		
	@BeforeEach
	public void setup() {
		standaloneSetup(this.graphController);
	}

	@Test
	public void deveRetornarSucesso_QuandoBuscarGraph() {
				
		when(this.graphService.findById(1)).thenReturn(createNewGraph());
		
		MockMvcResponse response = 
				given()
				.when()
					.get("/graph/{id}", 1)
				.then()
					.statusCode(HttpStatus.OK.value())
				.extract()
					.response();	
		
		assertEquals(response.getStatusCode(), 200, "O status deveria ser 200");
		
	}
	
	@Test
	public void deveRetornarVazioNoResponseBody_QuandoBuscarGraph() {
			
		when(this.graphService.findById(1)).thenReturn(null);
		
		MockMvcResponse response = 
				given()
					.accept(ContentType.JSON)
				.when()
					.get("/graph/{id}", 1);
		
		assertEquals(response.getBody().asString(), "", "O response body deveria ser vazio!");
		
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarTodosGraph() {
			
		List<Graph> graphs = new ArrayList<Graph>();
		graphs.add(createNewGraph());
		graphs.add(createNewGraph());
		graphs.add(createNewGraph());
		graphs.add(createNewGraph());
		
		when(this.graphService.findAll()).thenReturn(graphs);
		
		MockMvcResponse response = 
				given()
				.when()
					.get("/graph")
				.then()
					.statusCode(HttpStatus.OK.value())
				.extract()
					.response();
							
		String[] result=  response.jsonPath().getString("id").split("\\s+");
				
		assertEquals(result.length, 4, "A quantidade de registro para graph deveria ser 4!");
		
	}
	
	
	private Graph createNewGraph() {
				
		List<Data> datas = new ArrayList<Data>();
		
		Data d1 = new Data(null, "A", "B", 6, null);
		Data d2 = new Data(null, "A", "E", 4, null);
		Data d3 = new Data(null, "B", "A", 6, null);
		Data d4 = new Data(null, "B", "C", 2, null);
		Data d5 = new Data(null, "B", "D", 4, null);
		Data d6 = new Data(null, "C", "B", 3, null);
		Data d7 = new Data(null, "C", "D", 1, null);
		Data d8 = new Data(null, "C", "E", 7, null);
		Data d9 = new Data(null, "E", "B", 5, null);

		datas.addAll(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
		
		Graph graph = new Graph(null, datas);
		
		return graph;
	}

}
