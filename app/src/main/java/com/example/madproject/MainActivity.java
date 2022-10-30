package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
               switch (v.getId()) {
           case R.id.button:
                startActivity(new Intent(this, Register.class));
                break;
                  }
                    Toast.makeText(getApplicationContext(), "Transfer to Register Page", Toast.LENGTH_LONG).show();
               }

        };


