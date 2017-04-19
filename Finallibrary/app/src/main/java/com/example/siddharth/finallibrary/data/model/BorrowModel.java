package com.example.siddharth.finallibrary.data.model;

/**
 * Created by dell on 4/18/2017.
 */

public class BorrowModel {

    public static final String TAG = BorrowModel.class.getSimpleName();
    public static final String TABLE = "Borrow";

    // Labels Table Columns names
    public static final String KEY_BorrowID = "BorrowId";
    public static final String KEY_MName = "MName";
    public static final String KEY_BName = "BName";
    public static final String KEY_BAuthor= "BAuthor";


    private int ID ;
    private String mname;
    private String bname;
    private String bauthor;


    public int getBorrowId() {
        return ID;
    }

    public void setBorrowId(int ID) {
        this.ID = ID;
    }

    public String getMName() {
        return mname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setName(String mname) {
        this.mname = mname;
    }

    public void setBauthor(String bauthor)
    {
        this.bauthor=bauthor;
    }
    public String getBauthor()
    {
        return bauthor;
    }




}
