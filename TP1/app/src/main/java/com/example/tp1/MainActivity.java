package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonToast = (Button) findViewById(R.id.buttonToast);
        final EditText editToast = (EditText)  findViewById(R.id.editTextToast);
        final Button buttonNext = (Button) findViewById(R.id.buttonNext);
        final EditText editNext = (EditText)  findViewById(R.id.editTextNext);
        final Button buttonList = (Button) findViewById(R.id.buttonList);
        final EditText editList = (EditText)  findViewById(R.id.editTextList);
        buttonToast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OnClickToast(editToast.getText().toString());
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              OnClickNext(editNext.getText().toString());
            }
        });
        buttonList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OnclickList(editList.getText().toString());
            }
        });
    }
    private void OnClickToast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
    private void OnClickNext(String text){
        Intent myIntent = new Intent(this, firstActivity.class);
        myIntent.putExtra("key", text); //Optional parameters
        startActivity(myIntent);
    }

    private  void OnclickList(String text){
        Intent myIntent = new Intent(this, firstActivity.class);
        myIntent.putExtra("key", text); //Optional parameters
        startActivity(myIntent);

    }
}