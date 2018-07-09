package com.example.tyrone.scse_foc_2018.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.tyrone.scse_foc_2018.R;

public class ViewGroupScoreActivity extends AppCompatActivity {

    public DrawerLayout mDrawerLayout;
    public Toolbar toolbar;

    SharedPreferences sharedPref;

    TextView ApusTextView;
    TextView CorvusTextView;
    TextView LeoTextView;
    TextView LyraTextView;
    TextView OrionTextView;
    TextView ScorpiusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_score);
        initToolBar();
        initDrawer();

        ApusTextView = findViewById(R.id.ApusTextView);
        CorvusTextView = findViewById(R.id.CorvusTextView);
        LeoTextView = findViewById(R.id.LeoTextView);
        LyraTextView = findViewById(R.id.LyraTextView);
        OrionTextView = findViewById(R.id.OrionTextView);
        ScorpiusTextView = findViewById(R.id.ScorpiusTextView);

        sharedPref = this.getSharedPreferences("OG_Scores", Context.MODE_PRIVATE);

        /*SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("ApusScore", 0);
        editor.putInt("CorvusScore", 1);
        editor.putInt("LeoScore", 2);
        editor.putInt("LyraScore", 3);
        editor.putInt("OrionScore", 4);
        editor.putInt("ScorpiusScore", 5);
        editor.commit();*/
        RefreshScores();
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
    void RefreshScores()
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        ApusTextView.setText("" + sharedPref.getInt("ApusScore", 0));
        CorvusTextView.setText("" + sharedPref.getInt("CorvusScore", 0));
        LeoTextView.setText("" + sharedPref.getInt("LeoScore", 0));
        LyraTextView.setText("" + sharedPref.getInt("LyraScore", 0));
        OrionTextView.setText("" + sharedPref.getInt("OrionScore", 0));
        ScorpiusTextView.setText("" + sharedPref.getInt("ScorpiusScore", 0));


    }
    public void switcha(View v)
    {
        Intent intent = new Intent(this, EditGroupScoreActivity.class);
        startActivity(intent);
    }
}
