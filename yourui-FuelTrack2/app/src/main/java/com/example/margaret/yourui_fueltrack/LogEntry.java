package com.example.margaret.yourui_fueltrack;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yourui on 1/19/16.
 */
public class LogEntry implements Serializable {

    private static final long serialVersionUID = -7534525231059324254L;

    protected Date entryDate;
    protected String Station;
    protected double Odometer;
    protected String FuelGrade;
    protected double FuelAmount;
    protected double FuelUnitCost;
    protected double FuelCost;

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



}
