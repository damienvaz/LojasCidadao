package com.mlab.android.basicoverlays;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLlistLoja implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, SQLloja> listaLoja; 
	
	public SQLlistLoja() {
		this.listaLoja = new HashMap<String, SQLloja>();
	}
	
	public SQLlistLoja(HashMap<String, SQLloja> listaLoja){
		this.listaLoja = new HashMap<String, SQLloja>();
		
		for(SQLloja loja: listaLoja.values()){
			listaLoja.put(loja.getNome(), loja.clone());
		}
	}
	
	public SQLlistLoja(SQLlistLoja sqlListaLoja){
		this.listaLoja = new HashMap<String, SQLloja>();
		
		this.listaLoja = sqlListaLoja.getListaLoja();
	}
	
	public SQLlistLoja(SQLloja loja){
		this.listaLoja = new HashMap<String, SQLloja>();
		
		this.listaLoja.put(loja.getNome(),loja.clone());
	}
	
	public HashMap<String, SQLloja> getListaLoja() {
		return this.listaLoja;
	}
	
	public String[] getNomeLojaCidadao(){
		ArrayList<String> nomeLC = new ArrayList<String>();
		
		for(SQLloja loja : this.listaLoja.values()){
			nomeLC.add(loja.getNome());
		}
		
		String[] simpleArray = new String[ nomeLC.size()];
		nomeLC.toArray(simpleArray);
		
		return simpleArray;
	}

	public void setListaLoja(HashMap<String, SQLloja> listaLoja) {
		this.listaLoja = listaLoja;
	}

	public SQLlistLoja clone(){
		return new SQLlistLoja(this);
	}
	
}
