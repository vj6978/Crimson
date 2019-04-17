package com.example.crimson.crimson.PluggableAdapter;

import java.lang.reflect.Method;

public class PluggableAdapter implements IReport
{
    public Object adapt;
    public Method method;

    public PluggableAdapter(Object adapt)
    {
        this.adapt = adapt;
    }

    @Override
    public void createReport() throws Exception {
        Class c = this.adapt.getClass();
        this.method = c.getDeclaredMethod("generate");

    }
}

