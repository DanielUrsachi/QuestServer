import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Dan on 11-Jun-17.
 */
public class CliendCreator {


    public static void main( String[] args ){
        Event event = new Event();
        Vector <String> passes = new Vector<String>();

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


    }
}
