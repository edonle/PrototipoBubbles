package br.com.zeuus.prototipo.bubbles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayListBubblesActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listabubbles);

		// Obtendo o objeto intent do Bubbles Activity
		Intent intent = getIntent();
		String message = intent.getStringExtra(PrototipoBubblesActivity.EXTRA_MESSAGE);
		String localizacao = intent.getStringExtra(PrototipoBubblesActivity.CURRENT_LOCATION);
				
		TextView textView = (TextView) findViewById(R.id.my_bubbles);
		TextView textViewLocalizacao = (TextView) findViewById(R.id.my_location);
		
		// Create the text view
		textView.setTextSize(30);
		textView.setText(message);
		
		textViewLocalizacao.setTextSize(15);
		textViewLocalizacao.setText(String.valueOf(localizacao));
	}

}
