package org.goldrushtrail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class NavigationItemAboutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_item_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TODO: Review "onClick" methods with a switch statement to differentiate the Views that are clicked
        /*TextView textView  =(TextView)findViewById(R.id.about_paragraphs);
        textView.setMovementMethod(new ScrollingMovementMethod());
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("\n\n\t\t\tThe Gold Rush Trail Foundation, a non-profit public-benefit corporation, is sponsored by The Mechanics\' Institute, The California Historical Society, and The Society of California Pioneers.\n\n")
               .append(" ", new ImageSpan(getApplicationContext(),R.drawable.sponsor1),0)
                .append("\n")
               .append(" ", new ImageSpan(getApplicationContext(),R.drawable.sponsor2),0)
                .append("\n")
                .append(" ", new ImageSpan(getApplicationContext(),R.drawable.sponsor3),0)
               .append("\n\n\n\n\n");
        textView.setText(builder);

        Log.d("Spannable Builder"," "+builder.length());
        */


    }//onCreate()

}//class

