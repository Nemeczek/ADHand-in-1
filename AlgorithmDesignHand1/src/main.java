/**
 * Created by Stelios on 24/8/2015.
 */
import java.util.*;
import java.io.*;
import java.lang.System;

public class main {

       private static String[] ReadFile(String path) throws IOException
       {
              File file = new File(path);
              FileInputStream fis = new FileInputStream(file);
              byte[] data = new byte[(int) file.length()];
              fis.read(data);
              fis.close();
              String fileString = new String(data, "UTF-8");
              return fileString.split("\n");
       }

       public static void main(String [] args) throws IOException
       {
           String[] fileLines = ReadFile(args[0]);
           int n = 0;
           for (int i=0 ; i < fileLines.length ; i++){
               if (fileLines[i].contains("n=")) {
                       n = Integer.parseInt(fileLines[i].substring(2));
                       break;
               }
           }
           if (n==0){
               System.out.println("Number n missing from config file");
           }
           //reading names for fun
           String [] mNames = new String[n];
           String [] wNames = new String[n];
           int j = 1;
           for (int i=0 ; i<=fileLines.length && j<=2*n ; i++) {
               if (fileLines[i].startsWith(Integer.toString(j) + " ")) {
                   if (j%2==0) {
                       wNames[j / 2 - 1] = new String(fileLines[i].substring((int) (Math.log10(j) + 1 + 1))); //size of n+ space
                       //System.out.println( j + " is " + wNames[j/2-1] + " girl");
                   }
                   else {
                       mNames[(j - 1) / 2] = new String(fileLines[i].substring((int) (Math.log10(j) + 1 + 1))); //size of n+ space
                       //System.out.println( j + " is " + mNames[(j - 1) / 2] + " guy");
                   }
                   j++;
               }
           }
           Integer[][] wprefs = new Integer[n][n];
           Integer[][] mprefs = new Integer[n][n];
           j = 1;
           for (int i=0 ; i<=fileLines.length && j<=2*n ; i++) {
               if (fileLines[i].startsWith(Integer.toString(j) + ": ")) {
                   String [] tempStr = fileLines[i].substring(fileLines[i].lastIndexOf(":")+2).split(" ");
                   //System.out.printf(wNames[(j - 1)/2] + " woman");
                   for (int k=0 ; k<n ; k++) {
                       wprefs[(j - 1)/2][k]=Integer.parseInt(tempStr[k])/2-1; //size of n+ space
                       //System.out.printf(" " + wprefs[(j - 1)/2][k]);
                   }
                   //System.out.println();
                   j=j+2;
               }
           }


           j = 2;
           for (int i=0 ; i<=fileLines.length && j<=2*n ; i++) {
               if (fileLines[i].startsWith(Integer.toString(j) + ": ")) {
                   String [] tempStr = fileLines[i].substring(fileLines[i].lastIndexOf(":")+2).split(" ");
                   for (int k=0 ; k<n ; k++) {
                       mprefs[j/2-1][k]=(Integer.parseInt(tempStr[k])-1)/2; //size of n+ space
                   }
                   j=j+2;
               }
           }

           //creating inverted wprefs
           Integer[][] invertedWprefs = new Integer[n][n];
           for (int i = 0 ; i < n ; i++) {
               for (int k = 0; k < n; k++) {
                   invertedWprefs[i][wprefs[i][k]]=k;
               }
           }

           //create unmatched man list
           Stack<Integer> unmatched = new Stack();
           for (int i=0;i<n;i++){
               unmatched.push(i);
           }
           //create table of proposed girls
           Integer[] propose = new Integer[n];
           //fill it with zeroes
           //it means the number of the girl in the guy's prefs
           for (int i=0;i<n;i++){
                   propose[i]=0;
           }
           //create table of men's matches
           Integer[] mMatches = new Integer[n];
           //fill it with -1
           for (int i=0;i<n;i++){
                   mMatches[i]=-1;
           }
           //create table of women's matches
           Integer[] wMatches = new Integer[n];
           //fill it with -1
           for (int i=0;i<n;i++){
               wMatches[i]=-1;
           }

           while (!unmatched.empty()) {
               int guy = unmatched.peek();
               if (guy<0) break;
               int nextGirlInMPrefs = propose[guy];
               while (nextGirlInMPrefs<n){
                   int womanHeWantsNow = mprefs[guy][nextGirlInMPrefs];
                   if (wMatches[womanHeWantsNow]==-1) {
                       wMatches[womanHeWantsNow] = guy;
                       mMatches[guy] = womanHeWantsNow;
                       wMatches[womanHeWantsNow] = guy;
                       unmatched.pop();
                       break;
                   }else if(invertedWprefs[guy][womanHeWantsNow] < invertedWprefs[wMatches[womanHeWantsNow]][womanHeWantsNow]) {
                       unmatched.pop();
                       unmatched.push(wMatches[womanHeWantsNow]);
                       wMatches[invertedWprefs[wMatches[womanHeWantsNow]][womanHeWantsNow]] = -1;
                       wMatches[womanHeWantsNow] = guy;
                       mMatches[guy] = womanHeWantsNow;
                       break;
                   }else{
                       
                   }
                   propose[guy]++;
               }

           }
           for (int i=0;i<n;i++){
               System.out.println( i + " -- " + mMatches[i]);
               //System.out.println(mNames[i] + " -- " + wNames[mMatches[i]]);
           }
       };
}
