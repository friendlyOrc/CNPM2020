/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import model.Room;
import model.RoomStaticService;

/**
 *
 * @author Admin
 */
public class RoomStaticServiceDAO {
    public boolean addRoomStaticService(RoomStaticService rsls,Room room){
        boolean rs = false;
        String sql = "INSERT INTO tblRoomMonthlyService(price,number,staticserviceid,roomid) VALUES (?,?,?,?)";
        try{
            con.setAutoCommit(true);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, rsls.getPrice());
            ps.setFloat(2, rsls.getNumber());
            ps.setInt(3, rsls.getStaticService().getId());
            ps.setInt(4, room.getId());
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
