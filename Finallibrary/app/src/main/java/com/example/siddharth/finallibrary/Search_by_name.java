package com.example.siddharth.finallibrary;

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

public class Search_by_name extends AppCompatActivity {
    EditText bookname_field;
    Button search;
    BookRepo bookRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);
        bookRepo = new BookRepo();
        bookname_field = (EditText)findViewById(R.id.search_by_name_edit);
        search = (Button)findViewById(R.id.search_by_name_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookname_entered = bookname_field.getText().toString();
                Cursor c=bookRepo.checkBookname(bookname_entered);
                Log.v("bookname", DatabaseUtils.dumpCursorToString(c));
                if(c.getCount()>0)
                {
                    Toast.makeText(Search_by_name.this,bookname_entered+" exists & quantity = "+c.getCount(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Search_by_name.this,"Book does not exist ! Try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
