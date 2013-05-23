package com.example.basicmaponline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

private Context mContext;

private Integer[] mThumbIds = {
        R.drawable.googlemapsicon,
        R.drawable.lc_img_btn_red
};

private String[] mThumbtext = {
        "Mapa",
        "Lojas"
};


//Constructor
	public ImageAdapter(Context c){
		mContext = c;
	}

	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumbIds[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {			
	
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View gridView;
		
		if(convertView==null){
			gridView = new View(mContext);
			
			gridView = inflater.inflate(R.layout.fullimage, null);
			TextView textView = (TextView) gridView.findViewById(R.id.text);
			textView.setText(mThumbtext[position]);
			ImageView imageView = (ImageView) gridView.findViewById(R.id.full_image_view);
			imageView.setImageResource(mThumbIds[position]);
		}else {
			gridView = (View) convertView;
		}
		
		return gridView;
	}

}