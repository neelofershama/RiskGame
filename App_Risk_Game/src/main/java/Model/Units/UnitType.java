package Model.Units;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

 /**
 * Each unit has a type for eg: in risk game, troops are of
 * three types : infantry, artillery and cavalry.
 * Each type has the no of units assigned to them and the maximum units possible.
 */

public class UnitType {

	String type;
	int count;
	int max_units;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMaxUnits() {
		return max_units;
	}
	public void setMaxUnits(int max_units) {
		this.max_units = max_units;
	}
	
	
}
