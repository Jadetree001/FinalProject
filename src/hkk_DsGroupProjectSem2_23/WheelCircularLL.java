/*Hueland Hunter - 2006702 
 *Kevon Bennett - 2101075
*/
package hkk_DsGroupProjectSem2_23;

public class WheelCircularLL {
	
	private CardNode head;
    private CardNode tail;
    private int size;

    // Default constructor
    public WheelCircularLL() {
        head = null;
        tail = null;
        size = 0;
    }

    // Method to check if the linked list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to get the size of the linked list
    public int size() {
        return size;
    }

    // Method to add a card to the linked list
    public void add(Card card) {
    	CardNode newNode = new CardNode(card);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            tail.setNext(head); // Make the list circular
        } else {
            tail.setNext(newNode);
            tail = newNode;
            tail.setNext(head); // Make the list circular
        }
        size++; // Increment the size of the list
    }

    // Method to remove a card from the linked list
    public Card remove() {
        if (isEmpty()) {
            return null; // Return null if list is empty
        }
        Card removedCard = head.getCard();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            tail.setNext(head); // Update the tail's next pointer to maintain circularity
        }
        size--;// Decrement the size of the list
        return removedCard;// Return the removed card
    }

    // Method to get the head of the linked list
    public CardNode getHead() {
        return head;
    }

}
