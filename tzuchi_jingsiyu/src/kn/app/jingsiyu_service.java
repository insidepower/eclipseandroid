package kn.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

public class jingsiyu_service extends Service{
	public static final String UPDATE = "update";
	public static final String IMMEDIATE_UPDATE = "immediate_update";
	final static String TAG = "jingsiyu_service";
	TextView txt_jingsiyu = null;
	String str_jingsiyu = null;
	String info = null;
	int current_txt_index = 0;

	public void onStart(Intent intent, int startId) {
		String command = intent.getAction();
		int appWidgetId = intent.getExtras().getInt(
				AppWidgetManager.EXTRA_APPWIDGET_ID);
		int update_rate = intent.getExtras().getInt(Jingsiyu_widget.ALARM_RATE);
		if(command.equals(IMMEDIATE_UPDATE)){
			Jingsiyu_widget.setAlarm(getApplicationContext(), appWidgetId, -1);
		}
		RemoteViews rv = new RemoteViews(getApplicationContext()
				.getPackageName(), R.layout.widget);
		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(getApplicationContext());
		getFileContent(true);
		PendingIntent newPending = makeControlPendingIntent(getApplicationContext(),IMMEDIATE_UPDATE,appWidgetId);
		rv.setTextViewText(R.id.txt_jingsi_widget, str_jingsiyu);
		rv.setOnClickPendingIntent(R.id.widgetlayout, newPending);
		appWidgetManager.updateAppWidget(appWidgetId, rv);
		super.onStart(intent, startId);

	}
	
	public static PendingIntent makeControlPendingIntent(Context context, String command, int appWidgetId) {
        Intent active = new Intent(context,jingsiyu_service.class);
        active.setAction(command);
        active.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        //this Uri data is to make the PendingIntent unique, so it wont be updated by FLAG_UPDATE_CURRENT
        //so if there are multiple widget instances they wont override each other
        //Uri data = Uri.withAppendedPath(Uri.parse("countdownwidget://widget/id/#"+command+appWidgetId), String.valueOf(appWidgetId));
        //active.setData(data);
        return(PendingIntent.getService(context, 0, active, PendingIntent.FLAG_UPDATE_CURRENT));
    }

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public void getFileContent(Boolean isReadOneLine){
			try {
				//InputStream instream = openFileInput("myfilename.txt");
				InputStream instream = getApplicationContext().getResources().openRawResource(R.raw.jingsiyu);
				InputStreamReader inputreader = new InputStreamReader(instream);
				BufferedReader buffreader = new BufferedReader(inputreader);

				String line;
				info = buffreader.readLine();
				if (isReadOneLine && instream!=null) {
					int i = 1;
					Log.i(TAG, "before");
					while (current_txt_index > i++) {
						Log.i(TAG, "inside while "+current_txt_index+" :i="+i);
						buffreader.readLine();
					}
					Log.i(TAG, "after");
					current_txt_index++;
					str_jingsiyu = buffreader.readLine();
					Log.i(TAG, str_jingsiyu);
					//txt_jingsiyu.setText(str_jingsiyu);
				}

				// close the file again
				instream.close();
			} catch (java.io.FileNotFoundException e) {	
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

}
