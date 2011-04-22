package kn.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RemoteViews;
import android.widget.TextView;

public class ExampleAppWidgetProvider extends AppWidgetProvider{
	
	TextView txt_jingsiyu = null;
	String str_jingsiyu = null;
	String info = null;
	int current_txt_index = 0;
	Context mycontext = null;
	int appWidgetId = 0;
	AppWidgetManager myappWidgetManager = null;
	RemoteViews myviews = null;

    public void onEnabled(Context context) {
    	mycontext = context;
		getFileContent(true);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        // as we can create a few widget, this will update all?
        for (int i=0; i<N; i++) {
        	mycontext = context;
        	getFileContent(true);
            appWidgetId = appWidgetIds[i];
            myappWidgetManager = appWidgetManager;

            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, tzuchi_jingsiyu.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener to the button
            myviews = new RemoteViews(context.getPackageName(), R.layout.widget);
            //views.setOnClickPendingIntent(R.id.btn_invoke_int, pendingIntent);
            myviews.setOnClickPendingIntent(R.id.txt_jingsi_widget, pendingIntent);
            myviews.setTextViewText(R.id.txt_jingsi_widget, str_jingsiyu);

            // Tell the AppWidgetManager to perform an update on the current App Widget
            appWidgetManager.updateAppWidget(appWidgetId, myviews);
        }
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
