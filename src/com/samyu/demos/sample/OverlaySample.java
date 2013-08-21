
package com.samyu.demos.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.samyu.demos.R;

public class OverlaySample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overlay);
       
        Button btn = (Button)findViewById(R.id.start);
        btn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(OverlaySample.this, OverlayService.class);
                startService(intent);
            }
        });
    }

}
