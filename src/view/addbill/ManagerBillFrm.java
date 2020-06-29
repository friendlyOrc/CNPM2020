package view.addbill;
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
import java.time.LocalDate;
import dao.BillDAO;
import java.time.Month;
import javax.swing.JOptionPane;
import model.Contract;
import model.User;
import view.user.ManagerHomeFrm;

public class ManagerBillFrm extends JFrame implements ActionListener{
	private ArrayList<Contract> listContract;
	private JTextField txtKey;
	private JButton btnSearch, btnBack;
	private JTable tblResult;
	private User user;
	private ManagerBillFrm mainFrm;
	
	public ManagerBillFrm(User user){
		super("Tìm phòng chưa lên hóa đơn");
		this.user = user;
		mainFrm = this;
		listContract = new ArrayList<Contract>();
		
		JPanel pnMain = new JPanel();
		pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
		pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
		pnMain.add(Box.createRigidArea(new Dimension(0,10)));
		
		JLabel lblHome = new JLabel("Tìm phòng chưa lên hóa đơn");
		lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
		lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
		pnMain.add(lblHome);
		pnMain.add(Box.createRigidArea(new Dimension(0,20)));
		
		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1,BoxLayout.X_AXIS));
		pn1.setSize(this.getSize().width-5, 20);
		pn1.add(new JLabel("Tháng: "));
		txtKey = new JTextField();
                String month = Integer.toString(java.time.LocalDate.now().getMonthValue());
                txtKey.setText(month);
                txtKey.setEditable(false);
		pn1.add(txtKey);
		btnSearch = new JButton("Tìm");
		btnSearch.addActionListener(this);
                btnBack = new JButton("Quay lại");
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
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));
		
		tblResult.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = tblResult.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
				int row = e.getY() / tblResult.getRowHeight(); // get the row of the button

				// *Checking the row or column is valid or not
				if (row < tblResult.getRowCount() && row >= 0 && column < tblResult.getColumnCount() && column >= 0) {
					(new AddBillFrm(user, listContract.get(row))).setVisible(true);
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
		// TODO Auto-generated method stub
		JButton btnClicked = (JButton)e.getSource();
		if(btnClicked.equals(btnSearch)){
			if((txtKey.getText() == null)||(txtKey.getText().length() == 0))
				return;
			BillDAO rd = new BillDAO();                        
			listContract = rd.searchContractToAddBill(Integer.parseInt(txtKey.getText().trim()));
                        if(listContract.size() == 0){
                            JOptionPane.showMessageDialog(this, "Tất cả các phòng đã được lên hóa đơn");
                            (new ManagerHomeFrm(user)).setVisible(true);
                            this.dispose();
                        }

			String[] columnNames = {"Id Hợp đồng", "Tên khách hàng", "Tháng", "Phòng", };
			String[][] value = new String[listContract.size()][4];
			for(int i=0; i<listContract.size() ; i++){
				value[i][0] = listContract.get(i).getId() +"";
				value[i][1] = listContract.get(i).getClient().getName();
				value[i][2] = txtKey.getText();
				value[i][3] = listContract.get(i).getRoom().getName();
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
                if(btnClicked.equals(btnBack)){
                    (new ManagerHomeFrm(user)).setVisible(true);
                    this.dispose();
                }
	}
}
