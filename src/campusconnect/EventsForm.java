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

public class EventsForm extends javax.swing.JFrame {

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
    String date;
    String time;
    String location;
    String forWhom;
    String clubAssigned;
    String facultyAssigned;
    String details;

    public EventsForm() {
        initComponents();

        // Add action listener for the date picker
        inDateForm.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                dateChangedAction(event.getNewDate().toString());
            }
        });

        // Add action listener for the time picker
        inTimeForm.addTimeChangeListener(new TimeChangeListener() {
            @Override
            public void timeChanged(TimeChangeEvent event) {
                timeChangedAction(event.getNewTime().toString());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEvent = new javax.swing.JLabel();
        inEventNameForm = new test.TextFieldRound();
        jLabel1 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        inClubAssignedForm = new test.ComboBoxRound();
        createEventSubmitButton = new test.ButtonRound();
        inDateForm = new com.github.lgooddatepicker.components.DatePicker();
        inTimeForm = new com.github.lgooddatepicker.components.TimePicker();
        timeLabel = new javax.swing.JLabel();
        dateLabel1 = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        inLocationForm = new test.TextFieldRound();
        dateLabel2 = new javax.swing.JLabel();
        inFacultyAssignedForm = new test.ComboBoxRound();
        inDescriptionForm = new test.TextFieldRound();
        descriptionLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        lblEvent.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        lblEvent.setText("Create Event");

        inEventNameForm.setBorder(null);
        inEventNameForm.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
        inEventNameForm.setPlaceHolder("Enter event name");
        inEventNameForm.setRoundBottomLeft(8);
        inEventNameForm.setRoundBottomRight(8);
        inEventNameForm.setRoundTopLeft(8);
        inEventNameForm.setRoundTopRight(8);

        jLabel1.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel1.setText("Title/Name of Event");

        dateLabel.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        dateLabel.setText("Date");

        inClubAssignedForm.setBorder(null);
        inClubAssignedForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inClubAssignedFormActionPerformed(evt);
            }
        });

        createEventSubmitButton.setBackground(new java.awt.Color(255, 255, 204));
        createEventSubmitButton.setBorder(null);
        createEventSubmitButton.setText("Confirm");
        createEventSubmitButton.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        createEventSubmitButton.setRoundBottomLeft(8);
        createEventSubmitButton.setRoundBottomRight(8);
        createEventSubmitButton.setRoundTopLeft(8);
        createEventSubmitButton.setRoundTopRight(8);
        createEventSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createEventSubmitButtonActionPerformed(evt);
            }
        });

        timeLabel.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        timeLabel.setText("Time");

        dateLabel1.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        dateLabel1.setText("Club Assigned");

        locationLabel.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        locationLabel.setText("Location");

        inLocationForm.setBorder(null);
        inLocationForm.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
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

        dateLabel2.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        dateLabel2.setText("Faculty Assigned");

        inFacultyAssignedForm.setBorder(null);
        inFacultyAssignedForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inFacultyAssignedFormActionPerformed(evt);
            }
        });

        inDescriptionForm.setBorder(null);
        inDescriptionForm.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
        inDescriptionForm.setPlaceHolder("(optional)");
        inDescriptionForm.setRoundBottomLeft(8);
        inDescriptionForm.setRoundBottomRight(8);
        inDescriptionForm.setRoundTopLeft(8);
        inDescriptionForm.setRoundTopRight(8);
        inDescriptionForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inDescriptionFormActionPerformed(evt);
            }
        });

        descriptionLabel.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        descriptionLabel.setText("Desription");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descriptionLabel)
                    .addComponent(dateLabel2)
                    .addComponent(inLocationForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(locationLabel)
                    .addComponent(dateLabel1)
                    .addComponent(jLabel1)
                    .addComponent(inClubAssignedForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblEvent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createEventSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inEventNameForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inDateForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dateLabel)
                                .addGap(193, 193, 193)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timeLabel)
                            .addComponent(inTimeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(inFacultyAssignedForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inDescriptionForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(createEventSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(inEventNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(timeLabel))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inDateForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inTimeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(locationLabel)
                .addGap(0, 0, 0)
                .addComponent(inLocationForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(dateLabel1)
                .addGap(0, 0, 0)
                .addComponent(inClubAssignedForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(dateLabel2)
                .addGap(0, 0, 0)
                .addComponent(inFacultyAssignedForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(descriptionLabel)
                .addGap(0, 0, 0)
                .addComponent(inDescriptionForm, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void dateChangedAction(String newDate) {
        date = newDate;
    }

    private void timeChangedAction(String newTime) {
        time = newTime;
    }
    private void inClubAssignedFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inClubAssignedFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inClubAssignedFormActionPerformed

    private void createEventSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createEventSubmitButtonActionPerformed
        if (validateInputs()) {
            if (conn != null) {
                try {
                    java.sql.Statement st = conn.createStatement();
                    
                    // Insert new user into database
                    String addEventQuery = "INSERT INTO events (event_name, date, time, location, for_whom, club_assigned, faculty_assigned, details) "
                            + "VALUES ('" + eventName + "', '" + date + "', '" + time + "', '" + location + "', '" + forWhom + "', '" + clubAssigned + "', '" + facultyAssigned + "', '" + details + "')";
                    st.executeUpdate(addEventQuery);
                    JOptionPane.showMessageDialog(this, "Account created successfully!");
                   

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                }
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
        if (inDateForm.getText().isEmpty()) {
            inDateForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inDateForm.setBorder(null);
            date = inDateForm.getText();
        }
        if (inTimeForm.getText().isEmpty()) {
            inTimeForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inTimeForm.setBorder(null);
            time = inTimeForm.getText();
        }
        if (inLocationForm.getText().isEmpty()) {
            inLocationForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inLocationForm.setBorder(null);
            location = inLocationForm.getText();
        }
        if (inClubAssignedForm.getSelectedItem().toString().isEmpty()) {
            inClubAssignedForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inClubAssignedForm.setBorder(null);
            clubAssigned = inClubAssignedForm.getSelectedItem().toString();
        }
        if (inFacultyAssignedForm.getSelectedItem().toString().isEmpty()) {
            inFacultyAssignedForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inFacultyAssignedForm.setBorder(null);
            facultyAssigned = inFacultyAssignedForm.getSelectedItem().toString();
        }
        return isValid;

    }//GEN-LAST:event_createEventSubmitButtonActionPerformed

    private void inLocationFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inLocationFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inLocationFormActionPerformed

    private void inFacultyAssignedFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inFacultyAssignedFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inFacultyAssignedFormActionPerformed

    private void inDescriptionFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inDescriptionFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inDescriptionFormActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private test.ButtonRound createEventSubmitButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dateLabel1;
    private javax.swing.JLabel dateLabel2;
    private javax.swing.JLabel descriptionLabel;
    private test.ComboBoxRound inClubAssignedForm;
    private com.github.lgooddatepicker.components.DatePicker inDateForm;
    private test.TextFieldRound inDescriptionForm;
    private test.TextFieldRound inEventNameForm;
    private test.ComboBoxRound inFacultyAssignedForm;
    private test.TextFieldRound inLocationForm;
    private com.github.lgooddatepicker.components.TimePicker inTimeForm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblEvent;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
