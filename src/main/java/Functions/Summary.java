package Functions;

import IO.Input.CSV.ReadProjects;
import IO.Input.CSV.ReadStudents;
import IO.Output.CSV.WriteToCSVFile;

import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;

import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.table.*;
import java.lang.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Summary extends JPanel {

    private SolutionPermutation solution;
    private int solutionSize;
    JTable summaryTable;
    DefaultTableModel model;
    Object[] columnNames;

    public Summary(SolutionPermutation solution) {
        setPreferredSize(new Dimension(500, 500));
        columnNames = new Object[]{"Student Number", "Student Name,", "Project ID", "Project Name", "Preference"};
        model = new DefaultTableModel(columnNames, solutionSize);
        model.addRow(columnNames);
        summaryTable = new JTable(model);
        this.add(summaryTable);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(textSummary(solution.getPreferenceSummary()));
        this.add(exportButton(solution));
    }

    public void addEntries(SolutionPermutation solution) {
        TableColumn column;
    }

    private JTextPane textSummary(String string) {
        JTextPane text = new JTextPane();
        text.setEditable(false);

        Font font = new Font("Segoe Script", Font.PLAIN, 15);
        text.setFont(font);

        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        text.setText(string);
        return text;
    }

    private JPanel exportButton(SolutionPermutation solution) {
        JPanel export = new JPanel();
        export.setLayout(new FlowLayout());

        JButton exportButton = new JButton("Export");
        exportButton.setHorizontalAlignment(JButton.RIGHT);

        exportButton.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            try {
                int r = fc.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    WriteToCSVFile.Write(solution, file);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        export.add(exportButton);
        return export;
    }

    public static void main(String[] args) throws FileNotFoundException {
        java.util.List<Student> studentList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();
        List<Solution> solutionList = new ArrayList<>();

        ReadProjects.Read("project.csv", projectList);
        ReadStudents.Read("student.csv", studentList, projectList);

        for (Student s : studentList) {
            solutionList.add(new Solution(s, projectList, solutionList));
        }

        Summary summary = new Summary(new SolutionPermutation(solutionList));

        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(summary, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }
}

