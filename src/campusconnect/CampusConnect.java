package campusconnect;

import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CampusConnect extends JFrame {

    //instantiate connection
    Connection conn = conn();

    //instantiate an static frame of mainFrame
    private static CampusConnect instance;

    private JTable activeDetailedTable = null;

    //table models
    
    DefaultTableModel orgsModel;
    DefaultTableModel eventsModel;
    DefaultTableModel usersModel;
    
    
    public CampusConnect() {
        getContentPane().setBackground(new java.awt.Color(241, 240, 232));
        instance = this;
        initComponents();
        fetchMainTableData();
        updateDashboardDetails();
    }

    public static CampusConnect getInstance() {
        return instance;
    }

    //establish connection
    public static Connection conn() {
        try {
            String url = "jdbc:mysql://localhost:3306/campusconnect";
            String user = "justi";
            String password = "1234";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    //refresh data on tables
    public void refreshMainTableData() {
        // Clear the existing table data
        orgsModel.setRowCount(0);
        eventsModel.setRowCount(0);
        usersModel.setRowCount(0);
        // Reinitialize the data
        fetchMainTableData();
    }

    public void refreshTables() {
        refreshMembersTable();
        refreshEventParticipantsTable();
    }

    private void refreshMembersTable() {
        DefaultTableModel membersTableModel = (DefaultTableModel) membersTable.getModel();
        membersTableModel.setRowCount(0); // Clear existing rows

        try (Statement st = conn.createStatement()) {
            String orgName = getVisibleOrgName();
            String fetchMembersSQL = "SELECT members FROM orgs WHERE org_name = '" + orgName + "'";
            ResultSet rs = st.executeQuery(fetchMembersSQL);

            if (rs.next()) {
                String members = rs.getString("members");
                if (members != null && !members.isBlank()) {
                    String[] membersIDArray = members.split(" ");
                    for (String memberID : membersIDArray) {
                        String getStudentDetailsSQL = "SELECT * FROM users WHERE user_id = '02000" + memberID + "'";
                        ResultSet rt = st.executeQuery(getStudentDetailsSQL);
                        if (rt.next()) {
                            String studentID = rt.getString("user_id");
                            String studentName = rt.getString("user_name");
                            String level = rt.getString("student_type");
                            membersTableModel.addRow(new Object[]{studentID, studentName, level});
                        }
                    }
                }
            }
            totalMembersInfo.setText(membersTable.getRowCount() + "");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void refreshEventParticipantsTable() {
        DefaultTableModel eventParticipantsTableModel = (DefaultTableModel) eventParticipantsTable.getModel();
        eventParticipantsTableModel.setRowCount(0); // Clear existing rows

        try (Statement st = conn.createStatement()) {
            String eventName = getVisibleEventName();
            String fetchParticipantsSQL = "SELECT participants FROM events WHERE event_name = '" + eventName + "'";
            ResultSet rs = st.executeQuery(fetchParticipantsSQL);

            if (rs.next()) {
                String participants = rs.getString("participants");
                if (participants != null && !participants.isBlank()) {
                    String[] participantIDArray = participants.split(" ");
                    for (String participantID : participantIDArray) {
                        String getStudentDetailsSQL = "SELECT * FROM users WHERE user_id = '02000" + participantID + "'";
                        ResultSet rt = st.executeQuery(getStudentDetailsSQL);
                        if (rt.next()) {
                            String studentID = rt.getString("user_id");
                            String studentName = rt.getString("user_name");
                            String level = rt.getString("student_type");
                            eventParticipantsTableModel.addRow(new Object[]{studentID, studentName, level});
                        }
                    }
                }
            }

            totalParticipantsInfo.setText(eventParticipantsTable.getRowCount() + "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public JTable activeTable() {
        JTable activeTable = null;

        if (ClubsAndOrgsPanel.isVisible()) {
            activeTable = orgsTable;
        } else if (EventsPanel.isVisible()) {
            activeTable = eventsTable;
        } else if (UsersPanel.isVisible()) {
            activeTable = usersTable;
        }
        return activeTable;
    }

    public JTable activeDetailedTable() {
        if (orgsDetailedPanel.isVisible()) {
            activeDetailedTable = membersTable;
        } else if (eventsDetailedPanel.isVisible()) {
            activeDetailedTable = eventParticipantsTable;
        }
        return activeDetailedTable;
    }

    public String getVisibleEventName() {
        if (eventsDetailedPanel.isVisible()) {
            return eventNameInfo.getText();
        }
        return null;
    }

    public String getVisibleOrgName() {
        if (orgsDetailedPanel.isVisible()) {
            return orgsNameInfo.getText();
        }
        return null;
    }

    private void updateDashboardDetails() {
        totalUsers.setText(usersTable.getRowCount() + "");
        totalEvents.setText(eventsTable.getRowCount() + "");
    }

    //fetch mainpanels tabledata
    private void fetchMainTableData() {
        orgsModel = (DefaultTableModel) orgsTable.getModel();
        eventsModel = (DefaultTableModel) eventsTable.getModel();
        usersModel = (DefaultTableModel) usersTable.getModel();
        try {
            Statement st = conn.createStatement();

            //adds event data from database to eventsTable
            String selectEventsData = "SELECT * FROM events WHERE is_deleted = 0";
            ResultSet evrs = st.executeQuery(selectEventsData);

            while (evrs.next()) {
                String evn = evrs.getString("event_name");
                String fdt = evrs.getString("start_date");
                String fw = evrs.getString("user_access");
                String de = evrs.getString("details");
                String ss = evrs.getString("status");
                

                eventsModel.addRow(new Object[]{evn, fdt, fw, de, ss});

                //add total number of members in this part
            }

            //adds users data from database to usersTable
            String selectUsersData = "SELECT * FROM users WHERE is_deleted = 0";
            ResultSet usrs = st.executeQuery(selectUsersData);

            while (usrs.next()) {
                String uid = usrs.getString("user_id");
                String unm = usrs.getString("user_name");
                String uty = usrs.getString("user_type");
                String sty = usrs.getString("student_type");
                if (sty.equals("null")) {
                    sty = "";
                }

                usersModel.addRow(new Object[]{uid, unm, uty, sty});
            }

            //adds orgs data from database to orgsTable
            String selectOrgsData = "SELECT * FROM orgs WHERE is_deleted = 0";
            ResultSet orrs = st.executeQuery(selectOrgsData);

            while (orrs.next()) {
                String orn = orrs.getString("org_name");
                String mbc = orrs.getString("member_count");
                String lvl = orrs.getString("level");
                String adv = orrs.getString("adviser");
                String det = orrs.getString("details");

                //add total number of members in this part
                orgsModel.addRow(new Object[]{orn, mbc, lvl, adv, det});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    //fetch data depending on activetable
    private void fetchInternalTableData(JTable activeTable, int selectedRow) {
        if (activeTable == orgsTable) {
            fetchOrgData(selectedRow);
        } else if (activeTable == eventsTable) {
            fetchEventData(selectedRow);
        } else if (activeTable == usersTable) {
            fetchUserData(selectedRow);
        }
    }

    private void fetchOrgData(int selectedRow) {
        ArrayList<String> membersList = new ArrayList<>();
        DefaultTableModel membersTableModel = (DefaultTableModel) membersTable.getModel();
        DefaultTableModel orgEventTableModel = (DefaultTableModel) orgEventTable.getModel();

        membersTableModel.setRowCount(0);
        orgEventTableModel.setRowCount(0);
        membersList.clear();

        try {
            Statement st = conn.createStatement();
            String getOrgNameSQL = orgsTable.getValueAt(selectedRow, 0).toString();
            String openOrgDetailsSQL = "SELECT * FROM orgs WHERE org_name = '" + getOrgNameSQL + "'";
            var rs = st.executeQuery(openOrgDetailsSQL);

            if (rs.next()) {
                String orgName = rs.getString("org_name");
                String adviser = rs.getString("adviser");
                String members = rs.getString("members");

                if (members != null && !members.isBlank()) {
                    String[] membersIDArray = members.split(" ");
                    for (String membersID : membersIDArray) {
                        membersList.add("02000" + membersID);
                    }
                    for (String memberID : membersList) {
                        String getStudentDetailsSQL = "SELECT * FROM users WHERE user_id = '" + memberID + "'";
                        var rt = st.executeQuery(getStudentDetailsSQL);
                        if (rt.next()) {
                            String studentID = rt.getString("user_id");
                            String studentName = rt.getString("user_name");
                            String level = rt.getString("student_type");
                            membersTableModel.addRow(new Object[]{studentID, studentName, level});
                        }
                    }
                }

                String getOrgEventsSQL = "SELECT * FROM events WHERE club_assigned = '" + orgName + "'";
                var rt = st.executeQuery(getOrgEventsSQL);
                while (rt.next()) {
                    String eventName = rt.getString("event_name");
                    String startDate = rt.getString("start_date");
                    String status = rt.getString("status");
                    orgEventTableModel.addRow(new Object[]{eventName, startDate, status});
                }

                int rowCount = 0;
                for (int i = 0; i < orgEventTableModel.getRowCount(); i++) {
                    if ("ONGOING".equals(orgEventTableModel.getValueAt(i, 2))) {
                        rowCount++;
                    }
                }

                orgsNameInfo.setText(orgName);
                adviserInfo.setText(adviser);
                orgOngoingEventsInfo.setText(rowCount + "");
                totalMembersInfo.setText(membersTableModel.getRowCount() + "");

                eventsDetailedPanel.setVisible(false);
                orgsDetailedPanel.setVisible(true);
                studentDetailedPanel.setVisible(false);
                DashBoardPanel.setVisible(false);
                ClubsAndOrgsPanel.setVisible(false);
                EventsPanel.setVisible(false);
                UsersPanel.setVisible(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void fetchEventData(int selectedRow) {
        ArrayList<String> eventParticipantsList = new ArrayList<>();
        DefaultTableModel eventParticipantsTableModel = (DefaultTableModel) eventParticipantsTable.getModel();

        eventParticipantsTableModel.setRowCount(0);
        eventParticipantsList.clear();

        try {
            Statement st = conn.createStatement();
            String getEventTitle = eventsTable.getValueAt(selectedRow, 0).toString();
            String openEvent = "SELECT * FROM events WHERE event_name = '" + getEventTitle + "'";
            var rr = st.executeQuery(openEvent);

            if (rr.next()) {
                String eventName = rr.getString("event_name");
                String startDate = rr.getString("start_date");
                String endDate = rr.getString("end_date");
                String facultyAssigned = rr.getString("faculty_assigned");
                String status = rr.getString("status");
                String participants = rr.getString("participants");

                if (participants != null && !participants.isBlank()) {
                    String[] participantIDArray = participants.split(" ");
                    for (String participantID : participantIDArray) {
                        eventParticipantsList.add("02000" + participantID);
                    }
                    for (String participantID : eventParticipantsList) {
                        String getStudentDetailsSQL = "SELECT * FROM users WHERE user_id = '" + participantID + "'";
                        var rt = st.executeQuery(getStudentDetailsSQL);
                        if (rt.next()) {
                            String studentID = rt.getString("user_id");
                            String studentName = rt.getString("user_name");
                            String level = rt.getString("student_type");
                            eventParticipantsTableModel.addRow(new Object[]{studentID, studentName, level});
                        }
                    }
                }

                eventNameInfo.setText(eventName);
                officerInChargeInfo.setText(facultyAssigned);
                eventStatusInfo.setText(status);
                totalParticipantsInfo.setText(eventParticipantsTableModel.getRowCount() + "");

                eventsDetailedPanel.setVisible(true);
                orgsDetailedPanel.setVisible(false);
                studentDetailedPanel.setVisible(false);
                DashBoardPanel.setVisible(false);
                ClubsAndOrgsPanel.setVisible(false);
                EventsPanel.setVisible(false);
                UsersPanel.setVisible(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void fetchUserData(int selectedRow) {
        DefaultTableModel studentParticipatedEventsTableModel = (DefaultTableModel) studentParticipatedEventsTable.getModel();
        DefaultTableModel studentOrgsJoinedTableModel = (DefaultTableModel) studentOrgsJoinedTable.getModel();
        DefaultTableModel facultyEventsHandledTableModel = (DefaultTableModel) facultyHeadedEventsTable.getModel();
        DefaultTableModel facultyOrgAdvisoryTableModel = (DefaultTableModel) facultyOrgAdvisoryTable.getModel();
        ArrayList eventParticipantsArrayList = new ArrayList();

        studentParticipatedEventsTableModel.setRowCount(0);
        studentOrgsJoinedTableModel.setRowCount(0);

        try {
            Statement st = conn.createStatement();
            String getUserID = usersTable.getValueAt(selectedRow, 0).toString();
            String openUser = "SELECT * FROM users WHERE user_id = '" + getUserID + "'";
            var rs = st.executeQuery(openUser);

            if (rs.next()) {
                String userID = rs.getString("user_id");
                String userName = rs.getString("user_name");
                String userType = rs.getString("user_type");
                String studentType = rs.getString("student_type");

                if (userType.equals("Student")) {
                    String getEventMembersSQL = "SELECT * FROM events";
                    var rm = st.executeQuery(getEventMembersSQL);

                    while (rm.next()) {
                        String participantList = rm.getString("participants");
                        String eventName = rm.getString("event_name");
                        String startdate = rm.getString("start_date");
                        String status = rm.getString("status");

                        String UID = userID.replace("02000", "");

                        if (participantList != null && !participantList.isBlank()) {
                            if (participantList.contains(UID)) {
                                studentParticipatedEventsTableModel.addRow(new Object[]{eventName, startdate, status});
                            }
                        }
                    }

                    String getJoinedOrgsSQL = "SELECT * FROM orgs";
                    var rp = st.executeQuery(getJoinedOrgsSQL);

                    while (rp.next()) {
                        String memberList = rp.getString("members");
                        String orgName = rp.getString("org_name");
                        String adviser = rp.getString("adviser");

                        String UID = userID.replace("02000", "");

                        System.out.println("here at users");
                        if (memberList != null && !memberList.isBlank()) {
                            if (memberList.contains(UID)) {
                                studentOrgsJoinedTableModel.addRow(new Object[]{orgName, adviser});
                            }
                        }
                    }

                    studentNameInfo.setText(userName);
                    studentTypeInfo.setText(studentType.equals("null") ? userID + " | " + userType : userID + " | " + userType + " - " + studentType);
                    studentParticipatedEventsInfo.setText(studentParticipatedEventsTableModel.getRowCount() + "");
                    studentOrgsJoinedInfo.setText(studentOrgsJoinedTableModel.getRowCount() + "");

                    eventsDetailedPanel.setVisible(false);
                    orgsDetailedPanel.setVisible(false);
                    studentDetailedPanel.setVisible(true);
                    facultyDetailedPanel.setVisible(false);
                    DashBoardPanel.setVisible(false);
                    ClubsAndOrgsPanel.setVisible(false);
                    EventsPanel.setVisible(false);
                    UsersPanel.setVisible(false);
                } else if (userType.equals("Faculty")) {
                    JOptionPane.showMessageDialog(this, "Faculty user selected: " + userName);
                    String getFacultyEventDetailsSQL = "SELECT * FROM events WHERE faculty_assigned = '" + userName + "'";
                    var rm = st.executeQuery(getFacultyEventDetailsSQL);

                    while (rm.next()) {
                        String eventName = rm.getString("event_name");
                        String startDate = rm.getString("start_date");
                        String participants = rm.getString("participants");

                        if (participants != null && !participants.isBlank()) {
                            String[] participantIDArray = participants.split(" ");
                            for (String participantID : participantIDArray) {
                                eventParticipantsArrayList.add(participantID);
                            }
                        }
                        facultyEventsHandledTableModel.addRow(new Object[]{eventName, startDate, eventParticipantsArrayList.size()});

                        // You can add more detailed logic here for faculty if needed
                    }
                    facultyNameInfo.setText(userName);
                    facultyTypeInfo.setText(studentType.equals("null") ? userID + " | " + userType : userID + " | " + userType + " - " + studentType);
                    facultyEventsHeadedInfo.setText(facultyEventsHandledTableModel.getRowCount() + "");
                    facultyOrgsAdvisorInfo.setText(facultyOrgAdvisoryTableModel.getRowCount() + "");

                    eventsDetailedPanel.setVisible(false);
                    orgsDetailedPanel.setVisible(false);
                    studentDetailedPanel.setVisible(false);
                    facultyDetailedPanel.setVisible(true);
                    DashBoardPanel.setVisible(false);
                    ClubsAndOrgsPanel.setVisible(false);
                    EventsPanel.setVisible(false);
                    UsersPanel.setVisible(false);

                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    //rightclick popup
    private void showPopUp(MouseEvent e) {
        if (jScrollPane1.isShowing()) {
            popupMenu.show(jScrollPane1, e.getX(), e.getY());
        } else if (jScrollPane2.isShowing()) {
            popupMenu.show(jScrollPane2, e.getX(), e.getY());
        } else if (jScrollPane3.isShowing()) {
            popupMenu.show(jScrollPane3, e.getX(), e.getY());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        popupOpenButton = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        popupSelectAllButton = new javax.swing.JCheckBoxMenuItem();
        popupEditButton = new javax.swing.JMenuItem();
        popupDeleteButton = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        sideDashboard = new javax.swing.JPanel();
        dashboardButton = new test.ButtonRound();
        clubsAndOrgsButton = new test.ButtonRound();
        eventsButton = new test.ButtonRound();
        usersButton = new test.ButtonRound();
        logoPlaceHolder = new javax.swing.JLabel();
        panelRound1 = new test.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelRound4 = new test.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        toolsLabel = new javax.swing.JLabel();
        reportsButton = new test.ButtonRound();
        binButton = new test.ButtonRound();
        mainDashboard = new test.PanelRound();
        DashBoardPanel = new javax.swing.JPanel();
        panelRound5 = new test.PanelRound();
        clubAndOrgsLabel1 = new javax.swing.JLabel();
        panelRound6 = new test.PanelRound();
        panelRound15 = new test.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        totalEvents = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        panelRound20 = new test.PanelRound();
        jLabel11 = new javax.swing.JLabel();
        totalEvents1 = new javax.swing.JLabel();
        panelRound22 = new test.PanelRound();
        jLabel13 = new javax.swing.JLabel();
        totalEvents3 = new javax.swing.JLabel();
        panelRound21 = new test.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        totalEvents2 = new javax.swing.JLabel();
        panelRound7 = new test.PanelRound();
        panelRound19 = new test.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        totalUsers = new javax.swing.JLabel();
        panelRound24 = new test.PanelRound();
        jLabel15 = new javax.swing.JLabel();
        totalEvents4 = new javax.swing.JLabel();
        panelRound25 = new test.PanelRound();
        jLabel16 = new javax.swing.JLabel();
        totalEvents5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        panelRound8 = new test.PanelRound();
        panelRound9 = new test.PanelRound();
        ClubsAndOrgsPanel = new javax.swing.JPanel();
        panelRound12 = new test.PanelRound();
        clubAndOrgsLabel = new javax.swing.JLabel();
        addOrgsButton = new test.ButtonRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        orgsTable = new test.Table();
        EventsPanel = new javax.swing.JPanel();
        panelRound14 = new test.PanelRound();
        lblEvent = new javax.swing.JLabel();
        addEventButton = new test.ButtonRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        eventsTable = new test.Table();
        UsersPanel = new javax.swing.JPanel();
        panelRound16 = new test.PanelRound();
        addUsersButton = new test.ButtonRound();
        usersLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        usersTable = new test.Table();
        facultyDetailedPanel = new javax.swing.JPanel();
        backUsersButton1 = new javax.swing.JButton();
        facultyNameInfo = new javax.swing.JLabel();
        facultyTypeInfo = new javax.swing.JLabel();
        userTypeInfo10 = new javax.swing.JLabel();
        userTypeInfo11 = new javax.swing.JLabel();
        facultyEventsHeadedInfo = new javax.swing.JLabel();
        facultyOrgsAdvisorInfo = new javax.swing.JLabel();
        panelRound17 = new test.PanelRound();
        userTypeInfo12 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        facultyOrgAdvisoryTable = new javax.swing.JTable();
        panelRound18 = new test.PanelRound();
        userTypeInfo14 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        facultyHeadedEventsTable = new javax.swing.JTable();
        studentDetailedPanel = new javax.swing.JPanel();
        backUsersButton = new javax.swing.JButton();
        studentNameInfo = new javax.swing.JLabel();
        studentTypeInfo = new javax.swing.JLabel();
        studentEventParticipatedLabel = new javax.swing.JLabel();
        studentOrgsJoinedLabel = new javax.swing.JLabel();
        studentParticipatedEventsInfo = new javax.swing.JLabel();
        studentOrgsJoinedInfo = new javax.swing.JLabel();
        panelRound2 = new test.PanelRound();
        studentOrgsTableLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        studentOrgsJoinedTable = new javax.swing.JTable();
        panelRound3 = new test.PanelRound();
        studentEventTableLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        studentParticipatedEventsTable = new javax.swing.JTable();
        orgsDetailedPanel = new javax.swing.JPanel();
        backToDashboardButton = new javax.swing.JButton();
        orgsNameInfo = new javax.swing.JLabel();
        adviserInfo = new javax.swing.JLabel();
        userTypeInfo4 = new javax.swing.JLabel();
        userTypeInfo7 = new javax.swing.JLabel();
        totalMembersInfo = new javax.swing.JLabel();
        orgOngoingEventsInfo = new javax.swing.JLabel();
        panelRound10 = new test.PanelRound();
        userTypeInfo8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        orgEventTable = new javax.swing.JTable();
        panelRound11 = new test.PanelRound();
        userTypeInfo9 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        membersTable = new javax.swing.JTable();
        addOrgMembersButton = new test.ButtonRound();
        eventsDetailedPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        eventNameInfo = new javax.swing.JLabel();
        eventStatusInfo = new javax.swing.JLabel();
        totalParticipantsLabel = new javax.swing.JLabel();
        totalParticipantsInfo = new javax.swing.JLabel();
        panelRound13 = new test.PanelRound();
        userTypeInfo13 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        eventParticipantsTable = new javax.swing.JTable();
        addParticipantsButton = new test.ButtonRound();
        officerInChargeInfo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        popupMenu.setBorder(null);

        popupOpenButton.setText("Open");
        popupOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupOpenButtonActionPerformed(evt);
            }
        });
        popupMenu.add(popupOpenButton);
        popupMenu.add(jSeparator1);

        popupSelectAllButton.setText("Select All");
        popupSelectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupSelectAllButtonActionPerformed(evt);
            }
        });
        popupMenu.add(popupSelectAllButton);

        popupEditButton.setText("Edit");
        popupEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupEditButtonActionPerformed(evt);
            }
        });
        popupMenu.add(popupEditButton);

        popupDeleteButton.setText("Delete");
        popupDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupDeleteButtonActionPerformed(evt);
            }
        });
        popupMenu.add(popupDeleteButton);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Campus Connect");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1080, 720));
        setName("main"); // NOI18N

        sideDashboard.setBackground(new java.awt.Color(255, 204, 255));
        sideDashboard.setMaximumSize(new java.awt.Dimension(220, 32767));
        sideDashboard.setOpaque(false);
        sideDashboard.setPreferredSize(new java.awt.Dimension(218, 715));

        dashboardButton.setBackground(new java.awt.Color(201, 207, 222));
        dashboardButton.setBorder(null);
        dashboardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboards (1).png"))); // NOI18N
        dashboardButton.setText("Dashboard");
        dashboardButton.setAlignmentX(0.5F);
        buttonGroup1.add(dashboardButton);
        dashboardButton.setFocusPainted(false);
        dashboardButton.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        dashboardButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        dashboardButton.setIconTextGap(10);
        dashboardButton.setMargin(new java.awt.Insets(2, 15, 2, 14));
        dashboardButton.setRoundBottomLeft(10);
        dashboardButton.setRoundTopLeft(10);
        dashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardButtonActionPerformed(evt);
            }
        });

        clubsAndOrgsButton.setBackground(java.awt.Color.lightGray);
        clubsAndOrgsButton.setBorder(null);
        clubsAndOrgsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hierarchical-structure.png"))); // NOI18N
        clubsAndOrgsButton.setText("Organizations");
        clubsAndOrgsButton.setActionCommand("ClubsAndOrganizations");
        clubsAndOrgsButton.setAlignmentX(0.5F);
        buttonGroup1.add(clubsAndOrgsButton);
        clubsAndOrgsButton.setFocusPainted(false);
        clubsAndOrgsButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        clubsAndOrgsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        clubsAndOrgsButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        clubsAndOrgsButton.setIconTextGap(10);
        clubsAndOrgsButton.setMargin(new java.awt.Insets(2, 15, 2, 14));
        clubsAndOrgsButton.setRoundBottomLeft(10);
        clubsAndOrgsButton.setRoundBottomRight(10);
        clubsAndOrgsButton.setRoundTopLeft(10);
        clubsAndOrgsButton.setRoundTopRight(10);
        clubsAndOrgsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clubsAndOrgsButtonActionPerformed(evt);
            }
        });

        eventsButton.setBackground(java.awt.Color.lightGray);
        eventsButton.setBorder(null);
        eventsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/event.png"))); // NOI18N
        eventsButton.setText("Events");
        eventsButton.setAlignmentX(0.5F);
        buttonGroup1.add(eventsButton);
        eventsButton.setFocusPainted(false);
        eventsButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        eventsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        eventsButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        eventsButton.setIconTextGap(10);
        eventsButton.setMargin(new java.awt.Insets(2, 15, 2, 14));
        eventsButton.setRoundBottomLeft(10);
        eventsButton.setRoundBottomRight(10);
        eventsButton.setRoundTopLeft(10);
        eventsButton.setRoundTopRight(10);
        eventsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventsButtonActionPerformed(evt);
            }
        });

        usersButton.setBackground(java.awt.Color.lightGray);
        usersButton.setBorder(null);
        usersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/multiple-users-silhouette.png"))); // NOI18N
        usersButton.setText("Users");
        usersButton.setAlignmentX(0.5F);
        buttonGroup1.add(usersButton);
        usersButton.setFocusPainted(false);
        usersButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        usersButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usersButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        usersButton.setIconTextGap(10);
        usersButton.setMargin(new java.awt.Insets(2, 15, 2, 14));
        usersButton.setRoundBottomLeft(10);
        usersButton.setRoundBottomRight(10);
        usersButton.setRoundTopLeft(10);
        usersButton.setRoundTopRight(10);
        usersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersButtonActionPerformed(evt);
            }
        });

        logoPlaceHolder.setBackground(new java.awt.Color(255, 0, 153));
        logoPlaceHolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CampusConnectLogo1.png"))); // NOI18N

        panelRound1.setBackground(new java.awt.Color(143, 182, 194));
        panelRound1.setPreferredSize(new java.awt.Dimension(180, 55));
        panelRound1.setRoundBottomLeft(25);
        panelRound1.setRoundBottomRight(25);
        panelRound1.setRoundTopLeft(25);
        panelRound1.setRoundTopRight(25);

        jLabel1.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel1.setText("Administrator");
        jLabel1.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel3.setText("Justine A.");
        jLabel3.setToolTipText("");

        panelRound4.setPreferredSize(new java.awt.Dimension(40, 40));
        panelRound4.setRoundBottomLeft(50);
        panelRound4.setRoundBottomRight(50);
        panelRound4.setRoundTopLeft(50);
        panelRound4.setRoundTopRight(50);

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel2.setText("MAIN");

        toolsLabel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        toolsLabel.setText("TOOLS");

        reportsButton.setBackground(java.awt.Color.lightGray);
        reportsButton.setBorder(null);
        reportsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profit-report.png"))); // NOI18N
        reportsButton.setText("Reports");
        reportsButton.setAlignmentX(0.5F);
        buttonGroup1.add(reportsButton);
        reportsButton.setFocusPainted(false);
        reportsButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        reportsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reportsButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        reportsButton.setIconTextGap(10);
        reportsButton.setMargin(new java.awt.Insets(2, 15, 2, 14));
        reportsButton.setRoundBottomLeft(10);
        reportsButton.setRoundBottomRight(10);
        reportsButton.setRoundTopLeft(10);
        reportsButton.setRoundTopRight(10);
        reportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsButtonActionPerformed(evt);
            }
        });

        binButton.setBackground(java.awt.Color.lightGray);
        binButton.setBorder(null);
        binButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        binButton.setText("Bin");
        binButton.setAlignmentX(0.5F);
        buttonGroup1.add(binButton);
        binButton.setFocusPainted(false);
        binButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        binButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        binButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        binButton.setIconTextGap(10);
        binButton.setMargin(new java.awt.Insets(2, 15, 2, 14));
        binButton.setRoundBottomLeft(10);
        binButton.setRoundBottomRight(10);
        binButton.setRoundTopLeft(10);
        binButton.setRoundTopRight(10);
        binButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sideDashboardLayout = new javax.swing.GroupLayout(sideDashboard);
        sideDashboard.setLayout(sideDashboardLayout);
        sideDashboardLayout.setHorizontalGroup(
            sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideDashboardLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dashboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideDashboardLayout.createSequentialGroup()
                        .addGroup(sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toolsLabel)
                            .addComponent(jLabel2)
                            .addGroup(sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(logoPlaceHolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(clubsAndOrgsButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(usersButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reportsButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(binButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        sideDashboardLayout.setVerticalGroup(
            sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideDashboardLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(logoPlaceHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clubsAndOrgsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(toolsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(binButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        mainDashboard.setBackground(new java.awt.Color(204, 204, 255));
        mainDashboard.setRoundBottomLeft(25);
        mainDashboard.setRoundBottomRight(25);
        mainDashboard.setRoundTopLeft(25);
        mainDashboard.setRoundTopRight(25);
        mainDashboard.setLayout(new java.awt.CardLayout());

        DashBoardPanel.setBackground(new java.awt.Color(204, 255, 51));
        DashBoardPanel.setOpaque(false);

        panelRound5.setBackground(new java.awt.Color(143, 182, 194));
        panelRound5.setRoundBottomLeft(25);
        panelRound5.setRoundBottomRight(25);
        panelRound5.setRoundTopLeft(25);
        panelRound5.setRoundTopRight(25);

        clubAndOrgsLabel1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        clubAndOrgsLabel1.setText("Dashboard");

        panelRound6.setBackground(new java.awt.Color(201, 217, 222));
        panelRound6.setPreferredSize(new java.awt.Dimension(357, 369));
        panelRound6.setRoundBottomLeft(20);
        panelRound6.setRoundBottomRight(20);
        panelRound6.setRoundTopLeft(20);
        panelRound6.setRoundTopRight(20);

        panelRound15.setBackground(new java.awt.Color(241, 240, 232));
        panelRound15.setRoundBottomLeft(25);
        panelRound15.setRoundBottomRight(25);
        panelRound15.setRoundTopLeft(25);
        panelRound15.setRoundTopRight(25);

        jLabel5.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel5.setText("Total Events");

        totalEvents.setFont(new java.awt.Font("Inter Display ExtraBold", 0, 18)); // NOI18N
        totalEvents.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalEvents.setText("102");
        totalEvents.setFocusable(false);
        totalEvents.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        totalEvents.setPreferredSize(new java.awt.Dimension(30, 18));

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                .addComponent(totalEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalEvents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jLabel9.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/event.png"))); // NOI18N
        jLabel9.setText(" Events");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jSeparator2.setForeground(new java.awt.Color(121, 155, 174));
        jSeparator2.setToolTipText("");

        panelRound20.setBackground(new java.awt.Color(241, 240, 232));
        panelRound20.setRoundBottomLeft(25);
        panelRound20.setRoundBottomRight(25);
        panelRound20.setRoundTopLeft(25);
        panelRound20.setRoundTopRight(25);

        jLabel11.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel11.setText("Ongoing");

        totalEvents1.setFont(new java.awt.Font("Inter Display ExtraBold", 0, 18)); // NOI18N
        totalEvents1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalEvents1.setText("102");
        totalEvents1.setFocusable(false);
        totalEvents1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        totalEvents1.setPreferredSize(new java.awt.Dimension(30, 18));

        javax.swing.GroupLayout panelRound20Layout = new javax.swing.GroupLayout(panelRound20);
        panelRound20.setLayout(panelRound20Layout);
        panelRound20Layout.setHorizontalGroup(
            panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound20Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound20Layout.setVerticalGroup(
            panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound20Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        panelRound22.setBackground(new java.awt.Color(241, 240, 232));
        panelRound22.setRoundBottomLeft(25);
        panelRound22.setRoundBottomRight(25);
        panelRound22.setRoundTopLeft(25);
        panelRound22.setRoundTopRight(25);

        jLabel13.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel13.setText("Upcoming");

        totalEvents3.setFont(new java.awt.Font("Inter Display ExtraBold", 0, 18)); // NOI18N
        totalEvents3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalEvents3.setText("102");
        totalEvents3.setFocusable(false);
        totalEvents3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        totalEvents3.setPreferredSize(new java.awt.Dimension(30, 18));

        javax.swing.GroupLayout panelRound22Layout = new javax.swing.GroupLayout(panelRound22);
        panelRound22.setLayout(panelRound22Layout);
        panelRound22Layout.setHorizontalGroup(
            panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound22Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalEvents3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound22Layout.setVerticalGroup(
            panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound22Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalEvents3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        panelRound21.setBackground(new java.awt.Color(241, 240, 232));
        panelRound21.setRoundBottomLeft(25);
        panelRound21.setRoundBottomRight(25);
        panelRound21.setRoundTopLeft(25);
        panelRound21.setRoundTopRight(25);

        jLabel12.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel12.setText("Ongoing");

        totalEvents2.setFont(new java.awt.Font("Inter Display ExtraBold", 0, 18)); // NOI18N
        totalEvents2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalEvents2.setText("102");
        totalEvents2.setFocusable(false);
        totalEvents2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        totalEvents2.setPreferredSize(new java.awt.Dimension(30, 18));

        javax.swing.GroupLayout panelRound21Layout = new javax.swing.GroupLayout(panelRound21);
        panelRound21.setLayout(panelRound21Layout);
        panelRound21Layout.setHorizontalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound21Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalEvents2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound21Layout.setVerticalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound21Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalEvents2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(panelRound21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        panelRound7.setBackground(new java.awt.Color(201, 217, 222));
        panelRound7.setPreferredSize(new java.awt.Dimension(358, 369));
        panelRound7.setRoundBottomLeft(20);
        panelRound7.setRoundBottomRight(20);
        panelRound7.setRoundTopLeft(20);
        panelRound7.setRoundTopRight(20);

        panelRound19.setBackground(new java.awt.Color(241, 240, 232));
        panelRound19.setRoundBottomLeft(25);
        panelRound19.setRoundBottomRight(25);
        panelRound19.setRoundTopLeft(25);
        panelRound19.setRoundTopRight(25);

        jLabel10.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel10.setText("Total Users");

        totalUsers.setFont(new java.awt.Font("Inter Display ExtraBold", 0, 18)); // NOI18N
        totalUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalUsers.setText("102");
        totalUsers.setFocusable(false);
        totalUsers.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        totalUsers.setPreferredSize(new java.awt.Dimension(30, 18));

        javax.swing.GroupLayout panelRound19Layout = new javax.swing.GroupLayout(panelRound19);
        panelRound19.setLayout(panelRound19Layout);
        panelRound19Layout.setHorizontalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound19Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(totalUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound19Layout.setVerticalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound19Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(10, 10, 10))
        );

        panelRound24.setBackground(new java.awt.Color(241, 240, 232));
        panelRound24.setRoundBottomLeft(25);
        panelRound24.setRoundBottomRight(25);
        panelRound24.setRoundTopLeft(25);
        panelRound24.setRoundTopRight(25);

        jLabel15.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel15.setText("Faculty");

        totalEvents4.setFont(new java.awt.Font("Inter Display ExtraBold", 0, 18)); // NOI18N
        totalEvents4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalEvents4.setText("102");
        totalEvents4.setFocusable(false);
        totalEvents4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        totalEvents4.setPreferredSize(new java.awt.Dimension(30, 18));

        javax.swing.GroupLayout panelRound24Layout = new javax.swing.GroupLayout(panelRound24);
        panelRound24.setLayout(panelRound24Layout);
        panelRound24Layout.setHorizontalGroup(
            panelRound24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound24Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalEvents4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound24Layout.setVerticalGroup(
            panelRound24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound24Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalEvents4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        panelRound25.setBackground(new java.awt.Color(241, 240, 232));
        panelRound25.setRoundBottomLeft(25);
        panelRound25.setRoundBottomRight(25);
        panelRound25.setRoundTopLeft(25);
        panelRound25.setRoundTopRight(25);

        jLabel16.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel16.setText("Student");

        totalEvents5.setFont(new java.awt.Font("Inter Display ExtraBold", 0, 18)); // NOI18N
        totalEvents5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalEvents5.setText("102");
        totalEvents5.setFocusable(false);
        totalEvents5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        totalEvents5.setPreferredSize(new java.awt.Dimension(30, 18));

        javax.swing.GroupLayout panelRound25Layout = new javax.swing.GroupLayout(panelRound25);
        panelRound25.setLayout(panelRound25Layout);
        panelRound25Layout.setHorizontalGroup(
            panelRound25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound25Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalEvents5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound25Layout.setVerticalGroup(
            panelRound25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound25Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalEvents5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jSeparator3.setForeground(new java.awt.Color(121, 155, 174));
        jSeparator3.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/event.png"))); // NOI18N
        jLabel17.setText("Users");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel17))
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRound25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        panelRound8.setBackground(new java.awt.Color(201, 217, 222));
        panelRound8.setMaximumSize(new java.awt.Dimension(250, 250));
        panelRound8.setRoundBottomLeft(20);
        panelRound8.setRoundBottomRight(20);
        panelRound8.setRoundTopLeft(20);
        panelRound8.setRoundTopRight(20);

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        panelRound9.setBackground(new java.awt.Color(201, 217, 222));
        panelRound9.setRoundBottomLeft(20);
        panelRound9.setRoundBottomRight(20);
        panelRound9.setRoundTopLeft(20);
        panelRound9.setRoundTopRight(20);

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clubAndOrgsLabel1)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound5Layout.createSequentialGroup()
                                .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
                            .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(clubAndOrgsLabel1)
                .addGap(18, 18, 18)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout DashBoardPanelLayout = new javax.swing.GroupLayout(DashBoardPanel);
        DashBoardPanel.setLayout(DashBoardPanelLayout);
        DashBoardPanelLayout.setHorizontalGroup(
            DashBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DashBoardPanelLayout.setVerticalGroup(
            DashBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainDashboard.add(DashBoardPanel, "card2");

        ClubsAndOrgsPanel.setOpaque(false);

        panelRound12.setBackground(new java.awt.Color(143, 182, 194));
        panelRound12.setRoundBottomLeft(25);
        panelRound12.setRoundBottomRight(25);
        panelRound12.setRoundTopLeft(25);
        panelRound12.setRoundTopRight(25);

        clubAndOrgsLabel.setFont(new java.awt.Font("Inter Medium", 1, 24)); // NOI18N
        clubAndOrgsLabel.setText("Clubs And Organizations");

        addOrgsButton.setBackground(new java.awt.Color(255, 255, 204));
        addOrgsButton.setBorder(null);
        addOrgsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add16.png"))); // NOI18N
        addOrgsButton.setText("Add");
        addOrgsButton.setToolTipText("");
        addOrgsButton.setFocusable(false);
        addOrgsButton.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        addOrgsButton.setRoundBottomLeft(8);
        addOrgsButton.setRoundBottomRight(8);
        addOrgsButton.setRoundTopLeft(8);
        addOrgsButton.setRoundTopRight(8);
        addOrgsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrgsButtonActionPerformed(evt);
            }
        });

        orgsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Org/Club Name", "Member Count", "Level", "Adviser", "Details"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orgsTable.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        orgsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                orgsTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                orgsTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(orgsTable);
        orgsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout panelRound12Layout = new javax.swing.GroupLayout(panelRound12);
        panelRound12.setLayout(panelRound12Layout);
        panelRound12Layout.setHorizontalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound12Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(panelRound12Layout.createSequentialGroup()
                        .addComponent(clubAndOrgsLabel)
                        .addGap(635, 635, 635)
                        .addComponent(addOrgsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        panelRound12Layout.setVerticalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addOrgsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clubAndOrgsLabel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout ClubsAndOrgsPanelLayout = new javax.swing.GroupLayout(ClubsAndOrgsPanel);
        ClubsAndOrgsPanel.setLayout(ClubsAndOrgsPanelLayout);
        ClubsAndOrgsPanelLayout.setHorizontalGroup(
            ClubsAndOrgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ClubsAndOrgsPanelLayout.setVerticalGroup(
            ClubsAndOrgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainDashboard.add(ClubsAndOrgsPanel, "card3");

        EventsPanel.setBackground(new java.awt.Color(255, 204, 204));
        EventsPanel.setOpaque(false);

        panelRound14.setBackground(new java.awt.Color(143, 182, 194));
        panelRound14.setRoundBottomLeft(25);
        panelRound14.setRoundBottomRight(25);
        panelRound14.setRoundTopLeft(25);
        panelRound14.setRoundTopRight(25);

        lblEvent.setFont(new java.awt.Font("Inter Medium", 1, 24)); // NOI18N
        lblEvent.setText("Events");

        addEventButton.setBackground(new java.awt.Color(255, 255, 204));
        addEventButton.setBorder(null);
        addEventButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add16.png"))); // NOI18N
        addEventButton.setText("Add");
        addEventButton.setToolTipText("");
        addEventButton.setFocusable(false);
        addEventButton.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        addEventButton.setRoundBottomLeft(8);
        addEventButton.setRoundBottomRight(8);
        addEventButton.setRoundTopLeft(8);
        addEventButton.setRoundTopRight(8);
        addEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventButtonActionPerformed(evt);
            }
        });

        eventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Start Date", "User Access", "Details", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventsTable.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        eventsTable.getTableHeader().setReorderingAllowed(false);
        eventsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eventsTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                eventsTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(eventsTable);
        eventsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout panelRound14Layout = new javax.swing.GroupLayout(panelRound14);
        panelRound14.setLayout(panelRound14Layout);
        panelRound14Layout.setHorizontalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound14Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelRound14Layout.createSequentialGroup()
                        .addComponent(lblEvent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 903, Short.MAX_VALUE)
                        .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        panelRound14Layout.setVerticalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEvent)
                    .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout EventsPanelLayout = new javax.swing.GroupLayout(EventsPanel);
        EventsPanel.setLayout(EventsPanelLayout);
        EventsPanelLayout.setHorizontalGroup(
            EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        EventsPanelLayout.setVerticalGroup(
            EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainDashboard.add(EventsPanel, "card4");

        panelRound16.setBackground(new java.awt.Color(143, 182, 194));
        panelRound16.setRoundBottomLeft(25);
        panelRound16.setRoundBottomRight(25);
        panelRound16.setRoundTopLeft(25);
        panelRound16.setRoundTopRight(25);

        addUsersButton.setBackground(new java.awt.Color(255, 255, 204));
        addUsersButton.setBorder(null);
        addUsersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add16.png"))); // NOI18N
        addUsersButton.setText("Add");
        addUsersButton.setToolTipText("");
        addUsersButton.setFocusable(false);
        addUsersButton.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        addUsersButton.setRoundBottomLeft(8);
        addUsersButton.setRoundBottomRight(8);
        addUsersButton.setRoundTopLeft(8);
        addUsersButton.setRoundTopRight(8);
        addUsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUsersButtonActionPerformed(evt);
            }
        });

        usersLabel.setFont(new java.awt.Font("Inter Medium", 1, 24)); // NOI18N
        usersLabel.setText("Users");

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Username", "Faculty", "Student"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usersTable.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        usersTable.setShowGrid(false);
        usersTable.getTableHeader().setReorderingAllowed(false);
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                usersTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                usersTableMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(usersTable);
        usersTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout panelRound16Layout = new javax.swing.GroupLayout(panelRound16);
        panelRound16.setLayout(panelRound16Layout);
        panelRound16Layout.setHorizontalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound16Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(panelRound16Layout.createSequentialGroup()
                        .addComponent(usersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 886, Short.MAX_VALUE)
                        .addComponent(addUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        panelRound16Layout.setVerticalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound16Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usersLabel)
                    .addComponent(addUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout UsersPanelLayout = new javax.swing.GroupLayout(UsersPanel);
        UsersPanel.setLayout(UsersPanelLayout);
        UsersPanelLayout.setHorizontalGroup(
            UsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UsersPanelLayout.setVerticalGroup(
            UsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainDashboard.add(UsersPanel, "card5");

        facultyDetailedPanel.setBackground(new java.awt.Color(204, 255, 204));

        backUsersButton1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        backUsersButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        backUsersButton1.setText("Users");
        backUsersButton1.setBorder(null);
        backUsersButton1.setBorderPainted(false);
        backUsersButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backUsersButton1.setIconTextGap(12);
        backUsersButton1.setOpaque(false);
        backUsersButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backUsersButton1ActionPerformed(evt);
            }
        });

        facultyNameInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        facultyNameInfo.setText("Justine Acuña");
        facultyNameInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        facultyTypeInfo.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        facultyTypeInfo.setText("Faculty");
        facultyTypeInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        userTypeInfo10.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        userTypeInfo10.setText("Events Headed");

        userTypeInfo11.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        userTypeInfo11.setText("Orgs Advisor");

        facultyEventsHeadedInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        facultyEventsHeadedInfo.setText("312");

        facultyOrgsAdvisorInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        facultyOrgsAdvisorInfo.setText("36");

        panelRound17.setBackground(new java.awt.Color(255, 255, 255));
        panelRound17.setPreferredSize(new java.awt.Dimension(673, 394));
        panelRound17.setRoundBottomLeft(15);
        panelRound17.setRoundBottomRight(15);
        panelRound17.setRoundTopLeft(15);
        panelRound17.setRoundTopRight(15);

        userTypeInfo12.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        userTypeInfo12.setText("Advisor");
        userTypeInfo12.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        facultyOrgAdvisoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Organization Name", "Level", "No. of Members"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(facultyOrgAdvisoryTable);

        javax.swing.GroupLayout panelRound17Layout = new javax.swing.GroupLayout(panelRound17);
        panelRound17.setLayout(panelRound17Layout);
        panelRound17Layout.setHorizontalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addGroup(panelRound17Layout.createSequentialGroup()
                        .addComponent(userTypeInfo12, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRound17Layout.setVerticalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userTypeInfo12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound18.setBackground(new java.awt.Color(255, 255, 255));
        panelRound18.setPreferredSize(new java.awt.Dimension(673, 394));
        panelRound18.setRoundBottomLeft(15);
        panelRound18.setRoundBottomRight(15);
        panelRound18.setRoundTopLeft(15);
        panelRound18.setRoundTopRight(15);

        userTypeInfo14.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        userTypeInfo14.setText("Events Head");
        userTypeInfo14.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        facultyHeadedEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Date", "No. of Participants"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(facultyHeadedEventsTable);

        javax.swing.GroupLayout panelRound18Layout = new javax.swing.GroupLayout(panelRound18);
        panelRound18.setLayout(panelRound18Layout);
        panelRound18Layout.setHorizontalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound18Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound18Layout.createSequentialGroup()
                        .addComponent(userTypeInfo14, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound18Layout.setVerticalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userTypeInfo14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout facultyDetailedPanelLayout = new javax.swing.GroupLayout(facultyDetailedPanel);
        facultyDetailedPanel.setLayout(facultyDetailedPanelLayout);
        facultyDetailedPanelLayout.setHorizontalGroup(
            facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facultyDetailedPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(facultyDetailedPanelLayout.createSequentialGroup()
                        .addComponent(panelRound18, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(panelRound17, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                    .addGroup(facultyDetailedPanelLayout.createSequentialGroup()
                        .addGroup(facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(facultyDetailedPanelLayout.createSequentialGroup()
                                .addComponent(facultyTypeInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE))
                            .addGroup(facultyDetailedPanelLayout.createSequentialGroup()
                                .addComponent(facultyNameInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)))
                        .addGroup(facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userTypeInfo10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facultyEventsHeadedInfo))
                        .addGap(18, 18, 18)
                        .addGroup(facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(facultyOrgsAdvisorInfo)
                            .addComponent(userTypeInfo11))))
                .addGap(43, 43, 43))
            .addGroup(facultyDetailedPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(backUsersButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        facultyDetailedPanelLayout.setVerticalGroup(
            facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facultyDetailedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backUsersButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(facultyDetailedPanelLayout.createSequentialGroup()
                        .addGroup(facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userTypeInfo10)
                            .addComponent(userTypeInfo11))
                        .addGap(5, 5, 5)
                        .addGroup(facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(facultyEventsHeadedInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facultyOrgsAdvisorInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(facultyDetailedPanelLayout.createSequentialGroup()
                        .addComponent(facultyNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(facultyTypeInfo)))
                .addGap(119, 119, 119)
                .addGroup(facultyDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound17, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(panelRound18, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        mainDashboard.add(facultyDetailedPanel, "card6");

        studentDetailedPanel.setBackground(new java.awt.Color(204, 255, 204));

        backUsersButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        backUsersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        backUsersButton.setText("Users");
        backUsersButton.setBorder(null);
        backUsersButton.setBorderPainted(false);
        backUsersButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backUsersButton.setIconTextGap(12);
        backUsersButton.setOpaque(false);
        backUsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backUsersButtonActionPerformed(evt);
            }
        });

        studentNameInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        studentNameInfo.setText("Justine Acuña");
        studentNameInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        studentTypeInfo.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        studentTypeInfo.setText("Faculty");
        studentTypeInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        studentEventParticipatedLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        studentEventParticipatedLabel.setText("Events Participated");

        studentOrgsJoinedLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        studentOrgsJoinedLabel.setText("Orgs Joined");

        studentParticipatedEventsInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        studentParticipatedEventsInfo.setText("312");

        studentOrgsJoinedInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        studentOrgsJoinedInfo.setText("36");

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setPreferredSize(new java.awt.Dimension(673, 394));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        studentOrgsTableLabel.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        studentOrgsTableLabel.setText("Organizations Joined");
        studentOrgsTableLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        studentOrgsJoinedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Organization Name", "Adviser"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(studentOrgsJoinedTable);

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(studentOrgsTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentOrgsTableLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setPreferredSize(new java.awt.Dimension(673, 394));
        panelRound3.setRoundBottomLeft(15);
        panelRound3.setRoundBottomRight(15);
        panelRound3.setRoundTopLeft(15);
        panelRound3.setRoundTopRight(15);

        studentEventTableLabel.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        studentEventTableLabel.setText("Participated Events");
        studentEventTableLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        studentParticipatedEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(studentParticipatedEventsTable);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(studentEventTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentEventTableLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout studentDetailedPanelLayout = new javax.swing.GroupLayout(studentDetailedPanel);
        studentDetailedPanel.setLayout(studentDetailedPanelLayout);
        studentDetailedPanelLayout.setHorizontalGroup(
            studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentDetailedPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(studentDetailedPanelLayout.createSequentialGroup()
                        .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                    .addGroup(studentDetailedPanelLayout.createSequentialGroup()
                        .addGroup(studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(studentDetailedPanelLayout.createSequentialGroup()
                                .addComponent(studentTypeInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE))
                            .addGroup(studentDetailedPanelLayout.createSequentialGroup()
                                .addComponent(studentNameInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)))
                        .addGroup(studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentEventParticipatedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentParticipatedEventsInfo))
                        .addGap(18, 18, 18)
                        .addGroup(studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentOrgsJoinedInfo)
                            .addComponent(studentOrgsJoinedLabel))))
                .addGap(43, 43, 43))
            .addGroup(studentDetailedPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(backUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        studentDetailedPanelLayout.setVerticalGroup(
            studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentDetailedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(studentDetailedPanelLayout.createSequentialGroup()
                        .addGroup(studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(studentEventParticipatedLabel)
                            .addComponent(studentOrgsJoinedLabel))
                        .addGap(5, 5, 5)
                        .addGroup(studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(studentParticipatedEventsInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentOrgsJoinedInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(studentDetailedPanelLayout.createSequentialGroup()
                        .addComponent(studentNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(studentTypeInfo)))
                .addGap(119, 119, 119)
                .addGroup(studentDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        mainDashboard.add(studentDetailedPanel, "card6");

        orgsDetailedPanel.setBackground(new java.awt.Color(204, 255, 204));

        backToDashboardButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        backToDashboardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        backToDashboardButton.setText("Clubs and Organizations");
        backToDashboardButton.setBorder(null);
        backToDashboardButton.setBorderPainted(false);
        backToDashboardButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backToDashboardButton.setIconTextGap(12);
        backToDashboardButton.setOpaque(false);
        backToDashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToDashboardButtonActionPerformed(evt);
            }
        });

        orgsNameInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        orgsNameInfo.setText("Org Name");
        orgsNameInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        adviserInfo.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        adviserInfo.setText("Adviser");
        adviserInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        userTypeInfo4.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        userTypeInfo4.setText("Total Members");

        userTypeInfo7.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        userTypeInfo7.setText("Ongoing Events");

        totalMembersInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        totalMembersInfo.setText("312");

        orgOngoingEventsInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        orgOngoingEventsInfo.setText("36");

        panelRound10.setBackground(new java.awt.Color(255, 255, 255));
        panelRound10.setPreferredSize(new java.awt.Dimension(673, 394));
        panelRound10.setRoundBottomLeft(15);
        panelRound10.setRoundBottomRight(15);
        panelRound10.setRoundTopLeft(15);
        panelRound10.setRoundTopRight(15);

        userTypeInfo8.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        userTypeInfo8.setText("Events");
        userTypeInfo8.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        orgEventTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(orgEventTable);

        javax.swing.GroupLayout panelRound10Layout = new javax.swing.GroupLayout(panelRound10);
        panelRound10.setLayout(panelRound10Layout);
        panelRound10Layout.setHorizontalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound10Layout.createSequentialGroup()
                        .addComponent(userTypeInfo8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound10Layout.setVerticalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userTypeInfo8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound11.setBackground(new java.awt.Color(255, 255, 255));
        panelRound11.setPreferredSize(new java.awt.Dimension(673, 394));
        panelRound11.setRoundBottomLeft(15);
        panelRound11.setRoundBottomRight(15);
        panelRound11.setRoundTopLeft(15);
        panelRound11.setRoundTopRight(15);

        userTypeInfo9.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        userTypeInfo9.setText("Members");
        userTypeInfo9.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        membersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Level"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        membersTable.setRowHeight(25);
        jScrollPane7.setViewportView(membersTable);

        addOrgMembersButton.setBorder(null);
        addOrgMembersButton.setText("Add Members");
        addOrgMembersButton.setRoundBottomLeft(10);
        addOrgMembersButton.setRoundBottomRight(10);
        addOrgMembersButton.setRoundTopLeft(10);
        addOrgMembersButton.setRoundTopRight(10);
        addOrgMembersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrgMembersButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound11Layout = new javax.swing.GroupLayout(panelRound11);
        panelRound11.setLayout(panelRound11Layout);
        panelRound11Layout.setHorizontalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addGroup(panelRound11Layout.createSequentialGroup()
                        .addComponent(userTypeInfo9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addOrgMembersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelRound11Layout.setVerticalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userTypeInfo9)
                    .addComponent(addOrgMembersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout orgsDetailedPanelLayout = new javax.swing.GroupLayout(orgsDetailedPanel);
        orgsDetailedPanel.setLayout(orgsDetailedPanelLayout);
        orgsDetailedPanelLayout.setHorizontalGroup(
            orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orgsDetailedPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(orgsDetailedPanelLayout.createSequentialGroup()
                        .addComponent(panelRound11, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                    .addGroup(orgsDetailedPanelLayout.createSequentialGroup()
                        .addGroup(orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(orgsDetailedPanelLayout.createSequentialGroup()
                                .addComponent(orgsNameInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 500, Short.MAX_VALUE))
                            .addGroup(orgsDetailedPanelLayout.createSequentialGroup()
                                .addComponent(adviserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalMembersInfo)
                            .addComponent(userTypeInfo4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(orgOngoingEventsInfo)
                            .addComponent(userTypeInfo7))))
                .addGap(43, 43, 43))
            .addGroup(orgsDetailedPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(backToDashboardButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        orgsDetailedPanelLayout.setVerticalGroup(
            orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orgsDetailedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backToDashboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(orgsDetailedPanelLayout.createSequentialGroup()
                        .addGroup(orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userTypeInfo4)
                            .addComponent(userTypeInfo7))
                        .addGap(5, 5, 5)
                        .addGroup(orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalMembersInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orgOngoingEventsInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(orgsDetailedPanelLayout.createSequentialGroup()
                        .addComponent(orgsNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(adviserInfo)))
                .addGap(119, 119, 119)
                .addGroup(orgsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(panelRound11, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        mainDashboard.add(orgsDetailedPanel, "card6");

        eventsDetailedPanel.setBackground(new java.awt.Color(143, 182, 194));

        backButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        backButton.setText("Events");
        backButton.setBorder(null);
        backButton.setBorderPainted(false);
        backButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backButton.setIconTextGap(12);
        backButton.setOpaque(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        eventNameInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        eventNameInfo.setText("Event Title");
        eventNameInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        eventStatusInfo.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        eventStatusInfo.setText("Status");
        eventStatusInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        totalParticipantsLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        totalParticipantsLabel.setText("Total Participants");

        totalParticipantsInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        totalParticipantsInfo.setText("36");

        panelRound13.setBackground(new java.awt.Color(255, 255, 255));
        panelRound13.setPreferredSize(new java.awt.Dimension(673, 394));
        panelRound13.setRoundBottomLeft(15);
        panelRound13.setRoundBottomRight(15);
        panelRound13.setRoundTopLeft(15);
        panelRound13.setRoundTopRight(15);

        userTypeInfo13.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        userTypeInfo13.setText("Participants");
        userTypeInfo13.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        eventParticipantsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Level"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventParticipantsTable.setRowHeight(25);
        jScrollPane9.setViewportView(eventParticipantsTable);

        addParticipantsButton.setText("Add Participants");
        addParticipantsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addParticipantsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound13Layout = new javax.swing.GroupLayout(panelRound13);
        panelRound13.setLayout(panelRound13Layout);
        panelRound13Layout.setHorizontalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                    .addGroup(panelRound13Layout.createSequentialGroup()
                        .addComponent(userTypeInfo13, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addParticipantsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelRound13Layout.setVerticalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userTypeInfo13)
                    .addComponent(addParticipantsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        officerInChargeInfo.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        officerInChargeInfo.setText("Officer-In-Charge");
        officerInChargeInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jButton1.setFont(new java.awt.Font("Inter Display", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/export.png"))); // NOI18N
        jButton1.setText("Export");
        jButton1.setBorderPainted(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton1.setIconTextGap(5);
        jButton1.setMargin(new java.awt.Insets(0, 14, 0, 14));
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout eventsDetailedPanelLayout = new javax.swing.GroupLayout(eventsDetailedPanel);
        eventsDetailedPanel.setLayout(eventsDetailedPanelLayout);
        eventsDetailedPanelLayout.setHorizontalGroup(
            eventsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventsDetailedPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eventsDetailedPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(eventsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound13, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                    .addGroup(eventsDetailedPanelLayout.createSequentialGroup()
                        .addGroup(eventsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eventNameInfo)
                            .addGroup(eventsDetailedPanelLayout.createSequentialGroup()
                                .addComponent(eventStatusInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(officerInChargeInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(eventsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalParticipantsInfo)
                            .addComponent(totalParticipantsLabel))))
                .addGap(43, 43, 43))
        );
        eventsDetailedPanelLayout.setVerticalGroup(
            eventsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventsDetailedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eventsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(46, 46, 46)
                .addGroup(eventsDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(eventsDetailedPanelLayout.createSequentialGroup()
                        .addComponent(totalParticipantsLabel)
                        .addGap(5, 5, 5)
                        .addComponent(totalParticipantsInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(eventsDetailedPanelLayout.createSequentialGroup()
                        .addComponent(eventNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eventStatusInfo))
                    .addComponent(officerInChargeInfo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(119, 119, 119)
                .addComponent(panelRound13, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );

        mainDashboard.add(eventsDetailedPanel, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sideDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(mainDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
            .addComponent(sideDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardButtonActionPerformed
        eventsDetailedPanel.setVisible(false);
        orgsDetailedPanel.setVisible(false);
        studentDetailedPanel.setVisible(false);
        facultyDetailedPanel.setVisible(false);
        DashBoardPanel.setVisible(true);
        ClubsAndOrgsPanel.setVisible(false);
        EventsPanel.setVisible(false);
        UsersPanel.setVisible(false);
        updateDashboardDetails();

    }//GEN-LAST:event_dashboardButtonActionPerformed

    private void clubsAndOrgsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clubsAndOrgsButtonActionPerformed
        eventsDetailedPanel.setVisible(false);
        orgsDetailedPanel.setVisible(false);
        studentDetailedPanel.setVisible(false);
        facultyDetailedPanel.setVisible(false);
        DashBoardPanel.setVisible(false);
        ClubsAndOrgsPanel.setVisible(true);
        EventsPanel.setVisible(false);
        UsersPanel.setVisible(false);


    }//GEN-LAST:event_clubsAndOrgsButtonActionPerformed

    private void eventsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventsButtonActionPerformed
        eventsDetailedPanel.setVisible(false);
        orgsDetailedPanel.setVisible(false);
        studentDetailedPanel.setVisible(false);
        facultyDetailedPanel.setVisible(false);
        DashBoardPanel.setVisible(false);
        ClubsAndOrgsPanel.setVisible(false);
        EventsPanel.setVisible(true);
        UsersPanel.setVisible(false);

    }//GEN-LAST:event_eventsButtonActionPerformed

    private void usersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersButtonActionPerformed
        eventsDetailedPanel.setVisible(false);
        orgsDetailedPanel.setVisible(false);
        studentDetailedPanel.setVisible(false);
        facultyDetailedPanel.setVisible(false);
        DashBoardPanel.setVisible(false);
        ClubsAndOrgsPanel.setVisible(false);
        EventsPanel.setVisible(false);
        UsersPanel.setVisible(true);

    }//GEN-LAST:event_usersButtonActionPerformed

    private void addEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventButtonActionPerformed
        activeTable().clearSelection();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventsForm().setVisible(true);

            }
        });
    }//GEN-LAST:event_addEventButtonActionPerformed

    private void addOrgsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrgsButtonActionPerformed
        activeTable().clearSelection();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClubAndOrgForm().setVisible(true);
            }
        });
    }//GEN-LAST:event_addOrgsButtonActionPerformed

    private void addUsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUsersButtonActionPerformed
        activeTable().clearSelection();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsersForm().setVisible(true);
            }
        });
    }//GEN-LAST:event_addUsersButtonActionPerformed

    private void popupDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupDeleteButtonActionPerformed
        int[] selectedRows;

        if (activeTable() != null) {
            selectedRows = activeTable().getSelectedRows();// Get selected rows

            if (selectedRows.length > 0) {
                int confirmDelete = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this data?", "Delete?", JOptionPane.YES_NO_OPTION);

                if (confirmDelete == JOptionPane.YES_OPTION) {
                    try (Connection conn = conn()) { // Auto-close connection
                        conn.setAutoCommit(false); // Enable transaction for batch operations

                        PreparedStatement updateStmt = null;

                        if (activeTable() == orgsTable) {
                            // SQL statement for organizations
                            String updateOrgSQL = "UPDATE orgs SET is_deleted = 1 WHERE org_name = ?";
                            updateStmt = conn.prepareStatement(updateOrgSQL);
                        } else if (activeTable() == eventsTable) {
                            // SQL statement for events
                            String updateEventSQL = "UPDATE events SET is_deleted = 1 WHERE event_name = ?";
                            updateStmt = conn.prepareStatement(updateEventSQL);
                        } else if (activeTable() == usersTable) {
                            // SQL statement for users
                            String updateUserSQL = "UPDATE users SET is_deleted = 1 WHERE user_id = ?";
                            updateStmt = conn.prepareStatement(updateUserSQL);
                        }

                        // Iterate in reverse to prevent index shifting
                        for (int i = selectedRows.length - 1; i >= 0; i--) {
                            // Extract row data
                            if (activeTable() == orgsTable) {
                                Object orgName = activeTable().getValueAt(selectedRows[i], 0);
                                updateStmt.setString(1, orgName.toString());
                            } else if (activeTable() == eventsTable) {
                                Object eventName = activeTable().getValueAt(selectedRows[i], 0);
                                updateStmt.setString(1, eventName.toString());
                            } else if (activeTable() == usersTable) {
                                Object userID = usersTable.getValueAt(selectedRows[i], 0);
                                updateStmt.setString(1, userID.toString());
                            }

                            // Add to batch for updating is_deleted
                            if (updateStmt != null) {
                                updateStmt.addBatch();
                            }

                            // Remove row from table
                            if (activeTable() == orgsTable) {
                                ((DefaultTableModel) orgsTable.getModel()).removeRow(selectedRows[i]);
                            } else if (activeTable() == eventsTable) {
                                ((DefaultTableModel) eventsTable.getModel()).removeRow(selectedRows[i]);
                            } else {
                                ((DefaultTableModel) usersTable.getModel()).removeRow(selectedRows[i]);
                            }
                        }

                        // Execute batch operations
                        if (updateStmt != null) {
                            updateStmt.executeBatch();
                        }

                        conn.commit(); // Commit transaction

                        JOptionPane.showMessageDialog(null, "Selected rows have been marked as deleted successfully.");
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No rows selected for deletion.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No active table detected.");
        }
    }//GEN-LAST:event_popupDeleteButtonActionPerformed

    private void popupOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupOpenButtonActionPerformed
        if (activeTable() != null && activeTable().getSelectedRowCount() == 1) {
            fetchInternalTableData(activeTable(), activeTable().getSelectedRow());
        } else {
            String message = activeTable().getSelectedRowCount() < 1 ? "No row selected." : "Select one row to open.";
            JOptionPane.showMessageDialog(null, message);
            activeTable().clearSelection();
        }

    }//GEN-LAST:event_popupOpenButtonActionPerformed

    private void backUsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backUsersButtonActionPerformed
        if (studentDetailedPanel.isVisible()) {
            eventsDetailedPanel.setVisible(false);
            orgsDetailedPanel.setVisible(false);
            studentDetailedPanel.setVisible(false);
            facultyDetailedPanel.setVisible(false);
            DashBoardPanel.setVisible(false);
            ClubsAndOrgsPanel.setVisible(false);
            EventsPanel.setVisible(false);
            UsersPanel.setVisible(true);
        }

    }//GEN-LAST:event_backUsersButtonActionPerformed

    private void popupEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupEditButtonActionPerformed
        try {
            Statement st = conn.createStatement();

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    int selectedRow;

                    if (ClubsAndOrgsPanel.isVisible()) {
                        selectedRow = orgsTable.getSelectedRow();
                        if (selectedRow != -1) {
                            try {
                                String orgName = orgsTable.getValueAt(selectedRow, 0).toString();

                                String getOrgsDataSQL = "SELECT * FROM orgs WHERE org_name = '" + orgName + "'";
                                var rs = st.executeQuery(getOrgsDataSQL);

                                if (rs.next()) {
                                    String orgNameString = rs.getString("org_name");
                                    String levelString = rs.getString("level");
                                    String adviserString = rs.getString("adviser");
                                    String detailsString = rs.getString("details");

                                    ClubAndOrgForm clubAndOrgForm = new ClubAndOrgForm();
                                    clubAndOrgForm.setOrgDetails(orgName, levelString, adviserString, detailsString);
                                    clubAndOrgForm.setVisible(true);

                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(CampusConnect.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    } else if (EventsPanel.isVisible()) {
                        selectedRow = eventsTable.getSelectedRow();
                        if (selectedRow != -1) {
                            try {
                                String eventName = eventsTable.getValueAt(selectedRow, 0).toString();

                                String getEventDataSQL = "SELECT * FROM events WHERE event_name = '" + eventName + "'";
                                var rs = st.executeQuery(getEventDataSQL);

                                if (rs.next()) {
                                    String eventNameString = rs.getString("event_name");
                                    String userAccessString = rs.getString("user_access");
                                    String locationString = rs.getString("location");
                                    String orgInChargeString = rs.getString("club_assigned");
                                    String facultyInChargeString = rs.getString("faculty_assigned");
                                    String startDateString = rs.getString("start_date");
                                    String endDateString = rs.getString("end_date");
                                    String details = rs.getString("details");

                                    LocalDate startD = LocalDate.parse(startDateString);
                                    LocalDate endD = LocalDate.parse(endDateString);
                                    System.out.println(userAccessString);

                                    EventsForm eventsForm = new EventsForm();
                                    eventsForm.setEventDetails(eventNameString, userAccessString, locationString, orgInChargeString, facultyInChargeString, startD, endD, details);
                                    eventsForm.setVisible(true);
                                }

                                // Add other relevant fields as necessary
                            } catch (SQLException ex) {
                                Logger.getLogger(CampusConnect.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(EventsPanel, "Please select a row to edit.");
                        }

                    } else {
                        selectedRow = usersTable.getSelectedRow();
                        if (selectedRow != -1) {
                            try {
                                String userID = usersTable.getValueAt(selectedRow, 0).toString();

                                String getUserData = "SELECT * FROM users WHERE user_id = '" + userID + "'";
                                var rs = st.executeQuery(getUserData);
                                
                                if(rs.next()){
                                    String userIDString = rs.getString("user_id");
                                    String userNameString = rs.getString("user_name");
                                    String userTypeString = rs.getString("user_type");
                                    String studentTypeString = rs.getString("student_type");
                                    String passwordString = rs.getString("password");
                                    
                                    UsersForm usersForm = new UsersForm();
                                    usersForm.setUserDetails(userNameString, userTypeString, studentTypeString, userIDString, passwordString);
                                    usersForm.setVisible(true);
                                }
                                
                                
                            } catch (SQLException ex){
                                JOptionPane.showMessageDialog(UsersPanel, ex.getMessage());
                            }
                        }

                       
                    }

                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(CampusConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_popupEditButtonActionPerformed

    private void popupSelectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupSelectAllButtonActionPerformed
        if (popupSelectAllButton.isSelected()) {
            activeTable().selectAll();
            popupSelectAllButton.setText("Deselect All");
        } else {
            usersTable.clearSelection();
            popupSelectAllButton.setText("Select All");
        }
    }//GEN-LAST:event_popupSelectAllButtonActionPerformed

    private void reportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportsButtonActionPerformed

    private void binButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_binButtonActionPerformed

    private void backToDashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToDashboardButtonActionPerformed
        if (orgsDetailedPanel.isVisible()) {
            eventsDetailedPanel.setVisible(false);
            orgsDetailedPanel.setVisible(false);
            studentDetailedPanel.setVisible(false);
            facultyDetailedPanel.setVisible(false);
            DashBoardPanel.setVisible(false);
            ClubsAndOrgsPanel.setVisible(true);
            EventsPanel.setVisible(false);
            UsersPanel.setVisible(false);
        }
    }//GEN-LAST:event_backToDashboardButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if (eventsDetailedPanel.isVisible()) {
            eventsDetailedPanel.setVisible(false);
            orgsDetailedPanel.setVisible(false);
            studentDetailedPanel.setVisible(false);
            facultyDetailedPanel.setVisible(false);
            DashBoardPanel.setVisible(false);
            ClubsAndOrgsPanel.setVisible(false);
            EventsPanel.setVisible(true);
            UsersPanel.setVisible(false);

        }
    }//GEN-LAST:event_backButtonActionPerformed

    private void addParticipantsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addParticipantsButtonActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsersList().setVisible(true);
            }
        });
    }//GEN-LAST:event_addParticipantsButtonActionPerformed

    private void addOrgMembersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrgMembersButtonActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsersList().setVisible(true);
            }
        });
    }//GEN-LAST:event_addOrgMembersButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backUsersButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backUsersButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backUsersButton1ActionPerformed

    private void orgsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orgsTableMousePressed
        if (evt.isPopupTrigger()) {
            showPopUp(evt);
        }
    }//GEN-LAST:event_orgsTableMousePressed

    private void orgsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orgsTableMouseReleased
        if (evt.isPopupTrigger()) {
            showPopUp(evt);
        }
    }//GEN-LAST:event_orgsTableMouseReleased

    private void eventsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventsTableMousePressed
        if (evt.isPopupTrigger()) {
            showPopUp(evt);
        }
    }//GEN-LAST:event_eventsTableMousePressed

    private void eventsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventsTableMouseReleased
        if (evt.isPopupTrigger()) {
            showPopUp(evt);
        }
    }//GEN-LAST:event_eventsTableMouseReleased

    private void usersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMousePressed
        if (evt.isPopupTrigger()) {
            showPopUp(evt);
        }
    }//GEN-LAST:event_usersTableMousePressed

    private void usersTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseReleased
        if (evt.isPopupTrigger()) {
            showPopUp(evt);
        }
    }//GEN-LAST:event_usersTableMouseReleased

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CampusConnect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CampusConnect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CampusConnect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CampusConnect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CampusConnect().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ClubsAndOrgsPanel;
    private javax.swing.JPanel DashBoardPanel;
    private javax.swing.JPanel EventsPanel;
    private javax.swing.JPanel UsersPanel;
    private test.ButtonRound addEventButton;
    private test.ButtonRound addOrgMembersButton;
    private test.ButtonRound addOrgsButton;
    private test.ButtonRound addParticipantsButton;
    private test.ButtonRound addUsersButton;
    private javax.swing.JLabel adviserInfo;
    private javax.swing.JButton backButton;
    private javax.swing.JButton backToDashboardButton;
    private javax.swing.JButton backUsersButton;
    private javax.swing.JButton backUsersButton1;
    private test.ButtonRound binButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel clubAndOrgsLabel;
    private javax.swing.JLabel clubAndOrgsLabel1;
    private test.ButtonRound clubsAndOrgsButton;
    private test.ButtonRound dashboardButton;
    private javax.swing.JLabel eventNameInfo;
    public javax.swing.JTable eventParticipantsTable;
    private javax.swing.JLabel eventStatusInfo;
    private test.ButtonRound eventsButton;
    private javax.swing.JPanel eventsDetailedPanel;
    private test.Table eventsTable;
    private javax.swing.JPanel facultyDetailedPanel;
    private javax.swing.JLabel facultyEventsHeadedInfo;
    private javax.swing.JTable facultyHeadedEventsTable;
    private javax.swing.JLabel facultyNameInfo;
    private javax.swing.JTable facultyOrgAdvisoryTable;
    private javax.swing.JLabel facultyOrgsAdvisorInfo;
    private javax.swing.JLabel facultyTypeInfo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblEvent;
    private javax.swing.JLabel logoPlaceHolder;
    private test.PanelRound mainDashboard;
    public javax.swing.JTable membersTable;
    private javax.swing.JLabel officerInChargeInfo;
    private javax.swing.JTable orgEventTable;
    private javax.swing.JLabel orgOngoingEventsInfo;
    private javax.swing.JPanel orgsDetailedPanel;
    private javax.swing.JLabel orgsNameInfo;
    private test.Table orgsTable;
    private test.PanelRound panelRound1;
    private test.PanelRound panelRound10;
    private test.PanelRound panelRound11;
    private test.PanelRound panelRound12;
    private test.PanelRound panelRound13;
    private test.PanelRound panelRound14;
    private test.PanelRound panelRound15;
    private test.PanelRound panelRound16;
    private test.PanelRound panelRound17;
    private test.PanelRound panelRound18;
    private test.PanelRound panelRound19;
    private test.PanelRound panelRound2;
    private test.PanelRound panelRound20;
    private test.PanelRound panelRound21;
    private test.PanelRound panelRound22;
    private test.PanelRound panelRound24;
    private test.PanelRound panelRound25;
    private test.PanelRound panelRound3;
    private test.PanelRound panelRound4;
    private test.PanelRound panelRound5;
    private test.PanelRound panelRound6;
    private test.PanelRound panelRound7;
    private test.PanelRound panelRound8;
    private test.PanelRound panelRound9;
    private javax.swing.JMenuItem popupDeleteButton;
    private javax.swing.JMenuItem popupEditButton;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JMenuItem popupOpenButton;
    private javax.swing.JCheckBoxMenuItem popupSelectAllButton;
    private test.ButtonRound reportsButton;
    private javax.swing.JPanel sideDashboard;
    private javax.swing.JPanel studentDetailedPanel;
    private javax.swing.JLabel studentEventParticipatedLabel;
    private javax.swing.JLabel studentEventTableLabel;
    private javax.swing.JLabel studentNameInfo;
    private javax.swing.JLabel studentOrgsJoinedInfo;
    private javax.swing.JLabel studentOrgsJoinedLabel;
    private javax.swing.JTable studentOrgsJoinedTable;
    private javax.swing.JLabel studentOrgsTableLabel;
    private javax.swing.JLabel studentParticipatedEventsInfo;
    private javax.swing.JTable studentParticipatedEventsTable;
    private javax.swing.JLabel studentTypeInfo;
    private javax.swing.JLabel toolsLabel;
    private javax.swing.JLabel totalEvents;
    private javax.swing.JLabel totalEvents1;
    private javax.swing.JLabel totalEvents2;
    private javax.swing.JLabel totalEvents3;
    private javax.swing.JLabel totalEvents4;
    private javax.swing.JLabel totalEvents5;
    private javax.swing.JLabel totalMembersInfo;
    private javax.swing.JLabel totalParticipantsInfo;
    private javax.swing.JLabel totalParticipantsLabel;
    private javax.swing.JLabel totalUsers;
    private javax.swing.JLabel userTypeInfo10;
    private javax.swing.JLabel userTypeInfo11;
    private javax.swing.JLabel userTypeInfo12;
    private javax.swing.JLabel userTypeInfo13;
    private javax.swing.JLabel userTypeInfo14;
    private javax.swing.JLabel userTypeInfo4;
    private javax.swing.JLabel userTypeInfo7;
    private javax.swing.JLabel userTypeInfo8;
    private javax.swing.JLabel userTypeInfo9;
    private test.ButtonRound usersButton;
    private javax.swing.JLabel usersLabel;
    private test.Table usersTable;
    // End of variables declaration//GEN-END:variables
}
