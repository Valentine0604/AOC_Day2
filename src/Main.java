import java.io.*;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {

        File file = new File("temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {

            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File cannot be read");
            e.printStackTrace();
        }
    }
