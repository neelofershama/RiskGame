package App_Risk_Game.src.main.java.Model.Score;


import App_Risk_Game.src.interfaces.Observer;

import java.util.*;

/**
 *
 * Represents the dice rolls.
 */
public class Dice implements App_Risk_Game.src.interfaces.Observable {

	List<Observer> observers = new ArrayList<>();
	/**
	 * Store of number of dices to roll.
	 */
	private int number_of_dice;
	/**
	 * Store the result of dice roll.
	 */
	public List<Integer> dice_value;

	/**
	 * Store the result of dice roll.
	 */
	public List<Integer> dice_value1;
	/**
	 * Store the java random generation object
	 */
	private Random rand = new Random();

	public  List<Integer> sorted_dice_values;


	/**
	 * @param number_of_dice Get the number of dices to roll
	 */
	private void setNoOfDice(int number_of_dice) {
		this.number_of_dice = number_of_dice;
		//dice_value = new ArrayList<Integer>(number_of_dice);
		getScore(number_of_dice);
	}

	/**
	 * @param number_of_dice
	 * @return List of dice roll results
	 */
	public List getScore(int number_of_dice) {
		int max = 6;
		int min = 1;
		int range = max;
		Integer Dice[] = new Integer[number_of_dice];
		for (int a = 0; a < number_of_dice; a++) {
			Dice[a] = (int) (Math.random() * range) + min;
		}
		 Arrays.sort(Dice, Collections.reverseOrder());
		sorted_dice_values =Arrays.asList(Dice);
		return sorted_dice_values;
	}

	//return list containing troops lost by attacker followed by defence
	public  List<Integer> rollDice(int m, int n) {
		dice_value = getScore(m);
		dice_value1 = getScore(n);
		List<Integer> result = new ArrayList<>();
		int nooftroopslostbyp1 = 0;
		int nooftroopslostbyp2 = 0;
		for (int i = 0; i < dice_value1.size(); i++) {
			if (dice_value.get(i) > dice_value1.get(i)) {
				nooftroopslostbyp2++;
			} else nooftroopslostbyp1++;
		}
		result.add(nooftroopslostbyp1);
		result.add(nooftroopslostbyp2);
		return result;
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
	public void notifyObserver(App_Risk_Game.src.interfaces.Observable observable) {
		for (App_Risk_Game.src.interfaces.Observer o:observers
		) {
			o.update(observable);
		}
	}
}