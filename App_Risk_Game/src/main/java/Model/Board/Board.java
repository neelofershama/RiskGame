package Model.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Board class representing the board and its properties
 * 
 * @author pavankrishna
 * @ version 1.0
 *
 */
public class Board {
	
	/**
	 * Store the tiles of the board
	 */
	private HashMap<String, Tile> tiles;
	/**
	 * Store the width of the board
	 */
	private int width;
	/**
	 * Store the height of the board
	 */
	private int height;
	/**
	 * Store the tiles along with location on board
	 */
	private List<ArrayList<Tile>> board;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * Creates board and fill each location with tile 
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		board = new ArrayList<ArrayList<Tile>>(width);
		tiles = new HashMap<String, Tile>();
		for(int w=0; w<width; w++) {
			board.add(w, new ArrayList<Tile>(height));
		}
	}
	
	
	/**
	 * 
	 * @param tile_name
	 * @param x
	 * @param y
	 * 
	 * Creates tile with name and position associated with it, in the board
	 */
	public void createTile(String tile_name, int x, int y) {

	if(!tiles.containsKey(tile_name)) {
			Tile tile = new Tile(tile_name);
			tile.setXCoordinate(x);
			tile.setYCoordinate(y);
			tiles.put(tile_name, tile);
		}
	}
	
	private Tile getTile(String tile_name) {
		if(tiles.containsKey(tile_name)) {
			return tiles.get(tile_name);
		}
		return null;
	}
	
	/**
	 * 
	 * @param tile_name
	 * Used to remove the tile from the board
	 */
	public void removeTile(String tile_name) {
		if(tiles.containsKey(tile_name)) {
			tiles.remove(tile_name);
		}
	}
	
	/**
	 * 
	 * @param player_owned
	 * @param tileName
	 * Associates a player with a tile
	 */
	public void setPlayer(String player_owned, String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			tile.setPlayer(player_owned);
		}
	}
	
	/**
	 * 
	 * @param tileName
	 * @return Player associated with a tile
	 */
	public String getPlayer(String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			return tile.getPlayer();
		}
		return null;
	}
	
	/**
	 * 
	 * @param tileName
	 * @return coordinates of a tile
	 */
	public ArrayList<Integer> getCoordinates(String tile_name) {
		ArrayList<Integer> coordinates = new ArrayList<Integer>();
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			coordinates.add(tile.getXCoordinate());
			coordinates.add(tile.getYCoordinate());
			return coordinates;
		}
		return null;
	}
	
	/**
	 * 
	 * @param neighbour_tile
	 * @param tile_name
	 * Associates a list of neighboring tiles to the tile
	 */
	public void setNeighbourTile(List<String> neighbour_tile, String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			List<Tile> tile_list = new ArrayList<Tile>(); //Tile Neighbors
			List<Tile> t_list = new ArrayList<Tile>(); // Neighbor neighbor update 
			for(int i=0; i<neighbour_tile.size(); i++) {
				String neighbour = neighbour_tile.get(i);
				if(tiles.containsKey(neighbour)) {
					Tile t = tiles.get(neighbour);
					t_list.add(tiles.get(tile_name));
					t.setNeighbourTile((ArrayList<Tile>) t_list);
					tile_list.add(t);
				}	
			}
			tile.setNeighbourTile((ArrayList<Tile>) tile_list);
		}
	}
	
	/**
	 * 
	 * @param tileName
	 * @return List of neighbors surrounding the tile
	 */
	public List<String> getNeighbourTile(String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			return tile.getNeighbourTile();
		}
		return null;
	}
	
	/**
	 * 
	 * @param u
	 * @param tileName
	 * Associates unit to the tile
	 */
	public void addUnit(Unit u, String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			tile.addUnit(u);
		}
	}
	
	/**
	 * 
	 * @param u
	 * @param tileName
	 * Removes unit associated to the tile
	 */
	public void removeUnit(Unit u, String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			tile.removeUnit(u);
		}
	}
	
	/**
	 * List of units associated with the tile
	 */
	public ArrayList<Unit> getAllUnits(String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			return (ArrayList<Unit>) tile.getAllUnits();
		}
		return null;
	}
	
	/**
	 * 
	 * @param type
	 * @param tile_name
	 * Used to associate a type (land, water, etc..) to the tile
	 */
	public void setTileType(String type, String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			tile.setTileType(type);
		}
		
	}
	
	/**
	 * 
	 * @param tile_name
	 * @return Type of the tile
	 */
	public String getTileType(String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			return tile.getTileType();
		}
		return null;
	}
	
	/**
	 * 
	 * @param tile_value
	 * @param tile_name
	 * Associates a value to the tile
	 */
	public void setTileValue(int tile_value, String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			tile.setTileValue(tile_value);
		}
	}
	
	/**
	 * 
	 * @param tile_name
	 * @return Value associated with the tile
	 */
	public int getTileValue(String tile_name) {
		Tile tile = this.getTile(tile_name);
		if(tile!=null) {
			return tile.getTileValue();
		}
		return 0;
	}
}
 
