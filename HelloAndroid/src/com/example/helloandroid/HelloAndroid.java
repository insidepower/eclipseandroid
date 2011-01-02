package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class HelloAndroid extends Activity {

	long m_dwSplashTime = 3000;
	boolean m_bPaused = false;
	boolean m_bSplashActive = false;
	private static final String TAG = "Splash";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//TextView tv = new TextView(this);
		//tv.setText("My Hello Android");
		//setContentView(tv);

		/// very simple timer thread
		Thread splashTimer = new Thread(){
			public void run(){
				try{
					/// wait loop
					long ms = 0;
					while(m_bSplashActive && ms < m_dwSplashTime){
						sleep(100);
						/// Advance the timer only if we are running
						if (!m_bPaused)
							ms += 100;
					}
					/// Advance to next screen - implicit intent
					startActivity (new Intent (
					"com.example.helloandroid.CLEARSPLASH"));

				}catch(Exception e){
					Log.e(TAG, e.toString());
				}finally{
					//finish();
				}
			}
		};
		splashTimer.start();
	}

	protected void onStart()
	{
		super.onPause();
		Log.d(TAG, "@@ onStart @@ ");
	}

	protected void onPause()
	{
		super.onPause();
		m_bPaused = true;
		Log.d(TAG, "@@ onPause @@ ");
	}

	protected void onResume()
	{
		super.onResume();
		m_bPaused = false;
		Log.d(TAG, "@@ onResume @@ ");
	}

	protected void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG, "@@ onDestroy @@ ");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode, event);
		Log.d(TAG, "onKeyDown is called");
		//if we get any key, clear the splash screen
		if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
				keyCode == KeyEvent.KEYCODE_ENTER) {
			m_bSplashActive = false;
			Log.d(TAG, "Enter or Center key is pressed");

			/// explicit intent
			Intent i = new Intent();
			i.setClassName(
					"com.example.helloandroid",
					"com.example.helloandroid.simplehello");
			startActivity(i);

			return true;
		}else if(keyCode == KeyEvent.KEYCODE_BACK) {
			Log.d(TAG, "Back key is pressed");
			return true;
		}else {
			return false;
		}
	}
}
