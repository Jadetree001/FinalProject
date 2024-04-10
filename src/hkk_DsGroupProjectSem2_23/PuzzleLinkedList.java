/* 
 * Hueland Hunter - 2006702
*/
package hkk_DsGroupProjectSem2_23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuzzleLinkedList {
	
	private PuzzleNode head;
    private PuzzleNode tail;

    //Default Constructor
    public PuzzleLinkedList() {
        head = null;
        tail = null;
    }
    
    // Method to read categories and puzzles from a file
    public void readPuzzlesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String category = parts[0].trim();
                    String puzzleText = parts[1].trim();
                    Puzzle puzzle = new Puzzle(category, puzzleText);
                    addPuzzleNode(new PuzzleNode(puzzle));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading puzzles from file: " + e.getMessage());
        }
    }

   

    // Method to add a puzzle node to the linked list
    public void addPuzzleNode(PuzzleNode puzzle) {
    	PuzzleNode newNode = new PuzzleNode(puzzle);
    	if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    
    // Method to display all puzzles in the linked list
    public void displayPuzzles() {
        PuzzleNode current = head;
        while (current != null) {
            System.out.println("Category: " + current.getPuzzle().getCategory() + ", Puzzle: " + current.getPuzzle().getPuzzleText());
            current = current.getNext();
        }
           
    }
    
    // Method to display masked versions of all puzzles in the linked list
    public void displayMaskedPuzzles() {
        PuzzleNode current = head;
        while (current != null) {
            System.out.println("Category: " + current.getPuzzle().getCategory() + ", Masked Puzzle: " + current.getPuzzle().getMaskedPuzzleText());
            current = current.getNext();
        }
    }

    // Method to get a random puzzle from the linked list
    public Puzzle getRandomPuzzle() {
        List<Puzzle> categoryPuzzles = new ArrayList<>();
        PuzzleNode current = head;

        while (current != null) {
            
            categoryPuzzles.add(current.getPuzzle());
            current = current.getNext();
        }
        if (categoryPuzzles.isEmpty()) {
            return null; // No puzzles found in the category
        }
        
        Random random = new Random();
        int randomIndex = random.nextInt(categoryPuzzles.size());
        return categoryPuzzles.get(randomIndex);
    }



	public PuzzleNode getHead() {
		return head;
	}

}
