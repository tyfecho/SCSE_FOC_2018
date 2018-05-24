package com.example.tyrone.scse_foc_2018.entity;

/**
 * Created by Tyrone on 14/2/2018.
 */

public class Member {

    //  Member details
    private String email, password, name, avatar;
    private int mobileNo;

    public Member() {}

    public Member ( String name, String email, String password, int mobileNo, String avatar ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
        this.avatar = avatar;
    }

    //  Get Variables
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public String getPassword() { return this.password; }
    public String getAvatar() { return this.avatar; }
    public int getMobileNo() { return this.mobileNo; }

    //  Set Variable
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public void setMobileNo(int mobileNo) { this.mobileNo = mobileNo; }


}
