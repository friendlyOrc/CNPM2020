/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contract;

import Exception.BlankException;
import dao.ClientDAO;
import dao.RoomDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
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
import model.Client;
import model.Room;
import model.User;

/**
 *
 * @author Godzuuu
 */
public class SearchClientFrm extends JFrame implements ActionListener {

    private ArrayList<Client> listClient = new ArrayList<>();
    private JButton btnSearch;
    private JButton btnAdd;
    private JButton btnBack;
    private JTextField txtKey;
    private JTable tblResult;
    private SearchClientFrm mainFrm;
    
    private User user1;
    
    public SearchClientFrm(User user, Room room) {
        super("Search Client to choose");
		mainFrm = this;
		listClient = new ArrayList<Client>();
                user1 = user;
		JPanel pnMain = new JPanel();
		pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
		pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
		pnMain.add(Box.createRigidArea(new Dimension(0,10)));
		
		JLabel lblHome = new JLabel("Tìm kiếm danh sách khách hàng theo tên");
		lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
		lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
		pnMain.add(lblHome);
		pnMain.add(Box.createRigidArea(new Dimension(0,20)));
		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1,BoxLayout.X_AXIS));
		pn1.setSize(this.getSize().width-5, 10);
                pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
                pn1.add(new JLabel("Tên KH: "));
                txtKey = new JTextField();
                txtKey.setToolTipText("Nhập vào tên khách hàng ");
                pn1.add(txtKey);
                btnSearch = new JButton("Tìm kiếm");
                btnAdd = new JButton("Thêm");
                btnBack = new JButton("Quay lại");
		btnSearch.addActionListener(this);
                btnAdd.addActionListener(this);
                btnBack.addActionListener(this);
		pn1.add(btnSearch);
                pn1.add(btnAdd);
                pn1.add(btnBack);
		pnMain.add(pn1);
		pnMain.add(Box.createRigidArea(new Dimension(0,10)));

		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2,BoxLayout.Y_AXIS));		
		tblResult = new JTable();
		JScrollPane scrollPane= new  JScrollPane(tblResult);
		tblResult.setFillsViewportHeight(false); 
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 200));
		
		tblResult.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = tblResult.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
				int row = e.getY() / tblResult.getRowHeight(); // get the row of the button

				// *Checking the row or column is valid or not
				if (row < tblResult.getRowCount() && row >= 0 && column < tblResult.getColumnCount() && column >= 0) {
					ContractFrm c = new ContractFrm(user, room, listClient.get(row));
                                        c.setVisible(true);
					mainFrm.dispose();;
				}
			}
		});

		pn2.add(scrollPane);
		pnMain.add(pn2);	
		this.add(pnMain);
		this.setSize(600,300);				
		this.setLocation(200,10);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton)e.getSource();
        if(btnClicked.equals(btnAdd)){
            AddClientFrm m = new AddClientFrm();
            m.setVisible(true);
        }
        if(btnClicked.equals(btnBack)){
            this.dispose();
            SearchRoomFrm m = new SearchRoomFrm(user1);
        }
        if(btnClicked.equals(btnSearch)){
            if((txtKey.getText() == null)||(txtKey.getText().length() == 0)){
                return;
            }
            try{
                if(txtKey.getText() == null){
                    throw new BlankException();
                }
            }catch(BlankException e1){
                JOptionPane.showMessageDialog(this,"Không được để trống các ô!");
                return;
            }
            ClientDAO cd = new ClientDAO();
            listClient = cd.searchClient(txtKey.getText().trim());
            
            String[] columnNames = {"Id","Tên","Địa chỉ","Sinh nhật","Số CMND","Số điện thoại"};
            String[][] value = new String[listClient.size()][6];
            for(int i = 0; i < listClient.size(); i++){
                value[i][0] = listClient.get(i).getId() + "";
                value[i][1] = listClient.get(i).getName();
                value[i][2] = listClient.get(i).getAddress();
                value[i][3] = listClient.get(i).getDateOfBirth().toString();
                value[i][4] = listClient.get(i).getIdentityNumber();
                value[i][5] = listClient.get(i).getPhoneNumber();
            }
            DefaultTableModel model = new DefaultTableModel(value,columnNames){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            tblResult.setModel(model);
        }
        
    }
    public static void main(String[] args) {
        User u = new User();
        Room r = new Room();
        SearchClientFrm c = new SearchClientFrm(u, r);
        c.setVisible(true);
    }

}
