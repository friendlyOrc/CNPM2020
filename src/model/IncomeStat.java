/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class IncomeStat {
    private Date time;
    private float income;
    private ArrayList<Bill> listBill;

    public IncomeStat() {
    }

    public IncomeStat(Date time, float income, ArrayList<Bill> listBill) {
        this.time = time;
        this.income = income;
        this.listBill = listBill;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public ArrayList<Bill> getListBill() {
        return listBill;
    }

    public void setListBill(ArrayList<Bill> listBill) {
        this.listBill = listBill;
    }

    
    
    
}
