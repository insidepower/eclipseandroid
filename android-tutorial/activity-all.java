[ destroy current and create new function ]

1) [ AndroidManifest ][ xml ]
/// AndroidManifest.xml - must declare the new activity here as well
        <activity
            android:name="happygrass.app.guess.LoginActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name="MainActivity"></activity>

(3) [ start ][ delay ]
start an activity after delay
	public void submitOnClick(View view) {
		RadioGroup rg = (RadioGroup) findViewById(R.id.radio_question);
		TextView tv = (TextView) findViewById(R.id.txt_answer);
		int index = rg.getCheckedRadioButtonId();
		
		switch(index) {
		case R.id.radbtn_1:
			tv.setText(getString(R.string.firstloginWrong1));
			break;
		case R.id.radbtn_2:
			tv.setText(getString(R.string.firstloginWrong2));
			break;
		case R.id.radbtn_3:
			tv.setText(getString(R.string.firstloginCorrect));
			mHandler.postDelayed(finishThisActivit,900);
			break;
		default:	
			tv.setText("error");
		}
	}

	private Runnable finishThisActivit = new Runnable() {
		  public void run() {
			Intent i = new Intent(getApplicationContext(), GalleryActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			finish();
		  }
	};


[ ------------------------------------------------------------------ ]
[ screen ]
[ full screen ]
requestWindowFeature(Window.FEATURE_NO_TITLE);
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
						WindowManager.LayoutParams.FLAG_FULLSCREEN);


[ screen on ]
getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
/// Another way is to use a wake lock:
mWakeLock = getContext().getSystemService(Context.POWER_SERVICE)
    .newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass().getName());
/// --- manifest
<uses-permission android:name="android.permission.WAKE_LOCK"/>


[ ------------------------------------------------------------------ ]
[ new ][ activity ]

/// [ --- start ---- ]invoke an imported application
/// --- {{
Intent intent = new Intent();
intent.setClassName("com.android.bla", "com.android.bla.YourActivity");
startActivity(intent);
/// or---
import happygrass.app.imported.musicg.demo.android.WhistleActivity;
Intent i = new Intent(getApplicationContext(), WhistleActivity.class);
/// [ --- end ---- ]invoke an imported application
/// --- }}

/// [ --- start ---- ][ start ][ finish ]
/// --- {{
public void loginOnClick(View view) {
		Intent i = new Intent(this, MainActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		finish();
}
/// or if put in AndroidManifest.xml, then no need to call finish()
<activity android:name=".ClassName" android:noHistory="true" ... />
/// [ --- end ---- ][ start ][ finish ]
/// --- }}

[ ------------------------------------------------------------------ ]
[ ------------------------------------------------------------------ ]

[ xml ][ java ][ string ]
TextView tv = (TextView) findViewById(R.id.txt_answer);
final String passwordText = getString(R.string.password);

[ xml ][ image ]
put in res/drawable-nodpi

