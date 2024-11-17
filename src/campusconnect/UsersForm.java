package campusconnect;

import static campusconnect.CampusConnect.conn;
import java.awt.Color;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

class UsersForm extends javax.swing.JFrame {

    //variables
    String userName;
    String userType;
    ;
    String studentLevel;
    String userID;
    String password;

    public UsersForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userTypeButtonGroup = new javax.swing.ButtonGroup();
        studentLevelButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        eventLabel = new javax.swing.JLabel();
        addUserSubmitButton = new test.ButtonRound();
        userNameLabel = new javax.swing.JLabel();
        inUserName = new test.TextFieldRound();
        userTypeLabel = new javax.swing.JLabel();
        studentTypeButton = new test.ToggleButtonRound();
        facultyTypeButton = new test.ToggleButtonRound();
        studentLevelLabel = new javax.swing.JLabel();
        tertiaryLevelButton = new test.ToggleButtonRound();
        secondaryLevelButton = new test.ToggleButtonRound();
        userIDLabel = new javax.swing.JLabel();
        inUserID = new test.TextFieldRound();
        passwordLabel = new javax.swing.JLabel();
        inPassword = new test.TextFieldRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(913, 632));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        eventLabel.setText("Add User");
        eventLabel.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N

        addUserSubmitButton.setText("Confirm");
        addUserSubmitButton.setBackground(new java.awt.Color(255, 255, 204));
        addUserSubmitButton.setBorder(null);
        addUserSubmitButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        addUserSubmitButton.setRoundBottomLeft(8);
        addUserSubmitButton.setRoundBottomRight(8);
        addUserSubmitButton.setRoundTopLeft(8);
        addUserSubmitButton.setRoundTopRight(8);
        addUserSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserSubmitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(eventLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addUserSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        userNameLabel.setText("User Name");
        userNameLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        inUserName.setBorder(null);
        inUserName.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inUserName.setPlaceHolder("Enter full name");
        inUserName.setRoundBottomLeft(10);
        inUserName.setRoundBottomRight(10);
        inUserName.setRoundTopLeft(10);
        inUserName.setRoundTopRight(10);
        inUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inUserNameActionPerformed(evt);
            }
        });

        userTypeLabel.setText("User Type");
        userTypeLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        studentTypeButton.setBorder(null);
        userTypeButtonGroup.add(studentTypeButton);
        studentTypeButton.setText("Student");
        studentTypeButton.setToolTipText("");
        studentTypeButton.setFocusable(false);
        studentTypeButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        studentTypeButton.setRoundBottomLeft(10);
        studentTypeButton.setRoundBottomRight(10);
        studentTypeButton.setRoundTopLeft(10);
        studentTypeButton.setRoundTopRight(10);
        studentTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentTypeButtonActionPerformed(evt);
            }
        });

        facultyTypeButton.setBorder(null);
        userTypeButtonGroup.add(facultyTypeButton);
        facultyTypeButton.setText("Faculty");
        facultyTypeButton.setFocusable(false);
        facultyTypeButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        facultyTypeButton.setRoundBottomLeft(10);
        facultyTypeButton.setRoundBottomRight(10);
        facultyTypeButton.setRoundTopLeft(10);
        facultyTypeButton.setRoundTopRight(10);
        facultyTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyTypeButtonActionPerformed(evt);
            }
        });

        studentLevelLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        studentLevelLabel.setText("Student Level");

        tertiaryLevelButton.setBorder(null);
        studentLevelButtonGroup.add(tertiaryLevelButton);
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

        secondaryLevelButton.setBorder(null);
        studentLevelButtonGroup.add(secondaryLevelButton);
        secondaryLevelButton.setText("Secondary");
        secondaryLevelButton.setFocusable(false);
        secondaryLevelButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        secondaryLevelButton.setRoundBottomLeft(10);
        secondaryLevelButton.setRoundBottomRight(10);
        secondaryLevelButton.setRoundTopLeft(10);
        secondaryLevelButton.setRoundTopRight(10);
        secondaryLevelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secondaryLevelButtonActionPerformed(evt);
            }
        });

        userIDLabel.setText("User ID");
        userIDLabel.setFocusable(false);
        userIDLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        inUserID.setBorder(null);
        inUserID.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inUserID.setPlaceHolder("Enter User ID");
        inUserID.setRoundBottomLeft(8);
        inUserID.setRoundBottomRight(8);
        inUserID.setRoundTopLeft(8);
        inUserID.setRoundTopRight(8);
        inUserID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inUserIDActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        passwordLabel.setText("Password");
        passwordLabel.setFocusable(false);

        inPassword.setBorder(null);
        inPassword.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inPassword.setPlaceHolder("Enter User Password");
        inPassword.setRoundBottomLeft(8);
        inPassword.setRoundBottomRight(8);
        inPassword.setRoundTopLeft(8);
        inPassword.setRoundTopRight(8);
        inPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tertiaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(secondaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(studentLevelLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(studentTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(facultyTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(inUserID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(inUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(userNameLabel)
                                .addComponent(userTypeLabel))
                            .addGap(1, 1, 1))
                        .addComponent(inPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passwordLabel))
                    .addComponent(userIDLabel))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(userNameLabel)
                .addGap(11, 11, 11)
                .addComponent(inUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(userTypeLabel)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facultyTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(studentLevelLabel)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tertiaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondaryLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(userIDLabel)
                .addGap(10, 10, 10)
                .addComponent(inUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(passwordLabel)
                .addGap(10, 10, 10)
                .addComponent(inPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addUserSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserSubmitButtonActionPerformed
        if (validateInputs()) {
            try {
                Statement st = conn().createStatement();
                String checkUserExist = "SELECT * FROM users WHERE user_id = '" + userID + "'";
                var rs = st.executeQuery(checkUserExist);

                if (rs.next()) {  // If a matching student ID is found
                    JOptionPane.showMessageDialog(this, "Account with this User ID already exists.");
                    return;  // Exit the method to avoid duplicate insertion
                }

                String addUserQuery = "INSERT INTO users (user_name, user_type, student_type, user_id, password) "
                        + "VALUES ('" + userName + "', '" + userType + "', '" + studentLevel + "', '" + userID + "', '" + password + "')";
                st.executeUpdate(addUserQuery);

                JOptionPane.showMessageDialog(this, "Event created successfully!");
                dispose();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private boolean validateInputs() {
        boolean isValid = true;
        if (inUserName.getText().isEmpty()) {
            inUserName.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inUserName.setBorder(null);
            userName = inUserName.getText();
        }
        if (inUserID.getText().isEmpty()) {
            inUserID.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inUserID.setBorder(null);
            userID = inUserID.getText();
        }
        if (inPassword.getText().isEmpty()) {
            inPassword.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inPassword.setBorder(null);
            password = inPassword.getText();
        }
        //if faculty, sets boolean on sql of is_faculty to faculty, else user is Student
        if (facultyTypeButton.isSelected()) {

            userType = "Faculty";
        } else if (studentTypeButton.isSelected()) {
            userType = "Student";
        }

        //if, tertiary, sets boolean on sql of 
        if (tertiaryLevelButton.isSelected()) {
            studentLevel = "Tertiary";
        } else if (secondaryLevelButton.isSelected()) {
            studentLevel = "Secondary";
        }

        return isValid;

    }//GEN-LAST:event_addUserSubmitButtonActionPerformed

    private void inUserIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inUserIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inUserIDActionPerformed

    private void inUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inUserNameActionPerformed

    private void studentTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentTypeButtonActionPerformed
    }//GEN-LAST:event_studentTypeButtonActionPerformed

    private void inPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inPasswordActionPerformed

    private void tertiaryLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tertiaryLevelButtonActionPerformed
        if (facultyTypeButton.isSelected()) {
            studentLevelButtonGroup.clearSelection();
        }
    }//GEN-LAST:event_tertiaryLevelButtonActionPerformed
    private void facultyTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyTypeButtonActionPerformed
        if (facultyTypeButton.isSelected()) {
            studentLevelButtonGroup.clearSelection();
        }
    }//GEN-LAST:event_facultyTypeButtonActionPerformed

    private void secondaryLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondaryLevelButtonActionPerformed
        if (facultyTypeButton.isSelected()) {
            studentLevelButtonGroup.clearSelection();
        }
    }//GEN-LAST:event_secondaryLevelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private test.ButtonRound addUserSubmitButton;
    private javax.swing.JLabel eventLabel;
    private test.ToggleButtonRound facultyTypeButton;
    private test.TextFieldRound inPassword;
    private test.TextFieldRound inUserID;
    private test.TextFieldRound inUserName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel passwordLabel;
    private test.ToggleButtonRound secondaryLevelButton;
    private javax.swing.ButtonGroup studentLevelButtonGroup;
    private javax.swing.JLabel studentLevelLabel;
    private test.ToggleButtonRound studentTypeButton;
    private test.ToggleButtonRound tertiaryLevelButton;
    private javax.swing.JLabel userIDLabel;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.ButtonGroup userTypeButtonGroup;
    private javax.swing.JLabel userTypeLabel;
    // End of variables declaration//GEN-END:variables
}
