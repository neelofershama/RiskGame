package App_Risk_Game.src.main.java.Common;

import java.util.Random;

public class Common {
    private static Random random = new Random();

    public static int generateRandomNumber(int ub){
        return random.nextInt(ub)+1;
    }
}
