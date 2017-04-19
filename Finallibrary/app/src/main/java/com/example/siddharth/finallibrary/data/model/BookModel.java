package com.example.siddharth.finallibrary.data.model;

/**
 * Created by dell on 4/18/2017.
 */

public class BookModel {

    public static final String TAG = BookModel.class.getSimpleName();
    public static final String TABLE = "Book";
    // Labels Table Columns names
    public static final String KEY_BookId = "BookId";
    public static final String KEY_Name = "Name";
    public static final String KEY_Author = "Author";
    public static final String KEY_Publisher = "Publisher";
    public static final String KEY_Edition = "Edition";

    private int BookId;
    private String name;
    private String author;
    private String publisher;
    private String edition;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int BookId) {
        this.BookId = BookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEdition(String edition)
    {
        this.edition=edition;
    }
    public String getEdition()
    {
        return this.edition;
    }

}
