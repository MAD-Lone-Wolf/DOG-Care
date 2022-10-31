package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private EditText textfield;
    private Button update;
    private Button delete;


    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private void getFirstIssue() {
        FirebaseDatabase.getInstance().getReference().child("HelpDesk").child("Issues").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String json = snapshot.getValue().toString();

                String value = json.substring(json.indexOf("=") + 1, json.indexOf(","));
                textfield.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

//        =  FirebaseDatabase.getInstance().getReference().child("HelpDesk").child("Issue");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textfield = findViewById(R.id.editTextTextMultiLine);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        getFirstIssue();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Update Clicked", Toast.LENGTH_SHORT).show();
                String text = textfield.getText().toString();
                // update db, be in mainactivity2

                if (textfield.getText().equals("")){
                    Toast.makeText(MainActivity2.this, "Empty fields detected!", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference().child("HelpDesk").child("Issues").child("Issue").setValue(text);
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Delete Clicked", Toast.LENGTH_SHORT).show();

                FirebaseDatabase.getInstance().getReference().child("HelpDesk").child("Issues").removeValue();
                startActivity(new Intent(MainActivity2.this , MainActivity.class));
            }
        });

    }
}