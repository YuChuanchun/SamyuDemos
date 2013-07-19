package com.samyu.demos.sample;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import com.samyu.demos.R;

public class NotificationSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        String tickerText  = "wa haha";
        Notification notif = new Notification(R.drawable.ic_launcher, tickerText,
                System.currentTimeMillis());
        mNotificationManager.notify(111, notif);
    }
    
}
