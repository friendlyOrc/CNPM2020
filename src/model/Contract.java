/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Contract implements Serializable{
    private int id;
    private Date checkin;
    private int contractDuration;
    private float rentingPrice;
    private float deposit;
    private User user;
    private Client client;
    private Room room;

    public Contract() {
    }

    public Contract(int id, Date checkin, int contractDuration, float rentingPrice, float deposit, User user, Client client, Room room) {
        this.id = id;
        this.checkin = checkin;
        this.contractDuration = contractDuration;
        this.rentingPrice = rentingPrice;
        this.deposit = deposit;
        this.user = user;
        this.client = client;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public int getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    public float getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(float rentingPrice) {
        this.rentingPrice = rentingPrice;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    
}
