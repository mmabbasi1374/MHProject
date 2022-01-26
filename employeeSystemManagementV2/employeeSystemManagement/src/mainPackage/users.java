package mainPackage;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class users {

    ///In the “users” class, we have these properties:
    ///fullName, age, email, ID
    private String fullName;
    private Integer age;
    private String email;
    private Integer ID;

    //////constuct function
    public users() {
    }

    //constractor for delete function
    public users(String email) {
        this.email = email;
    }

    public users(String name, Integer age, String email, Integer id) {
        this.fullName = name;
        this.age = age;
        this.email = email;
        this.ID = id;
    }

    //////Get
    public String getName() {
        return this.fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public Integer getage() {
        return this.age;
    }

    public Integer getId() {
        return this.ID;
    }

    /////functions
    public static List<users> getUserList() {
        List<users> user = new ArrayList();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("user.xml");

            NodeList list = d.getElementsByTagName("user");
            for (int i = 0; i < list.getLength(); i++) {
                Node n = list.item(i);
                Element e = (Element) n;
                String name = e.getElementsByTagName("name").item(0).getTextContent();
                String email = e.getElementsByTagName("email").item(0).getTextContent();
                Integer age = Integer.parseInt(e.getElementsByTagName("age").item(0).getTextContent());
                Integer id = Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent());
                users users = new users(name, age, email, id);
                user.add(users);
            }
        } catch (Exception e) {
        }
        return user;
    }

    /*
*delete user from XML
     */
    public void deleteFromXML(String user) {
        // <person>
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("user.xml");
            NodeList nodes = d.getElementsByTagName("user");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element person = (Element) nodes.item(i);
                Element name = (Element) person.getElementsByTagName("email").item(0);
                String pName = name.getTextContent();
                if (pName.equals(user)) {
                    person.getParentNode().removeChild(person);
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StreamResult result = new StreamResult("user.xml");
            DOMSource source = new DOMSource(d);
            transformer.transform(source, result);
        } catch (Exception e) {
        }
    }
//this function will send data which we will get from textField(input that is related to sign up(add new user)) to XML file and store them.

    public void saveToXML() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("user.xml");

            Element root = d.getDocumentElement();
            Element job = d.createElement("user");
            root.appendChild(job);

            Element property1 = d.createElement("id");
            Element property2 = d.createElement("name");
            Element property3 = d.createElement("email");
            Element property4 = d.createElement("age");

            property1.appendChild(d.createTextNode(this.ID.toString()));
            property2.appendChild(d.createTextNode(this.fullName));
            property3.appendChild(d.createTextNode(this.email));
            property4.appendChild(d.createTextNode(this.age.toString()));

            job.appendChild(property1);
            job.appendChild(property2);
            job.appendChild(property3);
            job.appendChild(property4);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult("user.xml");
            t.transform(source, result);

        } catch (Exception e) {
            System.out.println("Can't access the file");
        }
    }
///we can list our users by this function in users class.

    public static ArrayList<users> usersList() {

        ArrayList usersList = new ArrayList();

        return usersList;
    }
///this function will be used for delete a user in users class

    public void remove() {
    }
//we will check if the data which comes from “log in” is the same as  data’s we have already gotten from “sign up” or not 

    public void checkLog() {
    }
}
