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
    String fromDate;
    String fromTime;
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

        jLabel1 = new javax.swing.JLabel();
        eventStartDate = new javax.swing.JLabel();
        inClubAssignedForm = new test.ComboBoxRound();
        inDateForm = new com.github.lgooddatepicker.components.DatePicker();
        inTimeForm = new com.github.lgooddatepicker.components.TimePicker();
        dateLabel1 = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        inLocationForm = new test.TextFieldRound();
        dateLabel2 = new javax.swing.JLabel();
        inFacultyAssignedForm = new test.ComboBoxRound();
        descriptionLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inDetailsForm = new test.TextAreaRound();
        inForWhom = new javax.swing.JLabel();
        inDateForm1 = new com.github.lgooddatepicker.components.DatePicker();
        inTimeForm1 = new com.github.lgooddatepicker.components.TimePicker();
        dateLabel3 = new javax.swing.JLabel();
        timeLabel3 = new javax.swing.JLabel();
        inEventNameForm = new test.TextFieldRound();
        jPanel1 = new javax.swing.JPanel();
        lblEvent = new javax.swing.JLabel();
        createEventSubmitButton = new test.ButtonRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setText("Title/Name of Event");
        jLabel1.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        eventStartDate.setText("Event Start");
        eventStartDate.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        inClubAssignedForm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Music", "Dance", "Csc Org" }));
        inClubAssignedForm.setBorder(null);
        inClubAssignedForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inClubAssignedForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inClubAssignedFormActionPerformed(evt);
            }
        });

        dateLabel1.setText("Org/Club Assigned");
        dateLabel1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        locationLabel.setText("Location");
        locationLabel.setFocusable(false);
        locationLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        inLocationForm.setBorder(null);
        inLocationForm.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
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

        dateLabel2.setText("Faculty Assigned");
        dateLabel2.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        inFacultyAssignedForm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elvis Atento", "Babylyn Ramos", "Akeem Pedrasa daddy" }));
        inFacultyAssignedForm.setBorder(null);
        inFacultyAssignedForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inFacultyAssignedForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inFacultyAssignedFormActionPerformed(evt);
            }
        });

        descriptionLabel.setText("Additional Details");
        descriptionLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        jScrollPane2.setBorder(null);

        inDetailsForm.setBorder(null);
        inDetailsForm.setColumns(20);
        inDetailsForm.setLineWrap(true);
        inDetailsForm.setRows(5);
        inDetailsForm.setWrapStyleWord(true);
        inDetailsForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inDetailsForm.setPlaceHolder("(optional)");
        inDetailsForm.setRoundBottomLeft(8);
        inDetailsForm.setRoundBottomRight(8);
        inDetailsForm.setRoundTopLeft(8);
        inDetailsForm.setRoundTopRight(8);
        jScrollPane2.setViewportView(inDetailsForm);

        inForWhom.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        inForWhom.setText("Users Access");

        dateLabel3.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N
        dateLabel3.setText("Event End");

        timeLabel3.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        timeLabel3.setText("To:");

        inEventNameForm.setBorder(null);
        inEventNameForm.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        inEventNameForm.setPlaceHolder("Enter event name");
        inEventNameForm.setRoundBottomLeft(8);
        inEventNameForm.setRoundBottomRight(8);
        inEventNameForm.setRoundTopLeft(8);
        inEventNameForm.setRoundTopRight(8);
        inEventNameForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inEventNameFormActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblEvent.setText("Create Event");
        lblEvent.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N

        createEventSubmitButton.setText("Confirm");
        createEventSubmitButton.setBackground(new java.awt.Color(255, 255, 204));
        createEventSubmitButton.setBorder(null);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblEvent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createEventSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createEventSubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(descriptionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(locationLabel)
                                    .addComponent(timeLabel3)
                                    .addComponent(inLocationForm, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateLabel1)
                                            .addComponent(inClubAssignedForm, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateLabel2)
                                            .addComponent(inFacultyAssignedForm, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(65, 65, 65))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inEventNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(inForWhom))
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inDateForm, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(inTimeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(eventStartDate)
                            .addComponent(dateLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inDateForm1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(inTimeForm1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(50, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(eventStartDate))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inEventNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inDateForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inTimeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel3)
                    .addComponent(inForWhom))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inDateForm1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inTimeForm1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(timeLabel3)
                .addGap(35, 35, 35)
                .addComponent(locationLabel)
                .addGap(0, 0, 0)
                .addComponent(inLocationForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel1)
                    .addComponent(dateLabel2))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inClubAssignedForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inFacultyAssignedForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(descriptionLabel))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void dateChangedAction(String newDate) {
        fromDate = newDate;
    }

    private void timeChangedAction(String newTime) {
        fromTime = newTime;
    }
    private void inClubAssignedFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inClubAssignedFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inClubAssignedFormActionPerformed

    private void createEventSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createEventSubmitButtonActionPerformed
        if (validateInputs()) {
            try {
                Statement st = conn().createStatement();
                String checkExistingEventQuery = "SELECT * FROM events WHERE event_name = '" + eventName + "'";
                var rs = st.executeQuery(checkExistingEventQuery);
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(this, "Event already exists.");
                    return;  // Exit the method to avoid duplicate insertion
                }
                
                String addEventQuery = "INSERT INTO events (event_name, date, time, location, for_whom, club_assigned, faculty_assigned, description) "
                        + "VALUES ('" + eventName + "', '" + fromDate + "', '" + fromTime + "', '" + location + "', '" + forWhom + "', '" + clubAssigned + "', '" + facultyAssigned + "', '" + details + "')";
                st.executeUpdate(addEventQuery);
                
                JOptionPane.showMessageDialog(this, "Account created successfully!");

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
        if (inDateForm.getDate().toString().isEmpty()) {
            inDateForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inDateForm.setBorder(null);
            fromDate = inDateForm.getDate().toString();
        }
        if (inTimeForm.getTime().toString().isEmpty()) {
            inTimeForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inTimeForm.setBorder(null);
            fromTime = inTimeForm.getTime().toString();
        }
        if (inForWhom.getText().isEmpty()) {
            inForWhom.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inForWhom.setBorder(null);
            forWhom = inForWhom.getText();
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
        if (inDetailsForm.getText().isEmpty()) {
            inDetailsForm.setBorder(new LineBorder(Color.RED, 1));
            isValid = false;
        } else {
            inDetailsForm.setBorder(null);
            details = inDetailsForm.getText();
        }
        return isValid;

    }//GEN-LAST:event_createEventSubmitButtonActionPerformed

    private void inLocationFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inLocationFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inLocationFormActionPerformed

    private void inFacultyAssignedFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inFacultyAssignedFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inFacultyAssignedFormActionPerformed

    private void inEventNameFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inEventNameFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inEventNameFormActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private test.ButtonRound createEventSubmitButton;
    private javax.swing.JLabel dateLabel1;
    private javax.swing.JLabel dateLabel2;
    private javax.swing.JLabel dateLabel3;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel eventStartDate;
    private test.ComboBoxRound inClubAssignedForm;
    private com.github.lgooddatepicker.components.DatePicker inDateForm;
    private com.github.lgooddatepicker.components.DatePicker inDateForm1;
    private test.TextAreaRound inDetailsForm;
    private test.TextFieldRound inEventNameForm;
    private test.ComboBoxRound inFacultyAssignedForm;
    private javax.swing.JLabel inForWhom;
    private test.TextFieldRound inLocationForm;
    private com.github.lgooddatepicker.components.TimePicker inTimeForm;
    private com.github.lgooddatepicker.components.TimePicker inTimeForm1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEvent;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel timeLabel3;
    // End of variables declaration//GEN-END:variables
    }
