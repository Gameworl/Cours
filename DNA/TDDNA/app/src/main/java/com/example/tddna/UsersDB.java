package com.example.tddna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'acces à la base de données
 */
public class UsersDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UsersDB";
    private static final int DATABASE_VERSION = 1;
	
    private static final String USER_TABLE_NAME = "user";

	public static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER = "number";

	     private static final String USER_TABLE_CREATE =
                "CREATE TABLE " + USER_TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_NUMBER + " TEXT);";
						
	public UsersDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// Rien pour le moment
    }

    /**
     * Retourne tous les utilisateurs
     * @return tous les utilisateurs
     */
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();

        Cursor cursor = getReadableDatabase().query(USER_TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String number = cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER));

            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setNumber(number);

            result.add(user);
        }

        return result;
    }

    /**
     * Ajoute un utilisateur
     * @param user l'utilisateur
     */
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_NUMBER, user.getNumber());
        getWritableDatabase().insert(USER_TABLE_NAME, null, values);
    }

    /**
     * Supprime un utilisateur
     * @param user l'utilisateur
     */
    public void removeUser(User user) {
        getWritableDatabase().delete(USER_TABLE_NAME, COLUMN_ID + " = ?", new String[]{ Integer.toString(user.getId())});
    }
}
