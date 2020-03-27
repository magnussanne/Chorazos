package IO.Input.CSV;

import Interface.Focus;
import Objects.Project;
import Objects.Staff;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ReadStaff extends Read {
    public static void Read(String FileName, List list, List<Project> projects) throws FileNotFoundException {
        Scanner sc = setScanner(FileName);

        while(sc.hasNext()) {
            String[] tokens = sc.next().split(",");
            Staff staff = new Staff(tokens[0], tokens[1], tokens[2], Focus.getFocus(tokens[3]));

            for(int i=4; i<tokens.length; i++) {
                for(Project p : projects) {
                    if(tokens[i].equals(p.getId().toString())) {
                        staff.addProject(p);
                    }
                }
            }

            list.add(staff);
        }
    }
}
