package kn.example;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    
	/// -------------- EditText < start > ------------------ ///
    public void hideEditText(View view) {
  	  myEditText.setVisibility(View.GONE);
    }
    
    public void showEditText(View view) {
  	  myEditText.setVisibility(View.VISIBLE);
	  myEditText.requestFocus();
    }
	/// -------------- EditText < end > ------------------ ///

	/// -------------- option menu < start > ------------------ ///
    final private int FIRST_ID = 1;
    final private int SECOND_ID = 2;
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);

		/// create and add new menu items 
		/// add (int groupId, int itemId, int order, int titleRes)
		MenuItem firstItem = menu.add(0, FIRST_ID, Menu.NONE, "first");
		MenuItem secondItem = menu.add(0, SECOND_ID, Menu.NONE, "second");
		firstItem.setIcon(R.drawable.icon);
		/// setShortcut (char numShortcut, char alphaShortcut)
		firstItem.setShortcut('0', 'a');
		secondItem.setShortcut('1', 'b');

    	return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch(item.getItemId()) {
			case (FIRST_ID): {
				Toast.makeText(this, " FIRST_ID",
						Toast.LENGTH_SHORT).show();
				//break;
				return true;
			}
			case (SECOND_ID): {
				Toast.makeText(this, " SECOND_ID",
						Toast.LENGTH_SHORT).show();
				return true;
			}
		}
		return false;
	}
	/// -------------- option menu < end > ------------------ ///

}
