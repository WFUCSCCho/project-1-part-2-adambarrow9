// @file: Proj1.java
// @description: main class to take in input and parse it
// @author: Adam Barrow
// @date: September 23 , 2024


import java.io.FileNotFoundException;
import java.io.IOException;

public class Proj1 {

    // main class  to take input
    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        new Parser(args[0]);
    }
}

//"/Users/adambarrow/IdeaProjects/project-1-part-2-adambarrow9/src/scoring.csv"