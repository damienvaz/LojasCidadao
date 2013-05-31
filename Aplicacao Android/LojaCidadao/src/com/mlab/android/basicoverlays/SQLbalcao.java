package com.mlab.android.basicoverlays;

import java.io.Serializable;

public class SQLbalcao implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	private Integer id_loja_cidadao;
	private Integer id_entidade;
	private String nome_entidade;
	
	public SQLbalcao(Integer id_loja_cidadao,Integer id_entidade,String nome_entidade){
		this.id_loja_cidadao = id_loja_cidadao;
		this.id_entidade = id_entidade;
		this.nome_entidade = nome_entidade;
	}
	
	public SQLbalcao(SQLbalcao balcao){
		this.id_loja_cidadao = balcao.getId_loja_cidadao();
		this.id_entidade = balcao.getId_entidade();
		this.nome_entidade = balcao.getNome_entidade();
	}

	public Integer getId_loja_cidadao() {
		return this.id_loja_cidadao;
	}

	public void setId_loja_cidadao(Integer id_loja_cidadao) {
		this.id_loja_cidadao = id_loja_cidadao;
	}

	public Integer getId_entidade() {
		return this.id_entidade;
	}

	public void setId_entidade(Integer id_entidade) {
		this.id_entidade = id_entidade;
	}

	public String getNome_entidade() {
		return this.nome_entidade;
	}

	public void setNome_entidade(String nome_entidade) {
		this.nome_entidade = nome_entidade;
	}
	
	public SQLbalcao clone(){
		return new SQLbalcao(this);
	}
	
	
}
