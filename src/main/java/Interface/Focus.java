package Interface;

import java.util.Random;

public enum Focus {
    CS, DS, CSDS;

    public static Focus getFocus() {
        Random rand = new Random();
        int r = rand.nextInt(10);

    //  From 0-5 is 60% of the possible random numbers, making 60% Cthulhu Study students
        if(r <= 5)
            return CS;
        else
            return DS;
    }

    public static Focus getFocus(String f) {
        if(f.equals("CS")) {
            return CS;
        } else if(f.equals("DS")) {
            return DS;
        } else {
            return CSDS;
        }
    }

    public boolean isCompatible(Focus f) {
        if(this == f || f == Focus.CSDS) {
            return true;
        } else {
            return false;
        }
    }
}
