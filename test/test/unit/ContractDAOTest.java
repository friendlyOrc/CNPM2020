///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package test.unit;
//
//import dao.ContractDAO;
//import static dao.DAO.con;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import model.Client;
//import model.Contract;
//import model.Room;
//import model.User;
//import org.junit.Assert;
//import org.junit.Test;
//
///**
// *
// * @author Admin
// */
//public class ContractDAOTest {
//
//    ContractDAO cDAO = new ContractDAO();
//
//    @Test
//    public void testAddContractStandard() throws SQLException {
//        Contract c = new Contract();
//        try {
//            con.setAutoCommit(false);
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-09");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            int contractDuration = 6;
//            float rentingPrice = 1500000;
//            float deposit = 500000;
//            User u = new User();
//            u.setId(1);
//            Client cl = new Client();
//            c.setId(1);
//            Room r = new Room();
//            r.setId(2);
//            c.setCheckin(date);
//            c.setContractDuration(contractDuration);
//            c.setRentingPrice(rentingPrice);
//            c.setDeposit(deposit);
//            c.setUser(u);
//            c.setClient(cl);
//            c.setRoom(r);
//            if(cDAO.createContract(c)){
//                System.out.println("1");
//            }
//            ArrayList <Contract> listClient = cDAO.getAllContract();
//            Assert.assertNotNull(listClient);
//            Assert.assertEquals(sdf.format(date), listClient.get(0).getCheckin());
//            Assert.assertEquals(contractDuration, listClient.get(0).getContractDuration());
//            Assert.assertEquals(u.getId(), listClient.get(0).getUser().getId());
//            Assert.assertEquals(r.getId(), listClient.get(0).getRoom().getId());
//            Assert.assertEquals(cl.getId(), listClient.get(0).getId());
//            con.rollback();
//            con.setAutoCommit(true);
//            
//        } catch (ParseException ex) {
//            Logger.getLogger(ContractDAOTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
