package com.example.basicmaponline;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mlab.android.basicoverlays.PostgreSQL;
import com.mlab.android.basicoverlays.SQLloja;

public class Mapa extends MapActivity{

    protected static Context MainActivity = null;
    public CapaTap itemizedOverlay;
    public Drawable defaultMarker;
    Drawable gpsMarker;
    OverlayItem itemGps;
    ArrayItemizedOverlay gpsItemizedOverlay;
    GeoPoint point;
    
    ArrayList<SQLloja> listaLoja;
    Double altitude;
    Double longitude;
    Integer zoom;
    LocationManager lm;
    MapView mapView ;
    
    Boolean firstTime=true;
    
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
		//gpsMarker = getResources().getDrawable(R.drawable.mapsredpoiiconsmall);
		//defaultMarker = getResources().getDrawable(R.drawable.markergold);
        //itemizedOverlay = new CapaTap(defaultMarker, MainActivity);
        //gpsItemizedOverlay = new ArrayItemizedOverlay(gpsMarker);
        
        /*for(SQLloja loja : listaLoja){
        	String informacoesLoja = "Rua : "+loja.getMorada()+"\nC.P. : "+loja.getCodigo_postal()+"\nTel. : "+loja.getTelefone();
        	itemizedOverlay.addItem(new OverlayItem(new GeoPoint(loja.getLatitude(), loja.getLongitude()),"Loja Cidadao "+loja.getNome(),informacoesLoja));
        }*/
        
        //mapView = new MapView(this, new MapnikTileDownloader());
        mapView = new MapView(this);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        
        copyFiles();
        
        defaultMarker = getResources().getDrawable(R.drawable.markergold);
        itemizedOverlay = new CapaTap(defaultMarker, MainActivity);
        
        for(SQLloja loja : listaLoja){
        	String informacoesLoja = "Rua : "+loja.getMorada()+"\nC.P. : "+loja.getCodigo_postal()+"\nTel. : "+loja.getTelefone();
        	itemizedOverlay.addItem(new OverlayItem(new GeoPoint(loja.getLatitude(), loja.getLongitude()),"Loja Cidadao "+loja.getNome(),informacoesLoja));
        }
        
        mapView.setTextScale(2f); 
        setContentView(mapView); 
        
        // Ajustar el zoom y el centro del mapa
        mapView.setCenter(new GeoPoint(altitude, longitude)); 
        
        mapView.getOverlays().add(itemizedOverlay);
        Log.d("GPS3",Integer.toString(mapView.getOverlays().size()));
        mapView.getController().setZoom(8);
        
        
        // MapScaleBar
        MapScaleBar scaleBar = mapView.getMapScaleBar();
        scaleBar.setShowMapScaleBar(true);

        
        /*---------------*/
        
       
        /*lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);    

        LocationListener locationListener = new MyLocationListener();

        lm.requestLocationUpdates( LocationManager.GPS_PROVIDER,  0, 0, locationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 20, locationListener);*/
        
    }
	
	private void copyFiles() {
		try {  	  
        	String filepath=Environment.getExternalStorageDirectory().getPath()+"/maps/portugal.map";
        	File mapfile=new File(filepath);
        	if(mapfile.exists()==false) {
        		mapfile=copyFileToSdcard(R.raw.portugal, "portugal.map", "maps");
        	}        	
        	mapView.setMapFile(mapfile);
        	
        	// Copiar el marcador a  sdcard/maps
    		Log.d("HAL","Copiando el marcador desde raw a "+mapfile.getAbsolutePath());
    		//copyFileToSdcard(R.drawable.markergold, "markergold.png", "maps");

    		
        } catch (Exception e) {
        	Log.e("HAL","Error"+e.getMessage());
        	return;
        
        }
        
	}
	
	private File copyFileToSdcard(int resid, String filename, String sdcardDirectoryName) {
    	Log.d("HAL","copyFileToSdcard()");	
    	String dirpath=Environment.getExternalStorageDirectory().getPath()+"/"+sdcardDirectoryName;
		File dir= new File(dirpath);
    	if(dir.exists()==false) {
        	Log.d("HAL","Creando directorio "+dir.getAbsolutePath());
    		dir.mkdir();
    	}
    	
		File file = new File(dirpath, filename); 
		try {
	        InputStream is = getResources().openRawResource(resid);
	        OutputStream os = new FileOutputStream(file);
	        byte[] data = new byte[is.available()];
	        is.read(data);
	        os.write(data);
	        is.close();
	        os.close();
	    } catch (IOException e) {
	        //Log.w("ExternalStorage", "Error writing " + file, e);
	        return null;
	    }
	    return file;
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
        //getMenuInflater().inflate(R.menu.activity_main, menu);
    	MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.homeactionbar, menu);
    	
    	return true;
    }
    
  //Botao para retroceder ( Action Bar)
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
    
    /* Class My Location Listener */

    /*public class MyLocationListener implements LocationListener {

    *@Override
	*
    *public void onLocationChanged(Location loc){
    *	double latitude = loc.getLatitude();
    *	double longitude = loc.getLongitude();
    *	Log.d("GPS","Latitude: "+latitude+" Longitude: "+longitude);
    *	Log.d("GPS2",Integer.toString(mapView.getOverlays().size()));
    *	
    *	GeoPoint point = new GeoPoint(latitude, longitude);
    *	//gps.addItem(new OverlayItem(point, null, null));
    *	//mapView.getOverlays().add(gps);
    *	//mapView.getOverlays().get(2).requestRedraw();
    *	//Marker marker = new Marker(R.drawable.mapsredpoiicon,point);
    *	//mapView.getOverlays().get(3).r
    *	if(firstTime == true){
    *		Log.d("GPS4","Funcionou");
    *		itemGps = new OverlayItem(point, null,null);
    *   	gpsItemizedOverlay.addItem(itemGps);
    *    	mapView.getOverlays().add(gpsItemizedOverlay);
    *    	firstTime=false;
    *    	
    *	}
    *
    *	//OverlayItem item = new OverlayItem(point, null,null);
    *	//gpsItemizedOverlay.addItem(item);
    *	//mapView.getOverlays().add(gpsItemizedOverlay);	
    	
    	else{
    		gpsItemizedOverlay.clear();
    		//itemGps.setPoint(point);
    		itemGps.setPoint(point);
    		gpsItemizedOverlay.addItem(itemGps);
    		gpsItemizedOverlay.requestRedraw();
    	}
    }

    @Override
    public void onProviderDisabled(String provider){
    }


    @Override
    public void onProviderEnabled(String provider){
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){
    }

    }*/ /*End of Class MyLocationListener*/
    
    
}
