package App_Risk_Game.src.main.java.Model.Units;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * contains the units i.e the troops in the risk game or money in monopoly.
 * each unit has three attributes : color, no_of_units and types_of_units.
 */

public class Unit {

	String color;
	int no_of_units;
	List<UnitType> types_of_units;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNo_of_units() {
		return no_of_units;
	}
	public void setNo_of_units(int no_of_units) {
		this.no_of_units = no_of_units;
	}
	public List<UnitType> getTypes_of_units() {
		return types_of_units;
	}
	public void setTypes_of_units(List<UnitType> types_of_units) {
		this.types_of_units = types_of_units;
	}
	public Unit(String color, int no_of_units, List<UnitType> types_of_units) {
		super();
		this.color = color;
		this.no_of_units = no_of_units;
		this.types_of_units = types_of_units;
	}
	
	@Override
	public String toString() {
		return "Unit [color=" + color + ", no_of_units=" + no_of_units
				+ ", types_of_units=" + types_of_units + "]";
	}
	
	/**
	 * initializes the unit module at the beginning of the game with the default units
	 * and unit values.
	 * @param list
	 * @param prop
	 * @return playerList
	 */
	
	public static LinkedList<Unit> initialize(HashMap<String, String> list, Properties prop ) {

		LinkedList<Unit> playerList = new LinkedList<Unit>();
		Iterator it = list.keySet().iterator();
		
		while (it.hasNext()) {

			String player = (String) it.next();
			List<UnitType> types_of_units = new LinkedList();
			UnitType ut= new UnitType(prop.getProperty("basicUnit"),Integer.parseInt(prop.getProperty("initialUnitValue")),Integer.parseInt(prop.getProperty("basicUnitMax")));
			types_of_units.add(ut);
			Unit unitObj = new Unit(list.get(player).toUpperCase(), Integer.parseInt(prop.getProperty("initialUnitValue")),
					types_of_units);
			playerList.add(unitObj);

		}
		return playerList;

	}
}

