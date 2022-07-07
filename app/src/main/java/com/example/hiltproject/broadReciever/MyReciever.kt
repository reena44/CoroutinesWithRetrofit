package com.example.hiltproject.broadReciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
    }
}