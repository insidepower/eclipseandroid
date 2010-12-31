package com.example.viewOnClickShowTime;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

/* code-base button */
/*
public class viewOnClickShowTime extends Activity implements View.OnClickListener {
	Button btn;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		btn = new Button(this);
		btn.setOnClickListener(this);
		updateTime();
		setContentView(btn);
	}
	public void onClick(View view) {
		updateTime();
	}
	private void updateTime() {
		btn.setText(new Date().toString());
	}
}
 */

/* xml-base button */
/*
public class viewOnClickShowTime extends Activity implements View.OnClickListener {
	Button btn;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		btn = (Button) findViewById(R.id.mybutton);
		btn.setOnClickListener(this);
		updateTime();
	}
	public void onClick(View view) {
		updateTime();
	}
	private void updateTime() {
		btn.setText(new Date().toString());
	}
}
 */

/* EditText */
/*
public class viewOnClickShowTime extends Activity{
	@Override
	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.edittext);

		EditText fld = (EditText) findViewById(R.id.myfield);
		fld.setText("haha Testing");
	}
}
 */

/* linear layout */
/*
public class viewOnClickShowTime extends Activity implements RadioGroup.OnCheckedChangeListener {
	RadioGroup orientation;
	RadioGroup gravity;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.myview);
		orientation=(RadioGroup)findViewById(R.id.orientation);
		orientation.setOnCheckedChangeListener(this);
		gravity=(RadioGroup)findViewById(R.id.gravity);
		gravity.setOnCheckedChangeListener(this);
	}
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group==orientation) {
			if (checkedId==R.id.horizontal) {
				orientation.setOrientation(LinearLayout.HORIZONTAL);
			}
			else {
				orientation.setOrientation(LinearLayout.VERTICAL);
			}
		}
		else if (group==gravity) {
			if (checkedId==R.id.left) {
				gravity.setGravity(Gravity.LEFT);
			}
			else if (checkedId==R.id.center) {
				gravity.setGravity(Gravity.CENTER_HORIZONTAL);
			}
			else if (checkedId==R.id.right) {
				gravity.setGravity(Gravity.RIGHT);
			}
		}
	}
}
*/

/* relative layout */ /* table layout */ /* scroll layout */
public class viewOnClickShowTime extends Activity{
	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		//setContentView(R.layout.myrelativelayout);
		//setContentView(R.layout.mytablelayout);
		setContentView(R.layout.myscrolllayout);
	}
}