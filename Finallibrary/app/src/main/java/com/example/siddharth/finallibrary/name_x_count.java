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

public class name_x_count extends AppCompatActivity {
    EditText bookname_field;
    Button search;
    BorrowRepo borrowRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_x_count);
        borrowRepo = new BorrowRepo();
        bookname_field = (EditText)findViewById(R.id.search_by_name_edit_bookx_count);
        search = (Button)findViewById(R.id.search_by_name_button_bookx_count);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookname_entered = bookname_field.getText().toString();
                Cursor c=borrowRepo.fetch_all_borrow_bookname(bookname_entered);
                if(c.getCount()>0)
                {
                    Toast.makeText(name_x_count.this,String.valueOf(c.getCount()),Toast.LENGTH_LONG).show();;

                }
                else
                {
                    Toast.makeText(name_x_count.this,"No one has borrowed this book",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
