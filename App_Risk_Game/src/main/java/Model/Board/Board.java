package App_Risk_Game.src.main.java.Model.Board;

import App_Risk_Game.src.interfaces.*;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Units.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Board class representing the board and its properties
 *
 * @author pavankrishna
 * @ version 1.0
 *
 */

public class Board  implements Observable {
	List<Observer> observers = new ArrayList<>();
	/**
	 * Store the tiles of the board
	 */
	public static HashMap<String, Tile> tiles = new HashMap<>();
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
	private List<ArrayList<Tile>> board = new ArrayList<>();
	HashMap<String, Integer> continents = new HashMap<String, Integer>();

    public Board() {
        // TODO Auto-generated constructor stub
    }


	public HashMap<String, Tile> getTiles() {
    	return tiles;
    }

    public void setTiles(HashMap<String, Tile> tiles) {
        this.tiles = tiles;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<ArrayList<Tile>> getBoard() {
        return board;
    }

    public void setBoard(List<ArrayList<Tile>> board) {
        this.board = board;
    }

    public HashMap<String, Integer> getContinents() {
        return continents;
    }

	public void setContinents(HashMap<String, Integer> continents) {
		this.continents = continents;
	}

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
	public void createTile(String tile_name, int x, int y, String continent) {
		if(!tiles.containsKey(tile_name)) {
			Tile tile = new Tile(tile_name);
			//board.get(x).add(y, tile);
			tile.setXCoordinate(x);
			tile.setYCoordinate(y);
			tile.setContinent(continent);
			tiles.put(tile_name, tile);
		}

	//	System.out.println(tiles.toString());
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
	public void setPlayer(String player_owned, String tileName) {

		//TODO-set player to tile
		if(tiles.containsKey(tileName)) {
			System.out.println(tileName);
			Tile tile = (Tile) tiles.get(tileName);

			tile.setPlayer(player_owned);
		}
		else {
			System.out.println("tileName");
		}
	}

	/**
	 *
	 * @param tileName
	 * @return Player associated with a tile
	 */
	public String getPlayer(String tileName) {
		String player_name = "";
		if(tiles.containsKey(tileName)) {
			Tile tile = tiles.get(tileName);
			player_name = tile.getPlayer();
		}
		return player_name;
	}

	/**
	 *
	 * @param tileName
	 * @return coordinates of a tile
	 */
	public ArrayList<Integer> getCoordinates(String tileName) {
		ArrayList<Integer> coordinates = new ArrayList<Integer>();
		if(tiles.containsKey(tileName)) {
			Tile tile = tiles.get(tileName);
			coordinates.add(tile.getXCoordinate());
			coordinates.add(tile.getYCoordinate());
		}
		return coordinates;
	}

	/**
	 *
	 * @param neighbour_tile
	 * @param tile_name
	 * Associates a list of neighboring tiles to the tile
	 */
	public void setNeighbourTile(List<String> neighbour_tile, String tile_name) {
		List<Tile> tile_list = new ArrayList<Tile>(); //Tile Neighbors
		List<Tile> t_list = new ArrayList<Tile>(); // Neighbor neighbor update
		Tile tile = tiles.get(tile_name);
		for(int i=0; i<neighbour_tile.size(); i++) {
			Tile t = tiles.get(neighbour_tile.get(i));
			t_list.add(tiles.get(tile_name));
			t.setNeighbourTile((ArrayList<Tile>) t_list);
			tile_list.add(tiles.get(neighbour_tile.get(i)));
		}
		tile.setNeighbourTile((ArrayList<Tile>) tile_list);
	}

	/**
	 *
	 * @param tileName
	 * @return List of neighbors surrounding the tile
	 */
	public List<String> getNeighbourTile(String tileName) {
		List<String> neighbour = new ArrayList<>();
		if(tiles.containsKey(tileName)) {
			neighbour =  tiles.get(tileName).getNeighbourTile();
		}
		return neighbour;
	}

	/**
	 *  @param u
	 * @param tileName
	 */
	public void addUnit(Unit u, String tileName) {
		if(tileName.contains(tileName)) {
			Tile tile = tiles.get(tileName);
			tile.addUnit(u);
		}
	}

	/**
	 *
	 * @param u
	 * @param tileName
	 * Removes unit associated to the tile
	 */
	public void removeUnit(Unit u, String tileName) {
		if(tileName.contains(tileName)) {
			Tile tile = tiles.get(tileName);
			tile.removeUnit(u);
		}
	}

	/**
	 * List of units associated with the tile
	 */
	public ArrayList<Unit> getAllUnits(String tileName) {
		List<Unit> units = new ArrayList<>();
		if(tileName.contains(tileName)) {
			Tile tile = tiles.get(tileName);
			units = (ArrayList<Unit>) tile.getAllUnits();
		}
		return (ArrayList<Unit>) units;
	}

	/**
	 *
	 * @param type
	 * @param tile_name
	 * Used to associate a type (land, water, etc..) to the tile
	 */
	public void setTileType(String type, String tile_name) {
		if(tile_name.contains(tile_name)) {
			Tile tile = tiles.get(tile_name);
			tile.setTileType(type);
		}

	}

	/**
	 *
	 * @param tile_name
	 * @return Type of the tile
	 */
	public String getTileType(String tile_name) {
		String tile_type = "";
		if(tile_name.contains(tile_name)) {
			Tile tile = tiles.get(tile_name);
			tile_type = tile.getTileType();
		}
		return tile_type;
	}

	/**
	 *
	 * @param tile_value
	 * @param tile_name
	 * Associates a value to the tile
	 */
	public void setTileValue(int tile_value, String tile_name) {
		if(tile_name.contains(tile_name)) {
			Tile tile = tiles.get(tile_name);
			tile.setTileValue(tile_value);
		}
	}

	/**
	 *
	 * @param tile_name
	 * @return Value associated with the tile
	 */
	public int getTileValue(String tile_name) {
		if(tile_name.contains(tile_name)) {
			Tile tile = tiles.get(tile_name);
			return tile.getTileValue();
		}
		return 0;
	}

	@Override
	public void attachObserver(App_Risk_Game.src.interfaces.Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void dettachObserver(App_Risk_Game.src.interfaces.Observer observer) {
this.observers.remove(observer);
	}

	@Override
	public  void notifyObserver(Observable observable) {
		for (Observer o:this.observers
		) {
			o.update(observable);
		}
	}

	public void notifyObservers() {
		notifyObserver(this);
	}
}
