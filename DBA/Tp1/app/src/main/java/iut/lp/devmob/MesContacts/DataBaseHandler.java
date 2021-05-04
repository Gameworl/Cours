package iut.lp.devmob.MesContacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static iut.lp.devmob.MesContacts.DataBaseConstants.DATABASE_NAME;
import static iut.lp.devmob.MesContacts.DataBaseConstants.DATABASE_VERSION;
import static iut.lp.devmob.MesContacts.DataBaseConstants.KEY_EMAIL;
import static iut.lp.devmob.MesContacts.DataBaseConstants.KEY_ID;
import static iut.lp.devmob.MesContacts.DataBaseConstants.KEY_NAME;
import static iut.lp.devmob.MesContacts.DataBaseConstants.KEY_PHONE_NUMBER;
import static iut.lp.devmob.MesContacts.DataBaseConstants.TABLE_NAME;
import static iut.lp.devmob.MesContacts.DataBaseConstants.contact_list;

public class DataBaseHandler extends SQLiteOpenHelper {



    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " + TABLE_NAME +"(" +
                KEY_ID + " INTEGER PRIMARY KEY,"+
                KEY_NAME +" TEXT,"+
                KEY_PHONE_NUMBER + " TEXT,"+
                KEY_EMAIL + " TEXT"+
                ")" ;
        db.execSQL(create_table);
        Log.d("dataBaseHandler", "db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_table = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(drop_table);
        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());
        values.put(KEY_EMAIL, contact.getEmail());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public ArrayList<Contact> GetAllContacts(){
        try {
            contact_list.clear();

            String selectQuery = "SELECT * FROM "+ TABLE_NAME;

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(selectQuery, null);

            // loop raw for adding in arrayList
            if (cursor.moveToFirst()){
                do {
                    Contact contact = new Contact();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setPhoneNumber(cursor.getString(2));
                    contact.setEmail(cursor.getString(3));

                    contact_list.add(contact);
                } while (cursor.moveToNext());
            }
            //return contact list
            cursor.close();
            db.close();
            return contact_list;
        }catch (Exception e){
            Log.d("ALLCONTACT", ""+e);
        }
        return contact_list;
    }

    public int UpdateContact() {
        return 0;
    }

    public void DeleteContact(){

    }
}
