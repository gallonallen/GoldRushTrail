package org.goldrushtrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity
{
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView)findViewById(R.id.image_splash);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        final Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                super.run();
                try
                {
                    sleep(3000);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }//run()

        };//TODO: Question for Karl: Why does the code, following the instantiation of the Thread object, look weired?
        thread.start();
    }//onCreate()
}//class
