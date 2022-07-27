package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioDTO;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatorioService  {

	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");

	private Boolean system = true;

	public RelatorioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao de relatorio voce deseja");
			System.out.println("0 - sair");
			System.out.println("1 - Gerar Relatorio Funcionario");
			System.out.println("2 - Busca funcionario nome, data contratacao e maior salario");
			System.out.println("3 - Busca maior data contratacao");
			System.out.println("4 - Busca funcionario nome e salario");

			Integer action = scanner.nextInt();

			switch (action) {
			case 1:
				this.listar(scanner);
				break;
			case 2:
				this.listarPorNomeDataSalario(scanner);
				break;
			case 3:
				this.listarPorMaiorDataContratacao(scanner);
				break;
			case 4:
				this.listarFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}

		}
	}

	private void listarFuncionarioSalario() {
		List<FuncionarioDTO> funcionarios = funcionarioRepository.listarFuncionarioSalario();
		funcionarios.forEach(System.out::println);
	}

	public void listar(Scanner scanner) {
		System.out.println("Qual nome deseja listar?");
		String nome = scanner.next();
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		funcionarios.forEach(System.out::println);
	}
	
	public void listarPorMaiorDataContratacao(Scanner scanner) {
		System.out.println("Qual data deseja listar?");
		String data = scanner.next();
		LocalDate dataFormatada = LocalDate.parse(data, formatter);
		List<Funcionario> funcionarios = funcionarioRepository.listarMaiorDataContratacao(dataFormatada);
		funcionarios.forEach(System.out::println);
	}
	
	public void listarPorNomeDataSalario(Scanner scanner) {
		System.out.println("Qual nome deseja listar?");
		String nome = scanner.next();
		System.out.println("Qual data deseja listar?");
		String data = scanner.next();
		LocalDate dataFormatada = LocalDate.parse(data, formatter);
		System.out.println("Qual saalrio deseja listar?");
		BigDecimal salario = scanner.nextBigDecimal();
		List<Funcionario> funcionarios = funcionarioRepository.listarPorNomeDataContratacaoMaiorSalario(nome, dataFormatada, salario);
		funcionarios.forEach(System.out::println);
	}

}
