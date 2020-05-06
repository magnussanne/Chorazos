package Functions;

import IO.Input.CSV.ReadProjects;
import IO.Input.CSV.ReadStudents;
import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import javax.swing.table.*;
import java.lang.*;


public class Summary extends JPanel {

    private SolutionPermutation solution;
    private int solutionSize;
    JTable summaryTable;
    DefaultTableModel model;
    Object[] columnNames;

    public Summary(){
        columnNames = new Object[]{"Student Number", "Student Name,", "Project ID", "Project Name", "Preference"};
        model = new DefaultTableModel(columnNames, solutionSize);
        summaryTable = new JTable(model);

    }

    public void addEntries(SolutionPermutation solution){
        TableColumn column;
    }
}

