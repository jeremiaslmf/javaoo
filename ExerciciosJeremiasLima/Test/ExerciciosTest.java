import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.edu.unicesumar.aulajava2.Exercicio;
import br.edu.unicesumar.aulajava2.Pessoa;


public class ExerciciosTest {

	private String _diretorioArquivo = "arquivoTest.txt";
	private String _diretorioDestino = "arquivoTest_Copia.txt";
	private Exercicio exercicio = new Exercicio();
	
	@Test
	public void exercicioUmTest() throws Exception{
		Integer retorno = exercicio.exercicioUm(_diretorioArquivo);		
		assertTrue(retorno > 0);
		assertTrue(retorno == 10);		
	}
	
	@Test
	public void exercicioDoisTest() throws Exception{		
		assertTrue(exercicio.exercicioDois(_diretorioArquivo, _diretorioDestino));
	}
	
	@Test
	public void exercicioTresTest() throws Exception{		
		String anoFiltro = "1974";
		List<String> retorno = exercicio.exercicioTres(_diretorioArquivo, anoFiltro);
		Long registro = retorno.stream().filter(x -> !x.contains(anoFiltro)).count();
		  
		assertTrue(registro == 0);		
	}
	
	@Test 
	public void exercicioQuatroTest() throws Exception{
		String filtro = "rb";
		List<Pessoa> retorno = exercicio.exercicioQuatro(_diretorioArquivo, filtro);
		Long registro = retorno.stream().filter(x -> !x.position.contains(filtro)).count();
		
		assertTrue(registro == 0);
	}

	@Test 
	public void exercicioCincoTest() throws Exception{
		List<Pessoa> retorno = exercicio.exercicioCinco(_diretorioArquivo);
		Integer resultado1 = retorno.stream().findFirst().get().birthYear;
		Long count = retorno.stream().count();
		Integer resultado2 = retorno.stream().skip(count -1).findAny().get().birthYear;
		
		assertTrue(resultado1 == 1945);
		assertTrue(resultado2 == 1979);		
	}
	
	@Test
	public void exercicioSeisTest() throws Exception {
		String filtro = "Adams";
		List<Pessoa> retorno = exercicio.exercicioSeis(_diretorioArquivo, filtro);
		Long teste1 = retorno.stream().filter(x -> !x.lastName.equals(filtro)).count();
		Long teste2 = retorno.stream().filter(x -> x.lastName.equals(filtro)).count();
		
		assertTrue(teste1 == 0);
		assertTrue(teste2 == 2); // Apenas 2 pessoas possuem sobrenome Adams
	}
}