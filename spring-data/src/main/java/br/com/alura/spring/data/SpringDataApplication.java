package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.service.RelatorioService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatorioService relatorioService;
	
	private Boolean system = true;
	
	//Ao cliar a classe que starta o spring ja atribuimos os valores dos repositories
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}
	
	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService,
			CrudUnidadeTrabalhoService unidadeTrabalhoService,RelatorioService relatorioService) {
		super();
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatorioService = relatorioService;
	}

	//Esse método sobrescrito do CommandLineRunner
	//é pra ser executado apos o boot do Spring
	@Override
	public void run(String... args) throws Exception {
		Scanner scanner  = new Scanner(System.in);
		while(system) {
			System.out.println("1 - CRUD Cargo");
			System.out.println("2 - CRUD Unidade de Trabalho");
			System.out.println("3 - CRUD Funcionario");
			System.out.println("4 - Relatorios");

			Integer action = scanner.nextInt();

			switch (action) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				unidadeTrabalhoService.inicial(scanner);
				break;
			case 3:
				funcionarioService.inicial(scanner);
				break;
			case 4:
				relatorioService.inicial(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}

}
