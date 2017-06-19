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

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            out.writeUTF("object " + this.getName() );
            out.flush();
            //Thread.sleep(4000);
             event = (Event) ois.readObject();
            if(event != null){
                out.writeUTF("end");
            }



            //event1.showEvent();






//            vector  = (Vector<String>) ois.readObject();
//
//            if (vector != null){
//                out.writeUTF("end");
//                out.flush();
//            }




        } catch (Exception e) {
            e.printStackTrace();
        }

        this.event = event;
    }
    public void setNames2(){
        Vector<String> v1 = new Vector<String>();
        v1.add("aa");
        v1.add("bb");
        v1.add("cc");
        this.names = v1;
    }
    public Vector<String> getNames2(){
        return this.names;
    }
    @Override
    protected String doInBackground(String... params) {
        //System.out.println(getNames().get(0));

        //String s = this.getName();
        setEvent();

        System.out.println("am fost aici");

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


}
