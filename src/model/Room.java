package model;

import java.io.Serializable;

public class Room  implements Serializable{
    private int id;
    private String name;
    private float price;
    private int floor;
    private String type;
    private String description;

    public Room() {
            super();
    }

    public Room(int id, String name, float price, int floor, String type, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.floor = floor;
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	
	
	
	
}
