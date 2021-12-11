package util;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesReader {
    Properties properties = new Properties();
    final static Logger logger = Logger.getLogger(PropertiesReader.class.getName());
    public PropertiesReader() {
        FileInputStream file;
        try{
            file = new FileInputStream("src/main/resources/config.properties");
            properties.load(file);
            file.close();
        }catch (Exception e){
            logger.info("Properties file is missing");
            System.out.println(e.getMessage());
        }
    }
    public String getURL(){
        return properties.getProperty("URL");
    }
}
