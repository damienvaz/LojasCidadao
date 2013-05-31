package com.example.basicmaponline;

import java.sql.SQLException;
import java.util.ArrayList;

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

import com.mlab.android.basicoverlays.PostgreSQL;
import com.mlab.android.basicoverlays.SQLbalcao;
import com.mlab.android.basicoverlays.SQLloja;

public class Balcoes extends ListActivity{

	ArrayList<SQLbalcao> listaBalcao;
	String[] lista;
	SQLloja loja;

	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//Action Bar is the bar on the top of each activity !
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);	
		Intent intent = getIntent();
		loja = (SQLloja) intent.getSerializableExtra("loja");
		
		
		
		if(listaBalcao == null){
			new loadDatabase().execute();
		}
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent ourIntent = new Intent("com.example.basicmaponline.ENTIDADE");
		ourIntent.putExtra("id_entidade", listaBalcao.get(position).getId_entidade());
		ourIntent.putExtra("id_loja", listaBalcao.get(position).getId_loja_cidadao());
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
	
	public class loadDatabase extends AsyncTask<Void, Void, ArrayList<SQLbalcao>>{
		
		@Override
	    protected void onPreExecute()
	    {
			progressDialog = new ProgressDialog(Balcoes.this); 
	        progressDialog.setTitle("Processando...");
	        progressDialog.setMessage("Por favor, espera...");
	        progressDialog.setCancelable(true);
	        progressDialog.show();             
	    }; 
		
		@Override
		protected ArrayList<SQLbalcao> doInBackground(Void... params) {	
			
			ArrayList<SQLbalcao> listaBalcoes = new ArrayList<SQLbalcao>();
		
			try {
				PostgreSQL pSQL = new PostgreSQL();
				listaBalcoes = pSQL.getBalcoesByOrder(pSQL.getBalcoes(loja.getId()));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			return listaBalcoes;
		}

		@Override
		protected void onPostExecute(ArrayList<SQLbalcao> listaBalcoes){
			
			listaBalcao = listaBalcoes;
			
			if(listaBalcao.size() == 0){
				progressDialog.dismiss();
				Builder builder=new AlertDialog.Builder(Balcoes.this);
				builder.setIcon(R.drawable.lojacidadaosmall);
				builder.setMessage("Nao existem balcoes nesta loja." );
				builder.setNegativeButton("OK", null);
				builder.show();
			}
			
			lista = new String[listaBalcao.size()];
			int i = 0;
			for(SQLbalcao balcao : listaBalcao){
				lista[i++] = balcao.getNome_entidade();
			}
			
			setListAdapter(new ArrayAdapter<String>(Balcoes.this, android.R.layout.simple_list_item_1,lista));
			
			progressDialog.dismiss();
		}
		
	}

}
