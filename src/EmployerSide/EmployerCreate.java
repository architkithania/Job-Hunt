package EmployerSide;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.sql.SQLOutput;

public class EmployerCreate {
    private static JLabel jobTitleLabel;
    private static JTextField jobTitleField;
    private static JLabel educationLabel;
    private static JComboBox educationComboBox;
    private static JLabel skillsLabel;
    private static JList skillsList;
    private static JLabel experienceLabel;
    private static JSpinner experienceSpinner;
    private static JLabel slotsLabel;
    private static JSpinner slotsSpinner;
    private static JLabel priority1Label;
    private static JComboBox priority1ComboBox;
    private static JLabel priority2Label;
    private static JComboBox priority2ComboBox;
    private static JLabel priority3Label;
    private static JComboBox priority3ComboBox;
    private static JButton button;

    private static JScrollPane scrollableSkillsList;

    private static ArrayList<String> skills;
    private static File file;
    private static Gson gson;

    private static String[] priorityOptions = {"", "Education", "Experience", "Skills"};

    public static void createListingsJSON(){
        gson = new Gson();
        try {
            ParseAPI parseAPI = new ParseAPI();
            for(int i = 0; i < parseAPI.records.length; i++){
                PrintStream ps = new PrintStream(new File("job_listings/" + parseAPI.records[i].jobtitle + ".json"));
            }
            JSONFileFormatter fileFormatter = new JSONFileFormatter();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<String> getListOfSkills(){
        ArrayList<String> skillsToReturn = new ArrayList<String>();
        try {
            ParseAPI parseAPI = new ParseAPI();
            for(int i = 0; i < parseAPI.records.length; i++){
                    String[] s = parseAPI.records[i].skills.split(",");
                for(int j = 0; j < s.length; j++){
                    if(!skillsToReturn.contains(s[j])){
                        skillsToReturn.add(s[j]);
                    }
                }
            }
            return skillsToReturn;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(600, 800);
        GridBagConstraints gc = new GridBagConstraints();
        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);

        Insets labelInsets = new Insets(0, 0, 0, 5);
        Insets blankInsets = new Insets(0,0,0,0);

        gc.weightx = 1.0;
        gc.weighty = 1.0;

        jobTitleLabel = new JLabel("Job Title:");
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInsets;
        panel.add(jobTitleLabel, gc);

        jobTitleField = new JTextField(10);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInsets;
        panel.add(jobTitleField, gc);

        educationLabel = new JLabel("Education:");
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInsets;
        panel.add(educationLabel, gc);

        String[] educationLevels = {"Diploma", "Bachelor's", "Master's", "PhD"};
        educationComboBox = new JComboBox(educationLevels);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInsets;
        panel.add(educationComboBox, gc);

        experienceLabel = new JLabel("Minimum Years of Experience");
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInsets;
        panel.add(experienceLabel, gc);

        experienceSpinner = new JSpinner(new SpinnerNumberModel(5.0, 0.0, 50.0, 1.0));
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = labelInsets;
        panel.add(experienceSpinner, gc);

        skillsLabel = new JLabel("Skills Wanted:");
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInsets;
        panel.add(skillsLabel, gc);

        skills = getListOfSkills();
        skillsList = new JList(skills.toArray());
        skillsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInsets;
        scrollableSkillsList = new JScrollPane(skillsList);
        scrollableSkillsList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollableSkillsList, gc);

        slotsLabel = new JLabel("Interview Slots:");
        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInsets;
        panel.add(slotsLabel, gc);

        slotsSpinner = new JSpinner(new SpinnerNumberModel(5.0, 0.0, 50.0, 1.0));
        gc.gridx = 1;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = labelInsets;
        panel.add(slotsSpinner, gc);

        priority1Label = new JLabel("1st Priority:");
        gc.gridx = 0;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInsets;
        panel.add(priority1Label, gc);

        priority1ComboBox = new JComboBox(priorityOptions);
        gc.gridx = 1;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInsets;
        panel.add(priority1ComboBox, gc);

        priority2Label = new JLabel("2nd Priority:");
        gc.gridx = 0;
        gc.gridy = 6;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInsets;
        panel.add(priority2Label, gc);

        priority2ComboBox = new JComboBox(priorityOptions);
        gc.gridx = 1;
        gc.gridy = 6;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInsets;
        panel.add(priority2ComboBox, gc);

        priority3Label = new JLabel("1st Priority:");
        gc.gridx = 0;
        gc.gridy = 7;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInsets;
        panel.add(priority3Label, gc);

        priority3ComboBox = new JComboBox(priorityOptions);
        gc.gridx = 1;
        gc.gridy = 7;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInsets;
        panel.add(priority3ComboBox, gc);

        button = new JButton("Done");
        gc.gridx = 0;
        gc.gridy = 8;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = blankInsets;
        panel.add(button, gc);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frame.setVisible(true);

        createListingsJSON();
    }
}