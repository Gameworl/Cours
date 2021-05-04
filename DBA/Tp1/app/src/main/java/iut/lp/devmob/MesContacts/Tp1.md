# DBA
## TP1

### Les constantes 
```java
public class DataBaseConstants {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "db_contact";
    public static final String TABLE_NAME = "contact";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME ="name";
    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_EMAIL = "email";
    public static final ArrayList<Contact> contact_list = new ArrayList<Contact>();
}
```

### le Handler
```java
public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

// creation de la table de la base de donnée
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

    // on drop la table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_table = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(drop_table);
        onCreate(db);
    }

    // Ajour d'un contact dans la base de donnée
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());
        values.put(KEY_EMAIL, contact.getEmail());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    // recupération de la liste de tout les contacts
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

}

```