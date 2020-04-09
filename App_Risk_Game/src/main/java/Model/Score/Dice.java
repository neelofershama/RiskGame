import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yashwanth
 * Represents the dice rolls.
 */
public class Dice {

    /**
     * Store of number of dices to roll.
     */
    private int number_of_dice;
    /**
     * Store the result of dice roll.
     */
    private List<Integer> dice_value;
    /**
     * Store the java random generation object
     */
    private Random rand = new Random();

    /**
     * @param number_of_dice Get the number of dices to roll
     */
    private void setNoOfDice(int number_of_dice) {
        this.number_of_dice = number_of_dice;
        dice_value = new ArrayList<Integer>(number_of_dice);
    }

    /**
     * @return List of dice roll results
     */
    private List getScore() {
        for (int i = 0; i < number_of_dice; i++) {
            dice_value.add(i, rand.nextInt(6) + 1);
        }
        return dice_value;
    }

}
