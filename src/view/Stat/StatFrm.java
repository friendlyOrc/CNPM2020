/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Stat;

import java.awt.BorderLayout;
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
import model.IncomeStat;
import model.User;
import view.Bill.BillInfoFrm;

/**
 *
 * @author DELL
 */
public class StatFrm extends javax.swing.JFrame implements ActionListener{
    private User user;
    private JButton btnBack;
    private JTable tblStat;
    private StatFrm mainFrm;
    private ArrayList<IncomeStat> lis;
    
    
    public StatFrm(User user, int opt, ArrayList<IncomeStat> lis) {
        super("Thống kê");
        
        mainFrm = this;
        this.user = user;
        this.lis = lis;

        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel lblHome;
        
        if(opt == 1){
            lblHome = new JLabel("Thống kê theo tháng");
        }else if(opt == 2){
            lblHome = new JLabel("Thống kê theo quý");
        }else{
            lblHome = new JLabel("Thống kê theo năm");
        }
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
        lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0,20)));

        

        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2,BoxLayout.Y_AXIS));		
        tblStat = new JTable();
        JScrollPane scrollPane= new  JScrollPane(tblStat);
        tblStat.setFillsViewportHeight(false); 
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));
        
        
        tblStat.setModel(tblUpdate(opt));

        tblStat.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int column = tblStat.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                    int row = e.getY() / tblStat.getRowHeight(); // get the row of the button

                    // *Checking the row or column is valid or not
                    if (row < tblStat.getRowCount() && row >= 0 && column < tblStat.getColumnCount() && column >= 0) {
                        
                        (new StatDetailFrm(user, opt, lis.get(row))).setVisible(true);
                        mainFrm.dispose();
                    }
                }
        });

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
    
    public DefaultTableModel tblUpdate(int opt){
        if(opt == 1){
            String[] columnNames = {"Tháng", "Năm", "Doanh thu"};
            String[][] value = new String[lis.size()][3];
            for(int i=0; i<lis.size(); i++){
                    value[i][0] = lis.get(i).getTime().getMonth() + 1 +"";
                    value[i][1] = lis.get(i).getTime().getYear() + 1900 + "";
                    value[i][2] = String.valueOf(lis.get(i).getIncome());
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            return tableModel;
        }else if(opt == 2){
            String[] columnNames = {"Quý", "Năm", "Doanh thu"};
            String[][] value = new String[lis.size()][3];
            for(int i=0; i<lis.size(); i++){
                    value[i][0] = lis.get(i).getTime().getMonth()/3 + 1 +"";
                    value[i][1] = lis.get(i).getTime().getYear() + 1900 + "";
                    value[i][2] = String.valueOf(lis.get(i).getIncome());
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            return tableModel;
        }else{
            String[] columnNames = {"Năm", "Doanh thu"};
            String[][] value = new String[lis.size()][3];
            for(int i=0; i<lis.size(); i++){
                    value[i][0] = lis.get(i).getTime().getYear() + 1900 + "";
                    value[i][1] = String.valueOf(lis.get(i).getIncome());
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            return tableModel;
        }
        
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
