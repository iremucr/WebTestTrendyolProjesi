package BaseFiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

public class JsonWebTypeReader {

    public static String getWebTypeByKey(String fileName,String keyName) {
            try {
                // JSON dosyasının yolu
                File jsonFile = new File("src/test/java/jsonFiles/"
                        +fileName+".json");

                // Jackson ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(jsonFile);

                // testData dizisine ulaş
                JsonNode testDataArray = root.get("testData");

                if (testDataArray != null && testDataArray.isArray()) {
                    for (JsonNode item : testDataArray) {
                        String key = item.get("key").asText();
                        if (key.equals(keyName)) {
                            return item.get("webType").asText();  // Eşleşen key için webType döner
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null; // Eşleşme yoksa null döner
    }

    public static String getWebValueByKey(String fileName,String keyName) {
        try {
            // JSON dosyasının yolu
            File jsonFile = new File("src/test/java/jsonFiles/"
                    +fileName+".json");

            // Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonFile);

            // testData dizisine ulaş
            JsonNode testDataArray = root.get("testData");

            if (testDataArray != null && testDataArray.isArray()) {
                for (JsonNode item : testDataArray) {
                    String key = item.get("key").asText();
                    if (key.equals(keyName)) {
                        return item.get("webValue").asText();  // Eşleşen key için webType döner
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Eşleşme yoksa null döner
    }

    private static String getWebTypeValue(String text){
        String value="";
        try {
            String[] list=text.split("@");
            value=getWebTypeByKey(list[0],list[1]);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
                return value;
    }
    private static String getWebValue(String text){
        String value="";
        try {
            String[] list=text.split("@");
            value=getWebValueByKey(list[0],list[1]);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static By getLocator(String text){
        By locator=null;
        try {
           String webType= getWebTypeValue(text);
           String webValue=getWebValue(text);
           if (webType.equalsIgnoreCase("id")){
               locator=By.id(webValue);
            }else if (webType.toLowerCase().equals("className")){
               locator=By.className(webValue);
           }else if (webType.toLowerCase().equals("tagName")){
               locator=By.tagName(webValue);
           }else if (webType.toLowerCase().equals("linkText")){
               locator=By.linkText(webValue);
           }else if (webType.toLowerCase().equals("partialLinkText")){
               locator=By.partialLinkText(webValue);
           }else if (webType.toLowerCase().equals("cssSelector")){
                locator=By.cssSelector(webValue);
            }else if (webType.toLowerCase().equals("xpath")){
                locator=By.xpath(webValue);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return locator;
    }





}
