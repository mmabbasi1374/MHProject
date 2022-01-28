/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainPackage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hadi
 */
public class login {

    private String username;
    private String password;

    public login() {
    }

    public login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Boolean isLogin() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse("login.xml");
            NodeList list = d.getElementsByTagName("user");

            for (int i = 0; i < list.getLength(); i++) {
                Node n = list.item(i);
                Element e = (Element) n;
                String uName = e.getElementsByTagName("username").item(0).getTextContent();
                String pass = e.getElementsByTagName("password").item(0).getTextContent();
                if (uName.equals(this.username) && pass.equals(this.password)) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
