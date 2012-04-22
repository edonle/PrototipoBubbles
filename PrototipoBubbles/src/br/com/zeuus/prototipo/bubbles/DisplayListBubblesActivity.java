package br.com.zeuus.prototipo.bubbles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayListBubblesActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get the message from the intent
		Intent intent = getIntent();
		String message = intent.getStringExtra(PrototipoBubblesActivity.EXTRA_MESSAGE);

		// Create the text view
		TextView textView = new TextView(this);
		textView.setTextSize(30);
		textView.setText(message);

		setContentView(textView);
	}

}
