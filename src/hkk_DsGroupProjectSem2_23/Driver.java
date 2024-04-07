package hkk_DsGroupProjectSem2_23;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; 


public class Driver {

    private static Scanner scanner = new Scanner(System.in);
    private static Player[] players = new Player[3];
    private static Wheel wheel = new Wheel();
    private static boolean roundContinue = true;
    private static boolean playerTurn = true;

	public static void main(String[] args) {

        
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

    public static void playGame() {
        // Initialize game components
        
        PuzzleLinkedList puzzles = new PuzzleLinkedList();
    
        // Add players to the game
        players[0] = new Player(1, "Alice");
        players[1] = new Player(2, "Bob");
        players[2] = new Player(3, "Charlie");

        

        try {
            File gameFile = new File("game.txt");
            Scanner gameScanner = new Scanner(gameFile);
            while (gameScanner.hasNextLine()){
                String[] data = gameScanner.nextLine().split(" ");
                String info = "";
                System.out.println(data);
                for (int num = 1; num < data.length; num++) {
                    System.out.println(num);
                    if (num == data.length - 1) {
                        info += data[num];
                    } else {
                        info += data[num] + " ";
                    }
                    System.out.println(info);
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

                System.out.println(players[playerIndex].getName() + "'s Grand Total: $" + players[playerIndex].getGrandTotal());
                
                playerIndex++;
                if (playerIndex > 2) {
                    roundContinue = false;
                }
            }
            
        }

        int highestGrandTotal = 0;
        int playerIndexForGrand = 0;
        for (int num = 0; num < players.length; num++) {
            if (players[num].getGrandTotal() > highestGrandTotal) {
                highestGrandTotal = players[num].getGrandTotal();
                playerIndexForGrand = num;
            }
        }

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

    public static String GuessGame(int playerIndex, Puzzle puzzle, boolean continuedTurn) {
        
        System.out.println("Puzzle Category: " + puzzle.getCategory());
        System.out.println("Puzzle: " + puzzle.getMaskedPuzzleText());
        if (!continuedTurn) {
            System.out.println("Guess a letter, 'solve' to solve, or 'vowel' to buy a vowel:");
        } else {
            System.out.println("'Spin' to spin wheel again, 'solve' to solve, or 'vowel' to buy a vowel:");
        }
        
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("spin") && continuedTurn) {
            wheelTurn(playerIndex);
        } else if (input.equalsIgnoreCase("solve")) {
            if (players[playerIndex].getRoundTotal() > 0) {
                System.out.println("Enter your solution:");
                String solution = scanner.nextLine();
                if (solution.toLowerCase().equalsIgnoreCase(puzzle.getPuzzleText().toLowerCase())) {
                    System.out.println(players[playerIndex].getName() + " solved the puzzle!");
                    
                    roundContinue = false;
                    /**
                     * add any money if it should be done
                     * end the round 
                     */
                }
                else {
                    System.out.println("Incorrect Solution!");
                    System.out.println(solution.length() + " -------- " + puzzle.getPuzzleText().length());

                }
            } else {
                System.out.println("Player " + players[playerIndex].getName() + "has no funds to attempt solving the game!");
            }
            
        } else if (input.equalsIgnoreCase("vowel")) {
            System.out.println("Enter the vowel you want to buy (A, E, I, O, U): ");
            char vowel = scanner.next().toUpperCase().charAt(0);
            if ("AEIOUaeiou".indexOf(vowel) != -1) { // check if vowel was entered
                if (puzzle.isLetterNotRevealedAlready(vowel)) {
                    if (players[playerIndex].buyVowel(vowel)) {
                        System.out.println("Vowel bought successfully.");                        
                        
                    } else {
                        System.out.println("Unable to buy vowel.");
                    }
                } else {
                    System.out.println("User this letter has already been revealed.");
                }
            } else {
                System.out.println("User did not enter a vowel.");
            }
            if (players[playerIndex].buyVowel(vowel)) {
                System.out.println("Vowel bought successfully.");
            } else {
                System.out.println("Unable to buy vowel.");
            }
        } else if (input.length() == 1 && Character.isLetter(input.charAt(0)) && !continuedTurn) {
            char letter = input.charAt(0);
            if (puzzle.isLetterNotRevealedAlready(letter)) {

                // Handle correct guess logic here
                puzzle.updatePuzzlesGuessed(letter);
                int occurrences = puzzle.getPuzzleText().toLowerCase().replaceAll("[^" + letter + "]", "").length();
                int moneyEarned = occurrences * players[playerIndex].getCurrentRoundTotal();
                players[playerIndex].addRoundTotal(moneyEarned);
                System.out.println(players[playerIndex].getName() + " guessed correctly! Earned $" + moneyEarned);
                return "continue";
            
            } else {
                System.out.println("Letter '" + letter + "' was already guessed. Turn moves to the next player.");
            }

        } else {

            System.out.println("Invalid input. Turn moves to the next player.");
        }

        return "";
    }

    public static void resetPlayerStates() {
        for (Player player: players) {
            player.resetRoundTotal();
            player.resetGrandTotal();
        }
    }
}