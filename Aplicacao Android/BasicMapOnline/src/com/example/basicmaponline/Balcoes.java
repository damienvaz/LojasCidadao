package com.example.basicmaponline;

import java.sql.SQLException;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
		Log.i("indicator","1");		
		Intent intent = getIntent();
		loja = (SQLloja) intent.getSerializableExtra("loja");
		
		if(loja == null){
			Log.d("BALCOESLOJA","LOJA ESTA NULL");
		}
		else{
			Log.d("BALCOESLOJA","LOJA NAO ESTA NULL !");
		}
		
		if(listaBalcao == null){
			new loadDatabase().execute();
			Log.i("indicator","2");
		}
		
		/*lista = new String[listaBalcao.size()];
		int i = 0;
		for(SQLbalcao balcao : listaBalcao){
			lista[i++] = balcao.getNome_entidade();
		}
		
		setListAdapter(new ArrayAdapter<String>(Balcoes.this, android.R.layout.simple_list_item_1,lista));*/
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Log.i("indicator","3");
		Intent ourIntent = new Intent("com.example.basicmaponline.ENTIDADE");
		ourIntent.putExtra("id_entidade", listaBalcao.get(position).getId_entidade());
		ourIntent.putExtra("id_loja", listaBalcao.get(position).getId_loja_cidadao());
		Log.i("indicator","4");
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
	        progressDialog.setMessage("Por favor,espera...");
	        progressDialog.setCancelable(true);
	        //progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	        //progressDialog.setMax(100);
	        progressDialog.show();             
	    }; 
		
		@Override
		protected ArrayList<SQLbalcao> doInBackground(Void... params) {	
		
			Log.i("indicator","5");
			
			ArrayList<SQLbalcao> listaBalcoes = new ArrayList<SQLbalcao>();
		
			try {
				Log.i("indicator","6");
				PostgreSQL pSQL = new PostgreSQL();
				listaBalcoes = pSQL.getBalcoesByOrder(pSQL.getBalcoes(loja.getId()));
				Log.i("indicator","7");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			Log.i("indicator","8");
			return listaBalcoes;
			
		}

		@Override
		protected void onPostExecute(ArrayList<SQLbalcao> listaBalcoes){
			
			Log.i("indicator","9");
			
			listaBalcao = listaBalcoes;
			
			lista = new String[listaBalcao.size()];
			int i = 0;
			for(SQLbalcao balcao : listaBalcao){
				lista[i++] = balcao.getNome_entidade();
			}
			
			setListAdapter(new ArrayAdapter<String>(Balcoes.this, android.R.layout.simple_list_item_1,lista));
			
			progressDialog.dismiss();
		
			Log.i("indicator","10");
		}
		
	}

}
