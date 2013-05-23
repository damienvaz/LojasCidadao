package com.example.basicmaponline;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mlab.android.basicoverlays.SQLloja;

public class InfoLoja extends Activity{

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
		
		TextView infoLojaNome = (TextView) findViewById(R.id.infoLojaNome);
		infoLojaNome.setText(loja.getNome());
		
		TextView infoLojaMorada = (TextView) findViewById(R.id.infoLojaMorada);
		infoLojaMorada.setText(loja.getMorada());
		
		TextView infoLojaCodigoPostal = (TextView) findViewById(R.id.infoLojaCodigoPostal);
		infoLojaCodigoPostal.setText(loja.getCodigo_postal());
		
		TextView infoLojaConcelho = (TextView) findViewById(R.id.infoLojaConcelho);
		infoLojaConcelho.setText(loja.getConselho());
		
		TextView infoLojaDistrito = (TextView) findViewById(R.id.infoLojaDistrito);
		infoLojaDistrito.setText(loja.getDistrito());
		
		TextView infoLojaTelefone = (TextView) findViewById(R.id.infoLojaTelefone);
		infoLojaTelefone.setText(loja.getTelefone());
		
		Button infoLojaBalcoes = (Button) findViewById(R.id.infoLojaBalcoes);
		infoLojaBalcoes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent ourIntent = new Intent("com.example.basicmaponline.BALCOES");
				ourIntent.putExtra("loja", loja);
				startActivity(ourIntent);
				
			}
		});
		
		Button infoLojaMapa = (Button) findViewById(R.id.infoLojaMapa);
		infoLojaMapa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent ourIntent = new Intent("com.example.basicmaponline.MAPA");
				ourIntent.putExtra("zoom", 18);
				ourIntent.putExtra("altitude",loja.getLatitude());
				ourIntent.putExtra("longitude", loja.getLongitude());
				startActivity(ourIntent);
				
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onBackPressed();
		
		return true;
	}
}
