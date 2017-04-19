package com.example.siddharth.finallibrary.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.siddharth.finallibrary.Member;
import com.example.siddharth.finallibrary.data.DatabaseManager;
import com.example.siddharth.finallibrary.data.model.MemberModel;

/**
 * Created by Siddharth on 4/17/2017.
 */

public class MemberRepo {

    private MemberModel memberModel;

    public MemberRepo(){

        memberModel= new MemberModel();

    }


    public static String createTable(){
        return "CREATE TABLE IF NOT EXISTS " + MemberModel.TABLE  + "("
                + MemberModel.KEY_Username+ " TEXT  PRIMARY KEY, "
                + MemberModel.KEY_Password + " TEXT, " + MemberModel.KEY_Fullname + " TEXT, " + MemberModel.KEY_Type + " TEXT, " + MemberModel.KEY_Fine + " INTEGER )";
    }



    public boolean insert(MemberModel memberModel) {
        long memberId;
        Log.v("insert_member",memberModel.getusername()+" "+memberModel.getpassword());
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Log.v("insert_member",memberModel.getusername()+" "+memberModel.getpassword());
        ContentValues values = new ContentValues();
        Log.v("insert_member",memberModel.getusername()+" "+memberModel.getpassword());
        values.put(MemberModel.KEY_Username, memberModel.getusername());
        values.put(MemberModel.KEY_Password, memberModel.getpassword());
        values.put(MemberModel.KEY_Fullname,memberModel.getName());
        values.put(MemberModel.KEY_Type,memberModel.getType());
        values.put(MemberModel.KEY_Fine,memberModel.getFine());
        Log.v("insert_member",memberModel.getusername()+" "+memberModel.getpassword());

        // Inserting Row
        db.insertWithOnConflict(MemberModel.TABLE,null,values,SQLiteDatabase.CONFLICT_IGNORE);
      //  db.insert(MemberModel.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return true;

    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(MemberModel.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public boolean checkMemberUsername (String username)
    {
        String[] columns = {MemberModel.KEY_Username,MemberModel.KEY_Password};
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        // selection criteria
        String selection = MemberModel.KEY_Username + " = ?";

        // selection arguments
        String[] selectionArgs = {username};
        Cursor cursor = db.query(MemberModel.TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                         //The sort order
        cursor.moveToFirst();
        // String na = cursor.getString(cursor.getColumnIndex(password));
        //Log.i("Checkuserandpassword",na);

        int cursorCount = cursor.getCount();
        cursor.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;

    }
    public boolean checkMemberUsernameAndPassword(String username,String password)
    {
        // array of columns to fetch
        String[] columns = {MemberModel.KEY_Username,MemberModel.KEY_Password};
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        // selection criteria
        String selection = MemberModel.KEY_Username + " = ?" + " AND " + MemberModel.KEY_Password + " = ?";

        // selection arguments
        String[] selectionArgs = {username,password};


        Cursor cursor = db.query(MemberModel.TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;

    }

    public Cursor getFullName(String username)
    {
        String[] columns = {MemberModel.KEY_Username,MemberModel.KEY_Fullname,MemberModel.KEY_Fine
                };
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        // selection criteria
        String selection = MemberModel.KEY_Username + " = ?";

        // selection arguments
        String[] selectionArgs = {username};
        Cursor cursor =  db.query(MemberModel.TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                         //The sort order
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor fetchAll_member()
    {
       SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor mCursor = db.query(MemberModel.TABLE, new String[] { MemberModel.KEY_Username, MemberModel.KEY_Fullname, MemberModel.KEY_Password, MemberModel.KEY_Type,MemberModel.KEY_Fine }, null, null, null, null, null);
        if(mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
