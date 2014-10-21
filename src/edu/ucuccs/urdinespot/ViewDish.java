package edu.ucuccs.urdinespot;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ViewDish extends Activity {
	// Declare Variables
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapter adapter;
	private List<WorldPopulation> worldpopulationlist = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.activity_view_dish);
		// Execute RemoteDataTask AsyncTask
		new RemoteDataTask().execute();
	}

	// RemoteDataTask AsyncTask
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(ViewDish.this);
			// Set progressdialog title
			mProgressDialog.setTitle("urdineSpot");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Create the array
			worldpopulationlist = new ArrayList<WorldPopulation>();
			try {
				
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"Dish");
				// Locate the column named "ranknum" in Parse.com and order list
				// by ascending
				query.orderByAscending("ranknum");
				ob = query.find();
				for (ParseObject dishes : ob) {
					// Locate images in flag column
					ParseFile image = (ParseFile) dishes.get("food");

					WorldPopulation map = new WorldPopulation();
					map.setRank((String) dishes.get("rank"));
					map.setName((String) dishes.get("Name"));
					map.setDescription((String) dishes.get("Description"));
					map.setfood(image.getUrl());
					worldpopulationlist.add(map);
				}
			} catch (ParseException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// Locate the listview in listview_main.xml
			listview = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(ViewDish.this, worldpopulationlist);
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
		}
	}
}
