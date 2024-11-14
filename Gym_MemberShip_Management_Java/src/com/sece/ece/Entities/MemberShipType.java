package com.sece.ece.Entities;

public class MemberShipType {
    private int membershipTypeID;
    private int  planId;
    private String membershipTypeName;
    private double price;
    private int duration;

    public MemberShipType(int membershipTypeID, int planId, String membershipTypeName, double price, int duration) {
        this.membershipTypeID = membershipTypeID;
        this.planId = planId;
        this.membershipTypeName = membershipTypeName;
        this.price = price;
        this.duration = duration;
    }

    public int getMembershipTypeID() {
        return membershipTypeID;
    }

    public void setMembershipTypeID(int membershipTypeID) {
        this.membershipTypeID = membershipTypeID;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getMembershipTypeName() {
        return membershipTypeName;
    }

    public void setMembershipTypeName(String membershipTypeName) {
        this.membershipTypeName = membershipTypeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
