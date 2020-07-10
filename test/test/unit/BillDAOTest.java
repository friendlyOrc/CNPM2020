/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.unit;

import dao.BillDAO;
import dao.DAO;
import static java.lang.Float.parseFloat;
import java.util.ArrayList;
import model.Bill;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
import model.Contract;
import model.RoomStaticService;

/**
 *
 * @author HP075
 */
public class BillDAOTest {
    BillDAO bd = new BillDAO();
    
    @Test
    public void testSearchBillStandard(){
        String key = "1";
        ArrayList<Bill> listBill = bd.searchBill(key);
        Assert.assertNotNull(listBill);
        Assert.assertEquals(4, listBill.size());
        for(int i=0; i<listBill.size(); i++){
            Assert.assertEquals(String.valueOf(listBill.get(i).getId()).contains(key),true);
        }
        return;
    }
    @Test
    public void testSearchBillException1(){
        String key = "xxxxxx";
        ArrayList<Bill> listBill = bd.searchBill(key);
        Assert.assertNotNull(listBill);
        Assert.assertEquals(0, listBill.size());
        
        return;
    }
    @Test
    public void testSearchBillException2(){
        String key = "101";
        ArrayList<Bill> listBill = bd.searchBill(key);
        Assert.assertNotNull(listBill);
        Assert.assertEquals(0, listBill.size());
        
        return;
    }
    @Test	
    public void testUpdateBill1(){
        Connection con = DAO.con;
        float newRentingFee = 1800000;
        float newENumber = 80;
        float newWNumber = 7;
        float newSV = 150000;
        String key = "1";

        try{
            con.setAutoCommit(false);
            ArrayList<Bill> lb = bd.searchBill(key);

            lb.get(0).setElectricityNumber(newENumber);
            lb.get(0).setWaterNumber(newWNumber);
            lb.get(0).setRentingFee(newRentingFee);
            lb.get(0).setServiceFee(newSV);
            bd.updateInfoBill(lb.get(0));


            //test the new updated row
            lb.clear();
            lb = bd.searchBill(key);
            Assert.assertEquals(lb.get(0).getId(),Integer.parseInt(key));

            Assert.assertEquals(newRentingFee, lb.get(0).getRentingFee(), 0.000001f);
            Assert.assertEquals(newENumber, lb.get(0).getElectricityNumber(), 0.000001f);
            Assert.assertEquals(newWNumber, lb.get(0).getWaterNumber(), 0.000001f);
            Assert.assertEquals(newSV, lb.get(0).getServiceFee(), 0.000001f);
        }catch(Exception e){
                e.printStackTrace();
        }finally{
                try{
                        con.rollback();
                        con.setAutoCommit(true);
                }catch(Exception ex){
                        ex.printStackTrace();
                }
        }
        return;
    }
    @Test
    public void testUpdateBill2(){
        Connection con = DAO.con;
        float newNumber = 5;
        String key = "1";

        try{
            con.setAutoCommit(false);
            ArrayList<Bill> lb = bd.searchBill(key);
            lb.get(0).getContract().getRoom().getListSS().get(0).setNumber(newNumber);
            lb.get(0).getContract().getRoom().getListSS().get(1).setNumber(newNumber);

            bd.updateInfoBill(lb.get(0));

            //test the new updated row
            lb.clear();
            lb = bd.searchBill(key);
            Assert.assertEquals(lb.get(0).getId(),Integer.parseInt(key));

            Assert.assertEquals(newNumber, lb.get(0).getContract().getRoom().getListSS().get(0).getNumber(), 0.000001f);
            Assert.assertEquals(newNumber, lb.get(0).getContract().getRoom().getListSS().get(1).getNumber(), 0.000001f);

        }catch(Exception e){
                e.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return;
    }
    @Test
    public void testUpdatePaidBill1(){
        Connection con = DAO.con;
        String key = "2";

        try{
            con.setAutoCommit(false);
            ArrayList<Bill> lb = bd.searchBill(key);

            float newEN = lb.get(0).getContract().getRoom().getListMS().get(0).getNumber() + lb.get(0).getElectricityNumber();
            float newWN = lb.get(0).getContract().getRoom().getListMS().get(1).getNumber() + lb.get(0).getWaterNumber();

            bd.updatePaidBill(lb.get(0), lb.get(0).getTotal());



            //test the new updated row
            lb.clear();
            lb = bd.searchBill(key);
            Assert.assertEquals(lb.get(0).getId(),Integer.parseInt(key));
            Assert.assertEquals(lb.get(0).isBillStatus(), true);
            Assert.assertEquals(lb.get(0).getContract().getRoom().getListMS().get(0).getNumber(), newEN, 0.000001f);
            Assert.assertEquals(lb.get(0).getContract().getRoom().getListMS().get(1).getNumber(), newWN, 0.000001f);


        }catch(Exception e){
                e.printStackTrace();
        }finally{
                try{
                        con.rollback();
                        con.setAutoCommit(true);
                }catch(Exception ex){
                        ex.printStackTrace();
                }
        }
        return;
    }
    
    @Test
    public void testUpdatePaidBill2(){
        Connection con = DAO.con;
        String key = "5";

        try{
            con.setAutoCommit(false);
            ArrayList<Bill> lb = bd.searchBill(key);

            float newEN = lb.get(0).getContract().getRoom().getListMS().get(0).getNumber() + lb.get(0).getElectricityNumber();
            float newWN = lb.get(0).getContract().getRoom().getListMS().get(1).getNumber() + lb.get(0).getWaterNumber();

            boolean rs = bd.updatePaidBill(lb.get(0), (lb.get(0).getTotal() - 500));

            //test the new updated row
            lb.clear();
            lb = bd.searchBill(key);
            Assert.assertEquals(lb.get(0).getId(),Integer.parseInt(key));
            Assert.assertEquals(rs, true);
            Assert.assertEquals(lb.get(0).isBillStatus(), true);
            Assert.assertEquals(lb.get(0).getContract().getRoom().getListMS().get(0).getNumber(), newEN, 0.000001f);
            Assert.assertEquals(lb.get(0).getContract().getRoom().getListMS().get(1).getNumber(), newWN, 0.000001f);
            Assert.assertEquals(lb.get(0).getDebt(), 500, 0.000001f);
                
        }catch(Exception e){
                e.printStackTrace();
        }finally{
                try{
                    con.rollback();
                    con.setAutoCommit(true);
                }catch(Exception ex){
                        ex.printStackTrace();
                }
        }
        return;
    }
    
//------------------------------------------------
    @Test
    public void testSearchContractToAddBillStandard(){
        int key = 6;
        ArrayList<Contract> listcontract = bd.searchContractToAddBill(key);
        Assert.assertNotNull(listcontract);
        Assert.assertEquals(3, listcontract.size());
        return;
    }
    
    @Test
    public void testAddBill(){
        Connection con = DAO.con;
        float newRentingFee = 1800000;
        float newENumber = 2200;
        float newWNumber = 80;
        int key = 6;

        try{
            con.setAutoCommit(true);
            ArrayList<Contract> listconList = bd. searchContractToAddBill(key);
            Contract contract = new Contract();
            Bill b = new Bill();
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);    
            float total = 0;
            ArrayList<RoomStaticService> rrss = contract.getRoom().getListSS();
            float numberWater = contract.getRoom().getListMS().get(1).getNumber();
            float numberElectricity = contract.getRoom().getListMS().get(0).getNumber();
            float debt = bd.searchBill(contract.getId());
            String[][] value = new String[rrss.size()][6];
            for(int i= 0; i< rrss.size(); i++){
                RoomStaticService sv = rrss.get(i);
                value[i][0] = sv.getId() +"";
                value[i][1] = sv.getStaticService().getName();
                value[i][2] = sv.getNumber() + "";
                value[i][3] = sv.getPrice() + "";
                value[i][4] = sv.getNumber()*sv.getPrice() + "";
                total += Float.parseFloat(value[i][4]);
            }
            b.setId(bd.searchIdBill() + 1);
            b.setWaterNumber(newWNumber - numberWater);
            b.setElectricityNumber(newENumber  - numberElectricity);
            b.setContract(contract);
            b.setCreated(date);
            b.setServiceFee(total);
            b.setBillStatus(false);
            b.setRentingFee(newRentingFee);
            b.setDebt(debt);
            b.setTotal(b.getRentingFee()
                        + b.getElectricityNumber()* contract.getRoom().getListMS().get(0).getPrice()
                        + b.getWaterNumber() * contract.getRoom().getListMS().get(1).getPrice()
                        + b.getServiceFee() + b.getDebt());
            bd.addBill(b);
            ArrayList<Contract> listcontract = bd.searchContractToAddBill(key);
            Assert.assertNotNull(listcontract);
            Assert.assertEquals(2, listcontract.size());
            
        }catch(Exception e){
                e.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(false);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
