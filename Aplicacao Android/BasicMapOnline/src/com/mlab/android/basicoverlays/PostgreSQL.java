package com.mlab.android.basicoverlays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PostgreSQL {
	private Connection conn;
	private String host;
	private String database;
	private String user;
	private String password;
	
	public PostgreSQL () throws SQLException, ClassNotFoundException{
		this.host = "webgis.di.uminho.pt";
		this.database = "lojas";
		this.user = "g05";
		this.password = "20130423";
	}
	
	
	public String getHost() {
		return this.host;
	}

	public String getDatabase() {
		return this.database;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public Connection getConnection(){
		return this.conn;
	}
	
	public String getLojasCidadao() throws SQLException{ 
		return queryLojasCidadaoOrderByName;
	} 
	
	public ArrayList<SQLloja> getLojasCidadaoByOrder() throws NumberFormatException, SQLException, ClassNotFoundException{
		this.conn = PostgreSQL.setConnection(this.host,this.database,this.user,this.password);
		
		ArrayList<SQLloja> listaLojas = new ArrayList<SQLloja>();
		String sql = PostgreSQL.queryLojasCidadaoOrderByName;
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()){
			int lcId = Integer.parseInt(rs.getString(1));
			String lcNome=rs.getString(2);
			String lcCP = rs.getString(3);
			double lcAltitude = Double.parseDouble(rs.getString(6));
			double lcLongitude = Double.parseDouble(rs.getString(7));
			String lcTelefone = rs.getString(8);
			boolean lcEstado = Boolean.parseBoolean(rs.getString(9));
			String lcRua = rs.getString(10);
			String lcDistrito = rs.getString(11);
			String lcConcelho = rs.getString(12);
			
			SQLloja loja = new SQLloja(lcId,lcNome,lcCP,lcDistrito,lcConcelho,lcAltitude,lcLongitude,lcTelefone,lcEstado,lcRua);
    		
			listaLojas.add(loja.clone());
			
		}
		rs.close();
		st.close();
		
		return listaLojas;
	}
	
	public ArrayList<String> getBalcoesByOrder(String sql) throws NumberFormatException, SQLException, ClassNotFoundException{
		this.conn = PostgreSQL.setConnection(this.host,this.database,this.user,this.password);
		
		ArrayList<String> listaBalcoes = new ArrayList<String>();
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()){
			
			String bNome = rs.getString(4);
			
			listaBalcoes.add(bNome);
			
			}
		rs.close();
		st.close();
		
		return listaBalcoes;
	}
	
	public String getBalcoes(int i){
		return "SELECT b.id_loja_cidadao,b.id_entidade,b.estado_balcao,e.nome_entidade" 
				+" FROM public.balcoes as b, public.entidades as e" 
				+" WHERE b.id_loja_cidadao = " + i
				+" AND b.id_entidade = e.id_entidade AND b.estado_balcao = 'true'"
				+" ORDER BY e.nome_entidade";
	}
	
	public String getNomeLojasCidadao(){
		return "SELECT nome_loja_cidadao"
				+    "FROM public.lojas_cidadao";
	}
	
	public static final Connection setConnection(String host,String database,String user,String password) throws SQLException,ClassNotFoundException{
		Class.forName ("org.postgresql.Driver") ;
		String url ;
		url = "jdbc:postgresql://"+host+":5432/"+database+"?sslfactory=org.postgresql.ssl.NonValidatingFactory"+"&ssl=true";
		Connection conn = DriverManager.getConnection(url,user,password) ;
		return conn;
	}
	
	public static final String queryLojasCidadao = "SELECT * FROM public.lojas_cidadao ";
	public static final String queryLojasCidadaoOrderByName = "SELECT l.id_loja_cidacao, l.nome_loja_cidadao, l.codigo_postal_loja_cidadao," 
																+" l.id_distrito_loja_cidadao, l.id_conselho_loja_cidadao, l.latitude_loja_cidadao," 
																+" l.longitude_loja_cidadao, l.telefone_loja_cidadao, l.estado_loja_cidadao," 
																+" l.morada_loja_cidadao,c.nome_conselho,d.nome_distrito"
																+" FROM public.lojas_cidadao as l,public.concelhos as c,public.distritos as d"
																+" WHERE estado_loja_cidadao = 'true' and l.id_conselho_loja_cidadao = c.id_conselho and"
																+" l.id_distrito_loja_cidadao = c.id_distrito and l.id_distrito_loja_cidadao = d.id_distrito"
																+" ORDER BY nome_loja_cidadao";
	public static final String queryBalcoesOrderByName = "SELECT b.id_loja_cidadao,b.id_entidade,b.estado_balcao,e.nome_entidade" 
														+" FROM public.balcoes as b, public.entidades as e" 
														+" WHERE b.id_loja_cidadao = "
														+" AND b.id_entidade = e.id_entidade AND b.estado_balcao = 'true'"
														+" ORDER BY e.nome_entidade";
}
