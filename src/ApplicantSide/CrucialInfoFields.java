package ApplicantSide;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CrucialInfoFields extends JPanel {
    private JLabel educationLabel;
    private JLabel skillsLabel;
    private JLabel experienceLabel;
    private JLabel jobTitleLabel;
    private JComboBox jobTitleComboBox;
//    private JTextField educationField;
//    private JTextField skillsField;
    private JTextField experienceField;
    private JComboBox educationComboBox;
    private JList<String> skillsJList;
//    private JTextField jobTitleField;
    private JButton submitButton;
    private ArrayList<String> titlesList = new ArrayList<>();
    private ArrayList<String> skillsList = new ArrayList<>();

    CrucialInfoFields() {
        setPreferredSize(new Dimension(300, 250));
        Border innerBorder = BorderFactory.createTitledBorder("Find Job Listings");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border border = BorderFactory.createCompoundBorder(innerBorder, outerBorder);
        setBorder(border);
        setLayout(new GridBagLayout());

        Scanner jobFile;
        Scanner skillsFile;
        try {
            skillsFile = new Scanner(new File("skills.txt"));
            jobFile = new Scanner(new File("jobs.txt"));
            while (jobFile.hasNextLine()) {
                String line = jobFile.nextLine();
                if (line.length() != 0)
                    titlesList.add(line);
            }
            while (skillsFile.hasNextLine()) {
                String line = skillsFile.nextLine();
                if (line.length() != 0)
                    skillsList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

//        skillsField = new JTextField(10);
//        educationField = new JTextField(10);
        experienceField = new JTextField(10);
//        jobTitleField = new JTextField(10);

        jobTitleComboBox = new JComboBox<>(titlesList.toArray());
        String [] educationStrings = {"Master Degree", "Bachelor Degree", "Diploma / Certificate"};
        educationComboBox = new JComboBox<>(educationStrings);



        submitButton = new JButton("Look for Jobs Now!");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((String) educationComboBox.getSelectedItem()).equals("") || skillsJList.isSelectionEmpty() || experienceField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Input all correct Fields");
                } else {
                    skillsList = (ArrayList<String>)skillsJList.getSelectedValuesList();
                    String[] skillsArray = (String[])skillsList.toArray();
                    String skills = skillsList.get(0);
                    for(int i = 1; i < skillsList.size(); i++){
                        skills += "," + skillsList.get(i);
                    }

                    String job = (String) jobTitleComboBox.getSelectedItem();
                    System.out.println(job);
                    AppendToJSON appendJSON = new AppendToJSON(job, educationComboBox.getSelectedItem().toString(), experienceField.getText(), skillsArray);
                    JOptionPane.showMessageDialog(null, "Information Passed");
                }
            }
        });


        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1.0;
        gc.weighty = 1.0;


        ////////// Visuals ////////

        Insets labelInset = new Insets(0,0,0,5);
        Insets blankInset = new Insets(0,0,0,0);

        // Row 1
        jobTitleLabel = new JLabel("Job Title:");
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInset;
        add(jobTitleLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInset;
        add(jobTitleComboBox, gc);

        // Row 2
        educationLabel = new JLabel("Education:");
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInset;
        add(educationLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInset;
        add(educationComboBox, gc);

        // Row 3
        skillsLabel = new JLabel("Skills:");
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInset;
        add(skillsLabel, gc);

        skillsJList = new JList<String>();
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInset;

        add(skillsJList, gc);

        // Row 4
        experienceLabel = new JLabel("Experience:");
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInset;
        add(experienceLabel, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInset;
        add(experienceField, gc);

        // Row 5
        gc.weightx = 1.0;
        gc.weighty = 10.0;
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.PAGE_START;
        add(submitButton, gc);
    }
}