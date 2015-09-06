import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class GaleShapley
{
    private int N, engagedCount;
    private String[][] menPref;
    private String[][] womenPref;
    private String[] men;
    private String[] women;
    private String[] womenPartner;
    private boolean[] menEngaged;

    public GaleShapley(String[] m, String[] w, String[][] mp, String[][] wp) throws FileNotFoundException {
        N = mp.length;
        engagedCount = 0;
        men = m;
        women = w;
        menPref = mp;
        womenPref = wp;
        menEngaged = new boolean[N];
        womenPartner = new String[N];
        try {
            calcMatches();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /** function to calculate all matches **/
    private void calcMatches() throws FileNotFoundException {
        while (engagedCount < N){
            int free;
            for (free = 0; free < N; free++)
                if (!menEngaged[free])
                    break;
            for (int i = 0; i < N && !menEngaged[free]; i++){
                int index = womenIndexOf(menPref[free][i]);
                if (womenPartner[index] == null){
                    womenPartner[index] = men[free];
                    menEngaged[free] = true;
                    engagedCount++;
                }
                else{
                    String currentPartner = womenPartner[index];
                    if (morePreference(currentPartner, men[free], index)){
                        womenPartner[index] = men[free];
                        menEngaged[free] = true;
                        menEngaged[menIndexOf(currentPartner)] = false;
                    }
                }
            }
        }
        try {
            printCouples();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /** function to check if women prefers new partner over old assigned partner **/
    private boolean morePreference(String curPartner, String newPartner, int index){
        for (int i = 0; i < N; i++){
            if (womenPref[index][i].equals(newPartner))
                return true;
            if (womenPref[index][i].equals(curPartner))
                return false;
        }
        return false;
    }
    /** get men index **/
    private int menIndexOf(String str){
        for (int i = 0; i < N; i++)
            if (men[i].equals(str))
                return i;
        return -1;
    }
    /** get women index **/
    private int womenIndexOf(String str){
        for (int i = 0; i < N; i++)
            if (women[i].equals(str))
                return i;
        return -1;
    }
    /** print couples **/
    public void printCouples() throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("GaleOutput.txt"));
        System.setOut(out);
        System.out.println("Gale Shapley Marriage Algorithm\n");
        System.out.println("Couples are : ");
        for (int i = 0; i < N; i++){
            System.out.println(womenPartner[i] + " " + women[i]);
        }
    }

    public static Object[] scanner(String filename) {
        File file = new File(filename);
        String[] data = null;
        String[] men = null;
        String[] women = null;
        String[][] menPref = null;
        String[][] womenPref = null;
        int n = 0;
        int dataCount = 0;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String i = sc.nextLine();
                if (i.startsWith("n")) {
                    n = Integer.parseInt(i.substring(2, i.length()));
                    men = new String[n];
                    women = new String[n];
                    menPref = new String[n][n];
                    womenPref = new String[n][n];
                    data = new String[n * 4];
                }
                if (i.matches("\\d.+")) {
                    data[dataCount] = i;
                    dataCount +=1;
                }
            }
            Integer menCount = 0;
            Integer womCount = 0;
            Integer mpCount = 0;
            Integer wpCount = 0;

            // man/women name arrays
            for (int i=0; i<(n*4); i++) {
                if (i % 2 == 0 && i < ((n * 4) / 2)) {
                    // ITS A BOY!
                    men[menCount] = data[i].split("\\s+")[0];
                    menCount += 1;
                }
                if (i % 2 != 0 && i < ((n * 4) / 2)) {
                    // ITS A GIRL!
                    women[womCount] = data[i].split("\\s+")[0];
                    womCount += 1;
                }
                if (i % 2 == 0 && i >= n * 2) {
                    // men prefs
                    for (int j=0; j<n; j++) {
                        menPref[mpCount][j] = data[i].split("\\s+")[j+1];
                    }
                    mpCount += 1;
                }
                if (i % 2 != 0 && i >= n * 2) {
                    // women prefs
                    for (int k=0; k<n; k++) {
                        womenPref[wpCount][k] = data[i].split("\\s+")[k+1];
                    }
                    wpCount += 1;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Object[] obj;
        return obj = new Object[] {men, women, menPref, womenPref};

    }

    /** main function **/
    // Provide filename as string to input file!
    public static void main(String[] args) throws FileNotFoundException {

        if (0 < args.length) {
            Object[] results = scanner(args[0]);
            String[] m;
            String[] w;
            String[][] mp;
            String[][] wp;
            m = (String[]) results[0];
            w = (String[]) results[1];
            mp = (String[][]) results[2];
            wp = (String[][]) results[3];

            GaleShapley gs = new GaleShapley(m, w, mp, wp);
        }
        else {
            System.out.println("Provide filepath as input (string)\n");
            System.out.println("Terminating....");
        }
    }
}