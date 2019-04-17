package com.example.crimson.crimson.PluggableAdapter;

import android.util.Log;

public class Report implements IReport
{

    @Override
    public void createReport() throws Exception {
        Log.i("Pluggable Adapter: ", "Downloading Report!");
    }
}
