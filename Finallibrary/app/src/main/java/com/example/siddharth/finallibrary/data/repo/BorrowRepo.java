package com.example.siddharth.finallibrary.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.siddharth.finallibrary.data.DatabaseManager;
import com.example.siddharth.finallibrary.data.model.BookModel;
import com.example.siddharth.finallibrary.data.model.BorrowModel;
import com.example.siddharth.finallibrary.data.model.MemberModel;

/**
 * Created by Siddharth on 4/17/2017.
 */

public class BorrowRepo {
    private BorrowModel borrowModel;

    public BorrowRepo(){

        borrowModel= new BorrowModel();

    }


    public static String createTable(){
        return "CREATE TABLE IF NOT EXISTS " + BorrowModel.TABLE  + "("
                + BorrowModel.KEY_BorrowID + " INTEGER  PRIMARY KEY, "
                + BorrowModel.KEY_MName + " TEXT, " + BorrowModel.KEY_BName + " TEXT, "+BorrowModel.KEY_BAuthor+" TEXT )";
    }



    public Boolean insert(BorrowModel borrowModel) {
        int borrowId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(BorrowModel.KEY_BorrowID, borrowModel.getBorrowId());
        values.put(BorrowModel.KEY_MName, borrowModel.getMName());
        values.put(BorrowModel.KEY_BName,borrowModel.getBname());
        values.put(BorrowModel.KEY_BAuthor,borrowModel.getBauthor());
        Log.v("insertingborrow","check0");
        // Inserting Row
        borrowId=(int)db.insert(BorrowModel.TABLE, null, values);
        Log.v("insertingborrow",String.valueOf(numberOfRows_Borrows()));
        Log.v("insertingborrow","done");
        DatabaseManager.getInstance().closeDatabase();
        return true;

    }
    public int numberOfRows_Borrows() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Log.v("insertborrow","checkborrow5");
        int numRows = (int) DatabaseUtils.queryNumEntries(db, BorrowModel.TABLE);
        Log.v("insertborrow","checkborrow6");
        return numRows;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(BorrowModel.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
    public Cursor borrow_join_book(String username) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Log.v("checking","check5");
      //  _OrderBy = BookModel.KEY_Name + " ASC";
        String query = " SELECT * FROM " + BorrowModel.TABLE + " As A "
                +"INNER JOIN "+BookModel.TABLE+" As B WHERE A."+BorrowModel.KEY_BorrowID+" = B."+BookModel.KEY_BookId
                +" AND A."+BorrowModel.KEY_MName+" = "+username;
        String[] columns = {BookModel.KEY_Name
        };
        // selection criteria
        String selection = BorrowModel.KEY_MName+ " = ?";

        // selection arguments
        String[] selectionArgs = {username};
        Log.v("print",query);
        Cursor c = db.query(BorrowModel.TABLE+" A JOIN "+BookModel.TABLE+" B ON A."+BorrowModel.KEY_BorrowID+" = B."+BookModel.KEY_BookId,columns,selection,selectionArgs,null,null,null);
        return c;
    }
public Cursor fetchAll_borrow()
{
    SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
    Log.v("fetah","check0");
    Cursor mCursor = db.rawQuery("SELECT * FROM " + BorrowModel.TABLE,null);
    Log.v("fetah","check1");
    return mCursor;
}
    public Cursor fetch_all_borrow_name(String username)
    {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + BorrowModel.TABLE+" WHERE "+BorrowModel.KEY_MName+" = ?",new String[]{username});
        return mCursor;

    }
    public Cursor fetch_all_borrow_bookname(String bookname)
    {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + BorrowModel.TABLE+" WHERE "+BorrowModel.KEY_BName+" = ?",new String[]{bookname});
        return mCursor;

    }

    public Cursor fetch_all_borrow_bookauthor(String author)
    {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + BorrowModel.TABLE+" WHERE "+BorrowModel.KEY_BAuthor+" = ?",new String[]{author});
        return mCursor;

    }
}
