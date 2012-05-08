package br.com.zeuus.prototipo.bubbles;

import android.os.Parcel;
import android.os.Parcelable;

public class Bubbles implements Parcelable {
	
	private String name;
	private double distance;
	
	public Bubbles () {
		setName("");
		setDistance(0.0);
	}
	
	public Bubbles (String name, double distance) {
		setName(name);
		setDistance(distance);
	}
	
	// TODO Estudar e Documentar
	public Bubbles(Parcel in) {
		readFromParcel(in);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {		
		arg0.writeString(name);
        arg0.writeDouble(distance);		
	}
	
	// TODO Estudar
	private void readFromParcel(Parcel in) {
		 
		// We just need to read back each
		// field in the order that it was
		// written to the parcel
		setName(in.readString());
		setDistance(in.readDouble());
	}
	
	// TODO Estudar
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR =
	    	new Parcelable.Creator() {
	            public Bubbles createFromParcel(Parcel in) {
	                return new Bubbles(in);
	            }
	 
	            public Bubbles[] newArray(int size) {
	                return new Bubbles[size];
	            }
	        };

}
