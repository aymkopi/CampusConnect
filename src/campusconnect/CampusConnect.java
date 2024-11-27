package campusconnect;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CampusConnect extends JFrame {

    Connection conn = conn();
    DefaultTableModel orgsModel = new DefaultTableModel(new String[]{"Org/Club Name", "Members", "Level", "Adviser", "Details"}, 0);
    DefaultTableModel eventsModel = new DefaultTableModel(new String[]{"Event Name", "Start Date", "End Date", "Start Time", "End Time", "Location", "User Access", "Club Assigned", "Faculty Assigned", "Details"}, 0);
    DefaultTableModel usersModel = new DefaultTableModel(new String[]{"User ID", "User Name", "Password", "User Type", "Student Type"}, 0);

    public CampusConnect() {
        initComponents();
        initTableData();

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

    //initialize table
    private void initTableData() {
        try {
            Statement st = conn.createStatement();

            //adds event data from database to eventsTable
            String selectEventsData = "SELECT * FROM events";
            ResultSet evrs = st.executeQuery(selectEventsData);

            while (evrs.next()) {
                String evn = evrs.getString("event_name");
                String fdt = evrs.getString("start_date");
                String tdt = evrs.getString("end_date");
                String ftm = evrs.getString("start_time");
                String ttm = evrs.getString("end_time");
                String lc = evrs.getString("location");
                String fw = evrs.getString("user_access");
                String ca = evrs.getString("club_assigned");
                String fa = evrs.getString("faculty_assigned");
                String de = evrs.getString("details");

                eventsModel.addRow(new Object[]{evn, fdt, tdt, ftm, ttm, lc, fw, ca, fa, de});

                //add total number of members in this part
            }

            //adds users data from database to usersTable
            String selectUsersData = "SELECT * FROM users";
            ResultSet usrs = st.executeQuery(selectUsersData);

            while (usrs.next()) {
                String uid = usrs.getString("user_id");
                String unm = usrs.getString("user_name");
                String psw = usrs.getString("password");
                String uty = usrs.getString("user_type");
                String sty = usrs.getString("student_type");

                usersModel.addRow(new Object[]{uid, unm, psw, uty, sty});
            }

            //adds orgs data from database to orgsTable
            String selectOrgsData = "SELECT * FROM orgs";
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
        mainDashboard = new test.PanelRound();
        ClubsAndOrgsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        orgsTable = new javax.swing.JTable();
        clubAndOrgsLabel = new javax.swing.JLabel();
        addOrgsButton = new test.ButtonRound();
        EventsPanel = new javax.swing.JPanel();
        lblEvent = new javax.swing.JLabel();
        addEventButton = new test.ButtonRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        eventsTable = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        UsersPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        updateButton1 = new javax.swing.JButton();
        addUsersButton = new test.ButtonRound();
        usersLabel = new javax.swing.JLabel();
        userDetailedPanel = new javax.swing.JPanel();
        backUsersButton = new javax.swing.JButton();
        userNameInfo = new javax.swing.JLabel();
        userTypeInfo = new javax.swing.JLabel();
        userTypeInfo1 = new javax.swing.JLabel();
        userTypeInfo2 = new javax.swing.JLabel();
        userNameInfo1 = new javax.swing.JLabel();
        userNameInfo2 = new javax.swing.JLabel();
        panelRound2 = new test.PanelRound();
        userTypeInfo5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelRound3 = new test.PanelRound();
        userTypeInfo6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        DashBoardPanel = new javax.swing.JPanel();
        panelRound5 = new test.PanelRound();
        clubAndOrgsLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

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
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1080, 720));
        setName("main"); // NOI18N

        sideDashboard.setBackground(new java.awt.Color(255, 204, 255));
        sideDashboard.setMaximumSize(new java.awt.Dimension(220, 32767));
        sideDashboard.setPreferredSize(new java.awt.Dimension(218, 715));

        dashboardButton.setBackground(java.awt.Color.lightGray);
        dashboardButton.setBorder(null);
        dashboardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard16.png"))); // NOI18N
        dashboardButton.setText("Dashboard");
        dashboardButton.setAlignmentX(0.5F);
        dashboardButton.setFocusPainted(false);
        dashboardButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        dashboardButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        dashboardButton.setIconTextGap(10);
        dashboardButton.setMargin(new java.awt.Insets(2, 15, 2, 14));
        dashboardButton.setRoundBottomLeft(10);
        dashboardButton.setRoundBottomRight(10);
        dashboardButton.setRoundTopLeft(10);
        dashboardButton.setRoundTopRight(10);
        dashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardButtonActionPerformed(evt);
            }
        });

        clubsAndOrgsButton.setBackground(java.awt.Color.lightGray);
        clubsAndOrgsButton.setBorder(null);
        clubsAndOrgsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard16.png"))); // NOI18N
        clubsAndOrgsButton.setText("Organizations");
        clubsAndOrgsButton.setActionCommand("ClubsAndOrganizations");
        clubsAndOrgsButton.setAlignmentX(0.5F);
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
        eventsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard16.png"))); // NOI18N
        eventsButton.setText("Events");
        eventsButton.setAlignmentX(0.5F);
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
        usersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard16.png"))); // NOI18N
        usersButton.setText("Users");
        usersButton.setAlignmentX(0.5F);
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

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
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

        javax.swing.GroupLayout sideDashboardLayout = new javax.swing.GroupLayout(sideDashboard);
        sideDashboard.setLayout(sideDashboardLayout);
        sideDashboardLayout.setHorizontalGroup(
            sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideDashboardLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logoPlaceHolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(sideDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dashboardButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clubsAndOrgsButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(usersButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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

        ClubsAndOrgsPanel.setOpaque(false);

        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane2.setAutoscrolls(true);

        orgsTable.setBorder(null);
        orgsTable.setFont(new java.awt.Font("Inter Display", 0, 12)); // NOI18N
        orgsTable.setModel(orgsModel);
        orgsTable.setRowHeight(30);
        orgsTable.setShowGrid(false);
        orgsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                orgsTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                orgsTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(orgsTable);

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

        javax.swing.GroupLayout ClubsAndOrgsPanelLayout = new javax.swing.GroupLayout(ClubsAndOrgsPanel);
        ClubsAndOrgsPanel.setLayout(ClubsAndOrgsPanelLayout);
        ClubsAndOrgsPanelLayout.setHorizontalGroup(
            ClubsAndOrgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClubsAndOrgsPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(ClubsAndOrgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ClubsAndOrgsPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(ClubsAndOrgsPanelLayout.createSequentialGroup()
                        .addComponent(clubAndOrgsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addOrgsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        ClubsAndOrgsPanelLayout.setVerticalGroup(
            ClubsAndOrgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClubsAndOrgsPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(ClubsAndOrgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addOrgsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clubAndOrgsLabel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        mainDashboard.add(ClubsAndOrgsPanel, "card3");

        EventsPanel.setBackground(new java.awt.Color(255, 204, 204));

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

        jScrollPane1.setAutoscrolls(true);

        eventsTable.setFont(new java.awt.Font("Inter Display", 0, 12)); // NOI18N
        eventsTable.setModel(eventsModel);
        eventsTable.setRowHeight(30);
        eventsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(eventsTable);

        updateButton.setText("update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EventsPanelLayout = new javax.swing.GroupLayout(EventsPanel);
        EventsPanel.setLayout(EventsPanelLayout);
        EventsPanelLayout.setHorizontalGroup(
            EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EventsPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EventsPanelLayout.createSequentialGroup()
                        .addComponent(lblEvent)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EventsPanelLayout.createSequentialGroup()
                        .addGroup(EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(EventsPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(updateButton)
                                .addGap(45, 45, 45)
                                .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))))
        );
        EventsPanelLayout.setVerticalGroup(
            EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EventsPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblEvent)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton))
                .addGap(22, 22, 22))
        );

        mainDashboard.add(EventsPanel, "card4");

        jScrollPane3.setBorder(BorderFactory.createEmptyBorder()
        );
        jScrollPane3.setAutoscrolls(true);

        usersTable.setBorder(BorderFactory.createEmptyBorder());
        usersTable.setFont(new java.awt.Font("Inter Display", 0, 14)); // NOI18N
        usersTable.setModel(usersModel);
        usersTable.setAutoscrolls(false);
        usersTable.setFocusable(false);
        usersTable.setRowHeight(30);
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

        updateButton1.setText("update");
        updateButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButton1ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout UsersPanelLayout = new javax.swing.GroupLayout(UsersPanel);
        UsersPanel.setLayout(UsersPanelLayout);
        UsersPanelLayout.setHorizontalGroup(
            UsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(UsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UsersPanelLayout.createSequentialGroup()
                        .addComponent(usersLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersPanelLayout.createSequentialGroup()
                        .addGroup(UsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
                            .addGroup(UsersPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(updateButton1)
                                .addGap(45, 45, 45)
                                .addComponent(addUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))))
        );
        UsersPanelLayout.setVerticalGroup(
            UsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(usersLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addGroup(UsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton1))
                .addGap(22, 22, 22))
        );

        mainDashboard.add(UsersPanel, "card5");

        userDetailedPanel.setBackground(new java.awt.Color(204, 255, 204));

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

        userNameInfo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        userNameInfo.setText("Justine Acuña");
        userNameInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        userTypeInfo.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        userTypeInfo.setText("Faculty");
        userTypeInfo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        userTypeInfo1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        userTypeInfo1.setText("Participated Events");

        userTypeInfo2.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        userTypeInfo2.setText("Clubs Joined");

        userNameInfo1.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        userNameInfo1.setText("312");

        userNameInfo2.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        userNameInfo2.setText("36");

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setPreferredSize(new java.awt.Dimension(673, 394));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        userTypeInfo5.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        userTypeInfo5.setText("Joined Clubs");
        userTypeInfo5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Event Name", "Date", "Status"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(userTypeInfo5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userTypeInfo5)
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

        userTypeInfo6.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        userTypeInfo6.setText("Participated Events");
        userTypeInfo6.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Event Name", "Date", "Status"
            }
        ));
        jScrollPane5.setViewportView(jTable2);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(userTypeInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userTypeInfo6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout userDetailedPanelLayout = new javax.swing.GroupLayout(userDetailedPanel);
        userDetailedPanel.setLayout(userDetailedPanelLayout);
        userDetailedPanelLayout.setHorizontalGroup(
            userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userDetailedPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(userDetailedPanelLayout.createSequentialGroup()
                        .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
                    .addGroup(userDetailedPanelLayout.createSequentialGroup()
                        .addGroup(userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(userDetailedPanelLayout.createSequentialGroup()
                                .addComponent(userTypeInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE))
                            .addGroup(userDetailedPanelLayout.createSequentialGroup()
                                .addComponent(userNameInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userTypeInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameInfo1))
                        .addGap(18, 18, 18)
                        .addGroup(userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userNameInfo2)
                            .addComponent(userTypeInfo2))))
                .addGap(43, 43, 43))
            .addGroup(userDetailedPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(backUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        userDetailedPanelLayout.setVerticalGroup(
            userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userDetailedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(userDetailedPanelLayout.createSequentialGroup()
                        .addGroup(userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userTypeInfo1)
                            .addComponent(userTypeInfo2))
                        .addGap(5, 5, 5)
                        .addGroup(userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userNameInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameInfo2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(userDetailedPanelLayout.createSequentialGroup()
                        .addComponent(userNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userTypeInfo)))
                .addGap(119, 119, 119)
                .addGroup(userDetailedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        mainDashboard.add(userDetailedPanel, "card6");

        DashBoardPanel.setBackground(new java.awt.Color(204, 255, 51));
        DashBoardPanel.setOpaque(false);

        panelRound5.setBackground(new java.awt.Color(255, 153, 153));
        panelRound5.setRoundBottomLeft(25);
        panelRound5.setRoundBottomRight(25);
        panelRound5.setRoundTopLeft(25);
        panelRound5.setRoundTopRight(25);

        clubAndOrgsLabel1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        clubAndOrgsLabel1.setText("Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(clubAndOrgsLabel1))
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(678, Short.MAX_VALUE))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(clubAndOrgsLabel1)
                .addGap(53, 53, 53)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(311, Short.MAX_VALUE))
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
            .addComponent(sideDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardButtonActionPerformed
        DashBoardPanel.setVisible(true);
        ClubsAndOrgsPanel.setVisible(false);
        EventsPanel.setVisible(false);
        UsersPanel.setVisible(false);

    }//GEN-LAST:event_dashboardButtonActionPerformed

    private void clubsAndOrgsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clubsAndOrgsButtonActionPerformed
        DashBoardPanel.setVisible(false);
        ClubsAndOrgsPanel.setVisible(true);
        EventsPanel.setVisible(false);
        UsersPanel.setVisible(false);


    }//GEN-LAST:event_clubsAndOrgsButtonActionPerformed

    private void eventsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventsButtonActionPerformed
        DashBoardPanel.setVisible(false);
        ClubsAndOrgsPanel.setVisible(false);
        EventsPanel.setVisible(true);
        UsersPanel.setVisible(false);

    }//GEN-LAST:event_eventsButtonActionPerformed

    private void usersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersButtonActionPerformed
        DashBoardPanel.setVisible(false);
        ClubsAndOrgsPanel.setVisible(false);
        EventsPanel.setVisible(false);
        UsersPanel.setVisible(true);
        if (usersButton.isSelected()){
            usersButton.setOpaque(true);
        }

    }//GEN-LAST:event_usersButtonActionPerformed

    private void addEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventButtonActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventsForm().setVisible(true);

            }
        });
    }//GEN-LAST:event_addEventButtonActionPerformed

    private void addOrgsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrgsButtonActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClubAndOrgForm().setVisible(true);
            }
        });
    }//GEN-LAST:event_addOrgsButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        eventsModel.setRowCount(0);
        initTableData();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void updateButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateButton1ActionPerformed

    private void addUsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUsersButtonActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsersForm().setVisible(true);
            }
        });
    }//GEN-LAST:event_addUsersButtonActionPerformed

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

    private void popupDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupDeleteButtonActionPerformed
        int[] selectedRows;
        JTable activeTable = null;

        if (ClubsAndOrgsPanel.isVisible()) {
            activeTable = orgsTable;
        } else if (EventsPanel.isVisible()) {
            activeTable = eventsTable;
        } else if (UsersPanel.isVisible()) {
            activeTable = usersTable;
        }

        if (activeTable != null) {
            selectedRows = activeTable.getSelectedRows(); // Get selected rows

            if (selectedRows.length > 0) {
                int confirmDelete = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this data?", "Delete?", JOptionPane.YES_NO_OPTION);

                if (confirmDelete == JOptionPane.YES_OPTION) {
                    try (Connection conn = conn()) { // Auto-close connection
                        conn.setAutoCommit(false); // Enable transaction for batch operations

                        PreparedStatement archiveStmt = null;
                        PreparedStatement deleteStmt = null;

                        if (activeTable == orgsTable) {
                            // SQL statement for organizations
                            String archiveOrgSQL = "INSERT INTO archived_orgs (org_name, member_count, level, adviser, details) VALUES (?, ?, ?, ?, ?)";
                            String deleteOrgSQL = "DELETE FROM orgs WHERE org_name = ?";
                            archiveStmt = conn.prepareStatement(archiveOrgSQL);
                            deleteStmt = conn.prepareStatement(deleteOrgSQL);
                        } else if (activeTable == eventsTable) {
                            // SQL statement for events
                            String archiveEventSQL = "INSERT INTO archived_events (event_name, start_date, end_date, start_time, end_time, location, user_access, club_assigned, faculty_assigned, details) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            String deleteEventSQL = "DELETE FROM orgs WHERE event_name = ?";
                            archiveStmt = conn.prepareStatement(archiveEventSQL);
                            deleteStmt = conn.prepareStatement(deleteEventSQL);
                        } else if (activeTable == usersTable) {
                            // SQL statement fro users
                            String archiveUserSQL = "INSERT INTO archived_users (user_id, user_name, password, user_type, student_type) VALUES (?, ?, ?, ?, ?)";
                            String deleteUserSQL = "DELETE FROM users WHERE user_id = ?";
                            archiveStmt = conn.prepareStatement(archiveUserSQL);
                            deleteStmt = conn.prepareStatement(deleteUserSQL);
                        }
                        // Iterate in reverse to prevent index shifting
                        for (int i = selectedRows.length - 1; i >= 0; i--) {
                            // Extract row data
                            if (activeTable == orgsTable) {
                                Object orgName = activeTable.getValueAt(selectedRows[i], 0);
                                Object memberCount = activeTable.getValueAt(selectedRows[i], 1);
                                Object level = activeTable.getValueAt(selectedRows[i], 2);
                                Object adviser = activeTable.getValueAt(selectedRows[i], 3);
                                Object details = activeTable.getValueAt(selectedRows[i], 4);

                                archiveStmt.setString(1, orgName.toString());
                                archiveStmt.setString(2, memberCount.toString());
                                archiveStmt.setString(3, level.toString());
                                archiveStmt.setString(4, adviser.toString());
                                archiveStmt.setString(5, details.toString());

                                deleteStmt.setString(1, orgName.toString());

                            } else if (activeTable == eventsTable) {
                                Object eventName = activeTable.getValueAt(selectedRows[i], 0);
                                Object startDate = activeTable.getValueAt(selectedRows[i], 1);
                                Object endDate = activeTable.getValueAt(selectedRows[i], 2);
                                Object startTime = activeTable.getValueAt(selectedRows[i], 3);
                                Object endTime = activeTable.getValueAt(selectedRows[i], 4);
                                Object location = activeTable.getValueAt(selectedRows[i], 5);
                                Object userAccess = activeTable.getValueAt(selectedRows[i], 6);
                                Object clubAssigned = activeTable.getValueAt(selectedRows[i], 7);
                                Object facultyAssigned = activeTable.getValueAt(selectedRows[i], 8);
                                Object details = activeTable.getValueAt(selectedRows[i], 9);

                                archiveStmt.setString(1, eventName.toString());
                                archiveStmt.setString(2, startDate.toString());
                                archiveStmt.setString(3, endDate.toString());
                                archiveStmt.setString(4, startTime.toString());
                                archiveStmt.setString(5, endTime.toString());
                                archiveStmt.setString(6, location.toString());
                                archiveStmt.setString(7, userAccess.toString());
                                archiveStmt.setString(8, clubAssigned.toString());
                                archiveStmt.setString(9, facultyAssigned.toString());
                                archiveStmt.setString(10, details.toString());

                                deleteStmt.setString(1, eventName.toString());

                            } else if (activeTable == usersTable) {
                                Object userID = usersTable.getValueAt(selectedRows[i], 0);
                                Object userName = usersTable.getValueAt(selectedRows[i], 1);
                                Object userPassword = usersTable.getValueAt(selectedRows[i], 2);
                                Object userType = usersTable.getValueAt(selectedRows[i], 3);
                                Object userStudentType = usersTable.getValueAt(selectedRows[i], 4);

                                archiveStmt.setString(1, userID.toString());
                                archiveStmt.setString(2, userName.toString());
                                archiveStmt.setString(3, userPassword.toString());
                                archiveStmt.setString(4, userType.toString());
                                archiveStmt.setString(5, userStudentType.toString());

                                deleteStmt.setString(1, userID.toString());
                            }

                            //Add to batch for archival
                            archiveStmt.addBatch();
                            deleteStmt.addBatch();

                            // Remove row from table
                            if (activeTable == orgsTable) {
                                ((DefaultTableModel) orgsTable.getModel()).removeRow(selectedRows[i]);
                            } else if (activeTable == eventsTable) {
                                ((DefaultTableModel) eventsTable.getModel()).removeRow(selectedRows[i]);
                            } else {
                                ((DefaultTableModel) usersTable.getModel()).removeRow(selectedRows[i]);
                            }

                        }

                        // Execute batch operations
                        if (archiveStmt != null && deleteStmt != null) {
                            archiveStmt.executeBatch();
                            deleteStmt.executeBatch();
                        }

                        conn.commit(); // Commit transaction

                        JOptionPane.showMessageDialog(null, "Selected rows have been deleted and archived successfully.");
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

    private void popupOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupOpenButtonActionPerformed
        int selectedRow;

        if (usersTable.isVisible()) {
            if (usersTable.getSelectedRowCount() == 1) {
                try {
                    Statement openUserDeets = conn().createStatement();
                    selectedRow = usersTable.getSelectedRow();

                    String getUserID = usersTable.getValueAt(selectedRow, 0).toString();

                    String openUser = "SELECT * FROM users WHERE user_id = '" + getUserID + "'";
                    var rs = openUserDeets.executeQuery(openUser);

                    rs.next();
                    String userID = rs.getString("user_id");
                    String userName = rs.getString("user_name");
                    String password = rs.getString("password");
                    String userType = rs.getString("user_type");
                    String studentType = rs.getString("student_type");

                    userNameInfo.setText(userName);
                    if (studentType.equals("null")) {
                        userTypeInfo.setText(userID + " | " + userType);
                    } else {
                        userTypeInfo.setText(userID + " | " + userType + " - " + studentType);
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                }

                userDetailedPanel.setVisible(true);
                DashBoardPanel.setVisible(false);
                ClubsAndOrgsPanel.setVisible(false);
                EventsPanel.setVisible(false);
                UsersPanel.setVisible(false);
            } else if (usersTable.getSelectedRowCount() < 1) {
                JOptionPane.showMessageDialog(null, "No rows selected.");
                usersTable.clearSelection();
            } else {
                JOptionPane.showMessageDialog(null, "Select only single user to open");
                usersTable.clearSelection();
            }

        }
    }//GEN-LAST:event_popupOpenButtonActionPerformed

    private void backUsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backUsersButtonActionPerformed
        if (userDetailedPanel.isVisible()) {
            userDetailedPanel.setVisible(false);
            DashBoardPanel.setVisible(false);
            ClubsAndOrgsPanel.setVisible(false);
            EventsPanel.setVisible(false);
            UsersPanel.setVisible(true);
        }
    }//GEN-LAST:event_backUsersButtonActionPerformed

    private void popupEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupEditButtonActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (ClubsAndOrgsPanel.isVisible()) {
                    new ClubAndOrgForm().setVisible(true);

                } else if (EventsPanel.isVisible()) {
                    new EventsForm().setVisible(true);
                } else {
                    new UsersForm().setVisible(true);
                }

            }
        });
    }//GEN-LAST:event_popupEditButtonActionPerformed

    private void popupSelectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupSelectAllButtonActionPerformed
        if (popupSelectAllButton.isSelected()) {
            usersTable.selectAll();
            popupSelectAllButton.setText("Deselect All");
        } else {
            usersTable.clearSelection();
            popupSelectAllButton.setText("Select All");
        }
    }//GEN-LAST:event_popupSelectAllButtonActionPerformed

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
    private test.ButtonRound addOrgsButton;
    private test.ButtonRound addUsersButton;
    private javax.swing.JButton backUsersButton;
    private javax.swing.JLabel clubAndOrgsLabel;
    private javax.swing.JLabel clubAndOrgsLabel1;
    private test.ButtonRound clubsAndOrgsButton;
    private test.ButtonRound dashboardButton;
    private test.ButtonRound eventsButton;
    private javax.swing.JTable eventsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblEvent;
    private javax.swing.JLabel logoPlaceHolder;
    private test.PanelRound mainDashboard;
    private javax.swing.JTable orgsTable;
    private test.PanelRound panelRound1;
    private test.PanelRound panelRound2;
    private test.PanelRound panelRound3;
    private test.PanelRound panelRound4;
    private test.PanelRound panelRound5;
    private javax.swing.JMenuItem popupDeleteButton;
    private javax.swing.JMenuItem popupEditButton;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JMenuItem popupOpenButton;
    private javax.swing.JCheckBoxMenuItem popupSelectAllButton;
    private javax.swing.JPanel sideDashboard;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton updateButton1;
    private javax.swing.JPanel userDetailedPanel;
    private javax.swing.JLabel userNameInfo;
    private javax.swing.JLabel userNameInfo1;
    private javax.swing.JLabel userNameInfo2;
    private javax.swing.JLabel userTypeInfo;
    private javax.swing.JLabel userTypeInfo1;
    private javax.swing.JLabel userTypeInfo2;
    private javax.swing.JLabel userTypeInfo5;
    private javax.swing.JLabel userTypeInfo6;
    private test.ButtonRound usersButton;
    private javax.swing.JLabel usersLabel;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
