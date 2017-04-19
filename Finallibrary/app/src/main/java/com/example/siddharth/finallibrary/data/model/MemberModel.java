package com.example.siddharth.finallibrary.data.model;

/**
 * Created by dell on 4/18/2017.
 */

public class MemberModel {

    public static final String TAG = MemberModel.class.getSimpleName();
    public static final String TABLE = "Member";
    // Labels Table Columns names
    public static final String KEY_Password = "Password";
    public static final String KEY_Username = "Username";
    public static final String KEY_Fullname = "Name";
    public static final String KEY_Type = "Type";
    public static final String KEY_Fine = "Fine";


    private String password;
    private String username;
    private String name;
    private String type;
    private int fine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFine() {
        return fine;
    }

    public void setFine() {
        this.fine = 0;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String ref) {
        this.password = ref;
    }

}
