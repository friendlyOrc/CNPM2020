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
import model.RentedRoom;
import model.RentedRoomMonthlyService;
import model.RentedRoomStaticService;
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
                temp.setRoomId(rs.getInt("roomid"));
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
                        ct.setCheckin(new SimpleDateFormat("yyyy-MM-dd").parse(rs2.getString("checkin")));
                        ct.setContractDuration(rs2.getInt("contractduration"));
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
                        String sql3 = "SELECT id, roomid FROM tblRentedRoom WHERE contractid = ? and roomid = ?";
                        try{
                            ps = con.prepareStatement(sql3);
                            ps.setInt(1, ct.getId());
                            ps.setInt(2, temp.getRoomId());
                            ResultSet rs3 = ps.executeQuery();
                            ArrayList<RentedRoom> rra = new ArrayList();
                            if(rs3.next()){
                                RentedRoom rr = new RentedRoom();
                                rr.setId(rs3.getInt("id"));

//                                    Tìm phòng ứng với phòng đã thuê
                                int roomID = rs3.getInt("roomid");
                                String sql4 = "SELECT name FROM tblRoom WHERE id = ?";
                                try{
                                    ps = con.prepareStatement(sql4);
                                    ps.setInt(1, roomID);
                                    ResultSet rs4 = ps.executeQuery();
                                    if(rs4.next()){
                                        Room r = new Room();
                                        r.setId(roomID);
                                        r.setName(rs4.getString("name"));

                                        rr.setRoom(r);
                                    }
                                }catch(Exception e) {
                                    e.printStackTrace();
                                }

//                                    Tìm Dịch vụ hàng tháng của phòng đã thuê
                                String sql5 = "SELECT * FROM tblRentedRoomMonthlyService WHERE rentedroomid = ?";
                                try{
                                    ps = con.prepareStatement(sql5);
                                    ps.setInt(1, rr.getId());
                                    ResultSet rs5 = ps.executeQuery();
                                    ArrayList<RentedRoomMonthlyService> rrms = new ArrayList();
                                    while(rs5.next()){
                                        RentedRoomMonthlyService ms = new RentedRoomMonthlyService();
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
                                    rr.setRrmService(rrms);

                                }catch(Exception e) {
                                    e.printStackTrace();
                                }
//                                    Tìm dịch vụ cố định cho phòng đã thuê
                                sql5 = "SELECT * FROM tblRentedRoomStaticService WHERE rentedroomid = ?";
                                try{
                                    ps = con.prepareStatement(sql5);
                                    ps.setInt(1, rr.getId());
                                    ResultSet rs5 = ps.executeQuery();
                                    ArrayList<RentedRoomStaticService> rrss = new ArrayList();
                                    while(rs5.next()){
                                        RentedRoomStaticService ss = new RentedRoomStaticService();
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
                                    rr.setRrsService(rrss);
                                }catch(Exception e) {
                                    e.printStackTrace();
                                }
                                rra.add(rr);
                            }
                            ct.setRooms(rra);
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                        temp.setContract(ct);
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            rsBill.add(temp);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rsBill;
    }
    
    public boolean updatePaidBill(Bill b){
        boolean rs = false;
        String sql = "UPDATE tblBill SET billstatus = 1 WHERE id = ?";
        String sqlMService = "UPDATE tblRentedRoomMonthlyService SET number = ? WHERE id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, b.getId());
            int qr = ps.executeUpdate();
            if(qr == 1){
                rs = true;
            }else return rs;
            int roomNum = b.getContract().getRooms().size();
            
            ps = con.prepareStatement(sqlMService);
            for(int i = 0; i < roomNum; i++){
                RentedRoom rr = b.getContract().getRooms().get(i);
                
                ps.setFloat(1, rr.getRrmService().get(0).getNumber() + b.getElectricityNumber());
                ps.setInt(2, rr.getRrmService().get(0).getId());
                qr = ps.executeUpdate();
                if(qr == 1){
                    rs = true;
                }else{
                    rs = false;
                    return rs;
                }
                
                ps.setFloat(1, rr.getRrmService().get(1).getNumber() + b.getWaterNumber());
                ps.setInt(2, rr.getRrmService().get(1).getId());
                qr = ps.executeUpdate();
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
    
    public boolean updateInfoBill(Bill b){
        boolean rs = false;
        String sqlBill = "UPDATE tblBill SET rentingFee = ?, electricityNumber = ?, waterNumber = ?, serviceFee = ? WHERE id = ?";
        String sqlSService = "UPDATE tblRentedRoomStaticService SET `number` = ? WHERE id = ?";
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
            int roomNum = b.getContract().getRooms().size();
            PreparedStatement ps2 = con.prepareStatement(sqlSService);
            for(int i = 0; i < roomNum; i++){
                RentedRoom rr = b.getContract().getRooms().get(i);
                
                int sSNum = rr.getRrsService().size();
                for(int j = 0; j < sSNum; j++){
                    ps2.setFloat(1, rr.getRrsService().get(j).getNumber());
                    ps2.setInt(2, rr.getRrsService().get(j).getId());
                    qr = ps2.executeUpdate();
                    if(qr == 1){
                        rs = true;
                    }else{
                        rs = false;
                        return rs;
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
}
