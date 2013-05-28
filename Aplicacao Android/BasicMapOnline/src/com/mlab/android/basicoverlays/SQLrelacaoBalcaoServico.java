 package com.mlab.android.basicoverlays;

public class SQLrelacaoBalcaoServico {

	private Integer id_loja_cidadao;
	private Integer id_entidade;
	private Integer id_servico;
	private Boolean estado_servico_balcao;
	private String nome_servico;
	private String tipo_servico;
	
	public SQLrelacaoBalcaoServico( Integer id_loja_cidadao, Integer id_entidade,Integer id_servico, Boolean estado_servico_balcao, 
			String nome_servico,String tipo_servico){
		this.id_servico = id_servico;
		this.id_entidade = id_entidade;
		this.nome_servico = nome_servico;
		this.id_loja_cidadao = id_loja_cidadao;
		this.estado_servico_balcao = estado_servico_balcao;
		this.tipo_servico = tipo_servico;
	}
	
	public SQLrelacaoBalcaoServico(SQLrelacaoBalcaoServico servico){
		this.id_servico = servico.getId_servico();
		this.id_entidade = servico.getId_entidade();
		this.nome_servico = servico.getNome_servico();
		this.id_loja_cidadao = servico.getId_loja_cidadao();
		this.estado_servico_balcao = servico.getEstado_servico_balcao();
		this.tipo_servico = servico.getTipoServico();
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

	public Integer getId_servico() {
		return this.id_servico;
	}

	public void setId_servico(Integer id_servico) {
		this.id_servico = id_servico;
	}

	public Boolean getEstado_servico_balcao() {
		return this.estado_servico_balcao;
	}

	public void setEstado_servico_balcao(Boolean estado_servico_balcao) {
		this.estado_servico_balcao = estado_servico_balcao;
	}

	public String getNome_servico() {
		return this.nome_servico;
	}

	public void setNome_servico(String nome_servico) {
		this.nome_servico = nome_servico;
	}
	
	public String getTipoServico(){
		return this.tipo_servico;
	}

	public SQLrelacaoBalcaoServico clone(){
		return new SQLrelacaoBalcaoServico(this);
	}
	
	
}
