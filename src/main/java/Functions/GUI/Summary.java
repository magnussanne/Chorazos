package Functions.GUI;

import IO.WriteToCSVFile;

import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;

import java.awt.*;
import javax.swing.table.*;
import java.lang.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.io.File;
import java.io.IOException;

public class Summary extends JPanel {
    JTable summaryTable;
    DefaultTableModel model;
    Object[] columnNames;

    public Summary(SolutionPermutation solution) {
        setPreferredSize(new Dimension(550, 500));
        model = createModel(solution);
        summaryTable = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                Object value = getModel().getValueAt(row, 3);

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

        summaryTable = columnWidth(summaryTable);
        JScrollPane scrollPane = new JScrollPane(summaryTable);
        summaryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(scrollPane);
        this.add(textSummary(solution.getPreferenceSummary()));
        this.add(exportButton(solution));
    }

    public DefaultTableModel createModel(SolutionPermutation solution) {


        columnNames = new Object[]{"Student Number", "Student Name,",  "Project Name", "Preference"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        Object[] row;

        for(Solution s : solution.getSolutionList()) {
            Student student = s.getStudent();
            Project project = s.getProject();
            row = new Object[]{student.getNumber(), student.getName(), project.getTitle(), s.getPreference()};
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

    public JTable columnWidth(JTable table) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        for (int i=0; i < table.getColumnCount(); i++) {
            TableColumn col = colModel.getColumn(i);
            int width = 0;
            for (int j=0; j<table.getRowCount(); j++) {
                TableCellRenderer renderer = table.getCellRenderer(j, i);
                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(j, i), false, false, j, i);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            width = Math.max(width, 100);
            col.setPreferredWidth(width + 2);
        }

        return table;
    }
}

