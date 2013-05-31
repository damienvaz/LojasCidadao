package com.mlab.android.basicoverlays;

import java.io.Serializable;

public class SQLloja implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String codigo_postal;
	private String distrito;
	private String concelho;
	private double latitude;
	private double longitude;
	private String telefone;
	private Boolean estado;
	private String morada;
	
	public SQLloja(int id, String nome, String codigo_postal, String distrito,String concelho, double latitude, double longitude,String telefone, Boolean estado, String morada) {
		this.id = id;
		this.nome = nome;
		this.codigo_postal = codigo_postal;
		this.distrito = distrito;
		this.concelho = concelho;
		this.latitude = latitude;
		this.longitude = longitude;
		this.telefone = telefone;
		this.estado = estado;
		this.morada = morada;
	}
	
	public SQLloja(SQLloja loja){
		this.id = loja.getId();
		this.nome = loja.getNome();
		this.codigo_postal = loja.getCodigo_postal();
		this.distrito = loja.getDistrito();
		this.concelho = loja.getConselho();
		this.latitude = loja.getLatitude();
		this.longitude = loja.getLongitude();
		this.telefone = loja.getTelefone();
		this.estado = loja.getEstado();
		this.morada = loja.getMorada();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo_postal() {
		return this.codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getDistrito() {
		return this.distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getConselho() {
		return this.concelho;
	}

	public void setConselho(String concelho) {
		this.concelho = concelho;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getMorada() {
		return this.morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}
	
	public SQLloja clone(){
		return new SQLloja(this);
	}
	
}
