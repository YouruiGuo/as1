package com.example.margaret.yourui_fueltrack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yourui on 1/19/16.
 */
public class LogEntriesList {

    private ArrayList<LogEntry> LogEntryList = new ArrayList<LogEntry>();

    public ArrayList<LogEntry> getLogEntryList(){
        return LogEntryList;
    }

    public void AddLogEntryList(LogEntry logEntry){
        if (LogEntryList.contains(logEntry)){
            throw new IllegalArgumentException();
        }
        else{
            LogEntryList.add(logEntry);
        }
    }

    public void RemoveLogEntryList(LogEntry logEntry){
        LogEntryList.remove(logEntry);
    }

    public boolean hasLogEntry(LogEntry logentry){
        int size = LogEntryList.size();
        for (int i = 0; i < size; i++){
            if(logentry.equals(logentry)){
                return true;
            }
        }
        return false;
    }

    public LogEntry getLogEntry(int index){
        LogEntry logentry = LogEntryList.get(index);
        return logentry;
    }
}
