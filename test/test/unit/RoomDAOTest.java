/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.unit;

import dao.RoomDAO;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Room;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author DELL
 */
public class RoomDAOTest {
    RoomDAO rDAO  = new RoomDAO();
    
    @Test
    public void testSearchRoomException1(){
        float price = 11;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList <Room> listRoom = rDAO.searchRoom(price, sdf.format(date));
        Assert.assertNotNull(listRoom);
        Assert.assertEquals(0, listRoom.size());
    }
    
    
    @Test
    public void testSearchRoomException2(){
        float price = 3000000;
        Date date;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse("2020-06-29");
            System.out.println(date.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            ArrayList <Room> listRoom = rDAO.searchRoom(price, sdf.format(date));
            Assert.assertNotNull(listRoom);
            Assert.assertEquals(3, listRoom.size());
        } catch (ParseException ex) {
            Logger.getLogger(RoomDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testSearchRoomStandard01(){
        float price = 3000000;
        Date date;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse("2020-07-10");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            ArrayList <Room> listRoom = rDAO.searchRoom(price, sdf.format(date));
            Assert.assertNotNull(listRoom);
            Assert.assertEquals(3, listRoom.size());
        } catch (ParseException ex) {
            Logger.getLogger(RoomDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testSearchRoomStandard02(){
        float price = 1150000;
        Date date;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse("2020-07-10");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            ArrayList <Room> listRoom = rDAO.searchRoom(price, sdf.format(date));
            Assert.assertNotNull(listRoom);
            Assert.assertEquals(1, listRoom.size());
        } catch (ParseException ex) {
            Logger.getLogger(RoomDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
