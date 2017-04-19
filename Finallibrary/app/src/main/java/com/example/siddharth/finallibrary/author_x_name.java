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

import com.example.siddharth.finallibrary.data.repo.BorrowRepo;

public class author_x_name extends AppCompatActivity {
    EditText authorname_field;
    Button search;
    BorrowRepo borrowRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_x_name);
        borrowRepo = new BorrowRepo();
        authorname_field = (EditText)findViewById(R.id.search_by_name_edit_authorx);
        search = (Button)findViewById(R.id.search_by_name_button_authorx);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String authorname_entered = authorname_field.getText().toString();
                Cursor c=borrowRepo.fetch_all_borrow_bookauthor(authorname_entered);
                if(c.getCount()>0)
                {
                    Intent i = new Intent(author_x_name.this,author_x.class);
                    i.putExtra("name",authorname_entered);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(author_x_name.this,"Book does not exist ! Try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
