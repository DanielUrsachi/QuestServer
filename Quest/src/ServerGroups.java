import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import com.example.dan.questclient.Event;

/**
 * Created by Dan on 13-Jun-17.
 */
public class ServerGroups extends Server  {

    public static void runServerGroups() {

        try {
            int port = Server.portSG;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("ServerGroups for extracting information on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());


                Thread t = new Thread(()->{
                    while (true) {
                        try {
                            EventList eventList = Saving.loadEventList();
                            Vector<String> v = eventList.getNames();


                            String msg = in.readUTF();
                            if(msg.equals("list")){
                                oos.writeObject(v);
                                oos.flush();
                            }
                            if(msg.equals("end")){
                                break;
                            }
                            if(msg.startsWith("object")){
                                System.out.println("ServerGroups: object!");
                                String arr[] = msg.split(" ", 2);
                                String o = arr[1];
                                System.out.println("ServerGroups: " + o);
                                Event event = eventList.getElementListName(o);
                                oos.writeObject(event);
                            }
                        } catch (IOException e) {
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