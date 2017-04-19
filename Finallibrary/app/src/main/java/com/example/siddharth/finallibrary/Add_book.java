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
import com.example.siddharth.finallibrary.data.repo.BookRepo;

public class Add_book extends AppCompatActivity {
    private EditText booktitle;
    private EditText author;
    private EditText publisher;
    private EditText edition;
    private EditText quantity;
    private Button addBtn;
    BookRepo bookRepo;
    BookModel bookModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        booktitle = (EditText) findViewById(R.id.booktitle);
        author = (EditText) findViewById(R.id.author);
        publisher = (EditText) findViewById(R.id.publisher);
        edition = (EditText) findViewById(R.id.edition);
        quantity = (EditText) findViewById(R.id.quantity);
        addBtn = (Button) findViewById(R.id.addBtn);
        bookRepo = new BookRepo();
        bookModel = new BookModel();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String booktitle1 = booktitle.getText().toString();
                String author1 = author.getText().toString();
                String publisher1 = publisher.getText().toString();
                String edition1 = edition.getText().toString();
                String quantity1 = quantity.getText().toString();
                bookModel.setName(booktitle1);
                bookModel.setAuthor(author1);
                bookModel.setPublisher(publisher1);
                bookModel.setEdition(edition1);


                if (booktitle1.equals("") || author1.equals("") || publisher1.equals("") || edition1.equals("") || quantity1.equals("")) {
                    Toast.makeText(Add_book.this, "Please Fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    int quantity_int = Integer.parseInt(quantity1);
                    boolean check = false;

                    for (int i = 0; i < quantity_int; i++) {

                        check = bookRepo.insert(bookModel);

                    }
                    if (check) {
                        Toast.makeText(Add_book.this, "Added successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Add_book.this, Admin.class);
                        i.putExtra("username", "jalote");
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(Add_book.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}

