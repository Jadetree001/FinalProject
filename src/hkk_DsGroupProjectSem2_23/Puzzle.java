/* 
 * Hueland Hunter - 2006702
*/
package hkk_DsGroupProjectSem2_23;


public class Puzzle {
	
	private String category;
    private String puzzleText;
    private boolean[] puzzles;

    //Default
    public Puzzle() {
        this.category = "";
        this.puzzleText = "";
    }

    //Primary
    public Puzzle(String category, String puzzleText) {
        this.category = category;
        this.puzzleText = puzzleText;
        puzzles = new boolean[puzzleText.length()];
        
    }

    //Copy
    public Puzzle(Puzzle copy) {
        this.category = copy.category;
        this.puzzleText = copy.puzzleText;
        puzzles = new boolean[puzzleText.length()];
    }

    //Setters & Getters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPuzzleText() {
        return puzzleText;
    }

    public void setPuzzleText(String puzzleText) {
        this.puzzleText = puzzleText;
        puzzles = new boolean[puzzleText.length()];// Initialize puzzles array based on new puzzle text length
    }

    // Method to get the masked puzzle text with revealed and unrevealed letters
    public String getMaskedPuzzleText() {
        StringBuilder maskedText = new StringBuilder();
        for (int num = 0; num < puzzleText.length(); num++) {
            char c = puzzleText.toCharArray()[num];
            if (puzzles[num]) {
                if (c == ' ') {
                    maskedText.append("  ");
                } else {
                    maskedText.append(c);
                }
                
            } else {
                if (c == ' ') {
                    maskedText.append("  ");
                } else {
                    maskedText.append(" _ ");
                }
            }
        }
        return maskedText.toString().trim(); // Remove trailing whitespace
    }

    // Method to update the revealed status of letters in the puzzle
    public void updatePuzzlesGuessed(char letter) {
        for (int num = 0; num < puzzleText.length(); num++) {
            if (puzzleText.toLowerCase().toCharArray()[num] == Character.toLowerCase(letter)){
                puzzles[num] = true;
            }
        }
    }

    // Method to check if a letter has not been revealed already in the puzzle
    public boolean isLetterNotRevealedAlready(char letter){
        return getMaskedPuzzleText().indexOf(letter) == -1;
    }

    // Method to refresh the puzzle by resetting the revealed status of all letters
    public void refreshPuzzle() {
        for (int num = 0; num < puzzleText.length(); num++) {
            puzzles[num] = false;
        }
    }


}
