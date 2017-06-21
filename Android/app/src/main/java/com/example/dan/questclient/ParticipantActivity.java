package com.example.dan.questclient;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.concurrent.ExecutionException;



public class ParticipantActivity extends AppCompatActivity {
    TextView clientHint;
    TextView clientNume;
    TextView clientTime;
    TextView clientEtapa;
    EditText clientPass;
    TextView clientNr;

    Button nextButton;
    CountDownTimer c;
    public Event event;


    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    final int[] i = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);

        TextView clientHint = (TextView) findViewById(R.id.clientHint);
        TextView clientNume = (TextView) findViewById(R.id.clientNume);
        TextView clientTime = (TextView)findViewById(R.id.clientTime);
        TextView clientEtapa = (TextView) findViewById(R.id.clientEtapa);
        TextView clientNr = (TextView) findViewById(R.id.clientNr);
        EditText clientPass = (EditText) findViewById(R.id.clientPass);
        Button nextButton = (Button) findViewById(R.id.nextButton);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

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

        //fill fields
        clientNume.setText(this.event.getName());
        clientEtapa.setText(Integer.toString(this.event.getEtapa())+1);
        clientHint.setText(this.event.getHint(1));
        clientNr.setText(Integer.toString(this.event.getNr()));

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        setTimer(this.event.getTime());
        mCountDownTimer.start();

    }

    public void setTimer(final int time) {
        final TextView clientTime = (TextView)findViewById(R.id.clientTime);;
        mProgressBar.setMax(time);
        mProgressBar.setProgress(i[0]);
        mCountDownTimer = new CountDownTimer(time * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress" + i[0] + millisUntilFinished);
                i[0]++;
                mProgressBar.setProgress(i[0]);
                clientTime.setText(Integer.toString(event.getTime() - i[0]));
            }
            @Override
            public void onFinish() {
                mProgressBar.setProgress(0);
                Toast toast = Toast.makeText(getApplicationContext(), "You loose", Toast.LENGTH_SHORT);
                toast.show();
            }
        };


    }
    public void check(){
        EditText clientPass = (EditText) findViewById(R.id.clientPass);
        TextView clientEtapa = (TextView) findViewById(R.id.clientEtapa);
        TextView clientHint = (TextView) findViewById(R.id.clientHint);
        String s = String.valueOf(clientPass.getText());
        if(s.equals(event.getPass(event.getEtapa()+1))){
            Toast toast = Toast.makeText(getApplicationContext(), "Corect!!!", Toast.LENGTH_SHORT);
            toast.show();
            if(event.getEtapa()<event.getNr()-1){
                int et = event.getEtapa()+1;
                event.setEtapa(et);

                clientEtapa.setText(Integer.toString(et+1));
                clientHint.setText(this.event.getHint(et+1));

                mCountDownTimer.cancel();
                setTimer(this.event.getTime());
                i[0]=0;
                mCountDownTimer.start();
            }
            else if(event.getEtapa()==event.getNr()-1){
                toast = Toast.makeText(getApplicationContext(), "Felicitari", Toast.LENGTH_SHORT);

                toast.show();
                mCountDownTimer.cancel();
            }
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Incorect!!!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void onClick(View v) {
                check();
    }
}
