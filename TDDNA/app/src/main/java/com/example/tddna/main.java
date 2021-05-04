package com.example.tddna;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class main extends AppCompatActivity {
    protected UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userAdapter = new UserAdapter(main.this , new UsersDB(this).getAllUsers());

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(userAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                LayoutInflater inflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View viewSupress =inflater.inflate(R.layout.supress_dialog, null,false);
                Dialog dialog = new AlertDialog.Builder(main.this).setTitle("Suppression")
                        .setView(viewSupress)
                        .setPositiveButton("valider", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                new UsersDB(main.this).removeUser(Objects.requireNonNull(userAdapter.getItem(position)));
                                updateList();
                            }
                        })
                        .setNegativeButton("annuler", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = userAdapter.getItem(position);
                Intent intent = new Intent(main.this, Contact.class);
                Bundle bundle = new Bundle();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            doAdd();
            return true;
        }
        else if(id == R.id.exit){
            doExit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doExit() {
        LayoutInflater inflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view =inflater.inflate(R.layout.exit_dialog, null,false);

        Dialog dialog = new AlertDialog.Builder(this).setTitle("Exit")
                .setView(view)
                .setPositiveButton("valider", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        finish();
                    }
                })
                .setNegativeButton("annuler", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
    private void doAdd() {
        LayoutInflater inflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view =inflater.inflate(R.layout.create_dialog, null,false);

        Dialog dialog = new AlertDialog.Builder(this).setTitle("Ajouter un contact")
                .setView(view)
                .setPositiveButton("valider", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        EditText etName = (EditText) view.findViewById(R.id.etName);
                        EditText etNumber = (EditText) view.findViewById(R.id.etNumber);

                        User user = new User();
                        user.setName(etName.getText().toString());
                        user.setNumber(etNumber.getText().toString());
                        new UsersDB(main.this).addUser(user);
                        updateList();
                    }
                })
                .create();
        dialog.show();
    }

    private void updateList(){
        userAdapter.clear();
        userAdapter.addAll(new UsersDB(main.this).getAllUsers());
        userAdapter.notifyDataSetChanged();
    }

}
