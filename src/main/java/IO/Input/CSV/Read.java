package IO.Input.CSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Read {
    private final static String PATH = "src/main/resources/tests/";

    static Scanner setScanner(String fileName) throws FileNotFoundException {
        File file = new File(PATH + fileName);
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");

        return sc;
    }
}
