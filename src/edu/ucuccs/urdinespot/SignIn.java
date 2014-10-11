package edu.ucuccs.urdinespot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignIn extends Activity {

	EditText txtUsername, txtPword;
	final String APPLICATION_ID = "bfjYX6c7TxQNAXe1oe0YgTOCkqJQ1kKZtih3rEzM";
	final String CLIENT_KEY = "MZflrVsCqs8D8T4qv4YcBIQQY8YKFdHiC3qaZcxe";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		txtUsername = (EditText) findViewById(R.id.txtunameup);
		txtPword = (EditText) findViewById(R.id.txtpwdup);
		Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
	}

	public void Signup(View v) {
		ParseUser user = new ParseUser();
		user.setUsername(txtUsername.getText().toString());
		user.setPassword(txtPword.getText().toString());
		user.signUpInBackground(new SignUpCallback() {

			@Override
			public void done(ParseException e) {
				if (e != null) {
					Toast.makeText(getApplicationContext(), "Successful",
							Toast.LENGTH_LONG).show();
					startActivity(new Intent(SignIn.this, MainActivity.class));
				}

			}

		});
	}
}

