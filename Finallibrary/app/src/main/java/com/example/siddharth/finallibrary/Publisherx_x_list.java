package com.example.siddharth.finallibrary;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.siddharth.finallibrary.data.model.BookModel;
import com.example.siddharth.finallibrary.data.model.BorrowModel;
import com.example.siddharth.finallibrary.data.repo.BookRepo;
import com.example.siddharth.finallibrary.data.repo.BorrowRepo;

import java.util.ArrayList;

public class Publisherx_x_list extends AppCompatActivity {
    BookRepo bookRepo;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisherx_x_list);
        Intent i =getIntent();
        String name = i.getStringExtra("name");
        bookRepo = new BookRepo();
        Cursor data = bookRepo.checkBookpublisher(name);
        ArrayList<String> listData = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                listData.add(data.getString(data.getColumnIndex(BookModel.KEY_Name)));
            } while (data.moveToNext());
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView = (ListView) findViewById(R.id.listview_publisherx);
        listView.setAdapter(adapter);

    }
}
