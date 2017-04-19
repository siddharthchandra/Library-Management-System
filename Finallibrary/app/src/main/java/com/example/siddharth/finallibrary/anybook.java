package com.example.siddharth.finallibrary;

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

public class anybook extends AppCompatActivity {
    BorrowRepo borrowRepo;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anybook);
        borrowRepo = new BorrowRepo();
        Cursor data = borrowRepo.fetchAll_borrow();
        ArrayList<String> listData = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                listData.add(data.getString(data.getColumnIndex(BorrowModel.KEY_MName)));
            } while (data.moveToNext());
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView = (ListView) findViewById(R.id.listview_anybook);
        listView.setAdapter(adapter);

    }
}