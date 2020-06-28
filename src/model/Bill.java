package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Bill implements Serializable{
    private int id;
    private int month;
    private float rentingFee;
    private float electricityNumber;
    private float waterNumber;
    private float serviceFee;
    private float debt;
    private float total;
    private Contract contract;
    private boolean billStatus;

    public Bill() {
    }

    public Bill(int id, int month, float rentingFee, float electricityNumber, float waterNumber, float serviceFee, float debt, float total, Contract contract, boolean billStatus) {
        this.id = id;
        this.month = month;
        this.rentingFee = rentingFee;
        this.electricityNumber = electricityNumber;
        this.waterNumber = waterNumber;
        this.serviceFee = serviceFee;
        this.debt = debt;
        this.total = total;
        this.contract = contract;
        this.billStatus = billStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public float getRentingFee() {
        return rentingFee;
    }

    public void setRentingFee(float rentingFee) {
        this.rentingFee = rentingFee;
    }

    public float getElectricityNumber() {
        return electricityNumber;
    }

    public void setElectricityNumber(float electricityNumber) {
        this.electricityNumber = electricityNumber;
    }

    public float getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(float waterNumber) {
        this.waterNumber = waterNumber;
    }

    public float getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public boolean isBillStatus() {
        return billStatus;
    }

    public void setBillStatus(boolean billStatus) {
        this.billStatus = billStatus;
    }

    
    
    
}
