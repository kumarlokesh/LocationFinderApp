package com.test.locationfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final int STOP_SPLASH_SCREEN = 0;

	private Handler splashScreenHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case STOP_SPLASH_SCREEN:
				showHomePage();
				finish();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Black_NoTitleBar);
		setContentView(R.layout.activity_main);

		Message msg = new Message();
		msg.what = STOP_SPLASH_SCREEN;
		splashScreenHandler.sendMessageDelayed(msg, 1000);
	}

	protected void showHomePage() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, HomePageActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
