package kn.example;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class customview extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText myEditText = (EditText)findViewById(R.id.myEditText);
        ListView myListView = (ListView) findViewById(R.id.customListView);
        
        final ArrayList<String> todoItems = new ArrayList<String> ();
        int resID = R.layout.customlistlayout;
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, resID, todoItems);
        myListView.setAdapter(aa);
    }
}