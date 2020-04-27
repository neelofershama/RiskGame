package App_Risk_Game.src.main.java.Model.Players;

import App_Risk_Game.src.main.java.Common.BehaviourStrategies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Player class representing the players and their possessions.
 *
 * @author Mutesham
 * @version 1.0
 */

public class Player {
    /**
     * Stores the player's name
     */
    private String player_name;

    /**
     * Stores the player's profile type
     */
    private BehaviourStrategies player_type;

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
    public HashMap<String,Integer> territories = new HashMap<>();

    // Stores player behavior
    private String player_behavior_type;

    /**
     * Store the players properties`
     */
    private Map properties;

    // To store player behavior type
    private String behavior_types;

    // Adding two fields continents owned and percentage of map owned
    private List<String> continents_owned = new ArrayList<>();
    private String map_owned;
    private float map_controlled;

    /**
     * Interface defining the player behaviour strategy
     */
    PlayerBehaviour player_behaviour;

    /**
     * Creates a new player
     */
    public Player() {
        //new player creation
    }

    /**
     * Creates a player with a specific name and Id
     *
     * @param name A string to set name of the player
     * @param id   A Integer used for identifying a player
     */
    public Player(String name, int id, PlayerBehaviour behaviour) {
        this.player_name = name;
        this.player_id = id;
        setPlayerBehaviour(behaviour);

    }

    public Player(String name, int id) {
        this.player_name = name;
        this.player_id = id;

    }

    public void setBehaviorType(String behavior_types){
        this.behavior_types = behavior_types;
    }

    public String getBehaviorType(){
        return behavior_types;
    }

    public String getMap_owned() {
        return map_owned;
    }

    public void setMap_owned(String map_owned) {
        this.map_owned = map_owned;
    }

    public void setBehaviorType(String behavior_type) {
        this.player_behavior_type = behavior_type;
    }

    public String getBehaviorType() {
        return this.player_behavior_type;
    }

    private void setPlayerBehaviour(PlayerBehaviour behaviour) {
        player_behaviour = behaviour;
        identifyPlayerBehaviorStrategy(player_behaviour);
    }

    /**
     * Constructor for player with all attributes
     *
     * @param player_name
     * @param player_color
     * @param player_id
     * @param territories
     */
    public Player(String player_name, String player_color, int player_id, HashMap<String, Integer> territories, List<String> continents_owned, String map_owned) {
        this.player_name = player_name;
        this.player_color = player_color;
        this.player_id = player_id;
        this.territories = territories;
        this.continents_owned = continents_owned;
        this.map_owned = map_owned;
    }
    /**
     * gets the player Id
     *
     * @return player Id
     */
    public int getId() {
        return player_id;
    }

    /**
     * sets the player Id
     *
     * @param id A integer for a player Id
     * @return player Id
     */
    public void setId(int id) {
        this.player_id = id;
    }

    /**
     * Sets a player name
     *
     * @param name A string for player name
     */

    public void setName(String name) {
        this.player_name = name;
    }

    /**
     * Gets a player's name
     *
     * @return player name
     */
    public String getName() {
        return player_name;
    }

    /**
     * Sets player color
     *
     * @param color A color of a particular player
     */
    public void setColor(String color) {
        this.player_color = color;
    }

    /**
     * Gets the players color
     *
     * @return color of the player
     */
    public String getColor() {
        return player_color;
    }

    /**
     * Setter method to assign player type
     *
     * @param type A string representing the player type
     */
    public void setType(BehaviourStrategies type) {
        this.player_type = type;
    }

    /**
     * Getter method to get the player type
     *
     * @return player type
     */
    public BehaviourStrategies getType() {
        return player_type;
    }

    public void setTerritories(HashMap<String, Integer> t) {
        territories = t;
    }

    public HashMap<String, Integer> getTerritories() {
        return territories;
    }

    public void setContinents_owned(List<String> continents) {
        this.continents_owned = continents;
        // continents_owned.add(continents);
    }

    public List<String> getContinents_owned() {
        return continents_owned;
    }

    public void setTerritory(String t, int i) {
        territories.put(t, i);
    }

    public void reinforce() throws IOException {
        player_behaviour.reinforce(this);
    }

    public void attack(){
        player_behaviour.attack();
    }

    public boolean fortify(){
        return player_behaviour.fortify();
    }

    private void identifyPlayerBehaviorStrategy(PlayerBehaviour name) {
        String v = name.getClass().toString().split("@")[0].split("\\ ")[1].split("\\.")[6];
        switch (v) {
            case "HumanPlayer":
                player_type = BehaviourStrategies.HumanPlayer;
                break;
            case "AggressivePlayer":
                player_type = BehaviourStrategies.AggressivePlayer;
                break;
            case "CheaterPlayer":
                player_type = BehaviourStrategies.CheaterPlayer;
                break;
            case "ConservativePlayer":
                player_type = BehaviourStrategies.ConservativePlayer;

                break;
            case "RandomPlayer":
                player_type = BehaviourStrategies.RandomPlayer;
                break;
        }

    }
}
