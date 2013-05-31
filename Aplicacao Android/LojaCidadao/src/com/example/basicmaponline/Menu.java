package com.example.basicmaponline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class Menu extends Activity{

	String classes[]={"Mapa",
					  "Lojas",
					  "Pesquisar"
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		GridView gridView = (GridView) findViewById(R.id.grid);
		
		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapterMenu(this));
		/**
		 * On Click event for Single Gridview Item
		 * */
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
				
				String nameClass = classes[position];
				
				@SuppressWarnings("rawtypes")
				Class ourClass;
				
				try {
					
					ourClass = Class.forName("com.example.basicmaponline."+nameClass);
					Intent intent = new Intent(Menu.this,ourClass);
					if(nameClass == "Mapa"){
						intent.putExtra("zoom", 8);
						intent.putExtra("altitude",39.342794);
						intent.putExtra("longitude", -8.635254);
					}
					startActivity(intent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
