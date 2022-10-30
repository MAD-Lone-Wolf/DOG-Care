package com.example.madproject;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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
            return;
        }

        if (Phone.isEmpty()){
            editTextPhone.setError("Phone number is Required");
            editTextPhone.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextEmailAddress.setError("Email Address is Required");
            editTextEmailAddress.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress.setError("Enter a valid Email Address");
            editTextEmailAddress.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() <6){
            editTextPassword.setError("Minimum 6 characters needed!");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(Username, Phone, email);


                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(Register.this, "Registration Failed!, Try again!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        } else {
                            Toast.makeText(Register.this, " Registration Failed!, Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}

































