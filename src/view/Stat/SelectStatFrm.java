/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Stat;

import dao.IncomeStatDAO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
import model.IncomeStat;
import model.User;
import view.Bill.SearchBillFrm;
import view.user.ManagerHomeFrm;

/**
 *
 * @author DELL
 */
public class SelectStatFrm extends javax.swing.JFrame implements ActionListener{
    private User user;
    private JButton btnYear, btnMonth, btnQuarter, btnBack;

    public SelectStatFrm() {
    }

    public SelectStatFrm(User user) {
        this.user = user;

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
        JPanel lblPane = new JPanel();
        lblPane.setLayout(new BoxLayout(lblPane, BoxLayout.LINE_AXIS));
        lblPane.add(Box.createRigidArea(new Dimension(450, 0)));
        JLabel lblUser = new JLabel("Loged in as: " + user.getName());
        lblUser.setAlignmentX(Component.RIGHT_ALIGNMENT);	
        lblPane.add(lblUser);
        listPane.add(lblPane);
        listPane.add(Box.createRigidArea(new Dimension(0,20)));

        JLabel lblHome = new JLabel("Thống kê doanh thu");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
        lblHome.setFont (lblHome.getFont ().deriveFont (28.0f));
        listPane.add(lblHome);
        listPane.add(Box.createRigidArea(new Dimension(0,20)));

        btnMonth = new JButton("Thống kê theo tháng");
        btnMonth.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMonth.addActionListener(this);
        listPane.add(btnMonth);
        listPane.add(Box.createRigidArea(new Dimension(0,20)));

        btnQuarter = new JButton("Thống kê theo quý");
        btnQuarter.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuarter.addActionListener(this);
        listPane.add(btnQuarter);
        listPane.add(Box.createRigidArea(new Dimension(0,20)));

        btnYear = new JButton("Thống kê theo năm");
        btnYear.setAlignmentX(Component.CENTER_ALIGNMENT);	
        btnYear.addActionListener(this);
        listPane.add(btnYear);
        listPane.add(Box.createRigidArea(new Dimension(0,15)));

        btnBack = new JButton("Quay lại");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        listPane.add(btnBack);
        listPane.add(Box.createRigidArea(new Dimension(0,10)));
        
        btnBack.addActionListener((ActionListener) this);

        this.setSize(600,300);				
        this.setLocation(200,10);
        this.add(listPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton)e.getSource();
        if(btnClicked.equals(btnBack)){
            (new ManagerHomeFrm(user)).setVisible(true);
            this.dispose();
            
        }else if(btnClicked.equals(btnMonth)){
            IncomeStatDAO isd = new IncomeStatDAO();
            ArrayList<IncomeStat> lis = isd.calcIncome(1);
            (new StatFrm(user,1, lis)).setVisible(true);
            this.dispose();
        }else if(btnClicked.equals(btnQuarter)){
            IncomeStatDAO isd = new IncomeStatDAO();
            ArrayList<IncomeStat> lis = isd.calcIncome(2);
            (new StatFrm(user,2, lis)).setVisible(true);
            this.dispose();
        }else if(btnClicked.equals(btnYear)){
            IncomeStatDAO isd = new IncomeStatDAO();
            ArrayList<IncomeStat> lis = isd.calcIncome(3);
            (new StatFrm(user,3, lis)).setVisible(true);
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(this, "Chức năng đang được hoàn thiện!");
        }
    }
    
    
}
