package hkk_DsGroupProjectSem2_23;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Wheel of Fortune Game!");

            // Menu for starting the game or exiting
            System.out.println("\nMenu:");
            System.out.println("1. Play");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                playGame(scanner);
            } else if (choice == 2) {
                System.out.println("\nThank you for playing!");
                break;
            } else {
                System.out.println("\nInvalid choice. Please enter 1 to play or 2 to exit.");
            }
        }

        scanner.close();
    }

    public static void playGame(Scanner scanner) {
        // Initialize game components
        PlayerQueue playerQueue = new PlayerQueue();
        Wheel wheel = new Wheel();
        PuzzleLinkedList puzzles = new PuzzleLinkedList();
        
        
        

        // Add players to the game
        playerQueue.enqueue(new Player(1, "Alice", 0, 0));
        playerQueue.enqueue(new Player(2, "Bob", 0, 0));
        playerQueue.enqueue(new Player(3, "Charlie", 0, 0));

        // Initialize puzzles
        puzzles.addPuzzleNode(new PuzzleNode(new Puzzle("Movies", "The Godfather")));
        puzzles.addPuzzleNode(new PuzzleNode(new Puzzle("Cities", "New York")));
        puzzles.addPuzzleNode(new PuzzleNode(new Puzzle("Animals", "Elephant")));

        // Start the game loop - 3 rounds
        for (int round = 1; round <= 3; round++) {
            System.out.println("\nRound " + round);

            // Get the puzzle for this round
           Puzzle puzzle = puzzles.getRandomPuzzle("Movies"); // For demonstration, we're choosing a category here
            

            // Initialize guessed letters for the round
            GuessedLetters guessedLetters = new GuessedLetters();

            // Store players for re-enqueuing in subsequent rounds
            Player[] players = new Player[playerQueue.size()];
            for (int i = 0; i < players.length; i++) {
                players[i] = playerQueue.dequeue();
            }
            
            System.out.println("Category: " + puzzle.getCategory());

            try {
                for (Player player : players) {
                    System.out.println("\n" + player.getName() + "'s turn to spin the wheel.");

                    // Player spins the wheel
                    Card spunCard = wheel.spin();
                    System.out.println("Spun: " + spunCard.getType() + " Value: $" + spunCard.getValue());

                    // Process the result of the spin
                    switch (spunCard.getType()) {
                        case MONEY:
                            player.addRoundTotal(spunCard.getValue());
                            System.out.println(player.getName() + " wins $" + spunCard.getValue());
                            break;
                        case LOSE_A_TURN:
                            System.out.println(player.getName() + " loses a turn.");
                            break;
                        case BANKRUPTCY:
                            player.setRoundTotal(0); // Reset round total to 0 for bankruptcy
                            System.out.println(player.getName() + " goes bankrupt.");
                            break;
                        case FREE_PLAY:
                            player.addRoundTotal(spunCard.getValue());
                            System.out.println(player.getName() + " gets a free play and wins $" + spunCard.getValue());
                            break;
                    }

                    // Put the player back in the queue if not bankrupt or lost a turn
                    if (spunCard.getType() != Card.CardType.LOSE_A_TURN && spunCard.getType() != Card.CardType.BANKRUPTCY) {
                        playerQueue.enqueue(player);
                    }

                    // Update the player's grand total
                    player.incrementGrandTotal();

                    // For demonstration, let's print the player's grand total after their turn
                    System.out.println(player.getName() + "'s Grand Total: $" + player.getGrandTotal());

                    // Handle guessing letters, solving the puzzle, and buying vowels
                    handleGuessing(scanner, player, puzzle, guessedLetters);
                }
            } catch (NoSuchElementException e) {
                System.out.println("The player queue is unexpectedly empty.");
            }
            System.out.println("-----------------------------------"); // Section divider between rounds
        }

        // Menu for playing again or exiting
        System.out.println("\nMenu:");
        System.out.println("1. Play again");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\nLet's play again!");
            playGame(scanner); // Recursively start the game again
        } else if (choice == 2) {
            System.out.println("\nThank you for playing!");
        } else {
            System.out.println("Invalid choice. Exiting the game.");
        }
    }

    public static void handleGuessing(Scanner scanner, Player player, Puzzle puzzle, GuessedLetters guessedLetters) {
        // Display the masked puzzle text
        System.out.println("Puzzle: " + puzzle.getMaskedPuzzleText());

        // Ask the player to guess a letter, solve the puzzle, or buy a vowel
        System.out.println("Guess a letter (or enter 'solve' to solve the puzzle or 'vowel' to buy a vowel):");
        String guess = scanner.next().toLowerCase();

        if (guess.equals("solve")) {
            System.out.println("Enter your solution:");
            String solution = scanner.next().toLowerCase();
            if (solution.equals(puzzle.getPuzzleText().toLowerCase())) {
                System.out.println(player.getName() + " solved the puzzle!");
                player.incrementGrandTotal(player.getRoundTotal());
                player.setRoundTotal(0); // Reset round total after solving the puzzle
            } else {
                System.out.println("Incorrect solution. Turn moves to the next player.");
            }
        } else if (guess.equals("vowel")) {
        	// Implement buying a vowel logic here
            System.out.println("Enter the vowel you want to buy (A, E, I, O, U): ");
            char vowel = scanner.next().toUpperCase().charAt(0);
            player.buyVowel(vowel, puzzle);
        } else if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
            char letter = guess.charAt(0);
            if (!guessedLetters.isLetterGuessedAlready(letter)) {
                // Handle correct guess logic here
                puzzle.updatePuzzlesGuessed(letter);
                int occurrences = puzzle.getPuzzleText().toLowerCase().replaceAll("[^" + letter + "]", "").length();
                int moneyEarned = occurrences * player.getCurrentRoundTotal();
                player.addRoundTotal(moneyEarned);
                System.out.println(player.getName() + " guessed correctly! Earned $" + moneyEarned);
            } else {
                System.out.println("Letter '" + letter + "' was already guessed. Turn moves to the next player.");
            }
        } else {
            System.out.println("Invalid input. Turn moves to the next player.");
        }
    }
		
		
	
		
		
		/*// Create some Player instances
        Player player1 = new Player(1, "Alice", 0, 0);
        Player player2 = new Player(2, "Bob", 0, 0);
        Player player3 = new Player(3, "Charlie", 0, 0);

       
        
        // Demonstrate Player functionality
        System.out.println("Contestant 1's name: " + player1.getName());
        player1.addRoundTotal(5);
        System.out.println("Contestant 1's round total after adding 5: " + player1.getRoundTotal());
        player1.incrementGrandTotal();
        System.out.println("Contestant 1's grand total after increment: " + player1.getGrandTotal());

        // Create a PlayerQueue and enqueue players
        PlayerQueue playerQueue = new PlayerQueue();
        playerQueue.enqueue(player1);
        playerQueue.enqueue(player2);
        playerQueue.enqueue(player3);
        
        // Process and restore using a lambda expression
        playerQueue.processAndRestore(player -> {
            System.out.println("\nProcessing Contestant: " + player.getName() + " with grand total: " + player.getGrandTotal());
            // Here you can add additional processing logic
        });

        System.out.println("\nFinished processing and restoring all players.");
    
        
        // Display the queue size
        System.out.println("\nQueue size after enqueueing 3 Contestant: " + playerQueue.size());

        // Peek at the queue
        try {
            Player peekedPlayer = playerQueue.queueFront();
            System.out.println("\nPeeked at queue, Contestant name: " + peekedPlayer.getName());
        } catch (NoSuchElementException e) {
            System.err.println("Failed to peek, the queue is empty.");
        }

        // Dequeue and display names
        try {
            while (!playerQueue.isEmpty()) {
                Player dequeuedPlayer = playerQueue.dequeue();
                System.out.println("Dequeued Contestant: " + dequeuedPlayer.getName());
            }
        } catch (NoSuchElementException e) {
            System.err.println("Failed to dequeue, the queue is empty.");
        }

        // Try to peek or dequeue from empty queue to demonstrate exception handling
        try {
            playerQueue.queueFront();
        } catch (NoSuchElementException e) {
            System.err.println("\nCannot peek, queue is empty.");
        }

        try {
            playerQueue.dequeue();
        } catch (NoSuchElementException e) {
            System.err.println("Cannot dequeue, queue is empty.");
        }
    }*/


}
