package happygrass.app.guess;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static int question_no = 0;
	public int answer[] = {};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstlogin_question);
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
			tv.setText("radbtn_1");
			break;
		case R.id.radbtn_2:
			tv.setText("radbtn_2");
			break;
		default:	
			tv.setText("error");
		}
	}

	public void answerOnClick(View view) {
	}
}
