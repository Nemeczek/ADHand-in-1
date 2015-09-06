package com.company;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by GROUP C on 02-09-2015.
 */
class GaleShapley {

    Stack<Integer> unmatched;
    Integer[] propose;
    Integer[] mMatches;
    Integer[] wMatches;

    public String[] mNames;
    public String[] wNames;
    public Integer[][] mPrefs;
    public Integer[][] wPrefs;
    Integer[][] wPrefsInverted;
    int n;

    public GaleShapley(int n) {

        mNames = new String[n];
        wNames = new String[n];
        wPrefs = new Integer[n][n];
        mPrefs = new Integer[n][n];
        wPrefsInverted = new Integer[n][n];
        this.n = n;
        unmatched = new Stack();
        //create table of proposed girls
        propose = new Integer[n];
        //create table of men's matches
        mMatches = new Integer[n];
        //create table of women's matches
        wMatches = new Integer[n];

        //create unmatched man list
        for (int i=n-1;i>=0;i--){
            unmatched.push(i);
        }

        //fill it with zeroes
        //it means the number of the girl in the guy's prefs
        for (int i=0;i<n;i++){
            propose[i]=0;
        }

        //fill it with -1
        for (int i=0;i<n;i++){
            mMatches[i]=-1;
        }

        //fill it with -1
        for (int i=0;i<n;i++){
            wMatches[i]=-1;
        }
    }

    public void StableMatcher () throws IOException {
        for(int i= 0;i < n;i++)
        {
            for (int j = 0; j < n; j++)
            {
                wPrefsInverted[i][wPrefs[i][j]] = j;
            }
        }

        int womansNewguysPr=-1,womansCurrBfPr=-1;
        while (!unmatched.empty()) {
            int guy = unmatched.peek();
            int nextGirlInMPrefs = propose[guy];
            while (nextGirlInMPrefs<n){
                int womanHeWantsNow = mPrefs[guy][nextGirlInMPrefs];
                if (wMatches[womanHeWantsNow]==-1) {
                    mMatches[guy] = womanHeWantsNow;
                    wMatches[womanHeWantsNow] = guy;
                    unmatched.pop();
                    propose[guy]++;
                    break;
                }

                womansNewguysPr = wPrefsInverted[womanHeWantsNow][guy];
                womansCurrBfPr = wPrefsInverted[womanHeWantsNow][wMatches[womanHeWantsNow]];

                if(womansNewguysPr < womansCurrBfPr) {
                    unmatched.pop();
                    unmatched.push(wMatches[womanHeWantsNow]);
                    mMatches[wMatches[womanHeWantsNow]]=-1;
                    wMatches[womanHeWantsNow] = guy;
                    mMatches[guy] = womanHeWantsNow;
                    propose[guy]++;
                    break;
                }else{
                    propose[guy]++;
                    nextGirlInMPrefs = propose[guy];
                }
            }
        }
        for (int i=0;i<n;i++){
            System.out.println(mNames[i] + " -- " + wNames[mMatches[i]]);
        }
    }
}
