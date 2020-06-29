/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import model.Room;
import model.RoomMonthlyService;

/**
 *
 * @author Admin
 */
public class RoomMonthlyServiceDAO extends DAO{
    public boolean addRoomMonthlyService(RoomMonthlyService rmls,Room room){
        boolean rs = false;
        String sql = "INSERT INTO tblRoomMonthlyService(price,number,monthlyserviceid,roomid) VALUES (?,?,?,?)";
        try{
            con.setAutoCommit(true);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, rmls.getPrice());
            ps.setFloat(2,rmls.getNumber());
            ps.setInt(3,rmls.getMonthlyService().getId());
            ps.setInt(4,room.getId());
            if(ps.executeUpdate()==1){
                rs = true;
                return rs;
            }
        }catch(Exception e){
            rs = false;
            return rs;
        }
        return rs;
    }
}
