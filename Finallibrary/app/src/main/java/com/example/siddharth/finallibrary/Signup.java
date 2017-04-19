package com.example.siddharth.finallibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.siddharth.finallibrary.data.model.MemberModel;
import com.example.siddharth.finallibrary.data.repo.MemberRepo;

import java.util.ArrayList;
import java.util.List;

public class Signup extends AppCompatActivity {
    private Button login_again,register_button;
    private String item;
    private EditText username_field,password_field,fullname_field;
    private String username;
    private String fullname;
    private String password;
    MemberRepo memberRepo;
    MemberModel memberModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username_field = (EditText)findViewById(R.id.username_signup);
        password_field = (EditText)findViewById(R.id.password_signup);
        fullname_field = (EditText)findViewById(R.id.name_signup);
        login_again=(Button)findViewById(R.id.btnLinkToLoginScreen);
        register_button=(Button)findViewById(R.id.btnRegister);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        memberRepo = new MemberRepo();
        memberModel=new MemberModel();
        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Signup.this,"Please select type",Toast.LENGTH_SHORT).show();
            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Faculty");
        categories.add("Student");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username_field.getText().toString();
                password = password_field.getText().toString();
                fullname = fullname_field.getText().toString();
                if (username.equals("")) {
                    Toast.makeText(Signup.this, "Please enter the username !",
                            Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(Signup.this, "Please enter the desired password !",
                            Toast.LENGTH_SHORT).show();
                } else if (fullname.equals("")) {
                    Toast.makeText(Signup.this, "Please enter your name !",
                            Toast.LENGTH_SHORT).show();
                } else {

                    boolean user = memberRepo.checkMemberUsername(username);
                    if(user == true)
                    {
                        Toast.makeText(Signup.this, "Username already exists !",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        boolean check = false;
                        Log.i("Toinsert", username + " " + password);
                        memberModel.setusername(username);
                        memberModel.setpassword(password);
                        memberModel.setFine();
                        memberModel.setName(fullname);
                        memberModel.setType(item);
                        check = memberRepo.insert(memberModel);
                        if (check == true) {
                            Toast.makeText(Signup.this, "Added successfully", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Signup.this,MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Signup.this, "ERROR", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }

        });
        login_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
