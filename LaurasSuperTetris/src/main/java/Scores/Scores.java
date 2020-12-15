
package Scores;

public class Scores {

    public String[][] scoreTable;    

    public Scores() {
        scoreTable = new String[10][3];
        for (int i = 0; i < 10; i++) {
            scoreTable[i][0] = (i + 1) + "."; 
            scoreTable[i][1] = "...";
            scoreTable[i][2] = "0";
        }
    }
    
    public void sortTable() {
        
    }
}
