package com.mncomunity1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mncomunity1.PrefManager;
import com.mncomunity1.R;


public class Splash extends Activity   {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    PrefManager pref;
    String REGID = "";
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(Splash.this, LoginActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }




}