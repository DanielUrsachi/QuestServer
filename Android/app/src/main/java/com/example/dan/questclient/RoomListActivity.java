package com.example.dan.questclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Vector;

public class RoomListActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        listView = (ListView)findViewById(R.id.lista);
        Vector<String> v1 = new Vector<String>();
        v1.add("aa");
        v1.add("bb");
        v1.add("cc");
        listView.
    }
}
