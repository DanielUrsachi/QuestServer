import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import com.example.dan.questclient.Event;

/**
 * Created by Dan on 13-Jun-17.
 */
public class ServerGroups {
    // public Vector<String> groups = new Vector<String>();;
    // public static EventList eventList = new EventList();;



    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {

        // Vector<String> groups = new Vector<>();

        //EventList eventList = new EventList();





        String nume1 = "nume1";
        String nume2 = "nume2";
        try {
            int port = 7879;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("TCP server running on port " + port);

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
                                System.out.println("object!");
                                String arr[] = msg.split(" ", 2);
                                String o = arr[1];
                                System.out.println(o);
                                Event event = eventList.getElementListName(o);
                                oos.writeObject(event);
                            }





//                            String msg = in.readUTF();
//                            System.out.println(" Room -->" + msg);
//                            Event event = eventList.getElementListName(msg);
//                            oos.writeObject(event);

                            /*if (msg.equals("nume1")){
                                System.out.println("primuuuuuu");
                                Thread.sleep(10000);
                                out.writeUTF("primu!" );

                            }
                            if (msg.equals("nume2")){
                                System.out.println("doileeaaaa");
                                Thread.sleep(1000);
                                out.writeUTF("doilea!" );

                            }*/



                            //out.writeUTF("citit dvajdi!:" + msg);



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