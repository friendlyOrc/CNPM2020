package model;

import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable{
    private int id;
    private String name;
    private String address;
    private String identityNumber;
    private String phoneNumber;
    private Date dateOfBirth;

    public Client() {
    }

    public Client(int id, String name, String address, String identityNumber, String phoneNumber, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.identityNumber = identityNumber;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
	
	
}
