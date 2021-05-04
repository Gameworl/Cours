package iut.lp.dba.birthdayresolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String URL = "content://iut.lp.dba.birthdayprovider/friends";
    private Uri friends= Uri.parse(URL);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void deleteAllBirthday(View view){
        int count = getContentResolver().delete(friends,null, null);
        String countNum = "BirthDay app" + count + "records are deleted ";
        Toast.makeText(getBaseContext(), countNum, Toast.LENGTH_LONG).show();
    }

    public void addBirthday(View view){
        ContentValues values = new ContentValues();
        values.put("name", ((EditText) findViewById(R.id.name)).getText().toString());
        values.put("birthday",((EditText) findViewById(R.id.birthday)).getText().toString());
        Uri uri = getContentResolver().insert(friends, values);
        Toast.makeText(getBaseContext(), uri.toString() + "inserted", Toast.LENGTH_LONG).show();
         ((EditText) findViewById(R.id.name)).setText("");
        ((EditText) findViewById(R.id.birthday)).setText("");
    }

    public void showAllBirthday(View view){
        Cursor c = getContentResolver().query(friends, null,null,null,"name");
        String result = "Birthday app result";

        if (!c.moveToFirst()){
            Toast.makeText(getBaseContext(), result+ "not content yet", Toast.LENGTH_LONG).show();
        }else {
            do {
               result= result + "\n" +c.getString(c.getColumnIndex("name"))
                        + "\n" +c.getString(c.getColumnIndex("id"))
                        + "\n" +c.getString(c.getColumnIndex("birthday"));
            }while (c.moveToNext());
            Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
        }
    }


}