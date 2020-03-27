package IO.Input.CSV;

import Interface.Focus;
import Objects.Staff;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ReadStaff extends Read {
    public static void Read(String FileName, List list) throws FileNotFoundException {
        Scanner sc = setScanner(FileName);

        while(sc.hasNext()) {
            String[] tokens = sc.next().split(",");
            list.add(new Staff(tokens[0], tokens[1], tokens[2], Focus.getFocus(tokens[3])));
        }
    }
}
