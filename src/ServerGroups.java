import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dan on 13-Jun-17.
 */
public class ServerGroups {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
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


                Thread t = new Thread(()->{
                    while (true) {
                        try {
                            String msg = in.readUTF();
                            System.out.println(" User -->" + msg + "<--");
                            if (msg.equals("nume1")){
                                System.out.println("primuuuuuu");
                                Thread.sleep(10000);
                                out.writeUTF("primu!" );

                            }
                            if (msg.equals("nume2")){
                                System.out.println("doileeaaaa");
                                Thread.sleep(1000);
                                out.writeUTF("doilea!" );

                            }



                            //out.writeUTF("citit dvajdi!:" + msg);



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
