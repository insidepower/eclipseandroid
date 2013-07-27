package happygrass.app.gift;

import happygrass.app.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class GalleryActivity extends Activity {
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
