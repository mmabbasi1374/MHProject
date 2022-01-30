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

/**
 *
 * @author Hadi
 */
public class tax {

    private Integer id;
    private String taxName;
    private Integer amount;

    public tax() {
    }

    public tax(String taxName) {
        this.taxName = taxName;
    }

    public tax(Integer id, Integer amount, String taxName) {
        this.id = id;
        this.amount = amount;
        this.taxName = taxName;
    }

    public String getTaxName() {
        return this.taxName;
    }

    public Integer getTaxrate() {
        return this.amount;
    }

    public static List<tax> getTaxList() {
        List<tax> taxList = new ArrayList();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("tax.xml");

            NodeList list = d.getElementsByTagName("tax");
            for (int i = 0; i < list.getLength(); i++) {
                Node n = list.item(i);
                Element e = (Element) n;
                Integer id = Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent());
                Integer amount = Integer.parseInt(e.getElementsByTagName("amount").item(0).getTextContent());
                String taxName = e.getElementsByTagName("taxname").item(0).getTextContent();
                tax fulltax = new tax(id, amount, taxName);
                taxList.add(fulltax);
            }
        } catch (Exception e) {
        }
        return taxList;
    }

    public void deleteFromXML(String taxName) {
        // <person>
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("tax.xml");
            NodeList nodes = d.getElementsByTagName("tax");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element tax = (Element) nodes.item(i);
                Element name = (Element) tax.getElementsByTagName("taxname").item(0);
                String tName = name.getTextContent();
                if (tName.equals(taxName)) {
                    tax.getParentNode().removeChild(tax);
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StreamResult result = new StreamResult("tax.xml");
            DOMSource source = new DOMSource(d);
            transformer.transform(source, result);
        } catch (Exception e) {
        }
    }

    public void saveToXML() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("tax.xml");

            Element root = d.getDocumentElement();
            Element job = d.createElement("tax");
            root.appendChild(job);

            Element property1 = d.createElement("id");
            Element property2 = d.createElement("amount");
            Element property3 = d.createElement("taxname");

            property1.appendChild(d.createTextNode(this.id.toString()));
            property2.appendChild(d.createTextNode(this.amount.toString()));
            property3.appendChild(d.createTextNode(this.taxName.toString()));

            job.appendChild(property1);
            job.appendChild(property2);
            job.appendChild(property3);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult("tax.xml");
            t.transform(source, result);

        } catch (Exception e) {
            System.out.println("Can't access the file");
        }
    }

}
