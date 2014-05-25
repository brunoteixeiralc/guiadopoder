package br.com.vector.guiadopoder.model;

import java.io.Serializable;
import java.util.List;


public class Orgao implements Serializable{

	private static final long serialVersionUID = 2124067153972294076L;

	private int id;

	private String nome;
	
	private String endereco;
	
	private String site;
	
	private String telefone;
	
	private Poder poder;

	private List<Cargo> cargos;

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getSite() {
		return site;
	}

	public String getTelefone() {
		return telefone;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Poder getPoder() {
		return poder;
	}

	public void setPoder(Poder poder) {
		this.poder = poder;
	}
}
