package com.example.tyrone.scse_foc_2018.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tyrone.scse_foc_2018.R;

/**
 * Created by Tyrone on 14/2/2018.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView et_name, et_email, et_password, et_confirm, et_mobileno;
    Button btn_submit, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_submit = (Button) findViewById(R.id.bSubmit);
        btn_back = (Button) findViewById(R.id.bBack);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onClick(View view) {
    }
}
