package kn.app.goodsentence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class GoodSentenceService extends Service {
    /// global static
    public static final String UPDATE = "update";
    public static final String SET_RANDOM = "set_random";
    public static final String TIMER_EXPIRE = "timer_expire";
    public static final String TAG = "GdSenteceSrv";
	public static final int UPDATE_RATE = 60000;

    /// instance specific
    public InputStream instream;
    public BufferedReader buffreader = null;
    public Boolean isRandomize = false;
    public Boolean isResetToBeginOfFile = false;
    public String[] lines;
    public GoodSentenceDatabase db = null;

    /// create a PendingIntent which will be executed upon clicked
    public PendingIntent createPendingIntent(
            Context context, String command, int appWidgetId) {
        Intent intent = new Intent(context, GoodSentenceService.class);
        intent.setAction(command);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        /// must set Uri data, else all widget are treat as same by android
        /// e.g. android will treat total instance as one
        //Uri data = Uri.withAppendedPath(
        //        Uri.parse("String" + "://widget/id/")
        //        ,String.valueOf(appWidgetId));
        //intent.setData(data);

        return (PendingIntent.getService(context, 0, intent, 
                    PendingIntent.FLAG_UPDATE_CURRENT));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int appWidgetId;
        String command = intent.getAction();
        Context context = getApplicationContext();

        if(command.equals(UPDATE)){
            Log.i(TAG, "update onStartCommand");
            appWidgetId = intent.getExtras().getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID);
            RemoteViews rv = new RemoteViews(context
                    .getPackageName(),R.layout.widget);
            AppWidgetManager appWidgetManager = AppWidgetManager
                .getInstance(context);
            PendingIntent newPI = createPendingIntent(
                    context, UPDATE, appWidgetId);
            if ( null == db ) {
                Log.i(TAG, "db is null");
                db = new GoodSentenceDatabase(context);
                if (!db.checkDatabaseAvailability()){
                    Log.i(TAG, "building database");
                    rv.setTextViewText(R.id.goodtext,
                            "Initializing database... Please wait");
                    appWidgetManager.updateAppWidget(appWidgetId,rv);
                    db.constructDatabase();
                }
            }
            rv.setTextViewText(R.id.goodtext, db.read_next_quote());
            rv.setOnClickPendingIntent(R.id.widgetlayout, newPI);
            appWidgetManager.updateAppWidget(appWidgetId,rv);
            setAlarm(context, appWidgetId);
        } else if(command.equals(SET_RANDOM)){
            int flag = intent.getExtras().getInt(SET_RANDOM);
            Log.i(TAG, "set random = "+flag);
            if ( null == db ) {
                Log.i(TAG, "db is null");
                db = new GoodSentenceDatabase(context);
                if (!db.checkDatabaseAvailability()){
                    Log.i(TAG, "building database");
                    db.constructDatabase();
                }
            }
            db.setRandom(flag);
            return Service.START_NOT_STICKY;
        } else if(command.equals(TIMER_EXPIRE)){
            /// terminate this service after idle for UPDATE_RATE
            Log.i(TAG, "terminate service rate="+UPDATE_RATE);
            this.stopSelf();
		}

        /// START_NOT_STICKY == if service is killed by OS, no need to restart
        /// it again.
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        db.close();
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}


	public void setAlarm(Context context, int appWidgetId) {
		PendingIntent newPending = createPendingIntent(
                context, TIMER_EXPIRE, appWidgetId);
		AlarmManager alarms = (AlarmManager)
            context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 30);
		alarms.set(AlarmManager.RTC,
                calendar.getTimeInMillis(), newPending);
    }
}
