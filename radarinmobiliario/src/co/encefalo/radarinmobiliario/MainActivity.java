package co.encefalo.radarinmobiliario;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	
	private Button btn_principal_nuevo;
	private Button btn_principal_buscar;

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
		
		btn_principal_buscar = (Button)findViewById(R.id.btn_principal_buscar);
		btn_principal_buscar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				buscar();
			}
		});
		
	}

	protected void buscar() {
		Intent i = new Intent(getBaseContext(), BuscarActivity.class);
		startActivity(i);
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
		Intent i = new Intent(getBaseContext(), ListarInmueblesActivity.class);
			//i.putExtra(Propiedades.ARRAYPROPIEDADES, jip.getInmuebles());
			i.putParcelableArrayListExtra(Propiedades.ARRAYPROPIEDADES, jip.getInmuebles());
			startActivity(i);
		
		
		/*jip.getImages(0);
		
		Intent i = new Intent(getBaseContext(), GaleriaActivity.class);      
		i.putStringArrayListExtra(Propiedades.IMAGES, jip.getmImages());
		i.putExtra(Propiedades.NUMVIEWS, jip.getmImages().size());
		startActivity(i);
		*/
		
	}
}
