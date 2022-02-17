package com.example.cse225ca1sharanya

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AeroplaneReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val isApModeEnabled = intent?.getBooleanExtra("state",false)?:return
        if (isApModeEnabled){
            Toast.makeText(context,"Aeroplane mode Enabled", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Aeroplane mode Disabled", Toast.LENGTH_LONG).show()
        }

    }
}