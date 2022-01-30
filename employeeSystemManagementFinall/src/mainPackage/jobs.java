/**
 *
 * @author Hadi
 */
package mainPackage;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class jobs {
    //	In the “jobs” class, we have these properties:
    //jobTitle, salary, Tax
    //////properties

    private String jobTitle;
    private Integer salary;
    private Integer tax;

    //////constuct function
    public jobs() {
    }

    //constractor for delete function
    public jobs(String jobName) {
        this.jobTitle = jobName;
    }

    public jobs(String title, Integer salary, Integer tax) {
        this.jobTitle = title;
        this.salary = salary;
        this.tax = tax;
    }

    //////Get
    public String getJobName() {
        return this.jobTitle;
    }

    public Integer getSalery() {
        return this.salary;
    }

    public Integer getTax() {
        return this.tax;
    }

    /////functions
    public static List<jobs> getJobList() {
        List<jobs> job = new ArrayList();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("job.xml");

            NodeList list = d.getElementsByTagName("job");
            for (int i = 0; i < list.getLength(); i++) {
                Node n = list.item(i);
                Element e = (Element) n;
                String jobname = e.getElementsByTagName("jobTitle").item(0).getTextContent();
                Integer salary = Integer.parseInt(e.getElementsByTagName("salary").item(0).getTextContent());
                Integer tax = Integer.parseInt(e.getElementsByTagName("tax").item(0).getTextContent());
                jobs jobs = new jobs(jobname, salary, tax);
                job.add(jobs);
            }
        } catch (Exception e) {
        }
        return job;
    }

    /*
* this function must delete element from xml
     */
    public void deleteFromXML(String job) {
        // <person>
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("job.xml");

            NodeList nodes = d.getElementsByTagName("job");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element person = (Element) nodes.item(i);
                // <name>
                Element name = (Element) person.getElementsByTagName("jobTitle").item(0);
                String pName = name.getTextContent();
//                System.out.println(pName.equals(job));
                if (pName.equals(job)) {
                    person.getParentNode().removeChild(person);
                }
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

//            StreamResult result = new StreamResult(new StringWriter());
            StreamResult result = new StreamResult("job.xml");

            DOMSource source = new DOMSource(d);
            transformer.transform(source, result);
        } catch (Exception e) {
        }
    }

    ////this function will send data which we will get from textField(input that is related to add new job) to a XML file and store them.
    public void saveToXML() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("job.xml");
            Element root = d.getDocumentElement();
            Element job = d.createElement("job");
            root.appendChild(job);
            Element property1 = d.createElement("jobTitle");
            Element property2 = d.createElement("salary");
            Element property3 = d.createElement("tax");

            property1.appendChild(d.createTextNode(this.jobTitle));
            property2.appendChild(d.createTextNode(this.salary.toString()));
            property3.appendChild(d.createTextNode(this.tax.toString()));

            job.appendChild(property1);
            job.appendChild(property2);
            job.appendChild(property3);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult("job.xml");
            t.transform(source, result);

        } catch (Exception e) {
            System.out.println("Can't access the file");
        }
    }
    ///we can list our jobs by this function in jobs class.

    public static ArrayList<jobs> jobsList() {

        ArrayList jobsList = new ArrayList();

        return jobsList;
    }

}
