package happygrass.app.gift;

import happygrass.app.gift.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class GalleryActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_act);

		MediaPlayer mp=new MediaPlayer();
		try {
			mp.setDataSource("/sdcard/mp3/song.mp3");
			mp.prepare();
			mp.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
