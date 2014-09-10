package co.encefalo.radarinmobiliario;

import java.util.ArrayList;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	
	private Button btn_principal_nuevo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_principal_nuevo = (Button)findViewById(R.id.btn_principal_nuevo);
		btn_principal_nuevo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nuevo();
			}
		});
		
	}

	protected void nuevo() {
		ConexionInternet ci = new ConexionInternet(this);
		Handler puente = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					conseguir_inmuebles(msg.obj.toString());
				} else if (msg.what == 2){
					Toast.makeText(getApplicationContext(), "Conéctese a internet", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(getApplicationContext(), "Error desconocido", Toast.LENGTH_SHORT).show();
				}
			}
		};
		ci.setPuente(puente);
		ci.setString_url(Propiedades.URL);
		ci.execute();
	}

	protected void conseguir_inmuebles(String string) {
		JSONInmuebleParser jip = new JSONInmuebleParser(string);
		try {
			Intent i = new Intent(getBaseContext(), ListarInmueblesActivity.class);
			//i.putExtra(Propiedades.ARRAYPROPIEDADES, jip.getInmuebles());
			i.putParcelableArrayListExtra(Propiedades.ARRAYPROPIEDADES, jip.getInmuebles());
			startActivity(i);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*jip.getImages(0);
		
		Intent i = new Intent(getBaseContext(), GaleriaActivity.class);      
		i.putStringArrayListExtra(Propiedades.IMAGES, jip.getmImages());
		i.putExtra(Propiedades.NUMVIEWS, jip.getmImages().size());
		startActivity(i);
		*/
		
	}
}
