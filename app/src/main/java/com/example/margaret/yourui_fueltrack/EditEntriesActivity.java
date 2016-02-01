package com.example.margaret.yourui_fueltrack;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditEntriesActivity extends ActionBarActivity {

    private static final String FILENAME = "file.sav";
  //  private ListView oldEntryList;
    private ArrayList<LogEntry> logentries = new ArrayList<LogEntry>();
    private ArrayAdapter<LogEntry> adapter;

    private EditText dateText;
    private EditText stationText;
    private EditText OdometerText;
    private EditText FuelGradeText;
    private EditText FuelAmountText;
    private EditText FuelUnitCostText;

    private int finalposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_log_entry);

        dateText = (EditText) findViewById(R.id.dateText);
        stationText = (EditText) findViewById(R.id.StationText);
        OdometerText = (EditText) findViewById(R.id.OdometerText);
        FuelGradeText = (EditText) findViewById(R.id.FuelGradeText);
        FuelAmountText = (EditText) findViewById(R.id.FuelAmountText);
        FuelUnitCostText = (EditText) findViewById(R.id.FuelUnitCostText);

        //oldEntryList = (ListView) findViewById(R.id.LogEntriesViews);

        Button addButton = (Button) findViewById(R.id.Addbutton);
        Button cancelButton = (Button) findViewById(R.id.Cancelbutton);

        String datee = getIntent().getExtras().getString("datee");
        String station = getIntent().getExtras().getString("station");
        String odometer = getIntent().getExtras().getString("odometer");
        String FuelGrade = getIntent().getExtras().getString("fuelgrade");
        String FuelAmount = getIntent().getExtras().getString("fuelamount");
        String FuelUnitCost = getIntent().getExtras().getString("fuelunitcost");
        finalposition = getIntent().getExtras().getInt("finalposition");

        dateText.setText(datee);
        stationText.setText(station);
        OdometerText.setText(odometer);
        FuelGradeText.setText(FuelGrade);
        FuelAmountText.setText(FuelAmount);
        FuelUnitCostText.setText(FuelUnitCost);

        addButton.setOnClickListener(new View.OnClickListener() {
            String station,FuelGrade;
            float odometer,FuelAmount,FuelUnitCost;
            Date datee = null;
            @Override
            public void onClick(View view) {
                logentries.remove(finalposition);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    datee = df.parse(dateText.getText().toString());
                } catch (ParseException e) {
                    datee = null;
                    e.printStackTrace();
                }
                try{
                    station = stationText.getText().toString();
                }catch(Exception e){
                    station = null;
                }
                try{
                    odometer = Float.valueOf(OdometerText.getText().toString());
                }catch (Exception e){
                    odometer = 0;
                }
                try{
                    FuelGrade = FuelGradeText.getText().toString();
                }catch (Exception e){
                    FuelGrade = null;
                }
                try{
                    FuelAmount = Float.valueOf(FuelAmountText.getText().toString());
                }catch (Exception e){
                    FuelAmount = 0;
                }
                try{
                    FuelUnitCost = Float.valueOf(FuelUnitCostText.getText().toString());
                }catch (Exception e){
                    FuelUnitCost = 0;
                }

                LogEntry logentry = new LogEntry(datee, station, odometer, FuelGrade, FuelAmount, FuelUnitCost);////////BUG!!!!
                if (logentries.contains(logentry) || (FuelAmount == 0 || FuelUnitCost == 0)){
                    Toast.makeText(EditEntriesActivity.this,"fail to edit log entry",Toast.LENGTH_SHORT).show();
                }
                else{
                    logentries.add(logentry);
                    adapter.notifyDataSetChanged();
                    saveInFile();
                    Toast.makeText(EditEntriesActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /*Intent intent = new Intent(EditEntriesActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(EditEntriesActivity.this,"cancel editing log entry",Toast.LENGTH_SHORT).show();*/
                finish();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<LogEntry>(EditEntriesActivity.this,R.layout.list_item,logentries);
        //oldEntryList.setAdapter(adapter);
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
