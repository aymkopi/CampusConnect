package campusconnect;

import static campusconnect.CampusConnect.conn;
import java.awt.Color;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.sql.PreparedStatement;

class ClubAndOrgForm extends javax.swing.JFrame {

    DefaultComboBoxModel<Object> facultyComboBoxModel = new DefaultComboBoxModel<>();
    String orgName;
    String level;
    String adviser;
    String details;

    public ClubAndOrgForm() {
        initComponents();
        initFaculty();
    }

    public void setOrgDetails(String orgName, String level, String adviser, String details) {
        tertiaryLevelButton.setSelected(false);
        secondaryLevelButton.setSelected(false);

        inOrgClubName.setText(orgName);
        if (level.equals("Tertiary")) {
            tertiaryLevelButton.setSelected(true);
        } else if (level.equals("Secondary")) {
            secondaryLevelButton.setSelected(true);
        }
        inAdviser.setSelectedItem(adviser);
        inAdditionalDetails.setText(details);

    }

    private void initFaculty() {
        try {
            Statement st = conn().createStatement();

            String selectFaculty = "SELECT * FROM users WHERE user_type = 'Faculty'";
            ResultSet sfrs = st.executeQuery(selectFaculty);

            while (sfrs.next()) {
                String fcn = sfrs.getString("user_name");
                facultyComboBoxModel.addElement(fcn);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        topPanel = new javax.swing.JPanel();
        createOrgLabel = new javax.swing.JLabel();
        createOrgSubmitButton = new test.ButtonRound();
        inOrgClubName = new test.TextFieldRound();
        OrgClubLabel = new javax.swing.JLabel();
        tertiaryLevelButton = new test.ToggleButtonRound();
        levelLabel = new javax.swing.JLabel();
        secondaryLevelButton = new test.ToggleButtonRound();
        adviserLabel = new javax.swing.JLabel();
        inAdviser = new test.ComboBoxRound();
        additionalDetailsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inAdditionalDetails = new test.TextAreaRound();
        orgWarning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        topPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        topPanel.setPreferredSize(new java.awt.Dimension(458, 83));

        createOrgLabel.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        createOrgLabel.setText("Organization");

        createOrgSubmitButton.setBackground(new java.awt.Color(255, 255, 204));
        createOrgSubmitButton.setBorder(null);
        createOrgSubmitButton.setText(" Confirm");
        createOrgSubmitButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        createOrgSubmitButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        createOrgSubmitButton.setRoundBottomLeft(8);
        createOrgSubmitButton.setRoundBottomRight(8);
        createOrgSubmitButton.setRoundTopLeft(8);
        createOrgSubmitButton.setRoundTopRight(8);
        createOrgSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOrgSubmitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(createOrgLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createOrgSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createOrgLabel)
                    .addComponent(createOrgSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        inOrgClubName.setBorder(null);
        inOrgClubName.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inOrgClubName.setPlaceHolder("Enter org/club name");
        inOrgClubName.setRoundBottomLeft(10);
        inOrgClubName.setRoundBottomRight(10);
        inOrgClubName.setRoundTopLeft(10);
        inOrgClubName.setRoundTopRight(10);

        OrgClubLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        OrgClubLabel.setText("Organization/Club Name");

        tertiaryLevelButton.setBorder(null);
        buttonGroup1.add(tertiaryLevelButton);
        tertiaryLevelButton.setText("Tertiary");
        tertiaryLevelButton.setToolTipText("");
        tertiaryLevelButton.setFocusable(false);
        tertiaryLevelButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        tertiaryLevelButton.setRoundBottomLeft(10);
        tertiaryLevelButton.setRoundBottomRight(10);
        tertiaryLevelButton.setRoundTopLeft(10);
        tertiaryLevelButton.setRoundTopRight(10);

        levelLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        levelLabel.setText("Level");

        secondaryLevelButton.setBorder(null);
        buttonGroup1.add(secondaryLevelButton);
        secondaryLevelButton.setText("Secondary");
        secondaryLevelButton.setFocusable(false);
        secondaryLevelButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        secondaryLevelButton.setRoundBottomLeft(10);
        secondaryLevelButton.setRoundBottomRight(10);
        secondaryLevelButton.setRoundTopLeft(10);
        secondaryLevelButton.setRoundTopRight(10);

        adviserLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        adviserLabel.setText("Adviser");

        inAdviser.setBorder(null);
        inAdviser.setModel(facultyComboBoxModel);
        inAdviser.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inAdviser.setRoundBottomLeft(10);
        inAdviser.setRoundBottomRight(10);
        inAdviser.setRoundTopLeft(10);
        inAdviser.setRoundTopRight(10);

        additionalDetailsLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        additionalDetailsLabel.setText("Additional Details");

        jScrollPane1.setBorder(null);

        inAdditionalDetails.setBorder(null);
        inAdditionalDetails.setColumns(20);
        inAdditionalDetails.setLineWrap(true);
        inAdditionalDetails.setRows(5);
        inAdditionalDetails.setWrapStyleWord(true);
        inAdditionalDetails.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inAdditionalDetails.setMargin(new java.awt.Insets(5, 5, 5, 5));
        inAdditionalDetails.setPlaceHolder("(optional)");
        inAdditionalDetails.setRoundBottomLeft(10);
        inAdditionalDetails.setRoundBottomRight(10);
        inAdditionalDetails.setRoundTopLeft(10);
        inAdditionalDetails.setRoundTopRight(10);
        jScrollPane1.setViewportView(inAdditionalDetails);

        orgWarning.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        orgWarning.setForeground(new java.awt.Color(255, 0, 0));
        orgWarning.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orgWarning)
                    .addComponent(levelLabel)
                    .addComponent(inOrgClubName, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adviserLabel)
                    .addComponent(additionalDetailsLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(inAdviser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(tertiaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(secondaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(OrgClubLabel))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(OrgClubLabel)
                .addGap(11, 11, 11)
                .addComponent(inOrgClubName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(orgWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(levelLabel)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tertiaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(adviserLabel)
                .addGap(11, 11, 11)
                .addComponent(inAdviser, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(additionalDetailsLabel)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void createOrgSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOrgSubmitButtonActionPerformed
        if (validateInputs()) {
            try (Connection conn = conn()) {
                int selectedRow = CampusConnect.getInstance().activeTable().getSelectedRow();
                String existingOrg = (selectedRow >= 0) ? CampusConnect.getInstance().activeTable().getValueAt(selectedRow, 0).toString() : "";

                if (selectedRow < 0) {

                    String addOrgQuery = "INSERT INTO orgs (org_name, level, adviser, details) "
                            + "VALUES (?, ?, ?, ?)";

                    try (PreparedStatement insertPstmt = conn.prepareStatement(addOrgQuery)) {
                        insertPstmt.setString(1, orgName);
                        insertPstmt.setString(2, level);
                        insertPstmt.setString(3, adviser);
                        insertPstmt.setString(4, details);
                        insertPstmt.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(this, "Organization created successfully!");
                } else {
                    String updateQuery = "UPDATE orgs SET org_name = ?, level = ?, adviser = ?, details = ? WHERE org_name = ?";
                    try (PreparedStatement updatePstmt = conn.prepareStatement(updateQuery)) {
                        updatePstmt.setString(1, orgName);
                        updatePstmt.setString(2, level);
                        updatePstmt.setString(3, adviser);
                        updatePstmt.setString(4, details);
                        updatePstmt.setString(5, existingOrg);
                        updatePstmt.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(this, "Organization updated successfully!");
                }

                // Refresh the users table in CampusConnect
                CampusConnect.getInstance().refreshMainTableData();
                dispose();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private boolean validateInputs() {
        try (Connection conn = conn(); Statement st = conn.createStatement()) {
            boolean isValid = true;

            String eventExistingChecker = "SELECT * FROM orgs WHERE is_deleted = 0";
            try (ResultSet rp = st.executeQuery(eventExistingChecker)) {
                boolean orgExists = false;

                while (rp.next()) {
                    String orgNString = rp.getString("org_name");
                    System.out.println(orgNString);

                    // Check if org name exists
                    if (inOrgClubName.getText().equalsIgnoreCase(orgNString)) {
                        orgWarning.setText("Organization exists");
                        inOrgClubName.setBorder(new LineBorder(Color.RED, 1));
                        isValid = false;
                        orgExists = true;
                        break;
                    }
                }

                // Check if org name is empty
                if (!orgExists) {
                    if (inOrgClubName.getText().isEmpty()) {
                        orgWarning.setText("Cannot be empty.");
                        inOrgClubName.setBorder(new LineBorder(Color.RED, 1));
                        isValid = false;
                    } else {
                        orgWarning.setText("");
                        inOrgClubName.setBorder(null);
                        orgName = inOrgClubName.getText();
                    }
                }
            }
            if (tertiaryLevelButton.isSelected()) {
                level = "Tertiary";
            }
            if (secondaryLevelButton.isSelected()) {
                level = "Secondary";
            }
            if (inAdviser.getSelectedItem().toString().isEmpty()) {
                inAdviser.setBorder(new LineBorder(Color.RED, 1));
                isValid = false;
            } else {
                inAdviser.setBorder(null);
                adviser = inAdviser.getSelectedItem().toString();
            }
            details = inAdditionalDetails.getText();

            return isValid;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            return false;
        }
    }//GEN-LAST:event_createOrgSubmitButtonActionPerformed

    /**
         * @param args the command line arguments
         */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel OrgClubLabel;
    private javax.swing.JLabel additionalDetailsLabel;
    private javax.swing.JLabel adviserLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel createOrgLabel;
    private test.ButtonRound createOrgSubmitButton;
    private test.TextAreaRound inAdditionalDetails;
    private test.ComboBoxRound inAdviser;
    private test.TextFieldRound inOrgClubName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel orgWarning;
    private test.ToggleButtonRound secondaryLevelButton;
    private test.ToggleButtonRound tertiaryLevelButton;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
