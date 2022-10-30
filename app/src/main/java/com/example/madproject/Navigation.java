package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Navigation extends AppCompatActivity {

    private Button logout,myaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        logout = (Button) findViewById(R.id.signOut);
        myaccount = (Button) findViewById(R.id.myaccount);


        myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.myaccount:
                       startActivity(new Intent(Navigation.this, MyAccount.class));
                       Toast.makeText(getApplicationContext(), "Transfer to the MyAccount Page", Toast.LENGTH_LONG).show();                        break;

            }
        }
//
//           @Override
//                public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//         logout.setOnClickListener(new View.OnClickListener() {
//               startActivity(new Intent(Navigation.this, MainActivity.class));

//                switch (v.getId()) {
//                    case R.id.myaccount:
//                        startActivity(new Intent(Navigation.this, MyAccount.class));
//                        Toast.makeText(getApplicationContext(), "Transfer to the MyAccount Page", Toast.LENGTH_LONG).show();
//                        break;
        //}
//            }


        });
    }
}