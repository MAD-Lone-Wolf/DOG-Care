package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class addhealthrecords extends AppCompatActivity {

    private EditText dogname,dogbreed,dob,description;
    private Button add,view;
    private FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhealthrecords);

        dogname = findViewById(R.id.dogname);
        dogbreed = findViewById(R.id.dogbreed);
        dob = findViewById(R.id.dob);
        description = findViewById(R.id.description);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);


        db= FirebaseFirestore.getInstance();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(addhealthrecords.this, show.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Dogname = dogname.getText().toString();
                String DogBreed = dogbreed.getText().toString();
                String DOB = dob.getText().toString();
                String Description = description.getText().toString();
                String id = UUID.randomUUID().toString();

                saveToFireStore(id,Dogname, DogBreed, DOB, Description );

            }
        });



    }

    private void saveToFireStore(String id, String dogname, String dogBreed, String dob, String description) {

//        if (!dogname.isEmpty() && !dob.isEmpty() && description.isEmpty() && dogBreed.isEmpty())
        {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("Dogname", dogname);
            map.put("DOB", dob);
            map.put("Description", description);

            db.collection("Documents").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(addhealthrecords.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(addhealthrecords.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });


//        }else
//            Toast.makeText(this, "Empty Fields are not Allowed", Toast.LENGTH_SHORT).show();


        }
    }
}