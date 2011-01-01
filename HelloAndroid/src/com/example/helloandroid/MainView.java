package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainView extends Activity {
	Button btn;
	Button btn2;
	Button btn3;
	private static final String TAG = "MainView";
	private static final int REQ_INTENT_CODE = 10;
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

		btn2 = (Button) findViewById(R.id.invokeExtInt2);
		btn2.setOnClickListener(new View.OnClickListener() {
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

		btn3 = (Button) findViewById(R.id.invokeExtInt3);
		btn3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				try {
					/// wait for result which invoke onActivityResult()
					Intent i = new Intent(
						"com.example.helloandroid.fromSomeone");
					i.putExtra( "i1", new Integer( 7 ) );
					i.putExtra( "i2", new Integer( 8 ) );
					startActivityForResult(i, REQ_INTENT_CODE);
				}catch (Exception e) {
					Log.d(TAG, e.toString());
				}
			}
		});
    }

	//protected void onActivityResult(int requestCode, int resultCode, 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
			Log.d(TAG,"@@ onActivityResult @@, requestCode="+requestCode
					+"; resultCode="+resultCode);
			switch(requestCode) {
				case REQ_INTENT_CODE:
					if (data != null){
						Bundle extras = data.getExtras();
						if (extras != null){
							Integer r = data.getExtras().getInt("r");
							Log.d(TAG,"@@ onActivityResult - REQ_INTENT_CODE @@ r="+r.intValue());
							if (r != null ){
								TextView t = (TextView)findViewById(R.id.result);
								t.setText(r.toString());
							}
						}else{
							Log.d(TAG,"@@ extras is null @@");
						}
					}else{
							Log.d(TAG,"@@ intent is null @@");
					}
				break;
		}
	}
}
