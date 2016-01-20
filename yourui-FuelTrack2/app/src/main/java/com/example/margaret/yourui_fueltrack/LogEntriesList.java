package com.example.margaret.yourui_fueltrack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yourui on 1/19/16.
 */
public class LogEntriesList implements Serializable {
    private static final long serialVersionUID = 0L;

    protected ArrayList<LogEntry> LogEntryList = null;

    public LogEntriesList(ArrayList<LogEntry> logEntryList) {
        LogEntryList = logEntryList;
    }


}
