package kn.android.example.listviewexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.ListActivity;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] btnName = new String[] {
                "Example1", "Example2","Example3", "Example4", "Example5",
                "Example1", "Example2","Example3", "Example4", "Example5",
                "Example1", "Example2","Example3", "Example4", "Example5",};
        MainBtnArrayAdapter adapter = new MainBtnArrayAdapter(this, btnName);

        ListView mylist = (ListView) findViewById(R.id.listView);
        mylist.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
