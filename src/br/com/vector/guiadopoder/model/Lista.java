package br.com.vector.guiadopoder.model;

import java.io.Serializable;


public class Lista implements Serializable{

	private static final long serialVersionUID = 997974708985973485L;

	private Integer id;
	
	private String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
