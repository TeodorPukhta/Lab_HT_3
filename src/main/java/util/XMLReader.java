package util;


import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.logging.Logger;


public class XMLReader {
    final static Logger logger = Logger.getLogger(PropertiesReader.class.getName());
    private String product;
    private String brand;
    private int price;

    public XMLReader() {
        File file;
        try{
            file = new File("src/main/resources/data.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document  = documentBuilder.parse(file);
            product = document.getElementsByTagName("product").item(0).getTextContent();
            brand = document.getElementsByTagName("brand").item(0).getTextContent();
            price = Integer.parseInt(document.getElementsByTagName("price").item(0).getTextContent());
        }catch (Exception e){
            logger.info("data file is missing");
            System.out.println(e.getMessage());
        }
    }

    public String getProduct() {
        return product;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }
}
