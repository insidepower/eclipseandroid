package com.example.viewOnClickShowTime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/* relative layout */ /* table layout */ /* scroll layout */
public class intentReceiver extends Activity{
	private static final String TAG = "IntentReceiver";

	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		//setContentView(R.layout.myrelativelayout);
		//setContentView(R.layout.mytablelayout);
		Intent myIntent = getIntent();
		Bundle extras = null;
		if ( myIntent != null )
			extras = myIntent.getExtras();
		if ( extras != null ){
			Integer i1 = extras.getInt("i1");
			Integer i2 = extras.getInt("i2");
			if (i1 != null && i2 != null){
				Log.d(TAG,i1.toString()+i2.toString());
				int res = i1.intValue()+i2.intValue();
				extras.putInt("r", new Integer(res));
				//setResult(RESULT_OK, null, extras);
			}
		}
		setContentView(R.layout.myscrolllayout);
	}
}
