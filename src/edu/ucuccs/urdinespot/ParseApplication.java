package edu.ucuccs.urdinespot;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import android.app.Application;


public class ParseApplication extends Application {

	String APP_ID = "bfjYX6c7TxQNAXe1oe0YgTOCkqJQ1kKZtih3rEzM";
	String CLIENT_KEY = "MZflrVsCqs8D8T4qv4YcBIQQY8YKFdHiC3qaZcxe";
					

	
	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
        Parse.initialize(this, APP_ID, CLIENT_KEY);

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}

}
