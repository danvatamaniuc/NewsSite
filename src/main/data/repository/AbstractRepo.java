package main.data.repository;

import main.data.domain.Validator;
import main.utils.Constants;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 1 on 10/20/2015.
 */
public abstract class AbstractRepo<E> implements CRUDRepository<E> {

    protected int lastId;
    private List<E> entities = new ArrayList<E>();
    private Validator<E> validator;
    private String storageFile;

    public AbstractRepo() {
        this.lastId = 0;

        storageFile = getNameForGenericE() + "Storage.xml";
//        storageFile = "resources/files/test.xml";
    }

    public abstract void setEntityId(E e) ;

    public void save(E e){
        validator.validate(e);

        lastId += 1;
        setEntityId(e);

        entities.add(e);
    }

    public Iterable<E> getAll(){
        return entities;
    }

    public void saveAllToXml() throws FileNotFoundException{

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

            // create XMLEventWriter
            final FileOutputStream outputStream = new FileOutputStream(storageFile);
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(outputStream);

            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            //newline
            XMLEvent end = eventFactory.createDTD("\n");
            //tab
            XMLEvent tab = eventFactory.createDTD("\t");

            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);

            // create file open tag
            StartElement configStartElement = eventFactory.createStartElement("",
                    "", Constants.OBJECT_TAG);
            eventWriter.add(configStartElement);
            eventWriter.add(end);

            //get class name
            final String className = getNameForGenericE();

            //iterator for the entities in the repository
            final Iterator<E> it = entities.iterator();

            //avoid reflecting every single object in the entities arraylist
            //and just get the fields and methods of the first item
            //since all the rest got the same fields and methods

            //also, bit of fun, in case the entities array is empty
            //the fields and methods variables won't be set
            //BUT! it's not a problem
            //since the execution will not enter the while that uses them
            //so it's ok
            //i hope

            Field[] fields = null;
            Method[] methods = null;

            if (!entities.isEmpty()){
                E item = entities.get(0);

                //get the fields and methods of the object E
                fields = item.getClass().getDeclaredFields();
                methods = item.getClass().getMethods();
            }

            //insert each element in entities into the xml file
            while(it.hasNext()) {
                //get the next item
                E item = it.next();

                eventWriter.add(tab);
                eventWriter.add(eventFactory.createStartElement("", "", className));
                eventWriter.add(end);

                //for each field
                //get the value of the field using get methods
                //and insert them into the file
                for (Field field : fields) {

                    String fieldName = field.getName();
                    String fieldValue = null; //to be updated with the value of the field

                    for (Method method : methods) {

                        //find the getter of the current field
                        if (method.getName().startsWith("get") &&
                                method.getName().toLowerCase().contains(field.getName().toLowerCase())) {

                            method.setAccessible(true);

                            //in case we find the value, store it and write into file
                            Object o = method.invoke(item);
                            if (o != null) {
                                fieldValue = o.toString();

                                createNode(eventWriter, fieldName, fieldValue);

                                break;
                            }

                        }
                    }
                }

                eventWriter.add(tab);
                eventWriter.add(eventFactory.createEndElement("", "", className));
                eventWriter.add(end);

            }

            eventWriter.add(eventFactory.createEndElement("", "", Constants.OBJECT_TAG));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());

            eventWriter.flush();
            eventWriter.close();
            outputStream.close();


        } catch (Exception e){
            e.printStackTrace();
        }


    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");

        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);

        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);

        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

    public void setValidator(Validator<E> validator) {
        this.validator = validator;
    }

//    private methods responsible for finding out the class name of the object in use
//    used heavily in xml I/Os

//    gets the type of the generic class
    private Type getTypeForGenericE() {
        Type classType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return classType;
    }

//    gets the name of the type of the generic class
    private String getNameForGenericE() {
        String className = getTypeForGenericE().getTypeName();
        return className.toLowerCase();
    }

}
