package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView2;
    private TextView Register;
    private EditText editTextUsername, editTextEmailAddress, editTextPassword, editTextPhone;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        Register = (Button) findViewById(R.id.Register);
        Register.setOnClickListener(this);

        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setOnClickListener(this);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView2:
                startActivity(new Intent(this, MainActivity.class));
                break;
                case R.id.Register:
                Register();
                break;
        }

    }

    private void Register() {
        String email = editTextEmailAddress.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String Username = editTextUsername.getText().toString().trim();
        String Phone = editTextPhone.getText().toString().trim();

        if (Username.isEmpty()){
            editTextUsername.setError("Username is Required");
            editTextUsername.requestFocus();
        }

        if (Phone.isEmpty()){
            editTextPhone.setError("Phone number is Required");
            editTextPhone.requestFocus();
        }

        if (email.isEmpty()){
            editTextEmailAddress.setError("Email Address is Required");
            editTextEmailAddress.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress.setError("Enter a valid Email Address");
            editTextEmailAddress.requestFocus();
        }
    }

}

































