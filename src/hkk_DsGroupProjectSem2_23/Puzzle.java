package hkk_DsGroupProjectSem2_23;


public class Puzzle {
	
	private String category;
    private String puzzleText;
    private boolean[] puzzles;

    public Puzzle() {
        this.category = "";
        this.puzzleText = "";
    }

    public Puzzle(String category, String puzzleText) {
        this.category = category;
        this.puzzleText = puzzleText;
        puzzles = new boolean[puzzleText.length()];
        
        /*hghghg */
    }

    public Puzzle(Puzzle copy) {
        this.category = copy.category;
        this.puzzleText = copy.puzzleText;
        puzzles = new boolean[puzzleText.length()];
    }

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
        puzzles = new boolean[puzzleText.length()];
    }

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

    public void updatePuzzlesGuessed(char letter) {
        for (int num = 0; num < puzzleText.length(); num++) {
            if (puzzleText.toLowerCase().toCharArray()[num] == Character.toLowerCase(letter)){
                puzzles[num] = true;
            }
        }
    }

	// public boolean revealGuessedLetter( char letter) {
	// 	boolean found = false;
	//     char[] puzzleChars = puzzleText.toCharArray();
	//     for (int i = 0; i < puzzleChars.length; i++) {
	//         if (Character.toLowerCase(puzzleChars[i]) == Character.toLowerCase(letter)) {
	//             puzzleChars[i] = puzzleText.charAt(i);
	//             found = true;
	//         }
	//     }
	//     puzzleText = new String(puzzleChars);
	// 	return false;
	// }

}
