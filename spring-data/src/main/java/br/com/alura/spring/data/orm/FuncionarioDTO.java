package br.com.alura.spring.data.orm;

import java.math.BigDecimal;

public class FuncionarioDTO {

	private Integer id;
	private String nome;
	private BigDecimal salario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "FuncionarioDTO [id=" + id + ", nome=" + nome + ", salario=" + salario + "]";
	}

}
