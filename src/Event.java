import java.util.Vector;

/**
 * Created by Dan on 11-Jun-17.
 */
public class Event {
    public String name;
    public int time;
    public int nr;
    private static Vector<String> passes ;


    Event(String name, Vector<String> passes, int time, int nr){
        this.name = name;
        this.time = time;
        this.nr = nr;
        this.passes = passes;

    }
    Event(){
        this.name = null;
        this.time = 0;
        this.nr = 0;
        this.passes = new Vector<String>();

    }
    public void setName(String name){
        this.name = name;
    }
    public void setTime(int time){
        this.time = time;
    }
    public void setPasses(Vector<String> passes){
        this.passes = passes;
        this.nr = this.passes.size();

    }
    public void setPass(String pass){
        this.passes.addElement(pass);
        this.nr = this.passes.size();
    }


    public void showEvent(){
        System.out.println("------------");
        System.out.println("Nume : " + this.name);
        System.out.println("Time : " + this.time);
        System.out.println("Nr : " + this.nr);
        System.out.println("Passes : ");
        for (Object e : this.passes) {
            System.out.println(e);
            
        }

    }
    public String getPass( int i){
        return this.passes.get(i);

    }





}
