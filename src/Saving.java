import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Vector;
import com.example.dan.questclient.Event;


/**
 * Created by Dan on 14-Jun-17.
 */

//https://stackoverflow.com/questions/33759441/delete-a-node-and-its-elements-from-an-xml-file-in-java
public class Saving {
    //unmarshal
    public static EventList loadEventList() throws JAXBException {
        File file = new File("D://file.xml");
        EventList eventList = new EventList();
        JAXBContext context = JAXBContext.newInstance(EventList.class);
        Unmarshaller um = context.createUnmarshaller();
        eventList = (EventList) um.unmarshal(file);
        return eventList;

    }
    //marshal
    public static void saveEventList(EventList eventList) throws JAXBException {
        File file = new File("D://file.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(EventList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(eventList,file);
    }
    public static void cleanEventList() throws JAXBException{
        File file = new File("D://file.xml");
        EventList eventList = null;
        JAXBContext jaxbContext = JAXBContext.newInstance(EventList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(eventList,file);

    }


}