package ApplicantSide;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrucialInfoFields extends JPanel {
    private JLabel educationLabel;
    private JLabel skillsLabel;
    private JLabel experienceLabel;
    private JLabel jobTitleLabel;
    private JTextField educationField;
    private JTextField skillsField;
    private JTextField experienceField;
    private JTextField jobTitleField;
    private JButton submitButton;

    CrucialInfoFields() {
        setPreferredSize(new Dimension(300, 250));
        Border innerBorder = BorderFactory.createTitledBorder("Find Job Listings");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border border = BorderFactory.createCompoundBorder(innerBorder, outerBorder);
        setBorder(border);
        setLayout(new GridBagLayout());

        skillsField = new JTextField(10);
        educationField = new JTextField(10);
        experienceField = new JTextField(10);
        jobTitleField = new JTextField(10);

        submitButton = new JButton("Look for Jobs Now!");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (educationField.getText().equals("") || skillsField.getText().equals("") || experienceField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Input all correct Fields");
                } else {
                    String[] skills = skillsField.getText().split(",");
                    AppendToJSON appendJSON = new AppendToJSON(jobTitleField.getText(), educationField.getText(), experienceField.getText(), skills);
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
        add(jobTitleField, gc);

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
        add(educationField, gc);

        // Row 3
        skillsLabel = new JLabel("Skills:");
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = labelInset;
        add(skillsLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = blankInset;
        add(skillsField, gc);

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