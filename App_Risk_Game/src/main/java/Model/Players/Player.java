package src.main.java.Model.Players;

import java.util.List;
import java.util.Map;
/**
 * Player class representing the players and their possessions. 
 * 
 * @author Mutesham
 * @version 1.0
 *
 */

public class Player {
	  	/**
	  	 * Stores the player's name
	  	 */
		private String player_name;
		
		/**
	     * Stores the player's profile type
	     */
		private String player_type;
	
		/**
	     * Stores the player's profile type
	     */
		private String player_color;
		
		/**
	     * Stores the player's Id
	     */ 
		private int player_id;
			    
	    /**
	     * Store the list of players
	     */
		private List players;
		
		/**
	     * Store the players properties
	     */
	    private Map properties;
	    
	    /**
	     *  Creates a new player
	     */
	    public Player() {
	    	//new player creation 
	    }
	    
	    /**
	     * Creates a player with a specific name and Id
	     * @param name A string to set name of the player
	     * @param id A Integer used for identifying a player
	     */
	    public Player(String name,int id) {
	        this.player_name=name;
	    	this.player_id = id;
	        
	    }

	    /**
	     * gets the player Id
	     * @return player Id
	     */
	    public int getId() {
	        return player_id;
	    }
	    /**
	     * sets the player Id
	     * @param id A integer for a player Id
	     * @return player Id
	     */
	    public void setId(int id) {
	    	this.player_id=id;
	    }
	    
	    /**
	     * Sets a player name 
	     * @param name A string for player name
	     */
	    
	    public void setName(String name)
	    {
	        this.player_name = name;
	    }
	    
	    /**
	     * Gets a player's name
	     * @return player name 
	     */
	    public String getName()
	    {
	        return player_name;
	    }
	    
	    /**
	     * Sets player color 
	     * @param color A color of a particular player
	     */
	    public void setColor(String color)
	    {
	        this.player_color = color;
	    }
	    
	    /**
	     * Gets the players color
	     * @return color of the player
	     */
	    public String getColor()
	    {
	        return player_color;
	    }

	    /**
	     * Setter method to assign player type
	     * @param type A string representing the player type
	     */
	    public void setType(String type)
	    {
	        this.player_type = type;
	    }

	    /**
	     * Getter method to get the player type
	     * @return player type
	     */
	    public String getType()
	    {    
	        return player_type;
	    }
	    
	    /**
	     * Setter method to assign the number of troops
	     * @param troops number of troops
	     */
	    public void setTroops(int troops) {
	        this.troops = troops;
	    }
	    
	    /**
	     * Getter method to get the number of troops
	     * @return player's number of troops
	     */
	    public int getTroops() {
	        return troops;
	    }

}