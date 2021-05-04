#Provider 
## contact provider
```java
public class ContactProvider extends ContentProvider {
    static final String PROVIDER_NAME = "iut.lp.dba.contactprovider";
    static final String URL = "content://" +PROVIDER_NAME + "/contacts";
    static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String TABLE_NAME = "contact";


    //Schéma Contact
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_MAIL = "mail";

    static final int CONTACTS = 1;
    static final int CONTACT = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "contacts", CONTACTS);
        uriMatcher.addURI(PROVIDER_NAME, "contacts/#", CONTACT);
    }

    DataBaseHandler databaseManager;
    SQLiteDatabase database;

    public static HashMap<String, String> contactMap;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        databaseManager = new DataBaseHandler(context);
        database = databaseManager.getWritableDatabase();

        if (database == null)
            return false;
        else
            return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)){
            //maps all database columns names
            case CONTACTS:
                queryBuilder.setProjectionMap(contactMap);
                break;
            case CONTACT:
                queryBuilder.appendWhere(KEY_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == ""){
            // no sorting => sort on names by default
            sortOrder = KEY_NAME;
        }
        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long row = database.insert(TABLE_NAME, "", values);

        //if record is added successfully
        if (row >0){
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case CONTACTS:
                return "vnd.android.cursor.dir/vnd.contactprovider.contacts";
            case CONTACT:
                return "vnd.android.cursor.dir/vnd.contactprovider.contacts";
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);

        }
    }

}
```

## dataBase 
```java

public class DataBaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "db_contact";
    public static final String TABLE_NAME = "contact";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME ="name";
    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_EMAIL = "email";


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

    public int UpdateContact() {
        return 0;
    }

    public void DeleteContact(){

    }
}
```

Main classe
```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show_contact(View view){
        Cursor c = getContentResolver().query(CONTENT_URI,null,null,null, "name");
        String result = "Contact resolver app";
        if (!c.moveToFirst()){
            Toast.makeText(this,  result + "not inserted  yet", Toast.LENGTH_LONG).show();
        }else {
            do {
                result = result + "\n"+ "id : " + c.getString(c.getColumnIndex("id")) +  "\n"+
                        "name : " + c.getString(c.getColumnIndex("name")) +  "\n"+
                        "phone : " + c.getString(c.getColumnIndex("phone_number")) +  "\n"+
                        "email : " + c.getString(c.getColumnIndex("email")) +  "\n";
            }while (c.moveToNext());

            Toast.makeText(this,  result , Toast.LENGTH_LONG).show();
        }
    }
}
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.126" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="64dp"
        android:text="@string/show"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        android:onClick="show_contact"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```