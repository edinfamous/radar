package co.encefalo.radarinmobiliario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	}

}
