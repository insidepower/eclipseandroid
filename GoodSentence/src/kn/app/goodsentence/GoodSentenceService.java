package kn.app.goodsentence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    public static final String TAG = "GdSenteceSrv";

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
        //String command = intent.getAction();
        int appWidgetId = intent.getExtras().getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID);
        Context context = getApplicationContext();
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

        //rv.setTextViewText(R.id.goodtext, getFileContent());
        rv.setTextViewText(R.id.goodtext, db.read_next_quote());
        rv.setOnClickPendingIntent(R.id.widgetlayout, newPI);
        appWidgetManager.updateAppWidget(appWidgetId,rv);

        /// stop the service
        //this.stopSelf();

        /// START_NOT_STICKY == if service is killed by OS, no need to restart
        /// it again.
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        // close the file again
        try {
			instream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

    public String getFileContent(){
    	String line = null;
    	try {
            if (buffreader==null || isRandomize || isResetToBeginOfFile) {
                Log.i(TAG, "buffer is null, isRandomize="+isRandomize);
                //InputStream instream = openFileInput("myfilename.txt");
                instream = getApplicationContext().getResources()
                    .openRawResource(R.raw.goodsentence);
                InputStreamReader inputreader = new InputStreamReader(instream);
                buffreader = new BufferedReader(inputreader);
            }

            line = buffreader.readLine();

        } catch (java.io.FileNotFoundException e) {	
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return line;
    }
}
