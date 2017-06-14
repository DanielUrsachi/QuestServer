import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Dan on 14-Jun-17.
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eventList")
public class EventList implements Serializable {
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
    }
    public Event getEventListItem(int i){
        return this.eventList.get(i);
    }


}
