package kn.app.earlier;

import java.io.IOException;
import java.util.Calendar;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class setActualTime extends Service{
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour, mMin, mSec;
	private static final String TAG = "EarlierService";
	private Handler mHandler = new Handler();

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		//Toast.makeText(this, "inside services", Toast.LENGTH_SHORT).show();
		//Log.d(TAG, "onCreate");
		//getDate();
		//mYear+=1; // set back to original year
		//setDate(strBuilder(mYear, mMonth, mDay, mHour, mMin, mSec));
		mHandler.postDelayed(mUpdateTimeTask, 20000);
	}

	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() {
			getDate();
			mYear+=1; // set back to original year
			setDate(strBuilder(mYear, mMonth, mDay, mHour, mMin, mSec));
			onDestroy();
			//finish();
		}
	};

	@Override
	public void onDestroy() {
		Toast.makeText(this, TAG+" onDestroy()", Toast.LENGTH_SHORT).show();
		//Log.d(TAG, "onDestroy()");
	}

	private void getDate() {
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH)+1;	// Month is 0 based so add 1
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMin = c.get(Calendar.MINUTE);
		mSec = c.get(Calendar.SECOND);
	}

	private String strBuilder(int year, int mon, int day, 
			int hr, int min, int sec) {
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

		return mDate.toString();
	}

	private void setDate(String newDate) {
		try {
			String[] hin1 = { "date", "-s", newDate};
			Process p = Runtime.getRuntime().exec(hin1);
			p.waitFor();
			if (0!=p.exitValue()) {
				Context context = getApplicationContext();
				CharSequence text = "Not able to change date!";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			Log.i(TAG, "p.exitValue() = " + p.exitValue());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
