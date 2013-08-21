
package com.samyu.demos.sample;

import com.samyu.demos.R;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class OverlayService extends Service {
    WindowManager mWM; // WindowManager
    WindowManager.LayoutParams mParams; // WindowManager参数

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        mParams.gravity = Gravity.CENTER;

        mWM = (WindowManager) getSystemService(WINDOW_SERVICE);

        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.set);
        iv.setOnTouchListener(new View.OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Toast.makeText(getApplicationContext(), "Hi, Don't touch me.", 5000).show();
                }
                return false;
            }
        });
        mWM.addView(iv, mParams);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}
