package uk.co.spiraltechnology.client;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.HttpURLConnection;
import java.net.URL;

public class XmlClient {

    public static void main(String[] args) throws Exception {

        URL u = new URL("http://localhost:8080/jersey/rest/helloworld/");
        HttpURLConnection uc = (HttpURLConnection) u.openConnection();

        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = docBuilder.parse(uc.getInputStream());

        String nodeValue = doc.getDocumentElement().getFirstChild().getFirstChild().getNodeValue();
        System.out.println(nodeValue);
    }
}