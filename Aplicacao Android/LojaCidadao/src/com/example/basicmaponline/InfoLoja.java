package com.example.basicmaponline;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mlab.android.basicoverlays.SQLloja;

public class InfoLoja extends Activity implements View.OnClickListener{

	SQLloja loja;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infoloja);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		loja =  (SQLloja) intent.getSerializableExtra("loja");
		
		Typeface type = Typeface.createFromAsset(getAssets(),"anonymous.ttf"); 
		//txtyour.setTypeface(type);
		
		TextView infoLojaNome = (TextView) findViewById(R.id.infoLojaNome);
		infoLojaNome.setTypeface(type);
		infoLojaNome.setText(loja.getNome());
		
		TextView infoLojaMorada = (TextView) findViewById(R.id.infoLojaMorada);
		infoLojaMorada.setTypeface(type);
		infoLojaMorada.setText(loja.getMorada());
		
		TextView infoLojaCodigoPostal = (TextView) findViewById(R.id.infoLojaCodigoPostal);
		infoLojaCodigoPostal.setTypeface(type);
		infoLojaCodigoPostal.setText(loja.getCodigo_postal());
		
		TextView infoLojaConcelho = (TextView) findViewById(R.id.infoLojaConcelho);
		infoLojaConcelho.setTypeface(type);
		infoLojaConcelho.setText(loja.getConselho());
		
		TextView infoLojaDistrito = (TextView) findViewById(R.id.infoLojaDistrito);
		infoLojaDistrito.setTypeface(type);
		infoLojaDistrito.setText(loja.getDistrito());
		
		TextView infoLojaTelefone = (TextView) findViewById(R.id.infoLojaTelefone);
		infoLojaTelefone.setText(loja.getTelefone());
		infoLojaTelefone.setTypeface(type);
		infoLojaTelefone.setOnClickListener(this);
		
		ImageButton infoLojaBalcoes = (ImageButton) findViewById(R.id.infoLojaBalcoes);
		infoLojaBalcoes.setOnClickListener(this);
		
		ImageButton infoLojaMapa = (ImageButton) findViewById(R.id.infoLojaMapa);
		infoLojaMapa.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.infoLojaMapa:	
				Intent mapaIntent = new Intent("com.example.basicmaponline.MAPA");
				mapaIntent.putExtra("zoom", 18);
				mapaIntent.putExtra("altitude",loja.getLatitude());
				mapaIntent.putExtra("longitude", loja.getLongitude());
				startActivity(mapaIntent);
				break;
			case R.id.infoLojaBalcoes:
				Intent balcoesIntent = new Intent("com.example.basicmaponline.BALCOES");
				balcoesIntent.putExtra("loja", loja);
				startActivity(balcoesIntent);
				break;
			case R.id.infoLojaTelefone:
				Intent telefoneIntent = new Intent(Intent.ACTION_CALL);
				telefoneIntent.setData(Uri.parse("tel:+"+loja.getTelefone().trim()));
				startActivity(telefoneIntent);
				break;
		default:
			break;
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
}
