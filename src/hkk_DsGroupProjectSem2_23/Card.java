/*
 * Kevon Bennett - 2101075
*/

package hkk_DsGroupProjectSem2_23;

public class Card {
	
	// Enum for different types of cards
    public enum CardType {
        MONEY,
        LOSE_A_TURN,
        BANKRUPTCY, FREE_PLAY
    }

    private CardType type;
    private int value;

    // Default constructor
    public Card() {
        type = CardType.MONEY;
        value = 0;
    }
    
    // Primary constructor
    public Card(CardType type, int value) {
        this.type = type;
        this.value = value;
    }
    
    

    // Setters / Getters 
    public void setType(CardType type) {
		this.type = type;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public CardType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}



