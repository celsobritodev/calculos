package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.CalculosApplication;
import app.entity.Entrada;
import app.entity.Saida;
import app.repository.CalculoRepository;

@SpringBootTest(classes = CalculosApplication.class)
public class CalculoControllerTest {

	@Autowired
	CalculoController calculoController;
	
	@MockitoBean
	CalculoRepository calculoRepository;
	
	
	@BeforeEach
	void setup() {
		List<Saida> lista = new ArrayList<>();
		lista.add(new Saida(1L,5,5.5,10));
		lista.add(new Saida(2L,5,6.7,10));
		lista.add(new Saida(3L,5,9.4,15));
		
		Saida saida = new Saida(1L,50,5.5,10);
		
		when(calculoRepository.findAll()).thenReturn(lista);
		when(calculoRepository.findById(1L)).thenReturn(Optional.of(saida));
		
	}
	
	
	@Test
	void cenario01() {
		ResponseEntity<List<Saida>> retorno = this.calculoController.findAll();
		assertEquals(3, retorno.getBody().size());
	}

	
	@Test
	void cenario02() {
		List<Integer> lista = new ArrayList<>();
		lista.add(2);
		lista.add(4);
		lista.add(5);
		Entrada entrada = new Entrada();
		entrada.setLista(lista);
		ResponseEntity<Saida> retorno = this.calculoController.calcular(entrada);
		assertEquals(HttpStatus.OK, retorno.getStatusCode());
	}
	
	
	@Test
	void cenario02b() {
		List<Integer> lista = new ArrayList<>();
		lista.add(null);
		lista.add(4);
		lista.add(5);
		Entrada entrada = new Entrada();
		entrada.setLista(lista);
		ResponseEntity<Saida> retorno = this.calculoController.calcular(entrada);
		assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	}
	
	
	
	@Test
	void cenario03() {
		ResponseEntity<List<Saida>> retorno = this.calculoController.findAll();
		assertEquals(HttpStatus.OK, retorno.getStatusCode());
	
	}
	
	@Test
	void cenario04() {
		ResponseEntity<Saida> retorno = this.calculoController.findById(1L);
		assertEquals(HttpStatus.OK, retorno.getStatusCode());
	
	}
	
	
	@Test
	void cenario05() {
		ResponseEntity<Saida> retorno = this.calculoController.findById(1L);
		assertEquals(50, retorno.getBody().getSoma());
	
	}
	

	@Test
	void cenario06() {
		ResponseEntity<Saida> retorno = this.calculoController.findById(-1L);
		assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	
	}
	
	
	

}
