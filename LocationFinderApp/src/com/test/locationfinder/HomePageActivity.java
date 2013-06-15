package com.test.locationfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomePageActivity extends Activity implements OnClickListener,
		LocationListener {
	private EditText placeET;
	private EditText captionET;
	private TextView latitude, longitude;
	private String place, caption, lat, lon;

	LocationManager mLocationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Black_NoTitleBar);
		setContentView(R.layout.activity_home_page);

		placeET = (EditText) findViewById(R.id.placeET);
		captionET = (EditText) findViewById(R.id.captionET);

		findViewById(R.id.cancelBtn).setOnClickListener(this);
		findViewById(R.id.submitBtn).setOnClickListener(this);
		findViewById(R.id.findLocBtn).setOnClickListener(this);
		findViewById(R.id.findPreviousBtn).setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_page, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.submitBtn:

			// values to be stored in database.
			place = placeET.getText().toString();
			caption = captionET.getText().toString();

			lat = latitude.getText().toString();
			lon = longitude.getText().toString();

			databaseInsertion(place, caption, lat, lon);
			break;

		case R.id.findLocBtn:
			findLocation();
			break;
		case R.id.cancelBtn:
			finish();
			break;
		case R.id.findPreviousBtn:
			Intent i = new Intent(this, FindPlaceActivity.class);
			startActivity(i);
			finish();
			break;
		}
	}

	private void databaseInsertion(String p, String c, String lt, String lo) {
		// TODO Auto-generated method stub
		DatabaseHandler dbh = DatabaseHandler.getDBHandlerInstance(this);
		dbh.insertDataOperation(p, c, lt, lo);

		Intent i = new Intent(this, OptionsPageActivity.class);
		startActivity(i);
		finish();
	}

	private void findLocation() {
		// TODO Auto-generated method stub
		Button findLoc = (Button) findViewById(R.id.findLocBtn);
		findLoc.setVisibility(View.GONE);
		LinearLayout ll = (LinearLayout) findViewById(R.id.latlongLL);
		ll.setVisibility(View.VISIBLE);

		latitude = (TextView) findViewById(R.id.tVLat);
		longitude = (TextView) findViewById(R.id.tVLong);

		// find location here.
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = mLocationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null) {

			lat = "" + location.getLatitude();
			lon = "" + location.getLongitude();

			Log.i("lat", lat);
			Log.i("lon", lon);

			latitude.setText(lat);
			longitude.setText(lon);
		} else {
			mLocationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, this);
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
