package com.example.margaret.yourui_fueltrack;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yourui on 1/19/16.
 */
public class LogEntry {

    private Date entryDate;
    private String Station;
    private double Odometer;
    private String FuelGrade;
    private double FuelAmount;
    private double FuelUnitCost;
    private double FuelCost;

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getStation() {
        return Station;
    }

    public void setStation(String station) {
        Station = station;
    }

    public String getOdometer() {
        return String.format("%.1f",Odometer);
    }

    public void setOdometer(double odometer) {
        Odometer = odometer;
    }

    public String getFuelGrade() {
        return FuelGrade;
    }

    public void setFuelGrade(String fuelGrade) {
        FuelGrade = fuelGrade;
    }

    public String getFuelAmount() {
        return String.format("%.3f",FuelAmount);
    }

    public void setFuelAmount(double fuelAmount) {
        FuelAmount = fuelAmount;
    }

    public String getFuelUnitCost() {
        return String.format("%.1f",FuelUnitCost);
    }

    public void setFuelUnitCost(double fuelUnitCost) {
        FuelUnitCost = fuelUnitCost;
    }

    public String getFuelCost() {
        return String.format("%.2f",FuelCost);
    }

    public void setFuelCost(double fuelCost) {
        FuelCost = fuelCost;
    }

    public LogEntry(String s, String costco, int i3, int i2, String a, int i1, int i) {
        this.entryDate = null;
        this.Station = null;
        this.Odometer = 0;
        this.FuelAmount = 0;
        this.FuelGrade = null;
        this.FuelUnitCost = 0;
        this.FuelCost = 0;

    }

    public LogEntry(Date entryDate, String station, double odometer, String fuelGrade,
                    double fuelAmount, double fuelUnitCost) {
        this.entryDate = entryDate;
        this.Station = station;
        this.Odometer = odometer;
        this.FuelGrade = fuelGrade;
        this.FuelAmount = fuelAmount;
        this.FuelUnitCost = fuelUnitCost;
        this.FuelCost = fuelUnitCost * fuelAmount;
    }

    public String toString(){
        return "Entry Date: " + entryDate + " | Station: " + Station + " | Odometer: " + String.format("%.1f",Odometer)
                + " | Fuel Grade:" + FuelGrade + " | Fuel Amount: " + String.format("%.3f",FuelAmount) + " | Fuel Unit Cost:"
                + String.format("%.1f",FuelUnitCost) + " | Fuel Cost: " +String.format("%.2f",FuelCost);
    }

    public Date getEntryDate() {
        return entryDate;
    }
}
