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
import android.widget.ImageButton;
import android.widget.TextView;

import com.mlab.android.basicoverlays.PostgreSQL;
import com.mlab.android.basicoverlays.SQLentidade;

public class Entidade extends Activity implements View.OnClickListener{

	SQLentidade entidade;
	Integer id_entidade;
	Integer id_loja;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		id_entidade = (Integer) intent.getSerializableExtra("id_entidade");
		id_loja = (Integer) intent.getSerializableExtra("id_loja");
		
		Integer id[] = new Integer[2];
		id[0] = id_entidade;
		
		
		try{
			entidade = new loadDatabase().execute(id).get(); //id_entidade provavelmente um array 
			
		}catch (InterruptedException e1) {
				// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e) {		
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setContentView(R.layout.entidade);
		
		Typeface type = Typeface.createFromAsset(getAssets(),"anonymous.ttf");
		
		TextView entidadeNome =(TextView) findViewById(R.id.entidadeNome);
		entidadeNome.setTypeface(type);
		entidadeNome.setText(entidade.getNome_entidade());
		
		TextView entidadeSigla = (TextView) findViewById(R.id.entidadeSigla);
		entidadeSigla.setTypeface(type);
		entidadeSigla.setText(entidade.getSigla_entidade());
		
		TextView entidadeMorada = (TextView) findViewById(R.id.entidadeMorada);
		entidadeMorada.setTypeface(type);
		entidadeMorada.setText(entidade.getMorada_entidade());
		
		TextView entidadeTelefone = (TextView) findViewById(R.id.entidadeTelefone);
		entidadeTelefone.setTypeface(type);
		entidadeTelefone.setText(entidade.getTelefone_entidade());
		entidadeTelefone.setOnClickListener(this);
		
		TextView entidadeFax = (TextView) findViewById(R.id.entidadeFax);
		entidadeFax.setTypeface(type);
		entidadeFax.setText(entidade.getFax_entidade());
		
		TextView entidadeEmail = (TextView) findViewById(R.id.entidadeEmail);
		entidadeEmail.setTypeface(type);
		entidadeEmail.setText(entidade.getEmail_entidade());
		entidadeEmail.setOnClickListener(this);
		
		TextView entidadeURL = (TextView) findViewById(R.id.entidadeURL);
		entidadeURL.setTypeface(type);
		entidadeURL.setText(entidade.getUrl_entidade());
		entidadeURL.setOnClickListener(this);
		
		TextView entidadeSite = (TextView) findViewById(R.id.entidadeSite);
		entidadeSite.setTypeface(type);
		entidadeSite.setText(entidade.getSite_entidade());
		entidadeSite.setOnClickListener(this);
		
		ImageButton entidadeButton = (ImageButton) findViewById(R.id.entidadeButton);
		entidadeButton.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.entidadeSite:
				String site = entidade.getSite_entidade();
				if (!site.startsWith("https://") && !site.startsWith("http://")){
				    site = "http://" + site;
				}
				Intent siteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(site));
				startActivity(siteIntent);
				break;
			case R.id.entidadeURL:
				String url = entidade.getUrl_entidade();
				if (!url.startsWith("https://") && !url.startsWith("http://")){
				    url = "http://" + url;
				}
				Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(urlIntent);
				break;
			case R.id.entidadeTelefone:
				Intent telefoneIntent = new Intent(Intent.ACTION_CALL);
				telefoneIntent.setData(Uri.parse("tel:+"+entidade.getTelefone_entidade().trim()));
				startActivity(telefoneIntent);
				break;
			case R.id.entidadeEmail:
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {entidade.getEmail_entidade()});
				startActivity(Intent.createChooser(emailIntent, ""));
				break;
			case R.id.entidadeButton:
				Intent ourIntent = new Intent("com.example.basicmaponline.SERVICOS");
				ourIntent.putExtra("id_entidade", id_entidade);
				ourIntent.putExtra("id_loja", id_loja);
				startActivity(ourIntent);
				break;
			default:
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
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
	
	public class loadDatabase extends AsyncTask<Integer, Void, SQLentidade>{
		
		@Override
		protected SQLentidade doInBackground(Integer...id) {	
			SQLentidade entidade = null;
			
			try {
				PostgreSQL pSQL = new PostgreSQL();
				entidade = pSQL.getEntidade(pSQL.getEntidadeQuery(id[0])); 
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return entidade;
			
		}
		
		@Override
		protected void onPostExecute(SQLentidade Entidade){
			entidade = Entidade;
		
		}
	}
	
}
