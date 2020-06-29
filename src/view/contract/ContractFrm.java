/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contract;

import dao.ContractDAO;
import dao.RoomMonthlyServiceDAO;
import dao.RoomStaticServiceDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Client;
import model.Contract;
import model.MonthlyService;
import model.Room;
import model.RoomMonthlyService;
import model.RoomStaticService;
import model.StaticService;
import model.User;
import view.user.ManagerHomeFrm;

/**
 *
 * @author Admin
 */
public class ContractFrm extends javax.swing.JFrame {
    
    private Room cRoom;
    private Client cClient;
    private User user1;
    /**
     * Creates new form ContractFrm
     */
    public ContractFrm(User user, Room confirmedRoom, Client confirmedClient) {
        initComponents();
        user1 = user;
        cRoom = confirmedRoom;
        cClient = confirmedClient;
        userName.setText(user1.getName());
        userAddress.setText(user1.getAddress());
        userDOB.setText(user.getDateOfBirth().toString());
        userIN.setText(user1.getIdentityNumber());
        userPN.setText(user1.getPhoneNumber());
        
        clientName.setText(cClient.getName());
        clientAddress.setText(cClient.getAddress());
        clientDOB.setText(cClient.getDateOfBirth().toString());
        clientIN.setText(cClient.getIdentityNumber());
        clientPN.setText(cClient.getPhoneNumber());
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        userAddress = new javax.swing.JTextField();
        userDOB = new javax.swing.JTextField();
        userIN = new javax.swing.JTextField();
        userPN = new javax.swing.JTextField();
        clientName = new javax.swing.JTextField();
        clientAddress = new javax.swing.JTextField();
        clientDOB = new javax.swing.JTextField();
        clientIN = new javax.swing.JTextField();
        clientPN = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        electricityFeeText = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        waterFeeText = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        parkingFeeText = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cleaningFeeText = new javax.swing.JTextField();
        confirm = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        duration = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        depositText = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        electricCounterText = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        waterCounterText = new javax.swing.JTextField();
        checkInDay = new com.toedter.calendar.JDateChooser();
        btnReturn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("HỢP ĐỒNG CHO THUÊ NHÀ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Bên A: Chủ trọ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Bên B: Khách hàng");

        jLabel4.setText("Họ và tên: ");

        jLabel5.setText("Địa chỉ");

        jLabel6.setText("Ngày tháng năm sinh");

        jLabel7.setText("Số CMND");

        jLabel8.setText("Số điện thoại");

        jLabel9.setText("Họ và tên");

        jLabel10.setText("Địa chỉ");

        jLabel11.setText("Ngày tháng năm sinh");

        jLabel12.setText("Số CMND");

        jLabel13.setText("Số điện thoại");

        userName.setEditable(false);
        userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameActionPerformed(evt);
            }
        });

        userAddress.setEditable(false);

        userDOB.setEditable(false);

        userIN.setEditable(false);

        userPN.setEditable(false);

        clientName.setEditable(false);

        clientAddress.setEditable(false);

        clientDOB.setEditable(false);

        clientIN.setEditable(false);

        clientPN.setEditable(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("Điều khoản cho thuê");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n Lorem Ipsum has been the industry's standard dummy text ever since the \n1500s, when an unknown printer took a galley of type and scrambled it to \nmake a type specimen book. It has survived not only five centuries, but \nalso the leap into electronic typesetting, remaining essentially unchanged.\nIt was popularised in the 1960s with the release of Letraset sheets\n software like Aldus PageMaker including versions of Lorem Ipsum.");
        jScrollPane2.setViewportView(jTextArea2);

        jLabel15.setText("Dịch vụ điện (vnđ/số điện)");

        electricityFeeText.setText("4000");
        electricityFeeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                electricityFeeTextActionPerformed(evt);
            }
        });

        jLabel16.setText("Dịch vụ nước sạch (vnđ/khối nước)");

        waterFeeText.setText("25000");
        waterFeeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waterFeeTextActionPerformed(evt);
            }
        });

        jLabel17.setText("Gửi xe (vnđ/tháng)");

        parkingFeeText.setText("50000");
        parkingFeeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parkingFeeTextActionPerformed(evt);
            }
        });

        jLabel18.setText("Dịch vụ vệ sinh (vnđ/tháng)");

        cleaningFeeText.setText("50000");
        cleaningFeeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleaningFeeTextActionPerformed(evt);
            }
        });

        confirm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confirm.setText("XÁC NHẬN HỢP ĐỒNG");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        jLabel20.setText("Thời hạn (tháng)");

        jLabel21.setText("Ngày bắt đầu ở");

        jLabel22.setText("Tiền đặt cọc");

        depositText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositTextActionPerformed(evt);
            }
        });

        jLabel23.setText("Số điện ở công tơ điện");

        electricCounterText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                electricCounterTextActionPerformed(evt);
            }
        });

        jLabel24.setText("Số nước ở đồng hồ");

        waterCounterText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waterCounterTextActionPerformed(evt);
            }
        });

        btnReturn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReturn.setText("Quay lại");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(128, 128, 128))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userPN, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userIN, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clientPN, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clientIN, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(clientAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clientName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(electricityFeeText, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(waterFeeText))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(electricCounterText, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(parkingFeeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(82, 82, 82)
                                .addComponent(duration, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(waterCounterText, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(30, 30, 30)
                                .addComponent(cleaningFeeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(confirm)
                        .addGap(50, 50, 50)
                        .addComponent(btnReturn)
                        .addGap(179, 179, 179)))
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkInDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(26, 26, 26)
                .addComponent(depositText, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(userAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(userDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(userIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(userPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(clientIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(clientPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(clientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(clientAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(clientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel22)
                        .addComponent(depositText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkInDay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(electricCounterText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(waterCounterText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(electricityFeeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(parkingFeeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(waterFeeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cleaningFeeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirm)
                    .addComponent(btnReturn))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void electricityFeeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_electricityFeeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_electricityFeeTextActionPerformed

    private void waterFeeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waterFeeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_waterFeeTextActionPerformed

    private void parkingFeeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parkingFeeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_parkingFeeTextActionPerformed

    private void cleaningFeeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleaningFeeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cleaningFeeTextActionPerformed

    private void userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        // TODO add your handling code here:
//        while(1>0){
//        try{
//            if(checkInDay.toString().isEmpty()||depositText.toString().isEmpty() || electricCounterText.toString().isEmpty() || electricityFeeText.toString().isEmpty() || waterCounterText.toString().isEmpty() || waterFeeText.toString().isEmpty()){
//                throw new BlankException();
//            }
//            else break;
//        }catch(BlankException e){
//            JOptionPane.showMessageDialog(this, "Không được để trống các ô dữ liệu");
//            }
//        }
//        
//        while(1>0){
//        try{
//            if(parkingFeeText.toString().isEmpty() || cleaningFeeText.toString().isEmpty() || duration.toString().isEmpty()){
//                throw new BlankException();
//            }
//            else break;
//        }catch(BlankException e){
//            JOptionPane.showMessageDialog(this,"Không được để trống các ô dữ liệu");
//            }
//        }

//        while(1>0){
//            try{
//                if(duration.toString().isEmpty()){
//                    throw new BlankException();
//                }
//                else break;
//            }catch(BlankException e){
//                JOptionPane.showMessageDialog(this, "abc");
//            }
//        }

        Contract contract = new Contract();
        contract.setClient(cClient);
        contract.setRentingPrice(cRoom.getPrice());
        contract.setDeposit(Float.parseFloat(depositText.getText()));
        contract.setUser(user1);
        contract.setContractDuration(Integer.parseInt(duration.getText()));
        contract.setRoom(cRoom);
        contract.setCheckin(checkInDay.getDate());
        ContractDAO cDAO = new ContractDAO();
        if(cDAO.createContract(contract)){
            JOptionPane.showMessageDialog(this, "Thêm hợp đồng thành công!");
        }
        
        int electricityCounter = Integer.parseInt(electricCounterText.getText());
        float electricityFee = Float.parseFloat(electricityFeeText.getText());
        int waterCounter = Integer.parseInt(waterCounterText.getText());
        float waterFee = Float.parseFloat(waterFeeText.getText());
        
        MonthlyService ms1 = new MonthlyService(1, "Gửi xe", "Tiền gửi xe tính theo số xe");
        MonthlyService ms2 = new MonthlyService(2, "Vệ sinh", "Tiền dọn vệ sinh hàng tháng");
        
        StaticService ss1 = new StaticService(1, "Điện", "Tiền điện 1 tháng");
        StaticService ss2 = new StaticService(2, "Nước", "Tiền nước tính theo khối");
        
        float parkingFee = Float.parseFloat(parkingFeeText.getText());
        float cleaningFee = Float.parseFloat(cleaningFeeText.getText());
        
        RoomMonthlyService rms1 = new RoomMonthlyService();
        rms1.setMonthlyService(ms1);
        rms1.setNumber(6);
        rms1.setPrice(parkingFee);
        
        RoomMonthlyService rms2 = new RoomMonthlyService();
        rms2.setNumber(6);
        rms2.setMonthlyService(ms2);
        rms2.setPrice(cleaningFee);
        
        RoomStaticService rss1 = new RoomStaticService();
        rss1.setNumber(electricityCounter);
        rss1.setPrice(electricityFee);
        rss1.setStaticService(ss1);
        
        RoomStaticService rss2 = new RoomStaticService();
        rss2.setNumber(waterCounter);
        rss2.setPrice(waterFee);
        rss2.setStaticService(ss2);
        
        RoomStaticServiceDAO rssDAO = new RoomStaticServiceDAO();
        rssDAO.addRoomStaticService(rss1, cRoom);
        rssDAO.addRoomStaticService(rss2, cRoom);
        
        RoomMonthlyServiceDAO rmsDAO = new RoomMonthlyServiceDAO();
        rmsDAO.addRoomMonthlyService(rms1, cRoom);
        rmsDAO.addRoomMonthlyService(rms2, cRoom);
        
        this.dispose();
        ManagerHomeFrm mhf = new ManagerHomeFrm(user1);

        mhf.setVisible(true);
    }//GEN-LAST:event_confirmActionPerformed

    private void depositTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_depositTextActionPerformed

    private void electricCounterTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_electricCounterTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_electricCounterTextActionPerformed

    private void waterCounterTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waterCounterTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_waterCounterTextActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        SearchClientFrm m = new SearchClientFrm(user1, cRoom);
    }//GEN-LAST:event_btnReturnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReturn;
    private com.toedter.calendar.JDateChooser checkInDay;
    private javax.swing.JTextField cleaningFeeText;
    private javax.swing.JTextField clientAddress;
    private javax.swing.JTextField clientDOB;
    private javax.swing.JTextField clientIN;
    private javax.swing.JTextField clientName;
    private javax.swing.JTextField clientPN;
    private javax.swing.JButton confirm;
    private javax.swing.JTextField depositText;
    private javax.swing.JTextField duration;
    private javax.swing.JTextField electricCounterText;
    private javax.swing.JTextField electricityFeeText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField parkingFeeText;
    private javax.swing.JTextField userAddress;
    private javax.swing.JTextField userDOB;
    private javax.swing.JTextField userIN;
    private javax.swing.JTextField userName;
    private javax.swing.JTextField userPN;
    private javax.swing.JTextField waterCounterText;
    private javax.swing.JTextField waterFeeText;
    // End of variables declaration//GEN-END:variables
}