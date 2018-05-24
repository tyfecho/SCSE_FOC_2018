package com.example.tyrone.scse_foc_2018.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyrone.scse_foc_2018.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView et_email, et_password;
    Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = (TextView)findViewById(R.id.etEmail);
        et_password = (TextView)findViewById(R.id.etPassword);
        btn_login = (Button) findViewById(R.id.bLogin);
        btn_register = (Button) findViewById(R.id.bRegister);

        btn_register.setOnClickListener(new View.OnClickListener() {
            public void onClick( View v ) {
                //hideProgressDialog();
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }

    @Override
    public void onClick(View view) {
    }
        /*setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_skip = findViewById(R.id.btn_skip);

        MemberController memberController = new MemberController();
        if (memberController.isLoggedIn()) {
            hideProgressDialog();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        et_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        btn_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        btn_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideProgressDialog();
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_skip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideProgressDialog();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {


    }
}
