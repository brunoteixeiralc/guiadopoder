package br.com.vector.guiadopoder.model;

import java.io.Serializable;
import java.util.List;


public class Cargos implements Serializable{

	private static final long serialVersionUID = 4011667352070275133L;

	private int id;
	
	private String cargo;
	
	private List<Funcionario> funcionarios;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
}
