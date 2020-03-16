package utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileUtils {
    public static String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                content.append(s);
            }
        } catch (Exception e) {
            System.out.println("readFromFile error fileName = [" + filePath + "]" + e.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
