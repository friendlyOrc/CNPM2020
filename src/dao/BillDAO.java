/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Bill;
import model.Client;
import model.Contract;
import model.MonthlyService;
import model.RoomMonthlyService;
import model.RoomStaticService;
import model.Room;
import model.StaticService;
import model.User;

/**
 *
 * @author DELL
 */
public class BillDAO extends DAO{

    public BillDAO() {
        super();
    }
    
    public ArrayList<Bill> searchBill(String key){
        ArrayList<Bill> rsBill = new ArrayList();
//        Tìm Bill
        String sql = "SELECT * FROM tblBill WHERE id LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Bill temp = new Bill();
                temp.setId(rs.getInt("id"));
                temp.setMonth(rs.getInt("month"));
                temp.setRentingFee(rs.getFloat("rentingfee"));
                temp.setServiceFee(rs.getFloat("serviceFee"));
                temp.setWaterNumber(rs.getFloat("waternumber"));
                temp.setElectricityNumber(rs.getFloat("electricitynumber"));
                

                if(rs.getInt("billstatus") == 1){
                    temp.setBillStatus(true);
                }else{
                    temp.setBillStatus(false);
                };

//                    Tìm Contract theo ContractID trong tblBill
                int contID = rs.getInt("contractid");

                String sql2 = "SELECT * FROM tblContract WHERE id = ?";
                try{
                    ps = con.prepareStatement(sql2);
                    ps.setInt(1, contID);
                    ResultSet rs2 = ps.executeQuery();
                    if(rs2.next()){
                        Contract ct = new Contract();
                        ct.setId(rs2.getInt("id"));
                        ct.setUser(new User());
                        
                        int clientID = rs2.getInt("clientid");

//                            Tìm Client
                        String sqlClient = "SELECT name FROM tblClient WHERE id = ?";
                        try{
                            ps = con.prepareStatement(sqlClient);
                            ps.setInt(1, clientID);
                            ResultSet rsC = ps.executeQuery();
                            if(rsC.next()){
                                Client c = new Client();
                                c.setName(rsC.getString("name"));
                                
                                ct.setClient(c);
                            }
                        }catch(Exception e) {
                            e.printStackTrace();
                        }

//                            Tìm phòng đã thuê trong Contract
                        String sql3 = "SELECT id, name FROM tblRoom WHERE id = ?";
                        try{
                            ps = con.prepareStatement(sql3);
                            ps.setInt(1, rs2.getInt("roomid"));
                            ResultSet rs3 = ps.executeQuery();
                            Room r = new Room();
                            if(rs3.next()){
                                r.setId(rs3.getInt("id"));
                                r.setName(rs3.getString("name"));

//                                    Tìm phòng ứng với phòng đã thuê
//                                    Tìm Dịch vụ hàng tháng của phòng đã thuê
                                String sql5 = "SELECT * FROM tblRoomMonthlyService WHERE roomid = ?";
                                try{
                                    ps = con.prepareStatement(sql5);
                                    ps.setInt(1, r.getId());
                                    ResultSet rs5 = ps.executeQuery();
                                    ArrayList<RoomMonthlyService> rrms = new ArrayList();
                                    while(rs5.next()){
                                        RoomMonthlyService ms = new RoomMonthlyService();
                                        ms.setId(rs5.getInt("id"));
                                        ms.setPrice(rs5.getFloat("price"));
                                        ms.setNumber(rs5.getInt("number"));
//                                            Tìm dịch vụ ứng với RRSS
                                        String sql6 = "SELECT name FROM tblMonthlyService WHERE id = ?";
                                        try{
                                            ps = con.prepareStatement(sql6);
                                            ps.setInt(1,  rs5.getInt("monthlyserviceid"));
                                            ResultSet rs6 = ps.executeQuery();
                                            if(rs6.next()){
                                                MonthlyService s = new MonthlyService();
                                                s.setName(rs6.getString("name"));

                                                ms.setMonthlyService(s);
                                            }
                                        }catch(Exception e) {
                                            e.printStackTrace();        
                                        }
                                        rrms.add(ms);
                                    }
                                    r.setListMS(rrms);

                                }catch(Exception e) {
                                    e.printStackTrace();
                                }
//                                    Tìm dịch vụ cố định cho phòng đã thuê
                                sql5 = "SELECT * FROM tblRoomStaticService WHERE roomid = ?";
                                try{
                                    ps = con.prepareStatement(sql5);
                                    ps.setInt(1, r.getId());
                                    ResultSet rs5 = ps.executeQuery();
                                    ArrayList<RoomStaticService> rrss = new ArrayList();
                                    while(rs5.next()){
                                        RoomStaticService ss = new RoomStaticService();
                                        ss.setId(rs5.getInt("id"));
                                        ss.setPrice(rs5.getFloat("price"));
                                        ss.setNumber(rs5.getInt("number"));
//                                            Tìm dịch vụ ứng với RRSS
                                        String sql6 = "SELECT name FROM tblStaticService WHERE id = ?";
                                        try{
                                            ps = con.prepareStatement(sql6);
                                            ps.setInt(1, rs5.getInt("staticserviceid"));
                                            ResultSet rs6 = ps.executeQuery();
                                            if(rs6.next()){
                                                StaticService s = new StaticService();
                                                s.setName(rs6.getString("name"));

                                                ss.setStaticService(s);
                                            }
                                        }catch(Exception e) {
                                            e.printStackTrace();
                                        }
                                        rrss.add(ss);
                                    }
                                    r.setListSS(rrss);
                                }catch(Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            ct.setRoom(r);
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                        temp.setContract(ct);
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
                temp.setTotal(temp.getRentingFee()
                            + temp.getElectricityNumber()*temp.getContract().getRoom().getListMS().get(0).getPrice() 
                            + temp.getWaterNumber()*temp.getContract().getRoom().getListMS().get(1).getPrice() 
                            + temp.getServiceFee() + temp.getDebt());
                rsBill.add(temp);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rsBill;
    }
    
    public boolean updatePaidBill(Bill b, float paid){
        boolean rs = false;
        float debt = b.getTotal() - paid;
        String sql = "UPDATE tblBill SET billstatus = 1, debt = ? WHERE id = ?";
        String sqlMService = "UPDATE tblRoomMonthlyService SET number = ? WHERE id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, debt);
            ps.setInt(2, b.getId());
            int qr = ps.executeUpdate();
            if(qr == 1){
                rs = true;
            }else return rs;
            
            ps = con.prepareStatement(sqlMService);
            
            Room r = b.getContract().getRoom();

            ps.setFloat(1, r.getListMS().get(0).getNumber() + b.getElectricityNumber());
            ps.setInt(2, r.getListMS().get(0).getId());
            qr = ps.executeUpdate();
            if(qr == 1){
                rs = true;
            }else{
                rs = false;
                return rs;
            }

            ps.setFloat(1, r.getListMS().get(1).getNumber() + b.getWaterNumber());
            ps.setInt(2, r.getListMS().get(1).getId());
            qr = ps.executeUpdate();
            if(qr == 1){
                rs = true;
            }else{
                rs = false;
                return rs;
            }
                
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public boolean updateInfoBill(Bill b){
        boolean rs = false;
        String sqlBill = "UPDATE tblBill SET rentingFee = ?, electricityNumber = ?, waterNumber = ?, serviceFee = ? WHERE id = ?";
        String sqlSService = "UPDATE tblRoomStaticService SET `number` = ? WHERE id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sqlBill);
            ps.setFloat(1, b.getRentingFee());
            ps.setFloat(2, b.getElectricityNumber());
            ps.setFloat(3, b.getWaterNumber());
            ps.setFloat(4, b.getServiceFee());
            ps.setInt(5,  b.getId());
            int qr = ps.executeUpdate();
            if(qr == 1){
                rs = true;
            }else{
                return rs;
            }
            PreparedStatement ps2 = con.prepareStatement(sqlSService);
            Room r = b.getContract().getRoom();

            int sSNum = r.getListSS().size();
            for(int j = 0; j < sSNum; j++){
                ps2.setFloat(1, r.getListSS().get(j).getNumber());
                ps2.setInt(2, r.getListSS().get(j).getId());
                qr = ps2.executeUpdate();
                if(qr == 1){
                    rs = true;
                }else{
                    rs = false;
                    return rs;
                }
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
}
