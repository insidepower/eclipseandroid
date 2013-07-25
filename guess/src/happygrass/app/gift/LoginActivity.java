package happygrass.app.gift;

import happygrass.app.R;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void loginOnClick(View view) {
		EditText et_passwd = (EditText) findViewById(R.id.txt_password);
		String passwd = et_passwd.getText().toString();
		if (passwd.equals("03012007")) {
			Intent i = new Intent(this, MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			finish();
		} else {
			AnimatorListener animeListener;
			animeListener = new AnimatorListener() {

				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animator animation) {
					TextView view_login = (TextView) findViewById(R.id.txt_login);
					view_login.setAlpha(1);

				}

				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub

				}
			};

			TextView view_login = (TextView) findViewById(R.id.txt_login);
			view_login.setText("Password incorrect. Try again.");
			//view_login.setAlpha(1);
			ObjectAnimator anime =
				ObjectAnimator.ofFloat(view_login, "alpha", 0);
			anime.addListener(animeListener);
			anime.setDuration(2000);
			anime.start();
		}
	}



}
