package com.mlab.android.basicoverlays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.util.Log;


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
	
	public ArrayList<SQLbalcao> getBalcoesByOrder(String sql) throws NumberFormatException, SQLException, ClassNotFoundException{
		this.conn = PostgreSQL.setConnection(this.host,this.database,this.user,this.password);
		
		ArrayList<SQLbalcao> listaBalcoes = new ArrayList<SQLbalcao>();
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()){
			
			Integer bIdLojaCidadao = Integer.parseInt(rs.getString(1));
			Integer bIdEntidade = Integer.parseInt(rs.getString(2));
			String bNome = rs.getString(4);
			
			SQLbalcao balcao = new SQLbalcao(bIdLojaCidadao,bIdEntidade,bNome);
			
			listaBalcoes.add(balcao);
			
			}
		rs.close();
		st.close();
		
		return listaBalcoes;
	}
	
	public SQLentidade getEntidade(String sql) throws NumberFormatException, SQLException, ClassNotFoundException{
		this.conn = PostgreSQL.setConnection(this.host,this.database,this.user,this.password);
		
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		SQLentidade entidade=null;
		
		while(rs.next()){
			Integer id_entidade = Integer.parseInt(rs.getString(1));
			String nome_entidade = rs.getString(2);
			String sigla_entidade = rs.getString(3);
			String morada_entidade = rs.getString(4);
			String telefone_entidade = rs.getString(5);
			String fax_entidade = rs.getString(6);
			String email_entidade = rs.getString(7);
			String url_entidade = rs.getString(8);
			String site_entidade = rs.getString(9);
		
			entidade = new SQLentidade(id_entidade,nome_entidade,sigla_entidade,morada_entidade
					,telefone_entidade,fax_entidade,email_entidade,url_entidade,site_entidade);
			Log.d("ENTIDADE",id_entidade+nome_entidade+sigla_entidade+morada_entidade+telefone_entidade+fax_entidade+email_entidade
					+url_entidade+site_entidade);
		}
		rs.close();
		st.close();
		
		return entidade;
	}
	
	public ArrayList<SQLrelacaoBalcaoServico> getServicos(String sql) throws NumberFormatException, SQLException, ClassNotFoundException{
		this.conn = PostgreSQL.setConnection(this.host,this.database,this.user,this.password);
		
		ArrayList<SQLrelacaoBalcaoServico> listaServicos = new ArrayList<SQLrelacaoBalcaoServico>();
		
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		
		
		while(rs.next()){
			Integer id_loja_cidadao = Integer.parseInt(rs.getString(1));
			Integer id_entidade = Integer.parseInt(rs.getString(2));
			Integer id_servico = Integer.parseInt(rs.getString(3));
			Boolean estado_servico_balcao = Boolean.parseBoolean(rs.getString(4));
			String nome_servico = rs.getString(5);
			String tipo_servico = rs.getString(6);
		
			SQLrelacaoBalcaoServico servico = new SQLrelacaoBalcaoServico(id_loja_cidadao,id_entidade,id_servico,estado_servico_balcao,nome_servico,tipo_servico);
			
			listaServicos.add(servico);
		}
		rs.close();
		st.close();
		
		return listaServicos;
	}
	
	public SQLservico getServico(String sql) throws NumberFormatException, SQLException, ClassNotFoundException{
		this.conn = PostgreSQL.setConnection(this.host,this.database,this.user,this.password);
		
		ArrayList<SQLrelacaoBalcaoServico> listaServicos = new ArrayList<SQLrelacaoBalcaoServico>();
		
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		SQLservico servico = null ;
		
		
		while(rs.next()){
			String nome_servico = rs.getString(3);
			String descricao_servico = rs.getString(4);
			String tipo_servico = rs.getString(5);
			String url_servico = rs.getString(6);
		
			servico = new SQLservico(nome_servico,descricao_servico,tipo_servico,url_servico);
		}
		rs.close();
		st.close();
		
		return servico;
	}
	
	public ArrayList<SQLloja> getLojasPesquisa(String sql) throws NumberFormatException, SQLException, ClassNotFoundException{
		this.conn = PostgreSQL.setConnection(this.host,this.database,this.user,this.password);
		
		ArrayList<SQLloja> listaLojas = new ArrayList<SQLloja>();
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		Log.d("PESQUISALOJA","ANTES DO WHILE DA QUERY");
		
		while(rs.next()){
			Log.d("PESQUISALOJA", "ENTREI NO WHILE!!");
			int lcId = Integer.parseInt(rs.getString(1));
			String lcNome=rs.getString(2);
			String lcCP = rs.getString(3);
			double lcAltitude = Double.parseDouble(rs.getString(6));
			double lcLongitude = Double.parseDouble(rs.getString(7));
			String lcTelefone = rs.getString(8);
			boolean lcEstado = Boolean.parseBoolean(rs.getString(9));
			String lcRua = rs.getString(10);
			String lcConcelho = rs.getString(11);
			String lcDistrito = rs.getString(12);
			
			SQLloja loja = new SQLloja(lcId,lcNome,lcCP,lcDistrito,lcConcelho,lcAltitude,lcLongitude,lcTelefone,lcEstado,lcRua);
    		
			if(loja != null){
				Log.d("PESQUISALOJA",loja.getNome());
			}
			else {
				Log.d("PESQUISALOJA","NAO ESTA A TER A PUTA DA LOJA !");
			}
			listaLojas.add(loja.clone());
			
		}
		rs.close();
		st.close();
		
		Log.d("PESQUISALOJA","DEPOIS DO WHILE DA QUERY");
		
		return listaLojas;
	}
	
	
	public String getEntidadeQuery(int i){
		return "SELECT id_entidade, nome_entidade, sigla_entidade, morada_entidade," 
	       +" telefone_entidade, fax_entidade, email_entidade, url_entidade,site_entidade"
	       +" FROM public.entidades"
	       +" WHERE id_entidade = " + i;
	}
	
	/*public String getServicosQuery(int entidade, int loja){
		return "SELECT id_servico, id_entidade, nome_servico, descricao_servico, tipo_servico, url_servico, estado_servico"
			    +" FROM public.servicos"
			    +" WHERE id_entidade= "+i+ " and estado_servico = 'true'"
			    +" ORDER BY nome_servico";
	}*/
	
	public String getServicoQuery(int id_servico){
		return "SELECT id_servico,id_entidade,nome_servico,descricao_servico,tipo_servico,url_servico,estado_servico "
				+" FROM public.servicos "
				+" WHERE id_servico=" + id_servico;
	}
	
	public String getServicosQuery(int idloja,int identidade){
		return "SELECT r.id_loja_cidadao, r.id_entidade, r.id_servico, r.estado_servico_balcao, s.nome_servico,s.tipo_servico"
			  +" FROM public.relacoes_balcao_servico as r, public.servicos as s"
			  +" WHERE r.id_loja_cidadao ="+idloja+" AND r.id_entidade ="+identidade+" AND r.id_servico = s.id_servico AND r.estado_servico_balcao ='true' ";
	}
	
	public String getBalcoes(int i){
		return "SELECT b.id_loja_cidadao,b.id_entidade,b.estado_balcao,e.nome_entidade" 
				+" FROM public.balcoes as b, public.entidades as e" 
				+" WHERE b.id_loja_cidadao = " + i +" AND b.id_entidade = e.id_entidade AND b.estado_balcao = 'true'"
				+" ORDER BY e.nome_entidade";
	}
	
	
	
	
	
	
	
	/*Query da Classe Pesquisa */
	
	
	
	
	
	
	
	public String getNomePesquisa(String nome){
		/*return "SELECT l.id_loja_cidacao, l.nome_loja_cidadao, l.codigo_postal_loja_cidadao," 
				+" l.id_distrito_loja_cidadao, l.id_conselho_loja_cidadao, l.latitude_loja_cidadao," 
				+" l.longitude_loja_cidadao, l.telefone_loja_cidadao, l.estado_loja_cidadao," 
			    +" l.morada_loja_cidadao"
				+" FROM public.lojas_cidadao as l"
				+" WHERE nome_loja_cidadao "
				+" LIKE '%"+nome+"%' AND estado_loja_cidadao ='true' "
				+" ORDER BY nome_loja_cidadao";*/
		return "SELECT l.id_loja_cidacao, l.nome_loja_cidadao, l.codigo_postal_loja_cidadao," 
				+" l.id_distrito_loja_cidadao, l.id_conselho_loja_cidadao, l.latitude_loja_cidadao," 
				+" l.longitude_loja_cidadao, l.telefone_loja_cidadao, l.estado_loja_cidadao," 
				+" l.morada_loja_cidadao,c.nome_conselho, d.nome_distrito "
				+" FROM public.lojas_cidadao as l, public.concelhos as c, public.distritos as d "
				+" WHERE l.id_distrito_loja_cidadao = c.id_distrito AND l.id_conselho_loja_cidadao = c.id_conselho "
				+" AND l.id_distrito_loja_cidadao = d.id_distrito AND l.nome_loja_cidadao "
				+" LIKE '%"+nome+"%'  AND l.estado_loja_cidadao ='true' "
				+" ORDER BY l.nome_loja_cidadao;";
	}
	
	public String getMoradaPesquisa(String morada){
		/*return "SELECT l.id_loja_cidacao, l.nome_loja_cidadao, l.codigo_postal_loja_cidadao," 
				+" l.id_distrito_loja_cidadao, l.id_conselho_loja_cidadao, l.latitude_loja_cidadao," 
				+" l.longitude_loja_cidadao, l.telefone_loja_cidadao, l.estado_loja_cidadao," 
			    +" l.morada_loja_cidadao"
				+" FROM public.lojas_cidadao as l"
				+" WHERE morada_loja_cidadao LIKE '%" + morada + "%' AND estado_loja_cidadao ='true' "
				+" ORDER BY nome_loja_cidadao";*/
		return "SELECT l.id_loja_cidacao, l.nome_loja_cidadao, l.codigo_postal_loja_cidadao," 
				+" l.id_distrito_loja_cidadao, l.id_conselho_loja_cidadao, l.latitude_loja_cidadao," 
				+" l.longitude_loja_cidadao, l.telefone_loja_cidadao, l.estado_loja_cidadao," 
				+" l.morada_loja_cidadao,c.nome_conselho, d.nome_distrito "
				+" FROM public.lojas_cidadao as l, public.concelhos as c, public.distritos as d "
				+" WHERE l.id_distrito_loja_cidadao = c.id_distrito AND l.id_conselho_loja_cidadao = c.id_conselho "
				+" AND l.id_distrito_loja_cidadao = d.id_distrito AND l.morada_loja_cidadao LIKE '%" + morada + "%' "
				+" AND l.estado_loja_cidadao ='true' "
				+"ORDER BY l.nome_loja_cidadao;";
	}
	
	public String getConcelhoPesquisa(String concelho){
		return "SELECT lc.id_loja_cidacao, lc.nome_loja_cidadao, lc.codigo_postal_loja_cidadao,"
				+" lc.id_distrito_loja_cidadao, lc.id_conselho_loja_cidadao, lc.latitude_loja_cidadao,"
				+" lc.longitude_loja_cidadao, lc.telefone_loja_cidadao, lc.estado_loja_cidadao, lc.morada_loja_cidadao"
				+" FROM (SELECT l.id_loja_cidacao, l.nome_loja_cidadao, l.codigo_postal_loja_cidadao," 
				+" l.id_distrito_loja_cidadao, l.id_conselho_loja_cidadao, l.latitude_loja_cidadao," 
				+" l.longitude_loja_cidadao, l.telefone_loja_cidadao, l.estado_loja_cidadao," 
			    +" l.morada_loja_cidadao,c.nome_conselho FROM public.lojas_cidadao as l, public.concelhos as c "
				+" WHERE l.id_conselho_loja_cidadao = c.id_conselho) as lc"
				+" WHERE lc.nome_conselho LIKE '%"+concelho+"%' AND lc.estado_loja_cidadao ='true' "
				+" ORDER BY lc.nome_loja_cidadao";
	}
	
	public String getEntidadeSiglaPesquisa(String sigla){
		return "SELECT DISTINCT l.id_loja_cidacao, l.nome_loja_cidadao, l.codigo_postal_loja_cidadao," 
				+" l.id_distrito_loja_cidadao, l.id_conselho_loja_cidadao, l.latitude_loja_cidadao," 
				+" l.longitude_loja_cidadao, l.telefone_loja_cidadao, l.estado_loja_cidadao," 
			    +" l.morada_loja_cidadao"
				+" FROM public.lojas_cidadao as l, "
				+" (SELECT b.id_loja_cidadao, b.id_entidade, b.estado_balcao,e.sigla_entidade FROM public.balcoes as b, public.entidades as e WHERE b.id_entidade = e.id_entidade) "
				+" as be "
				+" WHERE l.id_loja_cidacao = be.id_loja_cidadao AND be.sigla_entidade LIKE upper('%"+sigla+"%') "
				+" ORDER BY l.nome_loja_cidadao";
	}
	
	public String getServicoPesquisa(String nome){
		return "SELECT DISTINCT l.id_loja_cidacao, l.nome_loja_cidadao, l.codigo_postal_loja_cidadao," 
				+" l.id_distrito_loja_cidadao, l.id_conselho_loja_cidadao, l.latitude_loja_cidadao," 
				+" l.longitude_loja_cidadao, l.telefone_loja_cidadao, l.estado_loja_cidadao," 
			    +" l.morada_loja_cidadao "
				+" FROM public.lojas_cidadao as l, (SELECT r.*, s.nome_servico, s.tipo_servico "
				+" FROM public.relacoes_balcao_servico as r, public.servicos as s WHERE r.id_servico = s.id_servico) "
				+" as rs WHERE l.id_loja_cidacao = rs.id_loja_cidadao AND rs.nome_servico LIKE '%"+nome+"%' "
				+" ORDER BY l.nome_loja_cidadao";
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
	
	public static final String queryLojasCidadaoOrderByName = "SELECT l.id_loja_cidacao, l.nome_loja_cidadao, l.codigo_postal_loja_cidadao," 
																+" l.id_distrito_loja_cidadao, l.id_conselho_loja_cidadao, l.latitude_loja_cidadao," 
																+" l.longitude_loja_cidadao, l.telefone_loja_cidadao, l.estado_loja_cidadao," 
																+" l.morada_loja_cidadao,c.nome_conselho,d.nome_distrito"
																+" FROM public.lojas_cidadao as l,public.concelhos as c,public.distritos as d"
																+" WHERE estado_loja_cidadao = 'true' and l.id_conselho_loja_cidadao = c.id_conselho and"
																+" l.id_distrito_loja_cidadao = c.id_distrito and l.id_distrito_loja_cidadao = d.id_distrito"
																+" ORDER BY nome_loja_cidadao";
}
