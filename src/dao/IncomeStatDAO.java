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
import model.IncomeStat;
import model.MonthlyService;
import model.Room;
import model.RoomMonthlyService;
import model.RoomStaticService;
import model.StaticService;
import model.User;

/**
 *
 * @author DELL
 */
public class IncomeStatDAO extends DAO{
    
    public IncomeStatDAO() {
        super();
    }
    
    public ArrayList<IncomeStat> calcIncome(int opt){
        ArrayList<IncomeStat> lis = new ArrayList();
        String sql;
        if(opt == 1){
            sql = "SELECT (tblBill.created) as `time`, \n" +
                    " sum((tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep - tblBill.debt)) as income \n" +
                    " FROM tblBill, tblContract, \n" +
                    "	(SELECT roomid, price as wp \n" +
                    "		FROM tblRoomMonthlyService\n" +
                    "        WHERE monthlyserviceid = 2) as msw,\n" +
                    "	(SELECT roomid, price as ep \n" +
                    "		FROM tblRoomMonthlyService\n" +
                    "        WHERE monthlyserviceid = 1) as mep\n" +
                    " WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1\n" +
                    " GROUP BY MONTH(tblBill.created) \n"
                    + "ORDER BY `time` DESC;";
        }else if(opt == 2){
            sql = "SELECT QUARTER(tblBill.created) as `quarter`, (tblBill.created) as `time`, \n" +
                    " sum((tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep  - tblBill.debt)) as income \n" +
                    " FROM tblBill, tblContract, \n" +
                    "	(SELECT roomid, price as wp \n" +
                    "		FROM tblRoomMonthlyService\n" +
                    "        WHERE monthlyserviceid = 2) as msw,\n" +
                    "	(SELECT roomid, price as ep \n" +
                    "		FROM tblRoomMonthlyService\n" +
                    "        WHERE monthlyserviceid = 1) as mep\n" +
                    " WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1\n" +
                    " GROUP BY `quarter` \n"
                    + "ORDER BY `quarter` DESC;";
        }else{
            sql = "SELECT YEAR(tblBill.created) as `year`, (tblBill.created) as `time`, \n" +
                    " sum((tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep  - tblBill.debt)) as income \n" +
                    " FROM tblBill, tblContract, \n" +
                    "	(SELECT roomid, price as wp \n" +
                    "		FROM tblRoomMonthlyService\n" +
                    "        WHERE monthlyserviceid = 2) as msw,\n" +
                    "	(SELECT roomid, price as ep \n" +
                    "		FROM tblRoomMonthlyService\n" +
                    "        WHERE monthlyserviceid = 1) as mep\n" +
                    " WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1\n" +
                    " GROUP BY `year` \n"
                    + "ORDER BY `year` DESC;";
        }
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                IncomeStat is = new IncomeStat();
                is.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("time")));
                is.setIncome(rs.getFloat("income"));
                
                int time;
                
                String sqlBill = "";
                if(opt == 1){
                    time = is.getTime().getMonth() + 1;
                    sqlBill = "SELECT (tblBill.created) as `time`, tblBill.id as id, tblBill.contractid as contractid, tblBill.debt as debt, \n" +
                                "(tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep) as income \n" +
                                "FROM tblBill, \n" +
                                "	tblContract, \n" +
                                "	(SELECT roomid, price as wp \n" +
                                "		FROM tblRoomMonthlyService\n" +
                                "        WHERE monthlyserviceid = 2) as msw,\n" +
                                "	(SELECT roomid, price as ep \n" +
                                "		FROM tblRoomMonthlyService\n" +
                                "        WHERE monthlyserviceid = 1) as mep\n" +
                                " WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1 AND MONTH(created) = ? \n" +
                                " ORDER BY `time` DESC;";
                }else if(opt == 2){
                    time = is.getTime().getMonth()/3 + 1;
                    sqlBill = "SELECT (tblBill.created) as `time`, tblBill.id as id, tblBill.contractid as contractid, tblBill.debt as debt, \n" +
                                "(tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep) as income \n" +
                                "FROM tblBill, \n" +
                                "	tblContract, \n" +
                                "	(SELECT roomid, price as wp \n" +
                                "		FROM tblRoomMonthlyService\n" +
                                "        WHERE monthlyserviceid = 2) as msw,\n" +
                                "	(SELECT roomid, price as ep \n" +
                                "		FROM tblRoomMonthlyService\n" +
                                "        WHERE monthlyserviceid = 1) as mep\n" +
                                " WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1 AND QUARTER(created) = ? \n" +
                                " ORDER BY `time` DESC;";
                }else{
                    time = is.getTime().getYear() + 1900;
                    sqlBill = "SELECT (tblBill.created) as `time`, tblBill.id as id, tblBill.contractid as contractid, tblBill.debt as debt, \n" +
                                "(tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep) as income \n" +
                                "FROM tblBill, \n" +
                                "	tblContract, \n" +
                                "	(SELECT roomid, price as wp \n" +
                                "		FROM tblRoomMonthlyService\n" +
                                "        WHERE monthlyserviceid = 2) as msw,\n" +
                                "	(SELECT roomid, price as ep \n" +
                                "		FROM tblRoomMonthlyService\n" +
                                "        WHERE monthlyserviceid = 1) as mep\n" +
                                " WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1 AND YEAR(created) = ? \n" +
                                " ORDER BY `time` DESC;";
                }
                try {
                    ps = con.prepareStatement(sqlBill);
                    ps.setInt(1, time);
                    ResultSet rsBill = ps.executeQuery();
                    ArrayList<Bill> lb = new ArrayList();
                    while(rsBill.next()) {
                        Bill b = new Bill();
                        b.setId(rsBill.getInt("id"));
                        b.setCreated(new SimpleDateFormat("yyyy-MM-dd").parse(rsBill.getString("time")));
                        b.setTotal(rsBill.getFloat("income"));
                        b.setDebt(rsBill.getFloat("debt"));

            //                    Tìm Contract theo ContractID trong tblBill
                        int contID = rsBill.getInt("contractid");
                        String sql2 = "SELECT id, clientid, roomid FROM tblContract WHERE id = ?";
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
                                    }
                                    ct.setRoom(r);
                                }catch(Exception e) {
                                    e.printStackTrace();
                                }
                                b.setContract(ct);
                            }
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                        lb.add(b);
                    }
                    is.setListBill(lb);
                    
                }catch(Exception e) {
                    e.printStackTrace();
                }
                lis.add(is);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return lis;
    }
    
    
}
