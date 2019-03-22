package com.example.crimson.crimson.Controller.BuilderClasses;

public class SubSilver implements FacadeInterface {
    UserDetails userDetails_object;
    @Override
    public UserDetails createSub(String nameOfUserString, String ageOfUserString, String occupationOfUserString, String annualIncomeOfUserString,Boolean userType, String user_identifier)
    {
        userDetails_object= new UserDetails.Builder().setNameOfUser(nameOfUserString).setAgeOfUser(ageOfUserString)
                .setOccupationOfUser(occupationOfUserString).setAnnualIncomeOfUser(annualIncomeOfUserString).setUserType(Boolean.toString(userType))
                .setUserSubsType("Silver").setUserIdentifier(user_identifier).create();
        return userDetails_object;
    }
}
