package com.mlab.android.basicoverlays;

public class SQLservico {
	private String nome_servico;
	private String descricao_servico;
	private String tipo_servico;
	private String url_servico;
	
	public SQLservico(String nome_servico,String descricao_servico,String tipo_servico,String url_servico){
		this.nome_servico = nome_servico;
		this.descricao_servico = descricao_servico;
		this.tipo_servico = tipo_servico;
		this.url_servico = url_servico;
	}
	
	public SQLservico(SQLservico servico){
		this.nome_servico = servico.getNome_servico();
		this.descricao_servico = servico.getDescricao_servico();
		this.tipo_servico = servico.getTipo_servico();
		this.url_servico = servico.getUrl_servico();
	}

	public String getNome_servico() {
		return this.nome_servico;
	}

	public void setNome_servico(String nome_servico) {
		this.nome_servico = nome_servico;
	}

	public String getDescricao_servico() {
		return this.descricao_servico;
	}

	public void setDescricao_servico(String descricao_servico) {
		this.descricao_servico = descricao_servico;
	}

	public String getTipo_servico() {
		return this.tipo_servico;
	}

	public void setTipo_servico(String tipo_servico) {
		this.tipo_servico = tipo_servico;
	}

	public String getUrl_servico() {
		return this.url_servico;
	}

	public void setUrl_servico(String url_servico) {
		this.url_servico = url_servico;
	}
	
	public SQLservico clone(){
		return new SQLservico(this);
	}
	
}
