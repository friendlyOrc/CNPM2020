/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Bill;

import dao.BillDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @author DELL
 */
public class EditBillFrm extends javax.swing.JFrame implements ActionListener{
    private Bill bill;
    private JLabel lbId, lbRoomName, lbClient, lbMonth;
    private JTextField txtPaid, txtRFee, txtDebt;
    private JTable tblMService, tblSService;
    private JButton btnConfirm, btnReset, btnBack;
    private User user;
    DefaultTableModel tableModel, tableModel2;
	
	
    public EditBillFrm(User user, Bill bill){
        super("Edit a Bill");
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
        lbMonth = new JLabel(bill.getCreated().getMonth() + 1 + "");
        txtRFee = new JTextField(); txtRFee.setText(bill.getRentingFee() + "");
        txtDebt = new JTextField(); txtDebt.setText(bill.getDebt() + "");
        lbClient = new JLabel(bill.getContract().getClient().getName() + "");
        
        tblMService = new JTable();
        JScrollPane scrollPane= new  JScrollPane(tblMService);
//        tblMService.setFillsViewportHeight(false); 
//        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 300));
        
        tblSService = new JTable();
        JScrollPane scrollPane2 = new JScrollPane(tblSService);
//        tblSService.setFillsViewportHeight(false); 
//        scrollPane2.setPreferredSize(new Dimension(scrollPane2.getPreferredSize().width, 300));
        
        btnConfirm = new JButton("Sửa");
        btnReset = new JButton("Làm mới");
        btnBack = new JButton("Quay lại");

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new GridLayout(4,1));
        
        JPanel content1 = new JPanel();
        content1.setLayout(new GridLayout(6,2));
        
        content1.add(new JLabel("Bill ID:"));           content1.add(lbId);
        content1.add(new JLabel("Tên phòng:")); 	content1.add(lbRoomName);
        content1.add(new JLabel("Tên khách hàng: "));           content1.add(lbClient);
        content1.add(new JLabel("Tháng:"));             content1.add(lbMonth);
        content1.add(new JLabel("Giá phòng:"));             content1.add(txtRFee);
        content1.add(new JLabel("Nợ tháng trước:"));              content1.add(txtDebt);
        
        mainContent.add(content1);
        
        JPanel content2 = new JPanel();
        content2.setLayout(new GridLayout(2, 1));
        
        float total = bill.getRentingFee() + bill.getDebt();
        
        setTbl();
        
        content2.add(new JLabel("Dịch vụ hàng tháng"));
        content2.add(scrollPane);
        
        mainContent.add(content2);
        
        JPanel content3 = new JPanel();
        content3.setLayout(new GridLayout(2, 1));
        content3.add(new JLabel("Dịch vụ cố định"));
        content3.add(scrollPane2);
        
        mainContent.add(content3);
        
        JPanel content4 = new JPanel();
        content4.setLayout(new FlowLayout());
        
        content4.add(btnConfirm);       
        content4.add(btnReset);   
        content4.add(btnBack);
        
        mainContent.add(content4);
        
        
        pnMain.add(mainContent);		  
        btnConfirm.addActionListener(this);
        btnReset.addActionListener(this);
        btnBack.addActionListener(this);
        
		
        this.setContentPane(pnMain);
        this.setSize(500,600);				
        this.setLocation(200,10);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    private void setTbl(){
        String[] columnNames = {"Id", "Tên dịch vụ", "Số tháng trước", "Số tháng này", "Đơn giá"};
        String[][] value = new String[2][5];
        for(int i=0; i< 2; i++){
            RoomMonthlyService sv = bill.getContract().getRoom().getListMS().get(i);
            value[i][0] = sv.getId() +"";
            value[i][1] = sv.getMonthlyService().getName();
            value[i][2] = sv.getNumber() + "";
            if(i == 0){
                value[i][3] = sv.getNumber() + bill.getElectricityNumber() + "";
            }else{
                value[i][3] = sv.getNumber() + bill.getWaterNumber()+ "";
            }
            value[i][4] = sv.getPrice() + "";
        }
        tableModel = new DefaultTableModel(value, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //unable to edit cel
               if(column >= 3) return true;
               else
                return false;
            }
        };

        tblMService.setModel(tableModel);

        String[] columnNames2 = {"Id", "Tên dịch vụ", "Số lượng", "Đơn giá"};
        ArrayList<RoomStaticService> rrss = bill.getContract().getRoom().getListSS();
        String[][] value2 = new String[rrss.size()][6];
        for(int i= 0; i< rrss.size(); i++){
            RoomStaticService sv = rrss.get(i);
            value2[i][0] = sv.getId() +"";
            value2[i][1] = sv.getStaticService().getName();
            value2[i][2] = sv.getNumber() + "";
            value2[i][3] = sv.getPrice() + "";
        }
        tableModel2 = new DefaultTableModel(value2, columnNames2) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //unable to edit cells
               if(column >= 2) return true;
               else
                return false;
            }
        };

        tblSService.setModel(tableModel2);
    }
    private void initForm(){
        if(bill != null){
            txtRFee.setText(bill.getRentingFee() + "");
            txtDebt.setText(bill.getDebt() + "");
            
            setTbl();
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton btnClicked = (JButton)e.getSource();
        if(btnClicked.equals(btnReset)){
                initForm();
                return;
        }
        if(btnClicked.equals(btnConfirm)){
            if(txtRFee.getText().isBlank() || txtDebt.getText().isBlank()){
                JOptionPane.showMessageDialog(this, "Không được để trống thông tin!");
            }else{
                bill.setRentingFee(Float.parseFloat(txtRFee.getText()));
                bill.setDebt(Float.parseFloat(txtDebt.getText()));
                float OEN = bill.getContract().getRoom().getListMS().get(0).getNumber();
                float OWN = bill.getContract().getRoom().getListMS().get(1).getNumber();
                bill.setElectricityNumber(Float.parseFloat(tblMService.getValueAt(0, 3).toString()) - OEN);
                bill.setWaterNumber(Float.parseFloat(tblMService.getValueAt(1, 3).toString()) - OWN);

                bill.getContract().getRoom().getListMS().get(0).setPrice(Float.parseFloat(tblMService.getValueAt(0, 4).toString()));

                bill.getContract().getRoom().getListMS().get(1).setPrice(Float.parseFloat(tblMService.getValueAt(1, 4).toString()));

                int sNum = bill.getContract().getRoom().getListSS().size();
                for(int i = 0; i < sNum; i++){
                    bill.getContract().getRoom().getListSS().get(i).setNumber(Float.parseFloat(tblSService.getValueAt(i, 2).toString()));
                    bill.getContract().getRoom().getListSS().get(i).setPrice(Float.parseFloat(tblSService.getValueAt(i, 3).toString()));
                }
                BillDAO bd = new BillDAO();
                bd.updateInfoBill(bill);

                JOptionPane.showMessageDialog(this, "Sửa Bill thành công!");

                (new BillInfoFrm(user, bill)).setVisible(true);
                this.dispose();
            }
        }
        
        if(btnClicked.equals(btnBack)){
            (new BillInfoFrm(user, bill)).setVisible(true);
            this.dispose();
            
        }
    }

}
