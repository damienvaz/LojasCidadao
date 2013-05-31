package com.example.basicmaponline;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mlab.android.basicoverlays.PostgreSQL;
import com.mlab.android.basicoverlays.SQLservico;

public class InfoServico extends Activity implements View.OnClickListener{

	Integer id_servico;
	SQLservico servico;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.infoservico);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		id_servico = (Integer) intent.getSerializableExtra("id_servico");
		
		Integer id[] = new Integer[1];
		id[0] = id_servico;
		
		try{
			servico = new loadDatabase().execute(id).get(); 
		}catch (InterruptedException e1) {
				// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e) {		
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Typeface type = Typeface.createFromAsset(getAssets(),"anonymous.ttf"); 
		
		TextView infoServicoNome = (TextView) findViewById(R.id.infoServicoNome);
		infoServicoNome.setTypeface(type);
		infoServicoNome.setText(servico.getNome_servico());
		
		TextView infoServicoDescricao = (TextView) findViewById(R.id.infoServicoDescricao);
		infoServicoDescricao.setTypeface(type);
		infoServicoDescricao.setText(servico.getDescricao_servico());
		
		TextView infoServicoTipo = (TextView) findViewById(R.id.infoServicoTipo);
		infoServicoTipo.setTypeface(type);
		infoServicoTipo.setText(servico.getTipo_servico());
		
		TextView infoServicoUrl  = (TextView) findViewById(R.id.infoServicoUrl);
		infoServicoUrl.setTypeface(type); 
		infoServicoUrl.setText(servico.getUrl_servico());
		infoServicoUrl.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.infoServicoUrl:
				String url = servico.getUrl_servico();
				if (!url.startsWith("https://") && !url.startsWith("http://")){
				    url = "http://" + url;
				}
				Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(urlIntent);
				break;
			
		default:
			break;
		}
		
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        //getMenuInflater().inflate(R.menu.activity_main, menu);
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
	
	public class loadDatabase extends AsyncTask<Integer, Void, SQLservico>{

		@Override
		protected SQLservico doInBackground(Integer... params) {	
		
			SQLservico servico = null;
		
			try {
				PostgreSQL pSQL = new PostgreSQL();
				//listaBalcoes = pSQL.getBalcoesByOrder(pSQL.getBalcoes(loja.getId()));
				servico = pSQL.getServico(pSQL.getServicoQuery(id_servico));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return servico;
			
		}
		
		@Override
		protected void onPostExecute(SQLservico servco){
			servico = servco;
		}
		
	}

}
