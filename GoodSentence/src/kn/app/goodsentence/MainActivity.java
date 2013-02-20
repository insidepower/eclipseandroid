package kn.app.goodsentence;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";
    public int isRandom = 0;
    public int isResetToZero;
    public boolean isRandomValueChange = false;
    public boolean isResetToZeroValueChange = false;
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
        intent.setAction(GoodSentenceService.UPDATE_FROM_MAIN);
        CharSequence toasttext = "";
        if (isRandomValueChange){
            intent.putExtra(GoodSentenceService.SET_RANDOM, isRandom);
            isRandomValueChange = false;
            if (1==isRandom){
                toasttext="Quote is randomized; ";
            }else{
            	toasttext="Quote is not randomized; ";
            }
        }else{
            intent.putExtra(
                    GoodSentenceService.SET_RANDOM,
                    GoodSentenceService.NOT_SET);
        }

        if (isResetToZeroValueChange){
            intent.putExtra(GoodSentenceService.RESET_TO_START, isResetToZero);
            isResetToZeroValueChange = false;
            if (1==isResetToZero){
                toasttext=toasttext+"Quote is reset to first sentence;";
            }
        }else{
            intent.putExtra(
                    GoodSentenceService.RESET_TO_START,
                    GoodSentenceService.NOT_SET);
        }
        Toast.makeText(getApplicationContext(), toasttext,
                Toast.LENGTH_SHORT).show();
        getApplicationContext().startService(intent);

    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox_isRandom:
                isRandomValueChange = true;
                if(checked){
                    isRandom = 1;
                } else {
                    isRandom = 0;
                }
                break;
            case R.id.checkbox_isResetToBegin:
                isResetToZeroValueChange = true;
                if(checked){
                    isResetToZero = 1;
                } else {
                    isResetToZero = 0;
                }
                break;
            default:
        }
    }
}
