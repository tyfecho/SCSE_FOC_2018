package com.example.tyrone.scse_foc_2018.entity;

public class StudentAttendance {

    String Name;
    String AdminNum;
    boolean Present;//0 is absent, 1 is present

    public StudentAttendance(String inName, String inAdminNum, boolean inPresent)
    {
        Name = inName;
        AdminNum = inAdminNum;
        Present = inPresent;
    }

    public String getName() {
        return Name;
    }
    public String getAdminNum() {
        return AdminNum;
    }
    public boolean getPresent(){
        return Present;
    }
    public void setPresent(boolean status)
    {
        Present = status;
    }
}
