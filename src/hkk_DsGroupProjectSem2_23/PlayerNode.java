package hkk_DsGroupProjectSem2_23;

public class PlayerNode {
	private Player data;
	private PlayerNode next;
	private PlayerNode prev;
	
	//Default
	public PlayerNode() {
        data = new Player();
        next = null;
        prev = null;
    }
	
	//Primary 1
	public PlayerNode(Player data, PlayerNode next, PlayerNode prev) {
        this.data = data;
        this.next = next;
        this.next = prev;
    }
    
	//Primary 2
    public PlayerNode(Player data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    //Copy
    public PlayerNode(PlayerNode node) {
        this.data = node.data;
        this.next = node.next;
        this.prev = node.prev;
    }
    
    //Setters/Getters
	public Player getData() {
		return data;
	}

	public void setData(Player data) {
		this.data = data;
	}

	public PlayerNode getNext() {
		return next;
	}

	public void setNext(PlayerNode next) {
		this.next = next;
	}

	public PlayerNode getPrev() {
		return prev;
	}

	public void setPrev(PlayerNode prev) {
		this.prev = prev;
	}
	
	
	
}
