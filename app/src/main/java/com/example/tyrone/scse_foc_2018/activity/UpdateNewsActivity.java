package com.example.tyrone.scse_foc_2018.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tyrone.scse_foc_2018.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateNewsActivity extends AppCompatActivity {

    public DrawerLayout mDrawerLayout;
    public Toolbar toolbar;

    SharedPreferences sharedPref;

    String KeyMessage =  "Message No ";
    String KeyCurrentMessageIndex = "key";

    Button UpdateButton;
    TextView ContentTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_news);

        initToolBar();
        initDrawer();

        sharedPref = this.getSharedPreferences("News", Context.MODE_PRIVATE);

        UpdateButton = findViewById(R.id.UpdateButton);
        ContentTextView = findViewById(R.id.ContentTextView);


    }
    public void initDrawer() {
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
    }

    public void initToolBar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titlebar_news);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_menu_white);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        //Toast.makeText(LoginActivity.this,"clicking Toolbar");
                        mDrawerLayout.openDrawer(Gravity.START);
                    }
                }
        );
    }
    public void BroadcastMessage(View view)
    {
        Log.i("asd", "coming into broadcastmessage");
        SharedPreferences.Editor editor = sharedPref.edit();

        int CurrentMessageIndex = sharedPref.getInt(KeyCurrentMessageIndex, 0);

        CurrentMessageIndex++;

        //String message = editText.getText().toString();
        String author = "tyrone";
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        //add the message to the data storage along with the message index
        editor.putString(KeyMessage + CurrentMessageIndex, author + ":" + date + ":" + ContentTextView.getText().toString());
        editor.putInt(KeyCurrentMessageIndex, CurrentMessageIndex);


        Log.i("asd", "putting key is = " + KeyMessage + CurrentMessageIndex);

        editor.commit();
    }
}
