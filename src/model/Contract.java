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
    private User user;
    private Client client;
    private ArrayList<RentedRoom> rooms;

    public Contract() {
    }

    public Contract(int id, Date checkin, int contractDuration, User user, Client client, ArrayList<RentedRoom> rooms) {
        this.id = id;
        this.checkin = checkin;
        this.contractDuration = contractDuration;
        this.user = user;
        this.client = client;
        this.rooms = rooms;
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

    public ArrayList<RentedRoom> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<RentedRoom> rooms) {
        this.rooms = rooms;
    }

    
}
