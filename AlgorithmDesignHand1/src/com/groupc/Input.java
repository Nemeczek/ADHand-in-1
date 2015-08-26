package com.groupc;

/**
 * Created by nemec on 26/08/2015.
 */
public class Input {

    private Integer AmountOfPairs;
    private Item[] SetA;
    private Item[] SetB;
    private Integer[][] PreferencesOfA;
    private Integer[][] PreferencesOfB;

    public Integer getAmountOfPairs() {
        return AmountOfPairs;
    }

    public void setAmountOfPairs(Integer amountOfPairs) {
        AmountOfPairs = amountOfPairs;
        SetA = new Item[AmountOfPairs];
        SetB = new Item[AmountOfPairs];

        for(int i = 0; i < AmountOfPairs; i++){
            SetA[i] = new Item();
            SetB[i] = new Item();
        }

        PreferencesOfA = new Integer[AmountOfPairs][AmountOfPairs];
        PreferencesOfB = new Integer[AmountOfPairs][AmountOfPairs];
    }

    public Item[] getSetA() {
        return SetA;
    }

    public void setSetA(Item[] setA) {
        SetA = setA;
    }

    public Item[] getSetB() {
        return SetB;
    }

    public Integer[][] getPreferencesOfA() {
        return PreferencesOfA;
    }

    public Integer[][] getPreferencesOfB() {
        return PreferencesOfB;
    }

}


