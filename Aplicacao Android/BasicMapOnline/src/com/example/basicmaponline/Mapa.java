package com.example.basicmaponline;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.mapsforge.android.maps.MapActivity;
import org.mapsforge.android.maps.MapScaleBar;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.mapgenerator.tiledownloader.MapnikTileDownloader;
import org.mapsforge.android.maps.overlay.ArrayItemizedOverlay;
import org.mapsforge.android.maps.overlay.OverlayItem;
import org.mapsforge.core.GeoPoint;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mlab.android.basicoverlays.PostgreSQL;
import com.mlab.android.basicoverlays.SQLloja;

public class Mapa extends MapActivity{

    protected static Context MainActivity = null;
    public CapaTap itemizedOverlay;
    public Drawable defaultMarker;
    
    ArrayList<SQLloja> listaLoja;
    Double altitude;
    Double longitude;
    Integer zoom;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
			listaLoja = new loadDatabase().execute().get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e) {		
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        //Action Bar is the bar on the top of each activity !
        ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		zoom = (Integer) intent.getSerializableExtra("zoom");
		altitude = (Double) intent.getSerializableExtra("altitude");
		longitude = (Double) intent.getSerializableExtra("longitude");
		
		MainActivity=this;
		defaultMarker = getResources().getDrawable(R.drawable.markergold);
        itemizedOverlay = new CapaTap(defaultMarker, MainActivity);
        
        for(SQLloja loja : listaLoja){
        	String informacoesLoja = "Rua : "+loja.getMorada()+"\nC.P. : "+loja.getCodigo_postal()+"\nTel. : "+loja.getTelefone();
        	itemizedOverlay.addItem(new OverlayItem(new GeoPoint(loja.getLatitude(), loja.getLongitude()),"Loja Cidadao "+loja.getNome(),informacoesLoja));
        }
        
        MapView mapView = new MapView(this, new MapnikTileDownloader());
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        setContentView(mapView); 
        
        // Ajustar el zoom y el centro del mapa
        mapView.setCenter(new GeoPoint(altitude, longitude));
        mapView.zoom((byte) 2, 0);  //2
        
        mapView.getOverlays().add(itemizedOverlay);
        mapView.getController().setZoom(zoom);
        
        // MapScaleBar
        MapScaleBar scaleBar = mapView.getMapScaleBar();
        scaleBar.setShowMapScaleBar(true);


    }
	
	//Botao para retroceder ( Action Bar)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onBackPressed();
		return true;
	}

    public class CapaTap extends ArrayItemizedOverlay {
		private final Context context;
		
		CapaTap(Drawable defaultMarker, Context context) {
			super(defaultMarker);
			this.context = context;
			
		}

		@Override
		protected boolean onTap(final int index) {
			OverlayItem item = createItem(index);
			if(item!=null) {
				Builder builder=new AlertDialog.Builder(context);
				builder.setIcon(R.drawable.lojacidadaosmall);
				builder.setTitle(item.getTitle());
				builder.setMessage(item.getSnippet());
				builder.setNegativeButton("OK", null);
				builder.setPositiveButton("Go To", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	    Intent openMenu = new Intent("com.example.basicmaponline.INFOLOJA");
			       			openMenu.putExtra("loja",listaLoja.get(index));
			       			startActivity(openMenu);
			        	   
			           }
			       });
				builder.show();
			}
			
			return super.onTap(index);
		}
		
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public class loadDatabase extends AsyncTask<Void, Void, ArrayList<SQLloja>>{

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
		}
		
	}
    
}
