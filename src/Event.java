import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Dan on 11-Jun-17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "event")
public class Event implements Serializable {

    private String name;
    private int time;
    private int nr;
    private int etapa;
    public Vector<String> passes ;


    Event(String name, Vector<String> passes, int time, int nr){
        this.name = name;
        this.time = time;
        this.nr = nr;
        this.etapa = 0;
        this.passes = passes;

    }
    Event(){
        this.name = null;
        this.time = 0;
        this.nr = 0;
        this.etapa = 0;
        this.passes = new Vector<String>();

    }
    public void setName(String name){
        this.name = name;
    }
    public void setTime(int time){
        this.time = time;
    }
    public void setEtapa(int etapa){
        this.etapa = etapa;
    }
    public void setPasses(Vector<String> passes){
        this.passes = passes;
        this.nr = this.passes.size();

    }
    public void setPass(String pass){
        this.passes.addElement(pass);
        this.nr = this.passes.size();
    }
    public void setNr(int nr){
        this.nr = nr;
    }

    public void showEvent(){
        System.out.println("------------");
        System.out.println("Nume : " + this.name);
        System.out.println("Time : " + this.time);
        System.out.println("Etapa : " + this.etapa);
        System.out.println("Nr : " + this.nr);
        System.out.println("Passes : ");
        for (Object e : this.passes) {
            System.out.println(e);
            
        }

    }

    public String getPass( int i){
        return this.passes.get(i-1);

    }
    public String getName(){

        return this.name;
    }
    public int getEtapa(){

        return this.etapa;
    }
    public int getNr(){
        return this.nr;
    }
    public int getTime(){
        return this.time;
    }





}
