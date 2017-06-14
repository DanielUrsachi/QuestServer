import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Dan on 11-Jun-17.
 */
public class ClientCreator implements Serializable {

    public static void main( String[] args ){
        Event event = new Event();

        Scanner sc = new Scanner(System.in);
        System.out.println("Nume");
        event.setName(sc.nextLine());
        System.out.println("Time");
        event.setTime(Integer.parseInt(sc.nextLine()));
        System.out.println("Nr repetari");
        int nr = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < nr; i++){
            System.out.println("Pass etapa " + (i+1) + " : " );
            event.setPass(sc.nextLine());
        }



        event.showEvent();
        sendCreator(event);





    }
    public static void sendCreator(Event event){
        System.out.println(event.getPass(1));

        try {
            InetAddress ip = InetAddress.getByName("localhost");
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

            oos.writeObject(event);

                while (true){
                    String msg2 = in.readUTF();
                    if (msg2.equals("succes")){
                        System.out.println(event.getName() + " a este inregitrat");
                    }
                    if (msg2.equals("eroare")){
                        System.out.println(event.getName() + " este deja ocupat");
                    }


                }

           // }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
