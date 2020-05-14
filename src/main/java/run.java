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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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


    private JPanel cards() throws IOException{
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
        c.ipady = 190;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        simpleLayout.add(loadFileButton(), c);

        JButton advanced = new JButton("Advanced Settings");
        advanced.addActionListener(e -> cardLayout.next(cards));
        c.gridy = 10;
        simpleLayout.add(advanced, c);

        c.fill = GridBagConstraints.HORIZONTAL;
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

        simpleLayout.add(startSearchButton(ga, JSliderList, c), c);

        JButton simple1 = new JButton("Simple Settings");
        simple1.addActionListener(a -> cardLayout.next(cards));
        JButton simple2 = new JButton("Simple Settings");
        simple2.addActionListener(a -> cardLayout.next(cards));
        JButton simple3 = new JButton("Simple Settings");
        simple3.addActionListener(a -> cardLayout.next(cards));

        advancedLayout.addTab("Genetic Algorithm", gaPanel(simple1));
        advancedLayout.addTab("Simulated Annealing", saPanel(simple2));
        advancedLayout.addTab("Hill Climbing", hcPanel(simple3));

        cards.add(simpleLayout, "Simple");
        cards.add(advancedLayout, "Advanced");

        return cards;
    }

    private JPanel gaPanel(JButton simple1) throws IOException{
        JPanel container = new JPanel();
        GeneticAlgorithm ga = new GeneticAlgorithm(this.visual);
        JCheckBox useDefault = new JCheckBox("Use Default Values", true);
        List<JSlider> JSliderList = new ArrayList<>();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("question.jpg");
        ImageIcon image = new ImageIcon(ImageIO.read(stream));
        javax.swing.ToolTipManager.sharedInstance().setDismissDelay(100000 );
        javax.swing.ToolTipManager.sharedInstance().setInitialDelay(0);

        JLabel pLabel = new JLabel("Population Size", image, JLabel.LEFT);
        pLabel.setToolTipText("<html>" + "This decides the amount of solutions to create when finding the answer" + "<br>" +
                "Increasing this will increase run time and will improve the quality of the answer." + "</html>");
        JLabel mLabel = new JLabel("Percentage to be culled", image, JLabel.LEFT);
        mLabel.setToolTipText("<html>" + "The bottom percentage of the population that is gotten rid of" + "<br>" +
                "" + "</html>");
        JLabel nLabel = new JLabel("Percentage to be mated", image, JLabel.LEFT);
        nLabel.setToolTipText("<html>" + "The top percentage to be combined" + "<br>" +
                "" + "</html>");
        JLabel rLabel = new JLabel("Number of Generations without improvement before terminating", image, JLabel.LEFT);
        rLabel.setToolTipText("<html>" + "Increasing this increases run time and improves quality of result" + "<br>" +
                "" + "</html>");
        JLabel eLabel = new JLabel("Chance of Mutation", image, JLabel.LEFT);
        eLabel.setToolTipText("<html>" + "The chance a solution has of being randomly mutated before mating with another" + "<br>" +
                "" + "</html>");
        JLabel gpaLabel = new JLabel("Influence of GPA", image, JLabel.LEFT);
        gpaLabel.setToolTipText("<html>" + "The influence the students GPA has on their project" + "<br>" +
                "When this is high, the higher the students GPA, the more likely they are to get a higher choice" + "</html>");

        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 6;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(loadFileButton(), c);
        JSlider p = new JSlider(JSlider.HORIZONTAL, 1000, 40000, 1000);
        c.gridwidth = 3;
        p.setMajorTickSpacing(2000);
        p.setPaintTicks(true);

        Hashtable<Integer, JLabel> pLabelTable = new Hashtable<Integer, JLabel>();
        pLabelTable.put( new Integer( 1000 ), new JLabel("1,000") );
        pLabelTable.put( new Integer( 20000 ), new JLabel("20,000") );
        pLabelTable.put( new Integer( 40000 ), new JLabel("40,000") );
        p.setLabelTable( pLabelTable );

        p.setPaintLabels(true);
        c.ipady = 0;
        c.gridy = 1;
        c.gridx = 0;
        container.add(useDefault, c);
        c.gridx = 1;
        container.add(simple1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 0;
        c.ipady = 0;
        c.gridy = 2;
        c.gridx = 0;
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

        container.add(startSearchButton(ga, JSliderList, c), c);

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

    private JPanel saPanel(JButton simple2) throws IOException {
        JPanel container = new JPanel();
        SimulatedAnnealing sa = new SimulatedAnnealing(this.visual);
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("question.jpg");
        ImageIcon image = new ImageIcon(ImageIO.read(stream));
        javax.swing.ToolTipManager.sharedInstance().setDismissDelay(100000 );

        List<JSlider> JSliderList = new ArrayList<>();
        JCheckBox useDefault = new JCheckBox("Use Default Values", true);


        JLabel changesLabel = new JLabel("Number of Changes per iteration", image, JLabel.LEFT);
        changesLabel.setToolTipText("<html>" + "How many random changes is made to the solution" + "<br>" +
                "" + "</html>");
        JLabel tempLabel = new JLabel("Initial Temperature", image, JLabel.LEFT);
        tempLabel.setToolTipText("<html>" + "This affects how long the program will run for" + "<br>" +
                "The higher the initial temperature the longer it will run for and the better the solution" + "</html>");
        JLabel changeRateLabel = new JLabel("Change in Temperature", image, JLabel.LEFT);
        changeRateLabel.setToolTipText("<html>" + "How fast the temperature changes, is multiplicative" + "<br>" +
                "The higher this is the slower the change, the longer it runs, and the better the solution" + "</html>");
        JLabel gpaLabel = new JLabel("Influence of GPA", image, JLabel.LEFT);
        gpaLabel.setToolTipText("<html>" + "The influence the students GPA has on their project" + "<br>" +
                "When this is high, the higher the students GPA, the more likely they are to get a higher choice" + "</html>");

        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 6;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(loadFileButton(), c);
        JSlider changes = new JSlider();
        c.gridwidth = 3;
        changes.setMajorTickSpacing(10);
        changes.setPaintTicks(true);

        Hashtable<Integer, JLabel> changesLabelTable = new Hashtable<Integer, JLabel>();
        changesLabelTable.put( new Integer( 0 ), new JLabel("0") );
        changesLabelTable.put( new Integer( 50 ), new JLabel("50") );
        changesLabelTable.put( new Integer( 100 ), new JLabel("100") );
        changes.setLabelTable( changesLabelTable );

        changes.setPaintLabels(true);

        c.ipady = 0;
        c.gridy = 1;
        c.gridx = 0;
        container.add(useDefault, c);
        c.gridx = 1;
        container.add(simple2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        container.add(changesLabel, c);
        c.gridy = 3;
        container.add(changes, c);
        JSlider temp = new JSlider(JSlider.HORIZONTAL, 0, 1000000, 500000);
        c.ipady = 0;
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

        container.add(startSearchButton(sa, JSliderList, c), c);

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

    private JPanel hcPanel(JButton simple3) throws IOException {
        JPanel container = new JPanel();
        HillClimbing hc = new HillClimbing(this.visual);
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("question.jpg");
        ImageIcon image = new ImageIcon(ImageIO.read(stream));
        javax.swing.ToolTipManager.sharedInstance().setDismissDelay(100000 );

        List<JSlider> JSliderList = new ArrayList<>();

        JCheckBox useDefault = new JCheckBox("Use Default Values", true);


        JLabel changesLabel = new JLabel("Number of Changes per iteration", image, JLabel.LEFT);
        changesLabel.setToolTipText("<html>" + "How many random changes is made to the solution" + "<br>" +
                "" + "</html>");
        JLabel gpaLabel = new JLabel("Influence of GPA", image, JLabel.LEFT);
        gpaLabel.setToolTipText("<html>" + "The influence the students GPA has on their project" + "<br>" +
                "When this is high, the higher the students GPA, the more likely they are to get a higher choice" + "</html>");
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 6;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(loadFileButton(), c);
        JSlider changes = new JSlider();
        c.gridwidth = 3;
        changes.setMajorTickSpacing(10);
        changes.setPaintTicks(true);

        Hashtable<Integer, JLabel> changesLabelTable = new Hashtable<Integer, JLabel>();
        changesLabelTable.put( new Integer( 0 ), new JLabel("0") );
        changesLabelTable.put( new Integer( 50 ), new JLabel("50") );
        changesLabelTable.put( new Integer( 100 ), new JLabel("100") );
        changes.setLabelTable( changesLabelTable );

        changes.setPaintLabels(true);

        c.ipady = 0;
        c.gridy = 1;
        container.add(useDefault, c);
        c.gridx = 1;
        container.add(simple3, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        container.add(changesLabel, c);
        c.gridy = 3;
        container.add(changes, c);
        JSlider gpa = new JSlider();
        c.ipady = 0;
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

        container.add(startSearchButton(hc, JSliderList, c), c);

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
                        replaceComponent(this.logo, this.visual);
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

    private JButton startSearchButton(Search algorithm, List<JSlider> sliders, GridBagConstraints c) {
        JButton runButton = new JButton("Run");
        JButton cancelButton = cancelButton(algorithm, runButton, c);

        runButton.addActionListener(e -> {
            if(this.projectList == null || this.studentList == null) {
                showMessageDialog(null, "No inputs to run\nPlease input values to create a solution");
            } else if(this.visual.isRunning()) {
                showMessageDialog(null, "Another search is already running. Please cancel that search to start a new one.");
            } else {
                this.runningThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runButton(algorithm, sliders, runButton, cancelButton, c);
                    }
                });

                this.runningThread.start();
                replaceComponent(runButton, cancelButton, c);
            }
        });

        return runButton;
    }

    private void runButton(Search algorithm, List<JSlider> sliders, JButton run, JButton cancel, GridBagConstraints c) {
        try {
            replaceComponent(this.summary, this.visual);
        } catch (NullPointerException n) {}

        this.visual.resetGeneration();
        this.visual.startRunning();
        algorithm.setParameters(sliders);

        SolutionPermutation output = algorithm.solve(this.studentList, this.projectList);

        summary = new Summary(output);
        replaceComponent(this.visual, this.summary);
        this.visual.stopRunning();
        replaceComponent(cancel, run, c);
    }

    private JButton cancelButton(Search algorithm, JButton runButton, GridBagConstraints c) {
        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> {
            algorithm.cancel();
            this.visual.stopRunning();
            replaceComponent(cancelButton, runButton, c);
        });

        return cancelButton;
    }

    private void replaceComponent(Container oldComponent, Container newComponent) {
        Container container = oldComponent.getParent();
        container.remove(oldComponent);
        container.add(newComponent);
        this.mainWindow.validate();
        this.mainWindow.repaint();
    }

    private void replaceComponent(Container oldComponent, Container newComponent, GridBagConstraints c) {
        Container container = oldComponent.getParent();
        container.remove(oldComponent);
        container.add(newComponent, c);
        this.mainWindow.validate();
        this.mainWindow.repaint();
    }
}
