package com.mlab.android.basicoverlays;

public class SQLentidade {
	
	private Integer id_entidade;
	private String nome_entidade;
	private String sigla_entidade;
	private String morada_entidade;
	private String telefone_entidade;
	private String fax_entidade;
	private String email_entidade;
	private String url_entidade;
	private String site_entidade;
	
	public SQLentidade(Integer id_entidade,String nome_entidade,String sigla_entidade,
			String morada_entidade, String telefone_entidade,String fax_entidade,
			String email_entidade,String url_entidade,String site_entidade){
		
		this.id_entidade = id_entidade;
		this.nome_entidade = nome_entidade;
		this.sigla_entidade = sigla_entidade;
		this.morada_entidade = morada_entidade;
		this.telefone_entidade = telefone_entidade;
		this.fax_entidade = fax_entidade;
		this.email_entidade = email_entidade;
		this.url_entidade = url_entidade;
		this.site_entidade = site_entidade;
	}
	
	public SQLentidade(SQLentidade entidade){
		this.id_entidade = entidade.getId_entidade();
		this.nome_entidade = entidade.getNome_entidade();
		this.sigla_entidade = entidade.getSigla_entidade();
		this.morada_entidade = entidade.getMorada_entidade();
		this.telefone_entidade = entidade.getTelefone_entidade();
		this.fax_entidade = entidade.getFax_entidade();
		this.email_entidade = entidade.getEmail_entidade();
		this.url_entidade = entidade.getUrl_entidade();
		this.site_entidade = entidade.getSite_entidade();
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

	public String getSigla_entidade() {
		return this.sigla_entidade;
	}

	public void setSigla_entidade(String sigla_entidade) {
		this.sigla_entidade = sigla_entidade;
	}

	public String getMorada_entidade() {
		return this.morada_entidade;
	}

	public void setMorada_entidade(String morada_entidade) {
		this.morada_entidade = morada_entidade;
	}

	public String getTelefone_entidade() {
		return this.telefone_entidade;
	}

	public void setTelefone_entidade(String telefone_entidade) {
		this.telefone_entidade = telefone_entidade;
	}

	public String getFax_entidade() {
		return this.fax_entidade;
	}

	public void setFax_entidade(String fax_entidade) {
		this.fax_entidade = fax_entidade;
	}

	public String getEmail_entidade() {
		return this.email_entidade;
	}

	public void setEmail_entidade(String email_entidade) {
		this.email_entidade = email_entidade;
	}

	public String getUrl_entidade() {
		return this.url_entidade;
	}

	public void setUrl_entidade(String url_entidade) {
		this.url_entidade = url_entidade;
	}

	public String getSite_entidade() {
		return this.site_entidade;
	}

	public void setSite_entidade(String site_entidade) {
		this.site_entidade = site_entidade;
	}

	public SQLentidade clone(){
		return new SQLentidade(this);
	}
	
	
}
