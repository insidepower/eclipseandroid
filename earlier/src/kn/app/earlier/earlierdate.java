package kn.app.earlier;

import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class earlierdate extends Activity {
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour, mMin, mSec;
	private static final String TAG = "Earlier";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR)-1;
		mMonth = c.get(Calendar.MONTH)+1;
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMin = c.get(Calendar.MINUTE);
		mSec = c.get(Calendar.SECOND);
		
		StringBuilder mDate = new StringBuilder();
		mDate.append(mYear);
		if(mMonth<10) mDate.append("0");
		mDate.append(mMonth);
		if(mDay<10) mDate.append("0");
		mDate.append(mDay).append(".");
		if(mHour<10) mDate.append("0");
		mDate.append(mHour);
		if(mMin<10) mDate.append("0");
		mDate.append(mMin);
		if(mSec<10) mDate.append("0");
		mDate.append(mSec);
		Log.i(TAG, "yyyy-mm-dd = "+mYear+"-"+mMonth+"-"+mDay);
		Log.i(TAG, "hh:mm:ss = "+mHour+":"+mMin+":"+mSec);
		Log.i(TAG, mDate.toString());
		
		try {
			String[] hin1 = { "date", "-s", mDate.toString()};
			Process p = Runtime.getRuntime().exec(hin1);
			p.waitFor();
			if (0!=p.exitValue()) {
				Context context = getApplicationContext();
				CharSequence text = "Not able to change date!";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finish();
    }
}