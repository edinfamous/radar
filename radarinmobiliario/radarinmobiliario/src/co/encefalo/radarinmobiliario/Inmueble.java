package co.encefalo.radarinmobiliario;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Inmueble implements Parcelable{
	public static final String NID = "nid";
	public static final String BODY = "Body";
	public static final String AGE = "Age";
	public static final String NODE_TITLE = "node_title";
	public static final String BUSINESS_TYPE = "Business Type";
	public static final String IMAGES = "Images";
	
	private int nid;
	private String node_title;
	private String Age;
	private String Body;
	private String Business_Type;
	private ArrayList<String> Images;

	public Inmueble(int nid) {
		this.nid=nid;
	}
	
	@SuppressWarnings("unchecked")
	public Inmueble (Parcel p){
		nid=p.readInt();
		node_title=p.readString();
		Images=(ArrayList<String>) p.readSerializable();
		
	}

	public int getNid() {
		return nid;
	}

	public String getNode_title() {
		return node_title;
	}

	public void setNode_title(String node_title) {
		this.node_title = node_title;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		this.Age = age;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		this.Body = body;
	}

	public String getBusiness_Type() {
		return Business_Type;
	}

	public void setBusiness_Type(String business_Type) {
		this.Business_Type = business_Type;
	}
	
	public String toString(){
		return nid+" "+Age+" "+Body+" "+Images;
		
	}

	@Override
	public int describeContents() {
		return this.hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.nid);
	    dest.writeString(this.node_title);
	    dest.writeSerializable(this.Images);
	}
	

	public ArrayList<String> getImages() {
		return Images;
	}

	public void setImages(ArrayList<String> images) {
		this.Images = images;
	}


	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Inmueble createFromParcel(Parcel in) {
            return new Inmueble(in);
        }

        public Inmueble[] newArray(int size) {
            return new Inmueble[size];
        }
    }; 
	
	

}
