package com.google.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    //initialize variables
    EditText titleno,title , dogtype, writeairticle;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditTexts UI
        titleno = findViewById(R.id.editTextTextPersonName3);
        title= findViewById(R.id.editTextTextPersonName4);
        dogtype = findViewById(R.id.editTextTextPersonName5);
        writeairticle = findViewById(R.id.editTextTextPersonName);

        //Buttons UI
        insert = findViewById(R.id.button);
        update = findViewById(R.id.button2);
        delete = findViewById(R.id.button3);
        view = findViewById(R.id.button4);

        //initialize Database
        DB = new DBHelper(this);

    }

    public void insert_Data(View v) {
        String titlenoTXT = titleno.getText().toString();
        String ageTXT = title.getText().toString();
        String courseTXT = dogtype.getText().toString();
        String addressTXT = writeairticle.getText().toString();

        Boolean checkInsertData = DB.insertdata(titlenoTXT, ageTXT, addressTXT, courseTXT);
        if (checkInsertData)
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

        DB.close();
    }


    public void update_Data(View v){
        String nameTXT = titleno.getText().toString();
        String ageTXT = title.getText().toString();
        String addressTXT = dogtype.getText().toString();
        String courseTXT = writeairticle.getText().toString();

        Boolean checkUpdateData = DB.updatedata(nameTXT, ageTXT, addressTXT, courseTXT);
        if(checkUpdateData)
            Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();

        DB.close();
    }

    public void delete_Data(View v){
        String nameTXT = titleno.getText().toString();
        Boolean checkDeleteData = DB.deletedata(nameTXT);
        if(checkDeleteData)
            Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();

        DB.close();
    }

    public void view_Data(View v){
        Cursor res = DB.getdata();
        if(res.getCount()==0){
            Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("TitleNo: " + res.getString(0) + "\n");
            buffer.append("Title: " + res.getString(1) + "\n");
            buffer.append("Dogtype: " + res.getString(2) + "\n");
            buffer.append("Descrption: " + res.getString(3) + "\n\n");
        }
        //alert pop-up for viewing all data
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Entries");
        builder.setMessage(buffer.toString());
        builder.show();

        DB.close();
    }
}