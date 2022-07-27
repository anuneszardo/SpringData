package br.com.alura.spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioDTO;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
 JpaSpecificationExecutor<Funcionario>{
	
	//Derived Query: Utilizando o termo "findBy" o springdata identifica isso como uma derived query
	List<Funcionario> findByNome(String nome, Pageable pageable);
	
	List<Funcionario> findByNome(String nome);
	
	//JPQL: É análogo a consulta acima, mas nao é derivada de nehum padrão de nomenclatura para o método
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome")
	List<Funcionario> funcionarioPorNome(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.dataContratacao = :dataFormatada AND f.salario >= :salario")
	List<Funcionario> listarPorNomeDataContratacaoMaiorSalario(String nome, LocalDate dataFormatada,
			BigDecimal salario);
	
	//Native query precisa informar e utilizar os campos conforme o banco, o dialeto fica por conta do application.properties
	@Query( value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :dataFormatada",
			nativeQuery = true)
	List<Funcionario> listarMaiorDataContratacao(LocalDate dataFormatada);
	
	//Aqui usamos uma lista de FuncionarioDTO que como class based projection. Tbm pode ser usado uma Interface Based projection
	@Query( value = "SELECT f.id, f.nome, f.salario FROM funcionarios f",
			nativeQuery = true)
	List<FuncionarioDTO> listarFuncionarioSalario();
	
}
