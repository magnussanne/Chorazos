package IO.Input.CSV;

import Interface.Focus;
import Objects.Project;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ReadProjects extends Read {
    public static void Read(String FileName, List list) throws FileNotFoundException {
        Scanner sc = setScanner(FileName);

        while(sc.hasNext()) {
            String[] tokens = sc.next().split(",");
            list.add(new Project(tokens[0], tokens[1], Focus.getFocus(tokens[2])));
        }
    }
}
