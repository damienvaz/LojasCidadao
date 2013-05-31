package com.example.basicmaponline;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mlab.android.basicoverlays.PostgreSQL;
import com.mlab.android.basicoverlays.SQLrelacaoBalcaoServico;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Servicos extends ListActivity{

	ArrayList<SQLrelacaoBalcaoServico> listaServico;
	String lista[];
	Integer id_entidade;
	Integer id_loja;
	
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		
		//Action Bar is the bar on the top of each activity !
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		id_entidade = (Integer) intent.getSerializableExtra("id_entidade");
		id_loja = (Integer) intent.getSerializableExtra("id_loja");
		
		Integer id[] = new Integer[2];
		id[0] = id_loja;
		id[1] = id_entidade;
		
		if(listaServico == null){
			new loadDatabase().execute(id);
		}
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Intent ourIntent = new Intent("com.example.basicmaponline.INFOSERVICO");
		ourIntent.putExtra("id_servico", listaServico.get(position).getId_servico());
		startActivity(ourIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.homeactionbar, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.lojasHome){
			Intent intent = new Intent("com.example.basicmaponline.MENU");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
		}
		else {
			super.onBackPressed();
		}
		return true;
	}

	public class loadDatabase extends AsyncTask<Integer, Void, ArrayList<SQLrelacaoBalcaoServico>>{

		@Override
	    protected void onPreExecute()
	    {
			progressDialog = new ProgressDialog(Servicos.this); 
	        progressDialog.setTitle("Processando...");
	        progressDialog.setMessage("Por favor, espera...");
	        progressDialog.setCancelable(true);
	        progressDialog.show();             
	    }; 
		
		@Override
		protected ArrayList<SQLrelacaoBalcaoServico> doInBackground(Integer... id) {	
		
			ArrayList<SQLrelacaoBalcaoServico> listaLojas = new ArrayList<SQLrelacaoBalcaoServico>();
		
			try {
				PostgreSQL pSQL = new PostgreSQL();
				listaLojas = pSQL.getServicos(pSQL.getServicosQuery(id[0],id[1]));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return listaLojas;
			
		}
		
		@Override
		protected void onPostExecute(ArrayList<SQLrelacaoBalcaoServico> listaServicos){
			listaServico = listaServicos;
			
			if(listaServico.size() == 0){
				progressDialog.dismiss();
				Builder builder=new AlertDialog.Builder(Servicos.this);
				builder.setIcon(R.drawable.lojacidadaosmall);
				builder.setMessage("Nao existem serviços neste balcão." );
				builder.setNegativeButton("OK", null);
				builder.show();
			}
			else{
			lista = new String[listaServico.size()];
			int i = 0;
			for(SQLrelacaoBalcaoServico balcao : listaServico){
				String servico = balcao.getNome_servico();
				String tipo = balcao.getTipoServico();

				servico = servico.concat(" - ");
				servico = servico.concat(tipo);
				
				
				lista[i] = servico ;
				i++;
			}
			
			setListAdapter(new ArrayAdapter<String>(Servicos.this, android.R.layout.simple_list_item_1,lista));
			progressDialog.dismiss();
			}
		}
		
	}
	
}
