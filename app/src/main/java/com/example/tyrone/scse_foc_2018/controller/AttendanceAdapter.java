package com.example.tyrone.scse_foc_2018.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.tyrone.scse_foc_2018.R;
import com.example.tyrone.scse_foc_2018.entity.News;
import com.example.tyrone.scse_foc_2018.entity.StudentAttendance;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AttendanceAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<StudentAttendance> Students;

    public AttendanceAdapter(Context c)
    {
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Students = new ArrayList<StudentAttendance>();
    }
    public void AddStudentAttendance( String Name, String AdminNum, boolean Present)
    {
        StudentAttendance a = new StudentAttendance(Name, AdminNum, Present);

        Students.add(a);
    }
    @Override
    public int getCount() {
        return Students.size();
    }

    @Override
    public StudentAttendance getItem(int i) {
        return Students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View View, ViewGroup viewGroup) {

        View v = mInflater.inflate(R.layout.attendance_listview_detail, null);
        TextView NameTextView = (TextView) v.findViewById(R.id.NameTextView);
        TextView AdminNumTextView = (TextView) v.findViewById(R.id.AdminNumtextView);
        CheckBox AttendanceCheckBox = (CheckBox) v.findViewById(R.id.AttendanceCheckBox);

        String Name = Students.get(i).getName();
        String AdminNum = Students.get(i).getAdminNum();
        boolean Present = Students.get(i).getPresent();


        NameTextView.setText(Name);
        AdminNumTextView.setText(AdminNum);
        AttendanceCheckBox.setChecked(Present);

        return v;
    }
    public void onCheckboxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
    }
}
