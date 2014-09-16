package org.transparencia.gov2go.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pedrosjc on 20/04/14.
 */
public class Dao {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public Dao(Context context) {
        helper = new DatabaseHelper(context);
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close() {
        helper.close();
    }
}
