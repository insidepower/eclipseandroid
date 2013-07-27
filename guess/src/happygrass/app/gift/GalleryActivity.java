package happygrass.app.gift;

import happygrass.app.R;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.os.Handler;

public class GalleryActivity extends Activity {
	static GalleryActivity act;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		act = this;

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
								WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		setContentView(R.layout.gallery_act);

		ImageView img_cake = (ImageView) findViewById(R.id.img_cake);
		img_cake.setBackgroundResource(R.drawable.animate_cake);
		AnimationDrawable img_cake_ani = (AnimationDrawable) img_cake.getBackground();
		img_cake_ani.start();

		Animation ani_text_up = AnimationUtils.loadAnimation(
									this, R.anim.anim_text_scroll);
		TextView tv = (TextView) findViewById(R.id.gallery_text);
		tv.startAnimation(ani_text_up);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				ImageView img_dandelion = (ImageView) 
											findViewById(R.id.dandelion);
				img_dandelion.setImageResource(R.drawable.dandelion_knxy);
				ObjectAnimator ani_dandelion = ObjectAnimator.ofFloat(
							img_dandelion, "alpha", 0.0f, 1.0f);
				ani_dandelion.setDuration(4000);
				ani_dandelion.start();
			}
		}, 35000);


		try {
			mp = new MediaPlayer();
			mp.setDataSource("/sdcard/music/song.mp3");
			mp.prepare();
			mp.setVolume(1.0f, 1.0f);
			mp.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPause () {
		super.onPause();
		if (mp != null ){
			mp.stop();
			mp.release();
			mp = null;
		}
	}

	@Override
	protected void onDestroy () {
		super.onDestroy();
		if (mp != null ){
			mp.stop();
			mp.release();
			mp = null;
		}
	}
}
