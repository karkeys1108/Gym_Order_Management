package com.sece.ece.Entities;

public class MemberShipPlans {
    private int plan_id;
    private String plan_name;
    private double monthly_fees;
    private int duration_months ;
    private String description;


    public MemberShipPlans(int plan_id, String plan_name, double monthly_fees, int duration_months, String description) {
        this.plan_id = plan_id;
        this.plan_name = plan_name;
        this.monthly_fees = monthly_fees;
        this.duration_months = duration_months;
        this.description = description;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public double getMonthly_fees() {
        return monthly_fees;
    }

    public void setMonthly_fees(double monthly_fees) {
        this.monthly_fees = monthly_fees;
    }

    public int getDuration_months() {
        return duration_months;
    }

    public void setDuration_months(int duration_months) {
        this.duration_months = duration_months;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
