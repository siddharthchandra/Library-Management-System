package com.example.siddharth.finallibrary;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siddharth.finallibrary.data.repo.BookRepo;
import com.example.siddharth.finallibrary.data.repo.BorrowRepo;

public class Publisher_x extends AppCompatActivity {
    EditText publisher_field;
    Button search;
    BookRepo bookRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_x);
        bookRepo = new BookRepo();
        publisher_field = (EditText)findViewById(R.id.search_by_name_edit_publisherx);
        search = (Button)findViewById(R.id.search_by_publisher_name);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String publishername_entered = publisher_field.getText().toString();
                Cursor c=bookRepo.checkBookpublisher(publishername_entered);
                if(c.getCount()>0)
                {
                    Intent i = new Intent(Publisher_x.this,Publisherx_x_list.class);
                    i.putExtra("name",publishername_entered);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(Publisher_x.this,"Book with this publisher does not exist! Try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
