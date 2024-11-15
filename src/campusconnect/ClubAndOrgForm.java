package campusconnect;

import static campusconnect.EventsForm.conn;
import java.awt.Color;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

class ClubAndOrgForm extends javax.swing.JFrame {
    DefaultComboBoxModel<Object> facultyComboBoxModel = new DefaultComboBoxModel<>();
    String orgName;
    String level;
    String adviser;
    String details;
    
    public ClubAndOrgForm() {
        initComponents(); 
        facultyVerify();
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        createOrgLabel = new javax.swing.JLabel();
        createOrgSubmitButton = new test.ButtonRound();
        inOrgClubName = new test.TextFieldRound();
        OrgClubLabel = new javax.swing.JLabel();
        tertiaryLevelButton = new test.ToggleButtonRound();
        levelLabel = new javax.swing.JLabel();
        secondaryLevelButton = new test.ToggleButtonRound();
        levelLabel1 = new javax.swing.JLabel();
        comboBoxRound1 = new test.ComboBoxRound();
        levelLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaRound1 = new test.TextAreaRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        topPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        topPanel.setPreferredSize(new java.awt.Dimension(458, 83));

        createOrgLabel.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        createOrgLabel.setText("Create Organization/Club");

        createOrgSubmitButton.setBackground(new java.awt.Color(255, 255, 204));
        createOrgSubmitButton.setBorder(null);
        createOrgSubmitButton.setText("Confirm");
        createOrgSubmitButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
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
                .addGap(34, 34, 34))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createOrgSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createOrgLabel))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        inOrgClubName.setBorder(null);
        inOrgClubName.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inOrgClubName.setPlaceHolder("Enter org/club name");
        inOrgClubName.setRoundBottomLeft(10);
        inOrgClubName.setRoundBottomRight(10);
        inOrgClubName.setRoundTopLeft(10);
        inOrgClubName.setRoundTopRight(10);
        inOrgClubName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inOrgClubNameActionPerformed(evt);
            }
        });

        OrgClubLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        OrgClubLabel.setText("Organization/Club Name");

        tertiaryLevelButton.setBorder(null);
        tertiaryLevelButton.setText("Tertiary");
        tertiaryLevelButton.setToolTipText("");
        tertiaryLevelButton.setFocusable(false);
        tertiaryLevelButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        tertiaryLevelButton.setRoundBottomLeft(10);
        tertiaryLevelButton.setRoundBottomRight(10);
        tertiaryLevelButton.setRoundTopLeft(10);
        tertiaryLevelButton.setRoundTopRight(10);
        tertiaryLevelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tertiaryLevelButtonActionPerformed(evt);
            }
        });

        levelLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        levelLabel.setText("Level");

        secondaryLevelButton.setBorder(null);
        secondaryLevelButton.setText("Secondary");
        secondaryLevelButton.setFocusable(false);
        secondaryLevelButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        secondaryLevelButton.setRoundBottomLeft(10);
        secondaryLevelButton.setRoundBottomRight(10);
        secondaryLevelButton.setRoundTopLeft(10);
        secondaryLevelButton.setRoundTopRight(10);

        levelLabel1.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        levelLabel1.setText("Adviser");

        comboBoxRound1.setBorder(null);
        comboBoxRound1.setModel(facultyComboBoxModel);
        comboBoxRound1.setRoundBottomLeft(10);
        comboBoxRound1.setRoundBottomRight(10);
        comboBoxRound1.setRoundTopLeft(10);
        comboBoxRound1.setRoundTopRight(10);

        levelLabel2.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        levelLabel2.setText("Additional Details");

        jScrollPane1.setBorder(null);

        textAreaRound1.setBorder(null);
        textAreaRound1.setColumns(20);
        textAreaRound1.setRows(5);
        textAreaRound1.setRoundBottomLeft(10);
        textAreaRound1.setRoundBottomRight(10);
        textAreaRound1.setRoundTopLeft(10);
        textAreaRound1.setRoundTopRight(10);
        jScrollPane1.setViewportView(textAreaRound1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(levelLabel)
                            .addComponent(inOrgClubName, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(levelLabel1)
                            .addComponent(levelLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboBoxRound1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(tertiaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(secondaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(209, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(OrgClubLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(OrgClubLabel)
                .addGap(11, 11, 11)
                .addComponent(inOrgClubName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(levelLabel)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tertiaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(levelLabel1)
                .addGap(11, 11, 11)
                .addComponent(comboBoxRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(levelLabel2)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void facultyVerify(){
        try {
            Statement st = conn().createStatement();
            String checkIsFaculty = "SELECT full_name FROM users WHERE isFaculty = 1";
            var rs = st.executeQuery(checkIsFaculty);

            while (rs.next()) {
                String fullName = rs.getString("full_name");
                facultyComboBoxModel.addElement(fullName);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void createOrgSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOrgSubmitButtonActionPerformed
        if (validateInputs()) {
            try {
                Statement st = conn().createStatement();
                String checkExistingOrgQuery = "SELECT * FROM orgs WHERE org_name = '" + orgName + "'";
                var rs = st.executeQuery(checkExistingOrgQuery);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Organization already exists.");
                    return;  // Exit the method to avoid duplicate insertion
                }

                String addOrgQuery = "INSERT INTO orgs (org_name, level, adviser, details) "
                        + "VALUES ('" + orgName + "', '" + level + "', '" + adviser + "', '" + details + "')";
                st.executeUpdate(addOrgQuery);

                JOptionPane.showMessageDialog(this, "Organization created successfully!");
                dispose();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private boolean validateInputs() {
        boolean isValid = true;
        if (inOrgClubName.getText().isEmpty()) {
            inOrgClubName.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inOrgClubName.setBorder(null);
            orgName = inOrgClubName.getText();
        }
        
        return isValid;

    }//GEN-LAST:event_createOrgSubmitButtonActionPerformed

    private void inOrgClubNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inOrgClubNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inOrgClubNameActionPerformed

    private void tertiaryLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tertiaryLevelButtonActionPerformed

    }//GEN-LAST:event_tertiaryLevelButtonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel OrgClubLabel;
    private test.ComboBoxRound comboBoxRound1;
    private javax.swing.JLabel createOrgLabel;
    private test.ButtonRound createOrgSubmitButton;
    private test.TextFieldRound inOrgClubName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel levelLabel1;
    private javax.swing.JLabel levelLabel2;
    private test.ToggleButtonRound secondaryLevelButton;
    private test.ToggleButtonRound tertiaryLevelButton;
    private test.TextAreaRound textAreaRound1;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
