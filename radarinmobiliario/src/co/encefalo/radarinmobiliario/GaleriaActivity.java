package co.encefalo.radarinmobiliario;

import java.util.ArrayList;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class GaleriaActivity extends Activity {
	
	private ImagePageAdapter imagePageAdapter;
	private ArrayList<String> mImages;
	private ViewPager vp_galeria;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galeria);
		
		int num_views=0;
		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			num_views = extras.getInt(Propiedades.NUMVIEWS);
			mImages = extras.getStringArrayList(Propiedades.IMAGES);
			
			Log.d("mImages", mImages.toString());
			
			imagePageAdapter = new ImagePageAdapter(this);
			imagePageAdapter.setNUM_AWESOME_VIEWS(num_views);
			imagePageAdapter.setImages(mImages);
			vp_galeria = (ViewPager) findViewById(R.id.vp_galeria);
			vp_galeria.setAdapter(imagePageAdapter);
			
		}
		
		
		
	}

}
