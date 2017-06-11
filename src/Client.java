import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

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

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Type your msg: ");
                String msg = sc.nextLine();
                out.writeUTF(msg);
                out.flush();

                String msg2 = in.readUTF();
                System.out.println("qqqq" + msg2 + "qqq");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
