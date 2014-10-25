package edu.ucuccs.urdinespot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SubmitPhoto extends Activity {

	EditText txtName;
	EditText txtDescription;
	
	final String APP_ID = "bfjYX6c7TxQNAXe1oe0YgTOCkqJQ1kKZtih3rEzM";
	final String CLIENT_KEY = "MZflrVsCqs8D8T4qv4YcBIQQY8YKFdHiC3qaZcxe";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_photo);
		Parse.initialize(this, APP_ID, CLIENT_KEY);

		txtName = (EditText) findViewById(R.id.txtName);
		txtDescription = (EditText) findViewById(R.id.txtDescription);

		ParseUser Dish = new ParseUser();
		Dish.setUsername(txtName.getText().toString());
		Dish.setPassword(txtDescription.getText().toString());
		Dish.signUpInBackground(new SignUpCallback() {

			@Override
			public void done(ParseException e) {
				if (e != null) {
					Toast.makeText(getApplicationContext(), "Successful",
							Toast.LENGTH_LONG).show();
					startActivity(new Intent(SubmitPhoto.this, SpotaDish.class));
				}

			}

		});
	}
}
