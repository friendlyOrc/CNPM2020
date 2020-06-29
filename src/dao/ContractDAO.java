/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Client;
import model.Contract;
import model.Room;
import model.User;
import view.contract.SearchRoomFrm;

/**
 *
 * @author Admin
 */
public class ContractDAO extends DAO{
    
    public boolean createContract(Contract contract){
        boolean rs = false;
        String sqlAddContract = "INSERT INTO tblcontract(checkin,rentingprice,deposit,contractduration,userid,clientid,roomid)VALUES (?,?,?,?,?,?,?)";
        try{
            con.setAutoCommit(true);
            PreparedStatement ps = con.prepareStatement(sqlAddContract,Statement.RETURN_GENERATED_KEYS);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            ps.setString(1,sdf.format(contract.getCheckin()));
            ps.setFloat(2, contract.getRentingPrice());
            ps.setFloat(3, contract.getDeposit());
            ps.setInt(4, contract.getContractDuration());
            ps.setInt(5, contract.getUser().getId());
            ps.setInt(6, contract.getClient().getId());
            ps.setInt(7,contract.getRoom().getId());
            
            if(ps.executeUpdate() == 1){
                rs = true;
                return rs;
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
				contract.setId(generatedKeys.getInt(1));
			}
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return rs;
    }
    
    public ArrayList<Contract> getAllContract(){
		ArrayList<Contract> result = new ArrayList<Contract>();
		String sql = "SELECT * FROM tblcontract";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
                            Contract c = new Contract();
                            Room r = new Room();
                            r.setId(rs.getInt("roomid"));
                            c.setCheckin(rs.getDate("checkin"));
                            c.setContractDuration(rs.getInt("contractduration"));
                            c.setRoom(r);
                            result.add(c);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
}
