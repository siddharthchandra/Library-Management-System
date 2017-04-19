package com.example.siddharth.finallibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Borrowed_Books extends AppCompatActivity {
    Button anybook,book_x,author_x,name_x,member_x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowed__books);
        anybook=(Button)findViewById(R.id.anybook);
        book_x=(Button)findViewById(R.id.book_x);
        author_x =(Button)findViewById(R.id.author_x);
        name_x = (Button)findViewById(R.id.name_x);
        member_x =(Button)findViewById(R.id.member_x);
        anybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Borrowed_Books.this,anybook.class);
                startActivity(i);
            }
        });
        book_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Borrowed_Books.this,book_x_name.class);
                startActivity(i);
            }

        });
        name_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i =new Intent(Borrowed_Books.this,name_x_count.class);
            startActivity(i);
        }

        });
        author_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent i =new Intent(Borrowed_Books.this,author_x_name.class);
        startActivity(i);
        }

        });
        member_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent i =new Intent(Borrowed_Books.this,member_x_name.class);
        startActivity(i);
        }

        });
    }
}
