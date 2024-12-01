import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonHandler {
    private static final String JSON_FILE_NAME = "Climate News File.json";   //create the final name for json storage

    public static List<Map<String, String>> loadFromJson() {   //method to load articles from the json file
        File file = new File(JSON_FILE_NAME);   // Create file object with the JSON file name
        if (file.exists()) {   // Check if the file exists
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {   //opens the json file for reading
                StringBuilder jsonContent = new StringBuilder();          // StringBuilder to store the JSON content
                String line;
                while ((line = reader.readLine()) != null) {    // Read the file line by line
                    jsonContent.append(line);
                }
                return parseJson(jsonContent.toString());   // Parse the JSON content and return the list of articles
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();   // Return an empty list if the file does not exist
    }

    public static void saveToJson(List<Map<String, String>> jsonArticles) {   //method to save the articles to json file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_NAME))) {  //opens the file to allow writing
            writer.write(toJsonString(jsonArticles));    //writes the string to the file
            System.out.println("JSON file saved at: " + new File(JSON_FILE_NAME).getAbsolutePath());  //shows the file path
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, String>> parseJson(String json) {   //method to change the strings in the json file to a list of articles
        List<Map<String, String>> result = new ArrayList<>();   //creates an arraylist to store the articles
        json = json.trim();
        if (json.startsWith("[") && json.endsWith("]")) {    //checks if there is an array in the json string
            json = json.substring(1, json.length() - 1);    //removes the square brackets
            String[] objects = json.split("\\},\\s*\\{");    //splits the string into individual objects
            for (String obj : objects) {
                if (!obj.startsWith("{")) obj = "{" + obj;
                if (!obj.endsWith("}")) obj = obj + "}";
                Map<String, String> map = new HashMap<>();  // creates a hashmap to store the attributes of the article
                String[] pairs = obj.substring(1, obj.length() - 1).split(",");  //creates key value pairs
                for (String pair : pairs) {
                    String[] keyValue = pair.split(":", 2);  // Split into max 2 parts
                    if (keyValue.length == 2) {    // Check if the pair contains both key and value
                        String key = keyValue[0].trim().replace("\"", "");
                        String value = keyValue[1].trim().replace("\"", "");
                        map.put(key, value);  // Put the key-value pair into the map
                    }
                }
                if (!map.isEmpty()) {  //if the map is not empty
                    result.add(map);  //adds the map to the list
                }
            }
        }
        return result;
    }

    private static String toJsonString(List<Map<String, String>> jsonArticles) {  //method to convert a list of articles to a json string
        StringBuilder json = new StringBuilder("[");  //string builder which stores the json string
        for (int i = 0; i < jsonArticles.size(); i++) {  //loops through each article
            Map<String, String> article = jsonArticles.get(i);
            json.append("{");
            for (Map.Entry<String, String> entry : article.entrySet()) {  // Loop through each key-value pair in the article
                json.append("\"").append(entry.getKey()).append("\":\"")
                        .append(entry.getValue().replace("\"", "\\\"")).append("\",");   // Escape quotes in the value
            }
            if (json.charAt(json.length() - 1) == ',') {
                json.setLength(json.length() - 1);
            }
            json.append("}");
            if (i < jsonArticles.size() - 1) {
                json.append(",");    // Add a comma between articles
            }
        }
        json.append("]");
        return json.toString();  // Return the JSON string
    }
}