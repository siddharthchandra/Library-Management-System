package com.example.siddharth.finallibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.siddharth.finallibrary.data.repo.BookRepo;

public class Searchbook extends AppCompatActivity {
    private Button search_by_name;
    private Button search_by_publisher;
    private Button avg_book_id;
    BookRepo bookRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbook);
        search_by_name = (Button) findViewById(R.id.search_by_name);
        search_by_publisher = (Button) findViewById(R.id.search_by_publisher);
        avg_book_id = (Button)findViewById(R.id.average_book_id);
        bookRepo = new BookRepo();
        search_by_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Searchbook.this,Search_by_name.class);
                startActivity(i);
            }
        });

        search_by_publisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i= new Intent(Searchbook.this,Publisher_x.class);
                startActivity(i);
            }
        });
        avg_book_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("average","check 0");
            long avg = bookRepo.average_bookid();
                Toast.makeText(Searchbook.this,"Average value of book IDs = "+String.valueOf(avg),Toast.LENGTH_LONG).show();


            }
        });

    }
}
