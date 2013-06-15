package com.test.locationfinder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class FindPlaceActivity extends Activity implements OnClickListener {
	EditText placeName;
	String searchString, latitude, longitude;
	TextView lat, lon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Black_NoTitleBar);
		setContentView(R.layout.activity_find_place);

		placeName = (EditText) findViewById(R.id.eTPlaceSrch);
		lat = (TextView) findViewById(R.id.tvLatSrch);
		lon = (TextView) findViewById(R.id.tVLongSrch);

		findViewById(R.id.btnEnter).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_find_place, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnEnter:
			searchString = placeName.getText().toString();
			searchForRecord(searchString);
			break;
		}
	}

	private void searchForRecord(String searchString2) {
		// TODO Auto-generated method stub
		DatabaseHandler dbh = DatabaseHandler.getDBHandlerInstance(this);
		String[] latlon = dbh.getData(searchString2);
		// Log.i("latlon", latlon);
		lat.setText(latlon[0]);
		lon.setText(latlon[1]);
	}

}
