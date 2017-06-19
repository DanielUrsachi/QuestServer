package com.example.dan.questclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

public class RoomListActivity extends AppCompatActivity {
    ListView listView ;
    public static Vector<String> vector;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        listView = (ListView)findViewById(R.id.lista);
        Vector<String> v1 = new Vector<String>();
        v1.add("aa");
        v1.add("bb");
        v1.add("cc");


        intent = new Intent(this, ParticipantActivity.class);


//        Client client = (Client) new Client().execute("");
//
//        Vector<String> v2 = client.getNames();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, v2);
//        listView.setAdapter(adapter);
        Client client = new Client();
        //client.setNames();
        try {
            client.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        Vector<String> v2 = new Vector<String>();
        v2 = client.getNames2();

            for (String vv:v2) {
                String ss = vv;
            }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, v2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //String selected = (String) listView.getSelectedItem();
                String selected  = (String) adapter.getItem(position);
                System.out.println(selected);

                Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                toast.show();

                intent.putExtra("name",selected);
                startActivity(intent);

            }


        });



        //System.out.println(v2.get(2));




    }
}
