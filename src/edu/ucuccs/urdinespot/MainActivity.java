package edu.ucuccs.urdinespot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class MainActivity extends Activity {

	EditText txt1, txt2;
	String username, password;
	final String APPLICATION_ID = "bfjYX6c7TxQNAXe1oe0YgTOCkqJQ1kKZtih3rEzM";
	final String CLIENT_KEY = "MZflrVsCqs8D8T4qv4YcBIQQY8YKFdHiC3qaZcxe";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txt1 = (EditText) findViewById(R.id.txtuname);
		txt2 = (EditText) findViewById(R.id.txtpwd);
		Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

	}

	public void Login(View v) {
		username = txt1.getText().toString();
		password = txt2.getText().toString();
		ParseUser.logInInBackground(username, password, new LogInCallback() {

			ParseACL defaultACL = new ParseACL();

			@Override
			public void done(ParseUser user, com.parse.ParseException arg1) {
				// TODO Auto-generated method stub

				if (user != null) {
					startActivity(new Intent(MainActivity.this, Home.class));
				} else {
					Toast.makeText(getApplicationContext(), "No existing user",
							Toast.LENGTH_LONG).show();
				}

			}

		});

	};

	public void Signup(View v) {
		startActivity(new Intent(MainActivity.this, SignIn.class));
	}
}

/*
 * ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("PersonName");
 * query.whereEqualTo("fname", "Mary"); query.findInBackground(new
 * FindCallback<ParseObject>(){
 * 
 * @Override public void done(List<ParseObject> listahan, ParseException arg1) {
 * for(int i =0; i< listahan.size(); i++){
 * Toast.makeText(getApplicationContext(),
 * listahan.get(i).getParseGeoPoint("location").getLatitude()+"",
 * Toast.LENGTH_LONG).show(); }
 * 
 * }
 * 
 * });
 */
/*
 * ParseObject p = new ParseObject("PersonName"); ParseGeoPoint geo = new
 * ParseGeoPoint(15.9758027,120.5706933); p.put("location", geo);
 * p.saveInBackground();
 */