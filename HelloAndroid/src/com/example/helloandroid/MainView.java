package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainView extends Activity {
	Button btn;
	private static final String TAG = "MainView";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);
        //TextView tv = new TextView(this);
        //tv.setText("My Hello Android");
        //setContentView(tv);
        
        btn = (Button) findViewById(R.id.invokeExtInt);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
				Intent myIntent = new Intent("com.example.helloandroid.fromSomeone");
				startActivity(myIntent);
     		}
        });

		btn = (Button) findViewById(R.id.invokeExtInt2);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				try {
					Intent i = new Intent();
					i.setClassName("com.example.viewOnClickShowTime",
						"com.example.viewOnClickShowTime.intentReceiver");
					i.putExtra( "i1", new Integer( 5 ) );
					i.putExtra( "i2", new Integer( 6 ) );
					startActivity(i);
				}catch (Exception e) {
					Log.d(TAG, e.toString());
				}
			}
		});
    }
}
