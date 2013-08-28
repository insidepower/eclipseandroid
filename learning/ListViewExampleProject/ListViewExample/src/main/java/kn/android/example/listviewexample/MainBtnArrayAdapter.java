package kn.android.example.listviewexample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

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

        return rowView;
    }
}
