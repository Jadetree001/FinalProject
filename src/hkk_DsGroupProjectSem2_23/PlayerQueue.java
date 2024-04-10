/*
 * Kimar McDonald-Morgan - 1403238
*/
package hkk_DsGroupProjectSem2_23;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class PlayerQueue {
	
	private PlayerNode front;
    private PlayerNode rear;
    private int size;
    
    //Default
    public PlayerQueue() {
        front = null;
        rear = null;
        size = 0;
    }
    
    
    //Setters and Getters
    public PlayerNode getFront() {
		return front;
	}



	public void setFront(PlayerNode front) {
		this.front = front;
	}



	public PlayerNode getRear() {
		return rear;
	}



	public void setRear(PlayerNode rear) {
		this.rear = rear;
	}



	// Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Method to get the size of the queue
    public int size() {
        return size;
    }
    
    // Method to add a player to the end of the queue
    public void enqueue(Player data) {
    	PlayerNode newNode = new PlayerNode(data); // Correctly initialize with player data
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            newNode.setPrev(rear);
            rear = newNode;
        }
        size++; // This should be outside the else block to increment size whether the queue was empty or not
    }
    
    // Method to remove and return the player at the front of the queue
    public Player dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Player data = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }
    
    // Method to return the player at the front of the queue without removing it
    public Player queueFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.getData();
    }
    
 // New method to process each player and then restore the queue
    public void processAndRestore(Consumer<Player> processor) {
        PlayerQueue temporaryQueue = new PlayerQueue();
        
        // Process each element and enqueue it to the temporary queue
        while (!this.isEmpty()) {
            Player player = this.dequeue();
            processor.accept(player); // Process player with the provided processor function
            temporaryQueue.enqueue(player);
        }
        
        // Restore the original queue from the temporary queue
        while (!temporaryQueue.isEmpty()) {
            this.enqueue(temporaryQueue.dequeue());
        }
    }
    

}
