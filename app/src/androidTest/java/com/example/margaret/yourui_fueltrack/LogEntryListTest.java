package com.example.margaret.yourui_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by margaret on 16/1/28.
 */
public class LogEntryListTest extends ActivityInstrumentationTestCase2{
    public LogEntryListTest() {
        super(AddNewLogEntryActivity.class);
    }
    public void testAdd(){
        LogEntry logentry = new LogEntry("2016-01-28","costco",10,10,"A",10,10);
        LogEntriesList list = new LogEntriesList();

        list.AddLogEntryList(logentry);
        //LogEntry duplicatedlog = list.getLogEntry(0);
        //list.AddLogEntryList(logentry);

        /*assertEquals(duplicatedlog.getEntryDate(),logentry.getEntryDate());
        assertEquals(duplicatedlog.getStation(),logentry.getStation());
        assertEquals(duplicatedlog.getOdometer(),logentry.getOdometer());
        assertEquals(duplicatedlog.getFuelCost(),logentry.getFuelCost());
        assertEquals(duplicatedlog.getFuelUnitCost(),logentry.getFuelUnitCost());
        assertEquals(duplicatedlog.getFuelAmount(),logentry.getFuelAmount());
        assertEquals(duplicatedlog.getFuelGrade(),logentry.getFuelGrade());*/

        assertTrue(list.hasLogEntry(logentry));
    }

    public void testDelete(){
        LogEntry logentry = new LogEntry("2016-01-28","costco",10,10,"A",10,10);
        LogEntriesList list = new LogEntriesList();

        list.AddLogEntryList(logentry);
        list.RemoveLogEntryList(logentry);
        assertFalse(list.hasLogEntry(logentry));
    }

    public void testGet(){
        LogEntry logentry = new LogEntry("2016-01-28","costco",10,10,"A",10,10);
        LogEntriesList list = new LogEntriesList();
        list.AddLogEntryList(logentry);
        LogEntry returnedLogEntry = list.getLogEntry(0);
        assertEquals(returnedLogEntry.getEntryDate(),logentry.getEntryDate());
        assertEquals(returnedLogEntry.getStation(),logentry.getStation());
        assertEquals(returnedLogEntry.getOdometer(),logentry.getOdometer());
        assertEquals(returnedLogEntry.getFuelAmount(),logentry.getFuelAmount());
        assertEquals(returnedLogEntry.getFuelGrade(),logentry.getFuelGrade());
        assertEquals(returnedLogEntry.getFuelUnitCost(),logentry.getFuelUnitCost());
        assertEquals(returnedLogEntry.getFuelCost(),logentry.getFuelCost());
    }

}
