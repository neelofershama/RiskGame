import java.util.List;

/**
 * 
 * @author yashwanth
 * Represents Player's game attributes
 */

public class Score {
	/**
	 * Store the name of player
	 */
	private String player_name;
	/**
	 * Store the list of territories owned by the player
	 */
	private List<String> territories;
	/**
	 * Store the money owned by player
	 */
	private int money;
	/**
	 * Store the number of points owned by the player
	 */
	private int score;
	/**
	 * Store the list of troops owned by the player
	 */
	private List troops;
	
	public Score(String player_name) {
		this.player_name = player_name;
	}
	
	/**
	 * 
	 * @param territory
	 * Adds the territory to the list of player territories
	 */
	private void addTerritories(String territory) {
		if(!territories.contains(territory)) {
			territories.add(territory);
		}
	}
	
	/**
	 * 
	 * @return List of territories of the player
	 */
	private List<String> getTerritories(){
		return territories;
	}
	
	/**
	 * 
	 * @param money
	 * Set the money owned by the player
	 */
	private void setMoney(int money) {
		this.money = money;
	}
	
	/**
	 * 
	 * @return Money owned by the player
	 */
	private int getMoney() {
		return money;
	}
	
	private void updateMoney(int money) {
		this.money += money;
	}
	
	/**
	 * 
	 * @param points
	 * Set the points owned by the player
	 */
	private void setScore(int score ) {
		this.score = score;
	}
	
	/**
	 * 
	 * @return Points owned by the player
	 */
	private int getScore() {
		return score;
	}
	
	private void updateScore(int score) {
		
		this.score += score;
	}
	
	/**
	 * 
	 * @param troop
	 * Add a troop to the list of players troop
	 */
	private void addTroops(String troop) {
		if(!troops.contains(troop)) {
			troops.add(troop);
		}
	}
	
	/**
	 * 
	 * @return List of troops owned by the player
	 */
	private List<String> getTroops(){
		return troops;
	}
}
