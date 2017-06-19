package com.example.dan.questclient;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class ParticipantActivity extends AppCompatActivity {
    Intent intent;
    TextView clientHint;
    TextClock textClock;
    EditText clientPass;
    CountDownTimer c;
    public Event event;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);

        TextView clientHint = (TextView)findViewById(R.id.clientHint);
        TextClock textClock = (TextClock)findViewById(R.id.textClock);
        EditText clientPass = (EditText)findViewById(R.id.clientPass);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        System.out.println("activity nou " + name);


        ClientParticipant client = new ClientParticipant();
        client.setName(name);
        try {
            client.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        this.event = client.getEvent();
        this.event.showEvent();
//        String timeStamp = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
//        System.out.println(timeStamp);

        //textClock.setText(timeStamp);


        this.i = this.event.getTime();
        System.out.println(this.i);
        c = new CountDownTimer(40000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval

            public void onTick(long millisUntilFinished) {
                System.out.println("aaaa");
            }
            public void onFinish() {
                Toast toast = Toast.makeText(getApplicationContext(), "You loose", Toast.LENGTH_SHORT);
                toast.show();
            }
        };

        c.start();


    }


}
