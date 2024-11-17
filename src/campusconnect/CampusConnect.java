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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        popupOpenButton = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        popupEditButton = new javax.swing.JMenuItem();
        popupDeleteButton = new javax.swing.JMenuItem();
        navigationBar = new test.PanelRound();
        dashboardButton = new test.ButtonRound();
        logoPlaceHolder = new javax.swing.JLabel();
        clubsAndOrgsButton = new test.ButtonRound();
        eventsButton = new test.ButtonRound();
        usersButton = new test.ButtonRound();
        mainDashboard = new javax.swing.JPanel();
        DashBoardPanel = new javax.swing.JPanel();
        lblEvent1 = new javax.swing.JLabel();
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

        popupMenu.setBorder(null);

        popupOpenButton.setText("Open");
        popupOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupOpenButtonActionPerformed(evt);
            }
        });
        popupMenu.add(popupOpenButton);
        popupMenu.add(jSeparator1);

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
        setMinimumSize(new java.awt.Dimension(1080, 720));
        setName("main"); // NOI18N

        navigationBar.setBackground(java.awt.SystemColor.controlShadow);
        navigationBar.setPreferredSize(new java.awt.Dimension(229, 574));

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

        logoPlaceHolder.setText("logo");

        clubsAndOrgsButton.setBackground(java.awt.Color.lightGray);
        clubsAndOrgsButton.setBorder(null);
        clubsAndOrgsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard16.png"))); // NOI18N
        clubsAndOrgsButton.setText("Clubs and Organizations");
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

        javax.swing.GroupLayout navigationBarLayout = new javax.swing.GroupLayout(navigationBar);
        navigationBar.setLayout(navigationBarLayout);
        navigationBarLayout.setHorizontalGroup(
            navigationBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navigationBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navigationBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logoPlaceHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(navigationBarLayout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addGroup(navigationBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dashboardButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clubsAndOrgsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
        );
        navigationBarLayout.setVerticalGroup(
            navigationBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navigationBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoPlaceHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(dashboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clubsAndOrgsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainDashboard.setBackground(new java.awt.Color(204, 255, 255));
        mainDashboard.setLayout(new java.awt.CardLayout());

        lblEvent1.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        lblEvent1.setText("Dashboard");

        javax.swing.GroupLayout DashBoardPanelLayout = new javax.swing.GroupLayout(DashBoardPanel);
        DashBoardPanel.setLayout(DashBoardPanelLayout);
        DashBoardPanelLayout.setHorizontalGroup(
            DashBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashBoardPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblEvent1)
                .addContainerGap(624, Short.MAX_VALUE))
        );
        DashBoardPanelLayout.setVerticalGroup(
            DashBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashBoardPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblEvent1)
                .addContainerGap(615, Short.MAX_VALUE))
        );

        mainDashboard.add(DashBoardPanel, "card2");

        jScrollPane2.setBorder(null);
        jScrollPane2.setAutoscrolls(true);

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
                .addGap(30, 30, 30)
                .addGroup(ClubsAndOrgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ClubsAndOrgsPanelLayout.createSequentialGroup()
                        .addComponent(clubAndOrgsLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ClubsAndOrgsPanelLayout.createSequentialGroup()
                        .addGroup(ClubsAndOrgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                            .addGroup(ClubsAndOrgsPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(addOrgsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))))
        );
        ClubsAndOrgsPanelLayout.setVerticalGroup(
            ClubsAndOrgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClubsAndOrgsPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(clubAndOrgsLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(addOrgsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
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
        eventsTable.setShowHorizontalLines(false);
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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton))
                .addGap(22, 22, 22))
        );

        mainDashboard.add(EventsPanel, "card4");

        jScrollPane3.setAutoscrolls(true);

        usersTable.setFont(new java.awt.Font("Inter Display", 0, 12)); // NOI18N
        usersTable.setModel(usersModel);
        usersTable.setRowHeight(30);
        usersTable.setShowHorizontalLines(false);
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
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(UsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton1))
                .addGap(22, 22, 22))
        );

        mainDashboard.add(UsersPanel, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navigationBar, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navigationBar, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
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

    private void popupEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupEditButtonActionPerformed

    }//GEN-LAST:event_popupEditButtonActionPerformed

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
                    try (Connection connection = conn()) { // Auto-close connection
                        connection.setAutoCommit(false); // Enable transaction for batch operations

                        PreparedStatement archiveStmt = null;
                        PreparedStatement deleteStmt = null;

                        if (activeTable == orgsTable) {
                            // SQL statement for organizations
                            String archiveOrgSQL = "INSERT INTO archived_orgs (org_name, member_count, level, adviser, details) VALUES (?, ?, ?, ?, ?)";
                            String deleteOrgSQL = "DELETE FROM orgs WHERE org_name = ?";
                            archiveStmt = connection.prepareStatement(archiveOrgSQL);
                            deleteStmt = connection.prepareStatement(deleteOrgSQL);
                        } else if (activeTable == eventsTable) {
                            // SQL statement for events
                            String archiveEventSQL = "INSERT INTO archived_events (event_name, start_date, end_date, start_time, end_time, location, user_access, club_assigned, faculty_assigned, details) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            String deleteEventSQL = "DELETE FROM orgs WHERE event_name = ?";
                            archiveStmt = connection.prepareStatement(archiveEventSQL);
                            deleteStmt = connection.prepareStatement(deleteEventSQL);
                        } else if (activeTable == usersTable) {
                            // SQL statement fro users
                            String archiveUserSQL = "INSERT INTO archived_users (user_id, user_name, password, user_type, student_type) VALUES (?, ?, ?, ?, ?)";
                            String deleteUserSQL = "DELETE FROM users WHERE user_id = ?";
                            archiveStmt = connection.prepareStatement(archiveUserSQL);
                            deleteStmt = connection.prepareStatement(deleteUserSQL);
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

                        connection.commit(); // Commit transaction

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

    }//GEN-LAST:event_popupOpenButtonActionPerformed

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
    private javax.swing.JLabel clubAndOrgsLabel;
    private test.ButtonRound clubsAndOrgsButton;
    private test.ButtonRound dashboardButton;
    private test.ButtonRound eventsButton;
    private javax.swing.JTable eventsTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblEvent;
    private javax.swing.JLabel lblEvent1;
    private javax.swing.JLabel logoPlaceHolder;
    private javax.swing.JPanel mainDashboard;
    private test.PanelRound navigationBar;
    private javax.swing.JTable orgsTable;
    private javax.swing.JMenuItem popupDeleteButton;
    private javax.swing.JMenuItem popupEditButton;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JMenuItem popupOpenButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton updateButton1;
    private test.ButtonRound usersButton;
    private javax.swing.JLabel usersLabel;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
