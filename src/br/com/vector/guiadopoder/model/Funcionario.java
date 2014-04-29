package br.com.vector.guiadopoder.model;

import java.io.Serializable;
import java.util.List;


public class Funcionario implements Serializable{


	private static final long serialVersionUID = 4141997924306219470L;

	private int id;
	
	private String nome;
	
	private String aniversario;
	
	private String email;
	
	private String fax;
	
	private List<String> telefones;
	
	private String poder;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAniversario() {
		return aniversario;
	}

	public void setAniversario(String aniversario) {
		this.aniversario = aniversario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public String getPoder() {
		return poder;
	}

	public void setPoder(String poder) {
		this.poder = poder;
	}
	
	
	
}
