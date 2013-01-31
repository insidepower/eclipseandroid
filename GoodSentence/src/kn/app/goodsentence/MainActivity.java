package kn.app.goodsentence;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";
    public int isRandom = 0;
    public boolean isResetToZero;
    SharedPreferences setting;
    public GoodSentenceDatabase db = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //getActionBar().setDisplayHomeAsUpEnabled(false);
		setContentView(R.layout.option_menu);

        /// getting user old preference
        if ( null == db ) {
            db = new GoodSentenceDatabase(getApplicationContext());
            isRandom = db.read_info(GoodSentenceDatabase.IS_RANDOM);
        }
        //setting = getSharedPreferences(PREFS_NAME,  MODE_PRIVATE);
        //isRandom = setting.getBoolean(IS_RANDOM, false);
        CheckBox cb_isRandom =
            (CheckBox) findViewById(R.id.checkbox_isRandom);
        if(1==isRandom) {
        	cb_isRandom.setChecked(true);
        }
	}

    @Override
    public void onStop() {
        super.onStop();
        savePreference(null);
        db.close();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

    public void savePreference(View view){
        //setting = getSharedPreferences(PREFS_NAME,  MODE_PRIVATE);
        //SharedPreferences.Editor editor = setting.edit();
        //editor.putBoolean(IS_RANDOM, isRandom);
        //editor.commit();
        //db.setRandom(isRandom);
        Intent intent =
            new Intent(getApplicationContext(), GoodSentenceService.class);
        intent.setAction(GoodSentenceService.SET_RANDOM);
        intent.putExtra(GoodSentenceService.SET_RANDOM, isRandom);
        getApplicationContext().startService(intent);

    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox_isRandom:
                if(checked){
                    isRandom = 1;
                } else {
                    isRandom = 0;
                }
                break;
            case R.id.checkbox_isResetToBegin:
                if(checked){
                    isResetToZero = true;
                } else {
                    isResetToZero = false;
                }
                break;
            default:
        }
    }
}
