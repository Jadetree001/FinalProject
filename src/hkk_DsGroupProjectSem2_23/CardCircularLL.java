package hkk_DsGroupProjectSem2_23;

public class CardCircularLL {
	
	private CardNode head;
    private CardNode tail;
    private int size;

    public CardCircularLL() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

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
        size++;
    }

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
        size--;
        return removedCard;
    }

    public CardNode getHead() {
        return head;
    }

}
