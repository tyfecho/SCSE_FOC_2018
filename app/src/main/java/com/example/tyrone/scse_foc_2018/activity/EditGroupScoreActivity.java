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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tyrone.scse_foc_2018.R;

import java.security.acl.Group;

public class EditGroupScoreActivity extends AppCompatActivity {

    public DrawerLayout mDrawerLayout;
    public Toolbar toolbar;

    SharedPreferences sharedPref;

    Spinner GroupSelectSpinner;
    Button PlusButton;
    Button MinusButton;
    Button UpdateButton;
    TextView Score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group_score);

        initToolBar();
        initDrawer();

        sharedPref = this.getSharedPreferences("OG_Scores", Context.MODE_PRIVATE);

        GroupSelectSpinner = findViewById(R.id.GroupSelectSpinner);
        PlusButton = findViewById(R.id.PlusButton);
        MinusButton = findViewById(R.id.MinusButton);
        UpdateButton = findViewById(R.id.UpdateButton);
        Score = findViewById(R.id.ScoreTextView);

        String[] OG_Groups = getResources().getStringArray(R.array.OGs);
        ArrayAdapter<String> GroupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,OG_Groups);
        GroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        GroupSelectSpinner.setAdapter(GroupAdapter);

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
    public void MinusScore(View v)
    {
        int score = Integer.parseInt(Score.getText().toString());
        score -= 5;
        Score.setText("" + score);
    }

    public void PlusScore(View v)
    {
        int score = Integer.parseInt( Score.getText().toString());
        score += 5;
        Score.setText("" + score);
    }
    public void UpdateScore(View v)
    {
        int CurrentScore = sharedPref.getInt("ApusScore", 0);
        int AddScore = Integer.parseInt(Score.getText().toString());
        String OGname = GroupSelectSpinner.getSelectedItem().toString();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(OGname + "Score", CurrentScore + AddScore);
        editor.commit();
    }
    public void switcha(View v)
    {
        Intent intent = new Intent(this, ViewGroupScoreActivity.class);
        startActivity(intent);
    }
}
