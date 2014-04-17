package br.com.vector.guiadopoder.model;

import java.io.Serializable;
import java.util.List;


public class Area implements Serializable{


	private static final long serialVersionUID = 1296953622910841253L;

	private int id;
	
	private String nome;
	
	private String endereco;
	
	private String endWeb;
	
	private String telefone;

	private List<Cargos> cargos;
	
	private String poder;
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Cargos> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargos> cargos) {
		this.cargos = cargos;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndWeb() {
		return endWeb;
	}

	public void setEndWeb(String endWeb) {
		this.endWeb = endWeb;
	}

	public String getPoder() {
		return poder;
	}

	public void setPoder(String poder) {
		this.poder = poder;
	}
		

	
	
	
}
