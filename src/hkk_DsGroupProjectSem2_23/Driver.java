/*Hueland Hunter - 2006702 
 * Kimar McDonald-Morgan - 1403238
 * Kevon Bennett - 2101075
*/

package hkk_DsGroupProjectSem2_23;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; 


public class Driver {

    // Static variables for the game
    private static Scanner scanner = new Scanner(System.in);
    private static Player[] players = new Player[3];
    private static Wheel wheel = new Wheel();
    private static boolean roundContinue = true;
    private static boolean playerTurn = true;

	public static void main(String[] args) {

        
        // Main game loop
        while (true) {
            System.out.println("\nWelcome to the Wheel of Fortune Game!");
            System.out.println("\nMenu:");
            System.out.println("1. Play");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            if (choice == 1) {
                playGame();
            } else if (choice == 2) {
                System.out.println("\nThank you for playing!");
                break;
            } else {
                System.out.println("\nInvalid choice. Please enter 1 to play or 2 to exit.");
            }
        }

        scanner.close();


    }

    // Method to play the game
    public static void playGame() {
        // Initialize game components
        
        PuzzleLinkedList puzzles = new PuzzleLinkedList();
    
        // Add players to the game
        players[0] = new Player(1, "Alice");
        players[1] = new Player(2, "Bob");
        players[2] = new Player(3, "Charlie");

        

        try {
            // Load puzzles from file
            File gameFile = new File("game.txt");
            Scanner gameScanner = new Scanner(gameFile);
            while (gameScanner.hasNextLine()){
                String[] data = gameScanner.nextLine().split(" ");
                String info = "";
                for (int num = 1; num < data.length; num++) {
                    
                    if (num == data.length - 1) {
                        info += data[num];
                    } else {
                        info += data[num] + " ";
                    }
                    
                }
                puzzles.addPuzzleNode(new PuzzleNode(new Puzzle(data[0], info)));
            }
            gameScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error aoccured");
            e.printStackTrace();
        }
    
        // Start the game loop - 3 rounds
        for (int round = 1; round <= 3; round++) {
            System.out.println("\nRound " + round);
    
            // Get the puzzle for this round
            
            Puzzle puzzle = puzzles.getRandomPuzzle(); // Gets Random pzzle
            
    
            int playerIndex = 0;
            roundContinue = true;
            while (roundContinue) {

                playerTurn = true;

                System.out.println("----------------- " + players[playerIndex].getName() + "'s' Turn" + " -----------------");

                // ------------------------ wheel spin earn or lose -------------------------------------------------
                wheelTurn(playerIndex);
                
                // ----------------------------------------- GUESS Game -----------------------------------------------
                // if card results in lose or bankruptcy
                if (playerTurn) {

                    String next = GuessGame(playerIndex, puzzle, false);

                    if (next == "continue" && playerTurn) {

                        System.out.println("----------------Continued Turn--------------------");

                        GuessGame(playerIndex, puzzle, true);
                
                    }

                    players[playerIndex].incrementGrandTotal();
                }

                //Diplay contest GrandTotal
                System.out.println(players[playerIndex].getName() + "'s Grand Total: $" + players[playerIndex].getGrandTotal());
                
                playerIndex++;
                if (playerIndex > 2) {
                    roundContinue = false;
                }
            }
            
        }

        // Determine winner
        int highestGrandTotal = 0;
        int playerIndexForGrand = 0;
        for (int num = 0; num < players.length; num++) {
            if (players[num].getGrandTotal() > highestGrandTotal) {
                highestGrandTotal = players[num].getGrandTotal();
                playerIndexForGrand = num;
            }
        }

        // Anounce winner
        System.out.println("*******************************************");
        System.out.println(players[playerIndexForGrand].getName() + " wins the game!");
        System.out.println("With Grand total of $" + players[playerIndexForGrand].getGrandTotal());
        System.out.println("*******************************************");
    
        // Reset player states for the next game
        resetPlayerStates();
    
        // Menu for playing again or exiting
        System.out.println("\nMenu:");
        System.out.println("1. Play again");
        System.out.println("2. Exit");
        System.out.println("\nWould you like to play again? :- ");
        int replayChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (replayChoice == 1) {
            playGame();
        } else {
            System.out.println("Thank you for playing!");
        }
    }

    // Method for spinning the wheel and handling actions
    public static void wheelTurn(int playerIndex) {
        Card spunCard = wheel.spin();
        System.out.println("Spun: " + spunCard.getType() + " Value: $" + spunCard.getValue());

        switch (spunCard.getType()) {
            case MONEY:
                players[playerIndex].addRoundTotal(spunCard.getValue());
                System.out.println(players[playerIndex].getName() + " wins $" + spunCard.getValue());
                break;
            case LOSE_A_TURN:
                System.out.println(players[playerIndex].getName() + " loses a turn.");
                playerTurn = false;
                break;
            case BANKRUPTCY:
                players[playerIndex].resetRoundTotal(); // Reset round total to 0 for bankruptcy
                System.out.println(players[playerIndex].getName() + " goes bankrupt.");
                playerTurn = false;
                break;
            case FREE_PLAY:
                players[playerIndex].addRoundTotal(spunCard.getValue());
                System.out.println(players[playerIndex].getName() + " gets a free play and wins $" + spunCard.getValue());
                break;
        }
    }

    // Method for the guessing game logic
    public static String GuessGame(int playerIndex, Puzzle puzzle, boolean continuedTurn) {
        
        // Display the puzzle category and the current state of the puzzle (with masked letters)
        System.out.println("Puzzle Category: " + puzzle.getCategory());
        System.out.println("Puzzle: " + puzzle.getMaskedPuzzleText());

        // Prompt the player based on whether it's a continued turn or not
        if (!continuedTurn) {
            System.out.println("Guess a letter, 'solve' to solve, or 'vowel' to buy a vowel:");
        } else {
            System.out.println("'Spin' to spin wheel again, 'solve' to solve, or 'vowel' to buy a vowel:");
        }
        
        // Capture player input
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("spin") && continuedTurn) {
            // If player chooses to spin the wheel again
            wheelTurn(playerIndex);
        } else if (input.equalsIgnoreCase("solve")) {
            // Handling the puzzle solving attempt
            if (players[playerIndex].getRoundTotal() > 0) {
                System.out.println("Enter your solution:");
                String solution = scanner.nextLine();
                if (solution.toLowerCase().equalsIgnoreCase(puzzle.getPuzzleText().toLowerCase())) {
                    System.out.println(players[playerIndex].getName() + " solved the puzzle!");
                    
                    roundContinue = false;
                    // Logic for awarding prize money for solving the puzzle could be added here
                    // Example: players[playerIndex].addRoundTotal(prizeMoney);
                    // End the round
                }
                else {
                    System.out.println("Incorrect Solution!");
                    // Debugging aid - might be removed in production code
                    System.out.println(solution.length() + " -------- " + puzzle.getPuzzleText().length());

                }
            } else {
                System.out.println("Player " + players[playerIndex].getName() + "has no funds to attempt solving the game!");
            }
            
        } else if (input.equalsIgnoreCase("vowel")) {
            // Handle vowel buying logic
            System.out.println("Enter the vowel you want to buy (A, E, I, O, U): ");
            char vowel = scanner.nextLine().toUpperCase().charAt(0);
            if ("AEIOUaeiou".indexOf(vowel) != -1) { // check if a vowel was entered
                if (puzzle.isLetterNotRevealedAlready(vowel)) {
                    if (players[playerIndex].buyVowel(vowel)) {
                        System.out.println("Vowel bought successfully.");   
                        puzzle.updatePuzzlesGuessed(vowel);
                        int occurrences = puzzle.countLetterOccurences(vowel);
                        int moneyEarned = occurrences * players[playerIndex].getCurrentRoundTotal();
                        players[playerIndex].addRoundTotal(moneyEarned);
                        System.out.println(players[playerIndex].getName() + " Earned $" + Integer.toString(moneyEarned));                     
                        
                    } else {
                        System.out.println("Not enough funds to buy vowel or vowel purchase failed.");
                    }
                } else {
                    System.out.println("User this letter has already been revealed.");
                }
            } else {
                System.out.println("User did not enter a vowel.");
            }
        } else if (input.length() == 1 && Character.isLetter(input.charAt(0)) && !continuedTurn) {
            // Handle letter guessing logic
            char letter = input.charAt(0);
            if (puzzle.isLetterNotRevealedAlready(letter)) {

                // Handle correct guess logic here
                // Update puzzle state with guessed letter                
                puzzle.updatePuzzlesGuessed(letter);
                // Calculate and add money earned to player's round total
                // This logic assumes a fixed amount earned per correct letter; adjust as necessary
                int occurrences = puzzle.countLetterOccurences(letter);
                int moneyEarned = occurrences * players[playerIndex].getCurrentRoundTotal();
                players[playerIndex].addRoundTotal(moneyEarned);
                System.out.println(players[playerIndex].getName() + " guessed correctly! Earned $" + moneyEarned);
                return "continue";// Player continues their turn
            
            } else {
                System.out.println("Letter '" + letter + "' was already guessed. Turn moves to the next player.");
            }

        } else {

            // Handle invalid input
            System.out.println("Invalid input. Turn moves to the next player.");
        }

        return ""; // Return value could indicate turn end, continue, or specific outcomes
    }

    // Method to reset player states for a new game
    public static void resetPlayerStates() {
        for (Player player: players) {
            player.resetRoundTotal();
            player.resetGrandTotal();
        }
    }
}