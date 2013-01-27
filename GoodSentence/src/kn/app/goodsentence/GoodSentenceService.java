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
    
    /// create a PendingIntent which will be executed upon clicked
    public PendingIntent createPendingIntent(
            Context context, String command, int appWidgetId) {
        Intent intent = new Intent(context, GoodSentenceService.class);
        intent.setAction(command);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        /// must set Uri data, else all widget are treat as same by android
        /// e.g. android will treat total instance as one
        Uri data = Uri.withAppendedPath(
                Uri.parse("String" + "://widget/id/")
                ,String.valueOf(appWidgetId));
        intent.setData(data);

        return (PendingIntent.getService(context, 0, intent, 
                    PendingIntent.FLAG_UPDATE_CURRENT));
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
        rv.setTextViewText(R.id.goodtext, getFileContent());
        rv.setOnClickPendingIntent(R.id.widgetlayout, newPI);
        appWidgetManager.updateAppWidget(appWidgetId,rv);
        //this.stopSelf();
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
