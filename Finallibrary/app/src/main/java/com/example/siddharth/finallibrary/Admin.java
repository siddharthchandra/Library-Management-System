package com.example.siddharth.finallibrary;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.siddharth.finallibrary.data.model.MemberModel;
import com.example.siddharth.finallibrary.data.repo.MemberRepo;

public class Admin extends AppCompatActivity {
    private TextView name;
    private Button admin_borrow;
    private Button admin_search;
    private Button admin_addBook;
    private Button admin_memberList;
    private Button admin_bookList;
    MemberModel memberModel;
    MemberRepo memberRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent i = getIntent();
        String username = i.getStringExtra("username");
        memberRepo = new MemberRepo();
        name = (TextView) findViewById(R.id.textView_admin);
        admin_borrow = (Button) findViewById(R.id.admin_borrow);
        admin_search = (Button) findViewById(R.id.admin_search);
        admin_addBook = (Button) findViewById(R.id.admin_addBook);
        admin_memberList = (Button) findViewById(R.id.admin_memberList);
        admin_bookList = (Button) findViewById(R.id.admin_bookList);
        Cursor c = memberRepo.getFullName(username);
        String admin_name = c.getString(c.getColumnIndex(MemberModel.KEY_Fullname));
        name.setText(admin_name);
        admin_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(Admin.this,Borrowed_Books.class);
                startActivity(i);
            }
        });

        admin_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admin.this,Searchbook.class);
                startActivity(i);
            }
        });

        admin_addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this,Add_book.class);
                startActivity(i);
            }
        });

        admin_memberList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this,MemberList.class);
                startActivity(i);
            }
        });

        admin_bookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Admin.this,View_BookList.class);
                startActivity(i);
            }
        });


    }
}
