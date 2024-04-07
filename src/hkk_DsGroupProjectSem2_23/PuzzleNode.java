/* 
 * Hueland Hunter - 2006702
*/
package hkk_DsGroupProjectSem2_23;

public class PuzzleNode {
	
	private Puzzle puzzle;
    private PuzzleNode next;

    //Default
    public PuzzleNode() {
        this.puzzle = new Puzzle();
        this.next = null;
    }

    //Primary 1
    public PuzzleNode(Puzzle puzzle, PuzzleNode next) {
        this.puzzle = puzzle;
        this.next = next;
    }
    
    //Primary 2
    public PuzzleNode(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.next = null;
    }

    //Copy
    public PuzzleNode(PuzzleNode node) {
    	this.puzzle = node.puzzle;
        this.next = node.next;
    }

    //Setters / Getters
    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public PuzzleNode getNext() {
        return next;
    }

    public void setNext(PuzzleNode next) {
        this.next = next;
    }

}
