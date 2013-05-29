package com.example.basicmaponline;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.mlab.android.basicoverlays.SQLloja;




public class Lojas extends ListActivity{

	ProgressDialog progressDialog;
	String[] lista;
	ArrayList<SQLloja> listaLoja;
	Boolean pesquisaActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		listaLoja = (ArrayList<SQLloja>) intent.getSerializableExtra("listaLoja");
		pesquisaActivity = (Boolean) intent.getSerializableExtra("pesquisaActivity");
		//loja =  (SQLloja) intent.getSerializableExtra("loja");
		
		//Action Bar is the bar on the top of each activity !
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		if(pesquisaActivity == null){
			//listaLoja = new loadDatabase().execute().get();
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

	
	/*É Preciso preencher isto , nao esquecer  para fazer interacçao com a proxima Activity!!!*/
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Intent ourIntent = new Intent("com.example.basicmaponline.INFOLOJA");
		ourIntent.putExtra("loja", listaLoja.get(position));
		startActivity(ourIntent);
	}

	public class loadDatabase extends AsyncTask<Void, Void, ArrayList<SQLloja>>{
		//int numRows;
		
		@Override
	    protected void onPreExecute()
	    {
			progressDialog = new ProgressDialog(Lojas.this); 
	        progressDialog.setTitle("Processando...");
	        progressDialog.setMessage("Por favor,espera...");
	        //progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	        //progressDialog.setMax(100);
	        //progressDialog.setIndeterminate(false);
	        progressDialog.setCancelable(true);
	        progressDialog.show();             
	    }; 
		
		@Override
		protected ArrayList<SQLloja> doInBackground(Void... params) {	
		
			ArrayList<SQLloja> listaLojas = new ArrayList<SQLloja>();
		
			try {
				PostgreSQL pSQL = new PostgreSQL();
				listaLojas = pSQL.getLojasCidadaoByOrder();
				/*Connection connectionDatabase = pSQL.setConnection(pSQL.getHost(),pSQL.getDatabase(),pSQL.getUser(),pSQL.getPassword());
				
				
				//listaLojas1 = new ArrayList<SQLloja>();
				String sql = PostgreSQL.queryLojasCidadaoOrderByName;
				Statement st = connectionDatabase.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = st.executeQuery(sql);
				
				// Go to the last row 
				//rs.last(); 
				//numRows = rs.getRow();
				//Log.d("ROW",String.valueOf(numRows));
				// Reset row before iterating to get data 
				//rs.beforeFirst();
				//int i=0;
				//long row=0;
				
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

					//publishProgress((int) (i*100/numRows));
					//i++; 
				}
				rs.close();
				st.close();*/
				//i=0;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			
			return listaLojas;
			
		}
		
		
		
		/*@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			Log.d("ROWNUMBER",String.valueOf(values[0]));
			progressDialog.incrementProgressBy(values[0]);
			
		}*/

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