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
public class RentedRoomStaticService implements Serializable{
    private int id;
    private float price;
    private StaticService staticService;
    private float number;

    public RentedRoomStaticService() {
    }

    public RentedRoomStaticService(int id, float price, StaticService staticService, float number) {
        this.id = id;
        this.price = price;
        this.staticService = staticService;
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

    public StaticService getStaticService() {
        return staticService;
    }

    public void setStaticService(StaticService staticService) {
        this.staticService = staticService;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }
    
    
}
