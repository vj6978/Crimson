package com.example.crimson.crimson.PluggableAdapter;

import android.util.Log;

public class ExcelReport implements IExcelReport
{
    @Override
    public String generate()
    {
        return "Downloading Excel Report!";
    }
}