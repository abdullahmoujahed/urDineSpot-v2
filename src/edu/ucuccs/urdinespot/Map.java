package edu.ucuccs.urdinespot;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class Map extends Activity {

	GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
	}

}
