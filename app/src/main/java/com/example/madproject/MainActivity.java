package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button,signIn;
    private EditText editTextEmailAddress1,editTextPassword1;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmailAddress1 = (EditText) findViewById(R.id.editTextEmailAddress1);
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);

        mAuth=FirebaseAuth.getInstance();
    }
            @Override
            public void onClick(View v) {
               switch (v.getId()) {
           case R.id.button:
                startActivity(new Intent(this, Register.class));
               Toast.makeText(getApplicationContext(), "Transfer to Register Page", Toast.LENGTH_LONG).show();
                break;

                   case R.id.signIn:
                       userLogin();
                       break;
                  }


               }

    private void userLogin() {
        String email = editTextEmailAddress1.getText().toString().trim();
        String password = editTextPassword1.getText().toString().trim();

        if(email.isEmpty()){
             editTextEmailAddress1.setError("Email is Required");
             editTextEmailAddress1.requestFocus();
             return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress1.setError("Enter a valid Email");
            editTextEmailAddress1.requestFocus();
            return;
        }

        if(password.isEmpty()){
              editTextPassword1.setError("Password is Required");
              editTextPassword1.requestFocus();
              return;
        }

        if(password.length() <6){
            editTextPassword1.setError("Minimum 6 characters needed!");
            editTextPassword1.requestFocus();
            return;

        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    //Directs to the user page
                    startActivity(new Intent(MainActivity.this,Navigation.class));

                }else {
                    Toast.makeText(MainActivity.this, "Failed to Login! Please Try Again!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

};


