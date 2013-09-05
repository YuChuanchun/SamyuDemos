
package com.samyu.demos.sample;

import java.util.Locale;

import com.samyu.demos.R;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TextToSpeechSample extends Activity {
    private static final String TAG = "TextToSpeechSample";
    private TextToSpeech mSpeech;  
    private Button btn;  
    private EditText mEditText; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_to_speech);
        btn = (Button) findViewById(R.id.start);
        mEditText = (EditText) findViewById(R.id.editText);
        btn.setEnabled(false);
        // 创建TextToSpeech对象
        mSpeech = new TextToSpeech(this, new OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mSpeech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e(TAG, "not use");
                    } else {
                        btn.setEnabled(true);
                    }
                }
            }
        });
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpeech.speak(mEditText.getText().toString(),
                        TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mSpeech != null) {
            mSpeech.stop();
            mSpeech.shutdown();
        }
        super.onDestroy();
    }

}
