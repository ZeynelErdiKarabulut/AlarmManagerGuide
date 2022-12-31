package com.erdi.alarmmanagerguide

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        Toast.makeText(context,"Alarm triggered: $message",Toast.LENGTH_LONG).show()
    }
}