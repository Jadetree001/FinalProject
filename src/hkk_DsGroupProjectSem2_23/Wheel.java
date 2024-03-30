package hkk_DsGroupProjectSem2_23;

import java.util.Random;

public class Wheel {
	private CardCircularLL cards;
    private CardNode currentCard;
    private Puzzle currentPuzzle;

    public Wheel() {
        currentPuzzle = new Puzzle();
        cards = new CardCircularLL();
        initializeWheel();
        currentCard = null; // Initially, no card is selected
    }
    
    public Puzzle getCurrentPuzzle() {
        return currentPuzzle;
    }

    public void setCurrentPuzzle(Puzzle puzzle) {
        this.currentPuzzle = puzzle;
    }
    

    public CardCircularLL getCards() {
		return cards;
	}



	public void setCards(CardCircularLL cards) {
		this.cards = cards;
	}



	public CardNode getCurrentCard() {
		return currentCard;
	}



	public void setCurrentCard(CardNode currentCard) {
		this.currentCard = currentCard;
	}



	public void initializeWheel() {
        // Add 25 cards to the wheel
        cards.add(new Card(Card.CardType.MONEY, 500)); // $500
        cards.add(new Card(Card.CardType.MONEY, 1000)); // $1000
        cards.add(new Card(Card.CardType.MONEY, 1500)); // $1500
        cards.add(new Card(Card.CardType.MONEY, 2000)); // $2000
        cards.add(new Card(Card.CardType.MONEY, 2500)); // $2500
        cards.add(new Card(Card.CardType.BANKRUPTCY, 0)); // Bankruptcy
        cards.add(new Card(Card.CardType.LOSE_A_TURN, 0)); // Lose a Turn
        cards.add(new Card(Card.CardType.FREE_PLAY, 850)); // Free Play: $850
        cards.add(new Card(Card.CardType.MONEY, 500)); // $500
        cards.add(new Card(Card.CardType.MONEY, 1000)); // $1000
        cards.add(new Card(Card.CardType.MONEY, 1500)); // $1500
        cards.add(new Card(Card.CardType.MONEY, 2000)); // $2000
        cards.add(new Card(Card.CardType.MONEY, 2500)); // $2500
        cards.add(new Card(Card.CardType.BANKRUPTCY, 0)); // Bankruptcy
        cards.add(new Card(Card.CardType.LOSE_A_TURN, 0)); // Lose a Turn
        cards.add(new Card(Card.CardType.MONEY, 500)); // $500
        cards.add(new Card(Card.CardType.MONEY, 1000)); // $1000
        cards.add(new Card(Card.CardType.MONEY, 1500)); // $1500
        cards.add(new Card(Card.CardType.MONEY, 2000)); // $2000
        cards.add(new Card(Card.CardType.MONEY, 2500)); // $2500
        cards.add(new Card(Card.CardType.BANKRUPTCY, 0)); // Bankruptcy
        cards.add(new Card(Card.CardType.MONEY, 500)); // $500
        cards.add(new Card(Card.CardType.MONEY, 1000)); // $1000
        cards.add(new Card(Card.CardType.MONEY, 500)); // $500
        cards.add(new Card(Card.CardType.MONEY, 1000)); // $1000
        
    }

	public Card spin() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Wheel is empty. Cannot spin.");
        }
        Random random = new Random();
        int spinCount = random.nextInt(51) + 50; // Random number between 50 and 100
        
        
        System.out.println("Spin count: " + spinCount);
        
        for (int i = 0; i < spinCount; i++) {
            currentCard = currentCard != null ? currentCard.getNext() : cards.getHead();
        }
        return currentCard.getCard();
    }

}
