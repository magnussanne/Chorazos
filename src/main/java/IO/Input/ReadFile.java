package IO.Input;

import Interface.Focus;
import Objects.Staff;
import Objects.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) throws FileNotFoundException {
        List<Staff> staff = new ArrayList<>();
        readStaffFile("src/main/resources/input/staff_input.csv", staff);
        System.out.println(staff.toString());
    }

    public static void readStaffFile(String fileName, List list) throws FileNotFoundException {
        Scanner sc = setScanner(fileName);
        while(sc.hasNext()) {
            String[] tokens = sc.next().split(",");
            if(tokens.length == 4) {
                list.add(new Staff(tokens[0], tokens[1], tokens[2], Focus.DS));
            } else if(tokens.length == 3 && tokens[2].equals("Daegon Studies")) {
                list.add(new Staff(tokens[0], tokens[1], "", Focus.DS));
            } else if(tokens.length == 3) {
                list.add(new Staff(tokens[0], tokens[1], tokens[2], Focus.CS));
            } else {
                list.add(new Staff(tokens[0], tokens[1], "", Focus.CS));
            }
        }
    }

    public static void readStudentFile(String fileName, List list) throws FileNotFoundException {
        Scanner sc = setScanner(fileName);
        while(sc.hasNext()) {
            String[] tokens = sc.next().split(",");

            if(tokens[2].equals("CS")) {
                list.add(new Student(tokens[0], Integer.parseInt(tokens[1]), Focus.CS));
            } else {
                list.add(new Student(tokens[0], Integer.parseInt(tokens[1]), Focus.DS));
            }
        }
    }

    private static Scanner setScanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");

        return sc;
    }
}
