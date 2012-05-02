package br.com.zeuus.prototipo.bubbles;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PrototipoBubblesActivity extends Activity implements
		LocationListener {

	public final static String EXTRA_MESSAGE = "br.com.zeuus.prototipo.bubbles.MESSAGE";
	public final static String CURRENT_LOCATION = "br.com.zeuus.prototipo.bubbles.CURRENT_LOCATION";
	
	private LocationManager lm;
	private String latitude;
	private String longitude;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);

		loadMainScreen();
	}
	
	//Carrega tela principal
	public void loadMainScreen() {
		setContentView(R.layout.main);
		checkGPS();
		Button mainButton = (Button) findViewById(R.id.main_button);
		mainButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				loadBubblesList();
			}
		});
	}

	//Carrega a lista de Bubbles
	public void loadBubblesList() {

		Intent intent = new Intent(this, DisplayListBubblesActivity.class);
		Bundle bundle = new Bundle();

		String message = "Lojas Americanas\n450m\n\nMc Donalds\n510m\n\nDroga Raia\n1100m";

		bundle.putString(EXTRA_MESSAGE, message);
		bundle.putString(CURRENT_LOCATION, getCurrentLocation());

		intent.putExtras(bundle);
		startActivity(intent);
	}

	//Retorna a localizacao atual do usuário
	private String getCurrentLocation() {

		String message = "Meu Local:\nlat=" + getLatitude() + "\nlon="
				+ getLongitude();
		return message;
	}

	// Verifica se o GPS está ativo
	public void checkGPS() {

		LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			createGpsDisabledAlert();
		}
	}

	//Mostra o alerta de GPS desativado
	private void createGpsDisabledAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"O GPS do seu dispositivo está desativado! Deseja ativá-lo?")
				.setCancelable(false)
				.setPositiveButton("Ativar GPS",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								showGpsOptions();
							}
						});
		builder.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						System.exit(0);
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	private void showGpsOptions() {
		Intent gpsOptionsIntent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(gpsOptionsIntent);
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
	
	//Getters e Setters
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