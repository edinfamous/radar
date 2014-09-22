package co.encefalo.radarinmobiliario;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class ListarInmueblesAdapter extends ArrayAdapter<Inmueble>{

	private Context context;
	private int resource;
	private ArrayList<Inmueble> list;

	public ListarInmueblesAdapter(Context context, int resource, ArrayList<Inmueble> list) {
		super(context, resource, list);
		this.context=context;
		this.resource=resource;
		this.list=list;
	}
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolderItem viewHolder;
		 final Inmueble mInmueble = list.get(position);
		 Log.d("Resource", resource+"");

		if(convertView==null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.inmueble, parent, false);

			viewHolder = new ViewHolderItem();
	        viewHolder.tv_inmueble_titulo = (TextView) convertView.findViewById(R.id.tv_inmueble_titulo);
	        viewHolder.iv_inmueble_titulo = (ImageView) convertView.findViewById(R.id.iv_inmueble_titulo);
	        
	        Utilidades util = new Utilidades(context);
	        util.descargaImagen(mInmueble.getImages().get(0), viewHolder.iv_inmueble_titulo, null, null);

	        convertView.setTag(viewHolder);

	    }else{
	        viewHolder = (ViewHolderItem) convertView.getTag();
	    }
		
		if(mInmueble != null) {
	        viewHolder.tv_inmueble_titulo.setText(mInmueble.getNode_title());
	        viewHolder.tv_inmueble_titulo.setTag(mInmueble.getNid());
	    }
	    
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				conseguir_imagenes(mInmueble);
			}
		});
		
	    return convertView;
	}
	
	protected void conseguir_imagenes(Inmueble mInmueble) {
		ConexionInternet ci = new ConexionInternet(context);
		Handler puente = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					init(msg.obj.toString());
				} else if (msg.what == 2){
					Toast.makeText(context, "Conéctese a internet", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show();
				}
			}
		};
		ci.setPuente(puente);
		ci.setString_url(Propiedades.URL_INMUEBLE(mInmueble.getNid()));
		ci.execute();
			
			
		
	}

	protected void init(String string) {
		JSONInmuebleParser jip = new JSONInmuebleParser(string);
		jip.getImages(0);
		Intent i = new Intent(context, GaleriaActivity.class);
		i.putStringArrayListExtra(Propiedades.IMAGES, jip.getmImages());
		i.putExtra(Propiedades.NUMVIEWS, jip.getmImages().size());
		context.startActivity(i);

	}

	static class ViewHolderItem {
		TextView tv_inmueble_titulo;
		ImageView iv_inmueble_titulo;
	    View mViewItem;
	}

}
