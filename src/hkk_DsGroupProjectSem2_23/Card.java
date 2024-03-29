package hkk_DsGroupProjectSem2_23;

public class Card {
	
	public enum CardType {
        MONEY,
        LOSE_A_TURN,
        BANKRUPTCY, FREE_PLAY
    }

    private CardType type;
    private int value;

    public Card() {
        type = CardType.MONEY;
        value = 0;
    }
    
    
    public Card(CardType type, int value) {
        this.type = type;
        this.value = value;
    }
    
    

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



