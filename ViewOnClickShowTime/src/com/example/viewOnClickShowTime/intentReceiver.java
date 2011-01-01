package com.example.viewOnClickShowTime;

import android.app.Activity;
import android.os.Bundle;

/* relative layout */ /* table layout */ /* scroll layout */
public class intentReceiver extends Activity{
	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		//setContentView(R.layout.myrelativelayout);
		//setContentView(R.layout.mytablelayout);
		setContentView(R.layout.myscrolllayout);
	}
}