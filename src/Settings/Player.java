package Settings;

public class Player{

	private String PlayerName;
	private int PlayerID;
	private int Points;

	// Constructor
	public Player(String name, int id){
		PlayerName = name;
		PlayerID = id;
		Points = 0;
	}

	// Get Functions
	public String get_PlayerName(){
		return PlayerName;
	}

	public int get_PlayerID(){
		return PlayerID;
	}

	public int get_Points(){
		return Points;
	}


	// Points modifications
	public void add_Points(int x){
		this.Points += x;
	}

	public void reset_Points(){
		this.Points = 0;
	}


}
