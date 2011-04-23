package kn.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class jingsiyu_act extends Activity {
	TextView txt_jingsiyu = null;
	String str_jingsiyu = null;
	String info = null;
	int current_txt_index = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txt_jingsiyu = (TextView)findViewById(R.id.txt_jingsi);
		getFileContent(true);
	}

	public void getFileContent(Boolean isReadOneLine){
		try {
			//InputStream instream = openFileInput("myfilename.txt");
			InputStream instream = getResources().openRawResource(R.raw.jingsiyu);
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
				txt_jingsiyu.setText(str_jingsiyu);
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

