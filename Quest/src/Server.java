/**
 * Created by Dan on 20-Jun-17.
 */

public class Server {
    public static int portSS = 7878;
    public static int portSG = 7879;
    public static void main( String[] args ){
        Thread t = new Thread(() -> {
            ServerSide.runServerSide();

        });
        Thread t2 = new Thread(() -> {
            ServerGroups.runServerGroups();

        });

        t.start();
        t2.start();
    }

}
