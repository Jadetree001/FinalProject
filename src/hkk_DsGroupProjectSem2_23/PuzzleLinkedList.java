package hkk_DsGroupProjectSem2_23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleLinkedList {
	
	private PuzzleNode head;
    private PuzzleNode tail;

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

    
    public void displayPuzzles() {
        PuzzleNode current = head;
        while (current != null) {
            System.out.println("Category: " + current.getPuzzle().getCategory() + ", Puzzle: " + current.getPuzzle().getPuzzleText());
            current = current.getNext();
        }
           
    }
    
    public void displayMaskedPuzzles() {
        PuzzleNode current = head;
        while (current != null) {
            System.out.println("Category: " + current.getPuzzle().getCategory() + ", Masked Puzzle: " + current.getPuzzle().getMaskedPuzzleText());
            current = current.getNext();
        }
    }

    public Puzzle getRandomPuzzle(String category) {
        List<Puzzle> categoryPuzzles = new ArrayList<>();
        PuzzleNode current = head;
        while (current != null) {
            if (current.getPuzzle().getCategory().equals(category)) {
                categoryPuzzles.add(current.getPuzzle());
            }
            current = current.getNext();
        }
        if (categoryPuzzles.isEmpty()) {
            return null; // No puzzles found in the category
        }
        int randomIndex = (int) (Math.random() * categoryPuzzles.size());
        return categoryPuzzles.get(randomIndex);
    }



	public PuzzleNode getHead() {
		return head;
	}

}
