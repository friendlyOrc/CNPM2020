package view.contract;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import dao.ContractDAO;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import dao.RoomDAO;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import model.Contract;
import model.Room;
import model.User;
import view.user.ManagerHomeFrm;

public class SearchRoomFrm extends JFrame implements ActionListener{
	private ArrayList<Room> listRoom;
        ArrayList<Room> listRoom1;
        ArrayList<Room> listRoom2;
	private JDateChooser getDateChooser = new JDateChooser();
        private JTextField price;
	private JButton btnSearch;
        private JButton btnBack;
	private JTable tblResult;
	private SearchRoomFrm mainFrm;
        private User user1;
        private Date chosenDate;
	public SearchRoomFrm(User user){
                super("Search room to choose");
                user1 = user;
		mainFrm = this;
		listRoom = new ArrayList<Room>();
		User user1 = user;
		JPanel pnMain = new JPanel();
		pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
		pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
		pnMain.add(Box.createRigidArea(new Dimension(0,10)));
		
		JLabel lblHome = new JLabel("Tìm phòng còn trống");
		lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
		lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
		pnMain.add(lblHome);
		pnMain.add(Box.createRigidArea(new Dimension(0,20)));
		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1,BoxLayout.X_AXIS));
//		pn1.setSize(this.getSize().width-5, 10);
                pn1.setSize(this.getSize().width-5,50);
                
                pn1.setLayout(new FlowLayout());
		pn1.add(new JLabel("Ngày bắt đầu ở: "));
		pn1.add(getDateChooser);
                pn1.add(new JLabel("Nhập giá phòng: "));
                
                price = new JTextField();
                price.setColumns(10);
                pn1.add(price);
                
                btnSearch = new JButton("Tìm kiếm");
                btnBack = new JButton("Quay lại");
		btnSearch.addActionListener(this);
                btnBack.addActionListener(this);
                
		pn1.add(btnSearch);
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
					(new SearchClientFrm(user1, listRoom.get(row))).setVisible(true);
					mainFrm.dispose();
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
//		 TODO Auto-generated method stub
		JButton btnClicked = (JButton)e.getSource();
                if(btnClicked.equals(btnBack)){
                    this.dispose();
                    ManagerHomeFrm m = new ManagerHomeFrm(user1);
                    m.setVisible(true);
                }
		if(btnClicked.equals(btnSearch)){
			if((price.getText() == null)||(price.getText().length() == 0))
				return;
                        chosenDate = getDateChooser.getDate();
                        System.out.println(chosenDate);
			RoomDAO rd = new RoomDAO();
                        ContractDAO cDAO = new ContractDAO();
                        String date = new SimpleDateFormat("yyyyMMdd").format(chosenDate);
			listRoom = rd.searchRoom(Float.parseFloat(price.getText().trim()), date);
                        
			String[] columnNames = {"Id", "Name", "Price", "Floor", "Type","RentalFlat"};
			String[][] value = new String[listRoom.size()][6];
			for(int i=0; i<listRoom.size(); i++){
				value[i][0] = listRoom.get(i).getId() +"";
				value[i][1] = listRoom.get(i).getName()+"";
				value[i][2] = listRoom.get(i).getPrice() +"";
				value[i][3] = listRoom.get(i).getFloor() +"";
                                value[i][4] = listRoom.get(i).getType() +"";
			}
			DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			       //unable to edit cells
			       return false;
			    }
			};
			tblResult.setModel(tableModel);
		}
	}
        public static void main(String[] args) {
            User u = new User();
        SearchRoomFrm m = new SearchRoomFrm(u);
        m.setVisible(true);
    }
}
