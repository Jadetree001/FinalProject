package hkk_DsGroupProjectSem2_23;

public class CardNode {
	private Card card;
    private CardNode next;

    public CardNode() {
        card = new Card();
        next = null;
    }
    
    public CardNode(Card card) {
        this.card = card;
        this.next = null;
    }

    public CardNode(Card card, CardNode next) {
        this.card = card;
        this.next = next;
    }

    public CardNode(CardNode node) {
        this.card = node.card;
        this.next = node.next;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public CardNode getNext() {
        return next;
    }

    public void setNext(CardNode next) {
        this.next = next;
    }

}
