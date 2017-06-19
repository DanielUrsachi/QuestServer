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

public class Client extends AsyncTask<String, Void, String> {
    public Vector<String> names;
    public void setNames(){
        Vector<String> vector = new Vector<String>();
        try {
            InetAddress ip = InetAddress.getByName("10.0.2.2");
            int port = 7879;
            Socket socket = new Socket(ip, port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            out.writeUTF("list");
            out.flush();

            vector  = (Vector<String>) ois.readObject();

            if (vector != null){
                out.writeUTF("end");
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.names = vector;
    }

    public Vector<String> getNames2(){
        return this.names;
    }
    @Override
    protected String doInBackground(String... params) {
        this.setNames();
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
