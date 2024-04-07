package hkk_DsGroupProjectSem2_23;

public class Player {

    private int playerNumber;
    private String name;
    private int grandTotal;
    private int roundTotal;
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
	}

    public Player(int playerNumber, String name) {
        this.playerNumber = playerNumber;
        this.name = name;
        // this.money = 0;
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

    public void incrementGrandTotal() {
        grandTotal += roundTotal;
    }

    public boolean buyVowel(char vowel) {
        System.out.println("------------");
        System.out.println(grandTotal);
        System.out.println(grandTotal >= VOWEL_COST);
        if (grandTotal >= VOWEL_COST) {
            grandTotal -= VOWEL_COST;
            return true;
        } else {
            return false;
        }
    }

    public boolean solvePuzzle(String solution) {
        if (this.puzzle.equalsIgnoreCase(solution)) {
            int reward = calculatePuzzleReward(); // Implement this method based on desired logic
            grandTotal += reward;
            return true;
        } else {
            return false;
        }
    }

    private int calculatePuzzleReward() {
        // Example calculation, adjust as necessary
        return 1000; // Base reward for solving the puzzle
    }

}
