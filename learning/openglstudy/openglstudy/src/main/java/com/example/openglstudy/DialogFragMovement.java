package com.example.openglstudy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

/**
 * Created by knxy on 11/21/13.
 *
 * Customer dialog layout to change the movement value
 *
 */
public class DialogFragMovement extends DialogFragment {

    public interface DialogFragMovementListener {
        public void onMovementDialogPositiveClick(DialogFragment dialogFragment);
    }

    DialogFragMovementListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.diagloglayoutmovement, null))
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // call the relevant methods to change the value
                        mListener.onMovementDialogPositiveClick(DialogFragMovement.this);
                    }
                })
                .setNegativeButton(R.string.cancel, null);
        return builder.create();
    }
}
