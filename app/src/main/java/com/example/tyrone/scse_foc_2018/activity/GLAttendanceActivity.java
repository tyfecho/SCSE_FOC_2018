package com.example.tyrone.scse_foc_2018.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.tyrone.scse_foc_2018.R;
import com.example.tyrone.scse_foc_2018.controller.AttendanceAdapter;
import com.example.tyrone.scse_foc_2018.entity.StudentAttendance;

import java.util.StringTokenizer;


public class GLAttendanceActivity extends AppCompatActivity {

    Spinner OGSpinner;
    Button UpdateButton;
    ListView AttendanceListView;

    SharedPreferences sharedPref;

    String[] StudentNames;
    String[] AdminNumbers;
    String[] OGNames;
    int OGGroupSize = 0;
    String Date = "Date";//TODO: change to actual date

    AttendanceAdapter attendanceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl__attendance);

        sharedPref = this.getSharedPreferences("Attendance", Context.MODE_PRIVATE);

        OGSpinner = findViewById(R.id.OGSpinner);
        UpdateButton = findViewById(R.id.UpdateButton);
        AttendanceListView = findViewById(R.id.StudentListView);

        StudentNames = getResources().getStringArray(R.array.StudentNames);
        AdminNumbers = getResources().getStringArray(R.array.AdminNumbers);
        OGNames = getResources().getStringArray(R.array.OGs);

        ArrayAdapter<String> OGAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,OGNames);
        OGAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OGSpinner.setAdapter(OGAdapter);

        StringTokenizer tokens;
        OGGroupSize = sharedPref.getInt("Apus"+ "GroupSize", 0);

        attendanceAdapter= new AttendanceAdapter(this);

        AttendanceListView.setAdapter(attendanceAdapter);

        for (int i = 0; i < AdminNumbers.length; i++) {

            Boolean Present = sharedPref.getBoolean(Date + ":" + AdminNumbers[i] + ":" + StudentNames[i], false);
            attendanceAdapter.AddStudentAttendance(StudentNames[i], AdminNumbers[i], Present);
        }

        AttendanceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //StudentAttendance a = (StudentAttendance)adapterView.getItemAtPosition(i);
                //Log.i("ASD", "PRINTING " + a.getName());
            }
        });


    }



    public void InputNamesIntoDB(View view)
    {
        SharedPreferences.Editor editor = sharedPref.edit();

        for(int i = 0; i < AdminNumbers.length; i++)
            editor.putBoolean(Date + ":" + AdminNumbers[i] + ":" + StudentNames[i], false);

        editor.putInt("ApusGroupSize", AdminNumbers.length);
        editor.commit();
    }

    public void UpdateAttendance(View v)
    {
        //update the DB with the current information of the listview
        SharedPreferences.Editor editor = sharedPref.edit();


        //TODO: change AdminNUmbers.length to an appropriate variable
        for(int i = 0; i < AdminNumbers.length; i++)
        {
            CheckBox cb;
            cb = (CheckBox)AttendanceListView.getChildAt(i).findViewById(R.id.AttendanceCheckBox);

            boolean present = false;
            if(cb.isChecked()) present = true;

            String asd = attendanceAdapter.getItem(i).getName();
            editor.putBoolean(Date + ":" +
                    attendanceAdapter.getItem(i).getAdminNum() + ":" +
                    attendanceAdapter.getItem(i).getName(),
                    present);

        }

        editor.commit();

    }
}
