package com.example.basicmaponline;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.mlab.android.basicoverlays.PostgreSQL;
import com.mlab.android.basicoverlays.SQLloja;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Pesquisar extends Activity{

	RadioGroup radioGroup;
	RadioButton radioButton;
	ImageButton buttonSearch;
	EditText editText;
	
	ProgressDialog progressDialog;
	Boolean pesquisa;
	
	String[] lista;
	ArrayList<SQLloja> listaLoja;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesquisa);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		editText = (EditText) findViewById(R.id.pesquisaEditText);
		radioGroup= (RadioGroup) findViewById(R.id.pesquisaRadioGroup);
		buttonSearch = (ImageButton) findViewById(R.id.pesquisaSearchButton);
	 
		buttonSearch.setOnClickListener(new OnClickListener() {
	 
			@Override
			public void onClick(View v) {
				
				if(editText.getText().toString().compareTo("") == 0){
					Builder builder=new AlertDialog.Builder(Pesquisar.this);
					builder.setIcon(R.drawable.lojacidadaosmall);
					builder.setMessage("Insere texto" );
					builder.setNegativeButton("OK", null);
					builder.show();
				}
				else{
					int selectedId = radioGroup.getCheckedRadioButtonId();
					radioButton = (RadioButton) findViewById(selectedId);
					
					try {
						if(radioButton.getText().toString().compareTo("Nome")==0){
							String valueEditText = editText.getText().toString();
							String[] params = {valueEditText, "Nome" };
							
							listaLoja = new loadDatabase().execute(params).get();
							//new loadDatabase().execute(params).get();
							pesquisa = true;
							
							if(listaLoja.size() == 0){
								Builder builder=new AlertDialog.Builder(Pesquisar.this);
								builder.setIcon(R.drawable.lojacidadaosmall);
								builder.setMessage("Nao existem resultados para esta procura" );
								builder.setNegativeButton("OK", null);
								builder.show();
							}
							
							else{
								Intent lojasIntent = new Intent("com.example.basicmaponline.LOJAS");
								lojasIntent.putExtra("listaLoja", listaLoja);
								lojasIntent.putExtra("pesquisaActivity", pesquisa);
								startActivity(lojasIntent);
							}
						}else if(radioButton.getText().toString().compareTo("Morada")==0){
							String valueEditText = editText.getText().toString();
							String[] params = {valueEditText, "Morada" };
							
							listaLoja = new loadDatabase().execute(params).get();
							pesquisa = true;
							
							if(listaLoja.size() == 0){
								Builder builder=new AlertDialog.Builder(Pesquisar.this);
								builder.setIcon(R.drawable.lojacidadaosmall);
								builder.setMessage("Nao existem resultados para esta procura" );
								builder.setNegativeButton("OK", null);
								builder.show();
							}
							else{
							Intent lojasIntent = new Intent("com.example.basicmaponline.LOJAS");
							lojasIntent.putExtra("listaLoja", listaLoja);
							lojasIntent.putExtra("pesquisaActivity", pesquisa);
							startActivity(lojasIntent);
							}
						}else if(radioButton.getText().toString().compareTo("Concelho")==0){
							String valueEditText = editText.getText().toString();
							String[] params = {valueEditText, "Concelho" };
							
							listaLoja = new loadDatabase().execute(params).get();
							pesquisa = true;
							
							if(listaLoja.size() == 0){
								Builder builder=new AlertDialog.Builder(Pesquisar.this);
								builder.setIcon(R.drawable.lojacidadaosmall);
								builder.setMessage("Nao existem resultados para esta procura" );
								builder.setNegativeButton("OK", null);
								builder.show();
							}
							else{
								Intent lojasIntent = new Intent("com.example.basicmaponline.LOJAS");
								lojasIntent.putExtra("listaLoja", listaLoja);
								lojasIntent.putExtra("pesquisaActivity", pesquisa);
								startActivity(lojasIntent);
							}
						}else if(radioButton.getText().toString().compareTo("Entidade  (Sigla)")==0){
							String valueEditText = editText.getText().toString();
							String[] params = {valueEditText, "Entidade" };
							
							listaLoja = new loadDatabase().execute(params).get();
							pesquisa = true;
							
							if(listaLoja.size() == 0){
								Builder builder=new AlertDialog.Builder(Pesquisar.this);
								builder.setIcon(R.drawable.lojacidadaosmall);
								builder.setMessage("Nao existem resultados para esta procura" );
								builder.setNegativeButton("OK", null);
								builder.show();
							}
							else{
							Intent lojasIntent = new Intent("com.example.basicmaponline.LOJAS");
							lojasIntent.putExtra("listaLoja", listaLoja);
							lojasIntent.putExtra("pesquisaActivity", pesquisa);
							startActivity(lojasIntent);
							}
							
						}else {
							String valueEditText = editText.getText().toString();
							String[] params = {valueEditText, "Servico" };
							
							listaLoja = new loadDatabase().execute(params).get();
							pesquisa = true;
							
							if(listaLoja.size() == 0){
								Builder builder=new AlertDialog.Builder(Pesquisar.this);
								builder.setIcon(R.drawable.lojacidadaosmall);
								builder.setMessage("Nao existem resultados para esta procura" );
								builder.setNegativeButton("OK", null);
								builder.show();
							}
							else{
								Intent lojasIntent = new Intent("com.example.basicmaponline.LOJAS");
								lojasIntent.putExtra("listaLoja", listaLoja);
								lojasIntent.putExtra("pesquisaActivity", pesquisa);
								startActivity(lojasIntent);
							}
						}
						
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ExecutionException e) {		
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}
	 
		});
		
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
	
	public class loadDatabase extends AsyncTask<String, Void, ArrayList<SQLloja>>{

		@Override
	    protected void onPreExecute()
	    {
			progressDialog = new ProgressDialog(Pesquisar.this); 
	        progressDialog.setTitle("Processando...");
	        progressDialog.setMessage("Por favor, espera...");
	        progressDialog.setCancelable(true);
	        progressDialog.show();             
	    }; 
		
		@Override
		protected ArrayList<SQLloja> doInBackground(String... params) {	
		
			ArrayList<SQLloja> listaLojas = new ArrayList<SQLloja>();
			
			try {
				PostgreSQL pSQL = new PostgreSQL();
				
				if(params[1] == "Nome"){
					listaLojas = pSQL.getLojasPesquisa(pSQL.getNomePesquisa(params[0]));
				}else if(params[1] == "Morada"){
					listaLojas = pSQL.getLojasPesquisa(pSQL.getMoradaPesquisa(params[0]));
				}else if(params[1] == "Concelho"){
					listaLojas = pSQL.getLojasPesquisa(pSQL.getConcelhoPesquisa(params[0]));
				}else if(params[1] == "Entidade (Sigla)"){
					listaLojas = pSQL.getLojasPesquisa(pSQL.getEntidadeSiglaPesquisa(params[0]));
				}else{
					listaLojas = pSQL.getLojasPesquisa(pSQL.getServicoPesquisa(params[0]));
				}
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
		}
		
	}

}
