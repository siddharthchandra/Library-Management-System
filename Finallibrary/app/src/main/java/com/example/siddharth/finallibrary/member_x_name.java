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

public class member_x_name extends AppCompatActivity {
    EditText member_name_field;
    Button search;
    BorrowRepo borrowRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_x_name);
        borrowRepo = new BorrowRepo();
        member_name_field = (EditText)findViewById(R.id.search_by_name_edit_memberx);
        search = (Button)findViewById(R.id.search_by_name_button_memberx);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String membername_entered = member_name_field.getText().toString();
                Cursor c=borrowRepo.fetch_all_borrow_name(membername_entered);
                Log.v("bookname3", DatabaseUtils.dumpCursorToString(c));
                if(c.getCount()>0)
                {
                    Intent i = new Intent(member_x_name.this,member_x.class);
                    i.putExtra("name",membername_entered);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(member_x_name.this,"Member does not exist in borrow! Try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
