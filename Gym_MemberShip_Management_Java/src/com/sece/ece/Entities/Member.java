package com.sece.ece.Entities;

import java.util.Date;

public class Member {
    private int memberId;
    private String  firstName;
    private String  lastName;
    private String  email;
    private String phoneNumber;
    private String  address;
    private String memberShipType;
    private String entryDate;
    private String  memberShipExpiry;


    public Member(int memberId, String firstName, String lastName, String email, String phoneNumber, String address, String memberShipType, String entryDate, String memberShipExpiry) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.memberShipType = memberShipType;
        this.entryDate = entryDate;
        this.memberShipExpiry = memberShipExpiry;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemberShipType() {
        return memberShipType;
    }

    public void setMemberShipType(String memberShipType) {
        this.memberShipType = memberShipType;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getMemberShipExpiry() {
        return memberShipExpiry;
    }

    public void setMemberShipExpiry(String memberShipExpiry) {
        this.memberShipExpiry = memberShipExpiry;
    }

    @Override
    public String toString() {
        return "Your details : \n" +
                " MemberId = " + memberId +
                "\n FirstName =  " + firstName  +
                "\n LastName = " + lastName +
                "\n Email = " + email +
                "\n PhoneNumber = " + phoneNumber +
                "\n Address = " + address  +
                "\n MemberShipType = " + memberShipType +
                "\n EntryDate = " + entryDate  +
                "\n MemberShipExpiry = " + memberShipExpiry ;
    }
}
