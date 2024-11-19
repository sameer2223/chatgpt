import java.io.*;
import java.util.*;

public class SplitIT1ItemsToCollection {

    public static void main(String[] args) {
        // Path to the uploaded file
        String filePath = "sample.txt";
        
        // List to store collections of items
        List<Map<String, String>> itemList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map<String, String> currentItem = null;

            while ((line = br.readLine()) != null) {
                // Check if the line starts with IT1 (new item group)
                if (line.startsWith("IT1")) {
                    // Save the current item to the list if it's not null
                    if (currentItem != null) {
                        itemList.add(currentItem);
                    }
                    // Create a new map for the new item
                    currentItem = new LinkedHashMap<>();
                    currentItem.put("IT1", line);
                } else if (currentItem != null) {
                    // Add other attributes to the current item
                    String[] parts = line.split("\\*");
                    if (parts.length > 1) {
                        currentItem.put(parts[0], parts[1]);
                    }
                }
            }
            // Add the last item to the list
            if (currentItem != null) {
                itemList.add(currentItem);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Print the items
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println("Item " + (i + 1) + ": " + itemList.get(i));
        }
    }
}
