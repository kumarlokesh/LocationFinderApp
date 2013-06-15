package com.test.locationfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OptionsPageActivity extends Activity implements OnClickListener {

	Button addPlace, findSavedPlace;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Black_NoTitleBar);
		setContentView(R.layout.activity_options_page);

		findViewById(R.id.btnAddPlace).setOnClickListener(this);
		findViewById(R.id.btnFindSavedPlace).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_options_page, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnAddPlace:
			Intent i = new Intent(this, HomePageActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.btnFindSavedPlace:
			findSavedPlace();
			break;
		default:
			break;
		}
	}

	private void findSavedPlace() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, FindPlaceActivity.class);
		startActivity(i);
		finish();
	}

}
