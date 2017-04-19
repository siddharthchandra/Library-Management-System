package com.example.siddharth.finallibrary.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.siddharth.finallibrary.data.DatabaseManager;
import com.example.siddharth.finallibrary.data.model.BookModel;

/**
 * Created by Siddharth on 4/17/2017.
 */

public class BookRepo {
    private BookModel bookModel;

    public BookRepo(){

        bookModel = new BookModel();

    }


    public static String createTable(){
        return "CREATE TABLE IF NOT EXISTS " + BookModel.TABLE  + "("
                + BookModel.KEY_BookId  + "  INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookModel.KEY_Name + " TEXT, " + BookModel.KEY_Author + " TEXT, " + BookModel.KEY_Publisher + " TEXT, "+ BookModel.KEY_Edition + " TEXT)";
    }


    public Boolean insert(BookModel bookModel) {
        int bookId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(BookModel.KEY_Name, bookModel.getName());
        values.put(BookModel.KEY_Author,bookModel.getAuthor());
        values.put(BookModel.KEY_Publisher,bookModel.getPublisher());
        values.put(BookModel.KEY_Edition,bookModel.getEdition());
        String insert =" INSERT INTO "+BookModel.TABLE+"("+BookModel.KEY_BookId+","+BookModel.KEY_Name+","+BookModel.KEY_Author+","+BookModel.KEY_Publisher+","+BookModel.KEY_Edition+") VALUES("+bookModel.getBookId()+","+bookModel.getName()+","+bookModel.getAuthor()+","+bookModel.getPublisher()+","+bookModel.KEY_Edition+")";
        bookId=(int)db.insert(BookModel.TABLE, null, values);
        Log.v("Insert into BookRepo",insert);
        DatabaseManager.getInstance().closeDatabase();

        return true;
    }
    public Cursor fetchAll()
    {
        SQLiteDatabase db1=DatabaseManager.getInstance().openDatabase();
        Cursor data = db1.rawQuery("SELECT * FROM "+ BookModel.TABLE+" GROUP BY "+BookModel.KEY_Name,null);
        return data;
    }
    public Cursor fetchAllbook()
    {
        SQLiteDatabase db1=DatabaseManager.getInstance().openDatabase();
        Cursor data = db1.rawQuery("SELECT * FROM "+ BookModel.TABLE,null);
        return data;
    }




    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(BookModel.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
    public Cursor checkBookname(String bookname) {

        // array of columns to fetch
        String[] columns = {BookModel.KEY_BookId,BookModel.KEY_Name,BookModel.KEY_Author,BookModel.KEY_Publisher};
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        // selection criteria
        String selection = BookModel.KEY_Name + " = ?";
        String query = "SELECT "+BookModel.KEY_Name+" FROM "+BookModel.TABLE+" WHERE "+BookModel.KEY_Name+"="+bookname;

        // selection arguments
        String[] selectionArgs = {bookname};
        Cursor cursor =  db.query(BookModel.TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                         //The sort order
        cursor.moveToFirst();
        Log.v("checkbookname",query);
        return cursor;

    }
    public Cursor checkBookpublisher(String publisher) {

        // array of columns to fetch
        String[] columns = {BookModel.KEY_BookId,BookModel.KEY_Name,BookModel.KEY_Author,BookModel.KEY_Publisher};
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        // selection criteria
        String selection = BookModel.KEY_Publisher + " = ?";
        String query = "SELECT "+BookModel.KEY_Name+" FROM "+BookModel.TABLE+" WHERE "+BookModel.KEY_Publisher+"="+publisher;
        // selection arguments
        String[] selectionArgs = {publisher};
        Cursor cursor =  db.query(BookModel.TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                         //The sort order
        cursor.moveToFirst();
        Log.v("checkBookpublisher",query);
        return cursor;

    }
    public void delete_book(String book_id)
    {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(BookModel.TABLE, BookModel.KEY_BookId+" = ? ", new String[] {book_id});
    }
    public long average_bookid()
    {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor data = db.rawQuery("SELECT AVG("+BookModel.KEY_BookId+") FROM "+ BookModel.TABLE,null);
        Log.v("averagequery", DatabaseUtils.dumpCursorToString(data));
        data.moveToFirst();
        return data.getLong(data.getColumnIndex("AVG("+BookModel.KEY_BookId+")"));
    }


}
