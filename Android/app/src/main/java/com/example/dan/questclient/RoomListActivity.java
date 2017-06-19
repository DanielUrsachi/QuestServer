package com.example.dan.questclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Vector;
import java.util.concurrent.ExecutionException;


public class RoomListActivity extends AppCompatActivity {
    ListView listView ;
    public static Vector<String> vector;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        listView = (ListView)findViewById(R.id.lista);
        intent = new Intent(this, ParticipantActivity.class);

        Client client = new Client();

        try {
            client.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        Vector<String> v2 = new Vector<String>();
        v2 = client.getNames2();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, v2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected  = (String) adapter.getItem(position);

                intent.putExtra("name",selected);
                startActivity(intent);

            }


        });
    }
}
