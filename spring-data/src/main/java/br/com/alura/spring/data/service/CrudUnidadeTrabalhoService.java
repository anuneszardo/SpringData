package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService implements CrudEntidades {

	private final UnidadeTrabalhoRepository repository;

	private Boolean system = true;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao de Unidade de trabalho voce deseja");
			System.out.println("0 - sair");
			System.out.println("1 - salvar Unidade de trabalho");
			System.out.println("2 - atualizar Unidade de trabalho");
			System.out.println("3 - listar Unidade de trabalhos");
			System.out.println("4 - deletar Unidade de trabalho");

			Integer action = scanner.nextInt();

			switch (action) {
			case 1:
				this.salvar(scanner);
				break;
			case 2:
				this.atualizar(scanner);
				break;
			case 3:
				this.listar(scanner);
				break;
			case 4:
				this.remover(scanner);
				break;
			default:
				system = false;
				break;
			}

		}
	}

	@Override
	public void salvar(Scanner scanner) {
		System.out.println("Descricao do Unidade de trabalho");
		String descricao = scanner.next();
		System.out.println("Endereco do Unidade de trabalho");
		String endereco = scanner.next();
		UnidadeTrabalho UnidadeTrabalho = new UnidadeTrabalho();
		UnidadeTrabalho.setDescricao(descricao);
		UnidadeTrabalho.setEndereco(endereco);
		repository.save(UnidadeTrabalho);
		System.out.println("Unidade de trabalho salvo");
	}

	@Override
	public void atualizar(Scanner scanner) {
		System.out.println("Id do Unidade de trabalho que quer atualizar");
		Integer id = scanner.nextInt();
		System.out.println("Descricao do Unidade de trabalho");
		String descricao = scanner.next();
		System.out.println("Endereco do Unidade de trabalho");
		String endereco = scanner.next();
		UnidadeTrabalho UnidadeTrabalho = new UnidadeTrabalho();
		UnidadeTrabalho.setId(id);
		UnidadeTrabalho.setDescricao(descricao);
		UnidadeTrabalho.setEndereco(endereco);
		repository.save(UnidadeTrabalho);
		System.out.println("Unidade de trabalho salvo");
	}

	@Override
	public void remover(Scanner scanner) {
		System.out.println("Id do Unidade de trabalho que quer remover");
		Integer id = scanner.nextInt();
		repository.deleteById(id);
		System.out.println("Unidade de trabalho deletado");
	}

	@Override
	public void listar(Scanner scanner) {
		Iterable<UnidadeTrabalho> UnidadeTrabalhos = repository.findAll();
		UnidadeTrabalhos.forEach(c -> System.out.println(c));
	}

}
