package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class simplehello extends Activity{
	@Override
	public void onCreate(Bundle mystate) {
		super.onCreate(mystate);
		TextView tv = new TextView(this);
		tv.setText("HelloWorld from simplehello!");
		setContentView(tv);
		
		Toast.makeText(this, "hihi this is toast", Toast.LENGTH_SHORT).show();
	}
}
