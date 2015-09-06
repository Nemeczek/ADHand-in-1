package com.company;

import java.io.IOException;

/**
 * Created by GROUP C on 02-09-2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {

            // Parsing of fileinput - args[0]
            String[] input = FileParser.ReadFile(args[0]);
            GaleShapley gs = Transformer.Transformation(input);

            gs.StableMatcher();

            System.out.println("\n");
            System.out.println("Stable Matching complete!");
        }
        else {
            System.out.println("The program needs a filepath as input \n");
            System.out.println("Terminating ...");
        }
    }
}
