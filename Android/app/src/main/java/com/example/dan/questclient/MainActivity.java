package com.example.dan.questclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button createB;
    Button joinB;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createB = (Button)findViewById(R.id.create);
        Button joinB = (Button)findViewById(R.id.join);
        createB.setOnClickListener(this);
        joinB.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create:
                intent = new Intent(this, CreatorActivity.class);
                startActivity(intent);
                break;
            case R.id.join:
                intent = new Intent(this, RoomListActivity.class);
                startActivity(intent);
                break;
        }

    }
}
