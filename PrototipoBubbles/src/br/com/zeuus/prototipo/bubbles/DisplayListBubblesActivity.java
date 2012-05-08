package br.com.zeuus.prototipo.bubbles;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayListBubblesActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bubbleslist);

		// Obtendo o objeto intent do Bubbles Activity
		Intent intent = getIntent();

		String localizacao = intent
				.getStringExtra(DisplaySearchBubblesActivity.CURRENT_LOCATION);
		ArrayList<Bubbles> myBubblesList = intent
				.getParcelableArrayListExtra(DisplaySearchBubblesActivity.BUBBLES_FOUND);

		String message = "";

		// Cria um novo array do tamanho do Arraylist obtido
		Bubbles bubblesFound[] = new Bubbles[myBubblesList.size()];

		// Alimenta a string message com as informações dos Bubbles obtidos
		for (int i = 0; i < myBubblesList.size(); i++) {
			bubblesFound[i] = myBubblesList.get(i);
			
			message += bubblesFound[i].getName();
			message += "\n";
			message += bubblesFound[i].getDistance();
			message += "\n\n";
		}

		TextView textView = (TextView) findViewById(R.id.my_bubbles);
		TextView textViewLocalizacao = (TextView) findViewById(R.id.my_location);

		textView.setTextSize(30);
		textView.setText(message);

		textViewLocalizacao.setTextSize(15);
		textViewLocalizacao.setText(String.valueOf(localizacao));
	}

}
