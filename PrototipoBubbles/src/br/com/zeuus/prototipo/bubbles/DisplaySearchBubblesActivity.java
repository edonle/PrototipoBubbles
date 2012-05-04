package br.com.zeuus.prototipo.bubbles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class DisplaySearchBubblesActivity extends Activity implements
		LocationListener {

	public final static String EXTRA_MESSAGE = "br.com.zeuus.prototipo.bubbles.MESSAGE";
	public final static String CURRENT_LOCATION = "br.com.zeuus.prototipo.bubbles.CURRENT_LOCATION";

	private LocationManager lm;

	private String latitude;
	private String longitude;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.search);

		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);

		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {

					// Enquanto não identificou a latitude e a longitude
					while (getLatitude() == null || getLongitude() == null) {
						Log.e("GPS", "GPS location identifying");
						// Dorme 1 segundo
						Thread.sleep(1000);
					}

					// Aqui foi identificada a localização
					Log.e("GPS", "GPS location found");
					loadBubblesList();
					
				} catch (Exception e) {
				}
			}
		}).start();
	}

	// Carrega a lista de Bubbles
	public void loadBubblesList() {

		Intent intent = new Intent(this, DisplayListBubblesActivity.class);
		Bundle bundle = new Bundle();

		String message = "Lojas Americanas\n450m\n\nMc Donalds\n510m\n\nDroga Raia\n1100m";

		bundle.putString(EXTRA_MESSAGE, message);
		bundle.putString(CURRENT_LOCATION, getCurrentLocation());

		intent.putExtras(bundle);
		startActivity(intent);
	}

	// Retorna a localizacao atual do usuário
	private String getCurrentLocation() {

		// TODO Corrigir para internacionalização
		String message = "Meu Local:\nlat=" + getLatitude() + "\nlon="
				+ getLongitude();
		return message;
	}

	@Override
	public void onLocationChanged(Location location) {

		String lat = String.valueOf(location.getLatitude());
		String lon = String.valueOf(location.getLongitude());
		Log.e("GPS", "location changed: lat=" + lat + ", lon=" + lon);
		updateLocation(lat, lon);
	}

	public void onProviderDisabled(String arg0) {
		Log.e("GPS", "provider disabled " + arg0);
	}

	public void onProviderEnabled(String arg0) {
		Log.e("GPS", "provider enabled " + arg0);
	}

	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		Log.e("GPS", "status changed to " + arg0 + " [" + arg1 + "]");
	}

	private void updateLocation(String lat, String lon) {
		setLatitude(lat);
		setLongitude(lon);
	}

	// Getters e Setters
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}