package App_Risk_Game.src.main.java.Common;

import java.util.Random;

public class Common {
    private static Random random = new Random();

    public static int generateRandomNumber(int ub){
        if(ub <= 0)
            return 1;
        else
        return random.nextInt(ub)+1;
    }
}
