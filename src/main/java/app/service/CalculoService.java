package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Entrada;
import app.entity.Saida;
import app.repository.CalculoRepository;

@Service
public class CalculoService {
	
	@Autowired
	private CalculoRepository calculoRepository;

	public Saida calcular(Entrada entrada) {
		
		Saida saida = new Saida();
	    saida.setSoma(this.somar(entrada.getLista())); 
		saida.setMaiorNumero(this.buscarMaiorNumero(entrada.getLista()));
		
		//saida=this.calculoRepository.save(saida);
		
		return saida;
	
	}
	
	   public List<Saida> findAll() {
	    	return this.calculoRepository.findAll();
	    }
	   
	   
	   public Saida findById(long id) {
	    	return this.calculoRepository.findById(id).get();
	    }
	   
	   
	   
	   

	
	public int somar(List<Integer> lista) {
		int soma =0;
	//	if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
			//	if (lista.get(i) != null) {
					soma += lista.get(i);
				//}
				
			//}
		}		
		return soma;
	}
	
	private Integer buscarMaiorNumero(List<Integer> lista) {
		int maiorNumero =0;
		if (lista != null && !lista.isEmpty()) {
			maiorNumero = lista.get(0);
			for (int i = 1; i < lista.size(); i++) {
				if (lista.get(i) > maiorNumero) {
					maiorNumero = lista.get(i);
				}
			}
		}
		return maiorNumero;
	}
	


 
	
	
}
