package kn.example;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class customview extends Activity {
	private ArrayList<String> todoItems;
	EditText myEditText;
	ArrayAdapter<String> aa;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        myEditText = (EditText)findViewById(R.id.myEditText);
        ListView myListView = (ListView) findViewById(R.id.customListView);
        Button btnAddItem = (Button) findViewById(R.id.btnAddItem);
        
        todoItems = new ArrayList<String> ();
        int resID = R.layout.customlistlayout;
        aa = new ArrayAdapter<String>(this, resID, todoItems);
        myListView.setAdapter(aa);
    }
    
    public void addNewItem(View view) {
    	todoItems.add(0,myEditText.getText().toString());
    	myEditText.setText("");
        aa.notifyDataSetChanged();
    }
    
    public void hideEditText(View view) {
  	  myEditText.setVisibility(View.GONE);
    }
    
    public void showEditText(View view) {
  	  myEditText.setVisibility(View.VISIBLE);
	  myEditText.requestFocus();
    }

}