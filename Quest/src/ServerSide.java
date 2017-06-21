import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;
import com.example.dan.questclient.Event;

/**
 * Created by Dan on 11-Jun-17.
 */
public class ServerSide extends Server{
    public static void runServerSide() {

        try {
            int port = Server.portSS;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("ServerSide for registering on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Thread t = new Thread(()->{
                    while (true) {
                        try {
                            EventList eventList = new EventList();
                            eventList = Saving.loadEventList();
                            Event event;
                            Vector<String> groups = new Vector<>();

                            String msg2 = in.readUTF();
                            if (msg2.equals("add")) {
                                event = (Event) ois.readObject();
                                groups = eventList.getNames();
                                if (!groups.contains(event.getName())) {
                                    groups.add(event.getName());
                                    eventList.addEventListItem(event);
                                    System.out.println("ServerSide: " + event.getName() + " a fost inregistrat cu succes");
                                    out.writeUTF("succes");
                                    Saving.saveEventList(eventList);
                                } else {
                                    System.out.println("ServerSide: " + event.getName() + " este deja ocupat");
                                    out.writeUTF("eroare");
                                }
                            }
                            if (msg2.equals("end")) {
                                break;
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (JAXBException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}