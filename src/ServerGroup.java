import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dan on 11-Jun-17.
 */
public class ServerGroup {
    public static void Group(String nume){
        try {
            int port = 7879;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("TCP server running on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());


                Thread t = new Thread(()->{
                    while (true) {
                        try {
                            String msg = in.readUTF();
                            System.out.println(nume + " MSG -->" + msg + "<--");
                            out.writeUTF("citit:" + msg);
                            Thread.sleep(10000);
                            out.writeUTF("citit dvajdi!:" + msg);



                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
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
