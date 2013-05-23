package com.example.basicmaponline;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.mlab.android.basicoverlays.PostgreSQL;
import com.mlab.android.basicoverlays.SQLloja;

public class Balcoes extends ListActivity{

	ArrayList<String> listaBalcao;
	String[] lista;
	SQLloja loja;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//Action Bar is the bar on the top of each activity !
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
				
		Intent intent = getIntent();
		loja = (SQLloja) intent.getSerializableExtra("loja");
		
		try{
		listaBalcao = new loadDatabase().execute().get();
		}catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e) {		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lista = new String[listaBalcao.size()];
		int i = 0;
		for(String balcao : listaBalcao){
			lista[i++] = balcao;
		}
		
		setListAdapter(new ArrayAdapter<String>(Balcoes.this, android.R.layout.simple_list_item_1,lista));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onBackPressed();
		
		return true;
	}
	
	public class loadDatabase extends AsyncTask<Void, Void, ArrayList<String>>{

		@Override
		protected ArrayList<String> doInBackground(Void... params) {	
		
			ArrayList<String> listaBalcoes = new ArrayList<String>();
		
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
		protected void onPostExecute(ArrayList<String> listaBalcoes){
			listaBalcao = listaBalcoes;
		}
		
	}

}
