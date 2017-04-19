package com.example.siddharth.finallibrary.data;

/**
 * Created by Tan on 1/26/2016.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.siddharth.finallibrary.app.App;
import com.example.siddharth.finallibrary.data.model.BookModel;
import com.example.siddharth.finallibrary.data.model.BorrowModel;
import com.example.siddharth.finallibrary.data.model.MemberModel;
import com.example.siddharth.finallibrary.data.repo.BookRepo;
import com.example.siddharth.finallibrary.data.repo.BorrowRepo;
import com.example.siddharth.finallibrary.data.repo.MemberRepo;


public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION =15;
    // Database Name
    private static final String DATABASE_NAME = "data.db";
    private static final String TAG = DBHelper.class.getSimpleName().toString();

    public DBHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(BookRepo.createTable());
        db.execSQL(BorrowRepo.createTable());
        db.execSQL(MemberRepo.createTable());
        //db.execSQL(StudentCourseRepo.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + BookModel.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BorrowModel.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MemberModel.TABLE);
        //db.execSQL("DROP TABLE IF EXISTS " + StudentCourse.TABLE);
        onCreate(db);
    }

}