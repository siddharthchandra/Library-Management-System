package com.example.siddharth.finallibrary;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.siddharth.finallibrary.data.model.BookModel;
import com.example.siddharth.finallibrary.data.repo.BookRepo;

import java.util.ArrayList;

public class View_BookList extends AppCompatActivity {
    ListView listView;
    BookRepo bookRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__book_list);
        bookRepo = new BookRepo();
        Cursor data = bookRepo.fetchAll();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            listData.add(data.getString(data.getColumnIndex(BookModel.KEY_Name)));
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        listView = (ListView)findViewById(R.id.listview_book);
        listView.setAdapter(adapter);
    }
}
