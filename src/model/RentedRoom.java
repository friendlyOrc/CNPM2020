/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class RentedRoom implements Serializable{
    private int id;
    private int numberOfPerson;
    private float rentingPrice;
    private Room room;
    private ArrayList<RentedRoomStaticService> rrsService;
    private ArrayList<RentedRoomMonthlyService> rrmService;
    
    public RentedRoom() {
    }

    public RentedRoom(int id, int numberOfPerson, float rentingPrice, Room room, ArrayList<RentedRoomStaticService> rrsService, ArrayList<RentedRoomMonthlyService> rrmService) {
        this.id = id;
        this.numberOfPerson = numberOfPerson;
        this.rentingPrice = rentingPrice;
        this.room = room;
        this.rrsService = rrsService;
        this.rrmService = rrmService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(int numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    public float getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(float rentingPrice) {
        this.rentingPrice = rentingPrice;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<RentedRoomStaticService> getRrsService() {
        return rrsService;
    }

    public void setRrsService(ArrayList<RentedRoomStaticService> rrsService) {
        this.rrsService = rrsService;
    }

    public ArrayList<RentedRoomMonthlyService> getRrmService() {
        return rrmService;
    }

    public void setRrmService(ArrayList<RentedRoomMonthlyService> rrmService) {
        this.rrmService = rrmService;
    }

    
}
