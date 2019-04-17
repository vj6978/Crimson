package com.example.crimson.crimson.Controller.Interceptor;


import com.example.crimson.crimson.Interfaces.Observer;
import com.example.crimson.crimson.Utility.Util;

import java.io.IOException;

public class InterceptorDriver implements Observer {

    //Concrete Framework

    public Dispatcher dispatcher;
    public ContextObjectInterface expenseContextObject;

    public InterceptorDriver()
    {
        //Concrete Framework creates Dispatcher
        dispatcher = Dispatcher.getInstance();

//        //Create Application
//        InterceptorApplication application = new InterceptorApplication();
//        application.attachInterceptor(new DataInterceptor());

    }

    @Override
    public void update() throws IOException {

        //Concrete Framework creates Context Object
        this.expenseContextObject = new ContextObject();

        //Initial Context Object Configuration
        this.expenseContextObject.setHost(Util.getIp());
        this.expenseContextObject.setMethod("GET");
        this.expenseContextObject.setPort(8080);


        //Context Object sent to dispatcher on event. per-event
        dispatcher.iterate_list(this.expenseContextObject);
    }
}
