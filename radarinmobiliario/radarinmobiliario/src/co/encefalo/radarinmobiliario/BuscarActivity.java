package co.encefalo.radarinmobiliario;

import com.google.zxing.client.android.Intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
		/*IntentIntegrator integrator = new IntentIntegrator(BuscarActivity.this);
		integrator.addExtra("RESULT_DISPLAY_DURATION_MS", 3000L);
	      integrator.addExtra("PROMPT_MESSAGE", "Custom prompt to scan a product");
		 integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);*/
		//Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        //intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        //startActivityForResult(intent, 0);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    Log.d("Resultado", requestCode+" "+resultCode+" "+intent.getStringExtra("ARTICLE"));
		 /*IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		    if (result != null) {
		      String contents = result.getContents();
		      if (contents != null) {
		    	  Log.d("Resultado", requestCode+" "+resultCode+" "+intent.toString());
		      } else {
		    	  Log.d("Resultado", requestCode+" "+resultCode+" "+intent.toString());
		      }
		    }*/
	    
	  }


}
