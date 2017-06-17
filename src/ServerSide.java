import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Dan on 11-Jun-17.
 */
public class ServerSide {

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
                        EventList eventList = new EventList();
                        eventList = Saving.loadEventList();

                        Event event = (Event) ois.readObject();

                        Vector<String> groups = new Vector<>();
                        groups = eventList.getNames();

                        if(!groups.contains(event.getName())){
                            groups.add(event.getName());
                            eventList.addEventListItem(event);
                            System.out.println(event.getName() + " a fost inregistrat cu succes");
                            //System.out.println(event.getPass(1));
                            out.writeUTF("succes");
                            Saving.saveEventList(eventList);


                            String s = in.readUTF();
                            if(s.equals("delete")){
                                System.out.println("delete");
                                Vector<Event> v1 = eventList.getEventList();
                                v1.remove(event);
                                eventList.setEventList(v1);
                                Saving.saveEventList(eventList);
                            }


                            //ServerGroups.sendInfo(eventList);
                            //ServerGroup.Group(event.getName());

                        }
                        else {
                            System.out.println(event.getName() + " este deja ocupat");
                            out.writeUTF("eroare");
                        }



                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (JAXBException e) {
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
