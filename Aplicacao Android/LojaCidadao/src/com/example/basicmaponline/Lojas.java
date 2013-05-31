package com.example.basicmaponline;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.mlab.android.basicoverlays.SQLloja;




public class Lojas extends ListActivity{

	ProgressDialog progressDialog;
	String[] lista;
	ArrayList<SQLloja> listaLoja;
	Boolean pesquisaActivity;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		listaLoja = (ArrayList<SQLloja>) intent.getSerializableExtra("listaLoja");
		pesquisaActivity = (Boolean) intent.getSerializableExtra("pesquisaActivity");
		
		//Action Bar is the bar on the top of each activity !
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		if(pesquisaActivity == null){
			new loadDatabase().execute();
		}
		else{
		
		lista = new String[listaLoja.size()];
		int i = 0;
		for(SQLloja loja : listaLoja){
			lista[i++] = loja.getNome();
		}
		
		setListAdapter(new ArrayAdapter<String>(Lojas.this, android.R.layout.simple_list_item_1,lista));
		}
	}
	
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.homeactionbar, menu);
	    return true;
	}




	/*Serve para meter o botao de retorno na ActionBar ! */
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

	
	/*� Preciso preencher isto , nao esquecer  para fazer interac�ao com a proxima Activity!!!*/
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Intent ourIntent = new Intent("com.example.basicmaponline.INFOLOJA");
		ourIntent.putExtra("loja", listaLoja.get(position));
		startActivity(ourIntent);
	}

	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager  = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null;
	}

	public boolean hasActiveInternetConnection(Context context) {
	    if (isNetworkAvailable()) {
	        try {
	            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
	            urlc.setRequestProperty("User-Agent", "Test");
	            urlc.setRequestProperty("Connection", "close");
	            urlc.setConnectTimeout(1500); 
	            urlc.connect();
	            return (urlc.getResponseCode() == 200);
	        } catch (IOException e) {
	            Log.e("LOG_TAG", "Error checking internet connection", e);
	        }
	    } else {
	        Log.d("LOG_TAG", "No network available!");
	    }
	    return false;
	}
	
	
	
	
	
	public class loadDatabase extends AsyncTask<Void, Void, ArrayList<SQLloja>>{
		
		@Override
	    protected void onPreExecute()
	    {
			progressDialog = new ProgressDialog(Lojas.this); 
	        progressDialog.setTitle("Processando...");
	        progressDialog.setMessage("Por favor, espera...");
	        progressDialog.setCancelable(true);
	        progressDialog.show();             
	    }; 
		
		@Override
		protected ArrayList<SQLloja> doInBackground(Void... params) {	
		
			ArrayList<SQLloja> listaLojas = new ArrayList<SQLloja>();
		
			try {
				PostgreSQL pSQL = new PostgreSQL();
				listaLojas = pSQL.getLojasCidadaoByOrder();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			
			return listaLojas;
			
		}

		@Override
		protected void onPostExecute(ArrayList<SQLloja> listaLojas){
			listaLoja = listaLojas;
			
			progressDialog.dismiss();
			
			lista = new String[listaLoja.size()];
			int i = 0;
			for(SQLloja loja : listaLoja){
				lista[i++] = loja.getNome();
			}
			
			setListAdapter(new ArrayAdapter<String>(Lojas.this, android.R.layout.simple_list_item_1,lista));
			
		}
		
	}
	
	
}