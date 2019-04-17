package com.example.crimson.crimson.PluggableAdapter;

import android.util.Log;

public class PDFReport implements IPDFReport
{
    @Override
    public String generate()
    {
       return "Downloading PDF Report";
    }
}