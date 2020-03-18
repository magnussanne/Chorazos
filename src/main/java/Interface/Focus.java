package Interface;

import java.util.Random;

public enum Focus {
    CS, DS;

    public static Focus getFocus() {
        Random rand = new Random();
        int r = rand.nextInt(10);

    //  From 0-5 is 60% of the possible random numbers, making 60% Cthulhu Study students
        if(r <= 5)
            return CS;
        else
            return DS;
    }
}
