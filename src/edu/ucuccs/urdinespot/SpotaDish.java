package edu.ucuccs.urdinespot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SpotaDish extends Activity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spota_dish);
	}

	public void take(View v) {
		Intent tpic = new Intent(this, TakeAphoto.class);
		startActivity(tpic);
	}
}
