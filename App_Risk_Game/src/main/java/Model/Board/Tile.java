package src.main.java.Model.Board;

import java.util.ArrayList;
import java.util.List;

import src.main.java.Model.Units.Unit;

/**
 * Represent tile of a board
 * @author pavankrishna
 *
 */

public class Tile {
	/**
	 * Stores the name of the tile
	 */
	private String tile_name;
	/**
	 * Stores the player to whom the tile belongs
	 */
	private String player_owned;
	/**
	 * Stores the x-coordinate of the tile
	 */
	private int x;
	/**
	 * Stores the y-coordinate of the tile
	 */
	private int y;
	/**
	 * Stores the neighboring tiles of the tile
	 */
	private ArrayList<Tile> neighbour_tile;
	/**
	 * Stores the unit that will be on the tile
	 */
	private ArrayList<Unit> unit;
	/**
	 * Stores the tile type like land, water, etc...
	 */
	private String tile_type; // Land, Sea etc...
	/**
	 * Store the value of the tile
	 */
	private int tile_value=0;
	
	/**
	 * 
	 * @param tile_name
	 * Update the tile name
	 */
	protected Tile(String tile_name) {
		this.tile_name = tile_name;
		neighbour_tile = new ArrayList<Tile>();
		unit = new ArrayList<Unit>();
	}
	
	/**
	 * 
	 * @param tile_type
	 * Associates a type to the tile
	 */
	protected void setTileType(String tile_type) {
		this.tile_type = tile_type;
	}
	
	/**
	 * 
	 * @return Type of the tile
	 */
	protected String getTileType() {
		return tile_type;
	}
	
	/**
	 * 
	 * @param x
	 * Associate x-coordinate of the tile
	 */
	protected void setXCoordinate(int x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @param y
	 * Associate y-coordinate of the tile
	 */
	protected void setYCoordinate(int y) {
		this.y = y;
	}
	
	/**
	 * 
	 * @return x-coordinate of the tile
	 */
	protected int getXCoordinate() {
		return x;
	}
	
	/**
	 * 
	 * @return y-coordinate of the tile
	 */
	protected int getYCoordinate() {
		return y;
	}
	
	/**
	 * 
	 * @param neighbourTile
	 * Associate neighboring tiles to the tile
	 */
	protected void setNeighbourTile(ArrayList<Tile> neighbourTile) {
		for(int i=0; i<neighbourTile.size(); i++) {
			Tile tile = neighbourTile.get(i);
			neighbourTile.add(tile);
		}
	}
	/**
	 * 
	 * @return List of neighboring tiles to the tile
	 */
	protected ArrayList<Tile> getNeighbourTile(){
		return neighbour_tile;
	}
	/**
	 * 
	 * @param u
	 * Associates a unit to the tile
	 */
	protected void addUnit(Unit u) {
		if(!unit.contains(u))
			unit.add(u);
	}
	/**
	 * 
	 * @param u
	 * Removes the unit from the tile
	 */
	protected void removeUnit(Unit u) {
		if(unit.contains(u)) {
			unit.remove(u);
		}
	}
	/**
	 * 
	 * @return List of units associated to the tile
	 */
	protected List<Unit> getAllUnits() {
		return unit;
	}
	
	/**
	 * 
	 * @return Name of the tile
	 */
	protected String getTileName() {
		return tile_name;
	}
	
	/**
	 * 
	 * @param player_owned
	 * Associates a player to the tile
	 */
	protected void setPlayer(String player_owned) {
		this.player_owned = player_owned;
	}
	
	/**
	 * 
	 * @return Player associated with the tile
	 */
	protected String getPlayer() {
		return player_owned;
	}
	
	/**
	 * 
	 * @param tile_value
	 * Associate a value to the tile
	 */
	protected void setTileValue(int tile_value) {
		this.tile_value = tile_value;
	}
	
	/**
	 * 
	 * @return Value associated with the tile
	 */
	protected int getTileValue() {
		return tile_value;
	}
}
