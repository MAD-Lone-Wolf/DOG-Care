package com.example.myapplication;

import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText issue;
    private Button add;
    private Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.user_name);
        issue = findViewById(R.id.issue);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Add Clicked", Toast.LENGTH_SHORT).show();

                String txt_user_name = userName.getText().toString();
                String txt_issue = issue.getText().toString();

                if (txt_user_name.isEmpty() || txt_issue.isEmpty()){
                    Toast.makeText(MainActivity.this, "Empty fields detected!", Toast.LENGTH_SHORT).show();
                } else {
//                    userName.setText("");
//                    issue.setText("");
//                    FirebaseDatabase.getInstance().getReference().child("HelpDesk").child("Issue").updateChildren(issue_map);
                    FirebaseDatabase.getInstance().getReference().child("HelpDesk").child("Issues").child("Name").setValue(txt_user_name);
                    FirebaseDatabase.getInstance().getReference().child("HelpDesk").child("Issues").child("Issue").setValue(txt_issue);

                }
            }

        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "View Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this , MainActivity2.class));
//                finish();
            }
        });

    }
}