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
                    ps.setString(1,  String.valueOf(contID));
                    ResultSet rs2 = ps.executeQuery();
                    if(rs2.next()){
                        Contract ct = new Contract();
                        ct.setId(rs2.getInt("id"));
                        ct.setCheckin(new SimpleDateFormat("yyyy-MM-dd").parse(rs2.getString("checkin")));
                        ct.setContractDuration(rs2.getInt("contractduration"));
                        int userID = rs2.getInt("userid");
                        int clientID = rs2.getInt("clientid");

//                            Tìm user
                        String sqlUser = "SELECT * FROM tblUser WHERE id = ?";
                        try{
                            ps = con.prepareStatement(sqlUser);
                            ps.setString(1,  String.valueOf(userID));
                            ResultSet rsU = ps.executeQuery();
                            if(rsU.next()){
                                User u = new User();
                                u.setId(rsU.getInt("id"));
                                u.setName(rsU.getString("name"));
                                u.setAddress(rsU.getString("address"));
                                u.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(rsU.getString("dateofbirth")));
                                u.setIdentityNumber(rsU.getString("identitynumber"));
                                u.setPhoneNumber(rsU.getString("phonenumber"));
                                u.setUsername(rsU.getString("username"));
                                u.setPassword(rsU.getString("password"));

                                ct.setUser(u);
                            }
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
//                            Tìm Client
                        String sqlClient = "SELECT * FROM tblClient WHERE id = ?";
                        try{
                            ps = con.prepareStatement(sqlClient);
                            ps.setString(1,  String.valueOf(clientID));
                            ResultSet rsC = ps.executeQuery();
                            if(rsC.next()){
                                Client c = new Client();
                                c.setId(rsC.getInt("id"));
                                c.setName(rsC.getString("name"));
                                c.setAddress(rsC.getString("address"));
                                c.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(rsC.getString("dateofbirth")));
                                c.setIdentityNumber(rsC.getString("identitynumber"));
                                c.setPhoneNumber(rsC.getString("phonenumber"));

                                ct.setClient(c);
                            }
                        }catch(Exception e) {
                            e.printStackTrace();
                        }

//                            Tìm phòng đã thuê trong Contract
                        String sql3 = "SELECT * FROM tblRentedRoom WHERE contractid = ?";
                        try{
                            ps = con.prepareStatement(sql3);
                            ps.setString(1,  String.valueOf(ct.getId()));
                            ResultSet rs3 = ps.executeQuery();
                            ArrayList<RentedRoom> rra = new ArrayList();
                            while(rs3.next()){
                                RentedRoom rr = new RentedRoom();
                                rr.setId(rs3.getInt("id"));
                                rr.setRentingPrice(rs3.getFloat("rentingprice"));
                                rr.setNumberOfPerson(rs3.getInt("numberofperson"));

//                                    Tìm phòng ứng với phòng đã thuê
                                int roomID = rs3.getInt("roomid");
                                String sql4 = "SELECT * FROM tblRoom WHERE roomid = ?";
                                try{
                                    ps = con.prepareStatement(sql4);
                                    ps.setString(1,  String.valueOf(roomID));
                                    ResultSet rs4 = ps.executeQuery();
                                    if(rs4.next()){
                                        Room r = new Room();
                                        r.setId(roomID);
                                        r.setName(rs4.getString("name"));
                                        r.setPrice(rs4.getFloat("price"));
                                        r.setType(rs4.getString("type"));
                                        r.setFloor(rs4.getInt("floot"));
                                        r.setDescription(rs3.getString("description"));

                                        rr.setRoom(r);
                                    }
                                }catch(Exception e) {
                                    e.printStackTrace();
                                }

//                                    Tìm Dịch vụ hàng tháng của phòng đã thuê
                                String sql5 = "SELECT * FROM tblRentedRoomMonthlyService WHERE rentedroomid = ?";
                                try{
                                    ps = con.prepareStatement(sql5);
                                    ps.setString(1,  String.valueOf(rr.getId()));
                                    ResultSet rs5 = ps.executeQuery();
                                    ArrayList<RentedRoomMonthlyService> rrms = new ArrayList();
                                    while(rs5.next()){
                                        RentedRoomMonthlyService ms = new RentedRoomMonthlyService();
                                        ms.setId(rs5.getInt("id"));
                                        ms.setPrice(rs5.getFloat("price"));
                                        ms.setNumber(rs5.getInt("number"));
//                                            Tìm dịch vụ ứng với RRSS
                                        String sql6 = "SELECT * FROM tblMonthlyService WHERE id = ?";
                                        try{
                                            ps = con.prepareStatement(sql6);
                                            ps.setString(1,  String.valueOf(rs5.getInt("monthlyserviceid")));
                                            ResultSet rs6 = ps.executeQuery();
                                            if(rs6.next()){
                                                MonthlyService s = new MonthlyService();
                                                s.setId(rs6.getInt("id"));
                                                s.setName(rs6.getString("name"));
                                                s.setDescription(rs6.getString("description"));

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
                                    ps.setString(1,  String.valueOf(rr.getId()));
                                    ResultSet rs5 = ps.executeQuery();
                                    ArrayList<RentedRoomStaticService> rrss = new ArrayList();
                                    while(rs5.next()){
                                        RentedRoomStaticService ss = new RentedRoomStaticService();
                                        ss.setId(rs5.getInt("id"));
                                        ss.setPrice(rs5.getFloat("price"));
                                        ss.setNumber(rs5.getInt("number"));
//                                            Tìm dịch vụ ứng với RRSS
                                        String sql6 = "SELECT * FROM tblStaticService WHERE id = ?";
                                        try{
                                            ps = con.prepareStatement(sql6);
                                            ps.setString(1,  String.valueOf(rs5.getInt("staticserviceid")));
                                            ResultSet rs6 = ps.executeQuery();
                                            if(rs6.next()){
                                                StaticService s = new StaticService();
                                                s.setId(rs6.getInt("id"));
                                                s.setName(rs6.getString("name"));
                                                s.setDescription(rs6.getString("description"));

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
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rsBill;
    }
    
    public boolean updatePaidBill(Bill b){
        boolean rs = false;
        String sql = "UPDATE tblBill SET billstatus = 1 WHERE id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,  String.valueOf(b.getId()));
            int qr = ps.executeUpdate();
            if(qr == 1){
                rs = true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public boolean updateInfoBill(Bill b){
        boolean rs = false;
        String sql = "UPDATE tblBill SET  WHERE id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,  String.valueOf(b.getId()));
            int qr = ps.executeUpdate();
            if(qr == 1){
                rs = true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
}
