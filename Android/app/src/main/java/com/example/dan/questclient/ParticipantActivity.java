package com.example.dan.questclient;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import android.widget.ProgressBar;
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
    TextView clientNume;
    TextView clientTimp;
    TextView clientEtapa;
    EditText clientPass;
    CountDownTimer c;
    public Event event;


    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    final int[] i = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);

        TextView clientHint = (TextView)findViewById(R.id.clientHint);
        TextView clientNume = (TextView)findViewById(R.id.clientNume);
        TextView clientTimp = (TextView)findViewById(R.id.clientTimp);
        TextView clientEtapa = (TextView)findViewById(R.id.clientEtapa);
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

        clientNume.setText(event.getName());




        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);


        setTimer(this.event.getTime());
        //c.start();
        mCountDownTimer.start();

    }
    public void showTime(int nr){
//        TextView clientTimp = (TextView)findViewById(R.id.clientTimp);
//        clientTimp.setText(nr);
    }
    public void setTimer(final int time){
        mProgressBar.setMax(time);
        mProgressBar.setProgress(i[0]);
        mCountDownTimer=new CountDownTimer(time*1000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i[0] + millisUntilFinished);
                i[0]++;
                mProgressBar.setProgress(i[0]);

            }

            @Override
            public void onFinish() {
                //Do what you want
                i[0]++;
                mProgressBar.setProgress(i[0]);
                Toast toast = Toast.makeText(getApplicationContext(), "You loose", Toast.LENGTH_SHORT);
                toast.show();
            }
        };



    }


}
