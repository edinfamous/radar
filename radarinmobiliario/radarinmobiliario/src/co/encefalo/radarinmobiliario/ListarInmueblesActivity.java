package co.encefalo.radarinmobiliario;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ListarInmueblesActivity extends Activity{
	
	private ListView lv_listar_propiedades;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listar_propiedades);
		
		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			lv_listar_propiedades = (ListView) findViewById(R.id.lv_listar_propiedades);
			
			ArrayList<Inmueble> mInmuebles = extras.getParcelableArrayList(Propiedades.ARRAYPROPIEDADES);
			
			ListarInmueblesAdapter arrayAdapter = new ListarInmueblesAdapter(this,android.R.layout.simple_list_item_1,mInmuebles );

			lv_listar_propiedades.setAdapter(arrayAdapter); 
			
		}
        
	}

}
