/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.unit;

import dao.ClientDAO;
import static dao.DAO.con;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author DELL
 */
public class ClientDAOTest {

    ClientDAO cDAO = new ClientDAO();

    @Test
    public void testSearchClientStandard1() {
        String key = "h";
        ArrayList<Client> listClient = cDAO.searchClient(key);
        Assert.assertNotNull(listClient);
        Assert.assertEquals(3, listClient.size());
    }

    @Test
    public void testSearchClientStandard2() {
        String key = "Tâm";
        ArrayList<Client> listClient = cDAO.searchClient(key);
        Assert.assertNotNull(listClient);
        Assert.assertEquals(1, listClient.size());
    }

    @Test
    public void testSearchClientException1() {
        String key = "1615";
        ArrayList<Client> listClient = new ArrayList<>();
        Assert.assertNotNull(listClient);
        Assert.assertEquals(0, listClient.size());
    }

    @Test
    public void testSearchClientException2() {
        String key = "safasefsfsa";
        ArrayList<Client> listClient = new ArrayList<>();
        Assert.assertNotNull(listClient);
        Assert.assertEquals(0, listClient.size());
    }

    @Test
    public void testAddClientStandart1() {
        Client c = new Client();
        c.setName("Đường Tăng Khải");
        c.setAddress("Khương Trung, Thanh Xuân, Hà Nội");
        Date date = new Date();
        c.setDateOfBirth(date);
        c.setIdentityNumber("032423525");
        c.setPhoneNumber("342236246");
        try {
            con.setAutoCommit(false);
            cDAO.addClient(c);
            String key = "Đường Tăng Khải";
            ArrayList<Client> listClient = cDAO.searchClient(key);
            Assert.assertNotNull(listClient);
            Assert.assertEquals("Đường Tăng Khải", listClient.get(0).getName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Assert.assertEquals(sdf.format(date), sdf.format(listClient.get(0).getDateOfBirth()));
            Assert.assertEquals("Khương Trung, Thanh Xuân, Hà Nội", listClient.get(0).getAddress());
            Assert.assertEquals("032423525", listClient.get(0).getIdentityNumber());
            Assert.assertEquals("342236246", listClient.get(0).getPhoneNumber());
            con.rollback();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
