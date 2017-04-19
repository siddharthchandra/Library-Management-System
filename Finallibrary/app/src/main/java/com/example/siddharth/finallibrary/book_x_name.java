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

public class book_x_name extends AppCompatActivity {
    EditText bookname_field;
    Button search;
    BorrowRepo borrowRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_x_name);
        borrowRepo = new BorrowRepo();
        bookname_field = (EditText)findViewById(R.id.search_by_name_edit_bookx);
        search = (Button)findViewById(R.id.search_by_name_button_bookx);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookname_entered = bookname_field.getText().toString();
                Cursor c=borrowRepo.fetch_all_borrow_bookname(bookname_entered);
                if(c.getCount()>0)
                {
                    Toast.makeText(book_x_name.this,bookname_entered+" exists & quantity = "+c.getCount(),Toast.LENGTH_LONG).show();
                    Intent i = new Intent(book_x_name.this,book_x.class);
                    i.putExtra("name",bookname_entered);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(book_x_name.this,"Book does not exist ! Try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
