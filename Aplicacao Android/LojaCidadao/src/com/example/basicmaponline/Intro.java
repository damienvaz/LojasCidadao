package com.example.basicmaponline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Intro extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		//getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    //getActionBar().hide();
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
		setContentView(R.layout.intro);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(3000);
					Intent openMenu = new Intent("com.example.basicmaponline.MENU");
					startActivity(openMenu);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					finish();
				}
			}
		};
		timer.start();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
