package Functions;

import Interface.Focus;
import Objects.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreateStudents {
    private static final String First_Names = "src/main/resources/input/firstname.txt";
    private static final String Surnames = "src/main/resources/input/surname.txt";
    private static final int Staring_Student_Number = 20216952;

    private static Scanner sc;
    private static Random rand = new Random();

    public static void main(String[] args) throws FileNotFoundException {
        List<Student> students = createStudents(500);
        System.out.println(students);
    }

    public static List createStudents(int NumberStudents) throws FileNotFoundException {
        List<String> firstname = getNames(First_Names);
        List<String> surnames = getNames(Surnames);
        List<Student> students = new ArrayList<Student>();

        int currentNumber = Staring_Student_Number;

        for(int i=0; i<NumberStudents; i++) {
            String name = createName(firstname, surnames);
            Student s = new Student(name, currentNumber++, Focus.getFocus());

            students.add(s);
        }

        return students;
    }

    private static List getNames(String file) throws FileNotFoundException {
        sc = new Scanner(new File(file));
        List<String> names = new ArrayList<String>();

        while(sc.hasNext()){
            names.add(sc.next());
        }

        return names;
    }

    private static String createName(List firstname, List surname) {
        int first = rand.nextInt(240);
        int last = rand.nextInt(100);

        String name = firstname.get(first) + " " + surname.get(last);
        return name;
    }
}
