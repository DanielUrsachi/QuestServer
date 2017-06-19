import com.example.dan.questclient.test;
import com.example.dan.questclient.Event;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


import javax.xml.bind.JAXBException;

/**
 * Created by Dan on 19-Jun-17.
 */

public class ss2 {


        //public EventList eventList;


        public static void main(String[] args) {


            //EventList eventList = new EventList();

            //Saving.cleanEventList();



            try {
                int port = 7878;
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("TCP server running on port " + port);



                while (true) {
                    Socket socket = serverSocket.accept();
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                    Thread t = new Thread(()->{
                        //Object msg = ois.readObject();
                        try {
                            ;
                            // eventList = Saving.loadEventList();


                            //test tt = (test) ois.readObject();
                            Event event = (Event) ois.readObject();
                            System.out.println(event.getName());




                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

//                    while (true) {
//                        try {
//                            String msg = in.readUTF();
//                            System.out.println("MSG -->" + msg + "<--");
//                            out.writeUTF("citit:" + msg);
//                            Thread.sleep(10000);
//                            out.writeUTF("citit dvajdi!:" + msg);
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    });
                    t.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
