package com.example.crimson.crimson.Controller.BuilderClasses;

public class SubMaker {
    private FacadeInterface silver;
    private FacadeInterface gold;
    private FacadeInterface diamond;
    String nameOfUserString,ageOfUserString,occupationOfUserString,annualIncomeOfUserString,user_identifier;
    Boolean userType;
    UserDetails userDetails;
    public SubMaker(String nameOfUserString, String ageOfUserString, String occupationOfUserString, String annualIncomeOfUserString,Boolean userType, String user_identifier)
    {
        this.nameOfUserString=nameOfUserString;
        this.ageOfUserString=ageOfUserString;
        this.occupationOfUserString=occupationOfUserString;
        this.annualIncomeOfUserString=annualIncomeOfUserString;
        this.userType=userType;
        this.user_identifier=user_identifier;
        silver= new SubSilver();
            gold = new SubGold();
            diamond = new SubDiamond();
        }

        public UserDetails makeSilver(){
            userDetails=silver.createSub(nameOfUserString,ageOfUserString,occupationOfUserString,annualIncomeOfUserString,userType,user_identifier);
            return userDetails;
        }
        public UserDetails makeGold() {
            userDetails = gold.createSub(nameOfUserString, ageOfUserString, occupationOfUserString, annualIncomeOfUserString, userType, user_identifier);
            return userDetails;
        }

        public UserDetails makeDiamond() {
            userDetails = diamond.createSub(nameOfUserString, ageOfUserString, occupationOfUserString, annualIncomeOfUserString, userType, user_identifier);
            return userDetails;
        }
    }

