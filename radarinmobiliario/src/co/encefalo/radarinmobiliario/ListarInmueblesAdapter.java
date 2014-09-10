package co.encefalo.radarinmobiliario;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
		TextView v = new TextView(context);
		
		Inmueble inmueble = list.get(position);
		
		v.setText(inmueble.getNode_title()); 
		return v;
	}

}
