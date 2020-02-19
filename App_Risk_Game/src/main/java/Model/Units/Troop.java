package Model.Units;
import java.util.List;

public class Troop {

	String color;
	int no_of_troops;
	List<String> types_of_troops;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNo_of_troops() {
		return no_of_troops;
	}
	public void setNo_of_troops(int no_of_troops) {
		this.no_of_troops = no_of_troops;
	}
	public List<String> getTypes_of_troops() {
		return types_of_troops;
	}
	public void setTypes_of_troops(List<String> types_of_troops) {
		this.types_of_troops = types_of_troops;
	}
	public Troop(String color, int no_of_troops, List<String> types_of_troops) {
		super();
		this.color = color;
		this.no_of_troops = no_of_troops;
		this.types_of_troops = types_of_troops;
	}
	
}
