package campusconnect;

import static campusconnect.CampusConnect.conn;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;

class EventsForm extends javax.swing.JFrame {

    //current date
    LocalDate date = LocalDate.now();

    DefaultComboBoxModel<Object> orgsComboBoxModel = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Object> facultyComboBoxModel = new DefaultComboBoxModel<>();

    //init variables
    String eventName;
    String startDate;
    String endDate;
    String location;
    String userAccess = "";
    String clubInCharge;
    String facultyInCharge;
    String details;
    String status;

    public EventsForm() {
        initComponents();
        initOrgs();

        // Add action listener for the date picker
        inEventStartDate.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                dateChangedAction(event.getNewDate().toString());
            }
        });

        inEventEndDate.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                dateChangedAction(event.getNewDate().toString());
            }
        });

    }

    public void setEventDetails(String eventName, String userAccess, String location, String orgInCharge, String facultyInCharge, LocalDate startDate, LocalDate endDate, String details) {
        tertiaryAccessButton.setSelected(false);
        secondaryAccessButton.setSelected(false);

        inEventNameForm.setText(eventName);
        if (userAccess.contains("Tertiary")) {
            tertiaryAccessButton.setSelected(true);
        }
        if (userAccess.contains("Secondary")) {
            secondaryAccessButton.setSelected(true);
        }

        inLocationForm.setText(location);
        inOrgInChargeForm.setSelectedItem(orgInCharge);
        inFacultyInChargeForm.setSelectedItem(facultyInCharge);
        inEventStartDate.setDate(startDate);
        inEventEndDate.setDate(endDate);
        inDetailsForm.setText(details);
        // Set other relevant fields as necessary
    }

    private void initOrgs() {
        try {
            Statement st = conn().createStatement();

            String selectClubs = "SELECT * FROM orgs";
            ResultSet scrs = st.executeQuery(selectClubs);

            while (scrs.next()) {
                String orn = scrs.getString("org_name");
                orgsComboBoxModel.addElement(orn);

            }

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

        eventNameLabel = new javax.swing.JLabel();
        eventStartLabel = new javax.swing.JLabel();
        inOrgInChargeForm = new test.ComboBoxRound();
        inEventStartDate = new com.github.lgooddatepicker.components.DatePicker();
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
        eventEndLabel = new javax.swing.JLabel();
        inEventNameForm = new test.TextFieldRound();
        jPanel1 = new javax.swing.JPanel();
        eventLabel = new javax.swing.JLabel();
        createEventSubmitButton = new test.ButtonRound();
        tertiaryAccessButton = new test.ToggleButtonRound();
        secondaryAccessButton = new test.ToggleButtonRound();
        eventWarning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        eventNameLabel.setText("Event Name");
        eventNameLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        eventStartLabel.setText("Event Start");
        eventStartLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        inOrgInChargeForm.setModel(orgsComboBoxModel);
        inOrgInChargeForm.setBorder(null);
        inOrgInChargeForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inOrgInChargeForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inOrgInChargeFormActionPerformed(evt);
            }
        });

        inEventStartDate.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

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

        facultyInChargeLabel.setText("Faculty In-Charge");
        facultyInChargeLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        inFacultyInChargeForm.setModel(facultyComboBoxModel);
        inFacultyInChargeForm.setBorder(null);
        inFacultyInChargeForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        detailsLabel.setText("Additional Details");
        detailsLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        jScrollPane2.setBorder(null);

        inDetailsForm.setColumns(20);
        inDetailsForm.setLineWrap(true);
        inDetailsForm.setRows(5);
        inDetailsForm.setWrapStyleWord(true);
        inDetailsForm.setBorder(null);
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

        eventEndLabel.setText("Event End");
        eventEndLabel.setFont(new java.awt.Font("Inter Medium", 0, 15)); // NOI18N

        inEventNameForm.setBorder(null);
        inEventNameForm.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        inEventNameForm.setPlaceHolder("Enter event name");
        inEventNameForm.setRoundBottomLeft(10);
        inEventNameForm.setRoundBottomRight(10);
        inEventNameForm.setRoundTopLeft(10);
        inEventNameForm.setRoundTopRight(10);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        eventLabel.setText("Create Event");
        eventLabel.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N

        createEventSubmitButton.setText(" Confirm");
        createEventSubmitButton.setBackground(new java.awt.Color(255, 255, 204));
        createEventSubmitButton.setBorder(null);
        createEventSubmitButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        createEventSubmitButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
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

        secondaryAccessButton.setText("Secondary");
        secondaryAccessButton.setBorder(null);
        secondaryAccessButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        secondaryAccessButton.setRoundBottomLeft(10);
        secondaryAccessButton.setRoundBottomRight(10);
        secondaryAccessButton.setRoundTopLeft(10);
        secondaryAccessButton.setRoundTopRight(10);

        eventWarning.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        eventWarning.setForeground(new java.awt.Color(255, 0, 0));
        eventWarning.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eventWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(inFacultyInChargeForm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(inLocationForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(inEventNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(eventNameLabel))
                                            .addGap(1, 1, 1))
                                        .addComponent(orgClubInChargeLabel)
                                        .addComponent(inOrgInChargeForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(locationLabel)))
                            .addComponent(facultyInChargeLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tertiaryAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(secondaryAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(userAcessLabel))
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inEventStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(detailsLabel)
                                    .addComponent(eventStartLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(eventEndLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(inEventEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(50, 50, 50))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventNameLabel)
                    .addComponent(eventStartLabel)
                    .addComponent(eventEndLabel))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inEventNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inEventStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inEventEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(eventWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userAcessLabel)
                    .addComponent(detailsLabel))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tertiaryAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secondaryAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(locationLabel)
                        .addGap(11, 11, 11)
                        .addComponent(inLocationForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(orgClubInChargeLabel)
                        .addGap(11, 11, 11)
                        .addComponent(inOrgInChargeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(facultyInChargeLabel)
                        .addGap(11, 11, 11)
                        .addComponent(inFacultyInChargeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void dateChangedAction(String newDate) {
        startDate = newDate;
    }

    private void inOrgInChargeFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inOrgInChargeFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inOrgInChargeFormActionPerformed

    private void createEventSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createEventSubmitButtonActionPerformed
        if (validateInputs()) {
            try (Connection conn = conn()) {
                int selectedRow = CampusConnect.getInstance().activeTable().getSelectedRow();
                String existingEvent = (selectedRow >= 0) ? CampusConnect.getInstance().activeTable().getValueAt(selectedRow, 0).toString() : "";

                // Proceed in add
                if (selectedRow < 0) {
                    System.out.println("HERE IN ADD");
                    String addEventQuery = "INSERT INTO events (event_name, start_date, end_date, location, user_access, club_assigned, faculty_assigned, details, status) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement insertPstmt = conn.prepareStatement(addEventQuery)) {
                        insertPstmt.setString(1, eventName);
                        insertPstmt.setString(2, startDate);
                        insertPstmt.setString(3, endDate);
                        insertPstmt.setString(4, location);
                        insertPstmt.setString(5, userAccess);
                        insertPstmt.setString(6, clubInCharge);
                        insertPstmt.setString(7, facultyInCharge);
                        insertPstmt.setString(8, details);
                        insertPstmt.setString(9, status);
                        insertPstmt.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(this, "Event created successfully!");

                    // Proceed in editing event
                } else {
                    System.out.println("HERE IN EDIT");
                    String updateQuery = "UPDATE events SET event_name = ?, start_date = ?, end_date = ?, location = ?, user_access = ?, club_assigned = ?, faculty_assigned = ?, details = ?, status = ? WHERE event_name = ?";
                    try (PreparedStatement updatePstmt = conn.prepareStatement(updateQuery)) {
                        updatePstmt.setString(1, eventName);
                        updatePstmt.setString(2, startDate);
                        updatePstmt.setString(3, endDate);
                        updatePstmt.setString(4, location);
                        updatePstmt.setString(5, userAccess);
                        updatePstmt.setString(6, clubInCharge);
                        updatePstmt.setString(7, facultyInCharge);
                        updatePstmt.setString(8, details);
                        updatePstmt.setString(9, status);
                        updatePstmt.setString(10, existingEvent);
                        updatePstmt.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(this, "Event updated successfully!");
                }

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

            String eventExistingChecker = "SELECT * FROM events WHERE is_deleted = 0";
            try (ResultSet rp = st.executeQuery(eventExistingChecker)) {
                boolean eventExists = false;

                while (rp.next()) {
                    String eventNString = rp.getString("event_name");
                    System.out.println(eventNString);

                    // Check if event name exists
                    if (inEventNameForm.getText().equalsIgnoreCase(eventNString)) {
                        eventWarning.setText("Event name exists");
                        inEventNameForm.setBorder(new LineBorder(Color.RED, 1));
                        isValid = false;
                        eventExists = true;
                        break;
                    }
                }

                // Check if event name is empty
                if (!eventExists) {
                    if (inEventNameForm.getText().isEmpty()) {
                        eventWarning.setText("Cannot be empty.");
                        inEventNameForm.setBorder(new LineBorder(Color.RED, 1));
                        isValid = false;
                    } else {
                        eventWarning.setText("");
                        inEventNameForm.setBorder(null);
                        eventName = inEventNameForm.getText();
                    }
                }
            }

            // Validate start date
            if (inEventStartDate.getDate() == null) {
                inEventStartDate.setBorder(new LineBorder(Color.RED, 1));
                isValid = false;
            } else {
                inEventStartDate.setBorder(null);
                startDate = inEventStartDate.getDate().toString();
            }

            // Validate end date
            if (inEventEndDate.getDate() == null) {
                inEventEndDate.setBorder(new LineBorder(Color.RED, 1));
                isValid = false;
            } else {
                inEventEndDate.setBorder(null);
                endDate = inEventEndDate.getDate().toString();
            }

            // Validate location
            if (inLocationForm.getText().isEmpty()) {
                inLocationForm.setBorder(new LineBorder(Color.RED, 1));
                isValid = false;
            } else {
                inLocationForm.setBorder(null);
                location = inLocationForm.getText();
            }

            // Validate club in charge
            if (inOrgInChargeForm.getSelectedItem() == null || inOrgInChargeForm.getSelectedItem().toString().isEmpty()) {
                inOrgInChargeForm.setBorder(new LineBorder(Color.RED, 1));
                isValid = false;
            } else {
                inOrgInChargeForm.setBorder(null);
                clubInCharge = inOrgInChargeForm.getSelectedItem().toString();
            }

            // Validate faculty in charge
            if (inFacultyInChargeForm.getSelectedItem() == null || inFacultyInChargeForm.getSelectedItem().toString().isEmpty()) {
                inFacultyInChargeForm.setBorder(new LineBorder(Color.RED, 1));
                isValid = false;
            } else {
                inFacultyInChargeForm.setBorder(null);
                facultyInCharge = inFacultyInChargeForm.getSelectedItem().toString();
            }

            // Validate details
            if (inDetailsForm.getText().isEmpty()) {
                inDetailsForm.setBorder(new LineBorder(Color.RED, 1));
                isValid = false;
            } else {
                inDetailsForm.setBorder(null);
                details = inDetailsForm.getText();
            }

            // Set user access
            userAccess = "";
            if (tertiaryAccessButton.isSelected()) {
                userAccess = "Tertiary";
            }
            if (secondaryAccessButton.isSelected()) {
                if (userAccess.isEmpty()) {
                    userAccess = "Secondary";
                } else {
                    userAccess += ", Secondary";
                }
            }

            // Set event status based on dates
            LocalDate today = LocalDate.now();
            if (inEventStartDate.getDate().isAfter(today)) {
                status = "INCOMING";
            } else if (inEventStartDate.getDate().isEqual(today)
                    || (inEventStartDate.getDate().isBefore(today) && inEventEndDate.getDate().isAfter(today))) {
                status = "ONGOING";
            } else if (inEventEndDate.getDate().isBefore(today) || inEventEndDate.getDate().isEqual(today)) {
                status = "FINISHED";
            }

            System.out.println(inEventStartDate.getDate());
            System.out.println(today);

            return isValid;
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            return false;
        }

    }//GEN-LAST:event_createEventSubmitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private test.ButtonRound createEventSubmitButton;
    private javax.swing.JLabel detailsLabel;
    private javax.swing.JLabel eventEndLabel;
    private javax.swing.JLabel eventLabel;
    private javax.swing.JLabel eventNameLabel;
    private javax.swing.JLabel eventStartLabel;
    private javax.swing.JLabel eventWarning;
    private javax.swing.JLabel facultyInChargeLabel;
    private test.TextAreaRound inDetailsForm;
    private com.github.lgooddatepicker.components.DatePicker inEventEndDate;
    private test.TextFieldRound inEventNameForm;
    private com.github.lgooddatepicker.components.DatePicker inEventStartDate;
    private test.ComboBoxRound inFacultyInChargeForm;
    private test.TextFieldRound inLocationForm;
    private test.ComboBoxRound inOrgInChargeForm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel orgClubInChargeLabel;
    private test.ToggleButtonRound secondaryAccessButton;
    private test.ToggleButtonRound tertiaryAccessButton;
    private javax.swing.JLabel userAcessLabel;
    // End of variables declaration//GEN-END:variables
}
