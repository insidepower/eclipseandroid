package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class HelloAndroid extends Activity {

	long m_dwSplashTime = 3000;
	boolean m_bPaused = false;
	boolean m_bSplashActive = true;
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
					/// Advance to next screen
					startActivity (new Intent (
								"com.example.helloandroid.CLEARSPLASH"));
				}catch(Exception e){
					Log.e(TAG, e.toString());
				}finally{
					finish();
				}
			}
		};
		splashTimer.start();
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		//if we get any key, clear the splash screen
		super.onKeyDown(keyCode, event);
		m_bSplashActive = false;
		Log.d(TAG, "onKeyDown is called");
		return true;
	}
}
