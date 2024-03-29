package hkk_DsGroupProjectSem2_23;

public class Puzzle {
	
	private String category;
    private String puzzleText;

    public Puzzle() {
        this.category = "";
        this.puzzleText = "";
    }

    public Puzzle(String category, String puzzleText) {
        this.category = category;
        this.puzzleText = puzzleText;
    }

    public Puzzle(Puzzle copy) {
        this.category = copy.category;
        this.puzzleText = copy.puzzleText;
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
    }

    public String getMaskedPuzzleText() {
        StringBuilder maskedText = new StringBuilder();
        for (char c : puzzleText.toCharArray()) {
            if (Character.isLetter(c)) {
                maskedText.append("_ ");
            } else if (c == ' ') {
                maskedText.append("  "); // Preserve spaces
            } else {
                maskedText.append(c);
            }
        }
        return maskedText.toString().trim(); // Remove trailing whitespace
    }

	public boolean revealGuessedLetter( char letter) {
		boolean found = false;
	    char[] puzzleChars = puzzleText.toCharArray();
	    for (int i = 0; i < puzzleChars.length; i++) {
	        if (Character.toLowerCase(puzzleChars[i]) == Character.toLowerCase(letter)) {
	            puzzleChars[i] = puzzleText.charAt(i);
	            found = true;
	        }
	    }
	    puzzleText = new String(puzzleChars);
		return false;
	}

}
