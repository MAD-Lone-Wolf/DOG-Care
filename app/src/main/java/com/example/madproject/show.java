package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class show extends AppCompatActivity {


    private RecyclerView recyclerview;
    private FirebaseFirestore db;
    private MyAdapter adapter;
    private List<Model> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adapter = new MyAdapter(this , list);
        recyclerview.setAdapter(adapter);

        showData();
    }

    private void showData(){
        db.collection("Documents").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot snapshot:task.getResult()){

                            Model model = new Model(snapshot.getString("id"), snapshot.getString("dogname"), snapshot.getString("dogbreed") , snapshot.getString("dob"), snapshot.getString("description"));
                            list.clear();
                        }
                        adapter.notifyDataSetChanged();
                    }

                });
    }
}