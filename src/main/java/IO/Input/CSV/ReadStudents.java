package IO.Input.CSV;

import Interface.Focus;
import Objects.Student;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ReadStudents extends Read {
    public static void Read(String FileName, List list) throws FileNotFoundException {
        Scanner sc = setScanner(FileName);

        while(sc.hasNext()) {
            String[] tokens = sc.next().split(",");
            list.add(new Student(tokens[0], Integer.parseInt(tokens[1]), Focus.getFocus(tokens[2])));
        }
    }
}
