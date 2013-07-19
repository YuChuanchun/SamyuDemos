package com.samyu.demos.graphics.carousel.main;

import com.samyu.demos.graphics.carousel.controls.Carousel;
import com.samyu.demos.graphics.carousel.controls.CarouselAdapter;
import com.samyu.demos.graphics.carousel.controls.CarouselAdapter.OnItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.samyu.demos.R;

public class CarouselActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carousel);
        Carousel carousel = (Carousel) findViewById(R.id.carousel);
        carousel.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(CarouselAdapter<?> parent, View view,
                    int position, long id) {
                Toast.makeText(CarouselActivity.this, "Position=" + position, Toast.LENGTH_SHORT)
                        .show();
            }

        });
    }

}
