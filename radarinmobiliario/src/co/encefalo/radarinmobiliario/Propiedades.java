package co.encefalo.radarinmobiliario;

public class Propiedades {

	public static final String TAG = "radar_inmobiliario";
	public static final int GET = 0;
	public static final int POST = 1;
	public static final String URL = "http://encefalo.co/radar/endpoint_services/propertieslist";//"http://encefalo.co/radar/endpoint_services/properties";
	public static final String URL_QR = "http://encefalo.co/radar/endpoint_services/properties?nid=";
	public static final String IMAGES = "images";
	public static final String NUMVIEWS = "numviews";
	public static final String ARRAYPROPIEDADES = "arraypropiedades";

	public static String URL_INMUEBLE(int nid) {
		return "http://encefalo.co/radar/endpoint_services/properties?nid="+nid;
	}

}
