/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.unit;

import dao.BillDAO;
import dao.DAO;
import java.util.ArrayList;
import model.Bill;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;

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
        Assert.assertEquals(1, listBill.size());
        for(int i=0; i<listBill.size(); i++){
            Assert.assertEquals(listBill.get(i).getId(),Integer.parseInt(key));
            System.out.println(listBill.get(i).getRentingFee());
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
                lb.get(0).getContract().getRooms().get(0).getRrsService().get(0).setNumber(newNumber);
                lb.get(0).getContract().getRooms().get(0).getRrsService().get(1).setNumber(newNumber);

                bd.updateInfoBill(lb.get(0));

                //test the new updated row
                lb.clear();
                lb = bd.searchBill(key);
                Assert.assertEquals(lb.get(0).getId(),Integer.parseInt(key));

                Assert.assertEquals(newNumber, lb.get(0).getContract().getRooms().get(0).getRrsService().get(0).getNumber(), 0.000001f);
                Assert.assertEquals(newNumber, lb.get(0).getContract().getRooms().get(0).getRrsService().get(1).getNumber(), 0.000001f);

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
        String key = "1";

        try{
                con.setAutoCommit(false);
                ArrayList<Bill> lb = bd.searchBill(key);

                float newEN = lb.get(0).getContract().getRooms().get(0).getRrmService().get(0).getNumber() + lb.get(0).getElectricityNumber();
                float newWN = lb.get(0).getContract().getRooms().get(0).getRrmService().get(1).getNumber() + lb.get(0).getWaterNumber();
                
                bd.updatePaidBill(lb.get(0));
                
                

                //test the new updated row
                lb.clear();
                lb = bd.searchBill(key);
                Assert.assertEquals(lb.get(0).getId(),Integer.parseInt(key));
                Assert.assertEquals(lb.get(0).getContract().getRooms().get(0).getRrmService().get(0).getNumber(), newEN, 0.000001f);
                Assert.assertEquals(lb.get(0).getContract().getRooms().get(0).getRrmService().get(1).getNumber(), newWN, 0.000001f);



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
}
