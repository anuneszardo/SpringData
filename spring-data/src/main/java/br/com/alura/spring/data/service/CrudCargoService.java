package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService implements CrudEntidades {

	private final CargoRepository repository;

	private Boolean system = true;

	public CrudCargoService(CargoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao de cargo voce deseja");
			System.out.println("0 - sair");
			System.out.println("1 - salvar Cargo");
			System.out.println("2 - atualizar Cargo");
			System.out.println("3 - listar Cargos");
			System.out.println("4 - deletar Cargo");

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
		System.out.println("Descricao do cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		repository.save(cargo);
		System.out.println("Cargo salvo");
	}

	@Override
	public void atualizar(Scanner scanner) {
		System.out.println("Id do cargo que quer atualizar");
		Integer id = scanner.nextInt();
		System.out.println("Descricao do cargo");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		repository.save(cargo);
		System.out.println("Cargo salvo");
	}

	@Override
	public void remover(Scanner scanner) {
		System.out.println("Id do cargo que quer remover");
		Integer id = scanner.nextInt();
		repository.deleteById(id);
		System.out.println("Cargo deletado");
	}

	@Override
	public void listar(Scanner scanner) {
		Iterable<Cargo> cargos = repository.findAll();
		cargos.forEach(c -> System.out.println(c));
	}

}
