package com.example.margaret.yourui_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ViewLogEntries(View view){
        Toast.makeText(getApplicationContext(), "View Log Entries", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ViewLogEntriesActivity.class);
        startActivity(intent);
    }

    public void AddNewLogEntry(View view){
        Toast.makeText(MainActivity.this, "Add New Log Entry", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddNewLogEntryActivity.class);
        startActivity(intent);
    }
}
