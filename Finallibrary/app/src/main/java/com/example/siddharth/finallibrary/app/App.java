package com.example.siddharth.finallibrary.app;

import android.app.Application;
import android.content.Context;

import com.example.siddharth.finallibrary.data.DBHelper;
import com.example.siddharth.finallibrary.data.DatabaseManager;

/**
 * Created by Siddharth on 4/17/2017.
 */

public class  App extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);

    }


    public static Context getContext(){
        return context;
    }

}