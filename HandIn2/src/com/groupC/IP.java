package com.groupC;

import java.io.IOException;

public class IP {
 public static void main(String[] args) throws IOException {
     if (args.length > 0) {

         // Parsing of fileinput - args[0]
         String[] input = FileReader.ReadFile(args[0]);
        // GaleShapley gs = Transformer.Transformation(input);

         //gs.StableMatcher();

         System.out.println("\n");
         System.out.println("Interval partitioning complete!");
     }
     else {
         System.out.println("The program needs a filepath as input \n");
         System.out.println("Terminating ...");
     }

 }
}
