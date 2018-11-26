package com.example.crimson.crimson;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.channels.AlreadyBoundException;


public class user_profile extends Fragment {

    public View parentHolder;

    public FirebaseAuth mAuth;
    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference benefitmDbRef = mDbRef.child("User_Details");


    public UserDetails userDetails_object;

    private EditText nameOfUser;
    private EditText ageOfUser;
    private EditText occupationOfUser;
    private EditText annualIncomeOfUser;

    private CheckBox subscriptionCheckbox;
    private CheckBox isSilver;
    private CheckBox isGolden;
    private CheckBox isDiamond;

    private Button submitUserDetails;

    private String nameOfUserString;
    private String ageOfUserString;
    private String occupationOfUserString;
    private String annualIncomeOfUserString;
    private String user_identifier;
    private String checker;

    private boolean userType;
    private boolean isSilverUser;
    private boolean isGoldenUser;
    private boolean isDiamondUser;
    private boolean flag;

    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_user_profile, container, false);

        handler = new Handler();

        nameOfUser = (EditText)parentHolder.findViewById(R.id.UserProfileName);
        ageOfUser = (EditText)parentHolder.findViewById(R.id.editTextAge);
        occupationOfUser = (EditText)parentHolder.findViewById(R.id.editTextOccupation);
        annualIncomeOfUser = (EditText)parentHolder.findViewById(R.id.editTextIncome);
        subscriptionCheckbox = (CheckBox)parentHolder.findViewById(R.id.checkBoxSubscription);
        isSilver = (CheckBox)parentHolder.findViewById(R.id.checkBoxSilver);
        isGolden = (CheckBox)parentHolder.findViewById(R.id.checkBoxGolden);
        isDiamond = (CheckBox)parentHolder.findViewById(R.id.checkBoxDiamond);
        submitUserDetails = (Button)parentHolder.findViewById(R.id.buttonSubmitUserDetails);

        user_identifier = FirebaseAuth.getInstance().getUid();

        submitUserDetails.setEnabled(false);

        checkIfProfileExists(user_identifier);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!flag)
                {
                    submitUserDetails.setEnabled(true);
                }
                else
                {
                    new AlertDialog.Builder(parentHolder.getContext()).setTitle("Reminder").setMessage("You have already completed this section! Please contact us to change your subscription type!").create().show();
                }
            }
        }, 2000);

        submitUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameOfUserString = nameOfUser.getText().toString();
                ageOfUserString = ageOfUser.getText().toString();
                occupationOfUserString = occupationOfUser.getText().toString();
                annualIncomeOfUserString = annualIncomeOfUser.getText().toString();
                userType = subscriptionCheckbox.isChecked();
                isSilverUser = isSilver.isChecked();
                isGoldenUser = isGolden.isChecked();
                isDiamondUser = isDiamond.isChecked();


                if ((TextUtils.isEmpty(nameOfUserString)) || (TextUtils.isEmpty(ageOfUserString)|| !TextUtils.isDigitsOnly(ageOfUserString)) || (TextUtils.isEmpty(annualIncomeOfUserString)|| !TextUtils.isDigitsOnly(annualIncomeOfUserString) )|| (TextUtils.isEmpty(occupationOfUserString))) {
                    Util.makeToast(parentHolder.getContext(), "Enter All Details").show();
                }
                else if(isSilverUser){

                    userDetails_object = new UserDetails.Builder().setNameOfUser(nameOfUserString).setAgeOfUser(ageOfUserString)
                            .setOccupationOfUser(occupationOfUserString).setAnnualIncomeOfUser(annualIncomeOfUserString).setUserType(userType)
                            .setUserTypeSilver(isSilverUser).setUserIdentifier(user_identifier).create();
                    pushToDb();

                }else if(isGoldenUser){

                    userDetails_object = new UserDetails.Builder().setNameOfUser(nameOfUserString).setAgeOfUser(ageOfUserString)
                            .setOccupationOfUser(occupationOfUserString).setAnnualIncomeOfUser(annualIncomeOfUserString).setUserType(userType)
                            .setUserTypeGolden(isGoldenUser).setUserIdentifier(user_identifier).create();
                    pushToDb();
                }
                else if(isDiamondUser){

                    userDetails_object = new UserDetails.Builder().setNameOfUser(nameOfUserString).setAgeOfUser(ageOfUserString)
                            .setOccupationOfUser(occupationOfUserString).setAnnualIncomeOfUser(annualIncomeOfUserString).setUserType(userType)
                            .setUserTypeDiamond(isDiamondUser).setUserIdentifier(user_identifier).create();
                    pushToDb();
                }
                else {
                    userDetails_object = new UserDetails.Builder().setNameOfUser(nameOfUserString).setAgeOfUser(ageOfUserString)
                            .setOccupationOfUser(occupationOfUserString).setUserIdentifier(user_identifier).setAnnualIncomeOfUser(annualIncomeOfUserString).setUserType(userType).create();
                    pushToDb();

                    Util.makeToast(parentHolder.getContext(), "Your information has been saved! You are registered as a free user!").show();
                    disableCheckboxes();
                }


                if(userType){

                    User user = new WithOffers(new BasicUser());
                    Util.makeToast(parentHolder.getContext(), user.createRegisteredUser()).show();

                    disableCheckboxes();

                }
            }
        });

        return parentHolder;
    }

    public void disableCheckboxes()
    {
        subscriptionCheckbox.setEnabled(false);
        isSilver.setEnabled(false);
        isGolden.setEnabled(false);
        isDiamond.setEnabled(false);
        submitUserDetails.setEnabled(false);
    }

    public void checkIfProfileExists(final String user_identifier)
    {
        flag = false;

        benefitmDbRef.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    checker = ds.child("user_identifier").getValue(String.class);


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(checker.equals(user_identifier)) {
                                flag = true;
                            }
                        }
                    }, 200);

                    if(flag)
                    {
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw new UnsupportedOperationException();
            }
        });

    }

    public void pushToDb()
    {
        mDbRef.child("User_Details").push().setValue(userDetails_object);
    }

}