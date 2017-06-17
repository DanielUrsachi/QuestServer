import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Vector;


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

    public  static void main(String[] args) throws Exception {
        File file = new File("D://file.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(EventList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        EventList eventList = new EventList();

        Vector<String> strings = new Vector<String>();
        strings.add("pas1");
        strings.add("pas2");

        Event event1 = new Event("nume",strings, strings,20,2);
        eventList.addEventListItem(event1);

        ///
        Vector<String> strings2 = new Vector<String>();
        strings2.add("pas100");
        strings2.add("pasword");

        Event event2 = new Event("nume00",strings2, strings2,2000,2);
        eventList.addEventListItem(event2);




        ///eventList.showEventList();

        //marshal
        //setEventList(eventList.getEventListItem(0));
        saveEventList(eventList);

        //unmarshal
        EventList eventList2 = new EventList();
       eventList2 = loadEventList();

        /*Event books = new Event();

        JAXBContext context = JAXBContext.newInstance(EventList.class);
        Unmarshaller um = context.createUnmarshaller();
        books = (Event) um.unmarshal(file);
        books.showEvent();
*/

         //books.getBooks();


//        Unmarshaller um = jaxbContext.createUnmarshaller();
//        eventList2 = (EventList) um.unmarshal(file);
        eventList2.showEventList();



        ///eventList.getEventListItem(1).showEvent();

        ///
        /*

        jaxbMarshaller.marshal(eventList,file);


        //unmarshaling
        EventList eventList2 = new EventList();
        JAXBContext context = JAXBContext.newInstance(EventList.class);
        Unmarshaller um = context.createUnmarshaller();
        eventList2 = (EventList) um.unmarshal(file);
        jaxbMarshaller.marshal(eventList2,file);

*/



    }

}
