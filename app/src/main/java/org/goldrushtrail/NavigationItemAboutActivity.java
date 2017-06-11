package org.goldrushtrail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class NavigationItemAboutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_item_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //For next time, make sure that you import the following line:
        //    android.support.v7.app.ActionBar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }//onCreate()
    public void getWebsite(View v)
    {
        Uri uri = null;
        switch (v.getId())
        {
            case R.id.image_sponsor1: //Mechanic's Institute
                uri = Uri.parse("https://www.milibrary.org/");
                break;
            case R.id.image_sponsor2: //California Historical Society
                uri = Uri.parse("https://www.californiahistoricalsociety.org/");
                break;
            case R.id.image_sponsor3: //Society of California Pioneers
                uri = Uri.parse("http://www.californiapioneers.org/");
                break;
        }
        if(uri != null)
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }//getWebsite()


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            navigateUpTo(new Intent(this, NavigationDrawerActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected()


}//class



