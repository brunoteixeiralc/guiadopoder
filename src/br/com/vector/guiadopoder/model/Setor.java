package br.com.vector.guiadopoder.model;

import java.io.Serializable;
import java.util.List;


public class Setor implements Serializable{

	private static final long serialVersionUID = 9147950004434550250L;

	private int id;
	
	private String nome;
	
	private Poder poder;
	
	private List<Orgao> orgaos;

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

	public List<Orgao> getOrgaos() {
		return orgaos;
	}

	public void setOrgaos(List<Orgao> orgaos) {
		this.orgaos = orgaos;
	}

	public Poder getPoder() {
		return poder;
	}

	public void setPoder(Poder poder) {
		this.poder = poder;
	}
	
}
