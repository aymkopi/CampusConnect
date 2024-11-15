package campusconnect;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.TimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.TimeChangeEvent;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.sql.*;

class EventsForm extends javax.swing.JFrame {

    //establish connection
    public static Connection conn() {
        try {
            String url = "jdbc:mysql://localhost:3306/campusconnect?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "justi";
            String password = "1234";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    //variables
    Connection conn = conn();
    String eventName;
    String startDate;
    String startTime;
    String endDate;
    String endTime;
    String location;
    String userAccess = "";
    String clubInCharge;
    String facultyInCharge;
    String details;
    int tertiaryAccess;
    int juniorHighAccess;
    int seniorHighAccess;

    public EventsForm() {
        initComponents();

        // Add action listener for the date picker
        inEventStartDate.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                dateChangedAction(event.getNewDate().toString());
            }
        });

        // Add action listener for the time picker
        inEventStartTime.addTimeChangeListener(new TimeChangeListener() {
            @Override
            public void timeChanged(TimeChangeEvent event) {
                timeChangedAction(event.getNewTime().toString());
            }
        });
        inEventEndDate.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                dateChangedAction(event.getNewDate().toString());
            }
        });

        // Add action listener for the time picker
        inEventEndTime.addTimeChangeListener(new TimeChangeListener() {
            @Override
            public void timeChanged(TimeChangeEvent event) {
                timeChangedAction(event.getNewTime().toString());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eventNameLabel = new javax.swing.JLabel();
        eventStartLabel = new javax.swing.JLabel();
        inClubInChargeForm = new test.ComboBoxRound();
        inEventStartDate = new com.github.lgooddatepicker.components.DatePicker();
        inEventStartTime = new com.github.lgooddatepicker.components.TimePicker();
        orgClubInChargeLabel = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        inLocationForm = new test.TextFieldRound();
        facultyInChargeLabel = new javax.swing.JLabel();
        inFacultyInChargeForm = new test.ComboBoxRound();
        detailsLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inDetailsForm = new test.TextAreaRound();
        userAcessLabel = new javax.swing.JLabel();
        inEventEndDate = new com.github.lgooddatepicker.components.DatePicker();
        inEventEndTime = new com.github.lgooddatepicker.components.TimePicker();
        eventEndLabel = new javax.swing.JLabel();
        inEventNameForm = new test.TextFieldRound();
        jPanel1 = new javax.swing.JPanel();
        eventLabel = new javax.swing.JLabel();
        createEventSubmitButton = new test.ButtonRound();
        tertiaryAccessButton = new test.ToggleButtonRound();
        juniorHighAccessButton = new test.ToggleButtonRound();
        seniorHighAccessButton = new test.ToggleButtonRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        eventNameLabel.setText("Event Name");
        eventNameLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        eventStartLabel.setText("Event Start");
        eventStartLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        inClubInChargeForm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Music", "Dance", "Csc Org" }));
        inClubInChargeForm.setBorder(null);
        inClubInChargeForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inClubInChargeForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inClubInChargeFormActionPerformed(evt);
            }
        });

        inEventStartDate.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

        inEventStartTime.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

        orgClubInChargeLabel.setText("Org/Club In-Charge");
        orgClubInChargeLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        locationLabel.setText("Location");
        locationLabel.setFocusable(false);
        locationLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        inLocationForm.setBorder(null);
        inLocationForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inLocationForm.setPlaceHolder("Enter location");
        inLocationForm.setRoundBottomLeft(8);
        inLocationForm.setRoundBottomRight(8);
        inLocationForm.setRoundTopLeft(8);
        inLocationForm.setRoundTopRight(8);
        inLocationForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inLocationFormActionPerformed(evt);
            }
        });

        facultyInChargeLabel.setText("Faculty In-Charge");
        facultyInChargeLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        inFacultyInChargeForm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elvis Atento", "Babylyn Ramos", "Akeem Pedrasa daddy" }));
        inFacultyInChargeForm.setBorder(null);
        inFacultyInChargeForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inFacultyInChargeForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inFacultyInChargeFormActionPerformed(evt);
            }
        });

        detailsLabel.setText("Additional Details");
        detailsLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        jScrollPane2.setBorder(null);

        inDetailsForm.setBorder(null);
        inDetailsForm.setColumns(20);
        inDetailsForm.setLineWrap(true);
        inDetailsForm.setRows(5);
        inDetailsForm.setWrapStyleWord(true);
        inDetailsForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inDetailsForm.setPlaceHolder("(optional)");
        inDetailsForm.setRoundBottomLeft(10);
        inDetailsForm.setRoundBottomRight(10);
        inDetailsForm.setRoundTopLeft(10);
        inDetailsForm.setRoundTopRight(10);
        jScrollPane2.setViewportView(inDetailsForm);

        userAcessLabel.setText("Users Access");
        userAcessLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        inEventEndDate.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

        inEventEndTime.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

        eventEndLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        eventEndLabel.setText("Event End");

        inEventNameForm.setBorder(null);
        inEventNameForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inEventNameForm.setPlaceHolder("Enter event name");
        inEventNameForm.setRoundBottomLeft(10);
        inEventNameForm.setRoundBottomRight(10);
        inEventNameForm.setRoundTopLeft(10);
        inEventNameForm.setRoundTopRight(10);
        inEventNameForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inEventNameFormActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        eventLabel.setText("Create Event");
        eventLabel.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N

        createEventSubmitButton.setText("Confirm");
        createEventSubmitButton.setBackground(new java.awt.Color(255, 255, 204));
        createEventSubmitButton.setBorder(null);
        createEventSubmitButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        createEventSubmitButton.setRoundBottomLeft(8);
        createEventSubmitButton.setRoundBottomRight(8);
        createEventSubmitButton.setRoundTopLeft(8);
        createEventSubmitButton.setRoundTopRight(8);
        createEventSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createEventSubmitButtonActionPerformed(evt);
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
                .addComponent(createEventSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createEventSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tertiaryAccessButton.setText("Tertiary");
        tertiaryAccessButton.setBorder(null);
        tertiaryAccessButton.setFocusable(false);
        tertiaryAccessButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        tertiaryAccessButton.setRoundBottomLeft(10);
        tertiaryAccessButton.setRoundBottomRight(10);
        tertiaryAccessButton.setRoundTopLeft(10);
        tertiaryAccessButton.setRoundTopRight(10);
        tertiaryAccessButton.setToolTipText("");
        tertiaryAccessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tertiaryAccessButtonActionPerformed(evt);
            }
        });

        juniorHighAccessButton.setText("Junior High");
        juniorHighAccessButton.setBorder(null);
        juniorHighAccessButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        juniorHighAccessButton.setRoundBottomLeft(10);
        juniorHighAccessButton.setRoundBottomRight(10);
        juniorHighAccessButton.setRoundTopLeft(10);
        juniorHighAccessButton.setRoundTopRight(10);

        seniorHighAccessButton.setText("Senior High");
        seniorHighAccessButton.setBorder(null);
        seniorHighAccessButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        seniorHighAccessButton.setRoundBottomLeft(10);
        seniorHighAccessButton.setRoundBottomRight(10);
        seniorHighAccessButton.setRoundTopLeft(10);
        seniorHighAccessButton.setRoundTopRight(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(inFacultyInChargeForm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(inLocationForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inEventNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eventNameLabel)
                                    .addComponent(userAcessLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tertiaryAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(juniorHighAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(seniorHighAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(orgClubInChargeLabel)
                                .addComponent(inClubInChargeForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(locationLabel)))
                    .addComponent(facultyInChargeLabel))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detailsLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(inEventStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(inEventStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(eventStartLabel)
                        .addComponent(eventEndLabel)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(inEventEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(inEventEndTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventNameLabel)
                    .addComponent(eventStartLabel))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inEventNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inEventStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inEventStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userAcessLabel)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tertiaryAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(juniorHighAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seniorHighAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(locationLabel)
                        .addGap(11, 11, 11)
                        .addComponent(inLocationForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(orgClubInChargeLabel)
                        .addGap(11, 11, 11)
                        .addComponent(inClubInChargeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(facultyInChargeLabel)
                        .addGap(11, 11, 11)
                        .addComponent(inFacultyInChargeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eventEndLabel)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inEventEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inEventEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(detailsLabel)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void dateChangedAction(String newDate) {
        startDate = newDate;
    }

    private void timeChangedAction(String newTime) {
        startTime = newTime;
    }
    private void inClubInChargeFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inClubInChargeFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inClubInChargeFormActionPerformed

    private void createEventSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createEventSubmitButtonActionPerformed
        if (validateInputs()) {
            try {
                Statement st = conn().createStatement();
                String checkExistingEventQuery = "SELECT * FROM events WHERE event_name = '" + eventName + "'";
                var rs = st.executeQuery(checkExistingEventQuery);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Event already exists.");
                    return;  // Exit the method to avoid duplicate insertion
                }

                String addEventQuery = "INSERT INTO events (event_name, start_date, end_date, start_time, end_time, location, user_access, club_assigned, faculty_assigned, details, access_tertiary, access_juniorhigh, access_seniorhigh) "
                        + "VALUES ('" + eventName + "', '" + startDate + "', '" + endDate + "', '" + startTime + "', '" + endTime + "', '" + location + "', '" + userAccess + "', '" + clubInCharge + "', '" + facultyInCharge + "', '" + details + "', '" + tertiaryAccess + "', '" + juniorHighAccess + "', '" + seniorHighAccess + "')";
                st.executeUpdate(addEventQuery);

                JOptionPane.showMessageDialog(this, "Event created successfully!");
                dispose();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private boolean validateInputs() {
        boolean isValid = true;
        if (inEventNameForm.getText().isEmpty()) {
            inEventNameForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inEventNameForm.setBorder(null);
            eventName = inEventNameForm.getText();
        }
        if (inEventStartDate.getDate().toString().isEmpty()) {
            inEventStartDate.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inEventStartDate.setBorder(null);
            startDate = inEventStartDate.getDate().toString();
        }
        if (inEventStartTime.getTime().toString().isEmpty()) {
            inEventStartTime.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inEventStartTime.setBorder(null);
            startTime = inEventStartTime.getTime().toString();
        }
        if (inEventEndDate.getDate().toString().isEmpty()) {
            inEventEndDate.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inEventEndDate.setBorder(null);
            endDate = inEventEndDate.getDate().toString();
        }
        if (inEventEndTime.getTime().toString().isEmpty()) {
            inEventEndTime.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inEventEndTime.setBorder(null);
            endTime = inEventEndTime.getTime().toString();
        }
        if (inLocationForm.getText().isEmpty()) {
            inLocationForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inLocationForm.setBorder(null);
            location = inLocationForm.getText();
        }
        if (inClubInChargeForm.getSelectedItem().toString().isEmpty()) {
            inClubInChargeForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inClubInChargeForm.setBorder(null);
            clubInCharge = inClubInChargeForm.getSelectedItem().toString();
        }
        if (inFacultyInChargeForm.getSelectedItem().toString().isEmpty()) {
            inFacultyInChargeForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inFacultyInChargeForm.setBorder(null);
            facultyInCharge = inFacultyInChargeForm.getSelectedItem().toString();
        }
        if (inDetailsForm.getText().isEmpty()) {
            inDetailsForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inDetailsForm.setBorder(null);
            details = inDetailsForm.getText();
        }
        if (tertiaryAccessButton.isSelected()) {
            tertiaryAccess = 1;
            userAccess = "Tertiary";

        } else {
            tertiaryAccess = 0;
        }
        if (juniorHighAccessButton.isSelected()) {
            juniorHighAccess = 1;
            if (userAccess.isEmpty()) {
                userAccess = "Junior High";
            } else {
                userAccess += ", Junior High";
            }
        } else {
            juniorHighAccess = 0;
        }
        if (seniorHighAccessButton.isSelected()) {
            seniorHighAccess = 1;
            if (userAccess.isEmpty()) {
                userAccess = "Senior High";
            } else {
                userAccess += ", Senior High";
            }
        } else {
            seniorHighAccess = 0;
        }

        return isValid;

    }//GEN-LAST:event_createEventSubmitButtonActionPerformed

    private void inLocationFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inLocationFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inLocationFormActionPerformed

    private void inFacultyInChargeFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inFacultyInChargeFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inFacultyInChargeFormActionPerformed

    private void inEventNameFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inEventNameFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inEventNameFormActionPerformed

    private void tertiaryAccessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tertiaryAccessButtonActionPerformed

    }//GEN-LAST:event_tertiaryAccessButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private test.ButtonRound createEventSubmitButton;
    private javax.swing.JLabel detailsLabel;
    private javax.swing.JLabel eventEndLabel;
    private javax.swing.JLabel eventLabel;
    private javax.swing.JLabel eventNameLabel;
    private javax.swing.JLabel eventStartLabel;
    private javax.swing.JLabel facultyInChargeLabel;
    private test.ComboBoxRound inClubInChargeForm;
    private test.TextAreaRound inDetailsForm;
    private com.github.lgooddatepicker.components.DatePicker inEventEndDate;
    private com.github.lgooddatepicker.components.TimePicker inEventEndTime;
    private test.TextFieldRound inEventNameForm;
    private com.github.lgooddatepicker.components.DatePicker inEventStartDate;
    private com.github.lgooddatepicker.components.TimePicker inEventStartTime;
    private test.ComboBoxRound inFacultyInChargeForm;
    private test.TextFieldRound inLocationForm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private test.ToggleButtonRound juniorHighAccessButton;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel orgClubInChargeLabel;
    private test.ToggleButtonRound seniorHighAccessButton;
    private test.ToggleButtonRound tertiaryAccessButton;
    private javax.swing.JLabel userAcessLabel;
    // End of variables declaration//GEN-END:variables
}
