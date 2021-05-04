package iut.lp.dba.birthdayprovider;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BirthdayTable {
    public static final String TABLE_NAME = "BIRTHDAY";
    static final String ID = "id";
    static final String NAME = "name";
    static final String BIRTHDAY = "birthday";

    static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+
            ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            NAME+" TEXT NOT NULL,"+
            BIRTHDAY+" TEXT NOT NULL"+
            ");";
    public static void onCreate(SQLiteDatabase database){
        database.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(BirthdayTable.class.getName(), "Upgrading database fron version "
                + oldVersion + "to " + newVersion +
                ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(database);
    }
}
