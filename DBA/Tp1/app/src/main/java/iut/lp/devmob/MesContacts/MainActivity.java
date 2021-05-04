package iut.lp.devmob.MesContacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button Add_btn;
    ListView Contact_listView;
    ArrayList<Contact> Contact_data = new ArrayList<Contact>();
    Contact_Adapter CAdapter;
    DataBaseHandler db;
    String Toast_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Contact_listView = (ListView) findViewById(R.id.list);
            Contact_listView.setItemsCanFocus(false);
            Add_btn = (Button) findViewById(R.id.add_btn);
            Set_Refresh_data();
        }catch (Exception e){
            Log.d("Error", ""+e);
        }
        Add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_user = new Intent(MainActivity.this,Add_Update_Contact.class);
                add_user.putExtra("Calles", "add");
                add_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(add_user);
                finish();
            }
        });
    }

    public  void Set_Refresh_data(){
        Contact_data.clear();
        db = new DataBaseHandler(this);
        ArrayList<Contact> contact_Array_from_db = db.GetAllContacts();
        for (int i=0; i< contact_Array_from_db.size(); i++){
            int tidno = contact_Array_from_db.get(i).getId();
            String name = contact_Array_from_db.get(i).getName();
            String mobile = contact_Array_from_db.get(i).getPhoneNumber();
            String email = contact_Array_from_db.get(i).getEmail();
            Contact contact = new Contact();
            contact.setId(tidno);
            contact.setName(name);
            contact.setPhoneNumber(mobile);
            contact.setEmail(email);
            Contact_data.add(contact);
        }
        db.close();
        CAdapter = new Contact_Adapter(MainActivity.this,R.layout.listview_row, Contact_data);
        Contact_listView.setAdapter(CAdapter);
        CAdapter.notifyDataSetChanged();
    }

    public void Show_Toast(){
        Toast.makeText(getApplicationContext(),Toast_msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        Set_Refresh_data();
    }
}