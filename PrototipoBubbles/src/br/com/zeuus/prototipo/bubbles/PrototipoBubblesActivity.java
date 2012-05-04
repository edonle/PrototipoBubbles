package br.com.zeuus.prototipo.bubbles;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrototipoBubblesActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadMainScreen();
	}
	
	//Carrega tela principal
	public void loadMainScreen() {
		setContentView(R.layout.main);
		checkGPS();
		Button mainButton = (Button) findViewById(R.id.main_button);
		mainButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				loadBubblesSearch();
			}
		});
	}
	
	//Carrega a tela de busca
	public void loadBubblesSearch() {

		Intent intent = new Intent(this, DisplaySearchBubblesActivity.class);
				
		startActivity(intent);
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
		// TODO Corrigir para a internacionalização
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

	//Mostra opção para usuário ativar o GPS
	private void showGpsOptions() {
		Intent gpsOptionsIntent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(gpsOptionsIntent);
	}
}