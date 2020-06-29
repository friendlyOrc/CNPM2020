/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Stat;

import dao.BillDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.IncomeStat;
import model.User;

/**
 *
 * @author DELL
 */
public class StatDetailFrm  extends javax.swing.JFrame implements ActionListener{
    private User user;
    private JButton btnBack;
    private JTable tblStat;

    public StatDetailFrm() {
        super();
    }
    
    
    public StatDetailFrm(User user, int opt, IncomeStat is) {
        super("Thống kê chi tiết" );
        
        this.user = user;

        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));
        JLabel lblHome;
        
        if(opt == 1){
            lblHome = new JLabel("Thống kê theo tháng " + (is.getTime().getMonth() + 1) + "/" + (is.getTime().getYear() + 1900));
        }else if(opt == 2){
            lblHome = new JLabel("Thống kê theo quý " + (is.getTime().getMonth()/3 + 1) + "/" +(is.getTime().getYear() + 1900));
        }else{
            lblHome = new JLabel("Thống kê theo năm " + (is.getTime().getYear() + 1900));
        }
                
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
        lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0,20)));


        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2,BoxLayout.Y_AXIS));		
        tblStat = new JTable();
        JScrollPane scrollPane= new  JScrollPane(tblStat);
        
        String[] columnNames = {"ID Hóa đơn", "Tên khách hàng", "Tên phòng", "Thanh toán"};
        String[][] value = new String[is.getListBill().size()][4];
        for(int i=0; i<is.getListBill().size(); i++){
                value[i][0] = is.getListBill().get(i).getId() +"";
                value[i][1] = is.getListBill().get(i).getContract().getClient().getName() + "";
                value[i][2] = is.getListBill().get(i).getContract().getRoom().getName() + "";
                value[i][3] = String.valueOf(is.getListBill().get(i).getTotal() - is.getListBill().get(i).getDebt());
        }
        DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //unable to edit cells
               return false;
            }
        };
        tblStat.setModel(tableModel);

        pn2.add(scrollPane);
        pnMain.add(pn2);	
        
        JPanel pn3 = new JPanel();
        pn2.setLayout(new GridLayout(1, 1));
        btnBack = new JButton("Quay lại");
        btnBack.addActionListener((ActionListener) this);
        pn3.add(btnBack);
        
        pnMain.add(pn3);
        
        this.add(pnMain);
        this.setSize(600,300);				
        this.setLocation(200,10);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton)e.getSource();
        if(btnClicked.equals(btnBack)){
            (new SelectStatFrm(user)).setVisible(true);
            this.dispose();
            
        }
        
    }
}