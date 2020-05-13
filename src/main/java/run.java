import Functions.GUI.Image;
import Functions.GUI.Summary;
import Functions.GUI.Visualization;
import Functions.Search.GeneticAlgorithm;
import Functions.Search.HillClimbing;
import Functions.Search.Search;
import Functions.Search.SimulatedAnnealing;
import IO.textInput;
import Objects.Project;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

import static javax.swing.JOptionPane.showMessageDialog;

public class run {
    private Thread runningThread;
    private JFrame mainWindow;
    private List<Student> studentList;
    private List<Project> projectList;
    private Image logo;
    private Summary summary;
    private Visualization visual;

    public static void main(String[] args) throws IOException {
        run gui = new run();
        gui.createGUI();
    }

    private void createGUI() throws IOException {
        this.visual = new  Visualization();
        this.logo = new Image();
        this.runningThread = null;

        this.mainWindow = new JFrame("Chorazos");
        this.mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainWindow.setSize(1000, 720);
        this.mainWindow.setLayout(new GridLayout(1, 2));
        this.mainWindow.setResizable(false);
        this.mainWindow.add(cards());
        this.mainWindow.add(this.logo);
        this.mainWindow.pack();
        this.mainWindow.setLocationRelativeTo(null);
        this.mainWindow.setVisible(true);
    }

    private JPanel cards(){
        JPanel cards = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) cards.getLayout();

        JPanel simpleLayout = new JPanel();
        JTabbedPane advancedLayout = new JTabbedPane();

        GeneticAlgorithm ga = new GeneticAlgorithm(this.visual);
        List<JSlider> JSliderList = new ArrayList<>();
        simpleLayout.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        simpleLayout.add(loadFileButton(), c);

        JButton advanced = new JButton("Advanced Settings");
        advanced.addActionListener(e -> cardLayout.next(cards));
        c.gridy = 7;
        simpleLayout.add(advanced, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridy = 14;
        c.anchor = GridBagConstraints.PAGE_END;

        JSlider p = new JSlider();
        JSlider m = new JSlider();
        JSlider n = new JSlider();
        JSlider r = new JSlider();
        JSlider e = new JSlider();
        JSlider gpa = new JSlider();

        p.setValue(1000);
        m.setValue(32);
        n.setValue(100);
        r.setValue(15);
        e.setValue(15);
        gpa.setValue(50);

        JSliderList.add(p);
        JSliderList.add(m);
        JSliderList.add(n);
        JSliderList.add(r);
        JSliderList.add(e);
        JSliderList.add(gpa);

        simpleLayout.add(startSearchButton(ga, JSliderList), c);

        advancedLayout.addTab("Genetic Algorithm", gaPanel());
        advancedLayout.addTab("Simulated Annealing", saPanel());
        advancedLayout.addTab("Hill Climbing", hcPanel());

        cards.add(simpleLayout, "Simple");
        cards.add(advancedLayout, "Advanced");

        return cards;
    }

    private JPanel gaPanel() {
        JPanel container = new JPanel();
        GeneticAlgorithm ga = new GeneticAlgorithm(this.visual);
        JCheckBox useDefault = new JCheckBox("Use Default Values", true);
        List<JSlider> JSliderList = new ArrayList<>();

        JLabel pLabel = new JLabel();
        pLabel.setText("Population Size");
        JLabel mLabel = new JLabel();
        mLabel.setText("Percentage to be culled");
        JLabel nLabel = new JLabel();
        nLabel.setText("Percentage to be mated");
        JLabel rLabel = new JLabel();
        rLabel.setText("Number of Iterations without improvement before terminating");
        JLabel eLabel = new JLabel();
        eLabel.setText("Chance of Mutation");
        JLabel gpaLabel = new JLabel();
        gpaLabel.setText("Influence of GPA");

        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(loadFileButton(), c);
        JSlider p = new JSlider(JSlider.HORIZONTAL, 1000, 40000, 1000);

        p.setMajorTickSpacing(2000);
        p.setPaintTicks(true);

        Hashtable<Integer, JLabel> pLabelTable = new Hashtable<Integer, JLabel>();
        pLabelTable.put( new Integer( 1000 ), new JLabel("1,000") );
        pLabelTable.put( new Integer( 20000 ), new JLabel("20,000") );
        pLabelTable.put( new Integer( 40000 ), new JLabel("40,000") );
        p.setLabelTable( pLabelTable );

        p.setPaintLabels(true);
        c.gridy = 1;
        container.add(useDefault, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 0;
        c.ipady = 0;
        c.gridy = 2;
        container.add(pLabel, c);
        c.gridy = 3;
        container.add(p, c);
        JSlider m = new JSlider();

        m.setMajorTickSpacing(10);
        m.setPaintTicks(true);

        Hashtable<Integer, JLabel> mLabelTable = new Hashtable<Integer, JLabel>();
        mLabelTable.put(new Integer(0), new JLabel("0%"));
        mLabelTable.put(new Integer(50), new JLabel("50%"));
        mLabelTable.put(new Integer(100), new JLabel("100%"));
        m.setLabelTable(mLabelTable);

        m.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        container.add(mLabel, c);
        c.gridy = 5;
        container.add(m, c);
        JSlider n = new JSlider();

        n.setMajorTickSpacing(10);
        n.setPaintTicks(true);

        Hashtable<Integer, JLabel> nLabelTable = new Hashtable<Integer, JLabel>();
        nLabelTable.put(new Integer(0), new JLabel("0%"));
        nLabelTable.put(new Integer(50), new JLabel("50%"));
        nLabelTable.put(new Integer(100), new JLabel("100%"));
        n.setLabelTable(nLabelTable);

        n.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 6;
        container.add(nLabel, c);
        c.gridy = 7;
        container.add(n, c);
        JSlider r = new JSlider(JSlider.HORIZONTAL, 0, 50, 15);

        r.setMajorTickSpacing(5);
        r.setPaintTicks(true);

        Hashtable<Integer, JLabel> rLabelTable = new Hashtable<Integer, JLabel>();
        rLabelTable.put(new Integer(0), new JLabel("0"));
        rLabelTable.put(new Integer(25), new JLabel("25"));
        rLabelTable.put(new Integer(50), new JLabel("50"));
        r.setLabelTable(rLabelTable);

        r.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 8;
        container.add(rLabel, c);
        c.gridy = 9;
        container.add(r, c);
        JSlider e = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);

        e.setMajorTickSpacing(10);
        e.setPaintTicks(true);

        Hashtable<Integer, JLabel> eLabelTable = new Hashtable<Integer, JLabel>();
        eLabelTable.put(new Integer(0), new JLabel("0%"));
        eLabelTable.put(new Integer(50), new JLabel("50%"));
        eLabelTable.put(new Integer(100), new JLabel("100%"));
        e.setLabelTable(mLabelTable);

        e.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 10;
        container.add(eLabel, c);
        c.gridy = 11;
        container.add(e, c);
        JSlider gpa = new JSlider();

        gpa.setMajorTickSpacing(10);
        gpa.setPaintTicks(true);

        Hashtable<Integer, JLabel> gpaLabelTable = new Hashtable<Integer, JLabel>();
        gpaLabelTable.put(new Integer(0), new JLabel("0"));
        gpaLabelTable.put(new Integer(50), new JLabel("0.5"));
        gpaLabelTable.put(new Integer(100), new JLabel("1"));
        gpa.setLabelTable(gpaLabelTable);

        gpa.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 12;
        container.add(gpaLabel, c);
        c.gridy = 13;
        container.add(gpa, c);

        JSliderList.add(p);
        JSliderList.add(m);
        JSliderList.add(n);
        JSliderList.add(r);
        JSliderList.add(e);
        JSliderList.add(gpa);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridy = 14;
        c.anchor = GridBagConstraints.PAGE_END;

        container.add(startSearchButton(ga, JSliderList), c);

        p.setEnabled(!useDefault.isSelected());
        m.setEnabled(!useDefault.isSelected());
        n.setEnabled(!useDefault.isSelected());
        r.setEnabled(!useDefault.isSelected());
        e.setEnabled(!useDefault.isSelected());
        gpa.setEnabled(!useDefault.isSelected());

        useDefault.addActionListener(action -> {
            p.setEnabled(!useDefault.isSelected());
            m.setEnabled(!useDefault.isSelected());
            n.setEnabled(!useDefault.isSelected());
            r.setEnabled(!useDefault.isSelected());
            e.setEnabled(!useDefault.isSelected());
            gpa.setEnabled(!useDefault.isSelected());

            if(useDefault.isSelected()) {
                p.setValue(1000);
                m.setValue(32);
                n.setValue(100);
                r.setValue(15);
                e.setValue(15);
                gpa.setValue(50);
            }

            container.revalidate();
        });

        return container;
    }

    private JPanel saPanel() {
        JPanel container = new JPanel();
        SimulatedAnnealing sa = new SimulatedAnnealing(this.visual);

        List<JSlider> JSliderList = new ArrayList<>();
        JCheckBox useDefault = new JCheckBox("Use Default Values", true);


        JLabel changesLabel = new JLabel();
        changesLabel.setText("Number of Changes per iteration");
        JLabel tempLabel = new JLabel();
        tempLabel.setText("Initial Temperature");
        JLabel changeRateLabel = new JLabel();
        changeRateLabel.setText("Change in Temperature");
        JLabel gpaLabel = new JLabel();
        gpaLabel.setText("Influence of GPA");

        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(loadFileButton(), c);
        JSlider changes = new JSlider();

        changes.setMajorTickSpacing(10);
        changes.setPaintTicks(true);

        Hashtable<Integer, JLabel> changesLabelTable = new Hashtable<Integer, JLabel>();
        changesLabelTable.put( new Integer( 0 ), new JLabel("0") );
        changesLabelTable.put( new Integer( 50 ), new JLabel("50") );
        changesLabelTable.put( new Integer( 100 ), new JLabel("100") );
        changes.setLabelTable( changesLabelTable );

        changes.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 0;
        c.ipady = 0;
        c.gridy = 1;
        container.add(useDefault, c);
        c.gridy = 2;
        container.add(changesLabel, c);
        c.gridy = 3;
        container.add(changes, c);
        JSlider temp = new JSlider(JSlider.HORIZONTAL, 0, 1000000, 500000);

        temp.setMajorTickSpacing(100000);
        temp.setPaintTicks(true);

        Hashtable<Integer, JLabel> tempLabelTable = new Hashtable<Integer, JLabel>();
        tempLabelTable.put( new Integer( 0 ), new JLabel("0") );
        tempLabelTable.put( new Integer( 500000 ), new JLabel("500,000") );
        tempLabelTable.put( new Integer( 1000000 ), new JLabel("1,000,000") );
        temp.setLabelTable( tempLabelTable );

        temp.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        container.add(tempLabel, c);
        c.gridy = 5;
        container.add(temp, c);
        JSlider changeRate = new JSlider(JSlider.HORIZONTAL, 0, 10000, 9999);

        changeRate.setMajorTickSpacing(1000);
        changeRate.setPaintTicks(true);

        Hashtable<Integer, JLabel> changeRateLabelTable = new Hashtable<Integer, JLabel>();
        changeRateLabelTable.put( new Integer( 0 ), new JLabel("0") );
        changeRateLabelTable.put( new Integer( 5000 ), new JLabel("0.5") );
        changeRateLabelTable.put( new Integer( 10000 ), new JLabel("1") );
        changeRate.setLabelTable( changeRateLabelTable );

        changeRate.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 6;
        container.add(changeRateLabel, c);
        c.gridy = 7;
        container.add(changeRate, c);
        JSlider gpa = new JSlider();

        gpa.setMajorTickSpacing(10);
        gpa.setPaintTicks(true);

        Hashtable<Integer, JLabel> gpaLabelTable = new Hashtable<Integer, JLabel>();
        gpaLabelTable.put( new Integer( 0 ), new JLabel("0") );
        gpaLabelTable.put( new Integer( 50 ), new JLabel("0.5") );
        gpaLabelTable.put( new Integer( 100 ), new JLabel("1") );
        gpa.setLabelTable( gpaLabelTable );

        gpa.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 8;
        container.add(gpaLabel, c);
        c.gridy = 9;
        container.add(gpa, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridy = 10;
        c.anchor = GridBagConstraints.PAGE_END;

        JSliderList.add(changes);
        JSliderList.add(temp);
        JSliderList.add(changeRate);
        JSliderList.add(gpa);

        container.add(startSearchButton(sa, JSliderList), c);

        temp.setEnabled(!useDefault.isSelected());
        changeRate.setEnabled(!useDefault.isSelected());
        changes.setEnabled(!useDefault.isSelected());
        gpa.setEnabled(!useDefault.isSelected());

        useDefault.addActionListener(action -> {
            temp.setEnabled(!useDefault.isSelected());
            changeRate.setEnabled(!useDefault.isSelected());
            changes.setEnabled(!useDefault.isSelected());
            gpa.setEnabled(!useDefault.isSelected());

            if(useDefault.isSelected()) {
                temp.setValue(900000);
                changeRate.setValue(9999);
                changes.setValue(30);
                gpa.setValue(50);
            }

            container.revalidate();
        });

        return container;
    }

    private JPanel hcPanel() {
        JPanel container = new JPanel();
        HillClimbing hc = new HillClimbing(this.visual);

        List<JSlider> JSliderList = new ArrayList<>();

        JCheckBox useDefault = new JCheckBox("Use Default Values", true);


        JLabel changesLabel = new JLabel();
        changesLabel.setText("Number of Changes per iteration");
        JLabel gpaLabel = new JLabel();
        gpaLabel.setText("Influence of GPA");
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(loadFileButton(), c);
        JSlider changes = new JSlider();

        changes.setMajorTickSpacing(10);
        changes.setPaintTicks(true);

        Hashtable<Integer, JLabel> changesLabelTable = new Hashtable<Integer, JLabel>();
        changesLabelTable.put( new Integer( 0 ), new JLabel("0") );
        changesLabelTable.put( new Integer( 50 ), new JLabel("50") );
        changesLabelTable.put( new Integer( 100 ), new JLabel("100") );
        changes.setLabelTable( changesLabelTable );

        changes.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 0;
        c.ipady = 0;
        c.gridy = 1;
        container.add(useDefault, c);
        c.gridy = 2;
        container.add(changesLabel, c);
        c.gridy = 3;
        container.add(changes, c);
        JSlider gpa = new JSlider();

        gpa.setMajorTickSpacing(10);
        gpa.setPaintTicks(true);

        Hashtable<Integer, JLabel> gpaLabelTable = new Hashtable<Integer, JLabel>();
        gpaLabelTable.put( new Integer( 0 ), new JLabel("0") );
        gpaLabelTable.put( new Integer( 50 ), new JLabel("0.5") );
        gpaLabelTable.put( new Integer( 100 ), new JLabel("1") );
        gpa.setLabelTable( gpaLabelTable );

        gpa.setPaintLabels(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        container.add(gpaLabel, c);
        c.gridy = 5;
        container.add(gpa, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridy = 6;
        c.anchor = GridBagConstraints.PAGE_END;


        JSliderList.add(changes);
        JSliderList.add(gpa);

        container.add(startSearchButton(hc, JSliderList), c);

        changes.setEnabled(!useDefault.isSelected());
        gpa.setEnabled(!useDefault.isSelected());

        useDefault.addActionListener(action -> {
            changes.setEnabled(!useDefault.isSelected());
            gpa.setEnabled(!useDefault.isSelected());

            if(useDefault.isSelected()) {
                changes.setValue(50);
                gpa.setValue(50);
            }

            container.revalidate();
        });

        return container;
    }

    private JButton loadFileButton() {
        JButton loadFile = new JButton("Load Inputs");
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        loadFile.addActionListener(e -> {
            List<Project> oldProject;
            List<Student> oldStudent;

            if(this.projectList != null) {
                oldProject = this.projectList;
                oldStudent = this.studentList;
            } else {
                oldProject = null;
                oldStudent = null;
            }

            this.projectList = new ArrayList<>();
            this.studentList = new ArrayList<>();

            try {
                fc.setDialogTitle("Select Input");
                int r = fc.showOpenDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    textInput t = new textInput(file, ",");

                    if(t.stopError()) {
                        showMessageDialog(null, "Error: Missing Columns\n" + t.getStopError());
                        r = JFileChooser.CANCEL_OPTION;
                    }

                    if(!t.gpaPresent() && r == JFileChooser.APPROVE_OPTION) {
                        String message = "Student GPA column is not present. Is this correct?";
                        int proceed = JOptionPane.showConfirmDialog(null, message, "GPA", JOptionPane.YES_NO_OPTION);

                        if (proceed == JOptionPane.NO_OPTION) {
                            r = JFileChooser.CANCEL_OPTION;
                        }
                    }

                    if(r == JFileChooser.APPROVE_OPTION) {
                        if(t.unusedColumns()) {
                            showMessageDialog(null, "Unused Columns:\n" + t.getUnusedColumns());
                        }

                        t.Read(this.studentList, this.projectList);
                        this.visual.loadValues(this.studentList, this.projectList);
                        replacePanel(this.logo, this.visual);
                    }
                }

                if (r == JFileChooser.CANCEL_OPTION) {
                    this.projectList = oldProject;
                    this.studentList = oldStudent;
                }
            } catch (FileNotFoundException fileNotFoundException) {
                showMessageDialog(null, "Invalid File\nPlease reselect a valid file");
            }
        });

        return loadFile;
    }

    private JButton startSearchButton(Search algorithm, List<JSlider> sliders) {

        JButton runButton = new JButton("Run");

        runButton.addActionListener(e -> {
            if(this.runningThread == null) {
                this.runningThread = new Thread() {
                    public void run() {
                        runButton(algorithm, sliders);
                    }
                };
                this.runningThread.start();
                this.runningThread = null;
            }
        });

        return runButton;
    }

    private void runButton(Search algorithm, List<JSlider> sliders) {
        if(this.projectList == null || this.studentList == null) {
            showMessageDialog(null, "No inputs to run\nPlease input values to create a solution");
        } else {
            try {
                this.mainWindow.remove(this.summary);
                this.mainWindow.add(this.visual);
                this.mainWindow.validate();
                this.mainWindow.repaint();
            } catch (NullPointerException n) {

            }

            algorithm.setParameters(sliders);

            SolutionPermutation output = algorithm.solve(this.studentList, this.projectList);

            summary = new Summary(output);
            replacePanel(this.visual, this.summary);
        }
    }

    private void replacePanel(JPanel oldPanel, JPanel newPanel) {
        this.mainWindow.remove(oldPanel);
        this.mainWindow.add(newPanel);
        this.mainWindow.validate();
        this.mainWindow.repaint();
    }
}
