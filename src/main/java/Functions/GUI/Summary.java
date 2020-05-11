package Functions.GUI;

import IO.Input.CSV.ReadProjects;
import IO.Input.CSV.ReadStudents;
import IO.Output.CSV.WriteToCSVFile;

import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;

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
        setPreferredSize(new Dimension(1000, 500));
        model = createModel(solution);
        summaryTable = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                Object value = getModel().getValueAt(row, 4);


                    int colour;
                    if(value == "NA")
                        colour = 0;
                    else
                        colour = 255 * (21-Integer.parseInt(value.toString())) / 20;

                    comp.setBackground(new Color(255, colour, colour));
                

                return comp;
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        columnWidth(summaryTable);
        this.add(summaryTable);
        JScrollPane scrollPane = new JScrollPane(summaryTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(textSummary(solution.getPreferenceSummary()));
        this.add(exportButton(solution));
    }
    public DefaultTableModel createModel(SolutionPermutation solution) {
        columnNames = new Object[]{"Student Number", "Student Name,", "Project ID", "Project Name", "Preference"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        Object[] row;

        for(Solution s : solution.getSolutionList()) {
            Student student = s.getStudent();
            Project project = s.getProject();
            row = new Object[]{student.getNumber(), student.getName(), project.getId(), project.getTitle(), s.getPreference()};
            model.addRow(row);
        }

        return model;
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

    public void columnWidth(JTable table) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;
            for (int j = 0; j < table.getRowCount(); j++) {
                TableCellRenderer renderer = table.getCellRenderer(j, i);
                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(j, i), false, false, j, i);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            col.setPreferredWidth(width + 2);
        }
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

