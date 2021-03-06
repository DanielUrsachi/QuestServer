package com.example.dan.questclient;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Dan on 19-Jun-17.
 */

public class ClientParticipant extends AsyncTask<String, Void, String> {
    public Vector<String> names;
    public Event event;
    public String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public Event getEvent(){
        return this.event;
    }
    public void setEvent(){
        Event event = new Event();
        try {
            InetAddress ip = InetAddress.getByName("10.0.2.2");
            int port = 7879;

            Socket socket = new Socket(ip, port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            out.writeUTF("object " + this.getName() );
            out.flush();
             event = (Event) ois.readObject();
            if(event != null){
                out.writeUTF("end");
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.event = event;
    }


    @Override
    protected String doInBackground(String... params) {
        setEvent();
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


}
