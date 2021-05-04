package com.example.tp1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SecondActivity extends Activity {
    private ListView mListView;
    private String[] prenoms = new String[]{
            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String value = getIntent().getStringExtra("key"); //if it's a string you stored.
        mListView = (ListView) findViewById(R.id.listView);
       final String[] list = value.split("@");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondActivity.this,
                android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);
    }
}
