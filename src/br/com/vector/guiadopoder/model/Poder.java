package br.com.vector.guiadopoder.model;

import java.io.Serializable;
import java.util.List;


public class Poder implements Serializable  {

	private static final long serialVersionUID = 6873806464023624622L;

	private int id;
	
	private String nome;
	
	private String cor;
	
	private List<Setor> setores;

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	
}
