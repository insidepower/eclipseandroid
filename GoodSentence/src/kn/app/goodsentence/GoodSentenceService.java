package kn.app.goodsentence;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class GoodSentenceService extends Service {
    public static final String UPDATE = "update";
    public static final String TAG = "GdSenteceSrv";
    public static int num = 0;
    
    public PendingIntent createPendingIntent(
            Context context, String command, int appWidgetId) {
        Intent intent = new Intent(context, GoodSentenceService.class);
        intent.setAction(command);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        return (PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    @Override
    public void onStart(Intent intent, int startId) {
        //String command = intent.getAction();
        int appWidgetId = intent.getExtras().getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID);
        RemoteViews rv = new RemoteViews(getApplicationContext()
                .getPackageName(),R.layout.widget);
        AppWidgetManager appWidgetManager = AppWidgetManager
                .getInstance(getApplicationContext());
        PendingIntent newPI = createPendingIntent(
                getApplicationContext(), UPDATE, appWidgetId);
        rv.setTextViewText(R.id.goodtext, "test"+num);
        rv.setOnClickPendingIntent(R.id.widgetlayout, newPI);
        ++num;
        appWidgetManager.updateAppWidget(appWidgetId,rv);
        Log.i(TAG, "onStart");
        this.stopSelf();
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

 
}
