package com.example.openglstudy;

import android.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener, DialogFragMovement.DialogFragMovementListener {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    public static final String DRAW_SHAPE = "draw_poly";
    public static final String DRAW_SHAPE_MOVE = "draw_poly_move";

    private Fragment frag;
    private int sectionNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new String[] {
                                getString(R.string.title_section1),
                                getString(R.string.title_section2),
                                getString(R.string.title_section3),
                                getString(R.string.title_section4),
                        }),
                this);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getSupportActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getSupportActionBar().getSelectedNavigationIndex());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_try:
                DialogFragMovement dialog = new DialogFragMovement();
                dialog.show(getSupportFragmentManager(), "MovementChange");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.
        //getSupportFragmentManager().beginTransaction()
        //        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
        //       .commit();
        frag = selectFragment(position + 1);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, frag)
                .commit();
        return true;
    }

    public Fragment selectFragment(int sectionNum) {
        Fragment frag;
        this.sectionNum = sectionNum;
            switch(sectionNum){
                case 1: frag = new BlankOpenGl(); break;
                case 2: frag = new TouchEnabled(); break;
                case 3: frag = new DrawShape(DRAW_SHAPE); break;
                case 4: frag = new DrawShape(DRAW_SHAPE_MOVE); break;
                default: frag = new BlankOpenGl(); break;
            }
        return frag;
    }

    @Override
    public void onMovementDialogPositiveClick(android.support.v4.app.DialogFragment dialogFragment) {
        //if (frag instanceof DrawShape) {
        if ( 3 == sectionNum ) {
            boolean isReloadIdentify = false;
            CheckBox chkbox =  ((DrawShape) frag).chkboxIsReload;
            if ( chkbox.isChecked() ) {
                isReloadIdentify = true;
            }


            try {
                ((DrawShapeMoveRender) ((DrawShapeSurfaceView) ((DrawShape) frag).mGLView).mRenderer)
                        .setResetGl(isReloadIdentify);
            } catch (NullPointerException e) {
                String isNull = "null pointer: ";
                final String TAG = "MainActiviy";
                Log.e(TAG, isNull);
            }
        }
    }

    /**
     * A placeholder fragment containing a simple view. - not in used
     */

    /*public static class PlaceholderFragment extends Fragment {
        *//**
         * The fragment argument representing the section number for this
         * fragment.
         *//*
        private static final String ARG_SECTION_NUMBER = "section_number";

        *//**
         * Returns a new instance of this fragment for the given section
         * number.
         *//*
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }*/

}
