package co.encefalo.radarinmobiliario;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.util.Log;

public class JSONInmuebleParser {

	private String mString;
	private int mNumeroinmuebles;
	private JSONArray jsonArray;
	private ArrayList<String> mImages;
	

	public JSONInmuebleParser(String string) {
		this.mString = string;
		parseJson();
	}

	public void parseJson() {
		try {
			jsonArray = new JSONArray(mString);
			mNumeroinmuebles=jsonArray.length();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void getImages(int i){
		try {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			JSONArray jaImages = jsonObject.getJSONArray("Images");
			mImages = new ArrayList<String>();
			for (int j=0; j<jaImages.length(); j++){
				Document doc = Jsoup.parse(jaImages.getString(j), "UTF-8");
				mImages.add(doc.getElementsByTag("a").attr("href"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Inmueble> getInmuebles() throws JSONException{
		ArrayList<Inmueble> mInmuebles = new ArrayList<Inmueble>();
		for (int i=0; i<jsonArray.length(); i++){
			JSONObject jo = jsonArray.getJSONObject(i);
			
			Inmueble inmueble = new Inmueble(jo.getInt(Inmueble.NID));
			inmueble.setBody(jo.getString(Inmueble.BODY));
			inmueble.setAge(jo.getString(Inmueble.AGE));
			inmueble.setNode_title(jo.getString(Inmueble.NODE_TITLE));
			inmueble.setBusiness_Type(jo.getString(Inmueble.BUSINESS_TYPE));
			
			Log.d("Inmueble", i+" : "+inmueble.toString());
			
			mInmuebles.add(inmueble);
		}
		return mInmuebles;
	}

	public int getmNumeroinmuebles() {
		return mNumeroinmuebles;
	}

	public ArrayList<String> getmImages() {
		return mImages;
	}

}
