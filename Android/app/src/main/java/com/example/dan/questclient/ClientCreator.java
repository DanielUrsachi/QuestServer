package com.example.dan.questclient;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.ObjectOutputStream;
import java.net.InetAddress;

import java.net.Socket;


/**
 * Created by Dan on 18-Jun-17.
 */

public class ClientCreator extends AsyncTask<String, Void, String> {
    public Event event;
    public int request;
    public void setEvent(Event event){
        this.event = event;

    }

    public int getRequest(){
        return this.request;
    }
    public void setRequest(int request){
        this.request = request;
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        sendCreator();
        return null;
    }

    public void sendCreator(){
        try {
            InetAddress ip = InetAddress.getByName("10.0.2.2");
            int port = 7878;

            Socket socket = new Socket(ip, port);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            out.writeUTF("add");
            out.flush();

            oos.writeObject(this.event);
            oos.flush();

            String msg = in.readUTF();
            if (msg.equals("succes")){
                setRequest(1);
                out.writeUTF("end");
            }else if(msg.equals("eroare")){
                setRequest(0);
                out.writeUTF("end");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
