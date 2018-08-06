package br.edu.unicesumar.aulajava2;

import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Exercicio {

//	public String diretorioArquivo;
	
	// 1) Usando Java 7, faça um método que leia este arquivo e no final da
	// execução, escreva quantas linhas totais há no arquivo.
	public Integer exercicioUm(String diretorioArquivo) throws Exception{
		existeArquivo(diretorioArquivo);
		
		Path pathArquivo = Paths.get(diretorioArquivo);		
		Integer quantidadeLinhas = 0;
		
		try {
			
			Scanner scanner = new Scanner(pathArquivo);
			
			while(scanner.hasNextLine()){
				scanner.nextLine();				
				quantidadeLinhas++;
			}
			scanner.close();
			System.out.println("Quantidade de linhas: " + quantidadeLinhas);
			return quantidadeLinhas;
			
		} catch (Exception ex) {
			throw new Exception("Não foi possível efetuar a operação!");
		}
	}
	
	//2) Faça um método que copie o arquivo de uma pasta para outra, 
	//	como se fosse um backup, antes de começar a leitura
	public Boolean exercicioDois(String pathArquivo, String pathArquivoDestino) throws Exception{
		
		existeArquivo(pathArquivo);
		
		Path origem = Paths.get(pathArquivo);
		Path destino= Paths.get(pathArquivoDestino);
		
		CopyOption[] options = new CopyOption[] {StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES};
		
		try {	
			Files.copy(origem, destino, options);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return true;
	}
	
	//3) Leia o arquivo e escreva um novo arquivo, somente com os registros de pessoas nascidas em 1974
	public List<String> exercicioTres(String pathArquivo, String anoFiltro) throws Exception{
		
		existeArquivo(pathArquivo);		
		List<String> newList = new ArrayList<>();
		Path path = Paths.get(pathArquivo);
		
		try {			
			Scanner scanner = new Scanner(path);			
			while(scanner.hasNextLine()){				
				String linha = scanner.nextLine();
				if(linha.contains(anoFiltro)){
					newList.add(linha);
				}						
			}
			scanner.close();		
			newList.forEach(x-> System.out.println(x));
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		return newList;
	}

	//4) Faça um método que exiba somente as pessoas que tem posição “rb”. Usar lambda
	public List<Pessoa> exercicioQuatro(String pathArquivo, String filtro) throws Exception {
		
		existeArquivo(pathArquivo);
		Path path = Paths.get(pathArquivo);
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		pessoas = returnObjectPessoa(path);
		
		pessoas = pessoas.stream().filter(x-> x.position.equals(filtro)).collect(Collectors.toList());		
		pessoas.forEach(x-> System.out.println(x.id + " " + x.firstName + " " + x.lastName + " " + x.birthYear + " " + x.debutYear + " " + x.position));
		
		return pessoas; 
	}
	
	// 5) Faça um método que exiba as pessoas ordenadas pela sua data de nascimento
	// de forma decrescente (primeiro o mais novo). Usar Lambda
	public List<Pessoa> exercicioCinco(String pathArquivo) throws Exception {
		
		existeArquivo(pathArquivo);
		Path path = Paths.get(pathArquivo);
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		pessoas = returnObjectPessoa(path);		
		pessoas.sort(Comparator.comparing(Pessoa::getBirthYear));
		pessoas.forEach(p -> System.out.println(p.getFirstName() + " " + p.getBirthYear()));
		
		return pessoas;
	}
	
	// 6) Faça um método que filtre todos os últimos nome “Adams” e 
	// do resultado deste filtro, exiba o mais novo.
	public List<Pessoa> exercicioSeis(String pathArquivo, String filtro) throws Exception {
		
		existeArquivo(pathArquivo);
		Path path = Paths.get(pathArquivo);
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		pessoas = returnObjectPessoa(path);		
		pessoas = pessoas.stream().filter(x-> x.getLastName().equals(filtro)).collect(Collectors.toList());
		pessoas.sort(Comparator.comparing(Pessoa::getBirthYear));
		pessoas.forEach(p -> System.out.println(p.getFirstName() + ", " + p.getLastName() + ", " + p.getBirthYear()));
		
		return pessoas;		
	}

	private List<Pessoa> returnObjectPessoa(Path path) throws Exception {
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {
		
			Scanner scanner = new Scanner(path);
			
			while(scanner.hasNextLine()){	
				String linha = scanner.nextLine();
				
				if(linha.contains("ID")) // ignora a primeira linha (headers)
					continue;
				
				String[] dados = linha.trim().split(",");
				Integer anoNascimento = Integer.parseInt(dados[4]);
				Integer anoFalescimento = Integer.parseInt(dados[5]);				
				pessoas.add(new Pessoa(dados[0], dados[1], dados[2], dados[3], anoNascimento, anoFalescimento));				
			}
			scanner.close();
		} catch (Exception ex) {
			throw new Exception(ex);
		}

		return pessoas.stream().filter(distinctByKey(b -> b.getId())).collect(Collectors.toList());
	}
	
	private void existeArquivo(String diretorioArquivo) throws Exception {
		File f = new File(diretorioArquivo);
		if(!f.exists() && f.isDirectory()) { 
			throw new Exception("Arquivo não encontrado!");
		}		
	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }



	

	

}
