package co.encefalo.radarinmobiliario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BuscarActivity extends Activity {
	
	private Button btn_buscar_qr;
	private Button btn_principal_buscar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buscar);
		
		btn_buscar_qr = (Button)findViewById(R.id.btn_buscar_qr);
		btn_buscar_qr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				buscar_qr();
			}
		});
	}

	protected void buscar_qr() {
		Intent intent = new Intent("co.encefalo.radarinmobiliario.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
	    super.onActivityResult(requestCode, resultCode, data);
	    
	    if (data.hasExtra("displayContents")){
	    	traer_datos(data.getStringExtra("displayContents"));
	    }
	    
	    Log.d("onActivityResult", requestCode+" "+resultCode+" "+data.toString());
	}

	private void traer_datos(String stringExtra) {
		ConexionInternet ci = new ConexionInternet(this);
		Handler puente = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					conseguir_inmueble(msg.obj.toString());
				} else if (msg.what == 2){
					Toast.makeText(getApplicationContext(), "Conéctese a internet", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(getApplicationContext(), "Error desconocido", Toast.LENGTH_SHORT).show();
				}
			}
		};
		ci.setPuente(puente);
		ci.setString_url(Propiedades.URL_QR+stringExtra);
		ci.execute();
	}

	protected void conseguir_inmueble(String string) {
		JSONInmuebleParser jip = new JSONInmuebleParser(string);
		Intent i = new Intent(getBaseContext(), ListarInmueblesActivity.class);
		i.putParcelableArrayListExtra(Propiedades.ARRAYPROPIEDADES, jip.getInmuebles());
		startActivity(i);
	}

}
