/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class RentedRoomMonthlyService implements Serializable{
    private int id;
    private float price;
    private MonthlyService monthlyService;
    private float number;

    public RentedRoomMonthlyService() {
    }

    public RentedRoomMonthlyService(int id, float price, MonthlyService monthlyService, float number) {
        this.id = id;
        this.price = price;
        this.monthlyService = monthlyService;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public MonthlyService getMonthlyService() {
        return monthlyService;
    }

    public void setMonthlyService(MonthlyService monthlyService) {
        this.monthlyService = monthlyService;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    
}
