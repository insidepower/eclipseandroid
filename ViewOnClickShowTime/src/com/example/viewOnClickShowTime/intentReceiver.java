package com.example.viewOnClickShowTime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

/* relative layout */ /* table layout */ /* scroll layout */
public class intentReceiver extends Activity{
	private static final String TAG = "IntentReceiver";
	Intent myIntent = null;
	Intent newIntent = null;

	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		//setContentView(R.layout.myrelativelayout);
		//setContentView(R.layout.mytablelayout);
		myIntent = getIntent();
		Bundle extras = null;
		if ( myIntent != null )
			extras = myIntent.getExtras();
		if ( extras != null ){
			Integer i1 = extras.getInt("i1");
			Integer i2 = extras.getInt("i2");
			if (i1 != null && i2 != null){
				Log.d(TAG,i1.toString()+i2.toString());
				int res = i1.intValue()+i2.intValue();
				myIntent.putExtra("r", new Integer(res));
				//setResult(RESULT_OK,myIntent);
				//finish();
			}
		}
		setContentView(R.layout.myscrolllayout);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode, event);
		Log.d(TAG, "## onKeyDown ##");
		if (keyCode != KeyEvent.KEYCODE_BACK){
			if (myIntent != null){
				Log.d(TAG, "## myIntent is valid ##");
				setResult(Activity.RESULT_OK,myIntent);
			}
			finish();
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void onBackPressed ()
	{
		//super.onBackPressed();
		Log.d(TAG, "## onBackPressed ##");
		if (myIntent != null){
			Log.d(TAG, "## myIntent2 is valid ##");
			setResult(Activity.RESULT_OK,myIntent);
			//setResult(Activity.RESULT_OK,myIntent);
		}
		finish();
	}
}
