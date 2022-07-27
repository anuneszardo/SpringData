package br.com.alura.spring.data.service;

import java.util.Scanner;

public interface CrudEntidades {

	void inicial(Scanner scanner);
	void salvar(Scanner scanner);
	void atualizar(Scanner scanner);
	void remover(Scanner scanner);
	void listar(Scanner scanner);

}
