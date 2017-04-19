package com.example.siddharth.finallibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siddharth.finallibrary.data.model.MemberModel;
import com.example.siddharth.finallibrary.data.repo.BookRepo;
import com.example.siddharth.finallibrary.data.repo.BorrowRepo;
import com.example.siddharth.finallibrary.data.repo.MemberRepo;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private Button signupBtn;
    private EditText username_field;
    private EditText password_field;
    MemberRepo memberRepo;
    BookRepo bookRepo;
    BorrowRepo borrowRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        memberRepo = new MemberRepo();
        bookRepo = new BookRepo();
        borrowRepo = new BorrowRepo();
        MemberModel memberModel = new MemberModel();
        memberModel.setpassword("123");
        memberModel.setusername("jalote");
        memberModel.setFine();
        memberModel.setName("Pankaj Jalote");
        memberModel.setType("Admin");
        memberRepo.insert(memberModel);
        loginBtn = (Button) findViewById(R.id.btnLogin);
        signupBtn = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        username_field = (EditText)findViewById(R.id.username);
        password_field = (EditText)findViewById(R.id.password);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username_field.getText().toString().trim();
                String password = password_field.getText().toString().trim();
                if (username.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter the username !",
                            Toast.LENGTH_LONG).show();
                } else if (password.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter the password !",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean check_for_user = memberRepo.checkMemberUsername(username);
                    Log.v("maincheck",String.valueOf(check_for_user));
                    boolean check_for_user_and_password = memberRepo.checkMemberUsernameAndPassword(username,password);
                    Log.v("maincheck",String.valueOf(check_for_user_and_password));
                    if (check_for_user==true)
                    {
                        if(check_for_user_and_password==true)
                        {
                            if(username.equals("jalote"))
                            {

                                Toast.makeText(MainActivity.this, "Welcome Admin !",Toast.LENGTH_LONG).show();
                                Intent i=new Intent(MainActivity.this,Admin.class);
                                i.putExtra("username",username);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "There in library",Toast.LENGTH_LONG).show();
                                Intent i=new Intent(MainActivity.this,Member.class);
                                i.putExtra("username",username);
                                startActivity(i);
                            }

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Wrong Password ! Try Again.",Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Not exist",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(MainActivity.this, Signup.class);
                startActivity(signupIntent);
            }
        });

    }
 }

