package happygrass.app.gift;

import happygrass.app.R;
import happygrass.app.imported.musicg.demo.android.WhistleActivity;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.content.Intent;


public class MainActivity extends Activity {

	public static int question_no = 0;
	public int answer[] = {};
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstlogin_question);

		ImageView img_think = (ImageView) findViewById(R.id.imageView_thinking);
		img_think.setBackgroundResource(R.drawable.animate_thinking);
		AnimationDrawable img_think_ani = (AnimationDrawable) img_think.getBackground();
		img_think_ani.start();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

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
			Intent i = new Intent(getApplicationContext(), WhistleActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			finish();
		  }
	};

	public void answerOnClick(View view) {
	}
}
