package com.example.siddharth.finallibrary;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siddharth.finallibrary.data.model.MemberModel;
import com.example.siddharth.finallibrary.data.repo.BookRepo;
import com.example.siddharth.finallibrary.data.repo.BorrowRepo;
import com.example.siddharth.finallibrary.data.repo.MemberRepo;

public class Member extends AppCompatActivity {
    private Button issued;
    private Button search;
    private Button fine_due;
    private String username;
    private TextView username_view;
    MemberRepo memberRepo;
    MemberModel memberModel;
    BorrowRepo borrowRepo;
    BookRepo bookRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        getSupportActionBar().hide();
        memberRepo = new MemberRepo();
        bookRepo = new BookRepo();
        issued = (Button)findViewById(R.id.issued);
        fine_due = (Button)findViewById(R.id.fine_due);
        search = (Button)findViewById(R.id.search);
        username_view = (TextView)findViewById(R.id.textView);
        borrowRepo = new BorrowRepo();
        Intent getIntent = getIntent();
        username = getIntent.getStringExtra("username");
        Cursor c = memberRepo.getFullName(username);
        username_view.setText(c.getString(c.getColumnIndex(MemberModel.KEY_Fullname)));
        issued.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Member.this,Book_issued.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });
        fine_due.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c1 = memberRepo.getFullName(username);
                c1.moveToFirst();
                int fine=c1.getInt(c1.getColumnIndex(MemberModel.KEY_Fine));
                Toast.makeText(Member.this,"Current Fine Due: " + String.valueOf(fine),Toast.LENGTH_LONG ).show();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Member.this,Search_by_name_member.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

    }
    }

