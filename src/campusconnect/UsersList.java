package campusconnect;

import static campusconnect.CampusConnect.conn;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

class UsersList extends javax.swing.JFrame {

    public UsersList() {
        initComponents();
        initTableData();

    }

    private void initTableData() {
        try {
            Statement st = conn().createStatement();
            DefaultTableModel studentTableModel = (DefaultTableModel) studentsList.getModel();

            String getStudentListSQL = "SELECT * FROM users WHERE user_type = 'Student' AND is_deleted = 0";
            var rs = st.executeQuery(getStudentListSQL);

            while (rs.next()) {
                String studentID = rs.getString("user_id");
                String studentName = rs.getString("user_name");
                String studentLevel = rs.getString("student_type");
                
                studentTableModel.addRow(new Object[]{false,studentID, studentName, studentLevel});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eventLabel = new javax.swing.JLabel();
        addUserstoEventButton = new test.ButtonRound();
        textFieldRound1 = new test.TextFieldRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentsList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        eventLabel.setText("Users List");
        eventLabel.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N

        addUserstoEventButton.setText("Confirm");
        addUserstoEventButton.setBackground(new java.awt.Color(255, 255, 204));
        addUserstoEventButton.setBorder(null);
        addUserstoEventButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        addUserstoEventButton.setRoundBottomLeft(8);
        addUserstoEventButton.setRoundBottomRight(8);
        addUserstoEventButton.setRoundTopLeft(8);
        addUserstoEventButton.setRoundTopRight(8);
        addUserstoEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserstoEventButtonActionPerformed(evt);
            }
        });

        textFieldRound1.setBorder(null);
        textFieldRound1.setPlaceHolder("Search");
        textFieldRound1.setRoundBottomLeft(10);
        textFieldRound1.setRoundBottomRight(10);
        textFieldRound1.setRoundTopLeft(10);
        textFieldRound1.setRoundTopRight(10);

        studentsList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Student ID", "Student Name", "Level"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentsList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(studentsList);
        if (studentsList.getColumnModel().getColumnCount() > 0) {
            studentsList.getColumnModel().getColumn(0).setMaxWidth(20);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eventLabel)
                        .addGap(305, 305, 305)
                        .addComponent(addUserstoEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textFieldRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addUserstoEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(textFieldRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void addUserstoEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserstoEventButtonActionPerformed

    }//GEN-LAST:event_addUserstoEventButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private test.ButtonRound addUserstoEventButton;
    private javax.swing.JLabel eventLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable studentsList;
    private test.TextFieldRound textFieldRound1;
    // End of variables declaration//GEN-END:variables
}
