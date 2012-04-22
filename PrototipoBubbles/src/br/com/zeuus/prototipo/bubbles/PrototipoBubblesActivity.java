package br.com.zeuus.prototipo.bubbles;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

public class PrototipoBubblesActivity extends Activity {

	public final static String EXTRA_MESSAGE = "br.com.zeuus.prototipo.bubbles.MESSAGE";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			createGpsDisabledAlert();			
		}

	}

	/** Called when the user selects the Send button */
	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayListBubblesActivity.class);
		/* EditText editText = (EditText) findViewById(R.id.edit_message); */
		String message = "Lojas Americanas\nMc Donalds\nDroga Raia...";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

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
}