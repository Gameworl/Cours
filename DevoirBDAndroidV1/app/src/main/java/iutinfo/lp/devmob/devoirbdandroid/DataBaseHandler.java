package iutinfo.lp.devmob.devoirbdandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import iutinfo.lp.devmob.devoirbdandroid.model.CSEvent;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "DevoirBDAndroid";
    private static final int DB_VERSION = 1;

    //table
    private static final String TABLE_NAME = "cs_event";
    private static final String TABLE_ROW_ID = "id";
    private static final String TABLE_ROW_EVENTNAME = "cs_eventname";
    private static final String TABLE_ROW_EVENTDATE = "cs_eventdate";

    public DataBaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCsEvent = "CREATE TABLE " + TABLE_NAME + " (" +
                TABLE_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TABLE_ROW_EVENTNAME + " VARCHAR(50) NOT NULL, " +
                TABLE_ROW_EVENTDATE + " VARCHAR(50) NOT NULL " + ");";


        db.execSQL(createTableCsEvent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_table = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(drop_table);
        onCreate(db);
    }

    public void addCS() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_ROW_EVENTNAME, "Cs de BD");
        values.put(TABLE_ROW_EVENTDATE, "01/01/2021");
            try{
                db.insertOrThrow(TABLE_NAME,null,values);
                Log.e("CSEVENT", "insertion Valid");
            }catch (SQLException e){
                Log.e("CSEVENT", "insertion Invalide");
            }
        db.close();
    }

    public List<CSEvent> getListCs() {
        List<CSEvent> listCS = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TABLE_ROW_ID));
            String cs_eventname = cursor.getString(cursor.getColumnIndex(TABLE_ROW_EVENTNAME));
            String cs_eventdate = cursor.getString(cursor.getColumnIndex(TABLE_ROW_EVENTDATE));

            CSEvent cs = new CSEvent(id,cs_eventname,cs_eventdate);

            listCS.add(cs);
        }

        return listCS;
    }

    public void Delete_CS(int id) {
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_NAME, TABLE_ROW_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void Delete_All_CS(){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

}
