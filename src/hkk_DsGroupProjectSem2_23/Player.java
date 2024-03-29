package hkk_DsGroupProjectSem2_23;

public class Player {

    private int playerNumber;
    private String name;
    private int grandTotal;
    private int roundTotal;
    private GuessedLetters guessedLetters;
    private int money;
    private String puzzle; // Assuming player gets the puzzle to solve

    // Constants
    private static final int VOWEL_COST = 250;

    //Default Constructor
	public Player() {
		playerNumber = 0;
        name = " ";
        grandTotal = 0;
        roundTotal = 0;
        guessedLetters = new GuessedLetters();
	}

    public Player(int playerNumber, String name) {
        this.playerNumber = playerNumber;
        this.name = name;
        this.guessedLetters = new GuessedLetters();
        this.money = 0;
        this.puzzle = ""; // Initialize with an empty string
    }

    // Getter methods
    public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
    
    public int getGrandTotal() {
        return grandTotal;
    }

    public int getRoundTotal() {
        return roundTotal;
    }

    public int getCurrentRoundTotal() {
        return roundTotal;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    // Other methods
    public void resetRoundTotal() {
        this.roundTotal = 0;
    }

    public void resetGrandTotal() {
        this.grandTotal = 0;
    }

    public void addRoundTotal(int amount) {
        this.roundTotal += amount;
    }

    public void incrementGrandTotal(int amount) {
        grandTotal += amount;
    }

    public boolean buyVowel(char vowel) {
        if (money >= VOWEL_COST && !guessedLetters.contains(vowel) && isVowel(vowel)) {
            guessedLetters.add(vowel); // Mark vowel as guessed
            money -= VOWEL_COST;
            // Check if the vowel is in the puzzle, if necessary
            return true;
        } else {
            return false;
        }
    }

    public boolean solvePuzzle(String solution) {
        if (this.puzzle.equalsIgnoreCase(solution)) {
            int reward = calculatePuzzleReward(); // Implement this method based on desired logic
            money += reward;
            return true;
        } else {
            return false;
        }
    }

    private int calculatePuzzleReward() {
        // Example calculation, adjust as necessary
        return 1000; // Base reward for solving the puzzle
    }

    private boolean isVowel(char ch) {
        return "AEIOU".indexOf(Character.toUpperCase(ch)) >= 0;
    }
	
    /************ Original ****************/
	/*private int playerNumber;
	private String name;
	private int grandTotal;
	private int roundTotal;
	private GuessedLetters guessedLetters;
	
	//Default Constructor
	public Player() {
		playerNumber = 0;
        name = " ";
        grandTotal = 0;
        roundTotal = 0;
        guessedLetters = new GuessedLetters();
	}
	
	//Primary Constructor
	public Player(int playerNumber, String name, int grandTotal, int roundTotal) {
        this.playerNumber = playerNumber;
        this.name = name;
        this.grandTotal = grandTotal;
        this.roundTotal = roundTotal;
        guessedLetters = new GuessedLetters();
    
    }
    
	//Copy Constructor
    public Player(Player play) {
        this.playerNumber = play.playerNumber;
        this.name = play.name;
        this.grandTotal = play.grandTotal;
        this.roundTotal = play.roundTotal;
        this.guessedLetters = play.guessedLetters;
      
    }
    
    //Setters/Getters
	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

	public int getRoundTotal() {
		return roundTotal;
	}

	public void setRoundTotal(int roundTotal) {
		this.roundTotal = roundTotal;
	}
    
	//Methods
	public void resetRoundTotal() {
        this.roundTotal = 0;
    }

    public void addRoundTotal(int amount) {
        this.roundTotal += amount;
    }

    public void incrementGrandTotal() {
        this.grandTotal += roundTotal;
    }

    public int getCurrentRoundTotal() {
        return roundTotal;
    }
    
    public void incrementGrandTotal(int amount) {
        grandTotal += amount;
    }

    

    // Method to reset the grand total to 0
    public void resetGrandTotal() {
        this.grandTotal = 0;
    }
    
    public void isLetterGuessedAlready(char letter, Puzzle puzzle, Card spunCard) {
        if (guessedLetters.isLetterGuessedAlready(letter)) {
            int occurrences = 0;
            String puzzleText = puzzle.getPuzzleText();
            for (char c : puzzleText.toCharArray()) {
                if (Character.toUpperCase(c) == Character.toUpperCase(letter)) {
                    occurrences++;
                }
            }
            int winnings = occurrences * spunCard.getValue();
            System.out.println("Found " + occurrences + " occurrences of '" + letter + "'. You win $" + winnings);
            roundTotal += winnings;
        }
    }

    public void solvePuzzle(String guess, Puzzle puzzle) {
        if (guess.equalsIgnoreCase(puzzle.getPuzzleText())) {
            System.out.println("Congratulations! You solved the puzzle.");
            roundTotal += puzzle.getPuzzleText().length() * 100; // Assume $100 per letter
        } else {
            System.out.println("Incorrect guess. Turn moves to the next player.");
        }
    }

    public void buyVowel(char vowel, Puzzle puzzle) {
        if (vowel == 'A' || vowel == 'E' || vowel == 'I' || vowel == 'O' || vowel == 'U') {
            if (roundTotal >= 250) {
                if (guessedLetters.isLetterGuessedAlready(vowel)) {
                    int occurrences = 0;
                    String puzzleText = puzzle.getPuzzleText();
                    for (char c : puzzleText.toCharArray()) {
                        if (Character.toUpperCase(c) == Character.toUpperCase(vowel)) {
                            occurrences++;
                        }
                    }
                    System.out.println("Vowel found! '" + vowel + "' appears " + occurrences + " times.");
                    roundTotal -= 250; // Deduct $250 for buying a vowel
                }
            } else {
                System.out.println("Insufficient funds to buy a vowel.");
            }
        } else {
            System.out.println("Invalid vowel. Vowels allowed: A, E, I, O, U");
        }
    }
    */

}
