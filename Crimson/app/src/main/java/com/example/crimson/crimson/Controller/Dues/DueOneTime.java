package com.example.crimson.crimson.Controller.Dues;

import com.example.crimson.crimson.Interfaces.DueAPI;

public class DueOneTime implements DueAPI {

    /**
     * This is the implementation of a concrete class.
     * A due that is not periodic and to be paid only once is generated by this concrete class.
     *
     * The generateDueString is a method in the DueAPI interface that is defined in order to return the
     * The parameter Reciever Email is specific to the DueOneTime class and is added to be part of the
     * generated due string by this concrete class.
     *
     * There is a similar implementation for the Periodic Due category as well.
     */

    public String dueRecieverEmailStr;

    public DueOneTime(String dueRecieverEmail)
    {
        this.dueRecieverEmailStr = dueRecieverEmail;
    }

    public String generateDueString(String dueReceiverStr, String dueAmountSt)
    {
        return dueReceiverStr+","+dueAmountSt+","+this.dueRecieverEmailStr;
    }
}
