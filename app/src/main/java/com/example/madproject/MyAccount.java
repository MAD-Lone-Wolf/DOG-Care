package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MyAccount extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView UsernameTextView = (TextView) findViewById(R.id.username);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);
        final TextView phoneTextView = (TextView) findViewById(R.id.phone);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile = snapshot.getValue(User.class);

                if(userprofile != null){
                    String username = userprofile.Username;
                    String emailAddress = userprofile.email;
                    String phone = userprofile.Phone;

                    UsernameTextView.setText(username);
                    emailTextView.setText(emailAddress);
                    phoneTextView.setText(phone);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyAccount.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

            }
        });



    }
}

