<?xml version="1.0" encoding="utf-8"?>
<RadioGroup xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">
    <RadioButton android:id="@+id/radio_pirates"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/pirates"
        android:onClick="onRadioButtonClicked"/>
    <RadioButton android:id="@+id/radio_ninjas"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/ninjas"
        android:onClick="onRadioButtonClicked"/>
</RadioGroup>


public void onRadioButtonClicked(View view) {
    // Is the button now checked?
    boolean checked = ((RadioButton) view).isChecked();
    
    // Check which radio button was clicked
    switch(view.getId()) {
        case R.id.radio_pirates:
            if (checked)
                // Pirates are the best
            break;
        case R.id.radio_ninjas:
            if (checked)
                // Ninjas rule
            break;
    }
}

[---------------------------------------------------------------------------------------------------- ]
/// --- get the selected index of a RadioGroup --- ///

int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
View radioButton = radioButtonGroup.findViewById(radioButtonID);
int idx = radioButtonGroup.indexOfChild(radioButton);


<RadioGroup android:id="@+id/group1" android:layout_width="fill_parent" android:layout_height="wrap_content" android:orientation="vertical">
    <RadioButton android:id="@+id/radio1" android:text="option 1" android:layout_width="wrap_content" android:layout_height="wrap_content" />
</RadioGroup>

RadioGroup radioGroup = findViewById(R.id.radio_group);
int index = rg.getCheckedRadioButtonId();
int index = myRadioGroup.indexOfChild(findViewById(myRadioGroup.getCheckedRadioButtonId()));
[---------------------------------------------------------------------------------------------------- ]
final RadioButton radio1 = (RadioButton)findViewById(R.id.rb_sat1E);
radio1.setChecked(true);
radio2.setChecked(false);

[---------------------------------------------------------------------------------------------------- ]
private void createRadioButton() {
    final RadioButton[] rb = new RadioButton[5];
    RadioGroup rg = new RadioGroup(this); //create the RadioGroup
    rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
    for(int i=0; i<5; i++){
        rb[i]  = new RadioButton(this);
        rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
        rb[i].setText("Test");
    }
    ll.addView(rg);//you add the whole RadioGroup to the layout
    ll.addView(submit); 
    submit.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            for(int i = 0; i < 5; i++) { 
                rg.removeView(rb[i]);//now the RadioButtons are in the RadioGroup
            }  
            ll.removeView(submit);
            Questions();
        }
    });   
}


<TableRow>
    <RadioGroup
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:id="@+id/radiobuttons">
     </RadioGroup>
</TableRow>

public void makeRadioButtons(Vector tmpVector, int i,
LinearLayout.LayoutParams lp)
{
    RadioButton rb = new RadioButton(this);
    rb.setText((String) tmpVector.elementAt(i));
    //rg is private member of class which refers to the radio group which you can find by id.
    rg.addView(rb, 0, lp);

}
[---------------------------------------------------------------------------------------------------- ]
[---------------------------------------------------------------------------------------------------- ]
[---------------------------------------------------------------------------------------------------- ]
[---------------------------------------------------------------------------------------------------- ]
[---------------------------------------------------------------------------------------------------- ]
