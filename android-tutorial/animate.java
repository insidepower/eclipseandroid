[ xml ][ image ][ gif ][ animated ]
duration is in ms

create a animated.xml
xmlns:android="http://schemas.android.com/apk/res/android"
<animation-list android:id="selected" android:oneshot="false">
<item android:drawable="@drawable/connected01" android:duration="50" />
<item android:drawable="@drawable/connected02" android:duration="50" />
<item android:drawable="@drawable/connected03" android:duration="50" />
<item android:drawable="@drawable/connected04" android:duration="50" />
<item android:drawable="@drawable/connected05" android:duration="50" />
</animation-list>

then use it
    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@layout/animate_thinking" />


 // Load the ImageView that will host the animation and
 // set its background to our AnimationDrawable XML resource.
 ImageView img = (ImageView)findViewById(R.id.spinning_wheel_image);
 img.setBackgroundResource(R.drawable.spin_animation);

 // Get the background, which has been compiled to an AnimationDrawable object.
 AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

 // Start the animation (looped playback by default).
 frameAnimation.start();

--------
AnimationDrawable rocketAnimation;

public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);

  ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
  rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
  rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
}

public boolean onTouchEvent(MotionEvent event) {
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    rocketAnimation.start();
    return true;
  }
  return super.onTouchEvent(event);
}
