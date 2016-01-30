package com.example.margaret.yourui_fueltrack;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewLogEntriesActivity extends ActionBarActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<LogEntry> logentries = new ArrayList<LogEntry>();
    private ArrayAdapter<LogEntry> adapter;

    private ListView oldEntryList;
    private EditText TotalFuelCostText;
    private float sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log_entries);

        oldEntryList = (ListView) findViewById(R.id.LogEntriesViews);
        TotalFuelCostText = (EditText)findViewById(R.id.TotalFuelCostText);

        oldEntryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ViewLogEntriesActivity.this);
                adb.setMessage("Delete " + logentries.get(position).toString() + "?");
                adb.setCancelable(true);
                final int finalPosition = position;
                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logentries.remove(finalPosition);
                        adapter.notifyDataSetChanged();
                        saveInFile();
                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                adb.show();
                return false;
            }
        });

        oldEntryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ViewLogEntriesActivity.this);
                adb.setMessage("Do you want to edit it?");
                adb.setCancelable(true);
                final int finalPosition = position;
                adb.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // String datee = String.valueOf(logentries.get(finalPosition).entryDate);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = logentries.get(finalPosition).getEntryDate();
                        String datee = df.format(d);
                        String station = logentries.get(finalPosition).getStation();
                        String odometer = logentries.get(finalPosition).getOdometer();
                        String FuelGrade = logentries.get(finalPosition).getFuelGrade();
                        String FuelAmount = logentries.get(finalPosition).getFuelAmount();
                        String FuelUnitCost = logentries.get(finalPosition).getFuelUnitCost();

                        Intent intent = new Intent(ViewLogEntriesActivity.this, EditEntriesActivity.class);
                        intent.putExtra("finalposition",finalPosition);
                        intent.putExtra("datee",datee);
                        intent.putExtra("station",station);
                        intent.putExtra("odometer",odometer);
                        intent.putExtra("fuelgrade",FuelGrade);
                        intent.putExtra("fuelamount",FuelAmount);
                        intent.putExtra("fuelunitcost",FuelUnitCost);
                        startActivity(intent);
                        Toast.makeText(ViewLogEntriesActivity.this,"edit this log entry",Toast.LENGTH_SHORT).show();
                    }
                });
                adb.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                adb.show();
            }
        });

    }

    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<LogEntry>(ViewLogEntriesActivity.this,R.layout.list_item,logentries);
        oldEntryList.setAdapter(adapter);
        sum = 0;
        for (int i = 0; i < logentries.size(); i++){
            sum += Float.valueOf(logentries.get(i).getFuelCost());
        }
        String str = String.format("%.2f",sum);
        TotalFuelCostText.setText(str);

    }

    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html Jan-21-2016
            Type listType = new TypeToken<ArrayList<LogEntry>>() {}.getType();
            logentries = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            logentries = new ArrayList<LogEntry>();
        }catch(IOException e){
            throw new RuntimeException();
        }


    }

    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME,0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(logentries, out);
            out.flush();
            fos.close();
        }catch(FileNotFoundException e){
            throw new RuntimeException();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}
