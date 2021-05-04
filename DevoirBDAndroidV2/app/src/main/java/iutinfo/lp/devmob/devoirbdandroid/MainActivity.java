package iutinfo.lp.devmob.devoirbdandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URI;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URL = "iutinfo.lp.devmob.devoirbdandroid.CsProvider";
    private Uri cs = Uri.parse(URL);

    Button buttonAdd;
    Button buttonList;
    Button buttonUpdate;
    Button buttonDelete;
    Button buttonDeleteAll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdd = findViewById(R.id.ADD);
        buttonList = findViewById(R.id.LIST);
        buttonUpdate = findViewById(R.id.UPDATE);
        buttonDelete = findViewById(R.id.DELETE);
        buttonDeleteAll = findViewById(R.id.DELETES);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_cs();
            }
        });

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_cs();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_CS();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_CS();
            }
        });

        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_All_CS();
            }
        });

    }

    public void Add_cs() {
        ContentValues values = new ContentValues();
        values.put("cs_eventname","Cs de Bd");
        values.put("cs_eventdate","01/01/12");

        Uri uri = getContentResolver().insert(cs, values);
        Toast.makeText(getBaseContext(), "Cs resolver app :"+ uri.toString() + " inserted", Toast.LENGTH_LONG).show();
    }

    public void list_cs(){
        Cursor c = getContentResolver().query(cs,null,null,null, null);
        String result = "CS resolver app";
        if (!c.moveToFirst()){
            Toast.makeText(this,  result + "not inserted  yet", Toast.LENGTH_LONG).show();
        }else {
            do {
                result = result + "\n"+ "id : " + c.getString(c.getColumnIndex("id")) +  "\n"+
                        "name : " + c.getString(c.getColumnIndex("cs_eventname")) +  "\n"+
                        "date : " + c.getString(c.getColumnIndex("cs_eventdate")) +  "\n";
            }while (c.moveToNext());

            Toast.makeText(this,  result , Toast.LENGTH_LONG).show();
        }
    }

    public void update_CS(){

    }

    public void delete_CS(){

    }

    public void delete_All_CS(){

    }



}

