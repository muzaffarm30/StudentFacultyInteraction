package com.android.studentfaculty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.studentfaculty.utils.SharedPref;
import com.example.androidattendancesystem.R;

public class Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2500;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        SharedPref.init(Splash.this);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if(SharedPref.read("type","").equalsIgnoreCase("faculty")){
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(Splash.this, FacultyHomeActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }else if(SharedPref.read("type","").equalsIgnoreCase("student")){
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(Splash.this,StudentMaterialsViewActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();

                }else if(SharedPref.read("type","").equalsIgnoreCase("admin")){
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(Splash.this, AdminHomeActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();

                }else {
                    Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
