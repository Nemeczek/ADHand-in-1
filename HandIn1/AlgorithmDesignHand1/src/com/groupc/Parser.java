package com.groupc;

import java.io.*;

/**
 * Created by nemec on 26/08/2015.
 */
public class Parser {
    private String filePath;
    private BufferedReader reader;
    private Input parsingOutput;

    public Parser(String fileUri) {
        this.filePath = fileUri;
        parsingOutput = new Input();
    }

    public Input Parse() throws IOException {
        ReadFile();
        ParseAmountOfPairsAndSkipCommentsIfAny();
        ParseItemsNames();
        GoToNextLine();
        ParsePreferences();
        return parsingOutput;
    }

    private void ReadFile() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(new File(filePath)));
    }

    private void ParseAmountOfPairsAndSkipCommentsIfAny() throws IOException {
        String currentLine;
        while((currentLine = reader.readLine()) != null){
            if(currentLine.startsWith("#")) continue;
            parsingOutput.setAmountOfPairs(Integer.parseInt(currentLine.substring(2)));
            break;
        }
    }

    private void ParseItemsNames() throws IOException {
        String currentLine;
        for(int i = 0; i<parsingOutput.getAmountOfPairs(); i++) {
            currentLine = reader.readLine();
            parsingOutput.getSetA()[i].setName(currentLine.split("\\s+")[1]);

            currentLine = reader.readLine();
            parsingOutput.getSetB()[i].setName(currentLine.split("\\s+")[1]);
        }
    }

    private void GoToNextLine() throws IOException {
        reader.readLine();
    }

    private void ParsePreferences() throws IOException {
        String currentLine;
        for(int i = 0; i<parsingOutput.getAmountOfPairs(); i++){
            String[] currentPreferences;
            currentLine = reader.readLine();
            currentPreferences = currentLine.split("\\s+");
            for(int j = 1; j<currentPreferences.length;j++){
                parsingOutput.getPreferencesOfA()[i][j-1] = Integer.parseInt(currentPreferences[j])-1;
            }
            currentLine = reader.readLine();
            currentPreferences = currentLine.split("\\s+");
            for(int j = 1; j<currentPreferences.length;j++){
                parsingOutput.getPreferencesOfB()[i][j-1] = Integer.parseInt(currentPreferences[j])-1;
            }
        }
    }


}
