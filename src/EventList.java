import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Observable;
import java.util.Vector;
import com.example.dan.questclient.Event;

/**
 * Created by Dan on 14-Jun-17.
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eventList")
public class EventList extends Observable implements Serializable  {
    @XmlElement(name = "event", type = Event.class)
    public Vector<Event> eventList = new Vector<Event>();

    EventList(){
        eventList = new Vector<Event>();
    }

    public void setEventList(Vector<Event> eventList){
        this.eventList = eventList;
    }

    public Vector<Event> getEventList(Vector<Event> eventList){
        return this.eventList;
    }
    public Vector<String> getNames(){
        Vector<String> v1 = new Vector<String>();
        for (Object object :  this.eventList) {
            v1.add(((Event) object).getName());
        }

        return v1;
    }
    public Vector<Event> getEventList(){
        return eventList;
    }

    public void showEventList(){

        for (Object object : this.eventList) {
            System.out.print(" \n");
            //printOut((Discipline) discipline);
            ((Event) object).showEvent();
        }

    }
    public void addEventListItem(Event event){
        (this.eventList).add(event);
        setChanged();
        notifyObservers();
    }
    public Event getEventListItem(int i){
        return this.eventList.get(i);
    }
    public int length(){
        return this.eventList.size();
    }
    public Event getElementListName(String s){
        for (Object object : this.eventList) {
            if(s.equals(((Event) object).getName())){
                return ((Event) object);
            }
        }
        return null;
    }


}