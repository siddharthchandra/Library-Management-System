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

import com.example.siddharth.finallibrary.data.model.BookModel;
import com.example.siddharth.finallibrary.data.model.BorrowModel;
import com.example.siddharth.finallibrary.data.repo.BookRepo;
import com.example.siddharth.finallibrary.data.repo.BorrowRepo;

public class Search_by_name_member extends AppCompatActivity {
    EditText bookname_field;
    Button borrow;
    Button search;
    Cursor c;
    String username;
    String bookname_entered;
    BookModel bookModel;
    BookRepo bookRepo;
    BorrowRepo borrowRepo;
    BorrowModel borrowModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name_member);
        bookRepo = new BookRepo();
        bookModel = new BookModel();
        borrowRepo = new BorrowRepo();
        borrowModel = new BorrowModel();
        bookname_field = (EditText)findViewById(R.id.search_by_name_member_edit);
        borrow = (Button) findViewById(R.id.click_to_borrow);
        Intent i =getIntent();
        username = i.getStringExtra("username");
        search = (Button)findViewById(R.id.search_by_name_member_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookname_entered = bookname_field.getText().toString();
                c=bookRepo.checkBookname(bookname_entered);
                if(c.getCount()>0)
                {
                    Toast.makeText(Search_by_name_member.this,bookname_entered+" exists & quantity = "+c.getCount(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Search_by_name_member.this,"Book does not exist ! Try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookname_entered = bookname_field.getText().toString().trim();
                c=bookRepo.checkBookname(bookname_entered);
                if(c.getCount()>0)
                {
                    c.moveToFirst();
                    String author = c.getString(c.getColumnIndex(BookModel.KEY_Author));
                    int Book= Integer.parseInt(c.getString(c.getColumnIndex(BookModel.KEY_BookId)));
                    borrowModel.setName(username);
                    borrowModel.setBorrowId(Book);
                    borrowModel.setBname(bookname_entered);
                    borrowModel.setBauthor(author);
                    borrowRepo.insert(borrowModel);
                    bookRepo.delete_book(String.valueOf(Book));
                    Cursor cursor = borrowRepo.fetchAll_borrow();
                    Toast.makeText(Search_by_name_member.this,"You have successfully borrowed the book",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Search_by_name_member.this,"Book does not exist ! Try again.",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    }
