package view.addbill;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dao.BillDAO;
import static java.lang.Float.parseFloat;
import java.util.ArrayList;
import model.Bill;
import model.Contract;
import model.RoomStaticService;
import model.User;
import view.user.ManagerHomeFrm;

public class AddBillFrm extends JFrame implements ActionListener{
	private Bill bill;
        private Contract contract;
	private JTextField txtWater, txtElectricity, txtPrice, txtLMWater, txtLMElectricity;
	private JButton btnAdd, btnBack;
	private User user;
        private float numberWater, numberElectricity, debt;
	
	
	public AddBillFrm(User user, Contract contract){
		super("Thêm hóa đơn");
		this.user = user;
		this.contract = contract;
		
		JPanel pnMain = new JPanel();
		pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
		pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
		pnMain.add(Box.createRigidArea(new Dimension(0,10)));
		
		JLabel lblHome = new JLabel("Thêm hóa đơn");
		lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
		lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
		pnMain.add(lblHome);
		pnMain.add(Box.createRigidArea(new Dimension(0,20)));
		

		txtWater = new JTextField(15);
		txtElectricity = new JTextField(15);
                txtPrice = new JTextField(15);
                txtLMElectricity = new JTextField(15);
                txtLMWater = new JTextField(15);
                btnAdd = new JButton("Thêm");
                btnBack = new JButton("Quay lại");
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(6,2));
                content.add(new JLabel("Tiền phòng:"));                 content.add(txtPrice);
                content.add(new JLabel("Số nước tháng trước:")); 	content.add(txtLMWater);
		content.add(new JLabel("Số nước tháng này:"));  	content.add(txtWater);
                content.add(new JLabel("Số điện tháng trước:")); 	content.add(txtLMElectricity);
		content.add(new JLabel("Số điện tháng này:"));  	content.add(txtElectricity);
		content.add(btnAdd); 	
                content.add(btnBack);
		pnMain.add(content);		  
		btnAdd.addActionListener(this);
                btnBack.addActionListener(this);
		
		initForm();		
		this.setContentPane(pnMain);
		this.setSize(600,300);				
		this.setLocation(200,10);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void initForm(){
		if(contract != null){
                    numberWater = contract.getRoom().getListMS().get(1).getNumber();
                    txtLMWater.setText(Float.toString(numberWater));
                    numberElectricity = contract.getRoom().getListMS().get(0).getNumber();
                    txtLMElectricity.setText(Float.toString(numberElectricity));
                    Bill b = new Bill();
                    BillDAO bd = new BillDAO();
                    b = bd.searchBill(contract.getId());
                    if(b != null){
                        debt = b.getDebt();
                    }
                    else debt = 0;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btnClicked = (JButton)e.getSource();
		if(btnClicked.equals(btnAdd)){ 
                    Bill b = new Bill();
                    BillDAO bd = new BillDAO();
                    if(txtWater.getText() == null || txtWater.getText().length() == 0
                        ||txtElectricity.getText() == null || txtElectricity.getText().length() == 0
                        ||txtPrice.getText() == null || txtPrice.getText().length() == 0){
                        JOptionPane.showMessageDialog(this, "Không được để trống");
                        return;                            
                    }
                    if(parseFloat(txtWater.getText()) - numberWater < 0){
                        JOptionPane.showMessageDialog(this, "Nhập sai số nước");
                        return;
                    }
                    if(parseFloat(txtElectricity.getText()) - numberElectricity < 0){
                        JOptionPane.showMessageDialog(this, "Nhập sai số điện");
                        return;
                    }
                    long millis=System.currentTimeMillis();  
                    java.sql.Date date=new java.sql.Date(millis);    
                    float total = 0;
                     ArrayList<RoomStaticService> rrss = contract.getRoom().getListSS();
                     String[][] value = new String[rrss.size()][6];
                    for(int i= 0; i< rrss.size(); i++){
                        RoomStaticService sv = rrss.get(i);
                        value[i][0] = sv.getId() +"";
                        value[i][1] = sv.getStaticService().getName();
                        value[i][2] = sv.getNumber() + "";
                        value[i][3] = sv.getPrice() + "";
                        value[i][4] = sv.getNumber()*sv.getPrice() + "";
                        total += Float.parseFloat(value[i][4]);
                    }
                    b.setId(bd.searchIdBill() + 1);
                    b.setWaterNumber(parseFloat(txtWater.getText()) - numberWater);
                    b.setElectricityNumber(parseFloat(txtElectricity.getText()) - numberElectricity);
                    b.setContract(contract);
                    b.setCreated(date);
                    b.setServiceFee(total);
                    b.setBillStatus(false);
                    b.setRentingFee(parseFloat(txtPrice.getText()));
                    b.setDebt(debt);
                    b.setTotal(b.getRentingFee()
                                + b.getElectricityNumber()* contract.getRoom().getListMS().get(0).getPrice()
                                + b.getWaterNumber() * contract.getRoom().getListMS().get(1).getPrice()
                                + b.getServiceFee() + b.getDebt());
                    if(bd.addBill(b)){
                        JOptionPane.showMessageDialog(this, "Thêm hoán đơn thành công");
                        (new BillFrm(user, b)).setVisible(true);
                        this.dispose();
                    }
		}
                if(btnClicked.equals(btnBack)){
                    (new ManagerBillFrm(user)).setVisible(true);
                    this.dispose();
                }
	}
}
