# Resolver
## Main
```java
public class MainActivity extends AppCompatActivity {

    private String URL = "content://iut.lp.dba.contactprovider/contacts";
    private Uri contacts = Uri.parse(URL);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Add_btn(View view) {
        ContentValues values = new ContentValues();
        values.put("name","Natan Charpentier");
        values.put("phone_number","0695027213");
        values.put("email", "test@gmail.com");

        Uri uri = getContentResolver().insert(contacts, values);
        Toast.makeText(getBaseContext(), "Contact resolver app :"+ uri.toString() + " inserted", Toast.LENGTH_LONG).show();
    }

    public void show_btn(View view){

        Cursor c = getContentResolver().query(contacts,null,null,null, "name");
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

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/add"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        tools:layout_editor_absoluteY="113dp"
        tools:ignore="MissingConstraints"
        android:onClick="Add_btn"/>

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="64dp"
        android:text="@string/show"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button"
        android:onClick="show_btn"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```