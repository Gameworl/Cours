package com.example.tddna;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Contact extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        final User user = (User) Objects.requireNonNull(getIntent().getExtras()).getSerializable("user");

        TextView Name = (TextView) this.findViewById(R.id.tvName);
        TextView Number = (TextView) this.findViewById(R.id.tvNumber);
        Name.setText(user.getName());
        Number.setText(user.getNumber());
        Number.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OnClickToast(user.getNumber());
            }
        });
        ImageView img = (ImageView) findViewById(R.id.BackImage);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( Contact.this, main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        ImageView imgTel = (ImageView) findViewById(R.id.imgTelephone);
        imgTel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+user.getNumber()));
                startActivity(appel);
            }
        });
    }

    private void OnClickToast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
}
