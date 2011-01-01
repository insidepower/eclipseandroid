package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class my_second_rx extends BroadcastReceiver {
	private static final String TAG = "my_second_rx";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
	}
}
