package kn.android.example.listviewexample;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * Created by knxy on 8/29/13.
 */
public class MainBtnArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] btnNames;

    public MainBtnArrayAdapter(Context context, String[] btnText) {
        super(context, R.layout.listbutton, btnText);
        this.context = context;
        this.btnNames = btnText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listbutton, parent, false);
        Button btnView = (Button) rowView.findViewById(R.id.mainbtn);
        btnView.setText(btnNames[position]);
        btnView.setOnClickListener(activityBtnListener);

        return rowView;
    }

    public OnClickListener activityBtnListener = new OnClickListener(){
      @Override
      public void onClick(View v){
            String activityName = ((Button)v).getText().toString();
            Toast.makeText(context, activityName, Toast.LENGTH_SHORT).show();
      }
    };
}
