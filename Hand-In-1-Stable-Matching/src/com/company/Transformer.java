package com.company;

/**
 * Created by B006572 on 02-09-2015.
 */
public class Transformer {

    public static GaleShapley Transformation (String[] file) {

        String[] fileLines = file;

        int n = 0;
        for (int i = 0; i < fileLines.length; i++) {
            if (fileLines[i].contains("n=")) {
                n = Integer.parseInt(fileLines[i].substring(2));
                break;
            }
        }
        if (n == 0) System.out.println("Number n missing from config file");

        GaleShapley gs = new GaleShapley(n);

        int j = 1;
        for (int i = 0; i <= fileLines.length && j <= 2 * n; i++) {
            if (fileLines[i].startsWith(Integer.toString(j) + " ")) {
                if (j % 2 == 0) {
                    gs.wNames[j / 2 - 1] = new String(fileLines[i].substring((int) (Math.log10(j) + 1 + 1)));
                } else {
                    gs.mNames[(j - 1) / 2] = new String(fileLines[i].substring((int) (Math.log10(j) + 1 + 1)));
                }
                j++;
            }
        }

        j = 1;
        for (int i = 0; i <= fileLines.length && j <= 2 * n; i++) {
            if (fileLines[i].startsWith(Integer.toString(j) + ": ")) {
                String[] tempStr = fileLines[i].substring(fileLines[i].lastIndexOf(":") + 2).split(" ");
                for (int k = 0; k < n; k++) {
                    gs.mPrefs[(j - 1) / 2][k] = Integer.parseInt(tempStr[k]) / 2 - 1;
                }
                j = j + 2;
            }
        }

        j = 2;
        for (int i = 0; i <= fileLines.length && j <= 2 * n; i++) {
            if (fileLines[i].startsWith(Integer.toString(j) + ": ")) {
                String[] tempStr = fileLines[i].substring(fileLines[i].lastIndexOf(":") + 2).split(" ");
                for (int k = 0; k < n; k++) {
                    gs.wPrefs[j / 2 - 1][k] = (Integer.parseInt(tempStr[k]) - 1) / 2;
                }
                j = j + 2;
            }
        }
        return gs;
    }
}
