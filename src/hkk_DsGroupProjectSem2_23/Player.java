package hkk_DsGroupProjectSem2_23;

public class Player {
	
	private int playerNumber;
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

    
    
    public void guessLetter(char letter, Puzzle puzzle, Card spunCard) {
        if (guessedLetters.guessLetter(letter)) {
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
                if (guessedLetters.guessLetter(vowel)) {
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
    

}
