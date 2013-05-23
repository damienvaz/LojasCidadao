package com.example.basicmaponline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Intro extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(3000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent openMenu = new Intent("com.example.basicmaponline.MENU");
					startActivity(openMenu);
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
