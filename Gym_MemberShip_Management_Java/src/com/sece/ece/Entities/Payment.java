package com.sece.ece.Entities;

public class Payment {
    private int paymentID;
    private int memberID;
    private String paymentDate;
    private String paymentMethod;
    private double paymentAmount;
    private String paymentStatus;

    public Payment(int paymentID , int memberID, String paymentDate, String paymentMethod,double paymentAmount, String paymentStatus) {
        this.paymentID = paymentID;
        this.memberID = memberID;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
