// @file: Parser.java
// @description: implements a parser to read and operate on lines of an input file
// @author: Adam Barrow
// @date: September 23, 2024


import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.Double.parseDouble;

public class Parser {

    //Create a BST tree of Integer type
    private BST<NBAPlayer> mybst = new BST<NBAPlayer>();

    public Parser(String filename) throws IOException {
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws IOException {

        FileInputStream in = new FileInputStream(input);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");

            try {
                // takes each part of the line and uses it as parameters for NBAPlayer constructor
                if (values.length == 17){
                    int playerID = Integer.parseInt(values[0].trim());
                    int gp = Integer.parseInt(values[1].trim());
                    int gs = Integer.parseInt(values[2].trim());
                    double mpg = parseDouble(values[3].trim());
                    double ppg = parseDouble(values[4].trim());
                    int fgm = Integer.parseInt(values[5].trim());
                    int fga = Integer.parseInt(values[6].trim());
                    double fgp = parseDouble(values[7].trim());
                    int threesMade = Integer.parseInt(values[8].trim());
                    int threesAttempted = Integer.parseInt(values[9].trim());
                    double threePercentage = parseDouble(values[10].trim());
                    int ftm = Integer.parseInt(values[11].trim());
                    int fta = Integer.parseInt(values[12].trim());
                    double ftp = parseDouble(values[13].trim());
                    String player = values[14].trim();
                    String position = values[15].trim();
                    String team = values[16].trim();

                    NBAPlayer newPlayer = new NBAPlayer(playerID, gp, gs, mpg,ppg,fgm, fga, fgp, threesMade,
                            threesAttempted, threePercentage, ftm, fta, ftp, player, position, team);

                    operate_BST(newPlayer);

            }

        }
            catch (NumberFormatException e) {
                writeToFile("Skipping row with invalid data: " + line, "result.txt");
            }
        }
        writeToFile(mybst.print(), "result.txt");
    }


    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(NBAPlayer input) {
        try {
            mybst.insert(input);
        }
        catch (Exception e) {
            writeToFile("Error: ", "./result.txt");
        }
    }

    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(content);
            writer.newLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}