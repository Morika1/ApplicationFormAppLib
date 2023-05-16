package com.example.appformlib;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ApplicationForm {

  /*
     A simple application form to leave details with the following fields:
     - Name, Phone, Email, Address, Additional info

        ** The library contains a contentProvider which initiates this class **
      + Initialize this module in your Application class/ Content Provider:
            ApplicationForm.initHelper();

          + Open an empty activity to inject the survey.
            In your activity's onCreate method generate the form:

                ApplicationForm.get().generateForm(this, mainLay);
            param: this: Sends the activity's context
            param: mainLay: The Layout of the activity to inject the form to.

            For example:
             protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main); // An empty xml file of the empty activity.
                ConstraintLayout mainLay = findViewById(R.id.main_LAY_main); // The layout of this xml file when the R.id.main_LAY_maina is the id was given to the layout
             }

          + To notify your activity when form was submitted and use the provided details:
             Callback_DetailsProtocol callback_detailsProtocol = new Callback_DetailsProtocol() {
                @Override
                public void useDetails(ArrayList<String> details) {
                  // use details. details[0] = name, details[1] = phone, details[2] = email, details[3] = address , details[4] = additional info
                     Log.d("In callback", "ready to use details");
                }
             };

           + To use the callback function to use details, in the OnCreate function of the activity set the callback:
               ApplicationForm.get().setCallback_detailsProtocol(callback_detailsProtocol);

                ** The library is not checking edge cases such as empty fields, correct email address or phone number format.
                    It assumes that details will provide correctly

     */

    private static ApplicationForm instance;

    private AppCompatImageView form_IMG_background;
    private TextInputEditText[] form_LAY_details ;
    private MaterialButton form_BTN_submit;

    private Callback_DetailsProtocol callback_detailsProtocol;

    private  ArrayList<String> details = new ArrayList<>();

    public static ApplicationForm get() { return instance;}

    public static ApplicationForm initHelper(){
        if(instance == null)
            instance = new ApplicationForm();
        return instance;
    }

    public void generateForm(Context context, ConstraintLayout mainLay){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.application_form, null);
        findViews(view);
        initViews();
        Log.d("Before glide", "No view?");
        Glide.with(context).load("https://images.pexels.com/photos/255379/pexels-photo-255379.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1").into(form_IMG_background);
        mainLay.addView(view);
    }

    public void setCallback_detailsProtocol(Callback_DetailsProtocol callback_detailsProtocol){
        this.callback_detailsProtocol = callback_detailsProtocol;
    }
    private void initViews() {
        form_BTN_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<form_LAY_details.length; i++)
                    details.add(form_LAY_details[i].getText().toString());

                callback_detailsProtocol.useDetails(details);
            }
        });
    }

    private void findViews(View view) {

        form_IMG_background = view.findViewById(R.id.form_IMG_background);

        form_LAY_details =  new TextInputEditText[]{
                view.findViewById(R.id.form_TXT_name),
                view.findViewById(R.id.form_TXT_phone),
                view.findViewById(R.id.form_TXT_email),
                view.findViewById(R.id.form_TXT_address),
                view.findViewById(R.id.form_TXT_comments)
        };

        form_BTN_submit = view.findViewById(R.id.form_BTN_submit);
    }

}
