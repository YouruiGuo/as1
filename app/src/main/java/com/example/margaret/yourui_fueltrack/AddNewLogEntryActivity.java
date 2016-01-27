package com.example.margaret.yourui_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

public class AddNewLogEntryActivity extends ActionBarActivity {

    private static final String FILENAME = "file.sav";
   // private ListView oldEntryList;

    private EditText dateText;
    private EditText stationText;
    private EditText OdometerText;
    private EditText FuelGradeText;
    private EditText FuelAmountText;
    private EditText FuelUnitCostText;

    private ArrayList<LogEntry> logentries = new ArrayList<LogEntry>();
    private ArrayAdapter<LogEntry> adapter;


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



        Button addButton = (Button) findViewById(R.id.Addbutton);
        Button cancelButton = (Button) findViewById(R.id.Cancelbutton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date datee = null;

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    datee = df.parse(dateText.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String station = stationText.getText().toString();
                float odometer = Float.valueOf(OdometerText.getText().toString());
                String FuelGrade = FuelGradeText.getText().toString();
                float FuelAmount = Float.valueOf(FuelAmountText.getText().toString());
                float FuelUnitCost = Float.valueOf(FuelUnitCostText.getText().toString());

                Toast.makeText(AddNewLogEntryActivity.this, "Add new log entry :)", Toast.LENGTH_SHORT).show();

                LogEntry logentry = new LogEntry(datee, station, odometer, FuelGrade, FuelAmount, FuelUnitCost);////////BUG!!!!
                logentries.add(logentry);
                adapter.notifyDataSetChanged();
                saveInFile();

                dateText.setText("");
                stationText.setText("");
                OdometerText.setText("");
                FuelGradeText.setText("");
                FuelAmountText.setText("");
                FuelUnitCostText.setText("");

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dateText.setText("");
                stationText.setText("");
                OdometerText.setText("");
                FuelGradeText.setText("");
                FuelAmountText.setText("");
                FuelUnitCostText.setText("");

               /* Intent intent = new Intent(AddNewLogEntryActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(AddNewLogEntryActivity.this,"cancel adding new log entry",Toast.LENGTH_SHORT).show();
                */
            }
        });

    }

    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<LogEntry>(AddNewLogEntryActivity.this,R.layout.list_item,logentries);
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
