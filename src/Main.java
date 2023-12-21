/*
You play several games and record the information from each game (your puzzle input). Each game is listed with its ID number (like the 11 in Game 11: ...) followed by a semicolon-separated list of subsets of cubes that were revealed from the bag (like 3 red, 5 green, 4 blue).

For example, the record of a few games might look like this:

Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
In game 1, three sets of cubes are revealed from the bag (and then put back again). The first set is 3 blue cubes and 4 red cubes; the second set is 1 red cube, 2 green cubes, and 6 blue cubes; the third set is only 2 green cubes.

The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?

In the example above, games 1, 2, and 5 would have been possible if the bag had been loaded with that configuration. However, game 3 would have been impossible because at one point the Elf showed you 20 red cubes at once; similarly, game 4 would also have been impossible because the Elf showed you 15 blue cubes at once. If you add up the IDs of the games that would have been possible, you get 8.

Determine which games would have been possible if the bag had been loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes. What is the sum of the IDs of those games?
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//12 red 13 green 14 blue
public class Main {
    public static void main(String[] args) {

        File file = new File("input.txt");
        Pattern digitPattern = Pattern.compile("\\d+");
        Pattern colourWithNumber = Pattern.compile("(\\d+) (\\w+)");
        Map<String, Integer> colours = new HashMap<>();
        colours.put("red",0);
        colours.put("green",0);
        colours.put("blue",0);
        int isBigger = 0;
        int sum = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher digitPatternMatcher = digitPattern.matcher(line);
                Matcher colourWithNumberMatcher = colourWithNumber.matcher(line);
                StringBuffer gameNumberBuffer = new StringBuffer();

                while(digitPatternMatcher.find()){
                    gameNumberBuffer.append(digitPatternMatcher.group());
                    break;
                }

                while(colourWithNumberMatcher.find()){
                    String quantity = colourWithNumberMatcher.group(1);
                    String colour = colourWithNumberMatcher.group(2);
                    if(colours.containsKey(colour)){
                        colours.put(colour,Integer.parseInt(quantity));
                    }

                    int endOfMatch = colourWithNumberMatcher.end();

                    if(endOfMatch + 1 > line.length()){
                        if(colours.get("red") > 12 || colours.get("green") > 13 || colours.get("blue") > 14) {
                            isBigger = -1;
                            break;
                        }
                        colours.forEach((key,value) ->{
                            colours.put(key, 0);
                        });
                    }
                    else if(line.charAt(endOfMatch) == ';'){
                        if(colours.get("red") > 12 || colours.get("green") > 13 || colours.get("blue") > 14) {
                            isBigger = -1;
                            break;
                        }
                        colours.forEach((key,value) ->{
                            colours.put(key, 0);
                        });
                    }
                }

                if(isBigger != -1){
                    sum += Integer.parseInt(gameNumberBuffer.toString());
                }

                gameNumberBuffer.delete(0,gameNumberBuffer.length());
                colours.forEach((key,value) ->{
                    colours.put(key, 0);
                });
                isBigger = 0;
            }

            System.out.println("Sum: " + sum);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File cannot be read");
            e.printStackTrace();
        }
    }
}
