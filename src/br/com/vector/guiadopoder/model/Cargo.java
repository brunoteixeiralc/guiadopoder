package br.com.vector.guiadopoder.model;

import java.io.Serializable;
import java.util.List;


public class Cargo implements Serializable{

	private static final long serialVersionUID = 4011667352070275133L;

	private int id;
	
	private String nome;
	
	private Poder poder;
	
	private List<Funcionario> funcionarios;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Poder getPoder() {
		return poder;
	}

	public void setPoder(Poder poder) {
		this.poder = poder;
	}

	
	
}
