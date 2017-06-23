package org.goldrushtrail;


import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity
{
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar = getSupportActionBar();
        if (Build.VERSION.SDK_INT < 16)//before Jelly Bean Versions
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else // Jelly Bean and up
        {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int ui = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(ui);

            //Hide actionbar
            actionBar.hide();
        }
        imageView = (ImageView)findViewById(R.id.image_splash);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        final Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                super.run();
                try
                {
                    sleep(1500);
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
