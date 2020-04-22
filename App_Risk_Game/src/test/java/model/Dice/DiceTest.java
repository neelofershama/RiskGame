package App_Risk_Game.src.test.java.model.Dice;

import App_Risk_Game.src.main.java.Model.Score.Dice;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiceTest {
@Test
public void rollDicetest_1()
{
    Dice dice = new Dice();
    List<Integer> result1 = dice.rollDice(3,2);
    assertEquals(result1.size(),2);
}
}
