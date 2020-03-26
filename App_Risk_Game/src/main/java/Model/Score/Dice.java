package App_Risk_Game.src.main.java.Model.Score;

import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * 
 * @author yashwanth
 * Represents the dice rolls.
 */
public class Dice {
	
	/**
	 * Store of number of dices to roll.
	 */
	public static int number_of_dice;
	/**
	 * Store the result of dice roll.
	 */
	public static List<Integer> dice_value;
	/**
	 * Store the java random generation object
	 */
	public static Random rand = new Random();
	
	/**
	 * 
	 * @param numberofdice
	 * Get the number of dices to roll
	 */
	public static void setNoOfDice(int numberofdice) {
		number_of_dice = numberofdice;
		//dice_value = new ArrayList<Integer>(number_of_dice);

	}
	
	/**
	 * 
	 * @return List of dice roll results
	 */
	public static List getScore() {
		for(int i=0; i<number_of_dice; i++) {
			dice_value.add(i, rand.nextInt(6)+1);
		}
		return dice_value;
	}

}
