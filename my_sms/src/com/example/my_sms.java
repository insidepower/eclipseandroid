package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class my_sms extends BroadcastReceiver {
	private static final String TAG = "my_sms";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
	}
}
