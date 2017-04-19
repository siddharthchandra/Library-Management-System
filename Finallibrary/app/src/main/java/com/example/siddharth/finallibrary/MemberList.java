package com.example.siddharth.finallibrary;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.siddharth.finallibrary.data.model.BookModel;
import com.example.siddharth.finallibrary.data.model.MemberModel;
import com.example.siddharth.finallibrary.data.repo.MemberRepo;

import java.util.ArrayList;

public class MemberList extends AppCompatActivity {
    ListView listView;
    MemberRepo memberRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        memberRepo = new MemberRepo();
        Cursor data = memberRepo.fetchAll_member();
        ArrayList<String> listData = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                listData.add(data.getString(data.getColumnIndex(MemberModel.KEY_Fullname)));
            } while (data.moveToNext());
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        listView = (ListView)findViewById(R.id.listview_member);
        listView.setAdapter(adapter);
    }
}
