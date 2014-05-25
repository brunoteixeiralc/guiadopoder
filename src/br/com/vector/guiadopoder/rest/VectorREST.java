package br.com.vector.guiadopoder.rest;

import java.util.ArrayList;
import java.util.List;

import br.com.vector.guiadopoder.model.Poder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class VectorREST {

	public List<Poder> getListaVector() throws Exception {

		String URL_WS = "http://54.207.91.46:8080/Vector/REST/";
		
	     String[] resposta = new WebServiceVector().get(URL_WS + "poder");
	     
	     if (resposta[0].equals("200")) {
	         Gson gson = new Gson();
	         ArrayList<Poder> listaCliente = new ArrayList<Poder>();
	         JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
	         
	        for (int i = 0; i < array.size(); i++) {
	             listaCliente.add(gson.fromJson(array.get(i), Poder.class));
	         }
	         return listaCliente;
	     } else {
	         throw new Exception(resposta[1]);
	     }
	    }
}
