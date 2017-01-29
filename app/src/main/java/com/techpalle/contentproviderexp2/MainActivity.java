package com.techpalle.contentproviderexp2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    Button b1, b2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        tv = (TextView) findViewById(R.id.textView1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // CLIENT WANT TO INSERT STUDENT DETAILS INTO STUDENT DETAILS INTO FIRTS URI - STUDENT_URI
                ContentResolver contentResolver = getContentResolver();
                String name = et1.getText().toString();
                String sub = et2.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put(UriProvider.Name, name);
                contentValues.put(UriProvider.SUB, sub);
                // now insert
                contentResolver.insert(UriProvider.STUDENT_URI, contentValues);
                Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentResolver contentResolver = getContentResolver();
                Cursor c = contentResolver.query(UriProvider.STUDENT_URI,null,null,null,null);
                StringBuilder stringBuilder = new StringBuilder();
                if(c !=null){
                    while(c.moveToNext()){
                        int no = c.getInt(0);
                        String name = c.getString(1);
                        String sub = c.getString(2);
                        stringBuilder.append(" no: "+no+" name :"+name+" sub : "+sub);
                    }
                    tv.setText(stringBuilder.toString());
                }

            }
        });
    }
}

