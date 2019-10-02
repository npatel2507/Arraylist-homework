package Noppcommerce;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Test;
public class LoadProps {

    static Properties props;

        static FileInputStream inputStream;

        public String getProperty(String key) {
            props = new Properties();
            try{
                inputStream =new FileInputStream("src\\main\\Resources\\testdataconfig.Properties");
                props.load(inputStream);}

            catch (FileNotFoundException e) {
                e.printStackTrace(); }
            catch (IOException e) {
                e.printStackTrace();
            }
            return props.getProperty(key);
        }
        @Test
        public void openBrowser (){

            System.out.println(getProperty("url")); }
    }

