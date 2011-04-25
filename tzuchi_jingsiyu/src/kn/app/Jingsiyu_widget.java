package kn.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

public class Jingsiyu_widget extends AppWidgetProvider{

	TextView txt_jingsiyu = null;
	String str_jingsiyu = null;
	String info = null;
	int current_txt_index = 0;
	Context mycontext = null;
	int appWidgetId = 0;
	AppWidgetManager myappWidgetManager = null;
	RemoteViews myviews = null;
	public static final int UPDATE_RATE = 3500;
	final static String TAG = "jingsiyu_widget"; 
	final static String ALARM_RATE = "ALARM_RATE";
	int total_num_call = 1;

	public void onDeleted(Context context, int[] appWidgetIds) {
		for (int appWidgetId : appWidgetIds) { 
			setAlarm(context, appWidgetId, -1);
		}
	}
	public void onDisabled(Context context) {
		context.stopService(new Intent(context, jingsiyu_service.class));
		super.onDisabled(context);
	}

	public void onEnabled(Context context) {
		mycontext = context;
		getFileContent(true);
	}

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int N = appWidgetIds.length;

		// Perform this loop procedure for each App Widget that belongs to this provider
		// as we can create a few widget, this will update all?
		Log.i(TAG, "called "+total_num_call);
		total_num_call++;
		for (int appWidgetId : appWidgetIds) {
			setAlarm(context, appWidgetId, UPDATE_RATE);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	public static void setAlarm(Context context, int appWidgetId, int updateRate) {

		PendingIntent newPending = makeControlPendingIntent(context,jingsiyu_service.UPDATE,appWidgetId);
		AlarmManager alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		if (updateRate >= 0) {
			alarms.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), updateRate, newPending);
		} else {
			// on a negative updateRate stop the refreshing 
			alarms.cancel(newPending);
		}
	}

	public static PendingIntent makeControlPendingIntent(Context context, String command, int appWidgetId) {
		Intent active = new Intent(context,jingsiyu_service.class);
		active.setAction(command);
		active.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		active.putExtra(ALARM_RATE, UPDATE_RATE);
		//this Uri data is to make the PendingIntent unique, so it wont be updated by FLAG_UPDATE_CURRENT
		//so if there are multiple widget instances they wont override each other
		//Uri data = Uri.withAppendedPath(Uri.parse("countdownwidget://widget/id/#"+command+appWidgetId), String.valueOf(appWidgetId));
		//active.setData(data);
		return(PendingIntent.getService(context, 0, active, PendingIntent.FLAG_UPDATE_CURRENT));
	}


	public void getNextSentence(View view) {
		getFileContent(true);
		myviews.setTextViewText(R.id.txt_jingsi_widget, str_jingsiyu);
		myappWidgetManager.updateAppWidget(appWidgetId, myviews);
	}

	public void getFileContent(Boolean isReadOneLine){
		try {
			//InputStream instream = openFileInput("myfilename.txt");
			InputStream instream = mycontext.getResources().openRawResource(R.raw.jingsiyu);
			InputStreamReader inputreader = new InputStreamReader(instream);
			BufferedReader buffreader = new BufferedReader(inputreader);

			String line;
			info = buffreader.readLine();
			if (isReadOneLine) {
				int i = 1;
				while (current_txt_index > i++) {
					buffreader.readLine();
				}
				str_jingsiyu = buffreader.readLine();
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
