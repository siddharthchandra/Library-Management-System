package com.example.siddharth.finallibrary;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.siddharth.finallibrary.data.model.BookModel;
import com.example.siddharth.finallibrary.data.model.BorrowModel;
import com.example.siddharth.finallibrary.data.repo.BorrowRepo;

import java.util.ArrayList;

public class Book_issued extends AppCompatActivity {
    ListView listView;
    BorrowRepo borrowRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_issued);
        Intent i = getIntent();
        borrowRepo = new BorrowRepo();
        String username = i.getStringExtra("username");
        Cursor data = borrowRepo.fetch_all_borrow_name(username);
        ArrayList<String> listData = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                listData.add(data.getString(data.getColumnIndex(BorrowModel.KEY_BName)));
            } while (data.moveToNext());
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        listView = (ListView)findViewById(R.id.book_issued);
        listView.setAdapter(adapter);
    }
    }

