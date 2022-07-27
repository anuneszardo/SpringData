package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService implements CrudEntidades {

	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	private Boolean system = true;

	@Override
	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao de Funcionario voce deseja");
			System.out.println("0 - sair");
			System.out.println("1 - salvar Funcionario");
			System.out.println("2 - atualizar Funcionario");
			System.out.println("3 - listar Funcionarios");
			System.out.println("4 - deletar Funcionario");

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
		Funcionario Funcionario = new Funcionario();
		System.out.println("Nome do Funcionario");
		String nome = scanner.next();
		Funcionario.setNome(nome);
		System.out.println("cpf do Funcionario");
		String cpf = scanner.next();
		Funcionario.setCpf(cpf);
		System.out.println("salario do Funcionario");
		BigDecimal salario = scanner.nextBigDecimal();
		Funcionario.setSalario(salario);
		Funcionario.setDataContratacao(LocalDate.now());
		Funcionario.setCargo(cargo(scanner));
		Funcionario.setUnidadeTrabalhos(unidades(scanner));
		funcionarioRepository.save(Funcionario);
		System.out.println("Funcionario salvo");
	}

	private Cargo cargo(Scanner scanner) {
		System.out.println("Qual idCargo voce deseja atribuir ao funcionario?");
		int action = scanner.nextInt();
		Optional<Cargo> cargo = cargoRepository.findById(action);
		return cargo.get();
	}

	private List<UnidadeTrabalho> unidades(Scanner scanner) {
		Boolean continuaUnidadeTrabalho = true;
		List<UnidadeTrabalho> retorno = new ArrayList<UnidadeTrabalho>();

		while (continuaUnidadeTrabalho) {

			System.out.println("Qual idUnidade voce deseja atribuir ao funcionario (Digite 0 para parar)");

			int action = scanner.nextInt();
			if (action != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(action);
				retorno.add(unidade.get());
			} else {
				continuaUnidadeTrabalho = false;
			}
		}
		return retorno;

	}

	@Override
	public void atualizar(Scanner scanner) {
		Funcionario Funcionario = new Funcionario();
		System.out.println("Id do Funcionario que quer atualizar");
		Integer id = scanner.nextInt();
		System.out.println("Nome do Funcionario");
		String nome = scanner.next();
		Funcionario.setNome(nome);
		System.out.println("cpf do Funcionario");
		String cpf = scanner.next();
		Funcionario.setCpf(cpf);
		System.out.println("salario do Funcionario");
		BigDecimal salario = scanner.nextBigDecimal();
		Funcionario.setSalario(salario);
		Funcionario.setId(id);
		Funcionario.setCargo(cargo(scanner));
		Funcionario.setUnidadeTrabalhos(unidades(scanner));
		funcionarioRepository.save(Funcionario);
		System.out.println("Funcionario salvo");
	}

	@Override
	public void remover(Scanner scanner) {
		System.out.println("Id do Funcionario que quer remover");
		Integer id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Funcionario deletado");
	}

	@Override
	public void listar(Scanner scanner) {
		System.out.println("Qual página voce deseja consultar");
		Integer page = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "nome"));
		
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios); //qtd de paginas
		System.out.println("Pagina atual " + funcionarios.getNumber()); 
		System.out.println("Quantidade de registros " + funcionarios.getTotalElements());
		funcionarios.forEach(c -> System.out.println(c));
	}

}
