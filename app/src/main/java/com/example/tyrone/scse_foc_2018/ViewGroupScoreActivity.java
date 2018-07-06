package com.example.tyrone.scse_foc_2018;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewGroupScoreActivity extends AppCompatActivity {

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

        ApusTextView = findViewById(R.id.ApusTextView);
        CorvusTextView = findViewById(R.id.CorvusTextView);
        LeoTextView = findViewById(R.id.LeoTextView);
        LyraTextView = findViewById(R.id.LyraTextView);
        OrionTextView = findViewById(R.id.OrionTextView);
        ScorpiusTextView = findViewById(R.id.ScorpiusTextView);

        sharedPref = this.getSharedPreferences("OG_Scores", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("ApusScore", 0);
        editor.putInt("CorvusScore", 1);
        editor.putInt("LeoScore", 2);
        editor.putInt("LyraScore", 3);
        editor.putInt("OrionScore", 4);
        editor.putInt("ScorpiusScore", 5);
        editor.commit();
        RefreshScores();
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
}
