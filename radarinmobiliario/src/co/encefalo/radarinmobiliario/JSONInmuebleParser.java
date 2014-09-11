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
	
	public ArrayList<Inmueble> getInmuebles(){
		ArrayList<Inmueble> mInmuebles = new ArrayList<Inmueble>();
		for (int i=0; i<jsonArray.length(); i++){
			JSONObject jo;
			try {
				jo = jsonArray.getJSONObject(i);
				
				Inmueble inmueble = new Inmueble(jo.getInt(Inmueble.NID));
				try {
					inmueble.setBody(jo.getString(Inmueble.BODY));
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
				
				try {
					inmueble.setAge(jo.getString(Inmueble.AGE));
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
				
				try {
					inmueble.setNode_title(jo.getString(Inmueble.NODE_TITLE));
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
				
				try {
					inmueble.setBusiness_Type(jo.getString(Inmueble.BUSINESS_TYPE));
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
				
				getImages(jo);
				
				inmueble.setImages(mImages);
				
				mInmuebles.add(inmueble);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mInmuebles;
	}

	private void getImages(JSONObject jo) {
		try {
			JSONArray jaImages = jo.getJSONArray("Images");
			
			mImages = new ArrayList<String>();
			for (int j=0; j<jaImages.length(); j++){
				Document doc = Jsoup.parse(jaImages.getString(j), "UTF-8");
				mImages.add(doc.getElementsByTag("img").attr("src"));
				}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int getmNumeroinmuebles() {
		return mNumeroinmuebles;
	}

	public ArrayList<String> getmImages() {
		return mImages;
	}

}
