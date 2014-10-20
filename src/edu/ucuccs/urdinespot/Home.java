package edu.ucuccs.urdinespot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

	}

	public void spot(View v) {
		Intent spot = new Intent(this, SpotaDish.class);
		startActivity(spot);
	}

}

