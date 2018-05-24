package com.example.tyrone.scse_foc_2018.controller;

/**
 * Created by Tyrone on 14/2/2018.
 */

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.tyrone.scse_foc_2018.entity.Member;




public class MemberController {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference database;
    private Member member;

    //  Constructor
    public MemberController () {};

    //  Create Member Record
    public boolean createMemberRecord ( Member member ) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if ( user != null ) {
            database = FirebaseDatabase.getInstance().getReference();
            database.child("member").child(user.getUid()).setValue(member);
        }
        return false;
    }

}
