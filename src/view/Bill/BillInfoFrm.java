/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Bill;

import javax.swing.JButton;
import javax.swing.JTextField;
import dao.BillDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Bill;
import model.RoomMonthlyService;
import model.RoomStaticService;
import model.User;
import view.user.ManagerHomeFrm;

/**
 *
 * @author HP075
 */
public class BillInfoFrm extends javax.swing.JFrame implements ActionListener{

    private Bill bill;
    private JLabel lbId, lbRoomName, lbClient, lbMonth, lbRFee, lbSSFee, lbTotal, lbDebt;
    private JTextField txtPaid;
    private JTable tblMService, tblSService;
    private JButton btnConfirm, btnEdit;
    private User user;


    public BillInfoFrm(User user, Bill bill){
        super("Bill info");
        this.user = user;
        this.bill = bill;

        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel lblHome = new JLabel("Bill info");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
        lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0,20)));

        lbId = new JLabel(bill.getId() + "");
        lbRoomName = new JLabel(bill.getContract().getRoom().getName() + "");
        lbMonth = new JLabel(bill.getMonth() + "");
        lbRFee = new JLabel(bill.getRentingFee() + "");
        lbDebt = new JLabel(bill.getDebt()+ "");
        lbClient = new JLabel(bill.getContract().getClient().getName() + "");
        txtPaid = new JTextField(); txtPaid.setText("");
        
        tblMService = new JTable();
        JScrollPane scrollPane= new  JScrollPane(tblMService);
//        tblMService.setFillsViewportHeight(false); 
//        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 300));
        
        tblSService = new JTable();
        JScrollPane scrollPane2 = new JScrollPane(tblSService);
//        tblSService.setFillsViewportHeight(false); 
//        scrollPane2.setPreferredSize(new Dimension(scrollPane2.getPreferredSize().width, 300));
        
        btnConfirm = new JButton("Xác nhận");
        btnEdit = new JButton("Sửa");

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new GridLayout(4,1));
        
        JPanel content1 = new JPanel();
        content1.setLayout(new GridLayout(6,2));
        
        content1.add(new JLabel("Bill ID:"));           content1.add(lbId);
        content1.add(new JLabel("Room name:")); 	content1.add(lbRoomName);
        content1.add(new JLabel("Client: "));           content1.add(lbClient);
        content1.add(new JLabel("Month:"));             content1.add(lbMonth);
        content1.add(new JLabel("Price:"));             content1.add(lbRFee);
        content1.add(new JLabel("Debt:"));              content1.add(lbDebt);
        
        mainContent.add(content1);
        
        JPanel content2 = new JPanel();
        content2.setLayout(new GridLayout(2, 1));
        
        float total = bill.getRentingFee() + bill.getDebt();
        
        String[] columnNames = {"Id", "Tên dịch vụ", "Số tháng trước", "Số tháng này", "Đơn giá", "Thành tiền"};
        String[][] value = new String[2][6];
        for(int i=0; i< 2; i++){
            RoomMonthlyService sv = bill.getContract().getRoom().getListMS().get(i);
            value[i][0] = sv.getId() +"";
            value[i][1] = sv.getMonthlyService().getName();
            value[i][2] = sv.getNumber() + "";
            if(i == 0){
                value[i][3] = sv.getNumber() + bill.getElectricityNumber() + "";
                value[i][5] = sv.getPrice()*bill.getElectricityNumber() + "";
            }else{
                value[i][3] = sv.getNumber() + bill.getWaterNumber()+ "";
                value[i][5] = sv.getPrice()*bill.getWaterNumber() + "";
            }
            value[i][4] = sv.getPrice() + "";
            total += Float.parseFloat(value[i][5]);
        }
        DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //unable to edit cells
               return false;
            }
        };
        
        tblMService.setModel(tableModel);
        content2.add(new JLabel("Dịch vụ hàng tháng"));
        content2.add(scrollPane);
        
        mainContent.add(content2);
        
        String[] columnNames2 = {"Id", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"};
        ArrayList<RoomStaticService> rrss = bill.getContract().getRoom().getListSS();
        String[][] value2 = new String[rrss.size()][6];
        for(int i= 0; i< rrss.size(); i++){
            RoomStaticService sv = rrss.get(i);
            value2[i][0] = sv.getId() +"";
            value2[i][1] = sv.getStaticService().getName();
            value2[i][2] = sv.getNumber() + "";
            value2[i][3] = sv.getPrice() + "";
            value2[i][4] = sv.getNumber()*sv.getPrice() + "";
            total += Float.parseFloat(value[i][4]);
        }
        DefaultTableModel tableModel2 = new DefaultTableModel(value2, columnNames2) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //unable to edit cells
               return false;
            }
        };
        
        tblSService.setModel(tableModel2);
        
        JPanel content3 = new JPanel();
        content3.setLayout(new GridLayout(2, 1));
        content3.add(new JLabel("Dịch vụ cố định"));
        content3.add(scrollPane2);
        
        mainContent.add(content3);
        
        JPanel content4 = new JPanel();
        content4.setLayout(new GridLayout(3, 2));
        
        txtPaid = new JTextField(15);
        
        content4.add(new JLabel("Total:"));             content4.add(new JLabel(total + ""));
        content4.add(new JLabel("Paid: "));             content4.add(txtPaid);
        content4.add(btnConfirm);                       content4.add(btnEdit);
        
        mainContent.add(content4);
        
        pnMain.add(mainContent);		  
        btnConfirm.addActionListener(this);
        btnEdit.addActionListener(this);
		
        this.setContentPane(pnMain);
        this.setSize(500,600);				
        this.setLocation(200,10);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton btnClicked = (JButton)e.getSource();
            if(btnClicked.equals(btnConfirm)){
                BillDAO bd = new BillDAO();
                if(txtPaid.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Vui lòng điền số tiền thanh toán");
                }else if(bd.updatePaidBill(bill, Float.parseFloat(txtPaid.getText()))) {
                    JOptionPane.showMessageDialog(this, "Xác nhận thanh toán thành công");
                    (new ManagerHomeFrm(user)).setVisible(true);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "Lỗi");
                    (new ManagerHomeFrm(user)).setVisible(true);
                    this.dispose();
                }
            }
            if(btnClicked.equals(btnEdit)){
                (new EditBillFrm(user, bill)).setVisible(true);
                this.dispose();
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
