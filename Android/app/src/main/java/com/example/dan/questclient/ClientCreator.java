package com.example.dan.questclient;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Dan on 18-Jun-17.
 */

public class ClientCreator extends AsyncTask<String, Void, String> {
    public Event event;
    public int request = 0;
    public ClientCreator(Event event){
        this.event = event;
        this.request = 0;

    }
    public int getrequest(){
        return this.request;
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
        //System.out.println(event.getPass(1));

        try {
            InetAddress ip = InetAddress.getByName("10.0.2.2");
            int port = 7878;

            Socket socket = new Socket(ip, port);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);
            // while (true) {
//                System.out.println("Type your msg: ");
//                String msg = sc.nextLine();

//                String msg = "aa" + event.getPass(1);
//                out.writeUTF(msg);
//                out.flush();
            //test t = new test();
//            Vector<String> strings = new Vector<String>();
//            strings.add("pas1");
//            strings.add("pas2");
//
//            Event event = new Event("nume",strings, strings,20,2);
            oos.writeObject(this.event);


            while (true){
                String msg2 = in.readUTF();
                if (msg2.equals("succes")){
                    System.out.println(event.getName() + " a este inregitrat");
                    String s = sc.nextLine();
                    out.writeUTF(s);//exit
                    this.request = 1;

                }
                if (msg2.equals("eroare")){
                    System.out.println(event.getName() + " este deja ocupat");
                    this.request = -1;
                }


            }

            // }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
