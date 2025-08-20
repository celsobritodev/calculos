package app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.CalculosApplication;
import app.entity.Saida;
import app.repository.CalculoRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = CalculosApplication.class)
public class CalculosServiceTest {
	
	@Autowired
	CalculoService calculoService;
	
	@MockitoBean
	CalculoRepository calculoRepository;
	

	@BeforeEach
	void setup() {
		List<Saida> lista = new ArrayList<>();
		lista.add(new Saida(1L,5,5.5,10));
		lista.add(new Saida(2L,5,6.7,10));
	//	lista.add(new Saida(3L,5,9.4,15));
		
		when(calculoRepository.findAll()).thenReturn(lista);
		
		
	}
	
	
	@Test
	void cenario01() {
		// TESTE DE UNIDADE
		List<Integer> lista = new ArrayList<>();
		lista.add(2);
		lista.add(4);
		lista.add(5);
		
		int retorno = calculoService.somar(lista);
		assertEquals(11, retorno);
	}
	
	
	@Test
	void cenario02() {
		// TESTE DE UNIDADE
		List<Integer> lista = new ArrayList<>();
		lista.add(null);
		lista.add(4);
		lista.add(5);
		
		assertThrows(Exception.class, () -> { calculoService.somar(lista); });
		
	}
	
	
	@Test
	void cenario03() {
		// TESTE DE INTEGRAÇÃO COM MOCKITO
		List<Saida> lista =  this.calculoService.findAll();
		assertEquals(2, lista.size());
		
	}

}
