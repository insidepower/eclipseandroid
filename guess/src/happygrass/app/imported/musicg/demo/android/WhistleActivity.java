/*
 * Copyright (C) 2012 Jacquet Wong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * musicg api in Google Code: http://code.google.com/p/musicg/
 * Android Application in Google Play: https://play.google.com/store/apps/details?id=com.whistleapp
 * 
 */

package happygrass.app.imported.musicg.demo.android;

import happygrass.app.R;
import android.os.Bundle;
import android.os.Handler;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import happygrass.app.gift.GalleryActivity;

public class WhistleActivity extends Activity implements OnSignalsDetectedListener{

	static WhistleActivity mainApp;
	
	public static final int DETECT_NONE = 0;
	public static final int DETECT_WHISTLE = 1;
	public static int selectedDetection = DETECT_NONE;
	public static String txt_debug;

	// detection parameters
	private DetectorThread detectorThread;
	private RecorderThread recorderThread;
	private int numWhistleDetected = 0;

	// views
	private View mainView, listeningView;
	private Button whistleButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mainApp = this;
		
		// set views
		LayoutInflater inflater = LayoutInflater.from(this);
		mainView = inflater.inflate(R.layout.main, null);
		listeningView = inflater.inflate(R.layout.listening, null);
		selectedDetection = DETECT_WHISTLE;
		recorderThread = new RecorderThread();
		recorderThread.start();
		detectorThread = new DetectorThread(recorderThread);
		detectorThread.setOnSignalsDetectedListener(WhistleActivity.mainApp);
		detectorThread.start();
		setContentView(listeningView);


	}

	private void stopThread() {
		if (recorderThread != null) {
			recorderThread.stopRecording();
			recorderThread = null;
		}
		if (detectorThread != null) {
			detectorThread.stopDetection();
			detectorThread = null;
		}
		selectedDetection = DETECT_NONE;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Quit");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			finish();
			break;
		default:
		}
		return super.onOptionsItemSelected(item);
	}

	protected void onDestroy() {
		super.onDestroy();
		//android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	public void onWhistleDetected() {
		runOnUiThread(new Runnable() {
			public void run() {
				TextView textView = (TextView) WhistleActivity.mainApp.
										findViewById(R.id.detectedNumberText);
				textView.setText(String.valueOf(++numWhistleDetected));
				if (numWhistleDetected == DetectorThread.numWhistleRequired) {
					TextView tv = (TextView) WhistleActivity.mainApp.
									findViewById(R.id.txt_congrats);
					tv.setText(getString(R.string.txt_congrats));

					TextView tv_wish = (TextView) WhistleActivity.mainApp.
									findViewById(R.id.txt_wish);
					tv_wish.setText(getString(R.string.txt_wish));
					ObjectAnimator ani_wish = 
						ObjectAnimator.ofFloat(tv_wish, "alpha", 0.0f, 1.0f);
					ani_wish.setDuration(1500);
					ani_wish.start();
					stopThread();

					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							Intent i = new Intent(
								getApplication(), GalleryActivity.class);
							i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
										Intent.FLAG_ACTIVITY_SINGLE_TOP);
							startActivity(i);
							finish();
						}
					}, 5000);
				}
			}
		});
	}
}
