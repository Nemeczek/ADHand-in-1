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
                   if (j%2==0)
                       mNames[j/2-1] = new String(fileLines[i].substring((int) (Math.log10(j) + 1 + 1))); //size of n+ space
                   else
                       wNames[(j-1)/2] = new String(fileLines[i].substring((int) (Math.log10(j) + 1 + 1))); //size of n+ space
                   j++;
               }
           }

           //reading men's prefs
           Integer[][] mprefs = new Integer[n][n];
           j = 1;
           for (int i=0 ; i<=fileLines.length && j<=2*n ; i++) {
               if (fileLines[i].startsWith(Integer.toString(j) + ": ")) {
                   String [] tempStr = fileLines[i].substring(fileLines[i].lastIndexOf(":")+2).split(" ");
                   for (int k=0 ; k<n ; k++) {
                       mprefs[(j - 1)/2][k]=Integer.parseInt(tempStr[k])/2-1; //size of n+ space
                   }
                   j=j+2;
               }
           }

           //reading women's prefs
           Integer[][] wprefs = new Integer[n][n];
           j = 2;
           for (int i=0 ; i<=fileLines.length && j<=2*n ; i++) {
               if (fileLines[i].startsWith(Integer.toString(j) + ": ")) {
                   String [] tempStr = fileLines[i].substring(fileLines[i].lastIndexOf(":")+2).split(" ");
                   for (int k=0 ; k<n ; k++) {
                       wprefs[j/2-1][k]=(Integer.parseInt(tempStr[k])-1)/2; //size of n+ space
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

               while (propose[guy]<n){
                   if (wMatches[propose[guy]]==-1) {
                       wMatches[propose[guy]] = guy;
                       mMatches[guy] = propose[guy];
                       unmatched.pop();
                       break;
                   }else if(invertedWprefs[propose[guy]][guy] < invertedWprefs[propose[guy]][wMatches[propose[guy]]]) {
                       unmatched.pop();
                       unmatched.push(wMatches[propose[guy]]);
                       wMatches[propose[guy]] = guy;
                       mMatches[guy] = propose[guy];
                       break;
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
