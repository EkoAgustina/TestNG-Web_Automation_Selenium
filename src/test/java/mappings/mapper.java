package mappings;

import org.openqa.selenium.By;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class mapper {

    /**
     * Used to declare the path or locator of an element in a YAML file
     * @param element
     * @return Return the reading results from a YAML file according to the given element key
     */
    public static String key_element(String element){
        String path_element = null;
        if (element == null || element.isEmpty()){
            throw new RuntimeException("Element is required ..!");
        }
        else{
            path_element = "src/test/java/resources/selector/"+element.split(":")[0]+".yml"+":"+element.split(":")[1];
            return LoadYaml(path_element.split("\\:")[0],path_element.split("\\:")[1]);
        }
    }
    
    
    /**
     * Used to declare the path or data of an test data in a YAML file
     * @param data
     * @return Return the reading results from a YAML file according to the given test data key
     */
    public static String key_data(String data){
        String path_data = null;
        if (data == null || data.isEmpty()){
            throw new RuntimeException("Data is required ..!");
        }
        else{
            path_data = "src/test/java/resources/test_data/"+data.split(":")[0]+".yml"+":"+data.split(":")[1];
            return LoadYaml(path_data.split("\\:")[0],path_data.split("\\:")[1]);
        }
    }

    /**
     * Used to get a File object that represents the full path of a file or directory
     * @param path
     * @return Returns a File object representing a path
     */
    public static File file_path(String path){
        File base_path = new File(System.getProperty("user.dir"));
        File use_path = new File(base_path, path);
        return use_path;
    }
    
    /**
     * Used to convert a locator string into a Cost object that can be used by Selenium WebDriver
     * @param locator
     * @return Returns a By object that has been created according to the desired locator type
     */
    public static By locatorParser(String locator){
        By loc = null;
        if (locator.contains("By.id")) {
            loc = By.id(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
        }
        else if (locator.contains("By.name")){
            loc = By.name(locator.substring(locator.indexOf("\"") + 1,locator.length() - 2));
        }
        if (locator.contains("By.xpath")) {
            loc = By.xpath(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
        }
        return loc;
    }
    
    /**
     * Used to read configuration values from YAML files
     * @param selector
     * @param element
     * @return Returns configuration values from a YAML file
     */
    public static String LoadYaml(String selector, String element) {
        Map<String, String> conf = new HashMap<>(); 
        Yaml yaml = new Yaml();
        String config   = conf.toString();

        try {
            InputStream stream = new FileInputStream(selector);

            conf = yaml.load(stream);
            config = (String) conf.get(element);

            if (conf == null || conf.isEmpty() == true) {
                throw new RuntimeException("Failed to read config file");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No such file " + selector);
            throw new RuntimeException("No config file");
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new RuntimeException("Failed to read config file");
        }

        return config;
    }


}
