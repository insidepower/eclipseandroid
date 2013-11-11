package com.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class learning_app extends Activity {
	private OnClickListener mBtn2Listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			Toast.makeText(learning_app.this,
					"hihi this is toast2", Toast.LENGTH_SHORT).show();
		}
	};

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		/// internal declaration - more efficient
		Button btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(new OnClickListener () {
			public void onClick(View arg0){
				Toast.makeText(learning_app.this,
					"hihi this is toast1", Toast.LENGTH_SHORT).show();
			}
		});

		/// external declaration
		Button btn2 = (Button) findViewById(R.id.btn2);
		btn2.setOnClickListener(mBtn2Listener);

		/// dialog box
		Button btn3 = (Button) findViewById(R.id.btn3);
		btn3.setOnClickListener(new OnClickListener () {
			public void onClick(View arg0){
				AlertDialog diaBox = makeAndShowDialogBox();
				diaBox.show();
			}
		});
    }

	private AlertDialog makeAndShowDialogBox(){
		AlertDialog mDlg = new AlertDialog.Builder(this)
			.setTitle("Notification").setMessage("This is a pop up")
			.setPositiveButton("Ok",
					new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog,
							int whichButton){
				Toast.makeText(learning_app.this,
					"hihi this is toast3-ok", Toast.LENGTH_SHORT).show();
						}
			})
			.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog,
							int whichButton){
				Toast.makeText(learning_app.this,
					"hihi this is toast3-cancel", Toast.LENGTH_SHORT).show();
						}
					})
			.create();
			return mDlg;
	}
}
