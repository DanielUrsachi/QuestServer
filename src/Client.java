import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Dan on 11-Jun-17.
 */
public class Client {
    public static void main( String[] args )
    {

        try {
            InetAddress ip = InetAddress.getByName("localhost");
            int port = 7879;

            Socket socket = new Socket(ip, port);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);

                Vector<String> v  = (Vector<String>) ois.readObject();
            for (String vv:v) {
                System.out.println(" - " + vv);
            }

            System.out.println("Type your room : ");
            String msg = sc.nextLine();
            out.writeUTF(msg);
            out.flush();
            Event event = (Event) ois.readObject();
            event.showEvent();

            while (true) {
//                System.out.println("Type your msg: ");
//                String msg = sc.nextLine();
//                out.writeUTF(msg);
//                out.flush();


               // Thread.sleep(10000);

                String msg2 = in.readUTF();
                System.out.println("return from server: " + msg2 );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
